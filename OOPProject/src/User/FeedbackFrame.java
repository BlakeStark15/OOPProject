package User;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTable;
import javax.swing.border.LineBorder;

import Hotel.Hotel;
import Hotel.MyContainer;
import Login.LoginFrame;
import Login.User;
import Login.WelcomeFrame;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

@SuppressWarnings({ "serial", "unused" })
public class FeedbackFrame extends JFrame {

	private JPanel contentPane;
	public JLabel label_4, label_5, label_6, label_7, label_8, label_9, label_10, label_11, label_12, label_13;
	String str=null;
	int rating=0,flag=1;
	private JLabel lblNewLabel;
	private JButton btnBackToMybookings;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FeedbackFrame frame = new FeedbackFrame("Ankit",1);
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
	public FeedbackFrame(String username,int refno) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 30, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("BookMyHotel");
		label.setForeground(new Color(102, 0, 51));
		label.setFont(new Font("Consolas", Font.BOLD, 40));
		label.setBackground(Color.WHITE);
		label.setBounds(10, 11, 320, 65);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("-Your Stay our Responsibility");
		label_1.setForeground(new Color(102, 0, 51));
		label_1.setFont(new Font("Consolas", Font.ITALIC, 23));
		label_1.setBounds(264, 11, 399, 65);
		contentPane.add(label_1);
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				panel.setSize(panel.getWidth(), 150);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				panel.setSize(panel.getWidth(), 50);
			}
		});
		panel.setBackground(new Color(250, 235, 215));
		panel.setBounds(678, 11, 196, 50);
		contentPane.add(panel);
		
		JLabel User_label = new JLabel(User.findName(username));
		User_label.setToolTipText("User Menu");
		User_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				panel.setSize(panel.getWidth(), 150);
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(), 150);
			}
		});
		Image img2=new ImageIcon(this.getClass().getResource("/User.png")).getImage();
		User_label.setIcon(new ImageIcon(img2));
		User_label.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		User_label.setBounds(10, 11, 176, 32);
		panel.add(User_label);
		
		JLabel Home_label = new JLabel("Home ");
		Home_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WelcomeFrame obj=new WelcomeFrame();
				obj.Welcome_frame.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(), 150);
			}
		});
		Image img4=new ImageIcon(this.getClass().getResource("/Home icon.png")).getImage();
		Home_label.setIcon(new ImageIcon(img4));
		Home_label.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		Home_label.setBounds(10, 54, 92, 32);
		panel.add(Home_label);
		
		JLabel Logout_label = new JLabel("Logout ");
		Logout_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				LoginFrame obj=new LoginFrame();
				obj.setVisible(true);
				dispose();
			}
			@Override
			public void mouseEntered(MouseEvent e) {
				panel.setSize(panel.getWidth(), 150);
			}
		});
		Image img3=new ImageIcon(this.getClass().getResource("/Logout.png")).getImage();
		Logout_label.setIcon(new ImageIcon(img3));
		Logout_label.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		Logout_label.setBounds(10, 97, 92, 32);
		panel.add(Logout_label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 54, 176, 83);
		panel.add(panel_1);
		
		JLabel label_2 = new JLabel("Comments and Suggestions");
		label_2.setHorizontalAlignment(SwingConstants.CENTER);
		label_2.setForeground(new Color(0, 0, 0));
		label_2.setFont(new Font("Trebuchet MS", Font.ITALIC, 25));
		label_2.setBounds(277, 121, 320, 54);
		contentPane.add(label_2);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(189, 207, 487, 113);
		contentPane.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		scrollPane.setViewportView(textArea);
		textArea.setBackground(Color.WHITE);
		textArea.setForeground(new Color(0, 0, 0));
		textArea.setFont(new Font("Tahoma", Font.PLAIN, 17));
		textArea.setLineWrap(true);
		
		JLabel label_3 = new JLabel("Rate your stay at the hotel "+Bookingdisplays.gethotelname(refno));
		label_3.setHorizontalAlignment(SwingConstants.CENTER);
		label_3.setForeground(new Color(0, 0, 0));
		label_3.setFont(new Font("Trebuchet MS", Font.ITALIC, 23));
		label_3.setBounds(119, 341, 637, 64);
		contentPane.add(label_3);
		
		lblNewLabel = new JLabel(" Thank you for your feedback");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.ITALIC, 23));
		lblNewLabel.setBounds(277, 549, 314, 37);
		lblNewLabel.setVisible(false);
		contentPane.add(lblNewLabel);
		
		
		label_4 = new JLabel("");
		label_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(4);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				rating=1;
				ratingDisplay(4);
				flag=0;
			}
		});
		Image img21=new ImageIcon(this.getClass().getResource("/star1.png")).getImage();
		label_4.setIcon(new ImageIcon(img21));
		label_4.setBounds(189, 427, 85, 78);
		contentPane.add(label_4);
		
		label_5 = new JLabel("");
		label_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(5);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ratingDisplay(5);
				rating=2;
				flag=0;
			}
		});
		label_5.setIcon(new ImageIcon(img21));
		label_5.setBounds(289, 427, 85, 78);
		contentPane.add(label_5);
		
		label_6 = new JLabel("");
		label_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(6);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ratingDisplay(6);
				rating=3;
				flag=0;
			}
		});
		label_6.setIcon(new ImageIcon(img21));
		label_6.setBounds(389, 427, 85, 78);
		contentPane.add(label_6);
		
		label_7 = new JLabel("");
		label_7.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(7);
				rating=4;
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ratingDisplay(7);
				flag=0;
				rating=4;
			}
		});
		label_7.setIcon(new ImageIcon(img21));
		label_7.setBounds(494, 427, 85, 78);
		contentPane.add(label_7);
		
		label_8 = new JLabel("");
		label_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(8);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ratingDisplay(8);
				rating=5;
				flag=0;
			}
		});
		label_8.setIcon(new ImageIcon(img21));
		label_8.setBounds(589, 427, 85, 78);
		contentPane.add(label_8);
		
		label_9 = new JLabel("");
		label_9.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(4);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ratingDisplay(4);
				rating=1;
				flag=0;
			}
		});
		Image img=new ImageIcon(this.getClass().getResource("/star2.png")).getImage();
		label_9.setIcon(new ImageIcon(img));
		label_9.setBounds(189, 427, 85, 78);
		contentPane.add(label_9);
		
		label_10 = new JLabel("");
		label_10.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(5);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ratingDisplay(5);
				rating=2;
				flag=0;
			}
		});
		label_10.setIcon(new ImageIcon(img));
		label_10.setBounds(289, 427, 85, 78);
		contentPane.add(label_10);
		
		label_11 = new JLabel("");
		label_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(6);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ratingDisplay(6);
				rating=3;
				flag=0;
			}
		});
		label_11.setIcon(new ImageIcon(img));
		label_11.setBounds(389, 427, 85, 78);
		contentPane.add(label_11);
		
		label_12 = new JLabel("");
		label_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(7);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ratingDisplay(7);
				rating=4;
				flag=0;
			}
		});
		label_12.setBounds(494, 427, 85, 78);
		label_12.setIcon(new ImageIcon(img));
		contentPane.add(label_12);
		
		label_13 = new JLabel("");
		label_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(8);
			}
			@Override
			public void mouseExited(MouseEvent arg0) {
				if(flag==1)
				ratingDisplay(3);
			}
			@Override
			public void mouseClicked(MouseEvent arg0) {
				ratingDisplay(8);
				rating=5;
				flag=0;
			}
		});
		label_13.setIcon(new ImageIcon(img));
		label_13.setBounds(589, 427, 85, 78);
		contentPane.add(label_13);		
		
		JButton button = new JButton("Back to MyBookings");
		button.setVisible(false);
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyBookingFrame obj=new MyBookingFrame(username);
				obj.setVisible(true);
				dispose();
			}
		});
		button.setForeground(Color.WHITE);
		button.setFont(new Font("Tahoma", Font.PLAIN, 18));
		button.setBackground(new Color(106, 90, 205));
		button.setBounds(333, 597, 196, 34);
		contentPane.add(button);
		
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				lblNewLabel.setVisible(true);
				MyContainer obj1=MyContainer.getContainer();
				Hotel hotel= obj1.getHotel(Bookingdisplays.gethotelname(refno),Bookingdisplays.getlocation(refno));
				hotel.takeFeedback(refno,textArea.getText(), rating);
				MyContainer.storeContainer(obj1);
				btnSubmit.setVisible(false);
				btnBackToMybookings.setVisible(false);
				button.setVisible(true);
			}
		});
		
		btnSubmit.setForeground(Color.WHITE);
		btnSubmit.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnSubmit.setBackground(new Color(106, 90, 205));
		btnSubmit.setBounds(374, 597, 121, 34);
		contentPane.add(btnSubmit);
		
		btnBackToMybookings = new JButton("Back to MyBookings");
		btnBackToMybookings.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MyBookingFrame obj=new MyBookingFrame(username);
				obj.setVisible(true);
				dispose();
			}
		});
		btnBackToMybookings.setForeground(Color.WHITE);
		btnBackToMybookings.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnBackToMybookings.setBackground(new Color(106, 90, 205));
		btnBackToMybookings.setBounds(608, 597, 196, 34);
		contentPane.add(btnBackToMybookings);
}
	public void ratingDisplay(int a)
	{
		label_4.setVisible((4-a)>0);
		label_5.setVisible((5-a)>0);
		label_6.setVisible((6-a)>0);
		label_7.setVisible((7-a)>0);
		label_8.setVisible((8-a)>0);
		label_9.setVisible((4-a)<=0);
		label_10.setVisible((5-a)<=0);
		label_11.setVisible((6-a)<=0);
		label_12.setVisible((7-a)<=0);
		label_13.setVisible((8-a)<=0);
	}
}
