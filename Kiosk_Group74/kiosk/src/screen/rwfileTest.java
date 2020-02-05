package src.screen;

import static org.junit.Assert.*;

import java.io.IOException;

import org.junit.Test;

public class rwfileTest {

	@Test
	public void testReadFl() {
		try {
			new rwfile().readFl("kiosk/data/seats/Screen1.txt");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
