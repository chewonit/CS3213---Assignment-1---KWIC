package kwic_test;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.util.*;
import kwic.Filter;
import org.junit.Test;

public class FilterTest {

	@SuppressWarnings("unchecked")
	@Test
	public void testAddFilter() {
		HashMap<String, String> ignoredWords = new HashMap<String, String>();
		ignoredWords.put("hello", "hello");
		ignoredWords.put("world", "world");
		
		Filter filter = Filter.getInstance();

		filter.addFilter("hello");
		filter.addFilter("world");
		
		Field field;
		try {
			field = Filter.class.getDeclaredField("ignoredWords");
			field.setAccessible(true);
			
			HashMap<String, String> value = (HashMap<String, String>) field.get(filter);

			assertTrue(ignoredWords.equals(value));
		} catch (Exception e) {
			fail("Could not access private variable.");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testRemoveFilter() {
		HashMap<String, String> ignoredWords = new HashMap<String, String>();
		ignoredWords.put("hello", "hello");
		ignoredWords.put("world", "world");
		ignoredWords.remove("hello");
		
		Filter filter = Filter.getInstance();
		filter.clearFilters();

		filter.addFilter("hello");
		filter.addFilter("world");
		filter.removeFilter("hello");
		
		Field field;
		try {
			field = Filter.class.getDeclaredField("ignoredWords");
			field.setAccessible(true);
			
			HashMap<String, String> value = (HashMap<String, String>) field.get(filter);

			assertEquals(ignoredWords, value);
		} catch (Exception e) {
			fail("Could not access private variable.");
		}
	}
	
	@SuppressWarnings("unchecked")
	@Test
	public void testClearFilter() {
		
		Filter filter = Filter.getInstance();
		filter.clearFilters();

		filter.addFilter("Hello");
		filter.addFilter("World");
		filter.clearFilters();
		
		Field field;
		try {
			field = Filter.class.getDeclaredField("ignoredWords");
			field.setAccessible(true);
			
			HashMap<String, String> value = (HashMap<String, String>) field.get(filter);

			assertTrue(value.isEmpty());
		} catch (Exception e) {
			fail("Could not access private variable.");
		}
	}
	
	@Test
	public void testIsWordFiltered() {
		
		Filter filter = Filter.getInstance();
		filter.clearFilters();

		filter.addFilter("Hello");
		filter.addFilter("World");
		
		assertTrue(filter.isWordFiltered("Hello"));
		assertFalse(filter.isWordFiltered("Hellossss"));
	}
}
