package screenBuilders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainBuilder {

	JPanel content;
	JFrame frame;
	ArrayList<String> years = new ArrayList<String>();
	ArrayList<String> months = new ArrayList<String>();
	ArrayList<String> days = new ArrayList<String>();
	
	/**Builds main setlist search page,
	 * 
	 * @param content JPanel type, main container in JFrame
	 * @param frame JFrame type, main window
	 */
	public MainBuilder(JFrame frame, JPanel content){
		this.frame = frame;
		this.content = content;
		
		years.add("1982");
		months.add("1");
		days.add("1");
		
		//populate years options
		for(int i = 0; i < 37; i++) {
			int year = Integer.parseInt(years.get(i));
			year++;
			years.add(Integer.toString(year));
		}
		
		//populate months options
		for (int i = 0; i < 11; i++) {
			int month = Integer.parseInt(months.get(i));
			month++;
			months.add(Integer.toString(month));
		}
		
		//populate days options
		for (int i = 0; i < 30; i++) {
			int day = Integer.parseInt(days.get(i));
			day++;
			days.add(Integer.toString(day));
		}	
	}
	
	public void build(){
		
		//build JComboBoxes for year/month/day selection
		JComboBox<String> yearSelect = new JComboBox<String>(years.toArray(new String[years.size()]));
		yearSelect.setBackground(Color.white);
		yearSelect.setBorder(new EmptyBorder(0,0,0,10));
		
		JComboBox<String> monthSelect = new JComboBox<String>(months.toArray(new String[months.size()]));
		monthSelect.setBackground(Color.white);
		monthSelect.setBorder(new EmptyBorder(0,10,0,10));
	
		JComboBox<String> daySelect = new JComboBox<String>(days.toArray(new String[days.size()]));
		daySelect.setBackground(Color.white);
		daySelect.setBorder(new EmptyBorder(0,10,0,10));
		
		//add JComboBoxes to content JPanel
		content.add(yearSelect);
		content.add(monthSelect);
		content.add(daySelect);
		content.revalidate();
	}
}
