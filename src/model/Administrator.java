package model;

/**
 * The concrete implementation for an Administrator.
 */
public class Administrator extends User {

	/** The constant serialVersionUID of the Administrator class. */
	private static final long serialVersionUID = 6248300435705959684L;

	/**
	 * Instantiates a new administrator without any attributes.
	 */
	public Administrator() {
		super();
	}

	/**
	 * Instantiates a new administrator with the given attributes.
	 *
	 * @param firstName the administrator's first name
	 * @param lastName the administrator's last name
	 * @param emailAddress administrator's the email address
	 */
	public Administrator(String firstName, String lastName, String emailAddress) {
		super(firstName, lastName, emailAddress);
	}
	
}