package User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import Hotel.Hotel;
import Hotel.MyContainer;

import javax.swing.JScrollPane;
import javax.swing.border.LineBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

import javax.swing.table.*;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class HotelListingFrame extends JFrame
{
	ArrayList<Hotel> availableHotels;
	ArrayList<Hotel> unAvailableHotels;
	String location;
	JFrame frameObj = this;
	private JPanel contentPane;

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
					HotelListingFrame frame = new HotelListingFrame();
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
	public HotelListingFrame()
	{
		MyContainer myContainer = MyContainer.getContainer();
		//setting the panel
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 30, 900,700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//BookMyHotel label
		JLabel lblBookMyHotel = new JLabel("BookMyHotel");
		lblBookMyHotel.setBounds(23, 11, 320, 65);
		lblBookMyHotel.setBackground(new Color(255, 255, 255));
		lblBookMyHotel.setForeground(new Color(102, 0, 51));
		lblBookMyHotel.setFont(new Font("Consolas", Font.BOLD, 40));
		contentPane.add(lblBookMyHotel);
		
		//setting the available and unavailable hotels and location
		availableHotels = Booking.getAvailable();
		unAvailableHotels = Booking.getUnavailable();
		location = Booking.getLocation();
		
		
		//AvailableHotels panel
		JLabel lblAvailableHotels = new JLabel("Available Hotels");
		lblAvailableHotels.setBounds(23, 89, 167, 24);
		lblAvailableHotels.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		contentPane.add(lblAvailableHotels);
		String colHead[] = {"Name","Price"};
		DefaultTableModel tableModelAvail = new DefaultTableModel(colHead,0);
		JTable availTable = new JTable(tableModelAvail);
		availTable.setRowHeight(50);
		for(int i=0;i<availableHotels.size();i++)					//populating model for JTable
		{
			Hotel hotel = availableHotels.get(i);
			Object[] obj = {hotel.hotelName,hotel.pricePerRoom};
			tableModelAvail.addRow(obj);
		}
		availTable.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int index = availTable.getSelectedRow();
				String hotelName = tableModelAvail.getValueAt(index,0).toString();
				new HotelDetails(myContainer, hotelName,frameObj,true).setVisible(true);
			}
		});
		JScrollPane availScrollPane = new JScrollPane(availTable);
		availScrollPane.setSize(847, 229);
		availScrollPane.setLocation(23, 126);
		availScrollPane.setPreferredSize(new Dimension(120,90));
		contentPane.add(availScrollPane);
		
		//UnavailableHotels panel
		JLabel lblUnavailableHotels = new JLabel("Unavailable Hotels");
		lblUnavailableHotels.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblUnavailableHotels.setBounds(23, 371, 200, 24);
		contentPane.add(lblUnavailableHotels);
		DefaultTableModel tableModelUnavail = new DefaultTableModel(colHead,0);
		JTable unavailTable = new JTable(tableModelUnavail);
		unavailTable.setRowHeight(50);
		for(int i=0;i<unAvailableHotels.size();i++)						//populating model for JTable
		{
			Hotel hotel = unAvailableHotels.get(i);
			Object[] obj = {hotel.hotelName,hotel.pricePerRoom};
			tableModelUnavail.addRow(obj);
		}
		JScrollPane unavailScrollPane = new JScrollPane(unavailTable);
		unavailScrollPane.setSize(847, 229);
		unavailScrollPane.setLocation(23, 409);
		unavailScrollPane.setPreferredSize(new Dimension(120,90));
		contentPane.add(unavailScrollPane);
		unavailTable.addMouseListener(new MouseAdapter() 
		{
			@Override
			public void mouseClicked(MouseEvent e) 
			{
				int index = unavailTable.getSelectedRow();
				String hotelName = tableModelUnavail.getValueAt(index,0).toString();
				HotelDetails obj = new HotelDetails(myContainer,hotelName,frameObj,false);
				obj.setVisible(true);
			}
		});
	}
}
