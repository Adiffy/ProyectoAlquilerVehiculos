package metodos;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 * Clase de métodos públicos estáticos en el paquete de metodos
 * Contiene métodos que ayudarán a hacer procesos repetitivos 
 */
public class Helpers {

	/**
	 * Método que crea un nuevo calendario {@code GregorianCalendar} 
	 * que será un 'clon' identico del calendario dado.
	 * @param calendario El {@code GregorianCalendar} que se desea copiar
	 * @return	Un calendario indentico que por ejemplo, evitará el tampering
	 */
	public static GregorianCalendar nuevoCalendar(GregorianCalendar calendario)
	{
		//Calendario a devolver
		GregorianCalendar nuevoCalendario = new GregorianCalendar();
		//Propiedades para componer el nuevo calendario
		int anno = calendario.get(Calendar.YEAR);
		int mes = calendario.get(Calendar.MONTH);
		int dia = calendario.get(Calendar.DAY_OF_MONTH);
		//Creamos el nuevo calendario con los parámetros antes recogidos
		nuevoCalendario.set(anno, mes, dia);
		//Devolvemos el calendario 'clon'
		return nuevoCalendario;
	}
}
