package clases.Objetos;

import java.io.Serializable;
import java.util.GregorianCalendar;

import clases.Objetos.Interfaces.Coche;
import exceptions.EmisionesNoValidasException;
import exceptions.NumPlazasNoValidoException;

@SuppressWarnings("serial")
public final class CocheCombustion extends DeCombustion implements Coche, Serializable{

	
	
	//Propiedades
	int numPlazas = 1; //numero de plazas que tiene el coche
	String tipo = ""; //el tipo del coche in "Familiar, Deportivo"
	
	//Getters & Setters
	private void setNumPlazas(int num) throws NumPlazasNoValidoException
	{
		if (num>0 && num<=8) //Suponemos que en un coche no caben más de 8 personas
		{
			this.numPlazas = num;
		}else {
			throw new NumPlazasNoValidoException("Número de plazas no válido");
		}
	}
	private void setTipo(String Tipo) throws EmisionesNoValidasException 
	{
		if (Tipo.equalsIgnoreCase("deportivo")||Tipo.equalsIgnoreCase("familiar")||Tipo.equalsIgnoreCase("todoterreno")) //si es A o B o C
		{
			this.tipo = Tipo;
		}else {
			throw new EmisionesNoValidasException("El tipo puede ser únicamente 'familiar', 'deportivo' o 'todoterreno'");
		}
	}
	
	public CocheCombustion(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta, boolean alquilado, Direccion oficina, int num_km, int num_plazas, double consumo, String tipo, int potencia, String emisiones) throws EmisionesNoValidasException, NumPlazasNoValidoException {
		super(matricula, marca, modelo, categoria, color, fecha_alta, alquilado, oficina, num_km);
		this.setNumPlazas(num_plazas);
		this.setConsumo(consumo);
		this.setPotencia(potencia);
		this.setEmisiones(emisiones);
	}
	
	public CocheCombustion(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta, boolean alquilado, Direccion oficina, int num_km, double consumo,
			int caballaje, String emisiones, int numplazas, String tipo) throws EmisionesNoValidasException, NumPlazasNoValidoException {
		super(matricula, marca, modelo, categoria, color, fecha_alta, alquilado, oficina, num_km, consumo, caballaje,
				emisiones);
		this.setTipo(tipo);
		this.setNumPlazas(numplazas);
	}
	
	public CocheCombustion(Vehiculo vehiculo) {
		super(vehiculo);
		
	}
	
	/*	Constructor inútil ya que no puede existir un vehículo porque es abstracto
	public CocheCombustion(Vehiculo vehiculo, int numplazas, String tipo) throws EmisionesNoValidasException, NumPlazasNoValidoException {
		super(vehiculo);
		this.setTipo(tipo);
		this.setNumPlazas(numplazas);
	}*/
}
