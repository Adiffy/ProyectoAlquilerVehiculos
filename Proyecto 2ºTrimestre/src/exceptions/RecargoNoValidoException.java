package exceptions;


public class RecargoNoValidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public RecargoNoValidoException() {
		
	}

	public RecargoNoValidoException(String message) {
		super(message);
		
	}

	public RecargoNoValidoException(Throwable cause) {
		super(cause);
		
	}

	public RecargoNoValidoException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public RecargoNoValidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
