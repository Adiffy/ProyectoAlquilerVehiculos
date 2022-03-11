package clasesObjetos;

import java.util.TreeMap;

public class Empresa {

	//Propiedades
	private TreeMap<Matricula, Vehiculo> Stock;
	private TreeMap<String, Cliente> Clientes;
	private TreeMap<String, Empleado> Empleados;

	//Setters & Getters
	@SuppressWarnings("unchecked")
	public TreeMap<String, Empleado> getEmpleados() {
		return (TreeMap<String, Empleado>) Empleados.clone() ;
	}

	@SuppressWarnings("unchecked")
	public void setEmpleados(TreeMap<String, Empleado> empleados) {
		Empleados = (TreeMap<String, Empleado>) empleados.clone();
	}

	@SuppressWarnings("unchecked")
	public TreeMap<Matricula, Vehiculo> getGarage() {
		return (TreeMap<Matricula, Vehiculo>) Stock.clone() ;
	}

	/**
	 * Constructor de copia
	 * @param garage El {@code TreeMap} de Vehículos con clave principal del tipo {@code Matrícula}
	 */
	@SuppressWarnings("unchecked")
	public void setGaraje(TreeMap<Matricula, Vehiculo> garage) {
		Stock = (TreeMap<Matricula, Vehiculo>) garage.clone() ;
	}

	@SuppressWarnings("unchecked")
	public TreeMap<String, Cliente> getClientes() {
		return (TreeMap<String, Cliente>) Clientes.clone();
	}

	@SuppressWarnings("unchecked")
	public void setClientes(TreeMap<String, Cliente> clientes) {
		Clientes = (TreeMap<String, Cliente>) clientes.clone();
	}
	
}
