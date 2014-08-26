package kwic_test;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Vector;

import kwic.Line;
import kwic.LineCreator;

import org.junit.Test;

/**
 * @author Darry Chew (A0097797Y)
 *
 */
public class LineCreatorTest {

	@Test
	public void test() {
		
		String[] strings = { 
				"Hello World", 
				"Software engineering",
				"Creative Problems",
				"Lorem ipsum dolor sit amet consectetuer adipiscing elit",
				"Aenean commodo ligula eget dolor",
				"Curabitur ullamcorper ultricies nisi",
				"tellus eget condimentum rhoncus"
		};
		Line line;
		
		for (int i = 0; i < strings.length; i++) {
			line = LineCreator.createLine( new Vector<String>(Arrays.asList(strings[i].split(" "))) );
			assertEquals( line.getId(), i);
			
			int randomLoopTimes = (int) (Math.random() * 30);
			for (int j = 0; j < randomLoopTimes; j++) {
				line = LineCreator.createedShiftedLine( new Vector<String>(Arrays.asList(strings[i].split(" "))) );
				assertEquals( line.getId(), i);
			}
		}
		
	}

}
