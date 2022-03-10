package comparadores;

import java.util.Comparator;

import clasesObjetos.Cliente;

public class ComparaClientePorNombre implements Comparator<Cliente> {

	public ComparaClientePorNombre() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Cliente o1, Cliente o2) {
		return o1.getNombreCompleto().compareToIgnoreCase(o2.getNombreCompleto());
		 
	}

}
