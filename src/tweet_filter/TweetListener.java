package tweet_filter;

import java.net.UnknownHostException;

import twitter4j.StallWarning;
import twitter4j.Status;
import twitter4j.StatusDeletionNotice;
import twitter4j.StatusListener;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;

public class TweetListener implements StatusListener {
	
	// Database object
	MongoDB mongo;
	
	// Database in Mongolab
	MongoClient mongolab;
	DB db_mongolab;
	DBCollection coll_mongolab = null;

	
	
	public TweetListener(MongoDB _mongo, String host_database, int port_database, String db_name, String collection_name){
		mongo = _mongo;
		try {
			mongolab = new MongoClient(host_database, port_database);
			db_mongolab = mongolab.getDB(db_name);
			coll_mongolab = db_mongolab.getCollection(collection_name);
			boolean auth = db_mongolab.authenticate("tecweb_user", "tecweb12345".toCharArray());
			if (auth) {		 
				System.out.println("Login is successful!");
			} else {
				System.out.println("Login is failed!");
			}	
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void onStatus(Status status) {
        //System.out.println(status.getUser().getName() + " : " + status.getText());
        
        // Parsing and saving tweets filtered
        DBObject tweet = mongo.parsingTweet(null, status);        
        // Saving tweet
        coll_mongolab.insert(tweet);
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
