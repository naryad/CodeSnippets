package yn.networking;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import org.apache.commons.lang.StringUtils;

public class NormalizeURL
{
	public static String normalize1(final String taintedURL) throws MalformedURLException
    {
        final URL url;
        try
        {
            url = new URI(taintedURL).normalize().toURL();
        }
        catch (URISyntaxException e) {
            throw new MalformedURLException(e.getMessage());
        }

        final String path = url.getPath().replace("/$", "");
        final SortedMap<String, String> params = createParameterMap(url.getQuery());
        final int port = url.getPort();
        final String queryString;

        if (params != null)
        {
            // Some params are only relevant for user tracking, so remove the most commons ones.
            for (Iterator<String> i = params.keySet().iterator(); i.hasNext();)
            {
                final String key = i.next();
                if (key.startsWith("utm_") || key.contains("session"))
                {
                    i.remove();
                }
            }
            queryString = "?" + canonicalize(params);
        }
        else
        {
            queryString = "";
        }

        return url.getProtocol() + "://" + url.getHost()
            + (port != -1 && port != 80 ? ":" + port : "")
            + path + queryString;
    }

    /**
     * Takes a query string, separates the constituent name-value pairs, and
     * stores them in a SortedMap ordered by lexicographical order.
     * @return Null if there is no query string.
     */
    private static SortedMap<String, String> createParameterMap(final String queryString)
    {
        if (queryString == null || queryString.isEmpty())
        {
            return null;
        }

        final String[] pairs = queryString.split("&");
        final Map<String, String> params = new HashMap<String, String>(pairs.length);

        for (final String pair : pairs)
        {
            if (pair.length() < 1)
            {
                continue;
            }

            String[] tokens = pair.split("=", 2);
            for (int j = 0; j < tokens.length; j++)
            {
                try
                {
                    tokens[j] = URLDecoder.decode(tokens[j], "UTF-8");
                }
                catch (UnsupportedEncodingException ex)
                {
                    ex.printStackTrace();
                }
            }
            switch (tokens.length)
            {
                case 1:
                {
                    if (pair.charAt(0) == '=')
                    {
                        params.put("", tokens[0]);
                    }
                    else
                    {
                        params.put(tokens[0], "");
                    }
                    break;
                }
                case 2:
                {
                    params.put(tokens[0], tokens[1]);
                    break;
                }
            }
        }

        return new TreeMap<String, String>(params);
    }

    /**
     * Canonicalize the query string.
     *
     * @param sortedParamMap Parameter name-value pairs in lexicographical order.
     * @return Canonical form of query string.
     */
    private static String canonicalize(final SortedMap<String, String> sortedParamMap)
    {
        if (sortedParamMap == null || sortedParamMap.isEmpty())
        {
            return "";
        }

        final StringBuffer sb = new StringBuffer(350);
        final Iterator<Map.Entry<String, String>> iter = sortedParamMap.entrySet().iterator();

        while (iter.hasNext())
        {
            final Map.Entry<String, String> pair = iter.next();
            sb.append(percentEncodeRfc3986(pair.getKey()));
            sb.append('=');
            sb.append(percentEncodeRfc3986(pair.getValue()));
            if (iter.hasNext())
            {
                sb.append('&');
            }
        }

        return sb.toString();
    }

    /**
     * Percent-encode values according the RFC 3986. The built-in Java URLEncoder does not encode
     * according to the RFC, so we make the extra replacements.
     *
     * @param string Decoded string.
     * @return Encoded string per RFC 3986.
     */
    private static String percentEncodeRfc3986(final String string)
    {
        try
        {
            return URLEncoder.encode(string, "UTF-8").replace("+", "%20").replace("*", "%2A").replace("%7E", "~");
        }
        catch (UnsupportedEncodingException e)
        {
            return string;
        }
    }
    
    public static void main(String[] args) throws MalformedURLException, URISyntaxException {
    	String url = "http://x:y@www.lifehacker.com/five-best-cloud-storage-providers-614393607?utm_source=dlvr.it&utm_medium=twitter#test";
    	long startTime = System.nanoTime();
		System.out.println(NormalizeURL.normalize1(url));
		long endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000000.0);
		
		StringUtils.isEmpty("");
		
		startTime = System.nanoTime();
		System.out.println(normalize(url));
		endTime = System.nanoTime();
		System.out.println((endTime - startTime)/1000000.0);
	}
    
    /**
	 * @param url
	 * @return
	 */
	public static String normalize(String url) {
		if (StringUtils.isEmpty(url)) 
			return url;
		
		URL uRL;
		try {
			uRL = new URL(url);
		} catch (MalformedURLException e) {
			return url;
		}

		StringBuilder normalizedURL = new StringBuilder();
		String modifiedQueryString = "";
		
		if (StringUtils.isNotEmpty(uRL.getQuery())) {
			modifiedQueryString = uRL.getQuery().replaceAll("&?utm_[^&]+", "").replaceAll("^&", "");
		}
		normalizedURL
			.append(uRL.getProtocol())
			.append("://")
			.append(uRL.getAuthority())
			.append(uRL.getPath())
			.append(StringUtils.isEmpty(modifiedQueryString) ? modifiedQueryString : "?" + modifiedQueryString)
			.append(StringUtils.isEmpty(uRL.getRef()) ? "" : "#" + uRL.getRef());
		return normalizedURL.toString().replaceAll("/$", "");
	}
	
    public static String cleanUrl(String link) {
        String ret = link.replaceAll("utm_([^&])*", "");
        ret = ret.replaceAll("\\/\\?(&)*$", "");
        ret = ret.replaceAll("(&)*$", "");
        return ret;
    }
}