package exceptions;


public class TipoCarnetNoValidoException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TipoCarnetNoValidoException() {
		
	}

	public TipoCarnetNoValidoException(String message) {
		super(message);
		
	}

	public TipoCarnetNoValidoException(Throwable cause) {
		super(cause);
		
	}

	public TipoCarnetNoValidoException(String message, Throwable cause) {
		super(message, cause);
		
	}

	public TipoCarnetNoValidoException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
		
	}

}
