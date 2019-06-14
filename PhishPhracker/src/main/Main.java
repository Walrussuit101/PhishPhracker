package main;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import java.awt.event.*;
import java.util.concurrent.TimeUnit;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		
		//Initialize the JFrame, set to maximized
        JFrame frame = new JFrame("Phish Phracker");
        frame.setVisible(true);
        
        JPanel splash = new JPanel();
        splash.setBackground(Color.BLACK);
        splash.setLayout(new GridBagLayout());
        splash.setVisible(true);
        
        JLabel logo = new JLabel(new ImageIcon("res/phish.jpg"));
        splash.add(logo);
        
        frame.getContentPane().add(splash, BorderLayout.CENTER);
        
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
        try{
        	Thread.sleep(2000);
        	logo.setVisible(false);
        	splash.setBackground(Color.WHITE);
        }catch(InterruptedException e) {
        	System.out.println(e);
        }   
    }
}
