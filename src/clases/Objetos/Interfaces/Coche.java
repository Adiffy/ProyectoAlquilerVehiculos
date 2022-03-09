package clases.Objetos.Interfaces;

import exceptions.EmisionesNoValidasException;
import exceptions.NumPlazasNoValidoException;

public interface Coche {

	
	//Propiedades
	
	int numPlazas = 1; //numero de plazas que tiene el coche
	String tipo = ""; //el tipo del coche in "Familiar, Deportivo"
	
	private void setNum_Plazas(int num) {
	}
		
	
	public static int getNumPlazas() {
		int n = numPlazas; //Clonamos
		return n;
	}
	
	private void setTipo(String Tipo) throws EmisionesNoValidasException {
	}
	
	
	public static String getTipo()
	{
		String copia = tipo;
		return copia;
	}
}
