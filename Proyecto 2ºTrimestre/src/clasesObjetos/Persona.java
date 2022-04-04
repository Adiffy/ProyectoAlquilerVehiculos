package clasesObjetos;

import java.io.Serializable;

import exceptions.CarnetRequeridoInvalidoException;
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
		String copia = nombre;
		return copia;	//Devolvemos una copia
	}
	public void setNombre(String nombre) throws LongitudCadenaNoValidaException {
		if (nombre.length()<=25 && nombre.length()>=2) {
			String nom = nombre;
			this.nombre = nom;
		}else {
			throw new LongitudCadenaNoValidaException("Longitud de nombre no v�lida");
		}
	}
	public String getApellido1() {
		String Ap1 = apellido1;
		return Ap1;
	}
	public void setApellido1(String apellido1) throws LongitudCadenaNoValidaException {
		if (apellido1.length()>=2 && apellido1.length()<=25) {
			String Ap1 = apellido1;		//Evitamos el tampering
			this.apellido1 = Ap1;
		}else {
			throw new LongitudCadenaNoValidaException("El primer apellido es demasiado largo");
		}
	}
	public String getApellido2() {
		String Ap2 = apellido2; 
		return Ap2;
	}
	public void setApellido2(String apellido2) throws LongitudCadenaNoValidaException {
		if (apellido2.length()>=0 && apellido2.length()<=25) {
			String Ap2 = apellido2; 
			this.apellido2 = Ap2;
		}else {
			throw new LongitudCadenaNoValidaException("El segundo apellido es demasiado largo");
		}
	}
	public String getDni() {
		String DNIcopia = dni; 
		return DNIcopia;
	}
	protected void setDni(String dni) throws CarnetRequeridoInvalidoException {
		if(MetodoDni.DNIvalido(dni)) {
			String DNIcopia = dni; 
			this.dni=DNIcopia;
		}else {
			throw new CarnetRequeridoInvalidoException("DNI inv�lido");
		}
		
	}
	
	//Constructores 
	public Persona( String dni) throws CarnetRequeridoInvalidoException {
		super();
		this.setDni(dni);
	}
	
	public Persona(String nombre, String apellido1, String apellido2, String dni) throws CarnetRequeridoInvalidoException, LongitudCadenaNoValidaException {
		super();
		this.setNombre(nombre);
		this.setApellido1(apellido1);
		this.setApellido2(apellido2);
		this.setDni(dni);
	}
	
	public Persona(String nombre, String apellido1,  String dni) throws CarnetRequeridoInvalidoException, LongitudCadenaNoValidaException {
		super();
		this.setNombre(nombre);
		this.setApellido1(apellido1);
		this.setDni(dni);
	}
	
	//M�todos
	public String getNombreCompleto() {
		//Devolvemos una nueva String con la concatenaci�n de lo siguiente:
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
