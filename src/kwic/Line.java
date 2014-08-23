package kwic;

import java.util.Vector;

/**
 * Line holds all the words in a particular line.
 * 
 * @author A0097797Y
 *
 */
public class Line {

	int id;

	/** Boolean bit to mark that line has been filtered out */
	boolean isFiltered = false;

	/** Vector to hold all words in the line */
	Vector<String> words;

	/**
	 * Create a Line object that will hold all the words in a particular line.
	 * 
	 * @param id
	 * @param words
	 */
	public Line(int id, Vector<String> words) {

		assert(id >= 0 && words.size() > 0);

		this.id = id;
		this.words = words;
	}

	/**
	 * Get the number of words in this Line.
	 * 
	 * @return the number of words in this Line.
	 */
	public int size() {
		return words.size();
	}

	/**
	 * Get a word by index in this Line.
	 * 
	 * @param index
	 * @return the word at the specified index.
	 * @throws IndexOutOfBoundsException
	 */
	public String getWord(int index) throws IndexOutOfBoundsException {
		if (index < 0 || index >= words.size()) {
			throw new IndexOutOfBoundsException();
		}
		return words.get(index);
	}

	/**
	 * Get the first word in this Line.
	 * 
	 * @return the first word in this Line.
	 */
	public String getFirstWord() {
		return words.firstElement();
	}

	/**
	 * Checks if this Line has been marked as filtered.
	 * 
	 * @return if this Line is marked as filtered.
	 */
	public boolean isFiltered() {
		return isFiltered;
	}

	/**
	 * Sets this Line to be filtered or not.
	 * 
	 * @param isFiltered
	 */
	public void setFiltered(boolean isFiltered) {
		this.isFiltered = isFiltered;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		String postFix = "";
		for (String word : words) {
			sb.append(postFix);
			postFix = " ";
			sb.append(word);
		}

		return sb.toString();
	}
}
