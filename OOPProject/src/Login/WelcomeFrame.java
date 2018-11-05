package Login;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class WelcomeFrame {

	public JFrame Welcome_frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WelcomeFrame window = new WelcomeFrame();
					window.Welcome_frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WelcomeFrame() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		Welcome_frame = new JFrame();
		Welcome_frame.getContentPane().setBackground(new Color(250, 235, 215));
		Welcome_frame.getContentPane().setForeground(new Color(0, 255, 255));
		Welcome_frame.setBounds(180, 30, 900, 700);
		Welcome_frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Welcome_frame.getContentPane().setLayout(null);
		Welcome_frame.setTitle("BookMyHotel");
		JLabel label = new JLabel("");
		Image img2=new ImageIcon(this.getClass().getResource("/Hotel3.jpg")).getImage();
		label.setIcon(new ImageIcon(img2));
		label.setBounds(0, 421, 884, 240);
		Welcome_frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("BookMyHotel");
		lblNewLabel.setFont(new Font("Consolas", Font.BOLD, 45));
		lblNewLabel.setForeground(new Color(102, 0, 51));
		lblNewLabel.setBounds(88, 26, 310, 61);
		Welcome_frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setForeground(new Color(250, 235, 215));
		panel.setBackground(new Color(250, 235, 215));
		panel.setBounds(306, 177, 407, 155);
		Welcome_frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				LoginFrame obj =new LoginFrame();
				obj.setVisible(true);
				Welcome_frame.dispose();
			}
		});
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setBackground(new Color(106, 90, 205));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton.setBounds(259, 31, 121, 34);
		panel.add(btnNewButton);
		
		JLabel lblAreYouA = new JLabel("New User?");
		lblAreYouA.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		lblAreYouA.setBounds(126, 95, 121, 34);
		panel.add(lblAreYouA);
		
		JButton btnNewButton_1 = new JButton("Register");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				RegisterFrame obj=new RegisterFrame();
				obj.setVisible(true);
				Welcome_frame.dispose();
			}
		});
		btnNewButton_1.setBackground(new Color(106, 90, 205));
		btnNewButton_1.setForeground(new Color(255, 255, 255));
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_1.setBounds(259, 95, 121, 34);
		panel.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("-Your Stay our Responsibility");
		lblNewLabel_1.setFont(new Font("Consolas", Font.ITALIC, 25));
		lblNewLabel_1.setForeground(new Color(102, 0, 51));
		lblNewLabel_1.setBounds(378, 23, 446, 64);
		Welcome_frame.getContentPane().add(lblNewLabel_1);
	}
}
