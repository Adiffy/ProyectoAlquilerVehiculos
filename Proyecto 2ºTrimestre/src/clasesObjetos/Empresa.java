package clasesObjetos;

import java.util.TreeMap;

public class Empresa {

	//Propiedades
	private TreeMap<Matricula, Vehiculo> Stock;
	private TreeMap<String, Cliente> Clientes; //El String será el DNI
	private TreeMap<String, Empleado> Empleados; //El personal de la empresa
	private TreeMap<String, Oficina> Oficinas; //Las oficinas de la empresa

	//Setters & Getters
	
	public TreeMap<String, Empleado> getEmpleados() {
		//Devolvemos un clon
		return (TreeMap<String, Empleado>) Empleados.clone() ;
	}

	
	public void setEmpleados(TreeMap<String, Empleado> empleados) {
		//Devolvemos un clon
		Empleados = (TreeMap<String, Empleado>) empleados.clone();
	}

	public void addAlGaraje( Vehiculo nuevo ) 
	{
		this.Stock.put(nuevo.matricula, nuevo);
	}
	
	
	public TreeMap<Matricula, Vehiculo> getGaraje() {
		//Devolvemos un clon
		return (TreeMap<Matricula, Vehiculo>) Stock.clone() ;
	}

	/**
	 * Constructor "de copia" que requiere de un treeMap ya creado de garage
	 * @param garage El {@code TreeMap} de Vehículos con clave principal del tipo {@code Matrícula}
	 */
	public void setGaraje(TreeMap<Matricula, Vehiculo> garaje) {
		//Devolvemos un clon
		Stock = (TreeMap<Matricula, Vehiculo>) garaje.clone() ;
	}

	
	public TreeMap<String, Cliente> getClientes() {
		//Devolvemos un clon
		return (TreeMap<String, Cliente>) Clientes.clone();
	}

	
	public void setClientes(TreeMap<String, Cliente> clientes) {
		//Devolvemos un clon
		Clientes = (TreeMap<String, Cliente>) clientes.clone();
	}

	public TreeMap<String, Oficina> getOficinas() {
		return (TreeMap<String, Oficina>) Oficinas.clone() ;
	}

	
	public void setOficinas(TreeMap<String, Oficina> oficinas) {
		Oficinas = (TreeMap<String, Oficina>) oficinas.clone();
	}
	
	public void nuevaOficina(Oficina oficina) //Equivale a un put
	{
		Oficinas.put(oficina.getCódigo(), oficina);
	}
	public void nuevoCliente(Cliente cliente) //Equivale a un put
	{
		Clientes.put(cliente.dni, cliente);
	}
	public void nuevoVehiculo(Vehiculo auto) //Equivale a un put
	{
		Stock.put(auto.matricula, auto);
	}
	public void nuevoEmpleado(Empleado emple) //Equivale a un put
	{
		Empleados.put(emple.dni, emple);
	}
	
	//Constructor
	public Empresa()
	{
		TreeMap<String, Oficina> Oficinas = new TreeMap<String,Oficina>();
		TreeMap<String, Cliente> clientes = new TreeMap<String,Cliente>();
		TreeMap<String, Empleado> Empleados = new TreeMap<String, Empleado>();
		TreeMap<Matricula, Vehiculo> Stock = new TreeMap<Matricula, Vehiculo>();
		
	}
}
