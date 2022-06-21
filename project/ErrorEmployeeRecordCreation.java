package project;
//Custom Exception that is created for ensuring that Creation is correct
public class ErrorEmployeeRecordCreation extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ErrorEmployeeRecordCreation(String message) {
		super(message);
	}

}
