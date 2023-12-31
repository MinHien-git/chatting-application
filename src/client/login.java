package client;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.security.SecureRandom;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class login extends JPanel {
	private JTextField email;
	private JPasswordField password;
	private Application parent;
	/**
	 * Create the application.
	 */
	public login(Application parent) {
		this.parent = parent;
		initialize();
	}

	public static String generateRandomPassword(int length) {
		String validChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

		SecureRandom random = new SecureRandom();
		StringBuilder password = new StringBuilder(length);

		for (int i = 0; i < length; i++) {
			int randomIndex = random.nextInt(validChars.length());
			password.append(validChars.charAt(randomIndex));
		}

		return password.toString();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		setForeground(Color.BLACK);
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Login");
		lblNewLabel.setFont(new Font("Source Code Pro ExtraBold", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 70, 290, 28);
		panel_1.add(lblNewLabel);

		email = new JTextField();
		email.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		email.setPreferredSize(new Dimension(7, 22));
		email.setBounds(46, 129, 202, 28);
		panel_1.add(email);
		email.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("email or username:");
		lblNewLabel_1.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(46, 108, 202, 14);
		panel_1.add(lblNewLabel_1);

		password = new JPasswordField();
		password.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		password.setPreferredSize(new Dimension(7, 22));
		password.setColumns(10);
		password.setBounds(46, 189, 202, 28);
		panel_1.add(password);

		JLabel lblNewLabel_1_1 = new JLabel("password:");
		lblNewLabel_1_1.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(46, 168, 202, 14);
		panel_1.add(lblNewLabel_1_1);

		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBorder(UIManager.getBorder("Button.border"));
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(password.getText() != "" && email.getText() != "") {
					String hashedPW = User.hashPassword(password.getText());
					if (hashedPW == null) hashedPW = password.getText();

					User user = new User(email.getText(),hashedPW);
					//String _id = user.LogIn();
					try {
						parent.write("Login|"+email.getText()+"|"+hashedPW);
					}catch (IOException ex) {
						System.out.println("An error occurred");
						ex.printStackTrace();
					}
				}
			}
		});
		btnNewButton.setForeground(Color.WHITE);
		btnNewButton.setBackground(new Color(30, 144, 255));
		btnNewButton.setFont(new Font("Source Code Pro Black", Font.PLAIN, 11));
		btnNewButton.setBounds(46, 246, 203, 38);
		panel_1.add(btnNewButton);

		JButton btnlogin = new JButton("Register");
		btnlogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.ClearTab();
				//parent.applicationFrame.setLayout(new BoxLayout(parent.applicationFrame, BoxLayout.X_AXIS));
				parent.ChangeTab(new register(parent), 605, 476);
			}
		});
		btnlogin.setForeground(new Color(30, 144, 255));
		btnlogin.setFont(new Font("Source Code Pro Black", Font.PLAIN, 11));
		btnlogin.setBackground(Color.WHITE);
		btnlogin.setBounds(46, 294, 203, 38);
		panel_1.add(btnlogin);

		JButton resetPW = new JButton("Reset Password");
		resetPW.setBorder(UIManager.getBorder("Button.border"));
		resetPW.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				String toEmail = JOptionPane.showInputDialog("Please specify your email for password recovery");
				try {
					parent.write("ResetPassword|"+toEmail);
				}catch (IOException ex) {
					System.out.println("An error occurred");
					ex.printStackTrace();
				}
			 }
		});
		resetPW.setForeground(Color.WHITE);
		resetPW.setBackground(Color.RED);
		resetPW.setFont(new Font("Source Code Pro Black", Font.PLAIN, 11));
		resetPW.setBounds(46, 342, 203, 38);
		panel_1.add(resetPW);

		setBounds(100, 100, 605, 476);
//		frmLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}