package screenBuilders;

import java.io.IOException;

import javax.swing.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class ResultBuilder {
	private JPanel content;
	private JFrame frame;
	
	private String PHISHNETURL = "http://phish.net/setlists/phish/";
	private String year;
	private String month;
	private String day;
	
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
	public ResultBuilder(JFrame frame, JPanel content, String year, String month, String day) {
		this.frame = frame;
		this.content = content;
		
		this.year = year;
		this.month = month;
		this.day = day;
		
		PHISHNETURL += "year=" + this.year + "&month=" + this.month + "&day=" + this.day;
		html = getHTML(PHISHNETURL);
	}
	
	/**Builds resultScreen in content JPanel
	 */
	public void build() {
		System.out.println(html.title());
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
