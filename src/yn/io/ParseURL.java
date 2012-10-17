package yn.io;

import java.net.URL;

public class ParseURL {
    public static void main(String[] args) throws Exception {

        /*URL aURL = new URL("http://example.com:80/docs/books/tutorial"
                           + "/index.html?name=networking#DOWNLOADING");*/
    	
    	URL aURL = new URL("http://thenextweb.com/uk/2012/10/04/" +
    			"pogoplug-takes-its-amazon-glacier-powered-cloud-backup-service-to-the-uk/" +
    			"?utm_source=feedburner&utm_medium=feed&utm_campaign=Feed:+TheNextWeb+(The+Next+Web+All+Stories)");

        System.out.println("protocol = " + aURL.getProtocol());
        System.out.println("authority = " + aURL.getAuthority());
        System.out.println("host = " + aURL.getHost());
        System.out.println("port = " + aURL.getPort());
        System.out.println("path = " + aURL.getPath());
        System.out.println("query = " + aURL.getQuery());
        System.out.println("filename = " + aURL.getFile());
        System.out.println("ref = " + aURL.getRef());
        
        aURL = new URL("http://thenextweb.com/uk/2012/10/04/" +
        		"pogoplug-takes-its-amazon-glacier-powered-cloud-backup-service-to-the-uk/" +
        		"?utm_source=twitterfeed&utm_medium=twitter&utm_campaign=Feed:+TheNextWeb+(The+Next+Web+All+Stories)");

        System.out.println("protocol = " + aURL.getProtocol());
        System.out.println("authority = " + aURL.getAuthority());
        System.out.println("host = " + aURL.getHost());
        System.out.println("port = " + aURL.getPort());
        System.out.println("path = " + aURL.getPath());
        System.out.println("query = " + aURL.getQuery());
        System.out.println("filename = " + aURL.getFile());
        System.out.println("ref = " + aURL.getRef());
    }
}