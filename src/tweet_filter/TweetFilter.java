package tweet_filter;

import java.util.List;

import twitter4j.FilterQuery;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;
import twitter4j.StatusListener;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.TwitterStream;
import twitter4j.TwitterStreamFactory;
import twitter4j.auth.AccessToken;

public class TweetFilter {

	public static void main(String[] args) {
		
		TweetFilter tweetFilter = new TweetFilter();
		tweetFilter.startSearchTweets();
		
        	
	}
	
	public void startStream(){
		// Getting the instance associated with the configuration
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		
		// Setting the oAuth parameters
		twitterStream.setOAuthConsumer(TwitterConnection.getInstance().getConsumerKey(), TwitterConnection.getInstance().getConsumerSecret());
		AccessToken accessToken = new AccessToken(TwitterConnection.getInstance().getAccessToken() ,TwitterConnection.getInstance().getAccessTokenSecret());
		
		twitterStream.setOAuthAccessToken(accessToken);
		
		String keywords[] = {"Universidad de Santiago", "Usach", "UdeSantiago"};
		FilterQuery filter = new FilterQuery();
        filter.track(keywords);
        
        StatusListener listener = new TweetListener();
        twitterStream.addListener(listener);
        
        twitterStream.filter(filter);
	}
	
	public void startSearchTweets(){
		Twitter twitter = TwitterFactory.getSingleton();
		
		twitter.setOAuthConsumer(TwitterConnection.getInstance().getConsumerKey(), TwitterConnection.getInstance().getConsumerSecret());
		AccessToken accessToken = new AccessToken(TwitterConnection.getInstance().getAccessToken() ,TwitterConnection.getInstance().getAccessTokenSecret());
		twitter.setOAuthAccessToken(accessToken);
		
		Query query = new Query("Usach");
		query.count(100);
		query.since("2010-01-01");
		query.until("2010-04-01");
		
		QueryResult result;

		System.out.println("Hola");
		
		try {
			result = twitter.search(query);
			List<Status> tweets = result.getTweets();
            int num_tweet = 1;
			for (Status tweet : tweets) {
                System.out.println(num_tweet + "-   @" + tweet.getUser().getScreenName() + " - " + tweet.getText());
                num_tweet++;
			}
			
		} catch (TwitterException e) {
            e.printStackTrace();
            System.out.println("Failed to search tweets: " + e.getMessage());
            System.exit(-1);
		}
	    

		
	}

}
