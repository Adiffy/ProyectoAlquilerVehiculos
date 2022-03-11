package clasesObjetos;

import java.util.TreeMap;

public class Oficina {

	//Propiedades
	private Direccion ubicacion;
	private TreeMap<String, Empleado> personal;
	
	//Getters Y setters
	public Direccion getUbicacion() {
		return new Direccion(ubicacion); //Devolveremos una copia
	}
	public void setUbicacion(Direccion ubicacion) {
		//Suponemos que al haber creado la ubicación ya será correcta
		//Aún así, evitaremos el tampering mediante una copia
		this.ubicacion = new Direccion(ubicacion);
	}
	
	@SuppressWarnings("unchecked")
	public TreeMap<String, Empleado> getPersonal() {
		return (TreeMap<String, Empleado>) personal.clone() ;
	}
	public void setPersonal(TreeMap<String, Empleado> personal) {
		this.personal = personal;
	}
	public void addEmpleado(Empleado contratado)
	{
		String clavePrincipal = contratado.getDni() ;
		this.personal.put(clavePrincipal, new Empleado(contratado)); //Lo añadimos
	}
}
