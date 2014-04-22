package tweet_filter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import twitter4j.JSONException;
import twitter4j.JSONObject;

public class JsonReader {
	
	String filePath;
	Filter f; 
	int count; 
	
	public JsonReader(String _filePath, Filter _f) {
		filePath = _filePath;
		f = _f;
		count = 0;
	}
	
	public void readFilterAndOutput() {
		InputStream fis;
		BufferedReader br;
		String line;
		
		try {
			fis = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			
			// Start reading
			while ((line = br.readLine()) != null) {
				JSONObject jObject  = new JSONObject(line);
				String text_tweet = jObject.getString("text_tweet");
				if(!f.filter(text_tweet)) {
					System.out.println("NOOOO  " + count + "  " + text_tweet);
				}else{
					System.out.println("SIIII  " + count + "  " + text_tweet);
					count++;
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
