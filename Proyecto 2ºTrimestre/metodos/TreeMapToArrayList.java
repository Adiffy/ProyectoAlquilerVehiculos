package metodos;

import java.util.ArrayList;
import java.util.TreeMap;

import clasesObjetos.Matricula;
import clasesObjetos.Vehiculo;

public class TreeMapToArrayList {

	
	public static ArrayList<Vehiculo> listar(TreeMap<Matricula,Vehiculo> treemap) {
		
			ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>(treemap.values());
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
}
