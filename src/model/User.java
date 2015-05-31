package model;


import java.io.Serializable;



/**
 * The abstract class for all types of users.
 */
public abstract class User implements Serializable, Comparable<User> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -5431874612400360264L;

	/** The user's first name. */
	private String firstName;
	
	/** The user's last name. */
	private String lastName;
	
	/** The user's email address. */
	private String emailAddress;
	
	/**
	 * Instantiates a new user with no first name, last name, nor email address.
	 */
	public User() {
	}

	/**
	 * Instantiates a new user with the given attributes.
	 *
	 * @param firstName the user's first name
	 * @param lastName the user's last name
	 * @param emailAddress the user's email address
	 */
	public User(String firstName, String lastName, String emailAddress) {
		// set the attributes
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailAddress = emailAddress.toLowerCase();
	}

	/**
	 * Sets the user's first name.
	 *
	 * @param firstName the user's new first name
	 */
	public void setFirstName(String firstName) {
		// set first name
		this.firstName = firstName;
	}

	/**
	 * Sets the user's last name.
	 *
	 * @param lastName the user's new last name
	 */
	public void setLastName(String lastName) {
		// set last name
		this.lastName = lastName;
	}

	/**
	 * Sets the user's email address.
	 *
	 * @param emailAddress the user's new email address
	 */
	public void setEmailAddress(String emailAddress) {
		// set email
		this.emailAddress = emailAddress.toLowerCase();
	}

	/**
	 * Gets the user's first name.
	 *
	 * @return the user's first name
	 */
	public String getFirstName() {
		// return first name
		return firstName;
	}

	/**
	 * Gets the user's last name.
	 *
	 * @return the user's last name
	 */
	public String getLastName() {
		// return last name
		return lastName;
	}

	/**
	 * Gets the user's email address.
	 *
	 * @return the email address
	 */
	public String getEmailAddress() {
		// return email address
		return emailAddress;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((emailAddress == null) ? 0 : emailAddress.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (emailAddress == null) {
			if (other.emailAddress != null)
				return false;
		} else if (!emailAddress.equals(other.emailAddress))
			return false;
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(User other) {
		// If this last name is not equal to other user's last name:
		//		Return this.lastName.compareTo(other.lastName)
		// Else If this first name is not equal to other user's first name:
		//		Return this.firstName.compareTo(other.firstName)
		// Else:
		//		Return this.emailAddress.compareTo(other.emailAddress)
		/*
		if(!this.lastName.equals(other.lastName)) {
			return this.lastName.compareTo(other.lastName);
		} else if(!this.firstName.equals(other.firstName)) {
			return this.firstName.compareTo(other.firstName);
		} else {
			return this.emailAddress.compareTo(other.emailAddress);
		}*/
		
		// compare by email address only instead
		return this.emailAddress.compareTo(other.emailAddress);
	}
	
}
