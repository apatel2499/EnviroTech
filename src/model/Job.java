package model;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeSet;

/** Author: Leda Rezanejad **/

/**
* Job represents a job for a park.
*/

public class Job implements Serializable, Comparable<Job> {

        /**
	 * 
	 */
	private static final long serialVersionUID = 7064485507053168372L;

		private static final int MAX_ALLOWED_STARTDATE = 90;

        /** The job id. */
        private int jobId;

        /** The park. */
        private Park park;

        /** The start date. */
        private Date jobStartDate;

        /** The end date. */
        private Date jobEndDate;

        /** The set of light volunteers. */
        private TreeSet<Volunteer> lightVolunteers;

        /** The set of medium volunteers. */
        private TreeSet<Volunteer> mediumVolunteers;

        /** The set of heavy volunteers. */
        private TreeSet<Volunteer> heavyVolunteers;

        /** The maximum number light volunteers. */
        private int jobMaxNumLightVolunteers;

        /** The maximum number medium volunteers. */
        private int jobMaxNumMediumVolunteers;

        /** The maximum number heavy volunteers. */
        private int jobMaxNumHeavyVolunteers;

        /**
         * Instantiates a new job.
         */
        public Job(int jobId, Date jobStartDate, Park park) {

                lightVolunteers = new TreeSet<Volunteer>();
                mediumVolunteers = new TreeSet<Volunteer>();
                heavyVolunteers = new TreeSet<Volunteer>();

                this.jobId = jobId;
                this.park = park;
                this.park.addJob(this);
        }

        /**
         * Sets the park.
         *
         * @param park the new park
         */
        public void setPark(Park park) {
                this.park = park;
        }

        /**
         * Sets the start date.
         *
         * @param startDate the new start date
         */
        public void setStartDate(Date startDate) {
                startDate = jobStartDate;
        }

        /**
         * Sets the end date.
         *
         * @param endDate the new end date
         */
        public void setEndDate(Date endDate) {
                endDate = jobEndDate;
        }

        /**
         * Sets the maximum number  light volunteers.
         *
         * @param maxNumLightVolunteers the new maximum number  light volunteers
         */
        public void setMaxNumLightVolunteers(int maxNumLightVolunteers) {
                // set max number of light volunteers
                maxNumLightVolunteers = jobMaxNumLightVolunteers;

        }

        /**
         * Sets the maximum number  medium volunteers.
         *
         * @param maxNumMediumVolunteers the new maximum number  medium volunteers
         */
        public void setMaxNumMediumVolunteers(int maxNumMediumVolunteers) {
                // set max number of medium volunteers
                maxNumMediumVolunteers = jobMaxNumMediumVolunteers;
        }

        /**
         * Sets the maximum number  heavy volunteers.
         *
         * @param maxNumHeavyVolunteers the new maximum number  heavy volunteers
         */
        public void setMaxNumHeavyVolunteers(int maxNumHeavyVolunteers) {
                // set max number of heavy volunteers
                maxNumHeavyVolunteers = jobMaxNumHeavyVolunteers;
        }

        /**
         * Gets the park.
         *
         * @return the park
         */
        public Park getPark() {
                // return park
                System.out.println("Getting Park");
                return park;
        }

        /**
         * Gets the start date.
         *
         * @return the start date
         */
        public Date getStartDate() {
                // return start date
                return jobStartDate;
        }

        /**
         * Gets the end date.
         *
         * @return the end date
         */
        public Date getEndDate() {
                // return end date
                return jobEndDate;
        }

         /**
	 * Gets all light volunteers
	 * 
	 * @return the tree set of light volunteers
	 */
	public TreeSet<Volunteer> getLightVolunteers() {
		return lightVolunteers;
	}

	/**
	 * Gets all medium volunteers
	 * 
	 * @return the tree set of medium volunteers
	 */
	public TreeSet<Volunteer> getMediumVolunteers() {
		return mediumVolunteers;
	}

	/**
	 * Gets all heavy volunteers 
	 * 
	 * @return the tree set of heavy volunteers
	 */
	public TreeSet<Volunteer> getHeavyVolunteers() {
		return heavyVolunteers;
	}

        /**
         * Gets the maximum number light volunteers.
         *
         * @return the maximum number light volunteers
         */
        public int getMaxNumLightVolunteers() {
                // return maximum number of light volunteers
                return jobMaxNumLightVolunteers;
        }

        /**
         * Gets the maximum number medium volunteers.
         *
         * @return the maximum number medium volunteers
         */
        public int getMaxNumMediumVolunteers() {
                // return maximum number of medium volunteers
                return jobMaxNumMediumVolunteers;
        }

