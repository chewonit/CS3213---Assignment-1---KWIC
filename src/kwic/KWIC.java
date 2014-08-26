package kwic;

import java.awt.image.FilteredImageSource;
import java.util.Vector;

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
	
	public KWIC() {
		processor = new Processor();
		output = new Output();
		filter = Filter.getInstance();
	}
	
	public Vector<Line> process(String input) {
		processor.process(input);
		return output.output();
	}
	
	public Vector<Line> processFile(String input) {
		return new Vector<Line>();
	}
	
	public Vector<String> addFilter(String filter) {
		this.filter.addFilter(filter);
		return new Vector<String>();
	}

	public static void main(String[] args) {
		
		KWIC kwic = new KWIC();
		KWIC_GUI gui = new KWIC_GUI();
		
		gui.run(kwic);
	}

}
