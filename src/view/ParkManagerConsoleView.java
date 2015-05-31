package view;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import model.Job;
import model.Park;
import model.Volunteer;

/**
 * The ParkManagerConsoleView is a ParkManagerView implementation
 * that uses the console.
 * 
 * @author Ankit
 */
public class ParkManagerConsoleView implements ParkManagerView {

	/** The date format object for parsing dates. */
	private static final SimpleDateFormat INPUT_DATE_FORMAT = new SimpleDateFormat("MM/dd/yy");
	
	/** The date format object for formatting dates. */
	private static final SimpleDateFormat OUTPUT_DATE_FORMAT = new SimpleDateFormat("MM/dd/yyyy");
	
	/** The string format for printing a park line. */
	private static final String PARK_LINE_FORMAT = "%-5s %-25s \n";
	
	/** The string format for printing a job line. */
	private static final String JOB_LINE_FORMAT = "%-5s %-25s %-15s %-15s \n";
	
	/** The string format for printing a job line with more details. */
	private static final String JOB_LINE_EXTENDED_FORMAT = "%-5s %-25s %-25s %-15s %-15s %20s %20s %20s \n";
	
	/** The scanner for user input. */
	private Scanner keyboard;
	static {
		
	}

	/**
	 * Instantiates a new park manager view.
	 *
	 * @param keyboard the scanner for user input
	 */
	public ParkManagerConsoleView(Scanner keyboard) {
		this.keyboard = keyboard;
		OUTPUT_DATE_FORMAT.setLenient(false);
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#viewHome(java.lang.String)
	 */
	@Override
	public void viewHome(String name) {
		System.out.println("Ahoy, [PARK MANAGER] " + name + "! \n");
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#getMenuChoice()
	 */
	@Override
	public int getMenuChoice() {
		// prepare action menu options
		String[] actionOptions = new String[PM_NUM_MENU];
		actionOptions[PM_MENU_NEW_JOB] = "Submit a new job request";
		actionOptions[PM_MENU_VIEW_UPCOMING_JOBS] = "View upcoming jobs in the parks that I manage";
		actionOptions[PM_MENU_VIEW_VOLUNTEERS] = "View volunteers in the jobs in the parks that I manage";
		actionOptions[PM_MENU_EXIT] = "Exit";

		// ask and return chosen action
		int actionChosen = Console.inputOption(keyboard, "=============== MENU ===============", actionOptions);
		System.out.println();
		return actionChosen;
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#getNewJobName()
	 */
	@Override
	public String getNewJobName() {
		return Console.inputString(keyboard, "Enter job name: ", false);
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#getNewJobStartDate()
	 */
	@Override
	public Date getNewJobStartDate() {
		return Console.inputDate(keyboard, "Enter start date (mm/dd/yy): ", INPUT_DATE_FORMAT);
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#getNewJobEndDate()
	 */
	@Override
	public Date getNewJobEndDate() {
		return Console.inputDate(keyboard, "Enter end date (mm/dd/yy): ", INPUT_DATE_FORMAT);
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#getNewJobMaxNumLightVolunteers()
	 */
	@Override
	public int getNewJobMaxNumLightVolunteers() {
		return Console.inputInteger(keyboard, 
				"Enter maximum number of light volunteers (" + MIN_NUM_VOLUNTEERS + "-" + MAX_NUM_VOLUNTEERS + "): ",
				MIN_NUM_VOLUNTEERS, MAX_NUM_VOLUNTEERS);
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#getNewJobMaxNumMediumVolunteers()
	 */
	@Override
	public int getNewJobMaxNumMediumVolunteers() {
		return Console.inputInteger(keyboard, 
				"Enter maximum number of medium volunteers (" + MIN_NUM_VOLUNTEERS + "-" + MAX_NUM_VOLUNTEERS + "): ",
				MIN_NUM_VOLUNTEERS, MAX_NUM_VOLUNTEERS);
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#getNewJobMaxNumHeavyVolunteers()
	 */
	@Override
	public int getNewJobMaxNumHeavyVolunteers() {
		int maxHeavy = Console.inputInteger(keyboard, 
				"Enter maximum number of heavy volunteers (" + MIN_NUM_VOLUNTEERS + "-" + MAX_NUM_VOLUNTEERS + "): ",
				MIN_NUM_VOLUNTEERS, MAX_NUM_VOLUNTEERS);
		System.out.println();
		return maxHeavy;
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#viewSubmitJobSuccess()
	 */
	@Override
	public void viewSubmitJobSuccess(String jobName, int totalPendingJobs) {
		System.out.println("Job " + jobName + " created!");
		System.out.println("Total number of pending jobs: " + totalPendingJobs + " \n");
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#viewSubmitJobFailLimitReached()
	 */
	@Override
	public void viewSubmitJobFailLimitReached() {
		System.out.println("Sorry, the maximum number of pending jobs have been reached. \n");
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#viewSubmitJobFailWeekFull(java.lang.String)
	 */
	@Override
	public void viewSubmitJobFailWeekFull(String jobName) {
		System.out.println("Job " + jobName + " failed to submit. Week is already full. \n");
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#viewSubmitJobFailInvalidDates(java.lang.String)
	 */
	@Override
	public void viewSubmitJobFailInvalidDates(String jobName) {
		System.out.println("Job " + jobName + " failed to submit. Invalid dates. \n"
				+ "Make sure that the start date has not yet passed and is not farther than three months in the future, "
				+ "the end date does not come before the start date, "
				+ "job does not last more than two days. \n");
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#viewMyParks(java.util.List)
	 */
	@Override
	public void viewMyParks(List<Park> myParks) {
		if (myParks.isEmpty()) {
			System.out.println("No parks being managed.");
		} else {
			System.out.printf(PARK_LINE_FORMAT, "", "Park Name");
			for (int i = 1; i <= myParks.size(); i++) {
				printParkLine(i, myParks.get(i - 1));
			}
		}
		System.out.println();
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#getParkChoice(int)
	 */
	@Override
	public int getParkChoiceForViewing(int maxParkChoice) {
		int parkNumber = Console.inputInteger(keyboard, "Please enter the number of the park to view: ", 1, maxParkChoice) - 1;
		System.out.println();
		return parkNumber;
	}
	
	/* (non-Javadoc)
	 * @see view.ParkManagerView#getParkChoice(int)
	 */
	@Override
	public int getParkChoiceForNewJob(int maxParkChoice) {
		int parkNumber = Console.inputInteger(keyboard, "Please enter the number of the park for the new job: ", 1, maxParkChoice) - 1;
		System.out.println();
		return parkNumber;
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#viewMyJobs(java.util.List)
	 */
	@Override
	public void viewMyJobs(List<Job> myJobs) {
		if (myJobs.isEmpty()) {
			System.out.println("No jobs in this park yet.");
		} else {
			System.out.printf(JOB_LINE_FORMAT, "", "Job Name", "Start Date", "End Date");
			for (int i = 1; i <= myJobs.size(); i++) {
				printJobLine(i, myJobs.get(i - 1));
			}
		}
		System.out.println();
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#getJobChoice(int)
	 */
	@Override
	public int getJobChoiceForViewing(int maxJobChoice) {
		int jobNumber =  Console.inputInteger(keyboard, "Please enter the number of the job to view: ", 1, maxJobChoice) - 1;
		System.out.println();
		return jobNumber;
	}

	/* (non-Javadoc)
	 * @see view.ParkManagerView#viewMyUpcomingJobs(java.util.List)
	 */
	@Override
	public void viewMyUpcomingJobs(List<Job> myUpcomingJobs) {
		if (myUpcomingJobs.isEmpty()) {
			System.out.println("No upcoming jobs.");
		} else {
			System.out.printf(JOB_LINE_EXTENDED_FORMAT, "", "Job", "Park", "Start Date", "End Date", "Light Volunteers", "Medium Volunteers", "Heavy Volunteers");
			for (int i = 1; i <= myUpcomingJobs.size(); i++) {
				printJobLineExtended(i, myUpcomingJobs.get(i - 1));
			}
		}
		System.out.println();
	}
	
	/* (non-Javadoc)
	 * @see view.ParkManagerView#viewVolunteers(java.util.List, java.util.List, java.util.List)
	 */
	@Override
	public void viewVolunteers(List<Volunteer> lightVolunteers,
			List<Volunteer> mediumVolunteers, List<Volunteer> heavyVolunteers) {
		System.out.println("Light Category Volunteers:");
		printVolunteers(lightVolunteers);
		System.out.println();

		System.out.println("Medium Category Volunteers:");
		printVolunteers(mediumVolunteers);
		System.out.println();
		
		System.out.println("Heavy Category Volunteers:");
		printVolunteers(heavyVolunteers);
		System.out.println();
	}
	
	/**
	 * Prints a list of volunteers into the console.
	 *
	 * @param volunteers the volunteers to print
	 */
	private void printVolunteers(List<Volunteer> volunteers) {
		if (volunteers.isEmpty()) {
			System.out.println("No volunteers.");
		}
		for (int i = 1; i <= volunteers.size(); i++) {
			Volunteer volunteer = volunteers.get(i - 1);
			System.out.println(i + ") " + volunteer.getLastName() + ", " 
					+ volunteer.getFirstName() + " ("
					+ volunteer.getEmailAddress() + ")");
		}
	}

	/**
	 * Prints a line that shows the information about a job.
	 *
	 * @param lineNumber the line number
	 * @param job the job to print
	 */
	private void printJobLine(int lineNumber, Job job) {
		System.out.printf(JOB_LINE_FORMAT, Integer.toString(lineNumber), job.getJobName(),
				OUTPUT_DATE_FORMAT.format(job.getStartDate()), OUTPUT_DATE_FORMAT.format(job.getEndDate()));
	}
	
	/**
	 * Prints a line that shows the information about a job with more information.
	 *
	 * @param lineNumber the line number
	 * @param job the job to print
	 */
	private void printJobLineExtended(int lineNumber, Job job) {
		String lightVolunteersInfo = job.getLightVolunteers().size() + "/" + job.getMaxNumLightVolunteers();
		String mediumVolunteersInfo = job.getMediumVolunteers().size() + "/" + job.getMaxNumMediumVolunteers();
		String heavyVolunteersInfo = job.getHeavyVolunteers().size() + "/" + job.getMaxNumHeavyVolunteers();
		System.out.printf(JOB_LINE_EXTENDED_FORMAT, Integer.toString(lineNumber), job.getJobName(),
				job.getPark().getParkName(), OUTPUT_DATE_FORMAT.format(job.getStartDate()), OUTPUT_DATE_FORMAT.format(job.getEndDate()),
				lightVolunteersInfo, mediumVolunteersInfo, heavyVolunteersInfo);
	}
	
	/**
	 * Prints a line that shows the information about a park.
	 *
	 * @param lineNumber the line number
	 * @param park the park to print
	 */
	private void printParkLine(int lineNumber, Park park) {
		System.out.printf(PARK_LINE_FORMAT, Integer.toString(lineNumber), park.getParkName());
	}
	
}
