package Hotel;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

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
	
	
}
