package clasesObjetos;

import java.io.Serializable;
import java.util.GregorianCalendar;

import clasesObjetosInterfaces.Coche;
import exceptions.EmisionesNoValidasException;
import exceptions.NumPlazasNoValidoException;

@SuppressWarnings("serial")
public final class CocheCombustion extends DeCombustion implements Coche, Serializable{

	
	
	//Propiedades
	int numPlazas = 1; //numero de plazas que tiene el coche
	String tipo = ""; //el tipo del coche in "Familiar, Deportivo"
	
	
	//Getters & Setters
	@Override
	public void setNumPlazas(int num) throws NumPlazasNoValidoException
	{
		if (num>0 && num<=8) //Suponemos que en un coche no caben m�s de 8 personas
		{
			this.numPlazas = num;
		}else {
			throw new NumPlazasNoValidoException("N�mero de plazas no v�lido");
		}
	}
	public void setTipo(String Tipo) throws EmisionesNoValidasException 
	{
		if (Tipo.equalsIgnoreCase("deportivo")||Tipo.equalsIgnoreCase("familiar")||Tipo.equalsIgnoreCase("todoterreno") ||Tipo.equalsIgnoreCase("4x4")) //si es A o B o C
		{
			String trab = Tipo;
			this.tipo = trab;
		}else {
			throw new EmisionesNoValidasException("El tipo puede ser �nicamente 'familiar', 'deportivo' o 'todoterreno(4x4)'");
		}
	}

	public int getNumPlazas()
	{
		int cop = this.numPlazas; //clonamos en variable de trabajo
		return cop;
	}
	public String getTipo()
	{
		String cop = this.tipo; //Clonamos la string
		return cop;
	}
	
	/**
	 * Crea un coche de combusti�n
	 * Para ello necesita los siguientes par�metros:
	 * @param matricula	La matr�cula del coche de tipo {@code Matr�cula} ({@code int} numeros, {@code String} letras)
	 * @param marca	Una {@code String} que se refiere a su marca. Por ejemplo: "Skoda".
	 * @param modelo Una {@code String} que ser� su modelo. 
	 * @param categoria Una {@code Categoria} ({@code String} Codigo, {@code String} descripci�n, {@code double} recargo)
	 * @param color	Una {@code String} que indicar� el color
	 * @param fecha_alta	La fecha que se dio de alta o adquiri� el veh�culo. Tipo {@code GregorianCalendar}
	 * @param alquilado	Un {@code boolean} que reflejar� el estado actual del coche (Alquilado o no).
	 * @param oficina La oficina en la que se encuentra (si el coche no est� alquilado). Tipo {@code Direccion} ({@code String} nombreVia, {@code String} numVia, {@code String} codigoPostal, {@code String} localidad)
	 * @param num_km Los kil�metros recorridos por el veh�culo. Tipo {@code int}.
	 * @param num_plazas El n�mero de plazas que dispone el coche. Tipo {@code int}.
	 * @param consumo El consumo del coche (Litros por cada 100km). Tipo {@code double}.
	 * @param tipo	El tipo del coche, puede ser "Familiar, Deportivo". Tipo {@code String}.
	 * @param potencia	El caballaje del coche medido en CV. Tipo {@code int}.
	 * @param emisiones El {@code String} que indica el nivel de emisiones del coche. Puede ser "A", "B" o "C".
	 * 
	 * @throws EmisionesNoValidasException Error. Las emisiones no son "A" ni "B" ni "C".
	 * @throws NumPlazasNoValidoException  Error. El n�mero de plazas es 0 o muy alto(>8).
	 */
	public CocheCombustion(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta, boolean alquilado, Direccion oficina, int num_km, int num_plazas, double consumo, String tipo, int potencia, String emisiones, int numDias) throws EmisionesNoValidasException, NumPlazasNoValidoException {
		super(matricula, marca, modelo, categoria, color, fecha_alta, alquilado, oficina, num_km, consumo, potencia, emisiones);
		this.setNumPlazas(num_plazas);
		this.setTipo(tipo);
	}

	
	
	@Override
	public double precioAlquiler() {
		return (precioBase * this.getNumDias());
	}

	
	
	/*	Constructor in�til ya que no puede existir un veh�culo porque es abstracto
	public CocheCombustion(Vehiculo vehiculo, int numplazas, String tipo) throws EmisionesNoValidasException, NumPlazasNoValidoException {
		super(vehiculo);
		this.setTipo(tipo);
		this.setNumPlazas(numplazas);
	}*/
}
