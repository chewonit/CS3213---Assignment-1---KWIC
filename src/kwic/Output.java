package kwic;

import java.util.Vector;

/**
 * 
 * 
 * @author Chew Tee Ming (A0097964H)
 *
 */

public class Output {
	private Storage storage;
	public Output(){
		storage = Storage.getInstance();
	}
	
	public Vector<String> output(){
		Vector<String> formattedOutput = new Vector<String>();
		for(int i = 0; i < storage.size(); i++){
			Line retrievedLine = storage.getLine(i);
			if(retrievedLine.size() > 0){
				String retrievedString = retrievedLine.toString();
				formattedOutput.add(retrievedString);
			}
		}
		return formattedOutput;
	}
}