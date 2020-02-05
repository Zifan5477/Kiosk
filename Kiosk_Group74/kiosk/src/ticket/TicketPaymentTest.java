package src.ticket;

import static org.junit.Assert.*;

import org.junit.Test;

public class TicketPaymentTest {

	TicketPayment testTP = new TicketPayment();
	
//	@Test
//	public void testTicketPayment() {
//		fail("Not yet implemented");
//	}

	@Test
	public void testShowTicketInfo() {
		testTP.showTicketInfo();
	}
	
	@Test
	public void testWriteFile() {
		testTP.writeFile("kiosk/data/tickets/test.txt", "test\ntesttest\ntest");
	}

}
