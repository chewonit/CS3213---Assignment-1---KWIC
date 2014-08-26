package kwic;

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
	
	public KWIC() {
		processor = new Processor();
	}
	
	public Vector<Line> process(String input) {
		return new Vector<Line>();
	}
	
	public Vector<Line> processFile(String input) {
		return new Vector<Line>();
	}

	public static void main(String[] args) {
		
		KWIC kwic = new KWIC();
		KWIC_GUI gui = new KWIC_GUI();
		
		gui.run(kwic);
	}

}
