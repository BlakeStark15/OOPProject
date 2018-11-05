package Login;
import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.border.EmptyBorder;
import User.UserFrame;


@SuppressWarnings("serial")
public class LoginFrame extends JFrame {

	private JPanel contentPane;
	private JTextField UserNameField;
	private JPasswordField passwordField;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginFrame frame = new LoginFrame();
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
	public LoginFrame() {
		initialize();
	}
	private void initialize()
	{
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180, 30, 900,700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("BookMyHotel");
		
		UserNameField = new JTextField();
		UserNameField.setBounds(554, 283, 129, 30);
		contentPane.add(UserNameField);
		UserNameField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					JLabel errLabel = new JLabel("");
					errLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
					UIManager.put("Button.background", Color.white);
					
					if(User.login(UserNameField.getText(), passwordField.getText()))
					{
						errLabel.setText("Successful Login.");
						JOptionPane.showMessageDialog(null,errLabel);
						UserFrame obj=new UserFrame(UserNameField.getText());
						obj.setVisible(true);
						dispose();
					}
					else
					{
						errLabel.setText("Invalid Login.");
						JOptionPane.showMessageDialog(null, errLabel);
					}
				}
			}
		});
		passwordField.setBounds(554, 346, 129, 30);
		contentPane.add(passwordField);
		
		
		//ImageIcon i1=new ImageIcon("login.png");
		JButton btnLogin = new JButton("Login");
		Image img1=new ImageIcon(this.getClass().getResource("/login.png")).getImage();
		btnLogin.setIcon(new ImageIcon(img1));
		btnLogin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) 
			{
				
				JLabel errLabel = new JLabel("");
				errLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
				UIManager.put("Button.background", Color.white);
				
				if(User.login(UserNameField.getText(), passwordField.getText()))
				{
					errLabel.setText("Successful Login.");
					JOptionPane.showMessageDialog(null,errLabel);
					UserFrame obj=new UserFrame(UserNameField.getText());
					obj.setVisible(true);
					dispose();
				}
				else
				{
					errLabel.setText("Invalid Login.");
					JOptionPane.showMessageDialog(null, errLabel);
					
				}
					
			}
		});
		btnLogin.setBackground(new Color(0, 204, 204));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLogin.setBounds(582, 419, 101, 30);
		contentPane.add(btnLogin);
		
		JLabel UserNameLabel = new JLabel("Username");
		UserNameLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		UserNameLabel.setBounds(443, 283, 101, 30);
		contentPane.add(UserNameLabel);
		
		JLabel PassLabel = new JLabel("Password");
		PassLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		PassLabel.setBounds(443, 346, 101, 30);
		contentPane.add(PassLabel);

		JLabel label_image = new JLabel("");
		label_image.setBackground(new Color(250, 235, 215));
		Image img=new ImageIcon(this.getClass().getResource("/Label.png")).getImage();
		label_image.setIcon(new ImageIcon(img));
		label_image.setBounds(88, 230, 310, 236);
		contentPane.add(label_image);
		
		JLabel label = new JLabel("BookMyHotel");
		label.setBackground(new Color(255, 255, 255));
		label.setForeground(new Color(102, 0, 51));
		label.setFont(new Font("Consolas", Font.BOLD, 40));
		label.setBounds(23, 11, 320, 65);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("-Your Stay our Responsibility");
		label_1.setForeground(new Color(102, 0, 51));
		label_1.setFont(new Font("Consolas", Font.ITALIC, 23));
		label_1.setBounds(290, 11, 399, 65);
		contentPane.add(label_1);
		
		
		JLabel Home_label = new JLabel("");
		Home_label.setToolTipText("Back to Home");
		Image img4=new ImageIcon(this.getClass().getResource("/Home icon.png")).getImage();
		Home_label.setIcon(new ImageIcon(img4));
		Home_label.setBounds(834, 11, 40, 54);
		Home_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				WelcomeFrame obj=new WelcomeFrame();
				obj.Welcome_frame.setVisible(true);
				dispose();
			}
		});
		contentPane.add(Home_label);
	}
}
