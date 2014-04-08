package tweet_filter;

import java.net.UnknownHostException;

import twitter4j.Status;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;



public class MongoDB {
	
	private MongoClient mongo;
	private DB db;
	private DBCollection coll;
	private int status;
	
	
	public void MongoDB() {
		status = 0;		
	}
	
	public Boolean initMongoDB() {
		try{
			mongo = new MongoClient(MongoDBConnection.getInstance().getHostDatabase(), MongoDBConnection.getInstance().getPortDatabase());
			db = this.mongo.getDB(MongoDBConnection.getInstance().getDBName());
			coll = db.getCollection(MongoDBConnection.getInstance().getCollectionName());
			status = 1;
		}catch(UnknownHostException e){
			e.printStackTrace();
			status = -1;
			return false;
		}
		return true;
	}
	
	public DBObject parsingTweet(Status status){
        
		// Parsing the tweet
        DBObject tweet = new BasicDBObject();
        tweet.put("id_tweet", status.getId());
        tweet.put("id_user", status.getUser().getId());
        tweet.put("text_tweet", status.getText());
		tweet.put("date_tweet", status.getCreatedAt());
		
		// Getting user location
		if (status.getUser().getLocation() != null) {
			tweet.put("user_location", status.getUser().getLocation());
		}
		
		// Getting the user's location
		if (status.getGeoLocation() != null) {
			Double[] geo = {status.getGeoLocation().getLatitude(),	status.getGeoLocation().getLongitude()};
			tweet.put("loc", geo);
		}
		
		// Returning the tweet in the database
		return tweet;
	}
	
	
	public void saveTweet(DBObject tweet) {
		coll.insert(tweet);				
	}
	
	public void disconnect(){
		mongo.close();
	}
	

}
