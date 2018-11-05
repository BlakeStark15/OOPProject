package User;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;


public class Booking implements Serializable
{
	public static int refno;
	
	static
	{
		refno = 1;//Default
		Refno refnoobj;
		try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream("refno.txt")))
		{
			refnoobj = (Refno) objectInputStream.readObject();
			refno = refnoobj.getRefno();
		}
		catch(Exception e) 
		{
			System.out.println("Couldn't open file 'refno.txt'");
		}
	}
	
	public static int getRefno()
	{
		refno++;
		
		Refno refnoobj = new Refno(refno);
		try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("refno.txt")))
		{
			objectOutputStream.writeObject(refnoobj);
		}
		catch(Exception e)
		{
			System.out.println("Couldn't open file 'refno.txt'");
		}
		return refno;
	}
	
	public static void resetRefno()
	{
		refno = 0;
		Refno refnoobj = new Refno(refno);
		try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream("refno.txt")))
		{
			objectOutputStream.writeObject(refnoobj);
		}
		catch(Exception e)
		{
			System.out.println("Couldn't open file 'refno.txt'");
		}
	}
	
}

@SuppressWarnings("serial")
class Refno implements Serializable
{
	private int refno;
	
	Refno(int refno)
	{
		this.refno = refno;
	}
	
	public int getRefno()
	{
		return refno;
	}
	
	public void updateRefno(int refno)
	{
		this.refno = refno;
	}
}
