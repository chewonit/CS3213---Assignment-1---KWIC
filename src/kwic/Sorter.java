package kwic;

import java.util.Comparator;

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
