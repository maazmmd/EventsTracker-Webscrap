/**
 * 
 */
package com.galendata.eventtracker.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.ResourceUtils;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.galendata.eventtracker.model.Events;

/**
 * @author mohammedmaaz
 *
 */
public class WebscrapRestAction {

	final String webScrapTag1 = "website1";
	final String webScrapTag2 = "website2";

	public String[] getUrls() {
		String[] webScrapPages = new String[2];
		try {

			File xmlFile = ResourceUtils.getFile("classpath:config.xml");
			DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
			org.w3c.dom.Document doc = dBuilder.parse(xmlFile);
			doc.getDocumentElement().normalize();

			NodeList nList = doc.getElementsByTagName("webpages");
			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node nNode = nList.item(temp);
				if (nNode.getNodeType() == Node.ELEMENT_NODE) {
					org.w3c.dom.Element eElement = (org.w3c.dom.Element) nNode;
					webScrapPages[0] = eElement.getElementsByTagName(webScrapTag1).item(0).getTextContent();
					webScrapPages[1] = eElement.getElementsByTagName(webScrapTag2).item(0).getTextContent();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return webScrapPages;
	}

	public List<Events> parseWebPage1(String url) {
		List<Events> eventsList = new ArrayList<Events>();
		try {
			// fetch the document over HTTP
			Document doc = Jsoup.connect(url).get();

			// Get the page title
			String title = doc.title();

			// Get all links in page
			Elements links = doc.select("div.rhov > a");
			for (Element link : links) {
				Events oEvent = new Events();
				if (link.attr("href").contains("gotos")) {
					String href = link.attr("href");
					oEvent.setWebsite("https://" + href.substring(7, href.length() - 1));
				} else
					oEvent.setWebsite(link.attr("href"));
				Document doc1 = Jsoup.parse(link.html());
				Elements divs = doc1.select("div"); // Get all the div's inside <a> anchor tag
				oEvent.setOrigin(title);
				int count = 0;
				for (Element elem : divs) {
					String html = elem.html();
					if (count == 0) {
						oEvent.setStartDate(html.split("-")[0]);
						if (!(html.length() == 5 || html.length() == 6))
							oEvent.setEndDate(html.substring(0, 3).concat(" " + html.split("-")[1]));
					} else if (count == 1) {
						elem.select("em").remove(); // Remove all the em tags
						elem.select("span").remove(); // Remove all the span tags
						oEvent.setEvent(elem.html().replace("&amp;", "and"));
					} else {
						oEvent.setLocation(html);
					}
					count++;
				}
				eventsList.add(oEvent);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return eventsList;
	}

	public List<Events> parseWebPage2(String url) {
		List<Events> eventsList = new ArrayList<Events>();
		try {
			// fetch the document over HTTP
			// Document doc =
			Document doc = Jsoup.connect(url).get();

			// Get the page title
			String title = doc.title();
			// Get all links in page
			Elements table = doc.select("table#cwsearchabletable"); // select the first table.
			Elements rows = table.select("tr");

			for (int i = 1; i < rows.size(); i++) { // first row is the col names so skip it, which is the table header.
				Events oEvent = new Events();
				oEvent.setOrigin(title);

				Element row = rows.get(i);
				oEvent.setEvent(row.select("th").select("a").html());
				oEvent.setWebsite(row.select("th").select("a").attr("href"));
				Elements cols = row.select("td");
				int count = 0;
				for (Element elem : cols) {
					String html = elem.html();
					if (count == 0) {
						count++;
						continue;
					} else if (count == 1)
						oEvent.setStartDate(html);
					else if (count == 2)
						oEvent.setEndDate(html);
					else
						oEvent.setLocation(html);
					count++;
				}
				eventsList.add(oEvent);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return eventsList;
	}
}
