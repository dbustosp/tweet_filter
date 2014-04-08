package tweet_filter;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class FileReader {
	
	private String filePath;
	ArrayList<String> keywords;
	
	public FileReader(String _filePath) {
		filePath = _filePath;
		keywords = new ArrayList<String>();
	}
	
	public Boolean init() {
		
		InputStream fis;
		BufferedReader br;
		String line;
		
		try {
			fis = new FileInputStream(filePath);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			

			while ((line = br.readLine()) != null) {
			    System.out.println(line);
			    keywords.add(line);
			}
	
			// Done with the file
			br.close();
		} catch (FileNotFoundException e) {
			System.out.println("File not Found");
			e.printStackTrace();
			return false;
		} catch(IOException e1){
			System.out.println("IOException");
			e1.printStackTrace();
			return false;
		}
		
		br = null;
		fis = null;
		return true;
	}
	
	public ArrayList<String> getKeywords() {
		return keywords;
	}
	
	
	

}
