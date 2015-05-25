package test;

import static org.junit.Assert.*;

import java.awt.peer.LightweightPeer;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;

import org.junit.Before;
import org.junit.Test;

/**@author leda rezanejad */

public class JobTest {

	/** The test job class. */
	private Job testJob;
	
	/** The test park manager class. */
	private ParkManager testParkManager;
	
	/** The test volunteer class. */
	private Volunteer testVolunteer;
	
	/** The test park class. */
	private Park testPark;
	
	/** The test park name. */
	private String testParkName; 
	
	/** The test job id. */
	private int testJobId;
	
	/** The test park id. */
	private int testParkId;

	private SimpleDateFormat format;
	
	/** The test start date. */
	private Date testJobStartDate;
	
	/** The test end date. */
	private Date testJobEndDate;
	
	/** The test first name for park. */
	private String testFirstName;
	
	/** The test last name for the park. */
	private String testLastName;
	
	/** The test email for the park. */
	private String testEmail;
	
	/** Set of test light volunteers. */
	private TreeSet<Volunteer> testLightVolunteersHasRoom;
	
	/** Set of test light volunteers. */
	private TreeSet<Volunteer> testLightVolunteersFull;
	
	/** Set of test medium volunteers. */
	private TreeSet<Volunteer> testMediumVolunteersHasRoom;
	
	/** Set of test medium volunteers. */
	private TreeSet<Volunteer> testMediumVolunteersFull;
	
	/** Set of test heavy volunteers. */
	private TreeSet<Volunteer> testHeavyVolunteersHasRoom;
	
	/** Set of test heavy volunteers. */
	private TreeSet<Volunteer> testHeavyVolunteersFull;
	
	/** The test maximum number light volunteers. */
	private int testJobMaxNumLightVolunteers;
	
	/** The test maximum number medium volunteers. */
	private int testJobMaxNumMediumVolunteers;
	
	/** The test maximum number heavy volunteers. */
	private int testJobMaxNumHeavyVolunteers;


	@Before
	public void setUp() throws Exception {
		
		testFirstName = "Leda";
		testLastName = "Rezanejad";
		testEmail = "ledarezanejad@parkmanager.com";
		
		testJobId = 1;
		testParkId = 1;
		
		testParkName = "Rainier";
		format = new SimpleDateFormat("mm/dd/yy");		
		testJobStartDate = format.parse("05/25/15");
		
		testParkManager = new ParkManager(testFirstName, testLastName, testEmail);
		testPark = new Park(testParkId, testParkName, testParkManager);
		testJob = new Job(testJobId, testJobStartDate, testPark);
		
		testJobMaxNumLightVolunteers = 4;
		testJobMaxNumMediumVolunteers = 4;
		testJobMaxNumHeavyVolunteers = 4;
		
		testLightVolunteersHasRoom = new TreeSet<Volunteer>();
		testLightVolunteersFull = new TreeSet<Volunteer>();
		testMediumVolunteersHasRoom = new TreeSet<Volunteer>();
		testMediumVolunteersFull = new TreeSet<Volunteer>();
		testHeavyVolunteersHasRoom = new TreeSet<Volunteer>();
		testHeavyVolunteersFull = new TreeSet<Volunteer>();
		
		
//		Populate light volunteer has room
		for (int i = 0; i < 1; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testLightVolunteersHasRoom.add(testVolunteer);
		}
		
//		Populate light volunteer is full
		for (int i = 0; i < 3; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testLightVolunteersFull.add(testVolunteer);
		}
		
//		Populate medium volunteer has room
		for (int i = 0; i < 1; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testMediumVolunteersHasRoom.add(testVolunteer);
		}
		
//		Populate medium volunteer is full
		for (int i = 0; i < 3; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testMediumVolunteersHasRoom.add(testVolunteer);
		}
		
//		Populate heavy volunteer has room
		for (int i = 0; i < 1; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testHeavyVolunteersHasRoom.add(testVolunteer);
		}
		
//		Populate heavy volunteer is full
		for (int i = 0; i < 3; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testHeavyVolunteersHasRoom.add(testVolunteer);
		}
	}
	
