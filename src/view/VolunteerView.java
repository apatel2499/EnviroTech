package view;

import java.util.List;
import java.util.Map;

import model.Job;

/**
 * The VolunteerView is the interface for all view classes for 
 * a volunteer user.
 * 
 * @author Katie
 */
public interface VolunteerView {
	
	/** The number of menu items for a volunteer. */
	public static final int V_NUM_MENU = 4;
	
	/** The constant integer representing the 'view upcoming jobs' action. */
	public static final int V_MENU_VIEW_UPCOMING = 0;
	
	/** The constant integer representing the 'volunteer for a job' action. */
	public static final int V_MENU_VOLUNTEER = 1;
	
	/** The constant integer representing the 'view jobs I am signed up for' action. */
	public static final int V_MENU_VIEW_SIGNED = 2;
	
	/** The constant integer representing the 'exit' action. */
	public static final int V_MENU_EXIT = 3;
	
	/**
	 * Updates the view to the home view of a volunteer user.
	 *
	 * @param name the name of the volunteer
	 */
	public abstract void viewHome(String name);
	
	/**
	 * Returns the action the volunteer chose in the menu.
	 *
	 * @return 0 if user wants to view upcoming jobs or (optional) sign up for one of those jobs,
	 * 1 if user wants to view all the jobs he/she signed up for,
	 * 2 if user wants to exit
	 */
	public abstract int getMenuChoice();
	
	/**
	 * Updates the view to show the volunteer the list of upcoming jobs
	 * he/she can sign up for.
	 *
	 * @param upcomingJobs the upcoming jobs the volunteer can sign up for
	 */
	public abstract void viewUpcomingJobs(List<Job> upcomingJobs);
	
	/**
	 * Returns the job number (according to the way they were listed) the
	 * volunteer wishes to sign up for.
	 *
	 * @param maxJobNumber the max job number the volunteer can enter
	 * @return the number of the job the volunteer wishes to sign up for
	 */
	public abstract int getJobNumberSignUp(int maxJobNumber);
	
	/**
	 * Updates the view to show the different the job categories under a job
	 * that are not yet full.
	 *
	 * @param jobCategoriesAvailable the job categories available, plus an
	 * additional option to cancel
	 */
	public abstract void viewJobCategoryChoices(List<String> jobCategoriesAvailable);
	
	/**
	 * Returns the number of the job category the volunteer will sign up for.
	 *
	 * @param maxJobCategory the max job category number the volunteer can input
	 * @return the number of the job category (or cancel) the volunteer chose
	 */
	public abstract int getJobCategoryChoice(int maxJobCategory);
	
	/**
	 * Updates the view to show that the volunteer has successfully sign up for a job.
	 *
	 * @param jobName the name of the job the volunteer just signed up for
	 */
	public abstract void viewSignUpSuccess(String jobName);
	
	/**
	 * Updates the view to show that the volunteer has failed to sign up for a job
	 * (volunteer was not successfully added to chosen category).
	 *
	 * @param jobName the name of the job the volunteer wishes to sign up for
	 */
	public abstract void viewSignUpErrorOnJob(String jobName);
	
	/**
	 * Updates the view to show that the volunteer has failed to sign up for a job
	 * (job was not successfully added to volunteer's jobs list).
	 *
	 * @param jobName the name of the job the volunteer wishes to sign up for
	 */
	public abstract void viewSignUpErrorOnVolunteer(String jobName);

	/**
	 * Updates the view to show the jobs the volunteer has signed up for.
	 *
	 * @param allJobs the list of all jobs the volunteer signed up for
	 * @param allJobsCategories the map of job ids and the category where the volunteer signed up for
	 */
	public abstract void viewSignedUpJobs(List<Job> allJobs, Map<Integer, String> allJobsCategories);

}