package objetosBD;

/**
 * Clase creada para crear un objeto de clase persona
 * únicamente para recibir datos de la base de datos de forma conjunta y ordenada
 * @author Victor
 *
 */
public class PersonaBD {

	
	//Propiedades (las mismas que Persona)
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String dni;
	
	
	public PersonaBD(String dni2, String nombre2, String ap1, String ap2) {
		setDni(dni2);
		setNombre(nombre2);
		setApellido1(ap1);
		setApellido2(ap2);
	}
	public String getNombre() {
		String copia = nombre;
		return copia;
	}
	public void setNombre(String nombre) {
		String copia = nombre;
		this.nombre = copia;
	}
	public String getApellido1() {
		String ap1 = apellido1;
		return ap1;
	}
	public void setApellido1(String apellido1) {
		String ap1 = apellido1;
		this.apellido1 = ap1;
	}
	public String getApellido2() {
		String ap2 = apellido2;
		return ap2;
	}
	public void setApellido2(String apellido2) {
		String ap2 = apellido2;
		this.apellido2 = ap2;
	}
	public String getDni() {
		String DNI = dni;
		return DNI;
	}
	public void setDni(String dni) {
		String DNI = dni;
		this.dni = DNI;
	}
	
}
