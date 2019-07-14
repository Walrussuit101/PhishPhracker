package screenBuilders;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;


public class LogInBuilder implements ActionListener{
	private JPanel content;
	private JFrame frame;
	private JTextField username;
	private JTextField password;
	private JButton login;
	
	
	
	
	public LogInBuilder(JFrame frame, JPanel content) {
		this.frame = frame;
		this.content = content;
		
		username = new JTextField();
		username.setColumns(15);
		username.setBorder(BorderFactory.createLineBorder(Color.black));
		password = new JTextField("Password");
		password.setColumns(15);
		password.setBorder(BorderFactory.createLineBorder(Color.black));
		
		login = new JButton("Log In");
		login.setActionCommand("login");
		login.addActionListener(this);
	}
	
	
	public void build() {
		GridBagConstraints c = new GridBagConstraints();
		c.gridwidth = GridBagConstraints.REMAINDER;
		c.anchor = GridBagConstraints.WEST;
		c.fill = GridBagConstraints.VERTICAL;
		
		content.add(username, c);
		content.add(password, c);
		content.add(login, c);
		content.revalidate();
		frame.repaint();
		System.out.println("Succesful Build: LOGINBUILDER");		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//TODO: login duh
	}
	
}
