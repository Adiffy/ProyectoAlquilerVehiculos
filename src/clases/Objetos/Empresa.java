package clases.Objetos;

import java.util.TreeMap;

public class Empresa {

	//Propiedades
	private TreeMap<Persona, Vehiculo> Oficina;

	public TreeMap<Persona, Vehiculo> getOficina() {
		return Oficina;
	}

	public void setOficina(TreeMap<Persona, Vehiculo> oficina) {
		Oficina = oficina;
	}
	
}
