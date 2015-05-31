package view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

/**
 * The Console is a utility class that helps in asking user for inputs
 * of different types.
 * 
 * @author Leda
 */
public class Console {

	/** The message shown whenever the user enters an invalid input. */
	private static final String INVALID_INPUT_MSG = "Invalid input! Try again.";

	/**
	 * Asks user a yes/no question.
	 *
	 * @param keyboard the scanner for user input
	 * @param message the message to ask the user
	 * @return true if the user enters 'yes', false if the user enters 'no'
	 */
	public static boolean inputBoolean(Scanner keyboard, String message) {
		while (true) {
			System.out.print(message);
			String inputStr = keyboard.nextLine().trim();
			if (inputStr.equalsIgnoreCase("yes")) {
				return true;
			} else if (inputStr.equalsIgnoreCase("no")) {
				return false;
			} else {
				System.out.println(INVALID_INPUT_MSG);
			}
		}
	}

	/**
	 * Asks user to input an integer.
	 *
	 * @param keyboard the scanner used for user input
	 * @param message the message to ask the user
	 * @return the integer entered by the user
	 */
	public static int inputInteger(Scanner keyboard, String message, int minValue, int maxValue) {
		while (true) {
			System.out.print(message);
			String inputStr = keyboard.nextLine().trim();
			try {
				int input = Integer.parseInt(inputStr);
				if (input >= minValue && input <= maxValue) {
					return input;
				}
			} catch (NumberFormatException e) {}
			System.out.println(INVALID_INPUT_MSG);
		}
	}

	/**
	 * Asks user to enter a string.
	 *
	 * @param keyboard the scanner used for user input
	 * @param message the message to ask the user
	 * @param allowEmpty the boolean to mark if user is allowed to enter an empty string
	 * @return the string entered by the user
	 */
	public static String inputString(Scanner keyboard, String message, boolean allowEmpty) {
		while (true) {
			System.out.print(message);
			String inputStr = keyboard.nextLine().trim();
			if (!inputStr.isEmpty() || allowEmpty) {
				return inputStr;
			} else {
				System.out.println(INVALID_INPUT_MSG + " Input must not be empty.");
			}
		}
	}

	/**
	 * Asks user to select an option from a list of options.
	 *
	 * @param keyboard the scanner used for user input
	 * @param message the message to ask the user
	 * @return the index of the option selected by the user
	 */
	public static int inputOption(Scanner keyboard, String message, String[] options) {
		// verify options input
		if (options == null || options.length == 0) {
			throw new IllegalArgumentException("Options must not be null nor empty");
		}

		// print message and options first
		System.out.println(message);
		for (int i = 0; i < options.length; i++) {
			System.out.println((i + 1) + ") " + options[i]);
		}

		// ask user for number of choice
		return inputInteger(keyboard, "Chosen option number: ", 1, options.length) - 1;
	}
	
	/**
	 * Asks user to enter a date.
	 *
	 * @param keyboard the scanner used for user input
	 * @param message the message to ask the user
	 * @param dateFormat the date format for parsing the date
	 * @return the date entered by the user
	 */
	public static Date inputDate(Scanner keyboard, String message, SimpleDateFormat dateFormat) {
		while (true) {
			System.out.print(message);
			try {
				return dateFormat.parse(keyboard.nextLine().trim());
			} catch (ParseException e) {
				System.out.println(INVALID_INPUT_MSG);
			}
		}
	}

}
