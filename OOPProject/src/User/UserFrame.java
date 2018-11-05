package User;
import Login.*;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;

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
					UserFrame frame = new UserFrame("frame");
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
		IntroLabel.setBounds(33, 87, 158, 24);
		Panel.add(IntroLabel);
		
		JPanel panel3 = new JPanel();
		panel3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				panel3.setSize(panel3.getWidth(), 50);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel3.setSize(panel3.getWidth(), 150);
			}
		});
		panel3.setBackground(new Color(250, 235, 215));
		panel3.setBounds(762, 11, 112, 51);
		Panel.add(panel3);
		panel3.setLayout(null);
		
		
		JLabel User_label = new JLabel(User.findName(username));
		User_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel3.setSize(panel3.getWidth(), 150);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel3.setSize(panel3.getWidth(), 150);
			}
		});
		User_label.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		Image img2=new ImageIcon(this.getClass().getResource("/User.png")).getImage();
		User_label.setIcon(new ImageIcon(img2));
		User_label.setToolTipText("User Menu");
		User_label.setBounds(10, 11, 92, 32);
		panel3.add(User_label);
		
		
		JLabel Home_label = new JLabel("Home ");
		Home_label.setBounds(10, 54, 92, 32);
		panel3.add(Home_label);
		Image img4=new ImageIcon(this.getClass().getResource("/Home icon.png")).getImage();
		Home_label.setIcon(new ImageIcon(img4));
		Home_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WelcomeFrame obj=new WelcomeFrame();
				obj.Welcome_frame.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel3.setSize(panel3.getWidth(), 150);
			}
		});
		Home_label.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		
		
		
		
		JLabel Logout_label = new JLabel("Logout ");
		Logout_label.setBounds(10, 97, 92, 32);
		panel3.add(Logout_label);
		Image img3=new ImageIcon(this.getClass().getResource("/Logout.png")).getImage();
		Logout_label.setIcon(new ImageIcon(img3));
		Logout_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginFrame obj=new LoginFrame();
				obj.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel3.setSize(panel3.getWidth(), 150);
			}
		});
		Logout_label.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		
		JPanel panel_3 = new JPanel();
		panel_3.setBackground(new Color(255, 255, 255));
		panel_3.setBounds(10, 54, 92, 83);
		panel3.add(panel_3);
			
	}
}
