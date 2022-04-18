package exceptions;


public class LongitudCadenaNoValidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LongitudCadenaNoValidaException() {
		
	}

	public LongitudCadenaNoValidaException(String message) {
		super(message);
		
	}

	public LongitudCadenaNoValidaException(Throwable cause) {
		super(cause);
		
	}

	public LongitudCadenaNoValidaException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LongitudCadenaNoValidaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
