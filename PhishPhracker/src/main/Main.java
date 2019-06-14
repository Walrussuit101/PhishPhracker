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
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
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
