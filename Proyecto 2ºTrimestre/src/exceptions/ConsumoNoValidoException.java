package exceptions;


public class ConsumoNoValidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ConsumoNoValidoException() {
		
	}

	public ConsumoNoValidoException(String message) {
		super(message);
		
	}

	public ConsumoNoValidoException(Throwable cause) {
		super(cause);
		
	}

	public ConsumoNoValidoException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public ConsumoNoValidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
