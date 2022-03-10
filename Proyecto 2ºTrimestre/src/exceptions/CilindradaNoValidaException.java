package exceptions;


public class CilindradaNoValidaException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CilindradaNoValidaException() {
		
	}

	public CilindradaNoValidaException(String message) {
		super(message);
		
	}

	public CilindradaNoValidaException(Throwable cause) {
		super(cause); 
		
	}

	public CilindradaNoValidaException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public CilindradaNoValidaException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
