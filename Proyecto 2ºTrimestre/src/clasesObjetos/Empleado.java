package clasesObjetos;

public class Empleado extends Persona{
	
	//Propiedades
	

	
	//Getters y Setters
	
	//Constructor 
	public Empleado(String nombre, String apellido1, String apellido2, String dni) {
		super(nombre, apellido1, apellido2, dni);
		// TODO Auto-generated constructor stub
	}

	public Empleado(Empleado contratado) {
		super(contratado.getNombre(),contratado.getApellido2(),contratado.getDni());
		
	}

}
 