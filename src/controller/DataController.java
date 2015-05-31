package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import model.Handler;
import view.DataView;

/**
 * The DataController is the controller used for loading and saving data.
 * 
 * @author Leda
 */
public class DataController {
	
	/** The data view. */
	private DataView view;
	
	/** The handler model where all the users are stored. */
	private Handler model;
	
	/** The filename of the file where the data is stored. */
	private String dataFilename; 

	/**
	 * Instantiates a new data controller.
	 *
	 * @param view the view for displaying any load/save error messages
	 * @param dataFilename the filename where to load the data
	 * @param keyboard the scanner for user input
	 */
	public DataController(DataView view, String dataFilename) {
		this.view = view;
		this.dataFilename = dataFilename;
	}

	/**
	 * Loads the data (handler model) from the file.
	 * If the file does not exist, a new handler object is created and then saved.
	 * If there was a problem in reading the file, the error message will be printed
	 * and the system will exit.
	 */
	public void loadData() {
		try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(dataFilename)))) {
			model = (Handler) ois.readObject();
		} catch (FileNotFoundException e) {
			model = new Handler();
			view.viewCreatingNewDataFile();
			saveData();
		} catch (IOException | ClassNotFoundException e) {
			view.viewLoadDataError(e.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * Saves data (handler model) to the file.
	 * If there was a problem in opening/reading the file, the error message will be printed
	 * and the system will exit.
	 */
	public void saveData() {
		try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(dataFilename)))) {
			oos.writeObject(model);
			view.viewSaveDataSuccess();
		} catch (IOException e) {
			view.viewSaveDataError(e.getMessage());
			System.exit(1);
		}
	}
	
	/**
	 * Returns the data (handler mode).
	 *
	 * @return the data (handler mode)
	 */
	public Handler getData() {
		return model;
	}

}