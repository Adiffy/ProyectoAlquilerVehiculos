package metodos;

import java.util.ArrayList;
import java.util.Set;
import java.util.TreeMap;

import clasesObjetos.Cliente;
import clasesObjetos.CocheElectrico;
import clasesObjetos.DeCombustion;
import clasesObjetos.Electrico;
import clasesObjetos.Empleado;
import clasesObjetos.Matricula;
import clasesObjetos.Moto;
import clasesObjetos.Oficina;
import clasesObjetos.Vehiculo;

public class TreeMapToArrayList {

	
	public static ArrayList<Vehiculo> Garaje(TreeMap<Matricula,Vehiculo> treemap) {
		
		ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>(treemap.values());
		return lista;
	}
	public static ArrayList<Empleado> Empleados(TreeMap<String,Empleado> treemap) {
		
		ArrayList<Empleado> lista = new ArrayList<Empleado>(treemap.values());
		return lista;
	
	}
	public static ArrayList<Oficina> Oficinas(TreeMap<String,Oficina> treemap) {
		
		ArrayList<Oficina> lista = new ArrayList<Oficina>(treemap.values());
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
	public static String CodigoOficinas(TreeMap<String,Oficina> treemap)
	{
		Set<String> a = treemap.keySet();
		return a.toString();
	}
	public static ArrayList<Cliente> Clientes(TreeMap<String, Cliente> treemap) {
		ArrayList<Cliente> lista = new ArrayList<Cliente>(treemap.values());
		return lista;
	}
	@SuppressWarnings("null")
	public static ArrayList<CocheElectrico> CocheElectrico(TreeMap<Matricula, Vehiculo> treemap) {
		ArrayList<CocheElectrico> lista = null;
		ArrayList<Vehiculo> listado = new ArrayList<Vehiculo>(treemap.values());
		for (Vehiculo a:listado)
		{	//Si viene de electrico
			if (a.getClass().getSimpleName().compareToIgnoreCase("CocheElectrico")==0)	
			{
				//Lo a�adimos a la lista
				 lista.add((CocheElectrico) a);
			}
	}
		return lista;
	}
	@SuppressWarnings("null")
	public static ArrayList<Electrico> Electricos(TreeMap<Matricula, Vehiculo> treemap) {
		ArrayList<Electrico> lista = null;
		ArrayList<Vehiculo> listado = new ArrayList<Vehiculo>(treemap.values());
		for (Vehiculo a:listado)
		{	//Si viene de electrico
			if (a.getClass().getSimpleName().compareToIgnoreCase("Moto")==0 || a.getClass().getSimpleName().compareToIgnoreCase("CocheElectrico")==0)	
			{
				//Lo a�adimos a la lista
				 lista.add((Electrico) a);
			}
	}
		return lista;//devuelve la lista completa
	}
	@SuppressWarnings("null")
	public static ArrayList<DeCombustion> Combustion(TreeMap<Matricula, Vehiculo> treemap) {
		ArrayList<DeCombustion> lista = null;
		ArrayList<Vehiculo> listado = new ArrayList<Vehiculo>(treemap.values());
		for (Vehiculo a:listado)
		{	//Si viene de Combustion
			if (a.getClass().getSimpleName().compareToIgnoreCase("Furgoneta")==0 || a.getClass().getSimpleName().compareToIgnoreCase("CocheCombustion")==0)	
			{
				//Lo a�adimos a la lista
				 lista.add((DeCombustion) a);
			}
	}
		return lista;//devuelve la lista completa
	}
	@SuppressWarnings("null")
	public static ArrayList<DeCombustion> CocheCombustion(TreeMap<Matricula, Vehiculo> treemap) {
		ArrayList<DeCombustion> lista = null;
		ArrayList<Vehiculo> listado = new ArrayList<Vehiculo>(treemap.values());
		for (Vehiculo a:listado)
		{	//Si viene de Combustion
			if (a.getClass().getSimpleName().compareToIgnoreCase("CocheCombustion")==0)	
			{
				//Lo a�adimos a la lista
				 lista.add((DeCombustion) a);
			}
	}
		return lista;//devuelve la lista completa
	}
	@SuppressWarnings("null")
	public static ArrayList<DeCombustion> Furgoneta(TreeMap<Matricula, Vehiculo> treemap) {
		ArrayList<DeCombustion> lista = null;
		ArrayList<Vehiculo> listado = new ArrayList<Vehiculo>(treemap.values());
		for (Vehiculo a:listado)
		{	//Si viene de Combustion
			if (a.getClass().getSimpleName().compareToIgnoreCase("Furgoneta")==0)	
			{
				//Lo a�adimos a la lista
				 lista.add((DeCombustion) a);
			}
	}
		return lista;//devuelve la lista completa
	}
	

	@SuppressWarnings("null")
	public static ArrayList<Moto> Motos(TreeMap<Matricula, Vehiculo> treemap) {
		ArrayList<Moto> lista = null;
		ArrayList<Vehiculo> listado = new ArrayList<Vehiculo>(treemap.values());
		for (Vehiculo a:listado)
		{	//Si viene de electrico
			if (a.getClass().getSimpleName().compareToIgnoreCase("Moto")==0)	
			{
				//Lo a�adimos a la lista
				 lista.add((Moto) a);
			}
	}
		return lista;
	}
	
	
}
