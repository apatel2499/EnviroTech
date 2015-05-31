package model;

import java.util.Comparator;

/**
 * The ParkComparatorByName is a comparator for comparing parks by their park names.
 * 
 * @author Ankit
 */
public class ParkComparatorByName implements Comparator<Park> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Park park1, Park park2) {
		return park1.getParkName().compareTo(park2.getParkName());
	}

}
