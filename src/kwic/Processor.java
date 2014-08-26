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
		this.processWord(words);
	}
	
	public void process(Vector<String> inputStrings){
		for(int i = 0; i < inputStrings.size(); i++){
			this.process(inputStrings.get(i));
		}
	}
	
	private void processWord(Vector<String> words){
		boolean isFirstWord = true;
		Vector<String> shiftedWords = words;
		for(int i=0; i<words.size(); i++){
			shiftedWords = this.shiftWord(shiftedWords);
			boolean isFilteredWord = this.isWordFiltered(shiftedWords.firstElement());
			if(!isFilteredWord){
				Line newLine = null;
				if(isFirstWord){
					newLine = this.createLine(shiftedWords);
				} else {
					newLine = this.createShiftedLine(shiftedWords);
				}
				
				if(newLine != null){
					this.addToStorage(newLine);
				}
			}
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
		Line newLine = LineCreator.createLine(inputVectorStrings);
		return newLine;
	}
	
	private Line createShiftedLine(Vector<String> inputVectorStrings){
		Line newLine = LineCreator.createedShiftedLine(inputVectorStrings);
		return newLine;
	}
	
	private boolean isWordFiltered(String inputWord){
		return filter.isWordFiltered(inputWord);
	}
	
	private Vector<String> shiftWord(Vector<String> inputVectorStrings){
		return Shifter.shiftWord(inputVectorStrings);
	}
}
