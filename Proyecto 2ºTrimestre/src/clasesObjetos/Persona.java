package clasesObjetos;

import java.io.Serializable;
import exceptions.DNInoValidoException;
import exceptions.LongitudCadenaNoValidaException;
import metodos.MetodoDni;

public abstract class Persona implements Comparable<Persona>, Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	private String nombre;
	private String apellido1;
	private String apellido2;
	protected String dni; //protected para poder usarlo en otras clases que hereden
	
	
	
	//Getters & Setters
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) throws LongitudCadenaNoValidaException {
		if (nombre.length()<=25 && nombre.length()>=2) {
			this.nombre = nombre;
		}else {
			throw new LongitudCadenaNoValidaException("Longitud de nombre no válida");
		}
	}
	public String getApellido1() {
		return apellido1;
	}
	public void setApellido1(String apellido1) throws LongitudCadenaNoValidaException {
		if (apellido1.length()>=2 && apellido1.length()<=25) {
			this.apellido1 = apellido1;
		}else {
			throw new LongitudCadenaNoValidaException("El primer apellido es demasiado largo");
		}
	}
	public String getApellido2() {
		return apellido2;
	}
	public void setApellido2(String apellido2) throws LongitudCadenaNoValidaException {
		if (apellido2.length()>=0 && apellido2.length()<=25) {
			this.apellido2 = apellido2;
		}else {
			throw new LongitudCadenaNoValidaException("El segundo apellido es demasiado largo");
		}
	}
	public String getDni() {
		String copia = dni;
		return copia;
	}
	protected void setDni(String dni) throws DNInoValidoException {
		if(MetodoDni.DNIvalido(dni)) {
			this.dni=dni;
		}else {
			throw new DNInoValidoException("DNI inválido");
		}
		
	}
	
	//Constructores 
	public Persona( String dni) throws DNInoValidoException {
		super();
		this.setDni(dni);
	}
	
	public Persona(String nombre, String apellido1, String apellido2, String dni) throws DNInoValidoException, LongitudCadenaNoValidaException {
		super();
		this.setNombre(nombre);
		this.setApellido1(apellido1);
		this.setApellido2(apellido2);
		this.setDni(dni);
	}
	
	public Persona(String nombre, String apellido1,  String dni) throws DNInoValidoException, LongitudCadenaNoValidaException {
		super();
		this.setNombre(nombre);
		this.setApellido1(apellido1);
		this.setDni(dni);
	}
	
	//Métodos
	public String getNombreCompleto() {
		return apellido1+" "+apellido2+", "+nombre;
	}
	
	@Override
	public String toString() {
		return getDni()+" - "+getNombreCompleto();
	}
	
	@Override
	public boolean equals(Object a) {
		if (this == a)
			return true;
		if (a == null || this.getClass() != a.getClass())
			return false;
		Persona dada = (Persona) a; //Hacemos una copia tipo PERSONA y no OBJETO
		if (this.dni == dada.dni) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Compara 2 personas para ordenarlas alfab�ticamente, comparando sus nombres completos
	 * @param o	La persona con la que se compara.
	 * @return	Un entero que indica la diferencia entre las Personas comparadas o 0 si no hay ninguna.
	 */
	@Override
	public int compareTo(Persona o) {
		int resultado = 0;
		Persona copia = (Persona) o; //Hacemos una copia
		resultado = this.getDni().compareToIgnoreCase(copia.getDni()); //Comparamos sus nombres completos
		return resultado;
	}
	
}
