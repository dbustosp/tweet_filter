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
	private String filePathKeywords;
	ArrayList<String> keywords;
	ArrayList<String> keywords_filter;
	
	// Constructor
	public FileReader(String _filePath, String _filePathKeywords) {
		filePath = _filePath;
		filePathKeywords = _filePathKeywords;
		keywords = new ArrayList<String>();
		keywords_filter = new ArrayList<String>();
	}
	
	// Read the file with the keywords to filter the tweets once crawled
	public Boolean initReadKeywordsFilter() {
		
		InputStream fis;
		BufferedReader br;
		String line;
		System.out.println("initReadKeywordsCrawler: " + filePathKeywords);
		try {
			fis = new FileInputStream(filePathKeywords);
			br = new BufferedReader(new InputStreamReader(fis, Charset.forName("UTF-8")));
			
			// Start reading
			while ((line = br.readLine()) != null) {
			    System.out.println(line);
			    keywords_filter.add(line);
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
	
	// Read the file the keywords to filter the tweets from the API
	public Boolean initReadKeywordsCrawler() {
		
		InputStream fis;
		BufferedReader br;
		String line;
		System.out.println("initReadKeywordsCrawler: " + filePath);
		
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
		System.out.println("keyWoooooords:");
		System.out.println(keywords.toString());
		System.out.println(keywords.size());
		return keywords;
	}
	
	public ArrayList<String> getKeywordsFilter() {
		return keywords_filter;
	}
}
