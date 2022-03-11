package clasesObjetos;

import java.util.GregorianCalendar;

import clasesObjetosInterfaces.Alquilable;
import exceptions.FechaNoValidaException;


public abstract class Vehiculo implements Comparable<Vehiculo>, Alquilable {

	//Propiedades
	private Matricula matricula; //Matr�cula del veh�culo
	private String marca;	//Marca del coche. Ejemplo: "Renault"
	private String modelo; 	//Modelo del coche (puede ser num�rico y/o contener letras. Por ejemplo--> Audi "R8") 
	private String Color; //Color del veh�culo
	private GregorianCalendar fechaAlta;
	private int kms; //Los kil�metros que tiene
	private Categoria categoria;
	private Direccion ubi; 
	private boolean alquilado=false; //Por defecto NO est� alquilado
	
	//Variables que pueden ser nulas. Se usar�n en caso de alquiler
	private GregorianCalendar FechaInicioAlquiler = new GregorianCalendar();
	private GregorianCalendar FechaPrevistaFinAlquiler = new GregorianCalendar();
	private GregorianCalendar FechaDevolucion = new GregorianCalendar();
	
	//Getters y Setters 
	
	
	public String getModelo() {
		return modelo;
	}
	private void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	
	public GregorianCalendar getFechaInicioAlquiler() {
		return FechaInicioAlquiler;
	}
	public void setFechaInicioAlquiler(GregorianCalendar fechaInicioAlquiler) {
		FechaInicioAlquiler = fechaInicioAlquiler;
	}
	public Matricula getMatricula() {
		Matricula copia = new Matricula(this.matricula);
		return copia;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		if (marca.length()<10 && marca.length()>=2)
		{
			String clon = marca; 
			this.marca = clon;
		}
	}
	
	/**
	 * @return El n� de d�as que el veh�culo est� alquilado
	 */
	public int getNumDias()
	{
		@SuppressWarnings("static-access")
		int num = ((this.FechaDevolucion.MONTH - this.FechaInicioAlquiler.MONTH) + (this.FechaDevolucion.DATE - this.FechaInicioAlquiler.DATE));
		return num;
	}
	
	
	public GregorianCalendar getFechaPrevistaFinAlquiler() {
		return (GregorianCalendar) FechaPrevistaFinAlquiler.clone();
	}
	@SuppressWarnings("static-access")
	public void setFechaPrevistaFinAlquiler(GregorianCalendar fechaPrevistaFinAlquiler) throws FechaNoValidaException {
		if (fechaPrevistaFinAlquiler.DATE > GregorianCalendar.getInstance().DATE && (fechaPrevistaFinAlquiler.MONTH > GregorianCalendar.getInstance().MONTH || fechaPrevistaFinAlquiler.DAY_OF_YEAR > GregorianCalendar.getInstance().DAY_OF_YEAR))
		{
			FechaPrevistaFinAlquiler = fechaPrevistaFinAlquiler;
		}else 
		{
			throw new FechaNoValidaException("Fecha anterior a la actual");
		}
		
	}
	@SuppressWarnings("static-access")
	public GregorianCalendar getFechaAlta() {
		//Obtenemos los datos necesarios para construir un clon
		int dia = this.fechaAlta.DAY_OF_MONTH;
		int mes = this.fechaAlta.MONTH;
		int anno = this.fechaAlta.YEAR;
		GregorianCalendar copia = new GregorianCalendar(anno,mes,dia); //Hacemos una copia 
		return copia; //Devolvemos la copia
	}
	public void setFechaAlta(GregorianCalendar fechaAlta) {
		this.fechaAlta = fechaAlta;
	}
	public int getKms() {
		return kms;
	}
	public void setKms(int kms) {
		if (kms>=0)	//Puede ser 0 o m�s PERO NO negativo
		{
			this.kms = kms;
		}
	}
	public Direccion getUbi() {
		Direccion cop = new Direccion(ubi);
		return cop; //Devolvemos una copia
	}
	public void setUbi(Direccion ubi) {
		Direccion cop = new Direccion(ubi); //copiamos
		if (! this.isAlquilado()) {
			this.ubi = cop;
		}else {
			//TODO excepci�n ---> NO se puede meter una ubicaci�n si est� alquilado por alguien
		}
	}
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void setCategoria(String codigo, String descripcion, double recargo) {
		Categoria creada = new Categoria(codigo,descripcion,recargo);
		this.categoria = creada;
	}
	public boolean isAlquilado() {
		if (this.alquilado==true)
		{
			return true;
		}else {
			return false;
		}
	}
	/**
	 *	<p>
	 *  Alquilado es la propiedad que indica si un coche est� alquilado o no mediante un {@code boolean}.
	 *  		Ejemplo de uso:
	 *  				audi.setAlquilado(true); //Ahora este coche est� alquilado
	 *  </p>
	 * @param alquilado
	 */
	public void setAlquilado(boolean alquilado) {
		this.alquilado = alquilado;
	}
	
	
	//Constructores
	public Vehiculo(Matricula matricula,String marca, String modelo, String color) {
		super();
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setColor(color);
		this.setMatricula(matricula);
	}
	
