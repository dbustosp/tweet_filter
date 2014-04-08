package tweet_filter;

public class MongoDBConnection {
	
	//Variables
	private final String HOST_DATABASE = "localhost";
    private final int PORT_DATABASE = 27017;
    private final String DB_NAME = "data";
    private final String COLLECTION_NAME = "tweet"; 
    
    private static MongoDBConnection instance = new MongoDBConnection();
    
    private MongoDBConnection(){
    	
    }
    
    public static MongoDBConnection getInstance(){
		return instance;
	}
    
    public String getHostDatabase(){
    	return HOST_DATABASE;
    }
    
    public int getPortDatabase(){
    	return PORT_DATABASE;
    }
    
    public String getDBName(){
    	return DB_NAME;
    }
    
    public String getCollectionName(){
    	return COLLECTION_NAME;
    }     
}
