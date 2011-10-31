package yn.html;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HTMLParsingExperiments {
	public static void main(String[] args) throws Exception {
		/*
		{
		  "href": "http://stackoverflow.com/questions/3152138/what-are-the-pros-and-cons-of-the-leading-java-html-parsers",
		  "name": "What are the pros and cons of the leading Java HTML parsers? - Stack Overflow",
		  "caption": "stackoverflow.com",
		  "description": "Searching SO and Google, I've found that there are a few Java HTML parsers which are consistently recommended by various parties. Unfortunately it's hard to find any information on the strengths and weaknesses of the various libraries. I'm hoping that some people have spent some comparing these libr…",
		  "media": [
		    {
		      "type": "image",
		      "src": "http://sstatic.net/stackoverflow/img/apple-touch-icon.png"
		    }
		  ]
		}
		*/
		String url = "http://stackoverflow.com/questions/3152138/what-are-the-pros-and-cons-of-the-leading-java-html-parsers";
		Document document = Jsoup.connect(url).timeout(0).get();
		
		url = "http://t.co/NkzwqvZ3";
		document = Jsoup.connect(url).timeout(0).get();
		Connection con = Jsoup.connect(url).timeout(0);
		System.out.println(document.select("link[rel=canonical]").attr("href"));
		System.out.println(document.select("meta[property=og:image]").attr("content"));
		System.out.println(document.select("img").attr("src"));
		/*for (int i = 0; i < 10; i++) {
		    long t0 = System.nanoTime();
		    Connection c = Jsoup.connect("http://stackoverflow.com/questions/6696666/jsoup-connect-java-takes-a-long-time").timeout(10*1000);
		    long t1 = System.nanoTime();
		    Document doc = c.get();
		    long t2 = System.nanoTime();

		    System.err.println(url + ": t1=" + (t1-t0) + "   t2=" + (t2-t1));
		}*/
	}
}
