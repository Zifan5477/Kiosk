package src.screen;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class Screen1Test {
	
	Screen1 testScr1 = new Screen1();

//	@Test
//	public void testScreen1() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testInit() {
//		fail("Not yet implemented");
//	}
	
	@Test
	public void testGetSeatbyID() {
		System.out.println("#### testGetSeatbyID ####");
		ArrayList<Integer> numSeats = new ArrayList<Integer>();
		numSeats.add(100);
		numSeats.add(101);
		numSeats.add(200);
		numSeats.add(215);
		ArrayList<String> intSeats = testScr1.getSeatIDbyNo(numSeats);
//		assertEquals(100, intSeats[0]);
//		assertArrayEquals({100, 101}, intSeats);
//		for (int i=0; i<intSeats.size(); i++){
//			System.out.print(intSeats.get(i) + " ");
//		}
		System.out.println(intSeats);
		System.out.println("");
	}

	@Test
	public void testGetIDbySeat() {
		System.out.println("#### testGetIDbySeat ####");
		String strSeats[] = {"D8", "D7", "D4", "A1"};
		Integer[] intSeats = testScr1.getSeatNobyID(strSeats);
//		assertEquals(100, intSeats[0]);
//		assertArrayEquals({100, 101}, intSeats);
		for (int i=0; i<intSeats.length; i++){
			System.out.print(intSeats[i] + " ");
		}
	}
	
	

}
