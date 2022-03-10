package exceptions;


public class LicenciaNoValidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LicenciaNoValidaException() {
		
	}

	public LicenciaNoValidaException(String message) {
		super(message);
		
	}

	public LicenciaNoValidaException(Throwable cause) {
		super(cause);
		
	}

	public LicenciaNoValidaException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LicenciaNoValidaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