	public Vehiculo(Matricula matricula, String marca, String modelo, String color,GregorianCalendar fecha_alta ) {
		super();	
		this.setAlquilado(alquilado);
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setColor(color);
		this.setFechaAlta(fecha_alta);
		this.setMatricula(matricula);
	}
	/**
	 * Constructor de copia para veh�culo
	 * @param vehiculo el veh�culo a copiar
	 */
	public Vehiculo(Vehiculo vehiculo) {
		
		this.setModelo(vehiculo.getModelo());
		this.setMarca(vehiculo.getMarca());
		this.setAlquilado(vehiculo.isAlquilado());
		this.setCategoria(vehiculo.getCategoria());
		this.setFechaAlta(vehiculo.getFechaAlta());
		this.setKms(vehiculo.getKms());
		this.setUbi(vehiculo.getUbi());
		this.setColor(vehiculo.getColor());
		this.setMatricula(vehiculo.getMatricula());
	}
	
public Vehiculo(Matricula matricula, String marca, String modelo,Categoria categoria, String color, boolean alquilado, Direccion oficina, int num_km) {
		
		this.setMatricula(matricula);
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setAlquilado(alquilado);
		this.setColor(color);
		this.setCategoria(categoria);
		this.setUbi(oficina);
		this.setKms(num_km);
	}
/**
 * Constructor completo de veh�culo
 * @param matricula	 La matr�cula del veh�culo del tipo {@code Matricula}
 * @param marca	La marca del veh�culo del tipo {@code String}. Ejemplo: "Skoda"
 * @param modelo El modelo del veh�culo del tipo {@code String}. Ejemplo: "R8"
 * @param categoria La categor�a del veh�culo del tipo {@code Categoria}
 * @param color	El color del veh�culo del tipo {@code String} puede ser nulo (""). Ejemplo: "Naranja"
 * @param fecha_alta	La fecha en la que el veh�culo entr� en la base de datos, tipo {@code GregorianCalendar}
 * @param alquilado El buleano que identifica si el veh�culo se encuentra alquilado {@value true} o no {@value false}
 * @param oficina La oficina o ubicaci�n en la que se encuentra el veh�culo si NO est� alquilado {@value false}, de tipo {@code Direccion}
 * @param num_km El n� de kil�metros que tiene el coche de forma entera, es decir, de tipo {@code int}
 */
public Vehiculo(Matricula matricula, String marca, String modelo,Categoria categoria, String color, GregorianCalendar fecha_alta ,boolean alquilado, Direccion oficina, int num_km) {
	
	this.setMatricula(matricula);
	this.setMarca(marca);
	this.setModelo(modelo);
	this.setAlquilado(alquilado);
	this.setColor(color);
	this.setCategoria(categoria);
	this.setUbi(oficina);
	this.setKms(num_km);
	this.setFechaAlta(fecha_alta);
}

/**
 * 	Constructor completo para un veh�culo que va a ser alquilado
 * @param matricula	 La matr�cula del veh�culo del tipo {@code Matricula}
 * @param marca	La marca del veh�culo del tipo {@code String}. Ejemplo: "Skoda"
 * @param modelo El modelo del veh�culo del tipo {@code String}. Ejemplo: "R8"
 * @param categoria La categor�a del veh�culo del tipo {@code Categoria}
 * @param color	El color del veh�culo del tipo {@code String} puede ser nulo (""). Ejemplo: "Naranja"
 * @param fecha_alta	La fecha en la que el veh�culo entr� en la base de datos, tipo {@code GregorianCalendar}
 * @param alquilado El buleano que identifica si el veh�culo se encuentra alquilado {@value true} o no {@value false}
 * @param oficina La oficina o ubicaci�n en la que se encuentra el veh�culo si NO est� alquilado {@value false}, de tipo {@code Direccion}
 * @param num_km El n� de kil�metros que tiene el coche de forma entera, es decir, de tipo {@code int}
 * @param FechaInicioAlquiler	La fecha prevista para el alquiler del vehiculo en cuesti�n. Tipo {@code GregorianCalendar}
 * @param FechaFinAlquiler	La fehca en la que se espera (a priori) que el cliente deje el veh�culo. Tambi�n {@code GregorianCalendar}
 * @throws FechaNoValidaException 
 */
public Vehiculo(Matricula matricula, String marca, String modelo,Categoria categoria, String color, GregorianCalendar fecha_alta ,boolean alquilado, Direccion oficina, int num_km, GregorianCalendar FechaInicioAlquiler, GregorianCalendar FechaFinAlquiler) throws FechaNoValidaException {
	
	this.setMatricula(matricula);
	this.setMarca(marca);
	this.setModelo(modelo);
	this.setAlquilado(alquilado);
	this.setColor(color);
	this.setCategoria(categoria);
	this.setUbi(oficina);
	this.setKms(num_km);
	this.setFechaAlta(fecha_alta);
	this.setFechaInicioAlquiler(FechaInicioAlquiler);
	this.setFechaPrevistaFinAlquiler(FechaFinAlquiler);
}

	public Vehiculo(Matricula matricula, String marca, String modelo, String color, boolean alquilado) {
		
		this.setMatricula(matricula);
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setAlquilado(alquilado);
		this.setColor(color);
	}
	
	public String getNombreVehiculo() {
		return this.getMarca() + "" + this.getModelo() + ".";
	}
	
	@Override
	public boolean equals(Object otro)
	{
		if (this.hashCode()==otro.hashCode())
		{
			return true;
		} else if (this.getClass() != otro.getClass())
		{
			return false;
		}
		Vehiculo copia = (Vehiculo) otro;
		return (copia.matricula==this.getMatricula());
	}
	
	@Override
	public int compareTo(Vehiculo otro) 
	{
		int resultado = 0;
		Vehiculo copia = (Vehiculo) otro; //Hacemos una copia
		resultado = this.getNombreVehiculo().compareToIgnoreCase(copia.getNombreVehiculo()); //Comparamos sus nombres completos
		return resultado;
	}
	
	//public abstract double CalculaAlquiler();
	
}
