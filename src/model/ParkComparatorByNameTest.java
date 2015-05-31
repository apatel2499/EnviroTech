package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * The ParkComparatorByNameTest is the JUnit test class for testing the 
 * ParkComparatorByName class.
 * 
 * @author Ankit
 */
public class ParkComparatorByNameTest {
	
	/** The test comparator. */
	private ParkComparatorByName testComparator;
	
	/**
	 * Sets up the comparator.
	 */
	@Before
	public void setUp() {
		testComparator = new ParkComparatorByName();
	}

	/**
	 * Tests the compare method.
	 */
	@Test
	public void testCompare() {
		ParkManager parkManager = new ParkManager("Will", "Smith", "wsmith@pm.com");
		Park park1 = new Park(0, "Park A", parkManager);
		Park park2 = new Park(1, "Park B", parkManager);
		Park park3 = new Park(2, "Park C", parkManager);
		
		assertTrue(testComparator.compare(park1, park1) == 0);
		assertTrue(testComparator.compare(park1, park2) < 0);
		assertTrue(testComparator.compare(park1, park3) < 0);
		
		assertTrue(testComparator.compare(park2, park1) > 0);
		assertTrue(testComparator.compare(park2, park2) == 0);
		assertTrue(testComparator.compare(park2, park3) < 0);
		
		assertTrue(testComparator.compare(park3, park1) > 0);
		assertTrue(testComparator.compare(park3, park2) > 0);
		assertTrue(testComparator.compare(park3, park3) == 0);
	}

}
