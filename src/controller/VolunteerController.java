package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import model.Job;
import model.JobComparatorByDate;
import model.Volunteer;
import view.VolunteerView;

/**
 * The VolunteerController is the controller responsible for handling
 * user interactions with a Volunteer user.
 * 
 * @author Group 7
 */
public class VolunteerController implements UserController {

	/** The string representation of a light job category. */
	private static final String LIGHT_CAT = "Light";

	/** The string representation of a medium job category. */
	private static final String MEDIUM_CAT = "Medium";

	/** The string representation of a heavy job category. */
	private static final String HEAVY_CAT = "Heavy";
	
	/** The data controller. */
	private DataController dataController;

	/** The volunteer view. */
	private VolunteerView view;

	/** The current volunteer user. */
	private Volunteer volunteer;

	/**
	 * Instantiates a new volunteer controller.
	 *
	 * @param dataController the data controller
	 * @param view the view for the volunteer controller
	 * @param volunteer the current volunteer user
	 */
	public VolunteerController(DataController dataController, VolunteerView view, Volunteer volunteer) {
		this.dataController = dataController;
		this.view = view;
		this.volunteer = volunteer;
	}

	/* (non-Javadoc)
	 * @see controller.UserController#run()
	 */
	public void run() {
		// volunteer home
		view.viewHome(volunteer.getFirstName() + " " + volunteer.getLastName());

		// continuously ask user to select an action until he/she chooses to exit
		int actionChosen = 0;
		do {
			actionChosen = view.getMenuChoice();
			if (actionChosen == VolunteerView.V_MENU_VIEW_UPCOMING) {
				doViewUpcoming();
			} else if (actionChosen == VolunteerView.V_MENU_VOLUNTEER) {
				doSignup();
			} else if (actionChosen == VolunteerView.V_MENU_VIEW_SIGNED) {
				doViewSigned();
			}
		} while (actionChosen != VolunteerView.V_MENU_EXIT);
	}

	/**
	 * Lists the upcoming jobs the volunteer can sign up for.
	 */
	private List<Job> doViewUpcoming() {
		// get set of all upcoming jobs that the volunteer can sign up for:
		// - job must be in the future
		// - volunteer must be able to schedule for the job
		// - job must have at least 1 job category that's not yet full
		List<Job> upcomingJobs = new ArrayList<>();
		for (Job job : dataController.getData().getUpcomingJobs()) {
			if (volunteer.canSignUpForJob(job)) {
				if (!job.isLightVolunteersFull() || !job.isMediumVolunteersFull() || !job.isHeavyVolunteersFull()) {
					upcomingJobs.add(job);
				}
			}
		}
		Collections.sort(upcomingJobs, new JobComparatorByDate());
		view.viewUpcomingJobs(upcomingJobs);
		return upcomingJobs;
	}
	
	/**
	 * Lists the upcoming jobs the volunteer can sign up for and asks the user for the
	 * job he/she wishes to sign up for.
	 */
	private void doSignup() {
		List<Job> upcomingJobs = doViewUpcoming();
		if (!upcomingJobs.isEmpty()) {
			int jobNumber = view.getJobNumberSignUp(upcomingJobs.size());
			doSignupCategory(upcomingJobs.get(jobNumber));
		}
	}

	/**
	 * Lists the available categories and asks user to select one for signing up in the job.
	 *
	 * @param jobToSignUp the job the volunteer wishes to sign up for
	 */
	private void doSignupCategory(Job jobToSignUp) {
		// ask volunteer for the job category
		Map<Integer, String> jobCategoryMap = new TreeMap<>();
		int index = 0;
		if (!jobToSignUp.isLightVolunteersFull()) {
			jobCategoryMap.put(index++, LIGHT_CAT);
		} 
		if (!jobToSignUp.isMediumVolunteersFull()) {
			jobCategoryMap.put(index++, MEDIUM_CAT);
		}
		if (!jobToSignUp.isHeavyVolunteersFull()) {
			jobCategoryMap.put(index++, HEAVY_CAT);
		}
		view.viewJobCategoryChoices(new ArrayList<>(jobCategoryMap.values()));

		if (!jobCategoryMap.isEmpty()) {
			int jobCategory = view.getJobCategoryChoice(jobCategoryMap.size());
	
			boolean addedToJob = false;
			boolean addedToVolunteer = false;
			if (jobCategoryMap.get(jobCategory).equals(LIGHT_CAT)) {
				if (jobToSignUp.addLightVolunteer(volunteer)) {
					addedToJob = true;
					addedToVolunteer = volunteer.addLightJob(jobToSignUp);
				}
			} else if (jobCategoryMap.get(jobCategory).equals(MEDIUM_CAT)) {
				if (jobToSignUp.addMediumVolunteer(volunteer)) {
					addedToJob = true;
					addedToVolunteer = volunteer.addMediumJob(jobToSignUp);
				}
			} else if (jobCategoryMap.get(jobCategory).equals(HEAVY_CAT)) {
				if (jobToSignUp.addHeavyVolunteer(volunteer)) {
					addedToJob = true;
					addedToVolunteer = volunteer.addHeavyJob(jobToSignUp);
				}
			}
			
			if (addedToJob) {
				if (addedToVolunteer) {
					dataController.saveData();
					view.viewSignUpSuccess(jobToSignUp.getJobName());
				} else {
					view.viewSignUpErrorOnVolunteer(jobToSignUp.getJobName());
				}
			} else {
				view.viewSignUpErrorOnJob(jobToSignUp.getJobName());
			}
		}
	}

	/**
	 * Lists the jobs the volunteer has signed up for.
	 */
	private void doViewSigned() {
		Map<Integer, String> jobCategories = new HashMap<>();
		List<Job> allJobs = new ArrayList<>();
		for (Job job : volunteer.getLightJobs()) {
			jobCategories.put(job.getJobId(), "Light");
			allJobs.add(job);
		}
		for (Job job : volunteer.getMediumJobs()) {
			jobCategories.put(job.getJobId(), "Medium");
			allJobs.add(job);
		}
		for (Job job : volunteer.getHeavyJobs()) {
			jobCategories.put(job.getJobId(), "Heavy");
			allJobs.add(job);
		}
		Collections.sort(allJobs, new JobComparatorByDate());
		view.viewSignedUpJobs(allJobs, jobCategories);
	}

}
