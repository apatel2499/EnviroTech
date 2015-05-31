package model;


import java.util.Date;
import java.util.TreeSet;

/**
 * The concrete implementation for a ParkManager.
 * 
 * @author Ankit
 */
public class ParkManager extends User {
	
	/** The constant serialVersionUID for the ParkManager class. */
	private static final long serialVersionUID = -1905680807123719484L;
	
	/** The parks the park manager manages. */
	private TreeSet<Park> parks;
	
	/**
	 * Instantiates a new park manager without any attributes.
	 */
	public ParkManager() {
		super();
		parks = new TreeSet<Park>();
	}
	
	/**
	 * Instantiates a new park manager with the given attributes.
	 *
	 * @param firstName the park manager's first name
	 * @param lastName the park manager's last name
	 * @param emailAddress the park manager's email address
	 */
	public ParkManager(String firstName, String lastName, String emailAddress) {
		super(firstName, lastName, emailAddress);
		parks = new TreeSet<Park>();
	}

	/**
	 * Adds the park to the parks owned by the park manager.
	 *
	 * @param park the park to add
	 * @return true if the park has been added, false otherwise
	 */
	public boolean addPark(Park park) {
		return parks.add(park);
	}
	
	/**
	 * Gets the parks the park manager manages.
	 *
	 * @return the parks the park manager manages
	 */
	public TreeSet<Park> getParks() {
		return parks;
	}
	
	/**
	 * Gets all the upcoming jobs from all the parks managed by the park manager.
	 *
	 * @return all the upcoming jobs from all the parks managed by the park manager
	 */
	public TreeSet<Job> getAllUpcomingJobs() {
		TreeSet<Job> allUpcomingJobs = new TreeSet<Job>();
		Date today = new Date();
		for (Park park : parks) {
			for (Job job : park.getJobs()) {
				if (today.before(job.getStartDate())) {
					allUpcomingJobs.add(job);
				}
			}
		}
		return allUpcomingJobs;
	}
	
}
