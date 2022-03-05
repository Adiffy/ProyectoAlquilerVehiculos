package comparadores;

import java.util.Comparator;

import clasesObjetos.Cliente;

public class ComparaClientePorDNI implements Comparator<Cliente> {

	public ComparaClientePorDNI() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Cliente o1, Cliente o2) {
		return o1.getDni().compareToIgnoreCase(o2.getDni());
		 
	}

}
