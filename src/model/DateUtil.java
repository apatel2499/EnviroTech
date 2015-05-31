package model;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * The DateUtil is a utility class for the Date class.
 * 
 * @author Ankit
 */
public class DateUtil {

	/**
	 * Checks if two dates are equal, regardless of the time.
	 *
	 * @param date1 the first date to compare
	 * @param date2 the second date to compare
	 * @return true if the dates are equal regardless of the time, false otherwise
	 */
	public static boolean dateEqualsIgnoreTime(Date date1, Date date2) {
		return removeTime(date1).equals(removeTime(date2));
	}
	
	/**
	 * Removes the time portion of the given date.
	 *
	 * @param date the date
	 * @return the date without the time portion (all elements set to zero)
	 */
	public static Date removeTime(Date date) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
	    calendar.set(Calendar.MINUTE, 0);
	    calendar.set(Calendar.SECOND, 0);
	    calendar.set(Calendar.MILLISECOND, 0);
	    return calendar.getTime();
	}
	
	/**
	 * Checks if a given date is found in between (inclusive) the given
	 * bounds, ignoring time.
	 *
	 * @param date the date to check
	 * @param leftBound the left bound date
	 * @param rightBound the right bound date
	 * @return true if the date is within the bound, false otherwise.
	 */
	public static boolean inBoundsIgnoreTime(Date date, Date leftBound, Date rightBound) {
		date = removeTime(date);
		leftBound = removeTime(leftBound);
		rightBound = removeTime(rightBound);
		return (date.after(leftBound) || date.equals(leftBound)) 
				&& (date.before(rightBound) || date.equals(rightBound));
	}
	
}
