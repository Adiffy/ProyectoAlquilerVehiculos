package clasesObjetos;

import java.util.Calendar;
import java.util.GregorianCalendar;

import exceptions.DNInoValidoException;
import exceptions.LongitudCadenaNoValidaException;

public class Empleado extends Persona{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	private GregorianCalendar fechaAlta;
	private Oficina oficina;		//La oficina a la que est� asignado el empleado

	
	//Getters y Setters
	
	public GregorianCalendar getFechaAlta() { //Creamos una nueva fecha espejo
		int anio = fechaAlta.get(Calendar.YEAR);
		int mes = fechaAlta.get(Calendar.MONTH);
		int dia = fechaAlta.get(Calendar.DAY_OF_MONTH);
		return new GregorianCalendar(anio,mes,dia);
	}

	public void setFechaAlta(GregorianCalendar fechaAlta) {	//Le hacemos un clon
		this.fechaAlta = (GregorianCalendar) fechaAlta.clone() ;
	}

	public Oficina getOficina() { //Devolvemos una copia
		return new Oficina(oficina) ;
	}

	public void setOficina(Oficina oficina) {
		this.oficina = new Oficina(oficina); //Le hacemos una copia
	}

	//Constructores
	/**
	 * Constructor completo de {@code Empleado} donde necesita:
	 * @param nombre	Un {@code String} para indicar su nombre. Ejemplo: "Diego"
	 * @param apellido1	Otro {@code String} para indicar su primer apellido. Ejemplo: "Maradona"  
	 * @param apellido2 Otro {@code String} para su segundo apellido, puede ser "".
	 * @param dni Un {@code String} a modo de DNI (que ser� validado por el set)
	 * @param fechaAlta Un {@code GregorianCalendar} que indicar� cuando se di� de alta el {@code Empleado}
	 * @param oficinaTrabajo	La {@code Oficina} donde trabaja el {@code Empleado}
	 * @throws CarnetRequeridoInvalidoException 
	 * @throws LongitudCadenaNoValidaException 
	 * @throws DNInoValidoException 
	 */
	public Empleado(String nombre, String apellido1, String apellido2, String dni, GregorianCalendar fechaAlta, Oficina oficinaTrabajo) throws LongitudCadenaNoValidaException, DNInoValidoException {
		super(nombre, apellido1, apellido2, dni);
		this.setFechaAlta(fechaAlta);
		this.setOficina(oficinaTrabajo);
	}
	/**
	 * Constructor de copia
	 * @param contratado	El {@code Empleado} a copiar
	 * @throws CarnetRequeridoInvalidoException 
	 * @throws LongitudCadenaNoValidaException 
	 */
	public Empleado(Empleado contratado) throws DNInoValidoException, LongitudCadenaNoValidaException {
		super(contratado.getNombre(),contratado.getApellido2(),contratado.getDni());
		this.setOficina(contratado.getOficina());
		this.setFechaAlta(contratado.getFechaAlta());
	}

	@Override
	public String toString() {
		return getNombreCompleto();
	}

	// Separamos los atributos de un nombre completo
	public static String getAp1FromNombreCompleto(String nombreCompleto) {
		return (nombreCompleto.substring(0, nombreCompleto.indexOf(" ")).trim());
	}
	
	public static String getAp2FromNombreCompleto(String nombreCompleto) {
		return (nombreCompleto.substring(nombreCompleto.indexOf(" "), nombreCompleto.indexOf(" ")).trim());
	}
	
	public static String getNombreFromNombreCompleto(String nombreCompleto)
	{
		return (nombreCompleto.substring(nombreCompleto.indexOf(","),nombreCompleto.indexOf(" ")).trim());
	}

	@Override
	public boolean equals(Object a) {
		if (this == a)
			return true;
		if (a == null || this.getClass() != a.getClass())
			return false;
		Persona dado = (Empleado) a; //Hacemos una copia tipo PERSONA y no OBJETO
		if (this.getDni().compareToIgnoreCase(dado.getDni())==0) {
			return true;
		}else {
			return false;
		}
	}
	
}
 