package model;

import java.util.Comparator;

/**
 * The JobComparatorByDate is a comparator for comparing jobs by their start/end dates.
 * 
 * @author Ankit
 */
public class JobComparatorByDate implements Comparator<Job> {

	/* (non-Javadoc)
	 * @see java.util.Comparator#compare(java.lang.Object, java.lang.Object)
	 */
	@Override
	public int compare(Job job1, Job job2) {
		if (!DateUtil.dateEqualsIgnoreTime(job1.getStartDate(), job2.getStartDate())) {
			return job1.getStartDate().compareTo(job2.getStartDate());
		} else {
			return job1.getEndDate().compareTo(job2.getEndDate());
		}
	}
	
	

}
