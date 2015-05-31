package main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Scanner;

import model.Administrator;
import model.Handler;
import model.Job;
import model.Park;
import model.ParkManager;
import model.Volunteer;
import view.Console;

/**
 * The SetupTestData is a helper class that saves a sample set of data into the data file.
 * This will be useful during startup if you want the system to be pre-populated. It
 * OVERWRITES the data file.
 * 
 * <br>
 * <br>

 * This class creates:
 * <ol>
 * <li>2 Administrators</li>
 * <li>3 Park Managers</li>
 * <li>4 Park Managers</li>
 * <li>10 Parks (Total)</li>
 * <li>20 Upcoming Jobs</li>
 * </ol>

 * Note that all volunteers have not signed up for any job yet.
 * 
 * @author Group 7
 */
public class SetupTestData {

	public static void main(String[] args) throws Exception {
		// confirm overwrite
		Scanner keyboard = new Scanner(System.in);
		if (!Console.inputBoolean(keyboard, "Are you sure you want to overwrite the data file (yes/no)? ")) {
			keyboard.close();
			return;
		}
		keyboard.close();
		
		// create 2 test administrators
		Administrator admin1 = new Administrator("Ankit", "Patel", "admin1@sample.com");
		Administrator admin2 = new Administrator("Carleton", "Ericson", "admin2@sample.com");
		
		// create 3 test park managers
		ParkManager pm1 = new ParkManager("Ankit", "Patel", "pm1@sample.com");
		ParkManager pm2 = new ParkManager("Myles", "Allsopp", "pm2@sample.com");
		ParkManager pm3 = new ParkManager("Jayden", "Moors", "pm3@sample.com");
		
		// create 4 test volunteers
		Volunteer volunteer1 = new Volunteer("Ankit", "Patel", "vol1@sample.com");
		Volunteer volunteer2 = new Volunteer("Alanna", "Treloar", "vol2@sample.com");
		Volunteer volunteer3 = new Volunteer("Naomi", "Wynne", "vol3@sample.com");
		Volunteer volunteer4 = new Volunteer("Connie", "Braddock", "vol4@sample.com");

		// create total of 10 parks for park managers
		Park park1 = new Park(0, "Seattle Meadows", pm1);
		Park park2 = new Park(1, "Rain City", pm1);
		Park park3 = new Park(2, "Owlfeather Grounds", pm1);
		Park park4 = new Park(3, "Fairview Park", pm2);
		Park park5 = new Park(4, "Summer Gardens", pm2);
		Park park6 = new Park(5, "Bronze Arch Meadows", pm2);
		Park park7 = new Park(6, "Fall Garden", pm3);
		Park park8 = new Park(7, "Winter Garden", pm3);
		Park park9 = new Park(8, "Sunrise Grounds", pm3);
		Park park10 = new Park(9, "River View Gardens", pm3);
		Park[] parks = {park1, park2, park3, park4, park5, park6, park7, park8, park9, park10};

		Calendar calendar = new GregorianCalendar();
		calendar.add(Calendar.DATE, 2);
		
		// add 2 jobs to each park
		int nextPark = 0;
		for (int i = 0; i < (2 * parks.length); i++) {
			Job job = new Job(i, calendar.getTime(), calendar.getTime(), parks[nextPark]);
			job.setJobName("Some Filler Job");
			job.setMaxNumLightVolunteers(5);
			job.setMaxNumMediumVolunteers(5);
			job.setMaxNumHeavyVolunteers(5);
			
			calendar.add(Calendar.DATE, 2);
			if (i != 0 && i % 2 != 0) {
				nextPark++;
			}
		}
		
		// create a handler
		Handler handler = new Handler();
		
		// add the users to the handler
		handler.addUser(admin1);
		handler.addUser(admin2);
		handler.addUser(pm1);
		handler.addUser(pm2);
		handler.addUser(pm3);
		handler.addUser(volunteer1);
		handler.addUser(volunteer2);
		handler.addUser(volunteer3);
		handler.addUser(volunteer4);
		
		// save the handler to the persistent data file
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(ConsoleMainApplication.DATA_FILENAME)));
		oos.writeObject(handler);
		oos.close();
	}

}
