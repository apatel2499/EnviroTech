import java.util.Date;
import java.util.TreeSet;

/**
 * The concrete implementation for a Volunteer.
 */

@SuppressWarnings("serial")
public class Volunteer extends User {
	
	/** The jobs the volunteer has volunteered to. */
	private TreeSet<Job> jobs;
	
	/**
	 * Instantiates a new volunteer without any attributes.
	 */
	public Volunteer() {
		// call super()
		// instantiate the tree set of jobs
		super();
		jobs = new TreeSet<Job>();
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
		// instantiate the tree set of jobs
		super(firstName, lastName, emailAddress);
		jobs = new TreeSet<Job>();
		
	}

	/**
	 * Adds the job to the set of jobs the volunteer volunteered to.
	 *
	 * @param job the job to add
	 * @return true if the job has been added, false otherwise
	 */
	public boolean addJob(Job job) {
		// add job to jobs then return result
		return jobs.add(job);
	}
	
	/**
	 * Gets the jobs the volunteer volunteered to.
	 *
	 * @return the jobs the volunteer volunteered to
	 */
	public TreeSet<Job> getJobs() {
		// return jobs
		return jobs;
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
		
		for(Job temp: jobs) {
			if(temp.getStartDate().equals(job.getStartDate())
					|| temp.getEndDate().equals(job.getStartDate())) {
				flag = false;
			}
					
		}
		return flag;
	}

}
