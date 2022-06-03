import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.mysql.cj.xdevapi.Statement;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class LoginPage {

	private JFrame frame;
	private JTextField textUsername;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginPage window = new LoginPage();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public LoginPage() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Login System");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel.setBounds(173, 29, 112, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblUsername = new JLabel("Username");
		lblUsername.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblUsername.setBounds(59, 89, 89, 14);
		frame.getContentPane().add(lblUsername);
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblPassword.setBounds(59, 155, 89, 14);
		frame.getContentPane().add(lblPassword);
		
		textUsername = new JTextField();
		textUsername.setBounds(173, 88, 96, 20);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		password = new JTextField();
		password.setBounds(173, 154, 96, 20);
		frame.getContentPane().add(password);
		password.setColumns(10);
		
		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String un =textUsername.getText();
				String p = password.getText();
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/hospital","root","");
					Statement st = (Statement) conn.createStatement();
					String sql = "select * from user_login";
					ResultSet rs = ((java.sql.Statement) st).executeQuery(sql);
					while(rs.next()) {
						String username=rs.getString("username");
						String password=rs.getString("password");
						if(un.equals(username) && p.equals(password)){
							welcome info = new welcome();
							welcome.main(null);
						}
						else {
							JOptionPane.showMessageDialog(null, "Wrong username and password");
						}
					}
				}catch(Exception e1) {
					JOptionPane.showMessageDialog(null, "Connection error!!!");
				}
			}
		});
		btnSubmit.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnSubmit.setBounds(293, 216, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}

	protected void dispose() {
		// TODO Auto-generated method stub
		
	}
}
