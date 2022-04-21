package metodos;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase de m�todos p�blicos est�ticos en el paquete de metodos
 * Contiene m�todos que ayudar�n a hacer procesos repetitivos 
 */
public class Helpers {

	/**
	 * M�todo que crea un nuevo calendario {@code GregorianCalendar} 
	 * que ser� un 'clon' identico del calendario dado.
	 * @param calendario El {@code GregorianCalendar} que se desea copiar
	 * @return	Un calendario indentico que por ejemplo, evitar� el tampering
	 */
	public static GregorianCalendar nuevoCalendar(GregorianCalendar calendario)
	{
		//Calendario a devolver
		GregorianCalendar nuevoCalendario = new GregorianCalendar();
		//Propiedades para componer el nuevo calendario
		int anno = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		//Creamos el nuevo calendario con los par�metros antes recogidos
		nuevoCalendario.set(anno, mes, dia);
		//Devolvemos el calendario 'clon'
		return nuevoCalendario;
	}
}
