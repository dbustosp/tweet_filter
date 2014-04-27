package tweet_filter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.UnknownHostException;
import java.nio.charset.Charset;

import twitter4j.JSONException;
import twitter4j.JSONObject;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.util.JSON;


public class JsonReader {
	
	String filePath;
	Filter f; 
	int count; 
	
	public JsonReader(String _filePath, Filter _f) {
		filePath = _filePath;
		f = _f;
		count = 0;
	}
	
	public void readFilterAndOutput(String host_database, int port_database, String db_name, String collection_name) {
		InputStream fis;
		BufferedReader br;
		String line;
		MongoClient mongo;
		DB db;
		DBCollection coll = null;
		
		try {
			mongo = new MongoClient(host_database, port_database);
			db = mongo.getDB(db_name);
			coll = db.getCollection(collection_name);
			boolean auth = db.authenticate("tecweb_user", "tecweb12345".toCharArray());
			if (auth) {		 
				System.out.println("Login is successful!");
			} else {
				System.out.println("Login is failed!");
			}			
		} catch (UnknownHostException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		
		try {
			fis = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			
			// Start reading
			while ((line = br.readLine()) != null) {
				JSONObject jObject  = new JSONObject(line);
				String text_tweet = jObject.getString("text_tweet");
				if(f.filter(text_tweet)) {
					// Tweets que pasan el filtro				
					DBObject dbObject = (DBObject) JSON.parse(jObject.toString());
					System.out.println("Insertando: " + dbObject.toString());
					coll.insert(dbObject);
				}
			}
	
			// Done with the file
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			e.printStackTrace();
		} catch(IOException e1){
			System.out.println("IOException");
			e1.printStackTrace();
		} catch (JSONException e) {
			System.out.println("JSONException");
			e.printStackTrace();
		}
		
		br = null;
		fis = null;
	}
	
	

}
