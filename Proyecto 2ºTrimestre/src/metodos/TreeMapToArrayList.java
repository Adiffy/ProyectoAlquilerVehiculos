package metodos;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import clasesObjetos.Cliente;
import clasesObjetos.Empleado;
import clasesObjetos.Matricula;
import clasesObjetos.Vehiculo;

public class TreeMapToArrayList {

	
	public static ArrayList<Vehiculo> listar(TreeMap<Matricula,Vehiculo> treemap) {
		
		ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>(treemap.values());
		return lista;
	
	}
	public static ArrayList<Empleado> listarEmpleados(TreeMap<String,Empleado> treemap) {
		
		ArrayList<Empleado> lista = new ArrayList<Empleado>(treemap.values());
		return lista;
	
	}
	
	public static ArrayList<Integer> numVehiculos(TreeMap<Matricula,Vehiculo> treemap)
	{
			ArrayList<Integer> numVehiculos = new ArrayList<Integer>(treemap.size());
			return numVehiculos;
	}
	public static String numVehiculosCadena(TreeMap<Matricula,Vehiculo> treemap)
	{
			String lista = TreeMapToArrayList.numVehiculos(treemap).toString();
			return lista;
	}
	public static ArrayList<Integer> numEmpleados(TreeMap<String,Empleado> treemap)
	{
			ArrayList<Integer> numVehiculos = new ArrayList<Integer>(treemap.size());
			return numVehiculos;
	}
	public static String numEmpleadosCadena(TreeMap<String,Empleado> treemap)
	{
			String lista = TreeMapToArrayList.numEmpleados(treemap).toString();
			return lista;
	}
	public static String DNIsEmpleados(TreeMap<String,Empleado> treemap)
	{
		Set<String> a = treemap.keySet();
		return a.toString();
	}
	public static ArrayList<Cliente> listarClientes(TreeMap<String, Cliente> treemap) {
		ArrayList<Cliente> lista = new ArrayList<Cliente>(treemap.values());
		return lista;
	}
}
