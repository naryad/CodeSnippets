package yn.twitter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;
import twitter4j.auth.AccessToken;

/**
 * Lists followers' ids
 *
 * @author Narendra Yadala narendra@crowdspots.com
 */
public final class TwitterExperiments {
    /**
     * Usage: java com.verticalengine.service.GetFollowersIDs [screen name]
     *
     * @param args message
     * @throws IOException 
     */
    public static void main(String[] args) throws IOException {
        try {
        	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            Twitter twitter = new TwitterFactory().getInstance();
            //_finished handle
    		twitter.setOAuthConsumer("consumer key","consumer secret");
    		AccessToken accessToken = new AccessToken("access key", "access secret");
    		twitter.setOAuthAccessToken(accessToken);
    		User user = twitter.showUser("_finished");
    		System.out.println(user);
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get followers' ids: " + te.getMessage());
            System.exit(-1);
        }
    }
}