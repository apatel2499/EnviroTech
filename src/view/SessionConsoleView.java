package view;

import java.util.Scanner;

/**
 * The SessionConsoleView is a SessionView implementation
 * that uses the console.
 * 
 * @author qiyuan
 */
public class SessionConsoleView implements SessionView {
	
	/** The scanner used for user input. */
	public Scanner keyboard;
	
	/**
	 * Instantiates a new login view.
	 *
	 * @param keyboard the scanner for user input
	 */
	public SessionConsoleView(Scanner keyboard) {
		this.keyboard = keyboard;
	}

	/* (non-Javadoc)
	 * @see view.SessionView#getLoginEmail()
	 */
	@Override
	public String getLoginEmail() {
		System.out.println("***** Welcome to Park Jobs Management System *****");
		String emailLogin = Console.inputString(keyboard, "Enter your e-mail address to log-in: ", false);
		System.out.println();
		return emailLogin;
	}
	
	/* (non-Javadoc)
	 * @see view.SessionView#willCreateNewUser(java.lang.String)
	 */
	@Override
	public boolean getCreateNewUser(String email) {
		System.out.println("User with email '" + email + "' does not exist yet.");
		boolean createUser = Console.inputBoolean(keyboard, "Would you like to add the user (yes/no)? ");
		System.out.println();
		return createUser;
	}
	
	/* (non-Javadoc)
	 * @see view.SessionView#getUserType()
	 */
	@Override
	public int getUserType() {
		// build user type options first
		String[] userTypeOptions = new String[3];
		userTypeOptions[USER_ADMIN] = "Administrator";
		userTypeOptions[USER_PM] = "Park Manager";
		userTypeOptions[USER_VOLUNTEER] = "Volunteer";
		
		int userType = Console.inputOption(keyboard, "Select a User type: ", userTypeOptions);
		System.out.println();
		return userType;
	}
	
	/* (non-Javadoc)
	 * @see view.SessionView#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return Console.inputString(keyboard, "Please enter your first name: ", false);
	}
	
	/* (non-Javadoc)
	 * @see view.SessionView#getLastName()
	 */
	@Override
	public String getLastName() {
		return Console.inputString(keyboard, "Please enter your last name: ", false);
	}
	
	/* (non-Javadoc)
	 * @see view.SessionView#displaySuccessLogin(java.lang.String)
	 */
	@Override
	public void viewSuccessLogin(String email) {
		System.out.println("Login successful!");
		System.out.println("Logged in as: " + email + "\n");
	}
	
	/* (non-Javadoc)
	 * @see view.SessionView#displaySuccessUserCreation(java.lang.String)
	 */
	@Override
	public void viewSuccessUserCreation(String email) {
		System.out.println("User " + email + " created. \n");
	}
	
	/* (non-Javadoc)
	 * @see view.SessionView#displayExitMessage()
	 */
	@Override
	public void viewExit() {
		System.out.println("Good bye!");
	}

}