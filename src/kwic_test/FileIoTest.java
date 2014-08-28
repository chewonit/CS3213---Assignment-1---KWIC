package kwic_test;

import java.io.IOException;
import java.util.Vector;

import kwic.FileIo;

import org.junit.Test;

public class FileIoTest {

	@Test
	public void testReadFile() {
		FileIo fileIo = new FileIo();
		Vector<String> fileLines = new Vector<String>();
		
		try {
			fileLines = fileIo.readFile("bin/testFile.txt");
		} catch (IOException e) {
			System.out.println("Error opening file");
		}
		
		for (String line : fileLines) {
			System.out.println(line);
		}
	}

}