        /**
         * Gets the maximum number heavy volunteers.
         *
         * @return the maximum number heavy volunteers
         */
        public int getMaxNumHeavyVolunteers() {
                // return maximum number of heavy volunteers
                return jobMaxNumHeavyVolunteers;
        }

        /**
         * Checks if set of light volunteers is full.
         *
         * @return true, if set of light volunteers is full
         */
        public boolean isLightVolunteersFull() {
                // check if size of light volunteer set is >= to the max number of light volunteers
                return (lightVolunteers.size() >= jobMaxNumLightVolunteers);
        }

        /**
         * Checks if set of medium volunteers is full.
         *
         * @return true, if set of medium volunteers is full
         */
        public boolean isMediumVolunteersFull() {
                // check if size of medium volunteer set is >= to the max number of light volunteers
                return (mediumVolunteers.size() >= jobMaxNumMediumVolunteers);
        }

        /**
         * Checks if set of heavy volunteers is full.
         *
         * @return true, if set of heavy volunteers is full
         */
        public boolean isHeavyVolunteersFull() {
                // check if size of heavy volunteer set is >= to the max number of light volunteers
                return (heavyVolunteers.size() >= jobMaxNumHeavyVolunteers);
        }

        /**
         * Adds the light volunteer.
         *
         * @param volunteer the volunteer
         * @return true, if successful
         */
        public boolean addLightVolunteer(Volunteer volunteer) {
                // If volunteer is NOT in any of the set of volunteers:
                // Add to light volunteers set and return true
                // Return false

                if(!lightVolunteers.contains(volunteer)){
                        return lightVolunteers.add(volunteer);
                }else {
                        return false;
                }

        }

        /**
         * Adds the medium volunteer.
         *
         * @param volunteer the volunteer
         * @return true, if successful
         */
        public boolean addMediumVolunteer(Volunteer volunteer) {
                // If volunteer is NOT in any of the set of volunteers:
                // Add to medium volunteers set and return true
                // Return false

                if(!mediumVolunteers.contains(volunteer)){
                        return mediumVolunteers.add(volunteer);
                }else {
                        return false;
                }
        }

        /**
         * Adds the heavy volunteer.
         *
         * @param volunteer the volunteer
         * @return true, if successful
         */
        public boolean addHeavyVolunteer(Volunteer volunteer) {
                // If volunteer is NOT in any of the set of volunteers:
                // Add to heavy volunteers set and return true
                // Return false

                if(!heavyVolunteers.contains(volunteer)){
                        return heavyVolunteers.add(volunteer);
                }else{
                        return false;
                }
        }

        /**
         * Gets the all volunteers.
         *
         * @return the all volunteers
         */
        public TreeSet<Volunteer> getAllVolunteers() {
                // returns a union set of the light, medium, and heavy volunteers set
                TreeSet<Volunteer> allVolunteers = new TreeSet<Volunteer>();

                allVolunteers.addAll(lightVolunteers);
                allVolunteers.addAll(mediumVolunteers);
                allVolunteers.addAll(heavyVolunteers);

                return allVolunteers;
        }

        @Override
        public int hashCode() {
                final int prime = 31;
                int result = 1;
                result = prime * result + jobId;
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
                Job other = (Job) obj;
                if (jobId != other.jobId)
                        return false;
                return true;
        }




        /* (non-Javadoc)
         * @see java.lang.Comparable#compareTo(java.lang.Object)
         */
        @Override
        public int compareTo(Job job) {
                // compares by start date so any tree set collection of jobs are sorted by start date

                if(this.equals(job))
                        return 0;
                else if (this.jobStartDate.hashCode() > job.jobStartDate.hashCode())
                        return 1;
                else
                        return -1;
        }

        /**
         * Checks if is job schedule valid.
         *
         * @param startDate the start date
         * @param endDate the end date
         * @return true, if is job schedule valid
         */
        public static boolean isJobScheduleValid(Date startDate, Date endDate) {
                // checks if start date comes first before end date
                // checks if the number of days between the start and end date is not more than 2
                // checks if start date is not in the past and is not starting on more than 3 months in the future

                Date currentDate = new Date();
                int daysBetweenCurrentAndStartDate = (int)((startDate.getTime() - currentDate.getTime())/(1000 * 60 * 60 * 24));
                int daysBetweenStartAndEndDate = (int)((endDate.getTime() - startDate.getTime())/(1000 * 60 * 60 * 24));


                if(daysBetweenCurrentAndStartDate >= 0 && daysBetweenCurrentAndStartDate <= MAX_ALLOWED_STARTDATE &&
                                daysBetweenStartAndEndDate >= 0 && daysBetweenStartAndEndDate <= 2){
                        return true;
                }else{
                        return false;
                }
        }
}
