package kwic_test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Vector;

import kwic.Line;
import kwic.Storage;

import org.junit.BeforeClass;
import org.junit.Test;

public class StorageTest {

	private static Storage storage;

	@BeforeClass
	public static void setUp() {
		storage = Storage.getInstance();
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testAddLine() {
		storage.clear();

		Field field;
		Vector<Line> data = null;
		try {
			field = Storage.class.getDeclaredField("data");
			field.setAccessible(true);

			data = (Vector<Line>) field.get(storage);
			assertEquals(0, data.size());

		} catch (Exception e) {
			fail("Could not access private variable.");
		}

		Vector<String> words = new Vector<String>();

		words.add("Hello");
		words.add("World");
		storage.addLine(new Line(1, words));

		words.clear();
		words.add("Software");
		words.add("engineering");
		storage.addLine(new Line(2, words));

		words.clear();
		words.add("Creative");
		words.add("problems");
		storage.addLine(new Line(3, words));

		assertEquals(3, data.size());

		assertEquals("Hello World", data.elementAt(0).toString());
		assertEquals("Software engineering", data.elementAt(1).toString());
		assertEquals("Creative problems", data.elementAt(2).toString());

	}

	@Test
	public void testGetLine() {
		storage.clear();
		Vector<String> words = new Vector<String>();

		words.add("Hello");
		words.add("World");
		storage.addLine(new Line(1, words));

		words.clear();
		words.add("Software");
		words.add("engineering");
		storage.addLine(new Line(2, words));

		words.clear();
		words.add("Creative");
		words.add("problems");
		storage.addLine(new Line(3, words));

		Line line = storage.getLine(0);
		assertEquals("Hello World", line.toString());

		line = storage.getLine(1);
		assertEquals("Software engineering", line.toString());

		line = storage.getLine(2);
		assertEquals("Creative problems", line.toString());
	}

	@Test
	public void testRemoveLineById() {
		storage.clear();
		Vector<String> words = new Vector<String>();

		words.add("line1");
		storage.addLine(new Line(1, words));

		words.clear();
		words.add("line2");
		storage.addLine(new Line(2, words));

		words.clear();
		words.add("line1a");
		storage.addLine(new Line(1, words));

		words.clear();
		words.add("line3");
		storage.addLine(new Line(3, words));

		words.clear();
		words.add("line1c");
		storage.addLine(new Line(1, words));

		words.clear();
		words.add("line3a");
		storage.addLine(new Line(3, words));

		assertEquals(6, storage.size());

		Line line = storage.getLine(2);
		assertEquals("line1a", line.toString());

		line = storage.getLine(4);
		assertEquals("line1c", line.toString());

		storage.removeLineById(1);
		assertEquals(3, storage.size());

		line = storage.getLine(0);
		assertEquals("line2", line.toString());
		line = storage.getLine(2);
		assertEquals("line3a", line.toString());
	}

	@SuppressWarnings("unchecked")
	@Test
	public void testSize() {
		storage.clear();

		Field field = null;
		Vector<Line> data = null;
		try {
			field = Storage.class.getDeclaredField("data");
			field.setAccessible(true);

			data = (Vector<Line>) field.get(storage);

			assertEquals(0, data.size());
			assertEquals(0, storage.size());

		} catch (Exception e) {
			fail("Could not access private variable.");
		}

		Vector<String> words = new Vector<String>();

		words.add("line");
		storage.addLine(new Line(1, words));

		assertEquals(1, data.size());
		assertEquals(1, storage.size());

		for (int i = 0; i < 1000; i++) {
			words.add("line" + i);
			storage.addLine(new Line(i, words));
		}

		try {
			data = (Vector<Line>) field.get(storage);

		} catch (Exception e) {
			fail("Could not access private variable.");
		}

		assertEquals(1001, data.size());
		assertEquals(1001, storage.size());
	}

	@Test
	public void testSort() {
		storage.clear();

		String[] strings = { 
				"Hello World", 
				"Software engineering",
				"Creative Problems",
				"Lorem ipsum dolor sit amet consectetuer adipiscing elit",
				"Aenean commodo ligula eget dolor",
				"Curabitur ullamcorper ultricies nisi",
				"tellus eget condimentum rhoncus"
		};
		int[] idOrder = { 4, 2, 5, 0, 3, 1, 6 };

		for (int i = 0; i < strings.length; i++) {
			storage.addLine(new Line(i, new Vector<String>(Arrays
					.asList(strings[i].split(" ")))));
		}

		Comparator<Line> comparator = new Comparator<Line>() {

			@Override
			public int compare(Line o1, Line o2) {
				return o1.toString().compareTo(o2.toString());
			}
		};
		storage.sort(comparator);

		for (int i = 0; i < storage.size(); i++) {
			Line line = storage.getLine(i);
			assertEquals( idOrder[i], line.getId());
			System.out.println(line);
		}
	}
}
