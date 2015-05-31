package model;

import java.util.Date;
import java.util.TreeSet;

/**
 * The concrete implementation for a Volunteer.
 * 
 * @author Katie
 */

public class Volunteer extends User {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7798736071695713562L;
	
	/** The jobs the volunteer has volunteered to as a light volunteer. */
	private TreeSet<Job> lightJobs;
	
	/** The jobs the volunteer has volunteered to as a medium volunteer. */
	private TreeSet<Job> mediumJobs;
	
	/** The jobs the volunteer has volunteered to as a heavy volunteer. */
	private TreeSet<Job> heavyJobs;
	
	/**
	 * Instantiates a new volunteer without any attributes.
	 */
	public Volunteer() {
		// call super()
		// instantiate the tree sets of jobs
		super();
		lightJobs = new TreeSet<Job>();
		mediumJobs = new TreeSet<Job>();
		heavyJobs = new TreeSet<Job>();
	}
	
	/**
	 * Instantiates a new volunteer with the given attributes.
	 *
	 * @param firstName the volunteer's first name
	 * @param lastName the volunteer's last name
	 * @param emailAddress volunteer's the email address
	 */
	public Volunteer(String firstName, String lastName, String emailAddress) {
		// call super(firstName, lastName, emailAddress)
		// instantiate the tree sets of jobs
		super(firstName, lastName, emailAddress);
		lightJobs = new TreeSet<Job>();
		mediumJobs = new TreeSet<Job>();
		heavyJobs = new TreeSet<Job>();
	}

	/**
	 * Adds the job to the set of jobs the volunteer volunteered to as a light volunteer.
	 *
	 * @param job the job to add
	 * @return true if the job has been added, false otherwise
	 */
	public boolean addLightJob(Job job) {
		// add job then return result
		if (canSignUpForJob(job)) {
			return lightJobs.add(job);
		}
		
		return false;
	}
	
	/**
	 * Adds the job to the set of jobs the volunteer volunteered to as a medium volunteer.
	 *
	 * @param job the job to add
	 * @return true if the job has been added, false otherwise
	 */
	public boolean addMediumJob(Job job) {
		// add job then return result
		if (canSignUpForJob(job)) {
			return mediumJobs.add(job);
		}
		
		return false;
	}
	
	/**
	 * Adds the job to the set of jobs the volunteer volunteered to as a heavy volunteer.
	 *
	 * @param job the job to add
	 * @return true if the job has been added, false otherwise
	 */
	public boolean addHeavyJob(Job job) {
		// add job then return result
		if (canSignUpForJob(job)) {
			return heavyJobs.add(job);
		}
		
		return false;
	}
	
	/**
	 * Gets the jobs the volunteer volunteered to as a light volunteer.
	 *
	 * @return the jobs the volunteer volunteered to as a light volunteer
	 */
	public TreeSet<Job> getLightJobs() {
		// return jobs
		return lightJobs;
	}
	
	/**
	 * Gets the jobs the volunteer volunteered to as a medium volunteer.
	 *
	 * @return the jobs the volunteer volunteered to as a medium volunteer
	 */
	public TreeSet<Job> getMediumJobs() {
		// return jobs
		return mediumJobs;
	}
	
	/**
	 * Gets the jobs the volunteer volunteered to as a heavy volunteer.
	 *
	 * @return the jobs the volunteer volunteered to as a heavy volunteer
	 */
	public TreeSet<Job> getHeavyJobs() {
		// return jobs
		return heavyJobs;
	}
	
	/**
	 * Checks if the volunteer can sign up for the given job.
	 * The job shouldn't have started yet and the volunteer should have no
	 * other jobs on scheduled on its start and end date.
	 *
	 * @param job the job to check
	 * @return true if the volunteer can sign up for the given job,
	 * false otherwise
	 */
	public boolean canSignUpForJob(Job job) {
		// If job's start date has already passed:
		//		Return False
		//
		// For all jobs:
		// 		If job is within the the job's start and end date:
		//			Return false
		//
		// Return true
		
		boolean flag = true; //flag is set to false if the job cannot be signed up for
		Date today = new Date(); //the current date
		
		if(job.getStartDate().before(today)) {
			flag = false;
		}
		
		TreeSet<Job> allJobs = new TreeSet<Job>(lightJobs);
		allJobs.addAll(mediumJobs);
		allJobs.addAll(heavyJobs);
		for(Job temp: allJobs) {
			if(DateUtil.dateEqualsIgnoreTime(temp.getStartDate(), job.getStartDate())
					|| DateUtil.dateEqualsIgnoreTime(temp.getEndDate(), job.getStartDate())
					|| DateUtil.dateEqualsIgnoreTime(temp.getStartDate(), job.getEndDate())
					|| DateUtil.dateEqualsIgnoreTime(temp.getEndDate(), job.getEndDate())) {
				flag = false;
			}
		}

		return flag;
	}

}
