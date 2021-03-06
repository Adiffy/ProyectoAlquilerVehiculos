package clasesObjetos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.TreeMap;

import comparadores.ComparaClientePorNombre;
import comparadores.ComparaEmpleadoPorNombre;
import comparadores.ComparaVehiculoPorCategoria;
import exceptions.CodigoPostalException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;
import exceptions.LongitudNoValidaException;
import exceptions.RecargoNoValidoException;

public class Empresa implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	private TreeMap<Matricula, Vehiculo> Stock;
	private TreeMap<String, Cliente> Clientes; //El String ser� el DNI
	private TreeMap<String, Empleado> Empleados; //El personal de la empresa
	private TreeMap<String, Oficina> Oficinas; //Las oficinas de la empresa
	private TreeMap<String, Alquiler> Alquileres; //Los alquileres de la empresa
	private TreeMap<String, Categoria> Categorias;	//Las categorias

	//Setters & Getters
	
	/**
	 * @return las categorias
	 */
	@SuppressWarnings("unchecked")
	public TreeMap<String, Categoria> getCategorias() {
		return (TreeMap<String, Categoria>) Categorias.clone();
	}


	/**
	 * @param categorias las categorias a guardar
	 */
	@SuppressWarnings("unchecked")
	public void setCategorias(TreeMap<String, Categoria> categorias) {
		Categorias = (TreeMap<String, Categoria>) categorias.clone();
	}
	public void addCategoria(Categoria categoria) throws RecargoNoValidoException {
		Categoria copia = new Categoria(categoria);
		Categorias.put(copia.getCodigo(), copia);
	}


	@SuppressWarnings("unchecked")	//Quitamos el Warning para evitar errores al exportar
	public TreeMap<String, Empleado> getEmpleados() {
		//Devolvemos un clon
		return (TreeMap<String, Empleado>) Empleados.clone() ;
	}

	
	@SuppressWarnings("unchecked")
	public TreeMap<String, Alquiler> getAlquileres() {
		
		return (TreeMap<String, Alquiler>) Alquileres.clone();
	}


	@SuppressWarnings("unchecked")
	public void setAlquileres(TreeMap<String, Alquiler> alquileres) {
		Alquileres = (TreeMap<String, Alquiler>) alquileres.clone();
	}
	public void nuevoAlquiler(Alquiler nuevo)	//Equivale a un put
	{
		Alquileres.put(nuevo.getCodigo(), nuevo);
	}
	@SuppressWarnings("unlikely-arg-type")
	public void quitaAlquiler(GregorianCalendar pk)	//Equivale a un remove
	{
		Alquileres.remove(pk);
	}
	@SuppressWarnings("unlikely-arg-type")
	public void quitaAlquiler(Alquiler aQuitar)	//Equivale a un remove
	{
		Alquileres.remove(aQuitar.getFechaInicioAlquiler());
	}

	@SuppressWarnings("unchecked")	//Quitamos el Warning para evitar errores al exportar
	public void setEmpleados(TreeMap<String, Empleado> empleados) {
		//Devolvemos un clon
		Empleados = (TreeMap<String, Empleado>) empleados.clone();
	}

	public void addAlGaraje( Vehiculo nuevo ) 
	{
		
		this.Stock.put(nuevo.matricula, nuevo);
	}
	
	
	@SuppressWarnings("unchecked")	//Quitamos el Warning para evitar errores al exportar
	public TreeMap<Matricula, Vehiculo> getGaraje() {
		//Devolvemos un clon
		return (TreeMap<Matricula, Vehiculo>) Stock.clone() ;
	}

	/**
	 * Constructor "de copia" que requiere de un treeMap ya creado de garage
	 * @param garage El {@code TreeMap} de Veh�culos con clave principal del tipo {@code Matr�cula}
	 */
	@SuppressWarnings("unchecked")//Quitamos el Warning para evitar errores al exportar
	public void setGaraje(TreeMap<Matricula, Vehiculo> garaje) {
		//Devolvemos un clon
		Stock = (TreeMap<Matricula, Vehiculo>) garaje.clone() ;
	}

	
	@SuppressWarnings("unchecked") //Quitamos el Warning para evitar errores al exportar
	public TreeMap<String, Cliente> getClientes() {
		//Devolvemos un clon
		return (TreeMap<String, Cliente>) Clientes.clone();
	}

	
	@SuppressWarnings("unchecked")	//Quitamos el Warning para evitar errores al exportar
	public void setClientes(TreeMap<String, Cliente> clientes) {
		//Devolvemos un clon
		Clientes = (TreeMap<String, Cliente>) clientes.clone();
	}

	@SuppressWarnings("unchecked")	//Quitamos el Warning para evitar errores al exportar
	public TreeMap<String, Oficina> getOficinas() {
		return (TreeMap<String, Oficina>) Oficinas.clone() ;
	}

	
	@SuppressWarnings("unchecked")	//Quitamos el Warning para evitar errores al exportar
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
	public void despedirEmpleado(Empleado emple)
	{
		Empleados.remove(emple.dni);
	}
	public void despedirEmpleado(String dni)
	{
		Empleados.remove(dni);
	}
	
	public ArrayList<Empleado> ListarEmpleadoNombre() throws LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		//boolean PrimeraVez = true;	//Para recorrerlo
		
		ArrayList<Empleado> empleados = metodos.TreeMapToArrayList.Empleados(Empleados);//PASAMOS DE TREEMAP A ARRAYLIST
		ComparaEmpleadoPorNombre c = new ComparaEmpleadoPorNombre();
		empleados.sort(c);
		//Creamos un empleado con el constructor m�nimo para luego intercambiarlo con el empleado anterior al comparar en el bucle:
