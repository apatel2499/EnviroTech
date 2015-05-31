package controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import model.Administrator;
import model.UserComparatorByName;
import model.Volunteer;
import view.AdministratorView;

/**
 * The AdministratorController is the controller responsible for handling
 * user interactions with an Administrator user.
 * 
 * @author Ankit
 */
public class AdministratorController implements UserController {
	
	/** The data controller. */
	private DataController dataController;
	
	/** The administrator view. */
	private AdministratorView view;
	
	/** The current administrator user. */
	private Administrator administrator;

	/**
	 * Instantiates a new administrator controller.
	 *
	 * @param dataController the data controller
	 * @param view the view for the administrator controller
	 * @param administrator the current administrator user
	 */
	public AdministratorController(DataController dataController, AdministratorView view, Administrator administrator) {
		this.dataController = dataController;
		this.view = view;
		this.administrator = administrator;
	}
	
	/* (non-Javadoc)
	 * @see controller.UserController#run()
	 */
	public void run() {
		// administrator home
		view.viewHome(administrator.getFirstName() + " " + administrator.getLastName());
		
		// continuously ask user to select an action until he/she chooses to exit
		int actionChosen = 0;
		do {
			actionChosen = view.getMenuChoice();
			if (actionChosen == AdministratorView.A_MENU_SEARCH) {
				doSearch();
			}
		} while (actionChosen != AdministratorView.A_MENU_EXIT);
	}
	
	/**
	 * Gets the last name of volunteer to search and displays the result.
	 */
	private void doSearch() {
		String lastName = view.getLastNameSearch();
		List<Volunteer> results = new ArrayList<Volunteer>(dataController.getData().getVolunteersByLastName(lastName));
		Collections.sort(results, new UserComparatorByName());
		view.viewLastNameSearchResults(results);
	}
	
}