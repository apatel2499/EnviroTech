package view;

/**
 * The DataView is the interface for all view classes for displaying messages
 * during loading/saving of data.
 * 
 * @author Leda
 */
public interface DataView {

	/**
	 * Updates the view when there's an error message during loading of data.
	 *
	 * @param errorMessage the error message
	 */
	public abstract void viewLoadDataError(String errorMessage);

	/**
	 * Updates the view when there's an error message during saving of data.
	 *
	 * @param errorMessage the error message
	 */
	public abstract void viewSaveDataError(String errorMessage);

	/**
	 * Updates the view when there's a success message during saving of data.
	 */
	public abstract void viewSaveDataSuccess();

	/**
	 * Updates the view when there's no data file found yet and a new one is being created.
	 */
	public abstract void viewCreatingNewDataFile();
	
}