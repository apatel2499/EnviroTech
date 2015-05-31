package model;



import org.junit.runner.RunWith;
import org.junit.runners.Suite;
/**
 * Run all the JUnit tests.
 * 
 * @author Ankit
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({ AdministratorTest.class, HandlerTest.class, VolunteerTest.class, 
	ParkManagerTest.class, JobTest.class, UserTest.class, ParkTest.class , 
	JobComparatorByDateTest.class, ParkComparatorByNameTest.class, 
	UserComparatorByNameTest.class, DateUtilTest.class})

public class RunAllTest {

}