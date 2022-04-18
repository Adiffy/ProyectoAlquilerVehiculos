package clasesObjetosInterfaces;

import exceptions.NumPlazasNoValidoException;
import exceptions.TipoNoValidoException;

public interface Coche {

	
	//Propiedades
	
	int numPlazas = 1; //numero de plazas que tiene el coche
	String tipo = ""; //el tipo del coche in "Familiar, Deportivo, 4x4"
	
	
	public void setNumPlazas(int num)  throws NumPlazasNoValidoException;
		
	
	public static int getNumPlazas() {
		int n = numPlazas; //Clonamos
		return n;
	}
	
	public void setTipo(String Tipo) throws  TipoNoValidoException;
	
	
	public static String getTipo()
	{
		String copia = tipo;
		return copia;
	}
	
	public String toString();
}
