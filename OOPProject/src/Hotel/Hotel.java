package Hotel;

import java.io.Serializable;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.sql.Date;

import Admin.MyDate;
import Login.MyConnection;
import User.Booking;


public class Hotel implements Serializable
{
	private int roomsOccupied[] = new int[10000];
	public String hotelName;
	public String location;
	private int maxNoOfRooms;
	private double rating = 0;
	private String s[] = new String[1000];
	private int noOfUserFeedbacks;
	public int maxNoOfPeoplePerRoom;
	public double pricePerRoom;
	private int waitQueue[] = new int[1000];
	private int waitQueueSize;
	public boolean amenities[];
	
	public Hotel(String hotelname, String location, int maxNoOfRooms, int maxNoOfPeoplePerRoom, double pricePerRoom, boolean amenities[])
	{
		this.hotelName = hotelname;
		this.location = location;
		this.maxNoOfRooms = maxNoOfRooms;
		this.maxNoOfPeoplePerRoom = maxNoOfPeoplePerRoom;
		this.pricePerRoom = pricePerRoom;
		this.waitQueueSize = 0;
		this.noOfUserFeedbacks = 0;
		this.amenities = amenities;
	}
	
	public boolean checkRoomAvailability(Date checkIn, Date checkOut, int noOfRoomsRequired, int noOfPeople)
	{
		int flag = 0;
		int idxCheckIn = MyDate.getIndex(checkIn);
		int idxCheckOut = MyDate.getIndex(checkOut);
		if(idxCheckIn>idxCheckOut)
			return false;
		
		for(int i = idxCheckIn;i<=idxCheckOut;i++)
		{
			if(maxNoOfRooms - roomsOccupied[i]<noOfRoomsRequired)
			{
				flag = 1;
				break;
			}
		}
		if(noOfPeople>maxNoOfPeoplePerRoom*noOfRoomsRequired)
			flag = 1;
		
		if(flag==0)
			return true;
		else
			return false;
	}
	
	public boolean bookRooms(String username, Date checkIn, Date checkOut, int noOfRoomsRequired, int noOfPeople)
	{
		
		if(!checkRoomAvailability(checkIn, checkOut, noOfRoomsRequired, noOfPeople))
			return false;
		
		int idxCheckIn = MyDate.getIndex(checkIn);
		int idxCheckOut = MyDate.getIndex(checkOut);
		for(int i = idxCheckIn;i<=idxCheckOut;i++)
		{
			roomsOccupied[i] += noOfRoomsRequired;
		}
		
		String query = "INSERT INTO bookinginfo (`username`, `refno`, `location`, `hotel`, `checkin`, `checkout`, `status`, `noofrooms`, `noofpeople`)" 
					+ "VALUES ('"+username+"', '"+Booking.getRefno()+"', '"+location+"', '"+hotelName+"', '"+checkIn+"', '"+checkOut+"', 'CONFIRMED', '"+noOfRoomsRequired+"', '"+noOfPeople+"')";
		System.out.println("**");
		MyConnection.getConnection();
		MyConnection.updateQuery(query);
		MyConnection.closeConnection();
		return true;
	}
	
	public boolean cancelBooking(int refno)
	{
		
		String query = "select checkin, checkout, noofrooms from bookinginfo where refno = '"+refno+"'";
		MyConnection.getConnection();
		ResultSet resultSet = MyConnection.executeQuery(query);
		Date checkIn;
		Date checkOut;
		int noOfRoomsRequired;
		try
		{
			if(resultSet.next())
			{
				checkIn = resultSet.getDate(1);
				checkOut = resultSet.getDate(2);
				noOfRoomsRequired = resultSet.getInt(3);
			}
			else
				return false;
		}
		catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			return false;
		}
		int idxCheckIn = MyDate.getIndex(checkIn);
		int idxCheckOut = MyDate.getIndex(checkOut);
		for(int i = idxCheckIn; i <= idxCheckOut; i++)
		{
			if(roomsOccupied[i]<noOfRoomsRequired)
				return false;
			roomsOccupied[i] -= noOfRoomsRequired; 
		}
		
