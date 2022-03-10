package clasesObjetos;

import java.util.GregorianCalendar;

public class Empleado extends Persona{
	
	//Propiedades
	private GregorianCalendar fechaAlta;
	private Direccion oficina;

	
	//Getters y Setters
	
	@SuppressWarnings("static-access")
	public GregorianCalendar getFechaAlta() { //Creamos una nueva fecha espejo
		return new GregorianCalendar(fechaAlta.YEAR,fechaAlta.MONTH,fechaAlta.DAY_OF_MONTH);
	}

	public void setFechaAlta(GregorianCalendar fechaAlta) {	//Le hacemos un clon
		this.fechaAlta = (GregorianCalendar) fechaAlta.clone() ;
	}

	public Direccion getOficina() { //Devolvemos una copia
		return new Direccion(oficina) ;
	}

	public void setOficina(Direccion oficina) {
		this.oficina = new Direccion(oficina); //Le hacemos una copia
	}

	//Constructores
	/**
	 * Constructor completo de {@code Empleado} donde necesita:
	 * @param nombre	Un {@code String} para indicar su nombre. Ejemplo: "Diego"
	 * @param apellido1	Otro {@code String} para indicar su primer apellido. Ejemplo: "Maradona"  
	 * @param apellido2 Otro {@code String} para su segundo apellido, puede ser "".
	 * @param dni Un {@code String} a modo de DNI (que será validado por el set)
	 * @param fechaAlta Un {@code GregorianCalendar} que indicará cuando se dió de alta el {@code Empleado}
	 * @param oficinaTrabajo	La {@code Direccion} donde trabaja el {@code Empleado}
	 */
	public Empleado(String nombre, String apellido1, String apellido2, String dni, GregorianCalendar fechaAlta, Direccion oficinaTrabajo) {
		super(nombre, apellido1, apellido2, dni);
		this.setFechaAlta(fechaAlta);
		this.setOficina(oficinaTrabajo);
	}
	/**
	 * Constructor de copia
	 * @param contratado	El {@code Empleado} a copiar
	 */
	public Empleado(Empleado contratado) {
		super(contratado.getNombre(),contratado.getApellido2(),contratado.getDni());
		this.setOficina(contratado.getOficina());
		this.setFechaAlta(contratado.getFechaAlta());
	}

}
 