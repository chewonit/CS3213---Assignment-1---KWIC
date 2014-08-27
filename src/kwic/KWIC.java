package kwic;

import java.util.Vector;

import javafx.util.Pair;
import kwic_gui.KWIC_GUI;

/**
 * KWIC facade and main class.
 * 
 * @author Darry Chew (A0097797Y)
 *
 */
public class KWIC {
	
	private Processor processor;
	private Output output;
	private Filter filter;
	private Storage storage;
	private Sorter sorter;
	
	public KWIC() {
		processor = new Processor();
		output = new Output();
		sorter = new Sorter();
		filter = Filter.getInstance();
		storage = Storage.getInstance();
	}
	
	public Vector<Line> process(String input) {
		processor.process(input);
		sorter.sort();
		return output.output();
	}
	
	public Vector<Line> processFile(String input) {
		return new Vector<Line>();
	}
	
	public Vector<Line> removeLine(int lineId) {
		storage.removeLineById(lineId);
		sorter.sort();
		return output.output();
	}
	
	public Pair<Vector<String>, Vector<Line>> addFilter(String filter) {
		this.filter.addFilter(filter);
		processor.reprocess();
		sorter.sort();
		Pair<Vector<String>, Vector<Line>> filtersAndOutput = 
				new Pair<Vector<String>, Vector<Line>>(this.filter.getAllFilters(), output.output());
		return filtersAndOutput;
	}

	public static void main(String[] args) {
		
		KWIC kwic = new KWIC();
		KWIC_GUI gui = new KWIC_GUI();
		
		gui.run(kwic);
	}

}
