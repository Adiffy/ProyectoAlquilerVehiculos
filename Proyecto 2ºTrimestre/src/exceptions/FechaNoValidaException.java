package exceptions;

@SuppressWarnings("serial")
public class FechaNoValidaException extends Exception {

	public FechaNoValidaException(String mensaje)
	{
		super(mensaje);
	}
}
