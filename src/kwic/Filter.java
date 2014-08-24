package kwic;

import java.util.*;

/**
 * 
 * 
 * @author Chew Tee Ming (A0097964H)
 *
 */

public class Filter {
	
	private HashMap<String, String> ignoredWords;
	
	public Filter(){
		ignoredWords = new HashMap<String, String>();
	}
	
	public void addFilter(String inputWord){
		ignoredWords.put(inputWord, inputWord);
	}
	
	public void removeFilter(String inputWord){
		ignoredWords.remove(inputWord);
	}
	
	public boolean isWordFiltered(String inputWord){
		if(inputWord != null){
			return ignoredWords.containsKey(inputWord);
		} else {
			return false;
		}
	}
}