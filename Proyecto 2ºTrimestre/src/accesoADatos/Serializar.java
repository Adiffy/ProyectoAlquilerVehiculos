package acceso.a.Datos;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import clases.Objetos.Cliente;

public class Serializar {

	public static void grabaOficina(ArrayList<Cliente> l) //TODO no será Cliente, sino Empleado,Cliente,Vehiculos ... y se llamará grabaEmpresa
	{
		FileOutputStream f = null;
		ObjectOutputStream o = null;
		try {
			f = new FileOutputStream("oficina.ser");
			o = new ObjectOutputStream(f);
			o.writeObject(l);
		} catch (FileNotFoundException e) {
			System.out.println("Error al intentar grabar: " + e.getLocalizedMessage());
			
		}
		 catch (IOException e) {
			 System.out.println("Error al intentar grabar: " + e.getLocalizedMessage());
		}
	}
	
	
	
}
