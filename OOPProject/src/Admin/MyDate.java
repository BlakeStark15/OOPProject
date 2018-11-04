package Admin;

import java.util.Date;

public class MyDate 
{
	private static Date currDate;
	private final static long MILLISECONDS_PER_DAY = (long)8.64e+7;
	
	
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
