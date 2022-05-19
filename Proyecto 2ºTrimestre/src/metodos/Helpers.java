package metodos;

import java.sql.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class Helpers {

	
	@SuppressWarnings("deprecation")
	public static Date GregorianCalendarToDate(GregorianCalendar fecha)
	{
		int anio = fecha.get(Calendar.YEAR);
		int mes = fecha.get(Calendar.MONTH);
		int dia = fecha.get(Calendar.DAY_OF_MONTH);
		return new Date(anio,mes,dia);
	}
}
