package screenBuilders;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import actions.EmailSender;

public class ResultBuilder implements ActionListener{
	private JPanel content;
	private JFrame frame;
	private JScrollPane setlistsComponent;

	
	private String PHISHNETURL = "http://phish.net/setlists/";
	private ArrayList<String> selections;
	
	private Document html;
	
	/**Builds resultBuilder object
	 * preps URL for scraping
	 * 
	 * @param frame JFrame main frame
	 * @param content JPanel main content panel
	 * @param year String selected year
	 * @param month String selected month
	 * @param day String selected day
	 */
	public ResultBuilder(JFrame frame, JPanel content, ArrayList<String> selections) {
		this.frame = frame;
		this.content = content;
		
		this.selections = selections;
		
		//will always have a year
		PHISHNETURL += "?year=" + this.selections.get(0);
		
		//Build rest of url if given a month/day selection
		for(int i = 1; i < this.selections.size(); i++) {
			if(i == 1 && !this.selections.get(i).equals("MONTH")) {
				PHISHNETURL += "&month=" + selections.get(i);
			}else if(i == 2 && !this.selections.get(i).equals("DAY")) {
				PHISHNETURL += "&day=" + selections.get(i);
			}
		}
		
		System.out.println("Search: " + PHISHNETURL);
		html = getHTML(PHISHNETURL);
	}
	
	/**Builds resultScreen in content JPanel
	 */
	public void build() {
		
		//If setlist not found, return to search screen
		if(!html.title().contains("Setlist")) {
			JOptionPane.showMessageDialog(null, "No setlists found :(");
			
			MainBuilder searchScreen = new MainBuilder(frame, content);
			searchScreen.build();
			
		//if setlist found, build jlabels for setlist/notes/etc.
		}else {
			
			JButton back = new JButton("Back");
			back.setActionCommand("back");
			back.addActionListener(this);
			content.add(back);
			
			ArrayList<Element> setlists = html.getElementsByClass("setlist");
			
			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.VERTICAL;
			
			Dimension sizeDate = new Dimension(content.getWidth()-20, 50);
			Dimension sizeBody = new Dimension(content.getWidth()-20, 150);
			Dimension sizeFooter = new Dimension(content.getWidth()-20, 40);
			
			//start adding setlists into content JPanel
			//Have a row for set1/2/encore/footer, handled by HTML
			for(Element setlist : setlists) {
				
				//get for naming the body JLabel, and the email JButton
				String dateString = setlist.getElementsByClass("setlist-date-long").get(0).text();
				
				//date label
				JLabel date = new JLabel("<html><p style='font-size: 24;'>" + setlist.getElementsByClass("setlist-date-long").get(0).text() + "</p><br></html>");				
				date.setPreferredSize(sizeDate);
				date.setVisible(true);
				content.add(date, c);
				
				//setlist label
				//makes a new line for each <p> tag, which holds seperate sets
				JLabel body = new JLabel("<html><p style='font-size: 12;'>");
				ArrayList<Element> pTags = setlist.getElementsByClass("setlist-body").get(0).getElementsByTag("p");
				for(Element pTag : pTags) {
					body.setText(body.getText() + pTag.text() + "<br>");
				}
				body.setText(body.getText() + "</p></html>");
				body.setPreferredSize(sizeBody);
				body.setVisible(true);
				body.setName(dateString);
				content.add(body, c);

				//footer label
				JLabel footer = new JLabel("<html><p style='font-size: 10;'>" + setlist.getElementsByClass("setlist-footer").get(0).text() + "</p><br></html>");				
				footer.setPreferredSize(sizeFooter);
				footer.setVisible(true);
				content.add(footer, c);
				
				//button to send a show via email
				JButton emailShow = new JButton("Email Show");
				emailShow.setActionCommand(dateString + "-button");
				emailShow.addActionListener(this);
				content.add(emailShow, c);
			}
			
			setlistsComponent = new JScrollPane(content);
			setlistsComponent.getVerticalScrollBar().setUnitIncrement(10);
			frame.getContentPane().add(setlistsComponent, BorderLayout.CENTER);
			frame.getContentPane().revalidate();
			content.revalidate();
			frame.repaint();
		}
		System.out.println("Succesful Build: RESULTBUILDER");
	}
	
	/**Simply gets html from given url
	 * 
	 * @param url webpage to retrieve
	 * @return html from given url
	 */
	public Document getHTML(String url) {
		
		Document html = null;
		
		try {
			html = Jsoup.connect(url).get();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}

		return html;
	}
	
	@Override
	/**If action is "back" clear content, and rebuild main/search screen
	 * If action is date+"-button" then send an email with the setlist
	 */
	public void actionPerformed(ActionEvent e) {		
		if("back".equals(e.getActionCommand())) {
			
			System.out.println("Go Back");
			
			content.removeAll();
			content.revalidate();
			frame.getContentPane().remove(setlistsComponent);
			frame.getContentPane().revalidate();
			frame.getContentPane().repaint();
			frame.revalidate();
			frame.repaint();
			
			MainBuilder searchScreen2 = new MainBuilder(frame, content);
			searchScreen2.build();
			
		}else if(e.getActionCommand().contains("-button")) {
			Component[] components = content.getComponents();
			
			//Parse date out of JButton actionCommand
			String action = e.getActionCommand();
			int end = action.indexOf("-");
			String date = action.substring(0, end);
			System.out.println("Send Email: " + date);
			
			//Search for component with the name of "date"
			//Must cast component to JLabel to get the text, 
			//which contains the html formatted setlist
			for(Component c : components) {
				if(date.equals(c.getName())) {
					EmailSender emailSender = new EmailSender("tjefferson@ycp.edu");
					JLabel body = (JLabel) c;
					emailSender.sendShowEmail(date, body.getText());
				}
			}	
		}
	}
}