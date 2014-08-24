package kwic;

import java.util.*;

/**
 * 
 * 
 * @author Chew Tee Ming (A0097964H)
 *
 */

public class Filter {
	
	/** Singleton Instance */
	private static Filter instance = null;
	private HashMap<String, String> ignoredWords;
	
	private Filter(){
		ignoredWords = new HashMap<String, String>();
	}
	
	/**
	 * Add a word to ignored list
	 * @param inputWord
	 */
	public void addFilter(String inputWord){
		ignoredWords.put(inputWord, inputWord);
	}
	
	/**
	 * Remove a word from ignored list
	 * @param inputWord
	 */
	public void removeFilter(String inputWord){
		ignoredWords.remove(inputWord);
	}
	
	/**
	 * Check if a word is in the ignored list
	 * @param inputWord
	 * @return result of whether the word is in ignored list
	 */
	public boolean isWordFiltered(String inputWord){
		if(inputWord != null){
			return ignoredWords.containsKey(inputWord);
		} else {
			return false;
		}
	}
	
	/**
	 * Get the singleton instance of the Filter Class.
	 * 
	 * @return instance of the Filter Class.
	 */
	public static Filter getInstance() {
		if (instance == null) {
			instance = new Filter();
		}
		return instance;
	}
}