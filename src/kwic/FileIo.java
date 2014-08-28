package kwic;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Vector;

/**
 * This class is responsible for reading input from files.
 * 
 * @author Darry Chew (A0097797Y)
 *
 */
public class FileIo {

	public Vector<String> readFile(String path) throws IOException {

		Vector<String> fileLines = new Vector<String>();
		BufferedReader br = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader(path));

			while ((sCurrentLine = br.readLine()) != null) {
				fileLines.add(sCurrentLine);
			}

		} catch (IOException e) {
			throw new IOException();
		} finally {
			try {
				if (br != null) {
					br.close();
				}	
			} catch (IOException ex) {
				throw new IOException();
			}
		}
		return fileLines;
	}
}
