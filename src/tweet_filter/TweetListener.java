package tweet_filter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;

public class TweetListener implements StatusListener {
	
	MongoDB mongo;
	
	public TweetListener(MongoDB _mongo){
		mongo = _mongo;
	}
	
	public void onStatus(Status status) {
        System.out.println(status.getUser().getName() + " : " + status.getText());
        // Parsing and saving tweets
        DBObject tweet = mongo.parsingTweet(status);
        System.out.println(tweet.toString());
        mongo.saveTweet(tweet);
    }
	
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		
		
	}
    
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		
		
	}
    
	public void onException(Exception ex) {
        ex.printStackTrace();
    }

	public void onScrubGeo(long arg0, long arg1) {
		// TODO Auto-generated method stub
		
	}

	public void onStallWarning(StallWarning arg0) {
		// TODO Auto-generated method stub
		
	}

}
