package exceptions;

public class LetrasMatriculaNoValidasException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public LetrasMatriculaNoValidasException() {
		
	}

	public LetrasMatriculaNoValidasException(String message) {
		super(message);
		
	}

	public LetrasMatriculaNoValidasException(Throwable cause) {
		super(cause);
		
	}

	public LetrasMatriculaNoValidasException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public LetrasMatriculaNoValidasException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
