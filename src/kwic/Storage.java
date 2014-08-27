package kwic;

import java.util.Comparator;
import java.util.Vector;

/**
 * Storage Class to hold all Line.
 * 
 * @author Darry Chew (A0097797Y)
 *
 */
public class Storage {
	
	/** Singleton Instance */
	private static Storage instance = null;
	private Vector<Line> data;

	/**
	 * Private Constructor as this is a singleton class.
	 */
	private Storage() { 
		data = new Vector<Line>();
	}

	/**
	 * Get the singleton instance of the Storage Class.
	 * 
	 * @return instance of the Storage Class.
	 */
	public static Storage getInstance() {
		if (instance == null) {
			instance = new Storage();
		}
		return instance;
	}
	
	/**
	 * Add a new Line to the Storage.
	 * 
	 * @param line
	 */
	public void addLine(Line line) {
		if (line == null) {
			throw new IllegalArgumentException();
		}
		data.add(line);
	}
	
	/**
	 * Get Line by index from the Storage.
	 * 
	 * @param index
	 * @return
	 */
	public Line getLine(int index) {
		if (index < 0 || index >= data.size()) {
			throw new IndexOutOfBoundsException();
		}
		return data.elementAt(index);
	}
	
	/**
	 * Remove <b>all</b> Lines that have the specified ID.
	 * 
	 * @param id to identify Lines to be removed.
	 */
	public void removeLineById(int id) {
		for (int i = data.size() - 1; i >= 0; i--) {
			if (data.elementAt(i).getId() == id) {
				data.remove(i);
			}
		}
	}

	/**
	 * Remove a single Line by index.
	 * 
	 * @param index to identify Line to be removed.
	 * @return
	 */
	public Line removeLineByIndex(int index) {
		if ( index < 0 || index >= data.size() ) {
			throw new IndexOutOfBoundsException();
		}
		return data.remove(index);
	}
	
	/**
	 * Get the size of the Storage.
	 * 
	 * @return size of the Storage.
	 */
	public int size() {
		return data.size();
	}
	
	/**
	 * Sorts the Lines in the Storage.
	 * 
	 * @param comparator comparator to be used for sorting.
	 */
	public void sort(Comparator<Line> comparator) {
		if ( comparator == null ) {
			throw new IllegalArgumentException();
		}
		data.sort(comparator);
	}
	
	/**
	 * Clears everything in the Storage.
	 */
	public void clear() {
		data.clear();
	}
}
