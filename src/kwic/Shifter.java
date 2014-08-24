package kwic;

import java.util.Vector;

/**
 * 
 * 
 * @author Chew Tee Ming (A0097964H)
 *
 */

public class Shifter {
	public static Vector<String> shiftWord(Vector<String> inputVectorStrings){
		Vector<String> shiftedVector = new Vector<String>();
		for(int i = 1; i < inputVectorStrings.size(); i++){
			shiftedVector.add(inputVectorStrings.get(i));
		}
		shiftedVector.add(inputVectorStrings.firstElement());
		
		return shiftedVector;
	}
}