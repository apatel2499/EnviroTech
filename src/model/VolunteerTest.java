package model;


import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;

import org.junit.Test;


public class VolunteerTest {

	@Test
	public void testAddJob() {
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 1);
		Date date1 = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date date2 = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date date3 = calendar.getTime();
		
		Job job1 = new Job(0, date1, date1, park);
		assertTrue(person.addLightJob(job1));
		
		Job job2 = new Job(1, date2, date2, park);
		assertTrue(person.addMediumJob(job2));
		
		Job job3 = new Job(2, date3, date3, park);
		assertTrue(person.addHeavyJob(job3));
	}
	
	@Test
	public void testAddJobFails() {
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 1);
		Date date1 = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date date2 = calendar.getTime();
		calendar.add(Calendar.DATE, 1);
		Date date3 = calendar.getTime();
		
		Job job1 = new Job(0, date1, date1, park);
		person.addLightJob(job1);
		assertFalse(person.addLightJob(job1));
		assertFalse(person.addMediumJob(job1));
		assertFalse(person.addHeavyJob(job1));
		
		Job job2 = new Job(1, date2, date2, park);
		person.addMediumJob(job2);
		assertFalse(person.addLightJob(job2));
		assertFalse(person.addMediumJob(job2));
		assertFalse(person.addHeavyJob(job2));
		
		Job job3 = new Job(2, date3, date3, park);
		person.addHeavyJob(job3);
		assertFalse(person.addLightJob(job3));
		assertFalse(person.addMediumJob(job3));
		assertFalse(person.addHeavyJob(job3));
	}

	@Test
	public void testGetLightJobs() {
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		assertEquals(0, person.getLightJobs().size());
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 1);
		Date tomorrow = calendar.getTime();

		Job job = new Job(0, tomorrow, tomorrow, park);
		person.addLightJob(job);
		assertEquals(1, person.getLightJobs().size());
	}
	
	@Test
	public void testGetMediumJobs() {
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		assertEquals(0, person.getMediumJobs().size());
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 1);
		Date tomorrow = calendar.getTime();
		
		Job job = new Job(0, tomorrow, tomorrow, park);
		person.addMediumJob(job);
		assertEquals(1, person.getMediumJobs().size());
	}
	
	@Test
	public void testGetHeavyJobs() {
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		assertEquals(0, person.getHeavyJobs().size());
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 1);
		Date tomorrow = calendar.getTime();
		
		Job job = new Job(0, tomorrow, tomorrow, park);
		person.addHeavyJob(job);
		assertEquals(1, person.getHeavyJobs().size());
	}

	@Test
	public void testCanSignUpForJob() {
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 1);
		Date tomorrow = calendar.getTime();
		
		Job job = new Job(0, tomorrow, tomorrow, park);
		assertTrue(person.canSignUpForJob(job));
	}

	@Test
	public void testDateInThePast() {
		
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);
		
		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, -1);
		Date yesterday = calendar.getTime();
		
		Job job = new Job(0, yesterday, yesterday, park);
		assertFalse(person.canSignUpForJob(job));
	}
	
	@Test
	public void testDateConflict() {
		Volunteer person = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		ParkManager pm = new ParkManager("John", "Doe", "johndoe@gmail.com");
		Park park = new Park(0, "Linkin Park", pm);

		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 1);
		Date tomorrow = calendar.getTime();
		
		Job job = new Job(0, tomorrow, tomorrow, park);
		Job otherJob = new Job(1, tomorrow, tomorrow, park);
		person.addLightJob(job);
		assertFalse(person.canSignUpForJob(otherJob));
	}

}