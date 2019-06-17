package screenBuilders;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class ResultBuilder {
	private JPanel content;
	private JFrame frame;
	
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
		
		System.out.println(PHISHNETURL);
		html = getHTML(PHISHNETURL);
	}
	
	/**Builds resultScreen in content JPanel
	 */
	public void build() {
		
		//If setlist not found return to search screen
		if(!html.title().contains("Setlist")) {
			JOptionPane.showMessageDialog(null, "No setlists found :(");
			
			MainBuilder searchScreen = new MainBuilder(frame, content);
			searchScreen.build();
			
		//setlist found, build jlabels for setlist/notes/etc.
		}else {
			
			ArrayList<Element> setlists = html.getElementsByClass("setlist");
			
			GridBagConstraints c = new GridBagConstraints();
			c.gridwidth = GridBagConstraints.REMAINDER;
			c.anchor = GridBagConstraints.WEST;
			c.fill = GridBagConstraints.VERTICAL;
			
			Dimension sizeDate = new Dimension(content.getWidth()-20, 50);
			Dimension sizeBody = new Dimension(content.getWidth()-20, 150);
			Dimension sizeFooter = new Dimension(content.getWidth()-20, 20);
			
			for(Element setlist : setlists) {
				
				JLabel date = new JLabel("<html><p style='font-size: 24;'>" + setlist.getElementsByClass("setlist-date-long").get(0).text() + "</p><br></html>");				
				date.setPreferredSize(sizeDate);
				date.setVisible(true);
				content.add(date, c);
				
				JLabel body = new JLabel("<html><p style='font-size: 12;'>");
				ArrayList<Element> pTags = setlist.getElementsByClass("setlist-body").get(0).getElementsByTag("p");
				for(Element pTag : pTags) {
					body.setText(body.getText() + pTag.text() + "<br>");
				}
				body.setText(body.getText() + "</p></html>");
				body.setPreferredSize(sizeBody);
				body.setVisible(true);
				content.add(body, c);

				
				JLabel footer = new JLabel("<html><p style='font-size: 10;'>" + setlist.getElementsByClass("setlist-footer").get(0).text() + "</p><br></html>");				
				footer.setPreferredSize(sizeFooter);
				footer.setVisible(true);
				content.add(footer, c);
			}
			
			JScrollPane setlistsComponent = new JScrollPane(content);
			setlistsComponent.getVerticalScrollBar().setUnitIncrement(10);
			frame.getContentPane().add(setlistsComponent, BorderLayout.CENTER);
			content.revalidate();
			frame.repaint();
			
//			ArrayList<Component> components = new ArrayList<Component>(Arrays.asList(content.getComponents()));
//			for(Component component : components) {
//				System.out.println(component.getName());
//				System.out.println(component.getSize());
//			}
		}
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
}
