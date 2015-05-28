package model;


import static org.junit.Assert.*;

import java.util.Date;
import java.util.TreeSet;

import model.Job;
import model.Park;
import model.ParkManager;

import org.junit.Before;
import org.junit.Test;

public class ParkTest {
	
	private int parkId;

	private String parkName;

	private ParkManager parkManager;

	private TreeSet<Job> jobs;
	
	@Before
	public void setUp() {
		parkId = 0;
		parkName = "name";
		parkManager = new ParkManager("park","manager","pm@pm.com");
		jobs = new TreeSet<Job>();
	}

	@Test
	public void testPark() {
		
		// initiate a park through constructor, so park should not be null
		Park park = new Park(parkId, parkName, parkManager);
		assertNotNull(park);
		
	}

	/**
	 * 		Note: addJob() method is called in Job class constructor. When a job is initialized,
	 * 		it is automatically assigned to a park.
	 */
	@Test
	public void testAddJob() {
		
		Park park = new Park(parkId, parkName, parkManager);
		Date date = new Date();
		Job job = new Job(0, date, date, park);

		assertEquals(1, park.getJobs().size());
	}

}
