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
		inputWord = inputWord.toLowerCase();
		ignoredWords.put(inputWord, inputWord);
	}
	
	/**
	 * Remove a word from ignored list
	 * @param inputWord
	 */
	public void removeFilter(String inputWord){
		inputWord = inputWord.toLowerCase();
		ignoredWords.remove(inputWord);
	}
	
	/**
	 * Clear ignored list
	 * @param inputWord
	 */
	public void clearFilters(){
		ignoredWords.clear();
	}
	
	/**
	 * Get all filters
	 * 		
	 */
	public Vector<String> getAllFilters(){
		return new Vector<String>(ignoredWords.values());
	}
	
	/**
	 * Check if a word is in the ignored list
	 * @param inputWord
	 * @return result of whether the word is in ignored list
	 */
	public boolean isWordFiltered(String inputWord){
		inputWord = inputWord.toLowerCase();
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