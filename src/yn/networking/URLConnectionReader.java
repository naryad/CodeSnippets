package yn.networking;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

public class URLConnectionReader {
	private static String JSOUP_USER_AGENT = "Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36";
	private static Pattern SUPPORTED_CONTENT_TYPES = Pattern.compile("(?i)image/.*|text/html.*");
	
	public static void main(String[] args) throws Exception {
		//String link = "http://goo.gl/023aBc";
		//String link = "https://crowdspotsimages-localdev.s3-us-west-2.amazonaws.com/user-content/KcWSMXqGSE6kgLxZYYpa3g.jpg";
		String link = "http://i.imgur.com/qFITz1V.png?1";
		//String link = "https://pic.twitter.com/83SrHj8cFO";
		/*URL yahoo = new URL(link);
		URLConnection yc = yahoo.openConnection();
		BufferedReader in = new BufferedReader(new InputStreamReader(
				yc.getInputStream()));
		String inputLine;

		while ((inputLine = in.readLine()) != null) {
			System.out.println(inputLine);
		}
		in.close();*/
		
		Document document = null;
		
		/*document = Jsoup.connect(link).timeout(10000).userAgent(JSOUP_USER_AGENT).followRedirects(true).get();
		Elements meta = document.select("html head meta");
		if (StringUtils.containsIgnoreCase(meta.attr("http-equiv"), "refresh"))
	        document = Jsoup.connect(meta.attr("content").split("=")[1]).get();
		System.out.println(document);*/
		Response response = Jsoup.connect(link).followRedirects(true).ignoreContentType(true).execute();
		document = response.parse();
		System.out.println("Base URI " + document.baseUri());
		/*System.out.println(document);
		String descriptionFromParagraph = "";
		Elements paras = document.select("p");
		if (paras.size() > 0)
			descriptionFromParagraph = paras.first().text();
		System.out.println(descriptionFromParagraph);*/
		/*System.out.println(response.statusCode());
		String redirectUrl = response.header("location");
		System.out.println(redirectUrl);
		System.out.println(response.url());*/
		
		System.out.println(SUPPORTED_CONTENT_TYPES.matcher("TEXT/html charset=UTF-8").matches());
	}
}
