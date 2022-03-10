package clasesObjetos;

import java.util.TreeMap;

public class Empresa {

	//Propiedades
	private TreeMap<Matricula, Vehiculo> Stock;
	private TreeMap<String, Cliente> Clientes; //El String será el DNI
	private TreeMap<String, Empleado> Empleados; //El personal de la empresa
	private TreeMap<String, Oficina> Oficinas; //Las oficinas de la empresa

	//Setters & Getters
	@SuppressWarnings("unchecked")
	public TreeMap<String, Empleado> getEmpleados() {
		//Devolvemos un clon
		return (TreeMap<String, Empleado>) Empleados.clone() ;
	}

	@SuppressWarnings("unchecked")
	public void setEmpleados(TreeMap<String, Empleado> empleados) {
		//Devolvemos un clon
		Empleados = (TreeMap<String, Empleado>) empleados.clone();
	}

	public void addAlGaraje( Vehiculo nuevo ) 
	{
		this.Stock.put(nuevo.matricula, nuevo);
	}
	
	@SuppressWarnings("unchecked")
	public TreeMap<Matricula, Vehiculo> getGaraje() {
		//Devolvemos un clon
		return (TreeMap<Matricula, Vehiculo>) Stock.clone() ;
	}

	/**
	 * Constructor "de copia" que requiere de un treeMap ya creado de garage
	 * @param garage El {@code TreeMap} de Vehículos con clave principal del tipo {@code Matrícula}
	 */
	@SuppressWarnings("unchecked")
	public void setGaraje(TreeMap<Matricula, Vehiculo> garaje) {
		//Devolvemos un clon
		Stock = (TreeMap<Matricula, Vehiculo>) garaje.clone() ;
	}

	@SuppressWarnings("unchecked")
	public TreeMap<String, Cliente> getClientes() {
		//Devolvemos un clon
		return (TreeMap<String, Cliente>) Clientes.clone();
	}

	@SuppressWarnings("unchecked")
	public void setClientes(TreeMap<String, Cliente> clientes) {
		//Devolvemos un clon
		Clientes = (TreeMap<String, Cliente>) clientes.clone();
	}

	@SuppressWarnings("unchecked")
	public TreeMap<String, Oficina> getOficinas() {
		return (TreeMap<String, Oficina>) Oficinas.clone() ;
	}

	@SuppressWarnings("unchecked")
	public void setOficinas(TreeMap<String, Oficina> oficinas) {
		Oficinas = (TreeMap<String, Oficina>) oficinas.clone();
	}
	
}
