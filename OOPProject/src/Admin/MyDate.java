package Admin;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;

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
	
	public static void setDate(int year, int month, int date)
	{
		currDate = getDate(year, month, date);
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
}
