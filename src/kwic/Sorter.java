package kwic;

import java.util.Comparator;

/**
 * This class introduces a comparator to aid with the sorting.
 * 
 * @author Darry Chew (A0097797Y)
 *
 */
public class Sorter {
	
	private Storage storage;
	private Comparator<Line> comparator;
	
	public Sorter() {
		storage = Storage.getInstance();
		comparator = new Comparator<Line>() {
	        @Override
	        public int compare(Line line1, Line line2) {
	            return line1.toString().compareTo(line2.toString());
	        }
	    };
	}
	
	public void sort() {
		storage.sort(comparator);
	}
}
