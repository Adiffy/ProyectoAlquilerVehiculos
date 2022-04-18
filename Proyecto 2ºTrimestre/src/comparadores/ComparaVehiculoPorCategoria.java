package comparadores;

import java.util.Comparator;

import clasesObjetos.Vehiculo;

public class ComparaVehiculoPorCategoria implements Comparator<Vehiculo> {

	@Override
	public int compare(Vehiculo o1, Vehiculo o2) {
		//Comparamos los codigos de sus categor�as ignorando may�sculas
		return o1.getCategoria().getCodigo().compareToIgnoreCase(o2.getCategoria().getCodigo());
	}

	
}
