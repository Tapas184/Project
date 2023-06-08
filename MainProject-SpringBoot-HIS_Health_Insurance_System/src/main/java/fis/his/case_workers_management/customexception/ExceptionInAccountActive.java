package fis.his.case_workers_management.customexception;

public class ExceptionInAccountActive extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1428267852884086652L;

	public ExceptionInAccountActive(String message) {
		super(message);
	}

}
