package User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Hotel.Hotel;
import Hotel.MyContainer;
import java.awt.TextArea;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class HotelDetails extends JFrame
{
	static String hotelName;
	static JFrame prevFrame;
	String location;
	static boolean isAvailable;
	private JPanel contentPane;
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
					HotelDetails frame = new HotelDetails(null,hotelName,prevFrame,isAvailable);
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
	public HotelDetails(MyContainer myContainer, String hotelName, JFrame prevFrame, boolean isAvailable)
	{
		this.hotelName = hotelName;
		this.prevFrame = prevFrame;
		this.isAvailable = isAvailable;
		location = Booking.getLocation();
		
		Hotel hotelObj = myContainer.getHotel(hotelName, location);
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(180, 30, 644,683);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//hotelName label
		JLabel lblHotel = new JLabel(hotelName);
		lblHotel.setBounds(23, 11, 320, 65);
		lblHotel.setBackground(new Color(255, 255, 255));
		lblHotel.setForeground(new Color(102, 0, 51));
		lblHotel.setFont(new Font("Consolas", Font.BOLD, 40));
		contentPane.add(lblHotel);
		
		//amenities label and panel

		ImageIcon tick = new ImageIcon("tick.png");
		ImageIcon cross = new ImageIcon("cross.png");
		
		JLabel lblAmenities_1 = new JLabel("Amenities 1");
		lblAmenities_1.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblAmenities_1.setBounds(23, 126, 112, 24);
		if(hotelObj.amenities[0]==true)
			lblAmenities_1.setIcon(tick);
		else
			lblAmenities_1.setIcon(cross);
		contentPane.add(lblAmenities_1);
		
		JLabel lblAmenities_2 = new JLabel("Amenities 2");
		lblAmenities_2.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblAmenities_2.setBounds(23, 163, 112, 24);
		if(hotelObj.amenities[1]==true)
			lblAmenities_2.setIcon(tick);
		else
			lblAmenities_2.setIcon(cross);
		contentPane.add(lblAmenities_2);
		
		JLabel lblAmenities_3 = new JLabel("Amenities 3");
		lblAmenities_3.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblAmenities_3.setBounds(23, 200, 112, 24);
		if(hotelObj.amenities[2]==true)
			lblAmenities_3.setIcon(tick);
		else
			lblAmenities_3.setIcon(cross);
		contentPane.add(lblAmenities_3);
		
		JLabel lblAmenities_4 = new JLabel("Amenities 4");
		lblAmenities_4.setFont(new Font("Roboto", Font.PLAIN, 20));
		lblAmenities_4.setBounds(23, 241, 112, 24);
		if(hotelObj.amenities[3]==true)
			lblAmenities_4.setIcon(tick);
		else
			lblAmenities_4.setIcon(cross);
		contentPane.add(lblAmenities_4);
		
		JLabel lblAmenities = new JLabel("Amenities");
		lblAmenities.setBounds(23, 89, 167, 24);
		lblAmenities.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		contentPane.add(lblAmenities);
		
		JLabel lblReviews = new JLabel("Reviews");
		lblReviews.setFont(new Font("Trebuchet MS", Font.PLAIN, 23));
		lblReviews.setBounds(23, 298, 167, 24);
		contentPane.add(lblReviews);
		
		TextArea reviews = new TextArea();
		reviews.setBounds(23, 328, 568, 209);
		reviews.setFont(new Font("Roboto", Font.PLAIN, 16));
		reviews.setEditable(false);
		contentPane.add(reviews);
		
		ArrayList<String> feedback = hotelObj.getFeedback();
		for(int i=0;i<feedback.size();i+=4)
		{
			String username = feedback.get(i);
			String feed = feedback.get(i+1);
			String rating = feedback.get(i+2);
			String checkout = feedback.get(i+3);
			reviews.append(username+":\nRating: ");
			for(int j=0;j<Integer.parseInt(rating);j++)
				reviews.append("*");
			reviews.append("\n"+feed+"\n"+"(on "+checkout+")\n");
			reviews.append("-----------------------------------------------------------------------------\n\n");
		}
		
		JButton btn = new JButton();
		btn.setBounds(254, 565, 89, 42);
		btn.setBackground(new Color(0, 204, 204));
		btn.setForeground(new Color(255, 255, 255));
		btn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btn);
		
		if(isAvailable==true)
		{
			btn.setText("Book");
			btn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					(new ConfirmBooking(myContainer,hotelObj,true)).setVisible(true);
					Fobj.dispose();
				}
			});
		}
		
		else
		{
			btn.setText("Enroll");
			btn.addActionListener(new ActionListener() 
			{
				public void actionPerformed(ActionEvent arg0)
				{
					(new ConfirmBooking(myContainer,hotelObj,false)).setVisible(true);
					Fobj.dispose();
				}
			});
		}
	}
}
