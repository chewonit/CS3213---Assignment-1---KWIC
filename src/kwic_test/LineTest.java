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

	@Test
	public void testSize() {
		Vector<String> words = new Vector<String>();
		words.add("Hello");

		Line line = new Line(1, words);
		assertEquals(1, line.size());

		words.add("World");
		line = new Line(1, words);
		assertEquals(2, line.size());

		for (int i = 0; i < 1000; i++)
			words.add("dummy");
		line = new Line(1, words);
		assertEquals(1002, line.size());
	}

	@Test(expected = IndexOutOfBoundsException.class)
	public void testGetWordIndexOutOfBounds() {
		Vector<String> words = new Vector<String>();
		words.add("Hello");

		Line line = new Line(1, words);
		line.getWord(1);
	}

	@Test
	public void testGetWord() {
		Vector<String> words = new Vector<String>();
		words.add("Hello");

		Line line = new Line(1, words);
		assertEquals("Hello", line.getWord(0));

		words.add("World");
		line = new Line(1, words);
		assertEquals("World", line.getWord(1));

		for (int i = 0; i < 1000; i++)
			words.add("dummy");
		line = new Line(1, words);
		assertEquals("dummy", line.getWord(954));
	}

	@Test
	public void testFirstWord() {
		Vector<String> words = new Vector<String>();
		words.add("Hello");

		Line line = new Line(1, words);
		assertEquals("Hello", line.getFirstWord());

		words.add("World");
		line = new Line(1, words);
		assertEquals("Hello", line.getFirstWord());

		for (int i = 0; i < 1000; i++)
			words.add("dummy");
		line = new Line(1, words);
		assertEquals("Hello", line.getFirstWord());
	}

	@Test
	public void testSetFiltered() {
		Vector<String> words = new Vector<String>();
		words.add("Hello");
		words.add("World");

		Line line = new Line(1, words);

		Field field;
		try {
			field = Line.class.getDeclaredField("isFiltered");
			field.setAccessible(true);

			boolean value = (boolean) field.get(line);
			assertFalse(value);

			line.setFiltered(true);
			value = (boolean) field.get(line);
			assertTrue(value);

		} catch (Exception e) {
			fail("Could not access private variable.");
		}
	}

	@Test
	public void testIsFiltered() {
		Vector<String> words = new Vector<String>();
		words.add("Hello");
		words.add("World");

		Line line = new Line(1, words);
		assertFalse(line.isFiltered());

		line.setFiltered(true);
		assertTrue(line.isFiltered());
	}
	
	@Test
	public void testToString() {
		Vector<String> words = new Vector<String>();
		words.add("Hello");

		Line line = new Line(1, words);
		assertEquals("Hello", line.toString());
		
		words.add("World");
		line = new Line(1, words);
		assertEquals("Hello World", line.toString());
	}
}
