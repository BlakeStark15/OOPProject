package Hotel;

import java.io.Serializable;
import java.util.Date;
import Admin.MyDate;

@SuppressWarnings("serial")
public class Hotel implements Serializable
{
	private int roomsOccupied[] = new int[10000];
	private String hotelName;
	private String location;
	private int maxNoOfRooms;
	private double rating = 0;
	private String s[] = new String[1000];
	private int noOfUserFeedbacks;
	private int maxNoOfPeoplePerRoom;
	private double pricePerRoom;
	private int waitQueue[] = new int[1000];
	
	public Hotel(String hotelname, String location, int maxNoOfRooms, int maxNoOfPeoplePerRoom, double pricePerRoom)
	{
		this.hotelName = hotelname;
		this.location = location;
		this.maxNoOfRooms = maxNoOfRooms;
		this.maxNoOfPeoplePerRoom = maxNoOfPeoplePerRoom;
		this.pricePerRoom = pricePerRoom;
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
	
	public boolean bookRooms(Date checkIn, Date checkOut, int noOfRoomsRequired, int noOfPeople)
	{
		if(!checkRoomAvailability(checkIn, checkOut, noOfRoomsRequired, noOfPeople))
			return false;
		int idxCheckIn = MyDate.getIndex(checkIn);
		int idxCheckOut = MyDate.getIndex(checkOut);
		for(int i = idxCheckIn;i<=idxCheckOut;i++)
		{
			roomsOccupied[i] += noOfRoomsRequired;
		}
		return true;
	}
	
	public boolean cancelBooking(Date checkIn, Date checkOut, int noOfRoomsRequired, int noOfPeople)
	{
		int idxCheckIn = MyDate.getIndex(checkIn);
		int idxCheckOut = MyDate.getIndex(checkOut);
		for(int i = idxCheckIn; i <= idxCheckOut; i++)
		{
			if(roomsOccupied[i]<noOfRoomsRequired)
				return false;
			roomsOccupied[i] -= noOfRoomsRequired; 
		}
		return true;
	}
	
	public boolean modifyBooking(Date previousCheckIn, Date previousCheckOut, Date newCheckIn, Date newCheckOut, int prevNoOfRoomsRequired, int newNoOfRoomsRequired, int prevNoOfPeople, int newNoOfPeople)
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
				bookRooms(newCheckIn, newCheckOut, newNoOfRoomsRequired, newNoOfPeople);
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
