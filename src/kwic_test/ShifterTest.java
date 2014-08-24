package kwic_test;

import static org.junit.Assert.*;

import java.util.Vector;

import kwic.Shifter;

import org.junit.Test;

public class ShifterTest {

	@Test
	public void testShiftWord() {
		Vector<String> testVector = new Vector<String>();
		testVector.add("Hello");
		testVector.add("World");
		testVector.add("2");
		
		Vector<String> checkVector = new Vector<String>();
		checkVector.add("World");
		checkVector.add("2");
		checkVector.add("Hello");
		
		assertEquals(Shifter.shiftWord(testVector), checkVector);
	}

}
