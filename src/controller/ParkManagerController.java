package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import model.Job;
import model.JobComparatorByDate;
import model.Park;
import model.ParkComparatorByName;
import model.ParkManager;
import model.UserComparatorByName;
import model.Volunteer;
import view.ParkManagerView;

/**
 * The ParkManagerController is the controller responsible for handling
 * user interactions with a Park Manager user.
 * 
 * @author Ankit
 */
public class ParkManagerController implements UserController {
	
	/** The data controller. */
	private DataController dataController;
	
	/** The park manager view. */
	private ParkManagerView view;
	
	/** The current park manager user. */
	private ParkManager parkManager;

	/**
	 * Instantiates a new park manager controller.
	 *
	 * @param dataController the data controller
	 * @param view the view for the park manager controller
	 * @param parkManager the current park manager user
	 */
	public ParkManagerController(DataController dataController, ParkManagerView view, ParkManager parkManager) {
		this.dataController = dataController;
		this.view = view;
		this.parkManager = parkManager;
	}
	
	/* (non-Javadoc)
	 * @see controller.UserController#run()
	 */
	public void run() {
		// park manager home
		view.viewHome(parkManager.getFirstName() + " " + parkManager.getLastName());

		// continuously ask user to select an action until he/she chooses to exit
		int actionChosen = 0;
		do {
			actionChosen = view.getMenuChoice();
			if (actionChosen == ParkManagerView.PM_MENU_NEW_JOB) {
				doNewJob();
			} else if (actionChosen == ParkManagerView.PM_MENU_VIEW_UPCOMING_JOBS) {
				doViewUpcomingJobs();
			} else if (actionChosen == ParkManagerView.PM_MENU_VIEW_VOLUNTEERS) {
				doViewVolunteers();
			}
		} while (actionChosen != ParkManagerView.PM_MENU_EXIT);
	}

	/**
	 * Checks if pending jobs is not yet full. If not, asks the user
	 * to select a park and input the new job details.
	 */
	private void doNewJob() {
		if (!dataController.getData().isPendingJobsFull()) {
			// display parks managed by the user and ask user to select a park where to add a job
			List<Park> myParks = new ArrayList<>(parkManager.getParks());
			Collections.sort(myParks, new ParkComparatorByName());
			view.viewMyParks(myParks);
			if (!myParks.isEmpty()) {
				int parkNumber = view.getParkChoiceForNewJob(myParks.size());
				doCreateJob(myParks.get(parkNumber));
			}
		} else {
			view.viewSubmitJobFailLimitReached();
		}
	}
	
	/**
	 * Creates a job from user input.
	 *
	 * @param park the park where the job is
	 */
	private void doCreateJob(Park park) {
		// compute for the next job id
		int jobId = dataController.getData().getNextJobIdAvailable();
		
		// get job details from user
		String jobName = view.getNewJobName();
		Date startDate = view.getNewJobStartDate();
		Date endDate = view.getNewJobEndDate();
		int maxLightVolunteers = view.getNewJobMaxNumLightVolunteers();
		int maxMediumVolunteers = view.getNewJobMaxNumMediumVolunteers();
		int maxHeavyVolunteers = view.getNewJobMaxNumHeavyVolunteers();
		
		// check valid dates and week schedule
		if (Job.isJobScheduleValid(startDate, endDate)) {
			if (dataController.getData().isJobScheduleAvailable(startDate, endDate)) {
				// create job and save data
				Job job = new Job(jobId, startDate, endDate, park);
				job.setJobName(jobName);
				job.setMaxNumLightVolunteers(maxLightVolunteers);
				job.setMaxNumMediumVolunteers(maxMediumVolunteers);
				job.setMaxNumHeavyVolunteers(maxHeavyVolunteers);
				dataController.saveData();
				view.viewSubmitJobSuccess(jobName, dataController.getData().getUpcomingJobs().size());
			} else {
				view.viewSubmitJobFailWeekFull(jobName);
			}
		} else {
			view.viewSubmitJobFailInvalidDates(jobName);
		}
	}

	/**
	 * Displays the upcoming jobs under the parks the user manages.
	 */
	private void doViewUpcomingJobs() {
		List<Job> myUpcomingJobs = new ArrayList<>(parkManager.getAllUpcomingJobs());
		Collections.sort(myUpcomingJobs, new JobComparatorByDate());
		view.viewMyUpcomingJobs(myUpcomingJobs);
	}

	/**
	 * Asks user to select a park and a job then displays the volunteers
	 * for that job.
	 */
	private void doViewVolunteers() {
		// display parks managed by the user and ask user to select a park where to view a job
		List<Park> myParks = new ArrayList<>(parkManager.getParks());
		Collections.sort(myParks, new ParkComparatorByName());
		view.viewMyParks(myParks);
		
		if (!myParks.isEmpty()) {
			// ask user to select a park
			int parkNumber = view.getParkChoiceForViewing(myParks.size());
			Park park = myParks.get(parkNumber);
			
			// display jobs in selected park
			List<Job> myParkJobs = new ArrayList<>(park.getJobs());
			Collections.sort(myParkJobs, new JobComparatorByDate());
			view.viewMyJobs(myParkJobs);
			
			if (!myParkJobs.isEmpty()) {
				// ask user to select a job
				int jobNumber = view.getJobChoiceForViewing(myParkJobs.size());
				Job job = myParkJobs.get(jobNumber);
				
				// display volunteers in jobs
				List<Volunteer> lightVolunteers = new ArrayList<>(job.getLightVolunteers());
				List<Volunteer> mediumVolunteers = new ArrayList<>(job.getMediumVolunteers());
				List<Volunteer> heavyVolunteers = new ArrayList<>(job.getHeavyVolunteers());
				Collections.sort(lightVolunteers, new UserComparatorByName());
				Collections.sort(mediumVolunteers, new UserComparatorByName());
				Collections.sort(heavyVolunteers, new UserComparatorByName());
				view.viewVolunteers(lightVolunteers, mediumVolunteers, heavyVolunteers);
			}
		}
	}
	
}
