package view;

import java.util.List;

import model.Volunteer;

/**
 * The AdministratorView is the interface for all view classes for 
 * an administrator user.
 * 
 * @author Ankit
 */
public interface AdministratorView {
	
	/** The number of menu items for an administrator. */
	public static final int A_NUM_MENU = 2;
	
	/** The constant integer representing the 'search volunteer by last name' action. */
	public static final int A_MENU_SEARCH = 0;
	
	/** The constant integer representing the 'exit' action. */
	public static final int A_MENU_EXIT = 1;
	
	/**
	 * Updates the view to the home view of an administrator user.
	 *
	 * @param name the name of the administrator
	 */
	public abstract void viewHome(String name);
	
	/**
	 * Returns the action the administrator chose in the menu.
	 *
	 * @return 0 if user wants to search for a volunteer by last name,
	 * 1 if user wants to exit
	 */
	public abstract int getMenuChoice();
	
	/**
	 * Returns the last name entered by the user when searching for a volunteer.
	 *
	 * @return the last name of the volunteer/s the user wants to search
	 */
	public abstract String getLastNameSearch();
	
	/**
	 * Updates the view to display results when searching for volunteers by last name.
	 *
	 * @param results the results of the search
	 */
	public abstract void viewLastNameSearchResults(List<Volunteer> results);

}