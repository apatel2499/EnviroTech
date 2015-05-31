package model;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

/**
 * The UserComparatorByNameTest is the JUnit test class for testing the 
 * UserComparatorByName class.
 * 
 * @author Ankit
 */
public class UserComparatorByNameTest {
	
	/** The test comparator. */
	private UserComparatorByName testComparator;
	
	/**
	 * Sets up the comparator.
	 */
	@Before
	public void setUp() {
		testComparator = new UserComparatorByName();
	}

	/**
	 * Tests the compare method.
	 */
	@Test
	public void testCompare() {
		// users with same last name
		User user1 = new ParkManager("A", "Williams", "awilliams@user.com");
		User user2 = new ParkManager("B", "Williams", "bwilliams@user.com");
		assertTrue(testComparator.compare(user1, user2) < 0);
		
		// users with same first name
		User user3 = new ParkManager("Peter", "A", "petera@user.com");
		User user4 = new ParkManager("Peter", "B", "peterb@user.com");
		assertTrue(testComparator.compare(user3, user4) < 0);
		
		// users with same first name and last name
		User user5 = new ParkManager("Peter", "Williams", "pwilliamsA@user.com");
		User user6 = new ParkManager("Peter", "Williams", "pwilliamsB@user.com");
		assertTrue(testComparator.compare(user5, user6) < 0);
	}

}
