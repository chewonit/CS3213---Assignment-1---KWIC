package kwic;

import java.util.Arrays;
import java.util.Vector;

/**
 * 
 * 
 * @author Chew Tee Ming (A0097964H)
 *
 */

public class Processor {
	
	private Storage storage;
	private Filter filter;
	
	public Processor(){
		storage = Storage.getInstance();
		filter = Filter.getInstance();
	}
	
	public void process(String inputString){
		Vector<String> words = this.breakString(inputString);
		Vector<String> shiftedWords = this.shiftWord(words);
		
		boolean isFilteredWord = this.isWordFiltered(shiftedWords.firstElement());
		
		if(!isFilteredWord){
			Line newLine = this.createLine(shiftedWords);
			this.addToStorage(newLine);
		}
	}
	
	public void process(Vector<String> inputStrings){
		for(int i = 0; i < inputStrings.size(); i++){
			this.process(inputStrings.get(i));
		}
	}
	
	private void addToStorage(Line inputLine){
		storage.addLine(inputLine);
	}
	
	private Vector<String> breakString(String inputString){
		Vector<String> convertedVector = new Vector<String>(Arrays.asList(inputString.split(" ")));
		return convertedVector;
	}
	
	private Line createLine(Vector<String> inputVectorStrings){
		Line newLine = new Line(storage.size(), inputVectorStrings);
		return newLine;
	}
	
	private boolean isWordFiltered(String inputWord){
		return filter.isWordFiltered(inputWord);
	}
	
	private Vector<String> shiftWord(Vector<String> inputVectorStrings){
		return Shifter.shiftWord(inputVectorStrings);
	}
}
