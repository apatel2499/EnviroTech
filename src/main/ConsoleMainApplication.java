package main;

import java.util.Scanner;

import controller.SessionController;
import controller.DataController;
import view.AdministratorConsoleView;
import view.AdministratorView;
import view.DataView;
import view.ParkManagerConsoleView;
import view.ParkManagerView;
import view.SessionConsoleView;
import view.DataConsoleView;
import view.SessionView;
import view.VolunteerConsoleView;
import view.VolunteerView;

/**
 * The ConsoleMainApplication is the main class for starting the application
 * that runs through a console.
 * 
 * @author Group 7
 */
public class ConsoleMainApplication {

	/** The filename of the data file. */
	public static final String DATA_FILENAME = "PJMS.dat";
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		// create scanner for user input throughout the application use
		Scanner keyboard = new Scanner(System.in);
		
		// create all the views via console
		DataView dataView = new DataConsoleView();
		SessionView sessionView = new SessionConsoleView(keyboard);
		AdministratorView adminView = new AdministratorConsoleView(keyboard);
		ParkManagerView pmView = new ParkManagerConsoleView(keyboard);
		VolunteerView volunteerView = new VolunteerConsoleView(keyboard);
		
		// create a data controller and load data
		DataController dataController = new DataController(dataView, DATA_FILENAME);
		dataController.loadData();
		
		// create a new controller and proceed from there
		SessionController sessionController = new SessionController(dataController, 
				sessionView, adminView, pmView, volunteerView);
		sessionController.run();

		// close scanner for user input
		keyboard.close();
	}
	
}
