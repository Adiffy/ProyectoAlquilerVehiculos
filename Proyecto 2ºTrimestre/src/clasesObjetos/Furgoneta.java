package clasesObjetos;

import java.util.GregorianCalendar;

import exceptions.CapacidadDeCargaNoValidaException;
import exceptions.ConsumoNoValidoException;
import exceptions.EmisionesNoValidasException;
import exceptions.LetrasMatriculaNoValidasException;
import exceptions.NumeroMatriculaNoValidoException;
import exceptions.PotenciaNoValidaException;
import exceptions.TipoCarnetNoValidoException;

public class Furgoneta extends DeCombustion {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -622124846681716489L;
	//Propiedades
	private int capacidadCarga; //En metros c�bicos
	private String carnetRequerido; //Puede ser  B / C / D
	private int precioBase = 70;

	//Setters & Getters 
	
	public int getCapacidadCarga() {
		int copia = capacidadCarga;
		return copia ;
	}

	public void setCapacidadCarga(int capacidadCarga) throws CapacidadDeCargaNoValidaException {
		if (capacidadCarga>0)
		{
			//Es un entero, no hace falta hacer una copia.
			this.capacidadCarga = capacidadCarga;
		}else {
			throw new CapacidadDeCargaNoValidaException("Capacidad de carga muy baja");
		}
		
	}

	public String getCarnetRequerido() {
		String copia = carnetRequerido;
		return copia;
	}

	public void setCarnetRequerido(String carnetRequerido) throws TipoCarnetNoValidoException {
		if (carnetRequerido.compareToIgnoreCase("B")==0 || carnetRequerido.compareToIgnoreCase("C")==0 || carnetRequerido.compareToIgnoreCase("D")==0 )
		{
			String copia = carnetRequerido;
			this.carnetRequerido = copia;
		}else {
			throw new TipoCarnetNoValidoException("El carnet debe ser B, C o D");
		}
	}

	//Constructores
	/**
	 * Constructor completo de {@code Furgoneta}
	 * @param matricula	Objeto tipo {@code Matricula} 
	 * @param marca	Marca de la furgoneta, por ejemplo "skoda" en forma de {@code String}
	 * @param modelo	Modelo de la furgoneta, tipo {@code String}
	 * @param categoria	Categor�a de la furgoneta, tipo {@code Categoria}
	 * @param color	Color de la furgoneta, por ejemplo "blanco". Tipo {@code String}
	 * @param fecha_alta	Fecha de alta del veh�culo, del tipo {@code GregorianCalendar}
	 * @param alquilado {@code boolean} que indica si el veh�culo se encuentra alquilado (no disponible) o no (disponible)
	 * @param oficina	{@code Direccion} que indica donde se encuentra la furgoneta (si no est� alquilada)
	 * @param num_km	Los kil�metros que tiene el veh�culo en forma de {@code int}
	 * @param consumo	El consumo (Litros por cada 100km) de la furgoneta, es un {@code double}
	 * @param caballaje	La potencia medida en {@code int} (medida en CV)
	 * @param emisiones	El tipo de emisiones ({@code String} Puede ser A, B o C
	 * @param capacidadCarga	El {@code int} con la capacidad de carga en kg
	 * @param carnetRequerido	El {@code String} con el carnet requerido. Por ejemplo: "AM"
	 * @throws EmisionesNoValidasException
	 * @throws PotenciaNoValidaException 
	 * @throws ConsumoNoValidoException 
	 */
	public Furgoneta(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta, Oficina oficina, int num_km, double consumo,
			int caballaje, String emisiones, int capacidadCarga, String carnetRequerido)
			throws EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException {
		super(matricula, marca, modelo, categoria, color, fecha_alta, oficina, num_km, consumo, caballaje,
				emisiones);
		this.capacidadCarga = capacidadCarga;
		this.carnetRequerido = carnetRequerido;
	}
	public Furgoneta(Furgoneta aCopiar) throws EmisionesNoValidasException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException
	{
		super(aCopiar.getMatricula(),aCopiar.getMarca(), aCopiar.getModelo(), aCopiar.getCategoria(), aCopiar.getColor(), aCopiar.getFechaAlta(), null, aCopiar.getKms(), aCopiar.getConsumo(), aCopiar.getPotencia(),
				aCopiar.getEmisiones());
		this.capacidadCarga = aCopiar.getCapacidadCarga();
		this.carnetRequerido = aCopiar.getCarnetRequerido();
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
		Furgoneta dada = (Furgoneta) a; //Hacemos que lo trate como FURGONETA y no OBJETO
		
		if (this.matricula == dada.matricula) { //Comparamos sus matr�culas
			return true;
		}else {
			return false;	//Ya a estas alturas ha fallado todos los controles por lo que, es falso
		}
	}
	
		@Override
		public double PrecioAlquiler() {
			int precioBase = this.precioBase;
			int porcentaje =0; //de combustion
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
		

	

}
