package model;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.junit.Before;
import org.junit.Test;

/**
 * The JobComparatorByDateTest is the JUnit test class for testing 
 * the JobComparatorByDate class.
 * 
 * @author Ankit
 */
public class JobComparatorByDateTest {
	
	/** The test comparator. */
	private JobComparatorByDate testComparator;
	
	/**
	 * Sets up the comparator.
	 */
	@Before
	public void setUp() {
		testComparator = new JobComparatorByDate();
	}

	/**
	 * Tests the compare method.
	 */
	@Test
	public void testCompare() {
		ParkManager parkManager = new ParkManager("Will", "Smith", "wsmith@pm.com");
		Park park = new Park(0, "", parkManager);
		
		Calendar calendar = new GregorianCalendar();
		Date time1 = calendar.getTime();
		calendar.add(Calendar.DATE, -5);
		Date time0 = calendar.getTime();
		calendar.add(Calendar.DATE, 10);
		Date time2 = calendar.getTime();
		
		Job job1 = new Job(0, time0, time0, park);
		Job job2 = new Job(1, time1, time1, park);
		Job job3 = new Job(2, time2, time2, park);
		
		assertTrue(testComparator.compare(job1, job1) == 0);
		assertTrue(testComparator.compare(job1, job2) < 0);
		assertTrue(testComparator.compare(job1, job3) < 0);
		
		assertTrue(testComparator.compare(job2, job1) > 0);
		assertTrue(testComparator.compare(job2, job2) == 0);
		assertTrue(testComparator.compare(job2, job3) < 0);
		
		assertTrue(testComparator.compare(job3, job1) > 0);
		assertTrue(testComparator.compare(job3, job2) > 0);
		assertTrue(testComparator.compare(job3, job3) == 0);
	}

}
