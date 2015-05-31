package controller;

import model.Administrator;
import model.ParkManager;
import model.User;
import model.Volunteer;
import view.AdministratorView;
import view.ParkManagerView;
import view.SessionView;
import view.VolunteerView;

/**
 * The SessionController is the controller class used to handle user interaction
 * during the session view.
 * 
 * @author Ankit
 */
public class SessionController {
	
	/** The data controller. */
	private DataController dataController;
	
	/** The view during login/sign up. */
	private SessionView sessionView;
	
	/** The view for administrator users. */
	private AdministratorView adminView;
	
	/** The view for park manager users. */
	private ParkManagerView pmView;
	
	/** The view for volunteer users. */
	private VolunteerView volunteerView;
	
	/**
	 * Instantiates a new session controller.
	 *
	 * @param dataController the data controller
	 * @param sessionView the view for the session controller
	 * @param adminView the view for administrator users
	 * @param pmView the view for park manager users
	 * @param volunteerView the view volunteer users
	 */
	public SessionController(DataController dataController, SessionView sessionView,
			AdministratorView adminView, ParkManagerView pmView, VolunteerView volunteerView) {
		this.dataController = dataController;
		this.sessionView = sessionView;
		this.adminView = adminView;
		this.pmView = pmView;
		this.volunteerView = volunteerView;
	}
	
	/**
	 * Runs the controller by asking user to log in or sign up if email does not exist yet,
	 * then proceeds with the corresponding view/controller depending on the user type.
	 */
	public void run() {
		String emailLogin = sessionView.getLoginEmail().toLowerCase();
		User user = dataController.getData().getUserByEmail(emailLogin);
		if (user != null) {
			doLogin(user);
		} else {
			if (sessionView.getCreateNewUser(emailLogin)) {
				doSignup(emailLogin);
			}
		}
		
		// print exit message when the application ends
		sessionView.viewExit();
	}
	
	/**
	 * Logs in the user in the system and proceeds with the corresponding view
	 * and controller depending on the user type.
	 *
	 * @param user the user to log in
	 */
	private void doLogin(User user) {
		UserController nextController;
		
		if (user instanceof Administrator) {
			nextController = new AdministratorController(
					dataController, adminView, (Administrator) user);
		} else if (user instanceof ParkManager) {
			nextController = new ParkManagerController(
					dataController, pmView, (ParkManager) user);
		} else {
			nextController = new VolunteerController(
					dataController, volunteerView, (Volunteer) user);
		}
		
		sessionView.viewSuccessLogin(user.getEmailAddress());
		nextController.run();
	}
	
	private void doSignup(String emailLogin) {
		User user = null;
		
		// ask user to select a user type
		int userType = sessionView.getUserType();
		
		// ask user for name
		String firstName = sessionView.getFirstName();
		String lastName = sessionView.getLastName();
		
		// create user depending on type
		if (userType == SessionView.USER_ADMIN) {
			user = new Administrator(firstName, lastName, emailLogin);
		} else if (userType == SessionView.USER_PM) {
			user = new ParkManager(firstName, lastName, emailLogin);
		} else {
			user = new Volunteer(firstName, lastName, emailLogin);
		}
		
		// save new user
		dataController.getData().addUser(user);
		dataController.saveData();
		sessionView.viewSuccessUserCreation(emailLogin);
		
		// login user
		doLogin(user);
	}

}
