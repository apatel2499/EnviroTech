package model;

import java.util.Comparator;

/**
 * The UserComparatorByName is a comparator for comparing users by their last names,
 * then their first names, then their email addresses.
 * 
 * @author Ankit
 */
public class UserComparatorByName implements Comparator<User> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(User user1, User user2) {
		if(!user1.getLastName().equals(user2.getLastName())) {
			return user1.getLastName().compareTo(user2.getLastName());
		} else if(!user1.getFirstName().equals(user2.getFirstName())) {
			return user1.getFirstName().compareTo(user2.getFirstName());
		} else {
			return user1.getEmailAddress().compareTo(user2.getEmailAddress());
		}
	}

}
