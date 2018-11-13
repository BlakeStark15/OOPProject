package Admin;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

import Login.LoginFrame;
import User.UserFrame;

import javax.swing.JScrollPane;
import javax.swing.JScrollBar;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Date;
import java.awt.CardLayout;
import javax.swing.JSpinner;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import com.toedter.calendar.JDateChooser;

import Hotel.MyContainer;

import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;
import javax.swing.border.MatteBorder;
import java.awt.SystemColor;
import javax.swing.UIManager;
import javax.swing.JCheckBox;

public class AdminFrame extends JFrame {

	private JPanel contentPane;
	private JPanel Panel;
	private JTextField textFieldLocation;
	private JTextField textFieldHotel;
	private JTextField textFieldPricePerRoom;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					String username = "raghu";
					AdminFrame frame = new AdminFrame(username);
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
	public AdminFrame(String username) {
		
		Color high = new Color(0xffa07a);
		Color norm = new Color(0xffdab9);
		
		
		setTitle("BookMyHotel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 30, 900, 700);
		Panel = new JPanel();
		
		Panel.setBackground(new Color(250, 235, 215));
		Panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		setContentPane(Panel);
		Panel.setLayout(null);
		
		ImageIcon headIcon = new ImageIcon(getClass().getResource("/User.png"));
		ImageIcon logout = new ImageIcon(getClass().getResource("/Logout.png"));
		ImageIcon personicon = new ImageIcon(getClass().getResource("/person-icon.png"));
		
		JLabel label = new JLabel("-Your Stay our Responsibility");
		label.setForeground(new Color(102, 0, 51));
		label.setFont(new Font("Consolas", Font.ITALIC, 23));
		label.setBounds(277, 11, 399, 65);
		Panel.add(label);
		
		JLabel label_1 = new JLabel("BookMyHotel");
		label_1.setForeground(new Color(102, 0, 51));
		label_1.setFont(new Font("Consolas", Font.BOLD, 40));
		label_1.setBackground(Color.WHITE);
		label_1.setBounds(23, 11, 320, 65);
		Panel.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		
		panel.setBounds(718, 13, 145, 51);
		panel.setBackground(norm);
		Panel.add(panel);
		panel.setLayout(null);
		
		JLabel imageLabel = new JLabel("");
		imageLabel.setBounds(12, 8, 42, 36);
		panel.add(imageLabel);
		imageLabel.setIcon(headIcon);
		
		JLabel lblAdmin = new JLabel("Admin");
		lblAdmin.setBounds(50, 8, 67, 36);
		panel.add(lblAdmin);
		lblAdmin.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_1.setBackground(norm);
		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				new UserFrame(username).setVisible(true);
				dispose();
			}
			public void mouseEntered(MouseEvent e) {
				panel_1.setBackground(high);
			}
			public void mouseExited(MouseEvent me) {
				panel_1.setBackground(norm);
			}
		});
		panel_1.setBounds(0, 50, 145, 43);
		panel.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel imageLabel3 = new JLabel("");
		imageLabel3.setBounds(12, 0, 32, 40);
		panel_1.add(imageLabel3);
		imageLabel3.setIcon(personicon);
		
		JLabel labelUsername = new JLabel("Null");
		labelUsername.setBounds(50, 0, 90, 37);
		panel_1.add(labelUsername);
		
		labelUsername.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		labelUsername.setText(username);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent me) {
				new LoginFrame().setVisible(true);
				dispose();
			}
			public void mouseEntered(MouseEvent e) {
				panel_2.setBackground(high);
			}
			public void mouseExited(MouseEvent me) {
				panel_2.setBackground(norm);
			}
		});
		panel_2.setBounds(0, 90, 145, 43);
		panel.add(panel_2);
		panel_2.setLayout(null);
		panel_2.setBackground(norm);
		
		JLabel imageLabel2 = new JLabel("");
		imageLabel2.setBounds(12, 0, 32, 40);
		panel_2.add(imageLabel2);
		imageLabel2.setIcon(logout);
		
		JLabel lblNewLabel = new JLabel("Log Out");
		lblNewLabel.setBounds(56, 0, 69, 40);
		panel_2.add(lblNewLabel);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		
		ImageIcon locationIcon = new ImageIcon(getClass().getResource("/location-icon.png"));
		ImageIcon hotelIcon = new ImageIcon(getClass().getResource("/hotel-icon.png"));
		ImageIcon roomIcon = new ImageIcon(getClass().getResource("/room-icon.png"));
		ImageIcon peopleIcon = new ImageIcon(getClass().getResource("/people-icon.png"));
		ImageIcon priceIcon = new ImageIcon(getClass().getResource("/price-icon.png"));
		
		JLabel lblWelcomeAdmin = new JLabel("Welcome Admin,");
		lblWelcomeAdmin.setFont(new Font("Trebuchet MS", Font.ITALIC, 22));
		lblWelcomeAdmin.setBounds(23, 89, 173, 31);
		Panel.add(lblWelcomeAdmin);
		
		CardLayout clo = new CardLayout();
		
		JPanel cardPanel = new JPanel();
		cardPanel.setBounds(23, 201, 840, 420);
		cardPanel.setBackground(new Color(250, 235, 215));
		Panel.add(cardPanel);
		cardPanel.setLayout(clo);
		
		JPanel setDate = new JPanel();
		cardPanel.add(setDate, "setDate");
		setDate.setBackground(new Color(250, 235, 215));
		setDate.setLayout(null);
		
		JLabel lblCurrentDate = new JLabel("Current Date :");
		lblCurrentDate.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblCurrentDate.setBounds(205, 55, 139, 37);
		setDate.add(lblCurrentDate);
		
		JLabel dateLabel = new JLabel("null");
		dateLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		dateLabel.setBounds(401, 55, 139, 37);
		setDate.add(dateLabel);
		Date date = MyDate.getCurrDate();
		dateLabel.setText(date.toString());
		
		JLabel lblSetdate = new JLabel("Set New Date :");
		lblSetdate.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblSetdate.setBounds(205, 121, 139, 30);
		setDate.add(lblSetdate);
		
		@SuppressWarnings("restriction")
		JDateChooser dateChooser = new JDateChooser();
		dateChooser.setBounds(401, 121, 120, 30);
		setDate.add(dateChooser);
		
		JButton btnApply = new JButton("Apply Changes");
		btnApply.setForeground(Color.WHITE);
		btnApply.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnApply.setBackground(new Color(106, 90, 205));
		btnApply.setBounds(277, 206, 157, 34);
		setDate.add(btnApply);
		
		
		btnApply.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				@SuppressWarnings("restriction")
				Date udate = dateChooser.getDate();
				java.sql.Date sDate = MyDate.convToSqlDate(udate);
				MyDate.setDate(sDate);
				JOptionPane.showMessageDialog(null, "Changes Applied", "Information", JOptionPane.INFORMATION_MESSAGE);
				dateLabel.setText(MyDate.getCurrDate().toString());
				MyDate.updateStatus();
			}
		});
		
		JPanel addLocation = new JPanel();
		cardPanel.add(addLocation, "addLocation");
		addLocation.setBackground(new Color(250, 235, 215));
		addLocation.setLayout(null);
		
		JLabel lblAddNewLocation = new JLabel("Enter Location :");
		lblAddNewLocation.setBounds(59, 23, 188, 43);
		lblAddNewLocation.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		addLocation.add(lblAddNewLocation);
		
		textFieldLocation = new JTextField();
		textFieldLocation.setBounds(290, 25, 161, 34);
		addLocation.add(textFieldLocation);
		textFieldLocation.setColumns(10);
		
		JButton btnAdd = new JButton("ADD");
		btnAdd.setBounds(290, 359, 157, 34);
		
		btnAdd.setForeground(Color.WHITE);
		btnAdd.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnAdd.setBackground(new Color(106, 90, 205));
		addLocation.add(btnAdd);
		
		JLabel lblEnterHotelName = new JLabel("Enter Hotel Name :");
		lblEnterHotelName.setBounds(59, 79, 202, 43);
		lblEnterHotelName.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		addLocation.add(lblEnterHotelName);
		
		textFieldHotel = new JTextField();
		textFieldHotel.setBounds(290, 81, 161, 34);
		textFieldHotel.setColumns(10);
		addLocation.add(textFieldHotel);
		
		JLabel lblEnterNoOf = new JLabel("Max No Of Rooms :");
		lblEnterNoOf.setBounds(59, 139, 226, 43);
		lblEnterNoOf.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		addLocation.add(lblEnterNoOf);
		
		JLabel lblMaxNoOf = new JLabel("Max No Of People");
		lblMaxNoOf.setBounds(59, 195, 202, 43);
		lblMaxNoOf.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		addLocation.add(lblMaxNoOf);
		
		JLabel lblPerRoom = new JLabel("Per Room :");
		lblPerRoom.setBounds(59, 221, 202, 43);
		lblPerRoom.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		addLocation.add(lblPerRoom);
		
		JLabel lblPricePerRoom = new JLabel("Price Per Room :");
		lblPricePerRoom.setBounds(59, 271, 211, 43);
		lblPricePerRoom.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		addLocation.add(lblPricePerRoom);
		
		JLabel lblLocationIcon = new JLabel("");
		lblLocationIcon.setBounds(463, 23, 32, 34);
		addLocation.add(lblLocationIcon);
		lblLocationIcon.setIcon(locationIcon);
		
		JLabel lblHotelIcon = new JLabel("");
		lblHotelIcon.setBounds(463, 79, 32, 34);
		addLocation.add(lblHotelIcon);
		lblHotelIcon.setIcon(hotelIcon);
		
		JLabel lblRoomIcon = new JLabel("");
		lblRoomIcon.setBounds(463, 135, 32, 34);
		addLocation.add(lblRoomIcon);
		lblRoomIcon.setIcon(roomIcon);
		
		JLabel lblPeopleIcon = new JLabel("");
		lblPeopleIcon.setBounds(463, 206, 32, 34);
		addLocation.add(lblPeopleIcon);
		lblPeopleIcon.setIcon(peopleIcon);
		
		JLabel lblPriceIcon = new JLabel("");
		lblPriceIcon.setBounds(463, 267, 32, 34);
		addLocation.add(lblPriceIcon);
		lblPriceIcon.setIcon(priceIcon);
		
		JSpinner spinnerNoOfRooms = new JSpinner();
		spinnerNoOfRooms.setBounds(290, 137, 161, 34);
		addLocation.add(spinnerNoOfRooms);
		
		JSpinner spinnerPeoplePerRoom = new JSpinner();
		spinnerPeoplePerRoom.setBounds(290, 203, 161, 34);
		addLocation.add(spinnerPeoplePerRoom);
		
		textFieldPricePerRoom = new JTextField();
		textFieldPricePerRoom.setBounds(290, 267, 161, 34);
		textFieldPricePerRoom.setColumns(10);
		addLocation.add(textFieldPricePerRoom);
		
		JCheckBox chckbxFreeWifi = new JCheckBox("Free WiFi");
		chckbxFreeWifi.setBackground(new Color(255, 218, 185));
		chckbxFreeWifi.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		chckbxFreeWifi.setBounds(573, 79, 188, 43);
		addLocation.add(chckbxFreeWifi);
		
		JLabel lblAmenitiesProvided = new JLabel("Amenities Provided");
		lblAmenitiesProvided.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblAmenitiesProvided.setBounds(573, 23, 188, 43);
		addLocation.add(lblAmenitiesProvided);
		
		JCheckBox chckbxCompBr = new JCheckBox("Complementary BreakFast");
		chckbxCompBr.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		chckbxCompBr.setBackground(new Color(255, 218, 185));
		chckbxCompBr.setBounds(573, 140, 238, 43);
		addLocation.add(chckbxCompBr);
		
		JCheckBox chckbxSPool = new JCheckBox("Swimming Pool");
		chckbxSPool.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		chckbxSPool.setBackground(new Color(255, 218, 185));
		chckbxSPool.setBounds(573, 206, 151, 43);
		addLocation.add(chckbxSPool);
		
		JCheckBox chckbxFreeParking = new JCheckBox("Free Parking");
		chckbxFreeParking.setFont(new Font("Trebuchet MS", Font.BOLD, 16));
		chckbxFreeParking.setBackground(new Color(255, 218, 185));
		chckbxFreeParking.setBounds(573, 271, 151, 43);
		addLocation.add(chckbxFreeParking);
		
		JButton btnSetDate = new JButton("Set Date");
		btnSetDate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				clo.show(cardPanel, "setDate");
				
			}
		});
		btnSetDate.setForeground(Color.WHITE);
		btnSetDate.setBackground(Color.GREEN);
		btnSetDate.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnSetDate.setBounds(23, 153, 414, 35);
		Panel.add(btnSetDate);
		
		JButton btnAddLocation = new JButton("Add Hotel");
		btnAddLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				clo.show(cardPanel, "addLocation");
			}
		});
		btnAddLocation.setForeground(Color.WHITE);
		btnAddLocation.setFont(new Font("Segoe UI", Font.PLAIN, 20));
		btnAddLocation.setBackground(Color.GREEN);
		btnAddLocation.setBounds(452, 153, 414, 35);
		Panel.add(btnAddLocation);
		
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(), 134);
				panel.setBackground(new Color(0xffebcd));
			}
		});
		
		Panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(norm);
				panel.setSize(panel.getWidth(), 51);
			}
		});
		
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				String location = textFieldLocation.getText();
				String hotel = textFieldHotel.getText();
				int maxNoOfRooms = (Integer) spinnerNoOfRooms.getValue();
				int maxNoOfPeoplePerRoom = (Integer)spinnerPeoplePerRoom.getValue();
				
				if(location.equals("")||hotel.equals("")||maxNoOfRooms==0||maxNoOfPeoplePerRoom==0||textFieldPricePerRoom.equals(""))
				{
					JOptionPane.showMessageDialog(null, "Invalid Input. Please Check the Input and TRY AGAIN!", "ERROR" , JOptionPane.ERROR_MESSAGE);
				}
				else
				{
					try 
					{
						double pricePerRoom = Double.parseDouble(textFieldPricePerRoom.getText());
						boolean amenities[] = new boolean[4];
						
						amenities[0] = chckbxFreeWifi.isSelected();
						amenities[1] = chckbxFreeWifi.isSelected();
						amenities[2] = chckbxFreeWifi.isSelected();
						amenities[3] = chckbxFreeWifi.isSelected();
						
						MyContainer.addHotel(hotel, location, maxNoOfRooms, maxNoOfPeoplePerRoom, pricePerRoom,amenities);
						JOptionPane.showMessageDialog(null, "Hotel Added", "Information", JOptionPane.INFORMATION_MESSAGE);
					}
					catch (NumberFormatException e) 
					{
						// TODO: handle exception
						JOptionPane.showMessageDialog(null, "Invalid Input. Please Check the Input and TRY AGAIN!", "ERROR" , JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
}
