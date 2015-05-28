package model;


import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeSet;

/**
 * The Central Database for the system that holds all the users.
 * 
 * @author Qiyuan Zhou
 * 
 */

public class Handler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 22764371777244455L;

	/** The administrators mapped to their email addresses. */
	private Map<String, Administrator> administrators;

	/** The park managers mapped to their email addresses. */
	private Map<String, ParkManager> parkManagers;

	/** The volunteers mapped to their email addresses. */
	private Map<String, Volunteer> volunteers;

	private final int MAXPENDINGJOB = 30;
	private final int THREEDAYS = 3 * 1000 * 60 * 60 * 24;

	/**
	 * Instantiates a new central database.
	 */
	public Handler() {
		// instantiate the maps of users
		administrators = new HashMap<String, Administrator>();
		parkManagers = new HashMap<String, ParkManager>();
		volunteers = new HashMap<String, Volunteer>();
	}

	/**
	 * Adds the user to the system.
	 * 
	 * @param user
	 *            the user to add
	 */
	public void addUser(User user) {

		// check if user == null?

		// If User is an Administrator:
		if (user instanceof Administrator)
			administrators.put(user.getEmailAddress(), (Administrator) user);
		// Else If User is a Park Manager:
		else if (user instanceof ParkManager)
			parkManagers.put(user.getEmailAddress(), (ParkManager) user);
		// Else If User is a Volunteer:
		else if (user instanceof Volunteer)
			volunteers.put(user.getEmailAddress(), (Volunteer) user);
			
	}

	/**
	 * Gets all the jobs in the system.
	 * 
	 * @return all jobs in the system
	 */
	public TreeSet<Job> getAllJobs() {
		
		TreeSet<Job> allJobs = new TreeSet<Job>();

		// for every park manager
		for (String email : parkManagers.keySet()) {
			ParkManager tempPM = parkManagers.get(email);

			// for every park associate with the PM
			Iterator<Park> i = tempPM.getParks().iterator();
			while (i.hasNext()) {
				Park tempPark = i.next();

				// add all the job associate with the park
				allJobs.addAll(tempPark.getJobs()); // not tested yet, might work
			}
		}

		return allJobs;
	}

	/**
	 * Gets all the jobs that haven't started yet.
	 * 
	 * @return all upcoming jobs in the system
	 */
	public TreeSet<Job> getUpcomingJobs() {

		TreeSet<Job> upcomingJobs = new TreeSet<Job>();

		// for every job in getAllJobs()
		Iterator<Job> i = getAllJobs().iterator();
		while (i.hasNext()) {
			Job tempJob = i.next();
			Date today = new Date(System.currentTimeMillis());
			// if job has not started yet
			if (tempJob.getStartDate().after(today)) {
				// add when not
				// if (!isPendingJobsFull()) infinite loop
				if (upcomingJobs.size() < MAXPENDINGJOB)
					upcomingJobs.add(tempJob);
			}
		}

		return upcomingJobs;
	}

	/**
	 * Checks if the maximum number of upcoming jobs has already been reached.
	 * 
	 * @return true if the maximum number of upcoming jobs has already been
	 *         reached, false otherwise
	 */
	public boolean isPendingJobsFull() {
		if (getUpcomingJobs().size() >= MAXPENDINGJOB)
			return true;
		else
			return false;
	}

	/**
	 * Checks if a job with the given start date and end date can be added. That
	 * is, the number of jobs scheduled within the week is still less than 5.
	 * 
	 * @param startDate
	 *            the start date of the job to be added
	 * @param endDate
	 *            the end date of the job to be added
	 * @return true if a job with the given start date and end date can be
	 *         added, false otherwise
	 */
	public boolean isJobScheduleAvailable(Date startDate, Date endDate) {

		// creates a boundary of 3 days away from the current date
		Date leftBound = new Date(startDate.getTime() - THREEDAYS);
		Date rightBound = new Date(endDate.getTime() + THREEDAYS);
		int count = 0;

		// for all jobs in getUpcomingJobs(), count the jobs that fall in the
		// boundary
		Iterator<Job> i = getUpcomingJobs().iterator();
		while (i.hasNext()) {
			Job tempJob = i.next();
			if (tempJob.getEndDate().after(leftBound)
					&& tempJob.getStartDate().before(rightBound)) {
				count++;
			}
		}

		if (count > 4)
			return false;
		else
			return true;

	}

	/**
	 * Gets the volunteers with the given last name.
	 * 
	 * @param lastName
	 *            the last name to use for searching
	 * @return the set volunteers with the given last name
	 */
	public TreeSet<Volunteer> getVolunteersByLastName(String lastName) {

		TreeSet<Volunteer> foundVolunteer = new TreeSet<Volunteer>();
		// for all volunteers in the map, look for a last name match
		// then add it to foundVolunteer
		for (String email : volunteers.keySet()) {
			Volunteer tempVolunteer = volunteers.get(email);
			if (tempVolunteer.getLastName().equals(lastName))
				foundVolunteer.add(tempVolunteer);
		}

		return foundVolunteer;
	}

	/**
	 * Gets the user with the given email address.
	 * 
	 * @param email
	 *            the email to use for searching
	 * @return the user with the given email address
	 */
	public User getUserByEmail(String email) {

		if (administrators.containsKey(email))
			return administrators.get(email);
		else if (parkManagers.containsKey(email))
			return parkManagers.get(email);
		else if (volunteers.containsKey(email))
			return volunteers.get(email);
		else
			return null;
	}

	public Map<String, Administrator> getAdministrators() {
		return administrators;
	}

	public Map<String, ParkManager> getParkManagers() {
		return parkManagers;
	}

	public Map<String, Volunteer> getVolunteers() {
		return volunteers;
	}


}
