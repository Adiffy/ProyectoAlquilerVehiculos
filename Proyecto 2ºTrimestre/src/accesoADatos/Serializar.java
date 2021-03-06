package accesoADatos;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import clasesObjetos.Empresa;

public class Serializar {

	public static void grabaEmpresa(Empresa l) 
	{
		FileOutputStream f = null;
		ObjectOutputStream o = null;
		try {
			f = new FileOutputStream("empresa.ser");
			o = new ObjectOutputStream(f);
			o.writeObject(l);
		} catch (FileNotFoundException e) {
			System.out.println("Error al intentar grabar: " + e.getLocalizedMessage());
			
		}
		 catch (IOException i) {
			 System.out.println("Error al intentar grabar: " + i.getLocalizedMessage());
		}
	}
	
	public static Empresa LeeEmpresa() 
	{
		Empresa empresa = null;	//Preparamos el cascar?n vac?o para meterle la empresa desde fichero o crear con un NEW
		File f = new File ("empresa.ser");
		
		if (f.exists()) //Si existe el archivo lo lee
		{
			//Intentamos leer
			try 
			{
				FileInputStream file = new FileInputStream("empresa.ser");
				@SuppressWarnings("resource")	//Quitamos el Warning para evitar errores al exportar
				ObjectInputStream input = new ObjectInputStream(file);
				empresa = (Empresa) input.readObject();
			}catch (ClassNotFoundException e)
			{
				System.out.println("Error al intentar leer: " + e.getLocalizedMessage());
			} catch (IOException e) {
				System.out.println("Error al intentar leer: " + e.getLocalizedMessage());
			}
			
		}else //Si no existe el archivo lo crea con el constructor m?s b?sico 
		{
																												//Scanner lector = new Scanner(System.in);	//Para crear la empresa el m?todo PideDato necesita un Scanner
			empresa = new Empresa();
			grabaEmpresa(empresa);
		}
		
		return empresa;	//Devolvemos la empresa que hemos le?do o creado
		
	} 
	
}
