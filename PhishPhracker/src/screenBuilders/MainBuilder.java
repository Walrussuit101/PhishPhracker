package screenBuilders;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class MainBuilder implements ActionListener{
	
	private JPanel content;
	private JFrame frame;
	
	private ArrayList<String> years = new ArrayList<String>();
	private ArrayList<String> months = new ArrayList<String>();
	private ArrayList<String> days = new ArrayList<String>();
	
	private JComboBox<String> yearSelect = null;
	private JComboBox<String> monthSelect = null;
	private JComboBox<String> daySelect = null;
	private JButton submit = new JButton("Search");
	
	/**Builds main setlist search page,
	 * 
	 * @param content JPanel type, main container in JFrame
	 * @param frame JFrame type, main window
	 */
	public MainBuilder(JFrame frame, JPanel content){
		this.frame = frame;
		this.content = content;
		
		years.add("YEAR");
		months.add("MONTH");
		days.add("DAY");
		
		years.add("1982");
		months.add("1");
		days.add("1");
		
		//populate years options
		for(int i = 1; i < 38; i++) {
			int year = Integer.parseInt(years.get(i));
			year++;
			years.add(Integer.toString(year));
		}
		
		//populate months options
		for (int i = 1; i < 12; i++) {
			int month = Integer.parseInt(months.get(i));
			month++;
			months.add(Integer.toString(month));
		}
		
		//populate days options
		for (int i = 1; i < 31; i++) {
			int day = Integer.parseInt(days.get(i));
			day++;
			days.add(Integer.toString(day));
		}	
		
		yearSelect = new JComboBox<String>(years.toArray(new String[years.size()]));
		monthSelect = new JComboBox<String>(months.toArray(new String[months.size()])); 
		daySelect = new JComboBox<String>(days.toArray(new String[days.size()]));
	}
	
	/**Builds search screen with JComboBoxes/JButtons
	 * User can search setlist by date
	 */
	public void build(){
		
		//build JComboBoxes for year/month/day selection
		yearSelect.setBackground(Color.white);
		yearSelect.setBorder(new EmptyBorder(0,0,0,10));
		
		monthSelect.setBackground(Color.white);
		monthSelect.setBorder(new EmptyBorder(0,10,0,10));
	
		daySelect.setBackground(Color.white);
		daySelect.setBorder(new EmptyBorder(0,10,0,10));
				
		submit.setActionCommand("submit");
		submit.addActionListener(this);
		
		//add JComboBoxes to content JPanel
		content.add(yearSelect);
		content.add(monthSelect);
		content.add(daySelect);
		content.add(submit);
		content.revalidate();
		frame.repaint();
	}
	
	@Override
	/**If action is "submit" clear content JPanel, then call ResultBuilder
	 * pass selected year/month/day
	 */
	public void actionPerformed(ActionEvent e) {
		if("submit".equals(e.getActionCommand())) {
			
			//build selections array so ResultBuilder can iterate through them
			ArrayList<String> selections = new ArrayList<String>();
			selections.add((String) yearSelect.getSelectedItem());
			selections.add((String) monthSelect.getSelectedItem());
			selections.add((String) daySelect.getSelectedItem());
			
			//need atleast a year given
			if(selections.get(0).equals("YEAR")) {
				JOptionPane.showMessageDialog(null, "Please select a year");
			
			//if all ok remove all components and build results screen
			}else {
				content.removeAll();
				content.revalidate();
				frame.repaint();
				
				ResultBuilder resultScreen = new ResultBuilder(frame, content, selections);
				
				resultScreen.build();
			}
		}
	}
}