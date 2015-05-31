package view;

import java.util.Date;
import java.util.List;

import model.Job;
import model.Park;
import model.Volunteer;

/**
 * The ParkManagerView is the interface for all view classes for 
 * a park manager user.
 * 
 * @author Ankit
 */
public interface ParkManagerView {

	/** The number of menu items for a park manager. */
	public static final int PM_NUM_MENU = 4;
	
	/** The constant integer representing the 'submit a new job request' action. */
	public static final int PM_MENU_NEW_JOB = 0;
	
	/** The constant integer representing the 'view my upcoming jobs' action. */
	public static final int PM_MENU_VIEW_UPCOMING_JOBS = 1;
	
	/** The constant integer representing the 'view volunteers' action. */
	public static final int PM_MENU_VIEW_VOLUNTEERS = 2;
	
	/** The constant integer representing the 'exit' action. */
	public static final int PM_MENU_EXIT = 3;
	
	/** The minimum maximum number of volunteers the user can input for any category. */
	public static final int MIN_NUM_VOLUNTEERS = 0;
	
	/** The maximum maximum number of volunteers the user can input for any category. */
	public static final int MAX_NUM_VOLUNTEERS = 5;
	
	/**
	 * Updates the view to the home view of a park manager user.
	 *
	 * @param name the name of the park manager
	 */
	public abstract void viewHome(String name);
	
	/**
	 * Returns the action the park manager chose in the menu.
	 *
	 * @return 0 if user wants to submit a new job request,
	 * 1 if user wants to view upcoming jobs in the parks he/she manages,
	 * 2 if user wants to view the volunteers in the jobs in the parks he/she manages,
	 * 3 if user wants to exit
	 */
	public abstract int getMenuChoice();
	
	/**
	 * Returns the name of the new job the user wishes to submit.
	 *
	 * @return the new job name
	 */
	public abstract String getNewJobName();
	
	/**
	 * Returns the start date of the new job the user wishes to submit.
	 *
	 * @return the new job start date
	 */
	public abstract Date getNewJobStartDate();
	
	/**
	 * Returns the end date of the new job the user wishes to submit.
	 *
	 * @return the new job end date
	 */
	public abstract Date getNewJobEndDate();
	
	/**
	 * Returns the maximum number of light volunteers of the new job the user wishes to submit.
	 *
	 * @return the new job max number of light volunteers
	 */
	public abstract int getNewJobMaxNumLightVolunteers();
	
	/**
	 * Returns the maximum number of medium volunteers of the new job the user wishes to submit.
	 *
	 * @return the new job max number of medium volunteers
	 */
	public abstract int getNewJobMaxNumMediumVolunteers();
	
	/**
	 * Returns the maximum number of heavy volunteers of the new job the user wishes to submit.
	 *
	 * @return the new job max number of heavy volunteers
	 */
	public abstract int getNewJobMaxNumHeavyVolunteers();
	
	/**
	 * Updates the view to show success message when user submitted a job request.
	 *
	 * @param jobName the name of the job the user submitted
	 * @param totalPendingJobs the total number of pending jobs in the system
	 */
	public abstract void viewSubmitJobSuccess(String jobName, int totalPendingJobs);
	
	/**
	 * Updates the view to show error message when the user can no longer request for
	 * a job because the maximum number of upcoming pending jobs is reached.
	 */
	public abstract void viewSubmitJobFailLimitReached();
	
	/**
	 * Updates the view to show error message when the week of the requested job
	 * date is already full.

	 * @param jobName the name of the job the user wishes to submit
	 */
	public abstract void viewSubmitJobFailWeekFull(String jobName);
	
	/**
	 * Updates the view to show error message when user submits a job request where
	 * the dates are invalid (e.g. start date has passed, end dates comes first 
	 * before start date, job lasts more than 2 days, or start date is more than 3 months 
	 * in the future.
	
	 * @param jobName the name of the job the user wishes to submit
	 */
	public abstract void viewSubmitJobFailInvalidDates(String jobName);
	
	/**
	 * Updates the view to show the user the parks he/she manages.
	 *
	 * @param myParks the parks the park manager manages
	 */
	public abstract void viewMyParks(List<Park> myParks);
	
	/**
	 * Gets the number of the park chosen by the user for detailing purposes.
	 *
	 * @param maxParkChoice the maximum number of park choice the user can enter
	 * @return the number of the park chosen by the user
	 */
	public abstract int getParkChoiceForViewing(int maxParkChoice);
	
	/**
	 * Gets the number of the park chosen by the user for adding a job.
	 *
	 * @param maxParkChoice the maximum number of park choice the user can enter
	 * @return the number of the park chosen by the user
	 */
	public abstract int getParkChoiceForNewJob(int maxParkChoice);
	
	/**
	 * Updates the view to show the user jobs in a park he/she manages.
	 *
	 * @param myJobs the jobs in a park the user manages
	 */
	public abstract void viewMyJobs(List<Job> myJobs);
	
	/**
	 * Gets the number of the job chosen by the user for detailing purposes.
	 *
	 * @param maxJobChoice the max job choice the user can enter
	 * @return the number of the job chosen by the user
	 */
	public abstract int getJobChoiceForViewing(int maxJobChoice);
	
	/**
	 * Updates the view to show the upcoming jobs in a park the user manages.
	 *
	 * @param myUpcomingJobs the upcoming jobs in park the user manages
	 */
	public abstract void viewMyUpcomingJobs(List<Job> myUpcomingJobs);
	
	/**
	 * Updates the view to show the volunteers in a job.
	 *
	 * @param lightVolunteers the light volunteers
	 * @param mediumVolunteers the medium volunteers
	 * @param heavyVolunteers the heavy volunteers
	 */
	public abstract void viewVolunteers(List<Volunteer> lightVolunteers, 
			List<Volunteer> mediumVolunteers, List<Volunteer> heavyVolunteers);

}