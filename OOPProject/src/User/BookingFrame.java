package User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Login.LoginFrame;
import Login.MyConnection;
import Login.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.Date;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.UIManager;

import com.toedter.calendar.JCalendar;
import com.toedter.calendar.JDateChooser;

import Admin.MyDate;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.EtchedBorder;

public class BookingFrame extends JFrame {
	
	private JPanel contentPane;
	private JTextField textField_2;
	private JTextField textField_3;
	private JLabel errLabel;
	DefaultComboBoxModel<String> model;
	Date checkInDate;
	Date checkOutDate;
	int noOfRooms;
	int noOfPeople;
	String location;
	String username;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BookingFrame frame = new BookingFrame("blake");
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public BookingFrame(String username) 
	
	{
		this.username = username;
		//setting the panel
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
		
		//Customising and adding the drop-down menu
		JComboBox locationMenu = new JComboBox();
		locationMenu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) 
			{
				location = (String)locationMenu.getSelectedItem();		//updates location to the newly selected value
			}
		});
		
		//Adding a Model to the drop-down menu to display all unique locations
		model = new DefaultComboBoxModel();
		locationMenu.setModel(model);
		locationMenu.setToolTipText("");
		locationMenu.setBounds(306, 168, 204, 29);
		contentPane.add(locationMenu);
		populateModel();
		
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
		contentPane.add(textField_3);
		
		//calender for check-in date
		JDateChooser chooserCheckIn = new JDateChooser();
		chooserCheckIn.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) 
			{
				if(evt.getPropertyName().equals("date"))
				{
					checkInDate = chooserCheckIn.getDate();
					Date currentDate = MyDate.getCurrDate();
					if(!checkInDate.after(currentDate))
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
		chooserCheckOut.addPropertyChangeListener(new PropertyChangeListener() {
			public void propertyChange(PropertyChangeEvent evt) 
			{
				if(evt.getPropertyName().equals("date"))
				{
					checkOutDate = chooserCheckOut.getDate();
					if(!checkOutDate.after(checkInDate))
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
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(396, 475, 89, 42);
		btnNewButton.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) 
			{
				fetchDetails();
			}
		});
		btnNewButton.setBackground(new Color(0, 204, 204));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnNewButton);
		
		ImageIcon settingIcon = new ImageIcon(getClass().getResource("/settings.png"));
		ImageIcon userIcon = new ImageIcon(getClass().getResource("/User.png"));
		ImageIcon logoutIcon = new ImageIcon(getClass().getResource("/Logout.png"));
		ImageIcon personicon = new ImageIcon(getClass().getResource("/person-icon.png"));
		
		Color high = new Color(0xffa07a);
		Color norm = new Color(0xffdab9);
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() 
		{
			public void mouseEntered(MouseEvent e) 
			{
				panel.setBackground(new Color(0xffebcd));
				panel.setSize(panel.getWidth(), 103);
			}
		});
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBackground(new Color(255, 218, 185));
		panel.setBounds(712, 13, 158, 52);
		contentPane.add(panel);
		
		JLabel label = new JLabel("");
		label.setBounds(12, 8, 42, 36);
		panel.add(label);
		label.setIcon(userIcon);
		
		JLabel label_1 = new JLabel(username);
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		label_1.setBounds(50, 8, 95, 36);
		panel.add(label_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBackground(new Color(255, 218, 185));
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_1.setBackground(high);
			}
			public void mouseExited(MouseEvent me)
			{
				panel_1.setBackground(norm);
			}
			public void mouseClicked(MouseEvent me)
			{
				new LoginFrame().setVisible(true);
				dispose();
			}
		});
		panel_1.setBounds(0, 50, 158, 53);
		panel.add(panel_1);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(12, 6, 32, 40);
		panel_1.add(label_2);
		label_2.setIcon(logoutIcon);
		
		JLabel label_3 = new JLabel("Log Out");
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		label_3.setBounds(56, 6, 69, 40);
		panel_1.add(label_3);
		
		contentPane.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(), 52);
				panel.setBackground(norm);
			}
		});
	}
	
	//to populate the model to be used in the drop-down menu
	public void populateModel()
	{
		if(MyConnection.getConnection())
		{
			String query = "select location from hotelinfo";
			ResultSet rSet = MyConnection.executeQuery(query);
			try
			{
				while(rSet.next())
				{
					String nextLoc = rSet.getString(1);
					if(model.getIndexOf(nextLoc)==-1)			//to check if location already exists
						model.addElement(nextLoc);
				}
			} catch (SQLException e)
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//to get valid input from user and process the details
	public void fetchDetails()
	{
		String str = textField_2.getText();
		try
		{
			noOfPeople = Integer.parseInt(str);
		}
		catch(NumberFormatException e1)
		{
			errLabel.setText("Enter valid number for Number of People.");
			JOptionPane.showMessageDialog(null, errLabel);
		}
		str = textField_3.getText();
		try
		{
			noOfRooms = Integer.parseInt(str);
		}
		catch(NumberFormatException e2)
		{
			errLabel.setText("Enter valid number for Number of Rooms.");
			JOptionPane.showMessageDialog(null, errLabel);
		}
		//processing the details that have been input in this frame
		new Booking(username, location, convertToSQL(checkInDate), convertToSQL(checkOutDate), noOfPeople, noOfRooms);
		this.setVisible(false);
	}
	
	//to covert java.util date to java.sql date 
	public static java.sql.Date convertToSQL(Date date)
	{
		java.sql.Date dobj = new java.sql.Date(date.getTime());
		return dobj;
	}
}
