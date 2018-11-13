package User;

import Login.MyConnection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import Hotel.Hotel;
import Hotel.MyContainer;

import java.io.Serializable;

public class Booking implements Serializable
{
	static String username;
	static int refno;
	static java.sql.Date checkInDate;
	static java.sql.Date checkOutDate;
	static int noOfRooms;
	static int noOfPeople;
	static String location;
	static ArrayList<Hotel> availableHotels;
	static ArrayList<Hotel> unAvailableHotels;
	Hotel hotelObj;
	
	//constructor for starting a new booking
	Booking(String username,String loc, java.sql.Date cin, java.sql.Date cout, int ppl, int rooms)
	{
		Booking.username = username;												//to be removed <-- IMPORTANT									
		refno = getRefno();
		location = loc;
		checkInDate = cin;
		checkOutDate = cout;
		noOfPeople = ppl;
		noOfRooms = rooms;  													////To be removed  <-- IMPORTANT
		availableHotels = new ArrayList<Hotel>();
		unAvailableHotels = new ArrayList<Hotel>();
		getHotels();
	}
	
	//constructor for modifying a booking
	Booking(int refno, String username, String hotelName, String location, java.sql.Date cin, java.sql.Date cout, int ppl, int rooms)
	{
		Booking.username = username;																					
		Booking.refno = refno;
		checkInDate = cin;
		checkOutDate = cout;
		noOfPeople = ppl;
		noOfRooms = rooms;
		this.location = location;   													
		MyContainer obj = MyContainer.getContainer();
		hotelObj = obj.getHotel(hotelName, location);
		modify();
	}
	
	Booking(String username)
	{
		Booking.username = username;
	}
	
	static
	{
		MyConnection.getConnection();
		String query = "select refno from bookinginfo";
		ResultSet resultSet = MyConnection.executeQuery(query);
		int x = 0;
		try
		{
			while(resultSet.next()) {
				try
				{
					x = resultSet.getInt(1);
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x++;
		refno = x;
	}
	
	public static int getRefno()
	{
		MyConnection.getConnection();
		String query = "select refno from bookinginfo";
		ResultSet resultSet = MyConnection.executeQuery(query);
		int x = 0;
		try
		{
			while(resultSet.next()) {
				try
				{
					x = resultSet.getInt(1);
				} catch (SQLException e)
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		x++;
		refno = x;
		return x;
	}
	
	//getting appropriate available and unavailable hotels
	void getHotels()
	{
		MyContainer mycontainer = MyContainer.getContainer();
		int i=1;
		while(i<=MyContainer.getNoOfHotels())
		{
			Hotel hotelObj = mycontainer.hotel[i];
			if((hotelObj.location).equals(location))
			{
				if(hotelObj.checkRoomAvailability(checkInDate, checkOutDate, noOfRooms, noOfPeople))
				{
					if(!hotelObj.equals(null))
						availableHotels.add(hotelObj);
				}
				else
				{
					if(!hotelObj.equals(null)&&hotelObj.maxNoOfPeoplePerRoom*Booking.noOfRooms>=Booking.noOfPeople)
						unAvailableHotels.add(hotelObj);
				}
			}
			i++;
		}
		HotelListingFrame obj = new HotelListingFrame();
		obj.setVisible(true);
	}
	
	void modify()
	{
		MyConnection.getConnection();
		String query = "SELECT checkin,checkout,noofrooms,noofpeople FROM bookinginfo WHERE refno = "+refno;
		ResultSet rSet = MyConnection.executeQuery(query);
		java.sql.Date previousCheckIn = null;
		java.sql.Date previousCheckOut = null;
		int prevNoOfRoomsRequired = 0;
		int prevNoOfPeople = 0;
		try
		{
			if(rSet.next())
			{
				previousCheckIn = rSet.getDate(1);
				previousCheckOut = rSet.getDate(2);
				prevNoOfRoomsRequired = rSet.getInt(3);
				prevNoOfPeople = rSet.getInt(4);
				hotelObj.modifyBooking(refno, previousCheckIn, previousCheckOut, checkInDate, checkOutDate, prevNoOfRoomsRequired, noOfRooms, prevNoOfPeople, noOfPeople);
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ArrayList<Hotel> getAvailable()
	{
		return availableHotels;
	}
	
	public static ArrayList<Hotel> getUnavailable()
	{
		return unAvailableHotels;
	}
	
	public static String getLocation()
	{
		return location;
	}
}

