package screenBuilders;

import javax.swing.*;

public class ResultBuilder {
	private JPanel content;
	private JFrame frame;
	
	private String PHISHNETURL = "http://phish.net/setlists/phish/";
	private String year;
	private String month;
	private String day;
	
	/**Builds resultBuilder object
	 * preps URL for scraping
	 * 
	 * @param frame JFrame main frame
	 * @param content JPanel main content panel
	 * @param year String selected year
	 * @param month String selected month
	 * @param day String selected day
	 */
	public ResultBuilder(JFrame frame, JPanel content, String year, String month, String day) {
		this.frame = frame;
		this.content = content;
		
		this.year = year;
		this.month = month;
		this.day = day;
		
		PHISHNETURL += "year=" + this.year + "&month=" + this.month + "&day=" + this.day;
		System.out.println(PHISHNETURL);
	}
}
