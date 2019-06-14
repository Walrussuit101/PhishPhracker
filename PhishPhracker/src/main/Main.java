package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;

import javax.swing.*;

public class Main {
	
	/**Simply builds the initial frame/displays splash screen,
	 * Then hands things off to other builders for other screens
	 */
	public static void main(String[] args) {
		
		//Initialize the JFrame, set to maximized, end program on close
        JFrame frame = new JFrame("Phish Phracker");
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Initialize splash screen/logo
        JPanel splash = new JPanel();
        splash.setBackground(Color.BLACK);
        splash.setLayout(new GridBagLayout());
        splash.setVisible(true);
        JLabel logo = new JLabel(new ImageIcon("res/phish.jpg"));
        splash.add(logo);
        
        //add splash screen panel to frame
        frame.getContentPane().add(splash, BorderLayout.CENTER);
        
        //wait three seconds before removing splash screen
        try{
        	Thread.sleep(3000);
        	logo.setVisible(false);
        	splash.setBackground(Color.WHITE);
        }catch(InterruptedException e) {
        	System.out.println(e);
        }   
        
    }
}
