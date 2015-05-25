package com.swd.project;

import static org.junit.Assert.*;
import model.Administrator;
import model.ParkManager;
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
		
		// test when there are some jobs
		Park tempPark = new Park(0, "park a", pm);
		Job tempJob = new Job(0, tempPark);
		tempPark.addJob(tempJob);
		pm.addPark(tempPark);
		
		// need job class
		assertEquals(1, handler.getAllJobs().size());
		
	}

	@Test
	public void testGetUpcomingJobs() {
		fail("not yet implemented.");
	}

	@Test
	public void testIsPendingJobsFull() {
		fail("not yet implemented.");
	}

	@Test
	public void testIsJobScheduleAvailable() {
		fail("not yet implemented.");
	}

	@Test
	public void testGetVolunteersByLastName() {
		
		// when last name match a volunteer
		handler.addUser(vol);
		assertTrue(handler.getVolunteersByLastName("teer").contains(vol));
		
		// when last name match a user that is not a volunteer
		
		
		// when no last name match
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
