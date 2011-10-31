package yn.experiments;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

public class SpamSiteDetector {

    public static void main(String[] args) {

        String input = "Dear Arezzo,\n"
            + "Please check out my website at spam1.com "
            + "or http://www.spam1.com or http://spam1.com or " 
            + "spam1 dot com to win millions of dollars in prizes.\n"
            + "Thank you.";

        List<String> bannedSites = Arrays.asList("spam1", "spam2", "spam3");

        StringBuilder re = new StringBuilder();
        for (String bannedSite : bannedSites) {
            if (re.length() > 0)
                re.append("|");
            String quotedSite = Pattern.quote(bannedSite);
            System.out.println(quotedSite);
            re.append("https?://(www\\.)?" + quotedSite + "\\S*");
            re.append("|" + quotedSite + "\\s*(dot|\\.)?\\s*(com|net|org)");
            //re.append("|" ... your variation here);
        }

        System.out.println(input.replaceAll(re.toString(), "LINK REMOVED"));
    }
}