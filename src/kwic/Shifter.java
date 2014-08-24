package kwic;

import java.util.Vector;

/**
 * 
 * 
 * @author Chew Tee Ming (A0097964H)
 *
 */

public class Shifter {
	public static Line shiftWord(Line inputLine){
		Vector<String> shiftedVector = new Vector<String>();
		for(int i = 1; i < inputLine.size(); i++){
			shiftedVector.add(inputLine.getWord(i));
		}
		shiftedVector.add(inputLine.getFirstWord());
		
		return new Line(inputLine.getId(), shiftedVector);
	}
}