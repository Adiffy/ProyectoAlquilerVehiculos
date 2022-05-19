package exceptions;

public class CarnetRequeridoInvalidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CarnetRequeridoInvalidoException() {
		// 
	}

	public CarnetRequeridoInvalidoException(String message) {
		super(message);
		// 
	}

	public CarnetRequeridoInvalidoException(Throwable cause) {
		super(cause);
		// 
	}

	public CarnetRequeridoInvalidoException(String message, Throwable cause) {
		super(message, cause);
		// 
	}

	public CarnetRequeridoInvalidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		// 
	}

}
