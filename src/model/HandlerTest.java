package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.Date;

import model.Administrator;
import model.Handler;
import model.Job;
import model.Park;
import model.ParkManager;
import model.User;
import model.Volunteer;

import org.junit.Before;
import org.junit.Test;

public class HandlerTest {
	
	private Handler handler;
	private Administrator admin;
	private ParkManager pm;
	private Volunteer vol;
	
	@Before
	public void setUp()
	{
		handler = new Handler();
		admin = new Administrator("first","last","email@email.com");
		pm = new ParkManager("park","manager","pm@pm.com");
		vol = new Volunteer("volun","teer","volun@teer.com");
	}

	
	@Test
	public void testHandlerConstructor() {
		handler = new Handler();
		
		// constructors instantiate 3 hashmap, they should not be null
		assertNotNull(handler.getAdministrators());
		assertNotNull(handler.getParkManagers());
		assertNotNull(handler.getVolunteers());
		
	}
	
	
	@Test
	public void testAddUser() {
		
		// test add an administrator
		handler.addUser(admin);
		assertTrue(handler.getAdministrators().containsValue(admin));
		
		// test add a park manager
		handler.addUser(pm);
		assertTrue(handler.getParkManagers().containsValue(pm));
		
		// test add a volunteer
		handler.addUser(vol);
		assertTrue(handler.getVolunteers().containsValue(vol));
		
		// test add a null user
		User user = null;
		handler.addUser(user);
		assertTrue(!handler.getAdministrators().containsValue(null));
		assertTrue(!handler.getParkManagers().containsValue(null));
		assertTrue(!handler.getVolunteers().containsValue(null));
	}

	@Test
	public void testGetAllJobs() {

		// test when there's no job
		assertEquals(0, handler.getAllJobs().size());
		
		// test when there are one job
		Park tempPark = new Park(0, "park a", pm);
		Date tempDate = new Date(System.currentTimeMillis());
		Job tempJob = new Job(0, tempDate, tempDate, tempPark);
		pm.addPark(tempPark);
		handler.addUser(pm);
		assertEquals(1, handler.getAllJobs().size());
		
		// test when there are three jobs
		Job tempJob2 = new Job(1, tempDate, tempDate, tempPark);
		Park tempPark2 = new Park(1, "park b", pm);		// create another park 
		pm.addPark(tempPark2);
		Job tempJob3 = new Job(2, tempDate, tempDate, tempPark2);		// add a job to park 2
		assertEquals(3, handler.getAllJobs().size());
		
	}

	@Test
	public void testGetUpcomingJobs() {
		
		// test when there's no job
		assertEquals(0, handler.getUpcomingJobs().size());

		// test when there is one job but the job's start date is not in the future
		Park tempPark = new Park(0, "park a", pm);
		Date tempDate = new Date(System.currentTimeMillis());
		Job tempJob = new Job(0, tempDate, tempDate, tempPark);
		pm.addPark(tempPark);
		handler.addUser(pm);
		assertEquals(0, handler.getUpcomingJobs().size());

		// test when there is a job in the future
		tempDate = new Date(System.currentTimeMillis() + 1000*60*60*24);
		Job tempJob2 = new Job(1, tempDate, tempDate, tempPark);
		assertEquals(1, handler.getUpcomingJobs().size());
	
	}

	@Test
	public void testIsPendingJobsFull() {
		
		// when there are not more than 30 pending jobs
		assertEquals(false, handler.isPendingJobsFull());
		
	}

	/**
	 * 		Note:
	 *		For this method, I added a new constructor for Job class to accept jobEndDate.
	 *		ie. Job job = new Job(jobId, startDate, endDate, park);
	 *		Because the original constructor initialize a job instance and immediately assign
	 *		it to the associate park without initializing endDate. So when checking if a job
	 *		is available, the method tries to access a job end date that is not initialized, 
	 *		therefore getting a NullPointException. 
	 */
	@Test
	public void testIsJobScheduleAvailable() {
		
		// when there are less than 5 jobs within a week
		Park tempPark = new Park(0, "park a", pm);
		pm.addPark(tempPark);
		handler.addUser(pm);
		
		Date tempDate = new Date(System.currentTimeMillis() + 1000*60*60*24);
		Job tempJob = new Job(0, tempDate, tempDate, tempPark);
		Job tempJob2 = new Job(1, tempDate, tempDate, tempPark);
		
		Date startDate = new Date(System.currentTimeMillis());
		Date endDate = new Date(System.currentTimeMillis());
		
		assertTrue(handler.isJobScheduleAvailable(startDate, endDate));
		
		
		// when there are 5 jobs already within a week
		Job tempJob3 = new Job(2, tempDate, tempDate, tempPark);
		Job tempJob4 = new Job(3, tempDate, tempDate, tempPark);
		Job tempJob5 = new Job(4, tempDate, tempDate, tempPark);
		
		assertTrue(!handler.isJobScheduleAvailable(startDate, endDate));
		
	}

	@Test
	public void testGetVolunteersByLastName() {
		
		// when last name match a volunteer
		handler.addUser(vol);
		assertTrue(handler.getVolunteersByLastName("teer").contains(vol));
		
		// when last name match a user that is not a volunteer
		handler.addUser(pm);
		assertTrue(!handler.getVolunteersByLastName("manager").contains(pm));
		
		// when no last name match
		assertEquals(0, handler.getVolunteersByLastName("blahhhhh").size());
	}

	@Test
	public void testGetUserByEmail() {
		
		// when email match a user
		handler.addUser(admin);
		assertEquals(admin, handler.getUserByEmail("email@email.com"));
		
		// when email does not match a user
		assertEquals(null, handler.getUserByEmail("blahhhhhh"));
		
		
	}

}
