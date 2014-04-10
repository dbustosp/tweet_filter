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

import com.mongodb.DBObject;

public class TweetFilter {
    
	MongoDB mongo;
	long lowest_id;
	long highest_id;
	
    public TweetFilter(){
    	// Main object that will handle all the database operations 
    	// Instantiating the object mongo
    	mongo = new MongoDB();
    	
    	// Initializing the lowest id for tweets
    	lowest_id = (long) (Math.pow(2.0, 64.0) - 1);
    	
        // mongo.init for setting the connection to MongoDB
        if(!mongo.initMongoDB()){
        	System.out.println("It was impossible make the connection.");
        	System.exit(-1);
        }
    }
	
	public void startStream(String[] keywords, int size){
		
		// Getting the instance associated with the configuration
		TwitterStream twitterStream = new TwitterStreamFactory().getInstance();
		
		// Setting the oAuth parameters
		twitterStream.setOAuthConsumer(TwitterConnection.getInstance().getConsumerKey(), TwitterConnection.getInstance().getConsumerSecret());
		AccessToken accessToken = new AccessToken(TwitterConnection.getInstance().getAccessToken() ,TwitterConnection.getInstance().getAccessTokenSecret());
		
		twitterStream.setOAuthAccessToken(accessToken);
		
		//String keywords[] = {"Universidad de Santiago", "Usach", "UdeSantiago"};
		FilterQuery filter = new FilterQuery();
		
		// Passing the keywrods to the filter object
        filter.track(keywords);
        double[][] loc = {{-57.891497,-81.174317}, {-17.834536,-67.311036 }};
        filter.locations(loc);
        
        String[] lang = {"es"};
        
        filter.language(lang);
        
        
        // Attaching the listener to the twitterStream object
        StatusListener listener = new TweetListener(mongo);
        twitterStream.addListener(listener);
         
        // Starting to filter the tweets
        twitterStream.filter(filter);
	}
	
	// Method searching tweets considering the date
	public void startSearchTweetsDate(String[] keywords, int size){
		
		String[] anos = new String[] {"2014"};
		int numAnos = 1;
		String[] meses = new String[] {"04"};
		int numMeses = 1;
		String[] dias = new String[] {"01","02","03","04","05","06","07","08"};
		int numDias = 8;
		
		Twitter twitter = TwitterFactory.getSingleton();
				
		twitter.setOAuthConsumer(TwitterConnection.getInstance().getConsumerKey(), TwitterConnection.getInstance().getConsumerSecret());
		AccessToken accessToken = new AccessToken(TwitterConnection.getInstance().getAccessToken() ,TwitterConnection.getInstance().getAccessTokenSecret());
		twitter.setOAuthAccessToken(accessToken);
		
		int num_tweet = 1;
		// Iteration for keyword[]
		for(int keyword = 0; keyword < size; keyword++) {
			Query query = new Query(keywords[keyword]);
			query.setLang("es");
			query.count(100);
			
			// Iteration for years
			for(int i = 0; i < numAnos; i++) {
				// Iteration for moths
				for(int j = 0; j < numMeses; j++) {
					// Iteration for days
					for(int k = 0; k < (numDias - 1); k++) {
						//	System.out.println(anos[i] + "-" + meses[j] + "-" + dias[k] + "   Hasta    " + anos[i] + "-" + meses[j] + "-" + dias[k+1]);
						
						query.setSince(anos[i] + "-" + meses[j] + "-" + dias[k]);
						query.setUntil(anos[i] + "-" + meses[j] + "-" + dias[k+1]);
						
						QueryResult result;
						
						try {
							result = twitter.search(query);
							List<Status> tweets = result.getTweets();
				            
							for (Status tweet : tweets) {
				                System.out.println(num_tweet + "-   @" + tweet.getUser().getScreenName() + " - " + tweet.getText());
				                
				                // Parsing and saving tweets
				                DBObject object = mongo.parsingTweet(keywords[keyword], tweet);
				                mongo.saveTweet(object);	                
				                num_tweet++;
							}
							
						} catch (TwitterException e) {
				            e.printStackTrace();
				            System.out.println("Failed to search tweets: " + e.getMessage());
				            System.exit(-1);
						}	
					}
				} // end iteration for months
			} // end iteration for years		
		
			if(num_tweet >= 1500){
				try {
					Thread.sleep(900000);
				} catch (InterruptedException e) {
					System.out.println("No se pudo dormir la hebra.");
					e.printStackTrace();
				}
				num_tweet = 1;
			}
		
		} // end iteration for keyword[]
	}
	
	
	public static void main(String[] args) {
		
		// Instantiating the object that will contain the methods for extracting the tweets
		TweetFilter tweetFilter = new TweetFilter();
		
		// Defining a path for the file with all the keywords
		//String pathFile = "/Users/danilobustos/tweet_filter/keywords_universities.txt";
		String pathFile = args[0];
		System.out.println("PathFile: " + pathFile);
		
		// The object which will read the file and will return the keywords from the file
		FileReader fileReader = new FileReader(pathFile);
		
		// If is false is because the file was not read successfully
		if(!fileReader.init()){
			System.out.println("It was impossible read the file.");
		}
		
		// Casting from ArrayList<String> to String[]
		String []keywords = new String[fileReader.getKeywords().size()];
		fileReader.getKeywords().toArray(keywords);
		
		// Run two threads:
		// 1: Reading tweets with Stream API
		// 2: Reading tweets with Search API
		
		// Calling the method that will start the tweet's extraction
		tweetFilter.startStream(keywords, fileReader.getKeywords().size());
	}

}
