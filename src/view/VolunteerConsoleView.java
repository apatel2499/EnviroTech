package view;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import model.Job;
import model.Park;
import model.ParkManager;

/**
 * The VolunteerConsoleView is a VolunteerView implementation
 * that uses the console.
 * 
 * @author Katie
 */
public class VolunteerConsoleView implements VolunteerView {
	
	/** The date format for display. */
	private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	/** The string format for printing a job line for displaying jobs signed up. */
	private static final String JOB_LINE_SIGNED_FORMAT = "%-5s %-25s %-25s %-25s %-15s %-15s %-15s \n";
	
	/** The string format for printing a job line for displaying upcoming jobs that can be signed up for. */
	private static final String JOB_LINE_UPCOMING_FORMAT = "%-5s %-25s %-25s %-25s %-15s %-15s %20s %20s %20s \n";
	
	/** The scanner for user input. */
	private Scanner keyboard;

	/**
	 * Instantiates a new volunteer view.
	 *
	 * @param keyboard the scanner for user input
	 */
	public VolunteerConsoleView(Scanner keyboard) {
		this.keyboard = keyboard;
	}

	/* (non-Javadoc)
	 * @see view.VolunteerView#viewHome(java.lang.String)
	 */
	@Override
	public void viewHome(String name) {
		System.out.println("Ahoy, [VOLUNTEER] " + name + "! \n");
	}

	/* (non-Javadoc)
	 * @see view.VolunteerView#getMenuChoice()
	 */
	@Override
	public int getMenuChoice() {
		// prepare action menu options
		String[] actionOptions = new String[V_NUM_MENU];
		actionOptions[V_MENU_VIEW_UPCOMING] = "View upcoming jobs I can sign up for";
		actionOptions[V_MENU_VOLUNTEER] = "Sign-up for an upcoming job";
		actionOptions[V_MENU_VIEW_SIGNED] = "View Jobs I have signed up for";
		actionOptions[V_MENU_EXIT] = "Exit";

		// ask and return chosen action
		int actionChosen = Console.inputOption(keyboard, "=============== MENU ===============", actionOptions);
		System.out.println();
		return actionChosen;
	}

	/* (non-Javadoc)
	 * @see view.VolunteerView#viewUpcomingJobs(java.util.List)
	 */
	@Override
	public void viewUpcomingJobs(List<Job> upcomingJobs) {
		if (upcomingJobs.isEmpty()) {
			System.out.println("No upcoming jobs you can sign up for.");
		} else {
			System.out.printf(JOB_LINE_UPCOMING_FORMAT, "", "Job", "Park", "Park Manager", "Start Date", "End Date", "Slots Left (Light)", "Slots Left (Medium)", "Slots Left (Heavy)");
			for (int i = 1; i <= upcomingJobs.size(); i++) {
				printJobLineUpcoming(i, upcomingJobs.get(i - 1));
			}
		}
		System.out.println();
	}

	/* (non-Javadoc)
	 * @see view.VolunteerView#getSignUpJobNumberChoice(int)
	 */
	@Override
	public int getJobNumberSignUp(int maxJobNumber) {
		int jobNumber = Console.inputInteger(keyboard, 
				"Please enter the number of the job you would like to sign up for: ", 
				1, maxJobNumber) - 1;
		System.out.println();
		return jobNumber;
	}
	
	/* (non-Javadoc)
	 * @see view.VolunteerView#viewJobCategoryChoices(java.util.List)
	 */
	@Override
	public void viewJobCategoryChoices(List<String> jobCategoriesAvailable) {
		if (jobCategoriesAvailable.isEmpty()) {
			System.out.println("No more slots on any category.");
		} else {
			System.out.println("Categories available: ");
			for (int i = 1; i <= jobCategoriesAvailable.size(); i++) {
				System.out.println(i + ") " + jobCategoriesAvailable.get(i - 1));
			}
		}
		System.out.println();
	}

	/* (non-Javadoc)
	 * @see view.VolunteerView#getJobCategoryChoice(int)
	 */
	@Override
	public int getJobCategoryChoice(int maxJobCategory) {
		int jobCategoryNumber = Console.inputInteger(keyboard, 
				"Please enter the number of the category you choose: ", 
				1, maxJobCategory) - 1;
		System.out.println();
		return jobCategoryNumber;
	}

	/* (non-Javadoc)
	 * @see view.VolunteerView#viewSignupSuccess(java.lang.String)
	 */
	@Override
	public void viewSignUpSuccess(String jobName) {
		System.out.println("You have successfully signed up for " + jobName + ". \n");
	}

	/* (non-Javadoc)
	 * @see view.VolunteerView#viewSignUpErrorOnJob(java.lang.String)
	 */
	@Override
	public void viewSignUpErrorOnJob(String jobName) {
		System.out.println("Error on adding volunteer to job's list of volunteers. \n");
	}

	/* (non-Javadoc)
	 * @see view.VolunteerView#viewSignUpErrorOnVolunteer(java.lang.String)
	 */
	@Override
	public void viewSignUpErrorOnVolunteer(String jobName) {
		System.out.println("Error on adding job to volunteer's list of jobs. \n");
	}

	/* (non-Javadoc)
	 * @see view.VolunteerView#viewSignedUpJobs(java.util.List)
	 */
	@Override
	public void viewSignedUpJobs(List<Job> allJobs, Map<Integer, String> allJobsCategories) {
		if (allJobs.isEmpty()) {
			System.out.println("No signed up jobs yet.");
		} else {
			System.out.printf(JOB_LINE_SIGNED_FORMAT, "", "Job", "Park", "Park Manager", "Start Date", "End Date", "Category");
			for (int i = 1; i <= allJobs.size(); i++) {
				Job job = allJobs.get(i - 1);
				printJobLineSigned(i, job, allJobsCategories.get(job.getJobId()));
			}
		}
		System.out.println();
	}
	
	/**
	 * Prints a line that shows the information about a job the volunteer signed up for.
	 *
	 * @param lineNumber the line number
	 * @param job the job to print
	 */
	private void printJobLineSigned(int lineNumber, Job job, String jobCategory) {
		Park park = job.getPark();
		ParkManager pm = park.getParkManager();
		System.out.printf(JOB_LINE_SIGNED_FORMAT, Integer.toString(lineNumber), job.getJobName(), 
				job.getPark().getParkName(), pm.getFirstName() + " " + pm.getLastName(),
				DATE_FORMAT.format(job.getStartDate()), DATE_FORMAT.format(job.getEndDate()), jobCategory);
	}
	
	/**
	 * Prints a line that shows the information about an upcoming job.
	 *
	 * @param lineNumber the line number
	 * @param job the job to print
	 */
	private void printJobLineUpcoming(int lineNumber, Job job) {
		String slotsLightLeft = Integer.toString(job.getMaxNumLightVolunteers() - job.getLightVolunteers().size());
		String slotsMediumLeft = Integer.toString(job.getMaxNumMediumVolunteers() - job.getMediumVolunteers().size());
		String slotsHeavyLeft = Integer.toString(job.getMaxNumHeavyVolunteers() - job.getHeavyVolunteers().size());
		Park park = job.getPark();
		ParkManager pm = park.getParkManager();
		System.out.printf(JOB_LINE_UPCOMING_FORMAT, Integer.toString(lineNumber), job.getJobName(),
				park.getParkName(), pm.getFirstName() + " " + pm.getLastName(),
				DATE_FORMAT.format(job.getStartDate()), DATE_FORMAT.format(job.getEndDate()),
				slotsLightLeft, slotsMediumLeft, slotsHeavyLeft);
	}
	
}
