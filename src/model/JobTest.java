package model;

import static org.junit.Assert.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TreeSet;

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
	
	/** The test job name. */
	private String testJobName; 
	
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
	private TreeSet<Volunteer> testLightVolunteers;
	
	/** Set of test medium volunteers. */
	private TreeSet<Volunteer> testMediumVolunteers;
	
	/** Set of test heavy volunteers. */
	private TreeSet<Volunteer> testHeavyVolunteers;
	
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
		testJobName = "Clean Park";
		testParkId = 1;
		
		testParkName = "Rainier";
		format = new SimpleDateFormat("mm/dd/yy");		
		testJobStartDate = format.parse("05/25/15");
		
		testParkManager = new ParkManager(testFirstName, testLastName, testEmail);
		testPark = new Park(testParkId, testParkName, testParkManager);
		testJob = new Job(testJobId, testJobStartDate, testJobEndDate, testPark);
		testJob.setJobName(testJobName);
		
		testLightVolunteers = new TreeSet<Volunteer>();
		testMediumVolunteers = new TreeSet<Volunteer>();
		testHeavyVolunteers = new TreeSet<Volunteer>();
		
	}
	
	@Test
	public void testIsLightVolunteersFullHasRoom(){
//		tests the class isLightVolunteersFull when it returns true
		
		testJobMaxNumLightVolunteers = 4;
		
//		Populate light volunteer has room
		for (int i = 0; i <= 1; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testLightVolunteers.add(testVolunteer);
			assertTrue(testJob.isLightVolunteersFull());
		}
	}
	
	@Test
	public void testIsLightVolunteersFullIsFull(){
//		tests the class isLightVolunteersFull when it returns false
		
		testJobMaxNumLightVolunteers = 4;
		
//		testLightVolunteers = testJob.getLightVolunteers();
		
//		Populate light volunteer is full
		for (int i = 0; i <= 3; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testLightVolunteers.add(testVolunteer);
			assertTrue(testJob.isLightVolunteersFull());
		}
	}
	
	@Test
	public void testIsMediumVolunteersFullHasRoom(){
//		tests the class isMediumVolunteersFull when it returns true
		
		testJobMaxNumMediumVolunteers = 4;
		
//		Populate medium volunteer has room
		for (int i = 0; i <= 1; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testMediumVolunteers.add(testVolunteer);
			assertTrue(testJob.isMediumVolunteersFull());
		}
	}
	
	@Test
	public void testIsMediumVolunteersFullIsFull(){
//		tests the class isMediumVolunteersFull when it returns false
		
		testJobMaxNumMediumVolunteers = 4; 
		
//		Populate medium volunteer is full
		for (int i = 0; i <= 3; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testMediumVolunteers.add(testVolunteer);
			assertTrue(testJob.isMediumVolunteersFull());
		}
	}
	
	@Test
	public void testIsHeavyVolunteersFullHasRoom(){
//		tests the class isHeavyVolunteersFull when it returns true
		
		testJobMaxNumHeavyVolunteers = 4; 
		
//		Populate heavy volunteer has room
		for (int i = 0; i <= 1; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testHeavyVolunteers.add(testVolunteer);
		}
		
		assertTrue(testJob.isHeavyVolunteersFull());
	}
	
	@Test
	public void testIsHeavyVolunteersFullIsFull(){
//		tests the class isHeavyVolunteersFull when it returns false
		
		testJobMaxNumHeavyVolunteers = 4; 
		
//		Populate heavy volunteer is full
		for (int i = 0; i <= 3; i++) {
			testVolunteer = new Volunteer("vFirstName" + i, "vLirstName" + i, "VolunteerEmail@" + i);
			testHeavyVolunteers.add(testVolunteer);
			assertTrue(testJob.isHeavyVolunteersFull());
		}
	}
	
	@Test
	public void testAddLightVolunteer(){
		for(Volunteer volunteer : testLightVolunteers){
			assertTrue(testJob.addLightVolunteer(volunteer));
		}
	}
	
	@Test
	public void testAddMediumVolunteer(){
		for(Volunteer volunteer : testMediumVolunteers){
			assertTrue(testJob.addMediumVolunteer(volunteer));
		}
	}

	@Test
	public void testAddHeavyVolunteer(){
		for(Volunteer volunteer : testHeavyVolunteers){
			assertTrue(testJob.addHeavyVolunteer(volunteer));
		}
	}
	
	@Test
	public void testIsJobScheduleValidIsWithinBounds() throws Exception{
//		Checks dates that meet the required business rules
		boolean ans = false;
		boolean val;
		
		testJobStartDate = format.parse("05/30/15");
		testJobEndDate = format.parse("05/30/15");
		
		val = Job.isJobScheduleValid(testJobStartDate, testJobEndDate);
		assertEquals(ans, val);
	}
	
	@Test
	public void testIsJobScheduleValidIsNotWithinBounds() throws Exception{
//		Checks dates that doesn't meet the required business rules
		boolean ans = false;
		boolean val;
		
		testJobStartDate = format.parse("07/30/15");
		testJobEndDate = format.parse("08/13/15");
		
		val = Job.isJobScheduleValid(testJobStartDate, testJobEndDate);
		assertEquals(ans, val);
	}
	
}