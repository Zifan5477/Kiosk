package src.film;

import static org.junit.Assert.*;

import org.junit.Test;

public class ScheduleTest {

	Schedule sch = new Schedule();
	@Test
	public void testCompareTime() {
		System.out.println(sch.compareTime("13:00"));
	}

}