		query = "UPDATE bookinginfo SET status = 'CANCELLED' WHERE `refno` = '" + refno + "'";
		MyConnection.updateQuery(query);
		MyConnection.closeConnection();
		updateWaitQueue();
		return true;
	}
	
	public boolean modifyBooking(int refno, Date previousCheckIn, Date previousCheckOut, Date newCheckIn, Date newCheckOut, int prevNoOfRoomsRequired, int newNoOfRoomsRequired, int prevNoOfPeople, int newNoOfPeople)
	{
		int idxPreviousCheckIn = MyDate.getIndex(previousCheckIn);
		int idxPreviousCheckOut = MyDate.getIndex(previousCheckOut);
		int idxNewCheckIn = MyDate.getIndex(newCheckIn);
		Date currDate = MyDate.getCurrDate();
		int idxCurrDate = MyDate.getIndex(currDate);
		if(idxCurrDate>=idxPreviousCheckIn-3)
			return false;
		if(idxNewCheckIn<=idxCurrDate)
			return false;
		int flag = 0,idx = 0;
		for(int i=idxPreviousCheckIn;i<=idxPreviousCheckOut;i++)
		{
			if(roomsOccupied[i]<prevNoOfRoomsRequired)
			{
				flag = 1;
				idx  = i;
				break;
			}
			roomsOccupied[i] -= prevNoOfRoomsRequired;
		}
		if(flag==1) //Resetting array as it is since modification not possible.
		{
			for(int i=idxPreviousCheckIn;i<idx;i++)
			{
				roomsOccupied[i] += prevNoOfRoomsRequired;
			}
			return false;
		}
		else
		{
			if(checkRoomAvailability(newCheckIn, newCheckOut, newNoOfRoomsRequired, newNoOfPeople))
			{
				String query = "UPDATE bookinginfo SET checkin = '"+newCheckIn+"', checkout = '"+newCheckOut+"', status = 'CONFIRMED', noofrooms = "+newNoOfRoomsRequired+", noofpeople = "+newNoOfPeople+" WHERE (refno = "+refno+")";
				MyConnection.getConnection();
				MyConnection.updateQuery(query);
				MyConnection.closeConnection();
				return true;
			}
			else 
			{
				///Reseting array.
				for(int i=idxPreviousCheckIn;i<=idxPreviousCheckOut;i++)
					roomsOccupied[i] += prevNoOfRoomsRequired;
				return false;
			}
		}
			
	}
	
	public int enrollForWaitList(String username, Date checkIn, Date checkOut, int noOfRoomsRequired, int noOfPeople)
	{
		int refno = Booking.getRefno();
		waitQueue[waitQueueSize] = refno;
		waitQueueSize++;
		String query = "INSERT INTO bookinginfo (`username`, `refno`, `location`, `hotel`, `checkin`, `checkout`, `status`, `noofrooms`, `noofpeople`)" 
				+ "VALUES ('"+username+"', '"+refno+"', '"+location+"', '"+hotelName+"', '"+checkIn+"', '"+checkOut+"', 'WAITING', '"+noOfRoomsRequired+"', '"+noOfPeople+"')";
		
		MyConnection.getConnection();
		MyConnection.updateQuery(query);
		MyConnection.closeConnection();
		return refno;
	}
	
	public void updateWaitQueue()
	{
		MyConnection.getConnection();
		int idxs[] = new int[1000];
		int size = 0;
	
		for(int i=0;i<waitQueueSize;i++)
		{
			int refno = waitQueue[i];
			String query = "select checkin, checkout, noofrooms, noofpeople from bookinginfo where refno = '"+refno+"'";
			try
			{
				ResultSet resultSet = MyConnection.executeQuery(query);
				if(resultSet.next())
				{
					Date checkIn = resultSet.getDate(1);
					Date checkOut = resultSet.getDate(2);
					int noOfRooms = resultSet.getInt(3);
					int noOfPeople = resultSet.getInt(4);
					
					if(checkRoomAvailability(checkIn, checkOut, noOfRooms, noOfPeople))
					{
						idxs[size] = i;
						size++;
						int idxCheckIn = MyDate.getIndex(checkIn);
						int idxCheckOut = MyDate.getIndex(checkOut);
						for(int j = idxCheckIn;j<=idxCheckOut;j++)
						{
							roomsOccupied[j] += noOfRooms;
						}
						query = "UPDATE bookinginfo SET status = 'CONFIRMED' WHERE `refno` = '" + refno + "'";
						MyConnection.updateQuery(query);
					}
				}
			}
			catch(Exception e)
			{
				System.out.println("Connection error");
			}
		}
		int temp = size;
		int j = 0, g = 0, i = 0;
		while(temp>0&&g<waitQueueSize)
		{
			if(g==idxs[j])
			{
				j++;
				g++;
				temp--;
			}
			else
			{
				waitQueue[i] = waitQueue[g]; 
				i++;
				g++;
			}
		}
		waitQueueSize -= size;
		MyConnection.closeConnection();
	}
	
	public void takeFeedback(int refno, String feedback, int userrating)
	{
		String query = "UPDATE bookinginfo SET feedback = '"+feedback+"'" +", rating = "+userrating+" WHERE `refno` = '" + refno + "'";
		
		rating *= noOfUserFeedbacks;
		rating += userrating;
		noOfUserFeedbacks++;
		rating /= noOfUserFeedbacks;
		MyConnection.getConnection();
		MyConnection.updateQuery(query);
		MyConnection.closeConnection();
	}
	
	public double getRating()
	{
		return rating;
	}
	
	public ArrayList<String> getFeedback()
	{
		MyConnection.getConnection();
		ArrayList<String> feedback = new ArrayList<String>();
		String query = "SELECT username,feedback,rating,checkout FROM bookinginfo WHERE location = '"+location+"' AND hotel = '"+hotelName+"' AND status = 'COMPLETED'";
		ResultSet rs = MyConnection.executeQuery(query);
		try
		{
			while(rs.next())
			{
				feedback.add(rs.getString(1));
				feedback.add(rs.getString(2));
				feedback.add(rs.getString(3));
				feedback.add(rs.getString(4));
			}
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return feedback;
	}
	
	public boolean modifyCheck(int refno, Date newCheckIn, Date newCheckOut, int newNoOfRoomsRequired, int newNoOfPeople)
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
			}
		} 
		catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(prevNoOfPeople);
		int idxPreviousCheckIn = MyDate.getIndex(previousCheckIn);
		int idxPreviousCheckOut = MyDate.getIndex(previousCheckOut);
		int idxNewCheckIn = MyDate.getIndex(newCheckIn);
		Date currDate = MyDate.getCurrDate();
		System.out.println(currDate);
		int idxCurrDate = MyDate.getIndex(currDate);
		if(idxCurrDate>=idxPreviousCheckIn-3)
			return false;
		if(idxNewCheckIn<=idxCurrDate)
			return false;
		int flag = 0,idx = 0;
		for(int i=idxPreviousCheckIn;i<=idxPreviousCheckOut;i++)
		{
			if(roomsOccupied[i]<prevNoOfRoomsRequired)
			{
				flag = 1;
				idx  = i;
				break;
			}
			roomsOccupied[i] -= prevNoOfRoomsRequired;
		}
		if(flag==1) //Resetting array as it is since modification not possible.
		{
			for(int i=idxPreviousCheckIn;i<idx;i++)
			{
				roomsOccupied[i] += prevNoOfRoomsRequired;
			}
			return false;
		}
		else
		{
			if(checkRoomAvailability(newCheckIn, newCheckOut, newNoOfRoomsRequired, newNoOfPeople))
			{
				for(int i=idxPreviousCheckIn;i<=idxPreviousCheckOut;i++)//Reset array.
				{
					roomsOccupied[i] += prevNoOfRoomsRequired;
				}
				return true;
			}
			else 
			{
				///Reseting array.
				for(int i=idxPreviousCheckIn;i<=idxPreviousCheckOut;i++)
					roomsOccupied[i] += prevNoOfRoomsRequired;
				return false;
			}
		}
			
	}
}
