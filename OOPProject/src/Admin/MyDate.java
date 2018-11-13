package Admin;

import java.beans.Statement;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import Login.MyConnection;

public class MyDate 
{
	private static Date currDate;
	private final static long MILLISECONDS_PER_DAY = (long)8.64e+7;
	
	static
	{
		Date date = MyDate.getDate(2018, 1, 1);//Default
		try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("currdate.txt")))
		{
			date = (Date) objectInputStream.readObject();
		}
		catch(Exception e) 
		{
			System.out.println("Could'nt recover current date information");
		}
		currDate = date;
	}
	
	public static Date getDate(int year, int month, int date)
	{
		@SuppressWarnings("deprecation")
		Date d = new Date(year-1900,month-1,date);
		return d;
	}
	
	public static Date getCurrDate()
	{
		return currDate;
	}
	
	public static void setDate(Date date)
	{
		currDate = date;
		try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("currdate.txt")))
		{
			objectOutputStream.writeObject(currDate);
		}
		catch(Exception e)
		{
			System.out.println("IO Exception");
		}
	}
	
	public static int getIndex(Date d)//Returns the index of the date on the array.
	{
		Date d2 = getDate(2000, 1, 1);
		long l1 = d.getTime();
		long l2 = d2.getTime();
		int ans = (int)((l1-l2)/MILLISECONDS_PER_DAY);
		return ans;
	}
	
	public static java.sql.Date convToSqlDate(java.util.Date udate)
	{
		return new java.sql.Date(udate.getTime());
	}
	
	public static void updateStatus()
	{
		MyConnection.getConnection();
		String query = "select checkin, status from bookinginfo";
		ResultSet rSet = MyConnection.executeQuery(query);
		try {
			Connection connection = null;
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/db", "root", "root");
			java.sql.Statement statement = connection.createStatement();
			while(rSet.next())
			{
				
				Date checkInDate = rSet.getDate(1);
				String status = rSet.getString(2);
				String newStatus = "COMPLETED";//Default.
				if(MyDate.getIndex(checkInDate)<=MyDate.getIndex(currDate))
				{
					if(status.equals("CONFIRMED"))
					{
						newStatus = "COMPLETED";
					}
					else
					if(status.equals("WAITING"))
					{
						newStatus = "INCOMPLETE";
					}
					else 
					{
						newStatus = status;
					}
					query = "UPDATE bookinginfo SET status = '"+newStatus+"'";
					try {
						statement.executeUpdate(query);
					}
					catch(Exception e) {
						e.printStackTrace();
					}
				}
				
			}
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			MyConnection.closeConnection();
		}
	}
}
