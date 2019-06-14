package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.*;

import javax.swing.*;

public class Main {
	public static void main(String[] args) {
		
		//Initialize the JFrame, set to maximized
        JFrame frame = new JFrame("Phish Phracker");
        
        /* 
         * https://www.javaworld.com/article/2077467/java-tip-104--make-a-splash-with-swing.html
         */
        JLabel l = new JLabel(new ImageIcon("res/phish.jpg"));
        frame.getContentPane().add(l, BorderLayout.CENTER);
        frame.pack();
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension labelSize = l.getPreferredSize();
        frame.setLocation(screenSize.width/2 - (labelSize.width/2), screenSize.height/2 - (labelSize.height/2));
        frame.addMouseListener(new MouseAdapter()
            {
                public void mousePressed(MouseEvent e)
                {
                    frame.setVisible(false);
                    frame.dispose();
                }
            });
        frame.setVisible(true);
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        
    }
}
