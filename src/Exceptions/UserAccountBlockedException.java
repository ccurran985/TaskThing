package Exceptions;

public class UserAccountBlockedException extends Exception{

	private static final long serialVersionUID = 1L;

	public UserAccountBlockedException() {

	}

	public UserAccountBlockedException(String message) {
		super(message);
	}

	public UserAccountBlockedException(Throwable cause) {
		super(cause);
	}

	public UserAccountBlockedException(String message, Throwable cause) {
		super(message, cause);
	}
}
