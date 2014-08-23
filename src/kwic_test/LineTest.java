package kwic_test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.Vector;

import kwic.Line;

import org.junit.Test;

/**
 * Unit Test for the Line Class.
 * 
 * @author A0097797Y
 *
 */
public class LineTest {

	@SuppressWarnings("unused")
	@Test(expected = AssertionError.class)
	public void testEmptyLineCreation() {
		Vector<String> words = new Vector<String>();

		Line line = new Line(1, words);
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testLineCreation() {
		Vector<String> words = new Vector<String>();
		words.add("Hello");
		words.add("World");

		Line line = new Line(1, words);

		Field field;
		try {
			field = Line.class.getDeclaredField("words");
			field.setAccessible(true);

			Vector<String> value = (Vector<String>) field.get(line);

			assertEquals("Hello", value.elementAt(0));
			assertEquals("World", value.elementAt(1));

		} catch (Exception e) {
			fail("Could not access private variable.");
		}
	}

}
