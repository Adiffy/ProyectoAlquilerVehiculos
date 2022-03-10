package comparadores;

import java.util.Comparator;

import clasesObjetos.Empleado;

public class ComparaEmpleadoPorNombre implements Comparator<Empleado>{

	public ComparaEmpleadoPorNombre() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int compare(Empleado o1, Empleado o2) {
		return o1.getNombreCompleto().compareToIgnoreCase(o2.getNombreCompleto());
		 
	}
}
