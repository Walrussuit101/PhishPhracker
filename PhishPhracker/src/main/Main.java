package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Main {
	
	/**Simply builds the initial frame/displays splash screen,
	 * Then hands things off to other builders for other screens
	 */
	public static void main(String[] args) {
		
		//Initialize the JFrame, set to maximized, end program on close
        JFrame frame = new JFrame("Phish Phracker");
        frame.setBackground(Color.BLACK);
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        
        //Initialize content panel
        JPanel content = new JPanel();
        content.setBackground(Color.BLACK);
        content.setLayout(new GridBagLayout());
        content.setVisible(true);
        
        //Initialize logo, add GridBag constraints
        JLabel logo = new JLabel(new ImageIcon("res/phish.jpg"));
        GridBagConstraints c = new GridBagConstraints();
        c.anchor = GridBagConstraints.CENTER;
        c.gridx = 1;
        c.gridy = 1;
        content.add(logo, c);
        
        //Initialize title, add GridBag constraints
        JLabel title = new JLabel("PHISH PHRACKER");
        title.setForeground(Color.WHITE);
        title.setFont(new Font("MS PGothic", Font.BOLD, 60));
        title.setBorder(new EmptyBorder(50,0,0,0));
        title.setBackground(Color.CYAN);
        c.anchor = GridBagConstraints.PAGE_END;
        c.gridx = 1;
        c.gridy = 2;
        content.add(title, c);
        
        //add splash screen panel to frame
        frame.add(content);
        
        //wait three seconds before removing logo
        try{
        	Thread.sleep(4000);
        	content.remove(logo);
        	content.remove(title);
        	content.setBackground(Color.WHITE);
        	content.revalidate();
        	frame.repaint();
        }catch(InterruptedException e) {
        	System.out.println(e);
        }   
        
    }
}