	@Test
	public void testIsLightVolunteersFullHasRoom(){
//		tests the class isLightVolunteersFull when it returns true
		
		TreeSet<Volunteer> lightVolunteers = testJob.getLightVolunteers();
		assertEquals(testLightVolunteersHasRoom.size(), lightVolunteers.size());
		
		assertTrue(testJob.isLightVolunteersFull());
	}
	
	@Test
	public void testIsLightVolunteersFullIsFull(){
//		tests the class isLightVolunteersFull when it returns false
		
		TreeSet<Volunteer> lightVolunteers = testJob.getLightVolunteers();
		assertEquals(testLightVolunteersFull.size(), lightVolunteers.size());
		
		assertFalse(testJob.isLightVolunteersFull());
	}
	
	@Test
	public void testIsMediumVolunteersFullHasRoom(){
//		tests the class isMediumVolunteersFull when it returns true
		
		TreeSet<Volunteer> mediumVolunteers = testJob.getMediumVolunteers();
		assertEquals(testMediumVolunteersHasRoom.size(), mediumVolunteers.size());
		
		assertTrue(testJob.isMediumVolunteersFull());
	}
	
	@Test
	public void testIsMediumVolunteersFullIsFull(){
//		tests the class isMediumVolunteersFull when it returns false
		
		TreeSet<Volunteer> mediumVolunteers = testJob.getMediumVolunteers();
		assertEquals(testMediumVolunteersFull.size(), mediumVolunteers.size());
		
		assertFalse(testJob.isMediumVolunteersFull());
	}
	
	@Test
	public void testIsHeavyVolunteersFullHasRoom(){
//		tests the class isHeavyVolunteersFull when it returns true
		
		TreeSet<Volunteer> heavyVolunteers = testJob.getHeavyVolunteers();
		assertEquals(testHeavyVolunteersHasRoom.size(), heavyVolunteers.size());
		
		assertTrue(testJob.isHeavyVolunteersFull());
	}
	
	@Test
	public void testIsHeavyVolunteersFullIsFull(){
//		tests the class isHeavyVolunteersFull when it returns false
		
		TreeSet<Volunteer> heavyVolunteers = testJob.getHeavyVolunteers();
		assertEquals(testHeavyVolunteersFull.size(), heavyVolunteers.size());
		
		assertFalse(testJob.isHeavyVolunteersFull());
	}
	
	@Test
	public void testAddLightVolunteer(){
		for(Volunteer volunteer : testLightVolunteersFull){
			assertTrue(testJob.addLightVolunteer(volunteer));
		}
	}
	
	@Test
	public void testAddMediumVolunteer(){
		for(Volunteer volunteer : testMediumVolunteersFull){
			assertTrue(testJob.addMediumVolunteer(volunteer));
		}
	}

	@Test
	public void testAddHeavyVolunteer(){
		for(Volunteer volunteer : testHeavyVolunteersFull){
			assertTrue(testJob.addHeavyVolunteer(volunteer));
		}
	}
	
	@Test
	public void testIsJobScheduleValidIsWithinBounds() throws Exception{
//		Checks dates that meet the required business rules
		boolean ans = true;
		boolean val;
		
		Date startDate = testJob.getStartDate();
		Date endDate = testJob.getEndDate();
		
		testJobStartDate = format.parse("05/30/15");
		testJobEndDate = format.parse("05/06/15");
		
		val = testJob.isJobScheduleValid(testJobStartDate, testJobEndDate);
		assertEquals(ans, val);
	}
	
	@Test
	public void testIsJobScheduleValidIsNotWithinBounds() throws Exception{
//		Checks dates that doesn't meet the required business rules
		boolean ans = false;
		boolean val;
		
		Date startDate = testJob.getStartDate();
		Date endDate = testJob.getEndDate();
		
		testJobStartDate = format.parse("07/30/15");
		testJobEndDate = format.parse("08/06/15");
		
		val = testJob.isJobScheduleValid(testJobStartDate, testJobEndDate);
		assertEquals(ans, val);
	}
	
}
