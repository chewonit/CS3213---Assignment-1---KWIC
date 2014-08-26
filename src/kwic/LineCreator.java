package kwic;

import java.util.Vector;

/**
 * The LineCreator Class is responsible for the creation
 * of new and shifted lines.
 * It will maintain the line IDs in synchronization,
 * as Shifted lines will bear the same line ID as its
 * original line.
 * 
 * @author A0097797Y
 *
 */
public class LineCreator {

	private static int lineId = 0;
	
	/**
	 * Creates a new line with a brand new Line ID.
	 * 
	 * @param words in the line.
	 * @return the Line object.
	 */
	public static Line createLine(Vector<String> words) {
		Line line = new Line( getLineIdAndIncrement(), words );
		return line;
	}
	
	/**
	 * Creates a shifted line that bares the same Line ID
	 * with the most recent added Line.
	 * 
	 * @param words in the Line.
	 * @return the Line object.
	 */
	public static Line createedShiftedLine(Vector<String> words) {
		Line line = new Line( getMostRecentLineId(), words );
		return line;
	}
	
	/**
	 * Gets the last used Line ID.
	 * 
	 * @return the last used Line ID.
	 */
	public static int getMostRecentLineId() {
		return lineId-1;
	}
	
	/**
	 * Returns the current value of line ID but increments
	 * the Line ID stored value.
	 * 
	 * @return
	 */
	private static int getLineIdAndIncrement() {
		lineId = lineId + 1;
		return lineId-1;
	}
}
