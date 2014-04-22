package tweet_filter;

import java.util.ArrayList;

public class Filter {
	
	// Filter keywords
	ArrayList<String> keywords_filter;
	
	public Filter(ArrayList<String> _keywords_filter) {
		keywords_filter = _keywords_filter;
	}
	
	// Filter the tweet with the keywords_filter
	// Return true if none of the keywords is in the tweet
	public Boolean filter(String text_tweet) {
		// Iterating through the keywords and check if one of the keywords_filter is in the tweet
		for(String s : keywords_filter) {
			if(text_tweet.indexOf(s) > 0) {
				return false;				
			}
		}
		return true;
	}
}
