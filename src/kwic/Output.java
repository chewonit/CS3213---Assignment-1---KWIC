package kwic;

import java.util.Vector;

/**
 * 
 * 
 * @author Chew Tee Ming (A0097964H)
 *
 */

public class Output {
	private Storage storage;

	public Output() {
		storage = Storage.getInstance();
	}

	public Vector<Line> output() {
		Vector<Line> lines = new Vector<Line>();
		for (int i = 0; i < storage.size(); i++) {
			Line retrievedLine = storage.getLine(i);
			if (!retrievedLine.isFiltered()) {
				lines.add(retrievedLine);
			}
		}
		return lines;
	}
}