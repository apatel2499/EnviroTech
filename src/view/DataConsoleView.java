package view;


/**
 * The DataConsoleView is a DataView implementation
 * that uses the console.
 * 
 * @author Leda
 */
public class DataConsoleView implements DataView {
	
	/* (non-Javadoc)
	 * @see view.DataView#displayLoadDataError(java.lang.String)
	 */
	@Override
	public void viewLoadDataError(String errorMessage) {
		System.err.println("Error in loading data. \n" + errorMessage);
	}
	
	/* (non-Javadoc)
	 * @see view.DataView#displaySaveDataError(java.lang.String)
	 */
	@Override
	public void viewSaveDataError(String errorMessage) {
		System.err.println("Error in saving data. \n" + errorMessage);
	}

	/* (non-Javadoc)
	 * @see view.DataView#displaySaveDataSuccess()
	 */
	@Override
	public void viewSaveDataSuccess() {
		System.out.println("Data successfully saved! \n");
	}

	/* (non-Javadoc)
	 * @see view.DataView#viewCreatingNewDataFile()
	 */
	@Override
	public void viewCreatingNewDataFile() {
		System.out.println("Creating new data file...");
	}

}