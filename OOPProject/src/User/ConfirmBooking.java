package User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigInteger;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Hotel.Hotel;
import Hotel.MyContainer;
import Login.MyConnection;

import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JButton;

public class ConfirmBooking extends JFrame
{

	private JPanel contentPane;
	public static Hotel hotel;
	private JTextField textField;
	public static boolean isAvailable;
	JFrame Fobj = this;

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
					ConfirmBooking frame = new ConfirmBooking(null,hotel,isAvailable);
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
	public ConfirmBooking(MyContainer myContainer,Hotel hotel, boolean isAvailable)
	{
		this.hotel = hotel;
		this.isAvailable = isAvailable;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(180, 30, 900,700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//BookMyHotel label
		JLabel lblBookMyHotel = new JLabel("BookMyHotel");
		lblBookMyHotel.setBackground(new Color(255, 255, 255));
		lblBookMyHotel.setForeground(new Color(102, 0, 51));
		lblBookMyHotel.setFont(new Font("Consolas", Font.BOLD, 40));
		lblBookMyHotel.setBounds(23, 11, 320, 65);
		contentPane.add(lblBookMyHotel);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.WHITE);
		panel.setBounds(23, 136, 445, 395);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblYourBookingDetails = new JLabel("Your booking details");
		lblYourBookingDetails.setHorizontalAlignment(SwingConstants.CENTER);
		lblYourBookingDetails.setFont(new Font("Trebuchet MS", Font.PLAIN, 24));
		lblYourBookingDetails.setBounds(98, 26, 249, 35);
		panel.add(lblYourBookingDetails);
		
		JLabel lblLocation = new JLabel("Location");
		lblLocation.setHorizontalAlignment(SwingConstants.RIGHT);
		lblLocation.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblLocation.setBounds(82, 94, 113, 26);
		panel.add(lblLocation);
		
		JLabel lblNewLabel = new JLabel(Booking.location);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblNewLabel.setBounds(245, 94, 138, 29);
		panel.add(lblNewLabel);
		
		JLabel lblHotel = new JLabel("Hotel");
		lblHotel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblHotel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblHotel.setBounds(82, 135, 113, 26);
		panel.add(lblHotel);
		
		JLabel label_1 = new JLabel(hotel.hotelName);
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setFont(new Font("Roboto", Font.PLAIN, 20));
		label_1.setBounds(245, 135, 138, 29);
		panel.add(label_1);
		
		JLabel lblCheckIn = new JLabel("Check In");
		lblCheckIn.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheckIn.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblCheckIn.setBounds(82, 175, 113, 26);
		panel.add(lblCheckIn);
		
		JLabel label_3 = new JLabel(Booking.checkInDate.toString());
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setFont(new Font("Roboto", Font.PLAIN, 20));
		label_3.setBounds(245, 175, 138, 29);
		panel.add(label_3);
		
		JLabel lblCheckOut = new JLabel("Check Out");
		lblCheckOut.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCheckOut.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblCheckOut.setBounds(82, 215, 113, 26);
		panel.add(lblCheckOut);
		
		JLabel label_5 = new JLabel(Booking.checkOutDate.toString());
		label_5.setHorizontalAlignment(SwingConstants.CENTER);
		label_5.setFont(new Font("Roboto", Font.PLAIN, 20));
		label_5.setBounds(245, 215, 138, 29);
		panel.add(label_5);
		
		JLabel lblNumberOfRooms = new JLabel("Number of Rooms");
		lblNumberOfRooms.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfRooms.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNumberOfRooms.setBounds(34, 255, 161, 26);
		panel.add(lblNumberOfRooms);
		
		JLabel label_7 = new JLabel(Integer.toString(Booking.noOfRooms));
		label_7.setHorizontalAlignment(SwingConstants.CENTER);
		label_7.setFont(new Font("Roboto", Font.PLAIN, 20));
		label_7.setBounds(245, 255, 138, 29);
		panel.add(label_7);
		
		JLabel lblNumberOfPeople = new JLabel("Number of People");
		lblNumberOfPeople.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNumberOfPeople.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblNumberOfPeople.setBounds(12, 295, 183, 26);
		panel.add(lblNumberOfPeople);
		
		JLabel label_9 = new JLabel(Integer.toString(Booking.noOfPeople));
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setFont(new Font("Roboto", Font.PLAIN, 20));
		label_9.setBounds(245, 295, 138, 29);
		panel.add(label_9);
		
		JLabel lblPrice = new JLabel("Price");
		lblPrice.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPrice.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblPrice.setBounds(12, 336, 183, 26);
		panel.add(lblPrice);
		
		JLabel label_2 = new JLabel(Double.toString(hotel.pricePerRoom*Booking.noOfRooms));
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setFont(new Font("Roboto", Font.PLAIN, 20));
		label_2.setBounds(245, 336, 138, 29);
		panel.add(label_2);
		
		JLabel lblEnterAadharNumber = new JLabel("Enter Aadhar Number");
		lblEnterAadharNumber.setBounds(517, 236, 206, 34);
		lblEnterAadharNumber.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		contentPane.add(lblEnterAadharNumber);
		
		textField = new JTextField();
		textField.setFont(new Font("Roboto", Font.PLAIN, 17));
		textField.setBounds(517, 283, 320, 34);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JLabel errLabel = new JLabel("");
		errLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		UIManager.put("Button.background", Color.white);
		
		JButton btn = new JButton();
		btn.setBounds(612, 354, 124, 42);
		btn.setBackground(new Color(0, 204, 204));
		btn.setForeground(new Color(255, 255, 255));
		btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btn);
		if(isAvailable)
			btn.setText("Book");
		else
			btn.setText("Enroll");
		btn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				String aadhar = textField.getText();
				if(!(aadhar.matches("[0-9]+")&&aadhar.length()==12))
				{
					errLabel.setText("Enter valid Aadhar number.");
					JOptionPane.showMessageDialog(null, errLabel);
				}
				else
				{
					if(isAvailable)
					{
						hotel.bookRooms(Booking.username, Booking.checkInDate, Booking.checkOutDate, Booking.noOfRooms, Booking.noOfPeople);
						MyContainer.storeContainer(myContainer);
						errLabel.setText("Successfully booked.");
						JOptionPane.showMessageDialog(null, errLabel);
						Fobj.dispose();
					}
					else
					{
						hotel.enrollForWaitList(Booking.username, Booking.checkInDate, Booking.checkOutDate, Booking.noOfRooms, Booking.noOfPeople);
						MyContainer.storeContainer(myContainer);
						errLabel.setText("Successfully enrolled.");
						JOptionPane.showMessageDialog(null, errLabel);
						Fobj.dispose();
					}
				}
			}
		});
	}
}
