package test;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;


public class VolunteerTest {

	@Test
	public void testAddJob() {
		
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		Job job = new Job(0, new Date(115, 6, 4), new Date(115, 6, 4), park);
		assertTrue(person.addJob(job));
	}
	
	@Test
	public void testAddJobFails() {
		
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		Job job = new Job(0, new Date(115, 5, 1), new Date(115, 5, 1), park);
		person.addJob(job);
		assertFalse(person.addJob(job));
	}

	@Test
	public void testGetJobs() {
		
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		Job job = new Job(0, new Date(115, 5, 1), new Date(115, 5, 1), park);
		person.addJob(job);
		assertTrue(person.getJobs().size() > 0);
	}

	@Test
	public void testCanSignUpForJob() {
		
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		Job job = new Job(0, new Date(115, 5, 1), new Date(115, 5, 1), park);
		assertTrue(person.canSignUpForJob(job));
	}

	@Test
	public void testDateInThePast() {
		
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		Job job = new Job(0, new Date(115, 4, 1), new Date(115, 4, 1), park);
		assertFalse(person.canSignUpForJob(job));
	}
	
	@Test
	public void testDateConflict() {
		
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		Job job = new Job(0, new Date(115, 5, 1), new Date(115, 5, 1), park);
		Job otherJob = new Job(1, new Date(115, 5, 1), new Date(115, 5, 1), park);
		person.addJob(job);
		assertFalse(person.canSignUpForJob(otherJob));
	}

}
