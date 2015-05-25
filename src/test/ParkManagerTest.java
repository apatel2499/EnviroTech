package test;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

import model.Park;
import model.ParkManager;

import org.junit.Before;
import org.junit.Test;

/**
 * ParkManagerTest is a JUnit test class for testing the ParkManager class.
 * 
 * @author Ankit
 */
public class ParkManagerTest {
	
	/** The park manager instance used for testing. */
	private ParkManager testParkManager;
	
	/** The string to be set as the test park manager's first name. */
	private String testFirstName;
	
	/** The string to be set as the test park manager's last name. */
	private String testLastName;
	
	/** The string to be set as the test park manager's email address. */
	private String testEmailAddress;
	
	/** A list of sample parks that can be used for testing. */
	private List<Park> testSampleParks;

	/**
	 * Sets up the all the attributes of this test class.
	 */
	@Before
	public void setUp() {
		// set the first name, last name, and email address strings
		testFirstName = "John";
		testLastName = "Doe";
		testEmailAddress = "jdoe@myoffice.com";
		
		// set the test park manager
		testParkManager = new ParkManager(testFirstName, testLastName, testEmailAddress);
		
		// set and populate the list of sample parks
		testSampleParks = new ArrayList<Park>();
		for (int i = 0; i < 3; i++) {
			Park samplePark = new Park(i, "Park " + i, testParkManager);
			testSampleParks.add(samplePark);
		}
	}

	/**
	 * Tests the empty ParkManager constructor.
	 */
	@Test
	public void testAdministratorConstructor1() {
		// create a new park manager without setting any attributes
		testParkManager = new ParkManager();
		
		// after creation of the park manager using the empty constructor,
		// its attributes must still be null
		assertNull(testParkManager.getFirstName());
		assertNull(testParkManager.getLastName());
		assertNull(testParkManager.getEmailAddress());
	}

	/**
	 * Tests the ParkManager constructor with the parameters for the first name, last name, and email address.
	 */
	@Test
	public void testAdministratorConstructor2() {
		// create a new park manager and set the attributes
		testParkManager = new ParkManager(testFirstName, testLastName, testEmailAddress);

		// after creation of the park manager using the non-empty constructor,
		// its attributes must now be equal to the ones used
		assertEquals(testFirstName, testParkManager.getFirstName());
		assertEquals(testLastName, testParkManager.getLastName());
		assertEquals(testEmailAddress, testParkManager.getEmailAddress());
	}

	/**
	 * Test the addPark method.
	 * Since created parks are automatically added to the park manager,
	 * then the only thing we can test is to add again parks that already exist
	 * in the park manager. 
	 */
	@Test
	public void testAddPark() {
		// at this point, the sample parks must have been automatically be added 
		// to the park manager via the Park constructor in the JUnit set up
		
		// try to add any of the parks again, must return false
		for (Park park : testSampleParks) {
			assertFalse(testParkManager.addPark(park));
		}
	}

	/**
	 * Test the getParks method.
	 */
	@Test
	public void testGetParks() {
		// at this point, the sample parks must have been automatically be added 
		// to the park manager via the Park constructor in the JUnit set up
		
		TreeSet<Park> parks = testParkManager.getParks();
		
		// the retrieved set of parks and the list of sample parks must have the same size
		assertEquals(testSampleParks.size(), parks.size());
		
		// all parks in the sample parks must be found in the retrieved set of parks
		for (Park park : testSampleParks) {
			assertTrue(parks.contains(park));
		}
	}

}