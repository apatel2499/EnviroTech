package view;

import java.util.List;
import java.util.Scanner;

import model.Volunteer;

/**
 * The AdministratorConsoleView is an AdministratorView implementation
 * that uses the console.
 * 
 * @author Ankit
 */
public class AdministratorConsoleView implements AdministratorView {
	
	/** The scanner for user input. */
	private Scanner keyboard;

	/**
	 * Instantiates a new administrator view.
	 *
	 * @param keyboard the scanner for user input
	 */
	public AdministratorConsoleView(Scanner keyboard) {
		this.keyboard = keyboard;
	}

	/* (non-Javadoc)
	 * @see view.AdministratorView#viewWelcome(java.lang.String)
	 */
	@Override
	public void viewHome(String name) {
		System.out.println("Ahoy, [ADMINISTRATOR] " + name + "! \n");
	}

	/* (non-Javadoc)
	 * @see view.AdministratorView#getMenuChoice()
	 */
	@Override
	public int getMenuChoice() {
		// prepare action menu options
		String[] actionOptions = new String[A_NUM_MENU];
		actionOptions[A_MENU_SEARCH] = "Search for volunteers by last name";
		actionOptions[A_MENU_EXIT] = "Exit";
		
		// ask and return chosen action
		int actionChosen = Console.inputOption(keyboard, "=============== MENU ===============", actionOptions);
		System.out.println();
		return actionChosen;
	}

	/* (non-Javadoc)
	 * @see view.AdministratorView#getLastNameSearch()
	 */
	@Override
	public String getLastNameSearch() {
		String lastName = Console.inputString(keyboard, "Please enter the last name of the volunteer to search: ", false);
		System.out.println();
		return lastName;
	}

	/* (non-Javadoc)
	 * @see view.AdministratorView#viewLastNameSearchResults(java.util.TreeSet)
	 */
	@Override
	public void viewLastNameSearchResults(List<Volunteer> results) {
		if (results.isEmpty()) {
			System.out.println("No volunteers found.");
		} else {
			System.out.println("Results: ");
			for (int i = 1; i <= results.size(); i++) {
				Volunteer volunteer = results.get(i - 1);
				System.out.println(i + ") " + volunteer.getLastName() + ", " 
						+ volunteer.getFirstName() + " ("
						+ volunteer.getEmailAddress() + ")");
			}
		}
		System.out.println();
	}
	
}
