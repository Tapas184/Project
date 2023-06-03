package fis.ssn.exception;

public class UserNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1001L;

	public UserNotFoundException(String message) {
		super(message);
	}

}
