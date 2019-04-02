package pruebaScraping;

import java.io.File;
import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class Principal {
	static Element imgTag;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println(getTrackImage("https://en.wikipedia.org/wiki/Adelaide_Street_Circuit"));
	}
	
	public static Document getHtmlDocument(String url) {
	    Document doc = null;
		try {
		    doc = Jsoup.connect(url).userAgent("Mozilla/5.0").timeout(100000).get();
		    } catch (IOException ex) {
			System.out.println("Excepción al obtener el HTML de la página" + ex.getMessage());
		    }
	    return doc;
	}
	
	public static String getDriverImage(String url) {
		Document document = getHtmlDocument(url);
		Elements infobox = document.select("table.infobox.biography.vcard");
		Elements imgTags = infobox.select("img");
		if (imgTags.size() > 1) {
			imgTag = imgTags.first();
			return imgTag.attr("src");
		} else {
			return null;
		}
	}
	
	public static String getTrackImage(String url) {
		Document document = getHtmlDocument(url);
		Elements infobox = document.select("table.infobox.vcard");
		Elements imgTags = infobox.select("img");
		if (imgTags.size() > 1) {
			return imgTags.first().attr("src");
		} else {
			return null;
		}
	}
}
