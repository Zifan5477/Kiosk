package src.ticket;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class TicketTest {

	Ticket testTic = new Ticket();

	// @Test
	// public void testTicket() {
	// fail("Not yet implemented");
	// }

	@Test
	public void testCreateID() {
		System.out.println("\n#### testCreateID ####");
		int testID = testTic.createID();
		System.out.println(testID);
	}

	@Test
	public void testReadUsedIDs() {
		System.out.println("\n#### testReadUsedIDs ####");
		ArrayList<Integer> testListID = testTic.readUsedIDs("kiosk/data/tickets/TicketIDPool.txt");
		System.out.println(testListID);
	}

	@Test
	public void testIssueIDs() {
		System.out.println("\n#### testIssueIDs ####");
		int[] iss = testTic.issueIDs();
		for (int i = 0; i < iss.length; i++) {
			System.out.println(iss[i]);
		}
	}

	@Test
	public void testTicketToString() {
		System.out.println("\n#### testTicketToString ####");
		String testStr = testTic.ticketToString();
		System.out.println(testStr);
	}

	@Test
	public void testSeatsToString() {
		System.out.println("\n#### testSeatsToString ####");
		String testStr = testTic.seatsToString();
		System.out.println(testStr);
	}

	@Test
	public void testSetupTypes() {
		System.out.println("\n#### testSetupTypes ####");
		ArrayList<String> testType = testTic.setupTypes();
		System.out.println(testType);
	}
	
	@Test
	public void testRecordPurchase() {
		System.out.println("\n#### testRecordPurchase ####");
		String testStr = testTic.recordPurchase();
		System.out.println(testStr);
	}
	
	public void testRecordTicketID() {
		System.out.println("\n#### testRecordTicketID ####");
		String testStr = testTic.recordTicketID();
		System.out.println(testStr);
	}

}
