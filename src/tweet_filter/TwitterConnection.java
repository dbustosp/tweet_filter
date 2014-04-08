package tweet_filter;

public final class TwitterConnection {
	
	// Variables
	private String OAUTH_CONSUMER_KEY = "Ql65YGs9WYz0PUKDLmrYvY27F";
	private String OAUTH_CONSUMER_SECRET = "ER8Hs3nYjv9knBBN8KgMQxEbPLPzzrSgsIXnz9K4O3z9Cwj1JD";
	private String OAUTH_ACCESS_TOKEN = "267290560-YTdh4JpgapPdLwMLCRq5cfnv20PnzbSEhLx6bsbc";
	private String OAUTH_ACCESS_TOKEN_SECRET = "DWREl6GMXXnaV6a3V257u4TZ7qaEiBDP03imHmybhM06r";
	
	
	private static TwitterConnection instance = new TwitterConnection();
	
	private TwitterConnection(){
		
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
