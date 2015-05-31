package model;

import static org.junit.Assert.*;
import model.User;
import model.Volunteer;

import org.junit.Test;


public class UserTest {
	
	@Test
	public void testCompareTo() {
		
		User user = new Volunteer("Jane", "Doe", "janedoe@gmail.com");
		User otherUser = new Volunteer("John", "Doe", "johndoe@gmail.com");
		assertEquals(user.getEmailAddress().compareTo(otherUser.getEmailAddress()), user.compareTo(otherUser));
	}

}
