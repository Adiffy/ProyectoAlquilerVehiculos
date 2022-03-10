package exceptions;


public class NumeroMatriculaNoValidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NumeroMatriculaNoValidoException() {
		
	}

	public NumeroMatriculaNoValidoException(String message) {
		super(message);
		
	}

	public NumeroMatriculaNoValidoException(Throwable cause) {
		super(cause);
		
	}

	public NumeroMatriculaNoValidoException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public NumeroMatriculaNoValidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
