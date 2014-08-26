package kwic_test;

import static org.junit.Assert.*;

import kwic.Processor;
import kwic.Storage;

import org.junit.Test;

public class ProcessorTest {

	@Test
	public void testProcess() {
		Processor processor = new Processor();
		processor.process("Hello world everyone");
		Storage storage = Storage.getInstance();
		
		for(int i = 0; i<storage.size(); i++){
			if(i == 0){
				assertEquals("world", storage.getLine(i).getWord(0));
				assertEquals("everyone", storage.getLine(i).getWord(1));
				assertEquals("Hello", storage.getLine(i).getWord(2));
			} else if(i == 1){
				assertEquals("everyone", storage.getLine(i).getWord(0));
				assertEquals("Hello", storage.getLine(i).getWord(1));
				assertEquals("world", storage.getLine(i).getWord(2));
			} else if(i == 2){
				assertEquals("Hello", storage.getLine(i).getWord(0));
				assertEquals("world", storage.getLine(i).getWord(1));
				assertEquals("everyone", storage.getLine(i).getWord(2));
			}
		}
	}

}
