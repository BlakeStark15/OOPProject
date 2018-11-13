package User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import Admin.MyDate;
import Hotel.Hotel;
import Hotel.MyContainer;
import Login.MyConnection;

public class ModifyFrame extends JFrame
{
	private static int refno;
	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	Date checkInDate;
	Date checkOutDate;
	int noOfRooms;
	int noOfPeople;
	Date upCheckInDate;
	Date upCheckOutDate;
	int upNoOfRooms;
	int upNoOfPeople;
	String location;
	JLabel errLabel;
	String hotelName;
	String username;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					ModifyFrame frame = new ModifyFrame(2,"black");
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ModifyFrame(int refno, String username)
	{
		MyContainer myContainer = MyContainer.getContainer();
		this.refno = refno;
		fetch();			//to get details of booking against refno
		
		this.username = username;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 30, 900,700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//defining a single error label for use by all errors
		errLabel = new JLabel("");
		errLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		UIManager.put("Button.background", Color.white);
		
		//BookMyHotel label
		JLabel lblBookMyHotel = new JLabel("BookMyHotel");
		lblBookMyHotel.setBackground(new Color(255, 255, 255));
		lblBookMyHotel.setForeground(new Color(102, 0, 51));
		lblBookMyHotel.setFont(new Font("Consolas", Font.BOLD, 40));
		lblBookMyHotel.setBounds(23, 11, 320, 65);
		contentPane.add(lblBookMyHotel);
		
		//Setting original location
		JLabel lblLoc = new JLabel(location);
		lblLoc.setBounds(306, 168, 204, 29);
		lblLoc.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		contentPane.add(lblLoc);
		
		//location label
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblLocation.setBounds(88, 168, 124, 29);
		contentPane.add(lblLocation);
		
		//check-in date label
		JLabel lblCheckIn = new JLabel("Check-in Date");
		lblCheckIn.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblCheckIn.setBounds(88, 219, 139, 29);
		contentPane.add(lblCheckIn);
		
		//check-out date label
		JLabel lblCheckOut = new JLabel("Check-out Date");
		lblCheckOut.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblCheckOut.setBounds(88, 270, 139, 29);
		contentPane.add(lblCheckOut);
		
		//number of people label
		JLabel lblNoOfPeople = new JLabel("Number of People");
		lblNoOfPeople.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNoOfPeople.setBounds(88, 372, 194, 29);
		contentPane.add(lblNoOfPeople);
		
		//text-field for number of people
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(306, 372, 204, 29);
		textField_2.setText(Integer.toString(noOfPeople));
		contentPane.add(textField_2);
		
		//number of rooms label
		JLabel lblNoOfRooms = new JLabel("Number of Rooms");
		lblNoOfRooms.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNoOfRooms.setBounds(88, 321, 194, 29);
		contentPane.add(lblNoOfRooms);
		
		//text-field for number of rooms
		textField_3 = new JTextField();
		textField_3.setColumns(10);
		textField_3.setBounds(306, 321, 204, 29);
		textField_3.setText(Integer.toString(noOfRooms));
		contentPane.add(textField_3);
		
		//calender for check-in date
		JDateChooser chooserCheckIn = new JDateChooser();
		chooserCheckIn.setDate(checkInDate);
		upCheckInDate = chooserCheckIn.getDate();
		chooserCheckIn.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) 
			{
				if(evt.getPropertyName().equals("date"))
				{
					upCheckInDate = chooserCheckIn.getDate();
					Date currentDate = MyDate.getCurrDate();
					if(!upCheckInDate.after(currentDate))
					{
						errLabel.setText("Enter valid check-in date.");
						JOptionPane.showMessageDialog(null, errLabel);
					}
				}
			}
		});
		chooserCheckIn.setBounds(306, 219, 204, 29);
		contentPane.add(chooserCheckIn);
		
		//calender for check-out date
		JDateChooser chooserCheckOut = new JDateChooser();
		chooserCheckOut.setDate(checkOutDate);
		upCheckOutDate = chooserCheckOut.getDate();
		chooserCheckOut.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) 
			{
				if(evt.getPropertyName().equals("date"))
				{
					upCheckOutDate = chooserCheckOut.getDate();
					if(!upCheckOutDate.after(upCheckInDate))
					{
						errLabel.setText("Enter valid check-out date.");
						JOptionPane.showMessageDialog(null, errLabel);
					}
				}
			}
		});
		chooserCheckOut.setBounds(306, 270, 204, 29);
		contentPane.add(chooserCheckOut);
		
		//submit button
		
		JButton btnModify = new JButton("Modify");
		btnModify.setBounds(337, 475, 148, 42);
		btnModify.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) 
			{
				modify();
				MyContainer.storeContainer(myContainer);
			}
		});
		btnModify.setBackground(new Color(0, 204, 204));
		btnModify.setForeground(new Color(255, 255, 255));
		btnModify.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModify.setVisible(false);
		contentPane.add(btnModify);
		
		JButton btnCheck = new JButton("Check Availability");
		btnCheck.setBounds(337, 475, 148, 42);
		btnCheck.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) 
			{
				if(!valid())
					return;
				Hotel hotelObj = myContainer.getHotel(hotelName, location);
				if(hotelObj.modifyCheck(refno, convertToSQL(upCheckInDate), convertToSQL(upCheckOutDate), upNoOfRooms, upNoOfPeople))
				{
					errLabel.setText("Available!");
					JOptionPane.showMessageDialog(null, errLabel);
					btnCheck.setVisible(false);
					btnModify.setVisible(true);
				}
				else
				{
					errLabel.setText("Given number of rooms not available for this period");
					JOptionPane.showMessageDialog(null, errLabel);
				}
			}
		});
		btnCheck.setBackground(new Color(0, 204, 204));
		btnCheck.setForeground(new Color(255, 255, 255));
		btnCheck.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnCheck);
		
		JLabel lblHotel = new JLabel("Hotel");
		lblHotel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblHotel.setBounds(88, 115, 124, 29);
		contentPane.add(lblHotel);
		
		JLabel lblHot = new JLabel(hotelName);
		lblHot.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblHot.setBounds(306, 115, 204, 29);
		contentPane.add(lblHot);
	}
	
	//updates class variables by comparing against details of refno
	void fetch()
	{
		MyConnection.getConnection();
		String query = "SELECT * FROM bookinginfo WHERE refno = "+refno;
		ResultSet rSet = MyConnection.executeQuery(query);
		try
		{
			if(rSet.next())
			{
				checkInDate = rSet.getDate(5);
				checkOutDate = rSet.getDate(6);
				noOfRooms = rSet.getInt(9);
				noOfPeople = rSet.getInt(10);
				location = rSet.getString(3);
				hotelName = rSet.getString(4);
			}
		}
		catch(SQLException e)
		{
		}
	}
	
	//
	boolean valid()
	{
		String str = textField_2.getText();
		try
		{
			upNoOfPeople = Integer.parseInt(str);
		}
		catch(NumberFormatException e1)
		{
			errLabel.setText("Enter valid number for Number of People.");
			JOptionPane.showMessageDialog(null, errLabel);
			return false;
		}
		str = textField_3.getText();
		try
		{
			upNoOfRooms = Integer.parseInt(str);
		}
		catch(NumberFormatException e2)
		{
			errLabel.setText("Enter valid number for Number of Rooms.");
			JOptionPane.showMessageDialog(null, errLabel);
			return false;
		}
		return true;
	}
	void modify()
	{
		//processing the details that have been input in this frame
		new Booking(refno, username, hotelName, location, convertToSQL(upCheckInDate), convertToSQL(upCheckOutDate), upNoOfPeople, upNoOfRooms);
		errLabel.setText("Successfully modified.");
		JOptionPane.showMessageDialog(null, errLabel);
		new MyBookingFrame(username).setVisible(true);
		this.dispose();
	}
	
	public static java.sql.Date convertToSQL(Date date)
	{
		java.sql.Date dobj = new java.sql.Date(date.getTime());
		return dobj;
	}
}
