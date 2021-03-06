package clasesObjetos;

import java.io.Serializable;
import java.util.GregorianCalendar;

import clasesObjetosInterfaces.Coche;
import exceptions.ConsumoNoValidoException;
import exceptions.EmisionesNoValidasException;
import exceptions.NumPlazasNoValidoException;
import exceptions.PotenciaNoValidaException;
import exceptions.TipoNoValidoException;


public final class CocheCombustion extends DeCombustion implements Coche, Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	int numPlazas = 1; //numero de plazas que tiene el coche
	String tipo = ""; //el tipo del coche in "Familiar, Deportivo"
	
	private int precioBase = 50;
	
	//Getters & Setters
	@Override
	public void setNumPlazas(int num) throws NumPlazasNoValidoException
	{
//		if (num>0 && num<=15) //Suponemos que en un coche no caben m�s de 8 personas
//		{
			this.numPlazas = num;
//		}else {
//			throw new NumPlazasNoValidoException("Número de plazas no válido");
//		}
	}
	public void setTipo(String Tipo) throws TipoNoValidoException
	{
		if (Tipo.compareToIgnoreCase("deportivo")==0||Tipo.compareToIgnoreCase("familiar")==0||Tipo.compareToIgnoreCase("todoterreno")==0 ||Tipo.compareToIgnoreCase("4x4")==0) //si es A o B o C
		{
			String trab = Tipo;
			this.tipo = trab;
		}else {
			throw new TipoNoValidoException("El tipo puede ser únicamente 'familiar', 'deportivo' o 'todoterreno(4x4)'");
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
	 * Crea un coche de combustión
	 * Para ello necesita los siguientes parámetros:
	 * @param matricula	La matrícula del coche de tipo {@code Matrícula} ({@code int} numeros, {@code String} letras)
	 * @param marca	Una {@code String} que se refiere a su marca. Por ejemplo: "Skoda".
	 * @param modelo Una {@code String} que será su modelo. 
	 * @param categoria Una {@code Categoria} ({@code String} Codigo, {@code String} descripci�n, {@code double} recargo)
	 * @param color	Una {@code String} que indicar� el color
	 * @param fecha_alta	La fecha que se dio de alta o adquirió el vehículo. Tipo {@code GregorianCalendar}
	 * @param alquilado	Un {@code boolean} que reflejará el estado actual del coche (Alquilado o no).
	 * @param oficina La oficina en la que se encuentra (si el coche no est� alquilado). Tipo {@code Direccion} ({@code String} nombreVia, {@code String} numVia, {@code String} codigoPostal, {@code String} localidad)
	 * @param num_km Los kilómetros recorridos por el vehículo. Tipo {@code int}.
	 * @param num_plazas El número de plazas que dispone el coche. Tipo {@code int}.
	 * @param oficina2 
	 * @param consumo El consumo del coche (Litros por cada 100km). Tipo {@code double}.
	 * @param tipo	El tipo del coche, puede ser "Familiar, Deportivo". Tipo {@code String}.
	 * @param potencia	El caballaje del coche medido en CV. Tipo {@code int}.
	 * @param emisiones El {@code String} que indica el nivel de emisiones del coche. Puede ser "A", "B" o "C".
	 * 
	 * @throws EmisionesNoValidasException Error. Las emisiones no son "A" ni "B" ni "C".
	 * @throws NumPlazasNoValidoException  Error. El n�mero de plazas es 0 o muy alto(>8).
	 * @throws PotenciaNoValidaException 
	 * @throws ConsumoNoValidoException 
	 * @throws TipoNoValidoException 
	 */
	public CocheCombustion(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta, Oficina oficina, int num_km, int num_plazas, double consumo, String tipo, int potencia, String emisiones) throws EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, TipoNoValidoException {
		super(matricula, marca, modelo, categoria, color, fecha_alta, oficina, num_km, consumo, potencia, emisiones);
		this.setNumPlazas(num_plazas);
		this.setTipo(tipo);
	}
	

	@Override
	public boolean equals(Object a)
	{
		if (this == a)
		{
			return true;
		}
		if (a == null || this.getClass() != a.getClass())
		{
			return false;
		}
		CocheCombustion dado = (CocheCombustion) a; //Hacemos que lo trate como COCHE y no OBJETO
		
		if (this.matricula == dado.matricula) { //Comparamos sus matr�culas
			return true;
		}else {
			return false;	//Ya a estas alturas ha fallado todos los controles por lo que, es falso
		}
	}
	
	@Override
	public double PrecioAlquiler() {
		int precioBase = this.precioBase;
		int porcentaje = 0;
		double recargo = 0;
		double precio;
		
		precio = precioBase + (porcentaje*precioBase)/100;
		
		recargo = this.getCategoria().getRecargo();	//Averiguamos cuanto es el porcentaje: 10,15,30 ...
		recargo= (recargo*this.precioBase)/100;	//Calculamos el porcentaje
		precio += recargo;
		if (this.getOficina().isDeAeropuerto())
		{
			precio += (precioBase*10)/100; 
		}
		
		return precio;
	}
	
/*	
	@Override
	public double precioAlquiler() {
		return (precioBase * Alquiler.getNumDias());
	}*/

	
	
	/*	Constructor inútil ya que no puede existir un vehículo porque es abstracto
	public CocheCombustion(Vehiculo vehiculo, int numplazas, String tipo) throws EmisionesNoValidasException, NumPlazasNoValidoException {
		super(vehiculo);
		this.setTipo(tipo);
		this.setNumPlazas(numplazas);
	}*/
}
