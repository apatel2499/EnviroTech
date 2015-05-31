package view;

/**
 * The SessionView is the interface for all view classes when the user
 * logs in/signs up/exits.
 * 
 * @author qiyuan
 */
public interface SessionView {

	/** The constant integer representing an administrator type user. */
	public static final int USER_ADMIN = 0;
	
	/** The constant integer representing a park manager type user. */
	public static final int USER_PM = 1;
	
	/** The constant integer representing a volunteer type user. */
	public static final int USER_VOLUNTEER = 2;

	/**
	 * Returns the input of the user for the email login/sign-up.
	 *
	 * @return the email address entered by the user
	 */
	public abstract String getLoginEmail();

	/**
	 * Returns the input of the user if he/she wants to create a new user 
	 * if the entered email address does not exist yet.
	 *
	 * @param email the email addressed entered by the user during login
	 * @return true if user selected to create a new user, false otherwise
	 */
	public abstract boolean getCreateNewUser(String email);

	/**
	 * Returns the input of the user regarding the type of user he/she
	 * is signing up for.
	 *
	 * @return 0 for Administrator, 1 for Park Manager, and 2 for Volunteer
	 */
	public abstract int getUserType();

	/**
	 * Returns the input of the user's first name for signing up.
	 *
	 * @return the user's first name
	 */
	public abstract String getFirstName();

	/**
	 * Returns the input of the user's last name for signing up.
	 *
	 * @return the user's last name
	 */
	public abstract String getLastName();

	/**
	 * Updates the view when user successfully logs in.
	 *
	 * @param email the email address used by the logged in user
	 */
	public abstract void viewSuccessLogin(String email);

	/**
	 * Updates the view when the user successfully signs up.
	 *
	 * @param email the email address used by the newly signed up user
	 */
	public abstract void viewSuccessUserCreation(String email);

	/**
	 * Updates the view during exit.
	 */
	public abstract void viewExit();

}