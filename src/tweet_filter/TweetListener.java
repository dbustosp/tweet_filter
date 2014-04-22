package tweet_filter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.mongodb.DBObject;

public class TweetListener implements StatusListener {
	
	// Database object
	MongoDB mongo;

	
	
	public TweetListener(MongoDB _mongo){
		mongo = _mongo;
	}
	
	public void onStatus(Status status) {
        //System.out.println(status.getUser().getName() + " : " + status.getText());
        
        // Parsing and saving tweets filtered
        DBObject tweet = mongo.parsingTweet(null, status);        
        // Saving tweet
        mongo.saveTweet(tweet);
    }
	
	public void onDeletionNotice(StatusDeletionNotice statusDeletionNotice) {
		
	}
    
	public void onTrackLimitationNotice(int numberOfLimitedStatuses) {
		System.out.println("onTrackLimitationNotice -- raised - numberOfLimitedStatuses: " + numberOfLimitedStatuses);		
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
