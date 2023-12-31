package client;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.UUID;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class register extends JPanel {
	private JTextField email;
	private JPasswordField password;
	private JTextField name;

	private static int maxUserID = 1000000;
	private static int minUserID = 1;
	private Application parent;
	private JTextField fullname;

	/**
	 * Create the application.
	 */
	public register(Application app) {
		this.parent = app;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		//setResizable(false);
		setForeground(Color.BLACK);
		//setTitle("Register");
		setFont(new Font("Source Code Pro Light", Font.PLAIN, 12));
		setBackground(Color.WHITE);
		//frmRegister.setBackground(Color.WHITE);
		setFont(new Font("Source Code Pro Medium", Font.PLAIN, 11));
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

		JPanel panel = new JPanel();
		panel.setBackground(new Color(135, 206, 250));
		add(panel);

		JPanel panel_1 = new JPanel();
		panel_1.setBackground(Color.WHITE);
		add(panel_1);
		panel_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("Register");
		lblNewLabel.setFont(new Font("Source Code Pro ExtraBold", Font.PLAIN, 14));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 36, 290, 28);
		panel_1.add(lblNewLabel);
		
		email = new JTextField();
		email.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		email.setPreferredSize(new Dimension(7, 22));
		email.setBounds(45, 216, 202, 28);
		panel_1.add(email);
		email.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("email:");
		lblNewLabel_1.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		lblNewLabel_1.setBounds(45, 195, 202, 14);
		panel_1.add(lblNewLabel_1);

		password = new JPasswordField();
		password.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		password.setPreferredSize(new Dimension(7, 22));
		password.setColumns(10);
		password.setBounds(45, 276, 202, 28);
		panel_1.add(password);

		JLabel lblNewLabel_1_1 = new JLabel("password:");
		lblNewLabel_1_1.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		lblNewLabel_1_1.setBounds(45, 255, 202, 14);
		panel_1.add(lblNewLabel_1_1);

		JButton btnNewButton = new JButton("Register");
		btnNewButton.setBorder(null);
		btnNewButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(name.getText() != "" && password.getText() != "" && email.getText() != "") {
					String id = UUID.randomUUID().toString();
					String hashedPW = User.hashPassword(password.getText());

					if (hashedPW == null) hashedPW = password.getText();
					try {
						parent.write("Register|"+id+"|"+name.getText()+"|"+fullname.getText()+"|"+email.getText()+"|"+hashedPW);
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
		btnNewButton.setBounds(44, 330, 203, 38);
		panel_1.add(btnNewButton);

		JButton btnLogin = new JButton("Login");
		btnLogin.setBorder(UIManager.getBorder("Button.border"));
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				parent.ClearTab();
				//parent.applicationFrame.setLayout(new BoxLayout(parent.applicationFrame, BoxLayout.X_AXIS));
				parent.ChangeTab(new login(parent), 605, 476);
			}
		});
		btnLogin.setForeground(new Color(30, 144, 255));
		btnLogin.setFont(new Font("Source Code Pro Black", Font.PLAIN, 11));
		btnLogin.setBackground(Color.WHITE);
		btnLogin.setBounds(44, 378, 203, 38);
		panel_1.add(btnLogin);

		name = new JTextField();
		name.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		name.setPreferredSize(new Dimension(7, 22));
		name.setColumns(10);
		name.setBounds(45, 161, 202, 28);
		
		panel_1.add(name);
		JLabel lblNewLabel_1_2 = new JLabel("name:");
		lblNewLabel_1_2.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		lblNewLabel_1_2.setBounds(45, 140, 202, 14);
		panel_1.add(lblNewLabel_1_2);
		
		JLabel lblNewLabel_1_2_1 = new JLabel("fullname:");
		lblNewLabel_1_2_1.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		lblNewLabel_1_2_1.setBounds(45, 80, 202, 14);
		panel_1.add(lblNewLabel_1_2_1);
		
		fullname = new JTextField();
		fullname.setPreferredSize(new Dimension(7, 22));
		fullname.setFont(new Font("Source Code Pro", Font.PLAIN, 11));
		fullname.setColumns(10);
		fullname.setBounds(45, 101, 202, 28);
		panel_1.add(fullname);

		setBounds(100, 100, 605, 492);
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}