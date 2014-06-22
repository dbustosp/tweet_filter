package tweet_filter;

public class MongoDBConnection {
	
	//Variables
	private String HOST_DATABASE;
    private int PORT_DATABASE;
    private String DB_NAME;
    private String COLLECTION_NAME;
    
    public String getHOST_DATABASE() {
		return HOST_DATABASE;
	}

	public void setHOST_DATABASE(String hOST_DATABASE) {
		HOST_DATABASE = hOST_DATABASE;
	}

	public int getPORT_DATABASE() {
		return PORT_DATABASE;
	}

	public void setPORT_DATABASE(int pORT_DATABASE) {
		PORT_DATABASE = pORT_DATABASE;
	}

	public String getDB_NAME() {
		return DB_NAME;
	}

	public void setDB_NAME(String dB_NAME) {
		DB_NAME = dB_NAME;
	}

	public String getCOLLECTION_NAME() {
		return COLLECTION_NAME;
	}

	public void setCOLLECTION_NAME(String cOLLECTION_NAME) {
		COLLECTION_NAME = cOLLECTION_NAME;
	}

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
