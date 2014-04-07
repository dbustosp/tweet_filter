package tweet_filter;

import twitter4j.Status;
import twitter4j.StatusDeletionNotice;

public class TweetListener {
	
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

}
