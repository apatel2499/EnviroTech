package model;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

/**
 * The DateUtilTest is a JUnit test class for testing the DateUtil class.
 * 
 * @author Ankit
 */
public class DateUtilTest {

	/**
	 * Test date equals ignore time.
	 * @throws ParseException 
	 */
	@Test
	public void testDateEqualsIgnoreTime() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date date1 = dateFormat.parse("1/1/2016 00:30");
		Date date2 = dateFormat.parse("1/1/2016 12:30");
		assertTrue(DateUtil.dateEqualsIgnoreTime(date1, date2));
	}

	/**
	 * Test remove time.
	 * @throws ParseException 
	 */
	@Test
	public void testRemoveTime() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss:SS");
		Date date1 = dateFormat.parse("1/1/2016 00:30:00:00");
		Date date2 = dateFormat.parse("1/1/2016 12:30:00:00");
		Date goal = dateFormat.parse("1/1/2016 00:00:00:00");
		date1 = DateUtil.removeTime(date1);
		date2 = DateUtil.removeTime(date2);
		assertEquals(date1, goal);
		assertEquals(date2, goal);
	}
	
	/**
	 * Test in bounds ignore time.
	 *
	 * @throws ParseException the parse exception
	 */
	@Test
	public void testInBoundsIgnoreTime() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		Date leftBound = dateFormat.parse("1/1/2016 12:00");
		Date rightBound = dateFormat.parse("1/5/2016 12:00");
		
		Date date1 = dateFormat.parse("1/1/2016 10:30");
		assertTrue(DateUtil.inBoundsIgnoreTime(date1, leftBound, rightBound));
		
		Date date2 = dateFormat.parse("1/2/2016 10:30");
		assertTrue(DateUtil.inBoundsIgnoreTime(date2, leftBound, rightBound));
		
		Date date3 = dateFormat.parse("1/5/2016 17:30");
		assertTrue(DateUtil.inBoundsIgnoreTime(date3, leftBound, rightBound));
		
		Date date4 = dateFormat.parse("12/31/2015 23:59");
		assertFalse(DateUtil.inBoundsIgnoreTime(date4, leftBound, rightBound));
		
		Date date5 = dateFormat.parse("1/6/2016 00:00");
		assertFalse(DateUtil.inBoundsIgnoreTime(date5, leftBound, rightBound));
	}

}
