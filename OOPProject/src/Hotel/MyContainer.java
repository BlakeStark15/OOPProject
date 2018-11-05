package Hotel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;

import javax.xml.ws.AsyncHandler;

import Login.MyConnection;

public class MyContainer implements Serializable
{
	public Hotel hotel[] = new Hotel[100];
	public int noOfHotels;
	
	public static MyContainer getContainer()
	{
		try(ObjectInputStream oInputStream = new ObjectInputStream(new FileInputStream("dataFile.txt")))
		{
			MyContainer myContainer = (MyContainer) oInputStream.readObject();
			return myContainer;
		}
		catch(Exception e)
		{
			System.out.println("Could'nt load file");
			return null;	
		}
	}
	
	public static void storeContainer(MyContainer myContainer)
	{
		try(ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream("dataFile.txt")))
		{
			oStream.writeObject(myContainer);
		}
		catch(Exception e) {
			System.out.println("Could'nt write on to the file");
		}
	}
	
	public Hotel getHotel(String hotelName, String location)
	{
		String query = "select sno from hotelinfo where hotel = '"+hotelName+"' and location = '"+location+"'";
		MyConnection.getConnection();
		ResultSet resultSet = MyConnection.executeQuery(query);
		try
		{
			if(resultSet.next())
			{
				int sno = resultSet.getInt(1);
				return hotel[sno];
			}
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return null;
		}
		finally
		{
			MyConnection.closeConnection();
		}
		return null;
	}
	
	public static void addHotel(String hotelName, String location, int maxNoOfRooms, int maxNoOfPeoplePerRoom, int pricePerRoom)
	{
		int sno = 0;
		String query = "select sno from hotelinfo";
		MyConnection.getConnection();
		ResultSet resultSet = MyConnection.executeQuery(query);
		try
		{
			while(resultSet.next())
			{
				sno = resultSet.getInt(1);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		sno++;
		
		query = "INSERT INTO `db`.`hotelinfo` (`sno`, `location`, `hotel`, `maxNoOfRooms`, `maxNoOfPeoplePerRoom`, `pricePerRoom`)" 
				+ "VALUES ('"+sno+"', '"+location+"', '"+hotelName+"', '"+maxNoOfRooms+"', '"+maxNoOfPeoplePerRoom+"', '"+pricePerRoom+"')";
		MyConnection.updateQuery(query);
		MyConnection.closeConnection();
	}
	
	public static void reset()//Resets all hotel arrays to zero stores in the file.
	{
		String query = "select * from hotelinfo";
		MyConnection.getConnection();
		MyContainer myContainer = new MyContainer();
		ResultSet resultSet = MyConnection.executeQuery(query);
		try
		{
			while(resultSet.next()) 
			{
				int sno = resultSet.getInt(1);
				String location = resultSet.getString(2);
				String hotelName = resultSet.getString(3);
				int maxNoOfRooms = resultSet.getInt(4);
				int maxNoOfPeoplePerRoom = resultSet.getInt(5);
				int pricePerRoom = resultSet.getInt(6);
				
				myContainer.hotel[sno] = new Hotel(hotelName, location, maxNoOfRooms, maxNoOfPeoplePerRoom, pricePerRoom);
			}
			MyContainer.storeContainer(myContainer);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
}
