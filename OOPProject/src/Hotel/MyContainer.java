package Hotel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;

import Login.MyConnection;

public class MyContainer implements Serializable
{
	public Hotel hotel[] = new Hotel[100];
	public static int noOfHotels;
	
	public static MyContainer getContainer()
	{
		try(ObjectInputStream oInputStream = new ObjectInputStream(new FileInputStream("dataFile.txt")))
		{
			MyContainer myContainer = (MyContainer) oInputStream.readObject();
			return myContainer;
		}
		catch(Exception e)
		{
			System.out.println("Couldn't load file**");
			return null;	
		}
	}
	
	public static int getNoOfHotels()
	{
		String query = "SELECT COUNT(*) FROM hotelinfo";
		MyConnection.getConnection();
		ResultSet rs = MyConnection.executeQuery(query);
		try
		{
			if(rs.next())
				noOfHotels = rs.getInt(1);
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return noOfHotels;
	}
	
	public static void storeContainer(MyContainer myContainer)
	{
		try(ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream("dataFile.txt")))
		{
			oStream.writeObject(myContainer);
		}
		catch(Exception e) {
			System.out.println("Couldn't write on to the file");
		}
	}
	
	public  Hotel getHotel(String hotelName, String location)
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
	
	public static void addHotel(String hotelName, String location, int maxNoOfRooms, int maxNoOfPeoplePerRoom, double pricePerRoom, boolean amenities[])
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
		
		query = "INSERT INTO `db`.`hotelinfo` (`sno`, `location`, `hotel`, `maxNoOfRooms`, `maxNoOfPeoplePerRoom`, `pricePerRoom`, `amenities_1`, `amenities_2`, `amenities_3`, `amenities_4`)" 
				+ "VALUES ('"+sno+"', '"+location+"', '"+hotelName+"', '"+maxNoOfRooms+"', '"+maxNoOfPeoplePerRoom+"', '"+pricePerRoom+"', '"+convBoolToInt(amenities[0])+"', '"+convBoolToInt(amenities[1])+"', '"+convBoolToInt(amenities[2])+"', '"+convBoolToInt(amenities[3])+"')";
		
		MyContainer myContainer = MyContainer.getContainer();
		myContainer.hotel[sno] = new Hotel(hotelName, location, maxNoOfRooms, maxNoOfPeoplePerRoom, pricePerRoom, amenities);
		MyContainer.storeContainer(myContainer);
		
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
				boolean amenities[] = new boolean[4];
				amenities[0] = resultSet.getBoolean(7);
				amenities[1] = resultSet.getBoolean(8);
				amenities[2] = resultSet.getBoolean(9);
				amenities[3] = resultSet.getBoolean(10);
				myContainer.hotel[sno] = new Hotel(hotelName, location, maxNoOfRooms, maxNoOfPeoplePerRoom, pricePerRoom, amenities);
			}
			MyContainer.storeContainer(myContainer);
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}
	public static Hotel getHotel(int refno)
	{
		MyConnection.getConnection();
		String query= "select hotel,location from bookinginfo where refno ="+refno;
		ResultSet resultSet = MyConnection.executeQuery(query);	
		
		String hotel = "";
		String location = "";
		try {
			if(resultSet.next()) {
				hotel = resultSet.getString(1);
				location = resultSet.getString(2);
				MyContainer obj = MyContainer.getContainer();
				return obj.getHotel(hotel,location);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public static int convBoolToInt(boolean b) 
	{
		if(b)
			return 1;
		else
			return 0;
	}
}