//		Empleado b = new Empleado("Nombre","Apellido1", "Apellido2", "78280774k", null, new Oficina("23009","", new Direccion("Calle","12", "23009", "Ja�n"), "", "", true));
//		
//		for (Empleado a:empleados)
//		{
//			if (PrimeraVez)
//			{
//				b = a;
//				PrimeraVez = false;
//			}else {
//				ComparaEmpleadoPorNombre c = new ComparaEmpleadoPorNombre();
//				c.compare(a, b);
//				b = a;
//			}
//			
//		}
		return empleados;
	}

	public ArrayList<Cliente> ListarClienteNombre() throws LicenciaNoValidaException {
		
		//boolean PrimeraVez = true;	//Para recorrerlo
		
		ArrayList<Cliente> clientes = metodos.TreeMapToArrayList.Clientes(Clientes);//PASAMOS DE TREEMAP A ARRAYLIST
		//Creamos un empleado con el constructor m�nimo para luego intercambiarlo con el empleado anterior al comparar en el bucle:
		//Cliente b = new Cliente("Nombre", "Ap1", "Ap2", "78280774k", "A1", 0);//Las tarjetas de los clientes de verdad no empezar�n en 0 sino en 1
		ComparaClientePorNombre c = new ComparaClientePorNombre();
		clientes.sort(c);
/*
		for (Cliente a:clientes)
		{
			if (PrimeraVez)
			{
				b = a;
				PrimeraVez = false;
			}else {
				
				if (c.compare(a, b)<0)	//Si el segundo es mayor devuelve un negativo
				{
					a=b;
				}
				b = a;	//Para que compare con el anterior
			}
		}*/
		return clientes;
		
	}
	
	public ArrayList<Electrico> ListarVehiculoElectricoPorCategoria(TreeMap<Matricula, Vehiculo> flota) 
	{
		ArrayList<Electrico> vehiculos = metodos.TreeMapToArrayList.Electricos(flota);	//Pasamos a ArrayList
		ComparaVehiculoPorCategoria c = new ComparaVehiculoPorCategoria(); 
		vehiculos.sort(c);
		return vehiculos;
	}
	public void eliminarOficina(Oficina ofi) //Equivale a un remove
	{
		Oficinas.remove(ofi.getCódigo());
	}
	public void eliminarOficina(String ClaveOfi) //Equivale a un remove
	{
		Oficinas.remove(ClaveOfi);
	}
	
	
	//Constructor
	public Empresa()
	{
		this.Oficinas = new TreeMap<String,Oficina>();
		this.Clientes = new TreeMap<String,Cliente>();
		this.Empleados = new TreeMap<String, Empleado>();
		this.Stock = new TreeMap<Matricula, Vehiculo>();
		this.Categorias = new TreeMap<String,Categoria>();
		this.Alquileres = new TreeMap<String,Alquiler>();
		
	}
}
