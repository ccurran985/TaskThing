package Exceptions;

public class FailedToRegisterException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public FailedToRegisterException() {

	}

	public FailedToRegisterException(String message) {
		super(message);
	}

	public FailedToRegisterException(Throwable cause) {
		super(cause);
	}

	public FailedToRegisterException(String message, Throwable cause) {
		super(message, cause);
	}
}
