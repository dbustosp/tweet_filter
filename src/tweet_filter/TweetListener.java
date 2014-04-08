package tweet_filter;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

public class TweetListener implements StatusListener {
	
	public TweetListener(){
	
	}
	
	public void onStatus(Status status) {
        System.out.println(status.getUser().getName() + " : " + status.getText());
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
