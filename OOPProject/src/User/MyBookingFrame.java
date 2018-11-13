package User;
import Login.*;
import net.proteanit.sql.DbUtils;

import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.*;
import java.sql.ResultSet;

import javax.swing.border.LineBorder;

import Hotel.Hotel;
import Hotel.MyContainer;
@SuppressWarnings("serial")
public class MyBookingFrame extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JTable prevtable;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MyBookingFrame frame = new MyBookingFrame("raghu");
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
	public MyBookingFrame(String username) {
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180,30,900,700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel label = new JLabel("BookMyHotel");
		label.setForeground(new Color(102, 0, 51));
		label.setFont(new Font("Consolas", Font.BOLD, 40));
		label.setBackground(Color.WHITE);
		label.setBounds(24, 11, 320, 65);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("-Your Stay our Responsibility");
		label_1.setForeground(new Color(102, 0, 51));
		label_1.setFont(new Font("Consolas", Font.ITALIC, 23));
		label_1.setBounds(278, 11, 399, 65);
		contentPane.add(label_1);
		
		JPanel panel = new JPanel();
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
		panel.setLayout(null);
		panel.setBackground(new Color(250, 235, 215));
		panel.setBounds(678, 11, 196, 48);
		contentPane.add(panel);
		
		JLabel User_label = new JLabel(User.findName(username));
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
		User_label.setToolTipText("User Menu");
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
		Home_label.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		Image img4=new ImageIcon(this.getClass().getResource("/Home icon.png")).getImage();
		Home_label.setIcon(new ImageIcon(img4));
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
		Logout_label.setFont(new Font("Trebuchet MS", Font.PLAIN, 15));
		Image img3=new ImageIcon(this.getClass().getResource("/Logout.png")).getImage();
		Logout_label.setIcon(new ImageIcon(img3));
		Logout_label.setBounds(10, 97, 92, 32);
		panel.add(Logout_label);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		panel_1.setBounds(10, 54, 176, 83);
		panel.add(panel_1);
		
		JPanel bg_panel = new JPanel();
		bg_panel.setBackground(new Color(250, 235, 215));
		bg_panel.setBounds(10, 79, 864, 571);
		contentPane.add(bg_panel);
		bg_panel.setLayout(null);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(250, 235, 215));
		panel_2.setBounds(0, 0, 864, 64);
		bg_panel.add(panel_2);
		panel_2.setLayout(null);
		
		JPanel cardpanel = new JPanel();
		cardpanel.setBackground(new Color(250, 235, 215));
		cardpanel.setBounds(0, 64, 864, 507);
		bg_panel.add(cardpanel);
		cardpanel.setLayout(new CardLayout(0, 0));
		
		
		JPanel upbooking_panel = new JPanel();
		upbooking_panel.setBackground(new Color(250, 235, 215));
		cardpanel.add(upbooking_panel, "upbooking");
		upbooking_panel.setLayout(null);
		
		JButton btnMdifyBooking = new JButton("Modify Booking");
		btnMdifyBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ModifyFrame(getRefno1(),username).setVisible(true);dispose();
			}
		});
		btnMdifyBooking.setForeground(Color.WHITE);
		btnMdifyBooking.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnMdifyBooking.setBackground(new Color(106, 90, 205));
		btnMdifyBooking.setBounds(607, 416, 213, 34);
		btnMdifyBooking.setVisible(false);
		upbooking_panel.add(btnMdifyBooking);
		
		JButton btnCancelBooking = new JButton("Cancel Booking");
		btnCancelBooking.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, "Confirm Cancellation", "Information", JOptionPane.INFORMATION_MESSAGE);
				Hotel hotel = MyContainer.getHotel(getRefno1());
				hotel.cancelBooking(getRefno1());
				upBook(username);
			}
		});
		btnCancelBooking.setForeground(Color.WHITE);
		btnCancelBooking.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnCancelBooking.setBackground(new Color(106, 90, 205));
		btnCancelBooking.setBounds(607, 462, 213, 34);
		btnCancelBooking.setVisible(false);
		upbooking_panel.add(btnCancelBooking);
		
		
		JPanel prevbooking_panel = new JPanel();
		prevbooking_panel.setBackground(new Color(250, 235, 215));
		cardpanel.add(prevbooking_panel, "prevbooking");
		prevbooking_panel.setLayout(null);
		
		
		JButton Feedbackbtn = new JButton("Give Feedback");
		Feedbackbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FeedbackFrame obj= new FeedbackFrame(username,getRefno());
				obj.setVisible(true);
				dispose();
			}
		});
		Feedbackbtn.setForeground(Color.WHITE);
		Feedbackbtn.setFont(new Font("Tahoma", Font.PLAIN, 18));
		Feedbackbtn.setBackground(new Color(106, 90, 205));
		Feedbackbtn.setBounds(641, 431, 213, 34);
		Feedbackbtn.setVisible(false);
		prevbooking_panel.add(Feedbackbtn);
		
		
		 JLabel NoFeedback = new JLabel("Feedback given.");
		 NoFeedback.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
			NoFeedback.setBounds(641, 431, 213, 34);
			NoFeedback.setVisible(false);
			prevbooking_panel.add(NoFeedback);
			
			
		
		JButton upbooking_btn = new JButton("View Upcoming Booking");
		upbooking_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				upBook(username);
				Feedbackbtn.setVisible(false);
				NoFeedback.setVisible(false);
				btnMdifyBooking.setVisible(false);
				btnCancelBooking.setVisible(false);
				CardLayout c= (CardLayout)(cardpanel.getLayout());
				c.show(cardpanel,"upbooking");
			}
		});
		upbooking_btn.setBackground(new Color(204, 0, 0));
		upbooking_btn.setForeground(new Color(255, 255, 255));
		upbooking_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		upbooking_btn.setBounds(0, 0, 232, 64);
		panel_2.add(upbooking_btn);
		
		
		JButton prevbooking_btn = new JButton("View Previous Booking");
		prevbooking_btn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				prevBook(username);
				btnMdifyBooking.setVisible(false);
				btnCancelBooking.setVisible(false);
				Feedbackbtn.setVisible(false);
				NoFeedback.setVisible(false);
				CardLayout c= (CardLayout)(cardpanel.getLayout());
				c.show(cardpanel,"prevbooking");
			}
		});
		prevbooking_btn.setForeground(Color.WHITE);
		prevbooking_btn.setFont(new Font("Trebuchet MS", Font.PLAIN, 17));
		prevbooking_btn.setBackground(new Color(204, 0, 0));
		prevbooking_btn.setBounds(230, 0, 232, 64);
		panel_2.add(prevbooking_btn);

		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBackground(new Color(102, 102, 51));
		scrollPane.setBounds(10, 24, 844, 381);
		upbooking_panel.add(scrollPane);
		
		table = new JTable()
				{ private static final long serialVersionUID = 1L;

		        public boolean isCellEditable(int row, int column) {                
		                return false;   
		        };};
		        table.setRowHeight(30);
		        table.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				int a=Bookingdisplays.statusShow(getRefno1());
				if(a!=1)
				{
				btnMdifyBooking.setVisible(true);
				btnCancelBooking.setVisible(true);
				}
			}
		});
		scrollPane.setViewportView(table);
		
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 26, 844, 381);
		prevbooking_panel.add(scrollPane_1);
	
		
		prevtable = new JTable()
		{ private static final long serialVersionUID = 1L;

        public boolean isCellEditable(int row, int column) {                
                return false;               
        };
    };
	
	
	prevtable.setRowHeight(30);	
    prevtable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int c=getRefno();
				int a=Bookingdisplays.checkFeedback(c);
				if(a==1)
				{
					NoFeedback.setVisible(false);
					Feedbackbtn.setVisible(true);
				}
				else if(a==0)
				{
					NoFeedback.setVisible(true);
					Feedbackbtn.setVisible(false);
				}
					
			}
		});
		scrollPane_1.setViewportView(prevtable);
	}
	
	void upBook(String username)
	{
		ResultSet rs;
		rs=Bookingdisplays.Upbooking(username);
		table.setModel(DbUtils.resultSetToTableModel(rs));
		MyConnection.closeConnection();
	}
	void prevBook(String username)
	{
		ResultSet rs;
		rs=Bookingdisplays.Prevbooking(username);
		prevtable.setModel(DbUtils.resultSetToTableModel(rs));
		MyConnection.closeConnection();
	}
	int getRefno()
	{
		int b=prevtable.getSelectedRow();
		String s = prevtable.getModel().getValueAt(b,0).toString();
		int c = Integer.parseInt(s);
		return c;
	}
	int getRefno1()
	{
		int b=table.getSelectedRow();
		String s = table.getModel().getValueAt(b,0).toString();
		int c = Integer.parseInt(s);
		return c;
	}
}
