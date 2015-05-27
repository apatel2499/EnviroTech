package model;

import static org.junit.Assert.*;
import model.Administrator;

import org.junit.Before;
import org.junit.Test;

/**
 * AdministratorTest is a JUnit test class for testing the Administrator class.
 * 
 * @author Ankit
 */
public class AdministratorTest {
	
	/** The administrator instance used for testing. */
	private Administrator testAdministrator;
	
	/** The string to be set as the test administrator's first name. */
	private String testFirstName;
	
	/** The string to be set as the test administrator's last name. */
	private String testLastName;
	
	/** The string to be set as the test administrator's email address. */
	private String testEmailAddress;

	/**
	 * Sets up the all the attributes of this test class.
	 */
	@Before
	public void setUp() {
		// set the first name, last name, and email address strings
		testFirstName = "John";
		testLastName = "Doe";
		testEmailAddress = "jdoe@myoffice.com";
		
		// set the test administrator
		testAdministrator = new Administrator(testFirstName, testLastName, testEmailAddress);
	}

	/**
	 * Tests the empty Administrator constructor.
	 */
	@Test
	public void testAdministratorConstructor1() {
		// create a new administrator without setting any attributes
		testAdministrator = new Administrator();
		
		// after creation of the administrator using the empty constructor,
		// its attributes must still be null
		assertNull(testAdministrator.getFirstName());
		assertNull(testAdministrator.getLastName());
		assertNull(testAdministrator.getEmailAddress());
	}

	/**
	 * Tests the Administrator constructor with the parameters for the first name, last name, and email address.
	 */
	@Test
	public void testAdministratorConstructor2() {
		// create a new administrator and set the attributes
		testAdministrator = new Administrator(testFirstName, testLastName, testEmailAddress);

		// after creation of the administrator using the non-empty constructor,
		// its attributes must now be equal to the ones used
		assertEquals(testFirstName, testAdministrator.getFirstName());
		assertEquals(testLastName, testAdministrator.getLastName());
		assertEquals(testEmailAddress, testAdministrator.getEmailAddress());
	}

}