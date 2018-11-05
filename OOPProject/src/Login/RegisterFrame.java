package Login;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class RegisterFrame extends JFrame {

	private JPanel contentPane;
	private JTextField Name_Field;
	private JTextField DOB_Field;
	private JTextField Email_Field;
	private JTextField UName_Field;
	private JPasswordField FirstPass_Field;
	private Image ok;
	private Image error;
	private JLabel checklbl;
	private JPasswordField RePass_Field;
	private String uname = "";
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RegisterFrame frame = new RegisterFrame();
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
	public RegisterFrame() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(180,30, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(250, 235, 215));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("BookMyHotel");
		
		
		JLabel label = new JLabel("BookMyHotel");
		label.setBounds(21, 11, 320, 65);
		label.setForeground(new Color(102, 0, 51));
		label.setFont(new Font("Consolas", Font.BOLD, 40));
		label.setBackground(Color.WHITE);
		contentPane.add(label);
		
		JLabel label_1 = new JLabel("-Your Stay our Responsibility");
		label_1.setBounds(277, 11, 386, 65);
		label_1.setForeground(new Color(102, 0, 51));

		
		JLabel Name_Label = new JLabel("Name*");
		Name_Label.setBounds(88, 168, 124, 29);
		Name_Label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		contentPane.add(Name_Label);
		
		JLabel DOB_Label = new JLabel("Date Of Birth*");
		DOB_Label.setBounds(88, 219, 139, 29);
		DOB_Label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		contentPane.add(DOB_Label);
		
		JLabel Add_Label = new JLabel("Address*");
		Add_Label.setBounds(88, 288, 124, 21);
		Add_Label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		contentPane.add(Add_Label);
		
		JLabel Email_Label = new JLabel("EmailID*");
		Email_Label.setBounds(88, 356, 124, 21);
		Email_Label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		contentPane.add(Email_Label);
		
		JLabel UName_Label = new JLabel("UserName*");
		UName_Label.setBounds(88, 413, 124, 21);
		UName_Label.setFont(new Font("Trebuchet MS", Font.PLAIN, 20));
		contentPane.add(UName_Label);
		
		JLabel First_Pass = new JLabel("Password*");
		First_Pass.setBounds(88, 465, 124, 26);
		First_Pass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(First_Pass);
		
		JLabel Re_Pass = new JLabel("Reenter Password*");
		Re_Pass.setBounds(88, 514, 185, 29);
		Re_Pass.setFont(new Font("Tahoma", Font.PLAIN, 20));
		contentPane.add(Re_Pass);
		
		Name_Field = new JTextField();
		Name_Field.setBounds(306, 168, 204, 29);
		contentPane.add(Name_Field);
		Name_Field.setColumns(10);
		
		DOB_Field = new JTextField();
		DOB_Field.setBounds(306, 219, 204, 29);
		contentPane.add(DOB_Field);
		DOB_Field.setColumns(10);
		
		JTextArea Add_Field = new JTextArea();
		Add_Field.setForeground(new Color(0, 0, 0));
		Add_Field.setBounds(305, 275, 204, 53);
		contentPane.add(Add_Field);
		
		Email_Field =  new JTextField();
		Email_Field.setBounds(306, 355, 204, 29);
		contentPane.add(Email_Field);
		Email_Field.setColumns(10);
		
		UName_Field = new JTextField();
		UName_Field.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent ke) 
			{
				if(ke.getKeyCode()==KeyEvent.VK_BACK_SPACE&&uname.length()>0)
					uname = uname.substring(0, uname.length()-1);
				else
				if(ke.getKeyChar()!=KeyEvent.CHAR_UNDEFINED&&ke.getKeyCode()!=KeyEvent.VK_BACK_SPACE)
				{
					uname += ke.getKeyChar();
				}
				if(!User.checkAvailability(uname)||uname.equals("")) {
					checklbl.setIcon(new ImageIcon(error));
				}
				else
					checklbl.setIcon(new ImageIcon(ok));
			}
		});
		UName_Field.setBounds(306, 412, 204, 29);
		contentPane.add(UName_Field);
		UName_Field.setColumns(10);
		
		FirstPass_Field = new JPasswordField();
		FirstPass_Field.setBounds(306, 465, 204, 26);
		contentPane.add(FirstPass_Field);
		
		JLabel errLabel = new JLabel("");
		errLabel.setFont(new Font("Times New Roman", Font.PLAIN, 17));
		UIManager.put("Button.background", Color.white);
		
		RePass_Field = new JPasswordField();
		RePass_Field.setBounds(306, 514, 204, 29);
		contentPane.add(RePass_Field);
		RePass_Field.addKeyListener(new KeyAdapter() {
			@SuppressWarnings("deprecation")
			@Override
			public void keyPressed(KeyEvent e) {
				
				if(e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					int b=User.register(Name_Field.getText(),DOB_Field.getText(),Add_Field.getText(),Email_Field.getText(),UName_Field.getText(),FirstPass_Field.getText(),RePass_Field.getText());
					if(b==0)
					{
						errLabel.setText("No Field should be left vacant");
						JOptionPane.showMessageDialog(null, errLabel);
					}
					if(b==1)
					{
						errLabel.setText("UserName Exists");
						JOptionPane.showMessageDialog(null, errLabel);
					}
					if(b==2)
					{
						errLabel.setText("Password Mismatch");
						JOptionPane.showMessageDialog(null, errLabel);
					}
					if(b==3)
					{
						errLabel.setText("Successful Registration! Login to continue with booking");
						JOptionPane.showMessageDialog(null, errLabel);
						MyConnection.closeConnection();
						LoginFrame obj =new LoginFrame();
						obj.setVisible(true);
						dispose();
					}
				}
			}
		});
		contentPane.add(RePass_Field);
		
		
		JLabel lblNewLabel = new JLabel("All fields marked * compulsary");
		lblNewLabel.setBounds(490, 555, 197, 14);
		contentPane.add(lblNewLabel);
		
	
		
		JButton btnNewButton = new JButton("Submit");
		btnNewButton.setBounds(363, 598, 89, 42);
		btnNewButton.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				int a=User.register(Name_Field.getText(),DOB_Field.getText(),Add_Field.getText(),Email_Field.getText(),UName_Field.getText(),FirstPass_Field.getText(),RePass_Field.getText());
				if(a==0)
				{
					errLabel.setText("No Field should be left vacant");
					JOptionPane.showMessageDialog(null, errLabel);
				}
				if(a==1)
				{
					errLabel.setText("UserName Exists");
					JOptionPane.showMessageDialog(null, errLabel);
				}
				if(a==2)
				{
					errLabel.setText("Password Mismatch");
					JOptionPane.showMessageDialog(null, errLabel);
				}
				if(a==3)
				{
					errLabel.setText("Successful Registration! Login to continue with booking");
					JOptionPane.showMessageDialog(null, errLabel);
					MyConnection.closeConnection();
					LoginFrame obj =new LoginFrame();
					obj.setVisible(true);
					dispose();
				}

			}
		});
		
		btnNewButton.setBackground(new Color(0, 204, 204));
		btnNewButton.setForeground(new Color(255, 255, 255));
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 14));
		contentPane.add(btnNewButton);
		
		JLabel Home_label = new JLabel("");
		Image img4=new ImageIcon(this.getClass().getResource("/Home icon.png")).getImage();
		Home_label.setIcon(new ImageIcon(img4));
		Home_label.setToolTipText("Back to Home");
		Home_label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				WelcomeFrame obj=new WelcomeFrame();
				obj.Welcome_frame.setVisible(true);
				dispose();
			}
		});
		Home_label.setBounds(834, 11, 40, 54);
		contentPane.add(Home_label);
		
		checklbl = new JLabel("");
		checklbl = new JLabel("");
		checklbl.setBounds(522, 412, 24, 29);
		ok = new ImageIcon(this.getClass().getResource("/ok.png")).getImage();
		error = new ImageIcon(this.getClass().getResource("/error.png")).getImage();
		checklbl.setIcon(new ImageIcon(error));
		contentPane.add(checklbl);
		
		JLabel label_3 = new JLabel("-Your Stay our Responsibility");
		label_3.setForeground(new Color(102, 0, 51));
		label_3.setFont(new Font("Consolas", Font.ITALIC, 23));
		label_3.setBounds(290, 11, 399, 65);
		contentPane.add(label_3);

	}
}
