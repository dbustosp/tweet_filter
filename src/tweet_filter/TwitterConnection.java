package tweet_filter;

public final class TwitterConnection {
	
	// Variables
	private String OAUTH_CONSUMER_KEY = "";
	private String OAUTH_CONSUMER_SECRET = "";
	private String OAUTH_ACCESS_TOKEN = "";
	private String OAUTH_ACCESS_TOKEN_SECRET = "";
	
	
	private static TwitterConnection instance = new TwitterConnection();
	
	private TwitterConnection(){s
		
	}
	
	public static TwitterConnection getInstance(){
		return instance;
	}
	
	public String getConsumerKey(){
		return this.OAUTH_CONSUMER_KEY;
	}
	
	public String getConsumerSecret(){
		return this.OAUTH_CONSUMER_SECRET;
	}
	
	public String getAccessToken(){
		return this.OAUTH_ACCESS_TOKEN;
	}
	
	public String getAccessTokenSecret(){
		return this.OAUTH_ACCESS_TOKEN_SECRET;
	}
}
