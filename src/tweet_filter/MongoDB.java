package tweet_filter;

import java.net.UnknownHostException;

import twitter4j.Status;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;



public class MongoDB {
	
	// Variables
	private MongoClient mongo;
	private DB db;
	private DBCollection coll;
		
	// Constructor
	public MongoDB() {

	}
	
	// Init the MongoDB connection
	public Boolean initMongoDB() {
		try{
			mongo = new MongoClient(MongoDBConnection.getInstance().getHostDatabase(), MongoDBConnection.getInstance().getPortDatabase());
			db = this.mongo.getDB(MongoDBConnection.getInstance().getDBName());
			coll = db.getCollection(MongoDBConnection.getInstance().getCollectionName());
		}catch(UnknownHostException e){
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	// Parsing the tweet
	public DBObject parsingTweet(String keyword, Status status){
        
		// Parsing the tweet
        DBObject tweet = new BasicDBObject();
        tweet.put("keyword", keyword);
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
		
		// get tweet data
		tweet.put("num_retweet", status.getRetweetCount());
		tweet.put("num_fav", status.getFavoriteCount());	
		
		// get user data
		tweet.put("num_user_followers", status.getUser().getFollowersCount());
		tweet.put("num_user_friends", status.getUser().getFriendsCount());
		tweet.put("num_user_fav", status.getUser().getFavouritesCount());
		tweet.put("num_user_lists", status.getUser().getListedCount());
		tweet.put("user_name", status.getUser().getName());
				
		
		
		// profile data
		tweet.put("url_user", status.getUser().getURL());
		tweet.put("url_image_background", status.getUser().getProfileBackgroundImageURL());
		tweet.put("url_image_profile", status.getUser().getOriginalProfileImageURL());
		
		
		
		// Returning the tweet in the database
		return tweet;
	}
	
	// Saving the Tweet
	public void saveTweet(DBObject tweet) {
		coll.insert(tweet);				
	}
	
	// Disconenct the connection
	public void disconnect(){
		mongo.close();
	}
	

}
