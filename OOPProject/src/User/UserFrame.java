package User;
import Login.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.BevelBorder;

@SuppressWarnings("serial")
public class UserFrame extends JFrame {

	private JPanel Panel;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserFrame frame = new UserFrame("raghu");
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
	public UserFrame(String username) {
		
		Color high = new Color(0xffa07a);
		Color norm = new Color(0xffdab9);
		
		setTitle("BookMyHotel");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 30, 900, 700);
		Panel = new JPanel();
		
		Panel.setBackground(new Color(250, 235, 215));
		Panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Panel);
		Panel.setLayout(null);
		
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
		
		
		JLabel IntroLabel = new JLabel("Hello "+User.findName(username)+",");
		IntroLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		IntroLabel.setBounds(33, 87, 233, 24);
		Panel.add(IntroLabel);
		
		ImageIcon settingIcon = new ImageIcon(getClass().getResource("/settings.png"));
		ImageIcon userIcon = new ImageIcon(getClass().getResource("/User.png"));
		ImageIcon logoutIcon = new ImageIcon(getClass().getResource("/Logout.png"));
		ImageIcon personicon = new ImageIcon(getClass().getResource("/person-icon.png"));
		
		JPanel panel = new JPanel();
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setBackground(new Color(0xffebcd));
				panel.setSize(panel.getWidth(), 103);
			}
		});
		panel.setLayout(null);
		panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel.setBounds(712, 13, 158, 52);
		Panel.add(panel);
		panel.setBackground(norm);
		
		JLabel label_2 = new JLabel("");
		label_2.setBounds(12, 8, 42, 36);
		panel.add(label_2);
		label_2.setIcon(userIcon);
		
		JLabel label_3 = new JLabel(username);
		label_3.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		label_3.setBounds(50, 8, 95, 36);
		panel.add(label_3);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(norm);
		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_2.setBackground(high);
			}
			public void mouseExited(MouseEvent me)
			{
				panel_2.setBackground(norm);
			}
			public void mouseClicked(MouseEvent me)
			{
				new LoginFrame().setVisible(true);
				dispose();
			}
		});
		panel_2.setLayout(null);
		panel_2.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_2.setBounds(0, 50, 158, 53);
		panel.add(panel_2);
		
		ImageIcon userProfileIcon = new ImageIcon(getClass().getResource("/user-icon.png"));
		ImageIcon homeIcon = new ImageIcon(getClass().getResource("/home.png"));
		ImageIcon bookingIcon = new ImageIcon(getClass().getResource("/booking.png"));
		ImageIcon aboutus = new ImageIcon(getClass().getResource("/aboutus.png"));
		ImageIcon removeAccount = new ImageIcon(getClass().getResource("/user-remove-icon.png"));
		ImageIcon hotelImgIcon = new ImageIcon(getClass().getResource("/hotelimg.jpg"));
		ImageIcon searchimg = new ImageIcon(getClass().getResource("/srcimg.jpg"));
		
		JLabel label_6 = new JLabel("");
		label_6.setBounds(12, 6, 32, 40);
		panel_2.add(label_6);
		label_6.setIcon(logoutIcon);
		
		JLabel label_7 = new JLabel("Log Out");
		label_7.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		label_7.setBounds(56, 6, 69, 40);
		panel_2.add(label_7);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_3.setBackground(new Color(255, 218, 185));
		panel_3.setBounds(0, 142, 233, 498);
		Panel.add(panel_3);
		panel_3.setLayout(null);
		
		JPanel panel_6 = new JPanel();
		panel_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_6.setBackground(high);
			}
			public void mouseExited(MouseEvent me) {
				panel_6.setBackground(norm);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				new MyBookingFrame(username).setVisible(true);
				dispose();
			}
		});
		panel_6.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_6.setBackground(new Color(255, 218, 185));
		panel_6.setBounds(0, 164, 233, 84);
		panel_3.add(panel_6);
		panel_6.setLayout(null);
		
		JLabel label_8 = new JLabel("");
		label_8.setHorizontalAlignment(SwingConstants.CENTER);
		label_8.setBounds(12, 13, 56, 58);
		panel_6.add(label_8);
		label_8.setIcon(bookingIcon);
		
		JLabel lblMyBookings = new JLabel("My Bookings");
		lblMyBookings.setHorizontalAlignment(SwingConstants.LEFT);
		lblMyBookings.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblMyBookings.setBounds(80, 13, 126, 58);
		panel_6.add(lblMyBookings);
		
		JPanel panel_7 = new JPanel();
		panel_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_7.setBackground(high);
			}
			public void mouseExited(MouseEvent me) {
				panel_7.setBackground(norm);
			}
		});
		panel_7.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_7.setBackground(new Color(255, 218, 185));
		panel_7.setBounds(0, 245, 233, 84);
		panel_3.add(panel_7);
		panel_7.setLayout(null);
		
		JLabel label_10 = new JLabel("");
		label_10.setHorizontalAlignment(SwingConstants.CENTER);
		label_10.setBounds(12, 13, 56, 58);
		panel_7.add(label_10);
		label_10.setIcon(removeAccount);
		
		JLabel lblRemoveAccount = new JLabel("Remove Account");
		lblRemoveAccount.setHorizontalAlignment(SwingConstants.LEFT);
		lblRemoveAccount.setFont(new Font("Trebuchet MS", Font.PLAIN, 18));
		lblRemoveAccount.setBounds(79, 13, 154, 58);
		panel_7.add(lblRemoveAccount);
		
		JPanel panel_8 = new JPanel();
		panel_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_8.setBackground(high);
			}
			public void mouseExited(MouseEvent me) {
				panel_8.setBackground(norm);
			}
		});
		panel_8.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_8.setBackground(new Color(255, 218, 185));
		panel_8.setBounds(0, 410, 233, 88);
		panel_3.add(panel_8);
		panel_8.setLayout(null);
		
		JLabel label_9 = new JLabel("");
		label_9.setHorizontalAlignment(SwingConstants.CENTER);
		label_9.setBounds(12, 13, 56, 58);
		panel_8.add(label_9);
		label_9.setIcon(aboutus);
		
		JLabel lblAboutUs = new JLabel("About Us");
		lblAboutUs.setHorizontalAlignment(SwingConstants.LEFT);
		lblAboutUs.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblAboutUs.setBounds(80, 13, 113, 58);
		panel_8.add(lblAboutUs);
		
		JLabel lblUserProfile = new JLabel("");
		lblUserProfile.setHorizontalAlignment(SwingConstants.CENTER);
		lblUserProfile.setBounds(40, 13, 147, 138);
		panel_3.add(lblUserProfile);
		lblUserProfile.setIcon(userProfileIcon);
		
		JPanel panel_5 = new JPanel();
		panel_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel_5.setBackground(high);
			}
			public void mouseExited(MouseEvent me) {
				panel_5.setBackground(norm);
			}
		});
		panel_5.setLayout(null);
		panel_5.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
		panel_5.setBackground(new Color(255, 218, 185));
		panel_5.setBounds(0, 327, 233, 84);
		panel_3.add(panel_5);
		
		JLabel label_11 = new JLabel("");
		label_11.setHorizontalAlignment(SwingConstants.CENTER);
		label_11.setBounds(12, 13, 56, 58);
		panel_5.add(label_11);
		label_11.setIcon(settingIcon);
		
		JLabel lblSettings = new JLabel("Settings");
		lblSettings.setHorizontalAlignment(SwingConstants.LEFT);
		lblSettings.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblSettings.setBounds(80, 13, 102, 58);
		panel_5.add(lblSettings);
		
		JLabel label_12 = new JLabel("");
		label_12.setHorizontalAlignment(SwingConstants.CENTER);
		label_12.setBounds(12, 13, 56, 58);
		panel_5.add(label_12);
		
		JPanel panel_4 = new JPanel();
		panel_4.setBackground(new Color(255, 240, 245));
		panel_4.setBounds(231, 142, 639, 498);
		Panel.add(panel_4);
		panel_4.setLayout(null);
		
		JButton btnNewButton = new JButton("");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new BookingFrame(username).setVisible(true);
				dispose();
			}
		});
		btnNewButton.setBackground(Color.WHITE);
		btnNewButton.setBounds(165, 302, 295, 58);
		panel_4.add(btnNewButton);
		btnNewButton.setFont(new Font("Trebuchet MS", Font.ITALIC, 28));
		btnNewButton.setIcon(searchimg);
		JLabel hotelImg = new JLabel("");
		hotelImg.setBounds(12, 159, 615, 339);
		panel_4.add(hotelImg);
		hotelImg.setIcon(hotelImgIcon);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(255, 218, 185));
		panel_1.setBounds(597, 0, 42, 135);
		panel_4.add(panel_1);
		
		JPanel panel_9 = new JPanel();
		panel_9.setBackground(new Color(255, 218, 185));
		panel_9.setBounds(555, 0, 42, 83);
		panel_4.add(panel_9);
		
		JLabel lblNewLabel = new JLabel("Welcome !!!");
		lblNewLabel.setForeground(new Color(255, 0, 0));
		lblNewLabel.setFont(new Font("Pristina", Font.BOLD, 60));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(96, 41, 401, 83);
		panel_4.add(lblNewLabel);
		
		Panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(), 52);
				panel.setBackground(norm);
			}
		});
			
	}
}
