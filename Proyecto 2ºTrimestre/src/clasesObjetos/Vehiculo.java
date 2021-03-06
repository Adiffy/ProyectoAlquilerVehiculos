package clasesObjetos;

import java.io.Serializable;
import java.util.GregorianCalendar;

import clasesObjetosInterfaces.Alquilable;
import exceptions.LetrasMatriculaNoValidasException;
import exceptions.NumeroMatriculaNoValidoException;
import exceptions.RecargoNoValidoException;


public abstract class Vehiculo implements Comparable<Vehiculo>, Alquilable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	protected Matricula matricula; //matrícula del vehículo
	private String marca;	//Marca del coche. Ejemplo: "Renault"
	private String modelo; 	//Modelo del coche (puede ser num�rico y/o contener letras. Por ejemplo--> Audi "R8") 
	private String Color; //Color del vehículo
	private GregorianCalendar fechaAlta;
	private int kms; //Los kilómetros que tiene
	private Categoria categoria;
	 
	private boolean alquilado=false; //Por defecto NO est� alquilado
	
	
	private Oficina Oficina;
	private boolean esElectrico;
	
	//Getters y Setters 
	
	
	public Oficina getOficina() {
		return new Oficina(Oficina);
	}
	public void setOficina(Oficina oficina) {
		//Ya est� creada (se supone que validada)
		Oficina = oficina;
	}
	public boolean isEsElectrico() {
		boolean cop = this.esElectrico;
		return cop;
	}
	public void setEsElectrico(boolean esElectrico) {
		boolean copia = esElectrico;
		this.esElectrico = copia;
	}
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
	
	
	public Matricula getMatricula() throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException {
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
	/*public Direccion getUbi() {
		Direccion cop = new Direccion(ubi);
		return cop; //Devolvemos una copia
	}
	public void setUbi(Direccion ubi) {
		Direccion cop = new Direccion(ubi); //copiamos
		if (! this.isAlquilado()) {
			this.ubi = cop;
		}else {
			//TODO excepci�n ---> NO se puede meter una ubicaci�n si est� alquilado por alguien
			 //Mala filosof�a
		}
	}*/
	public Categoria getCategoria() {
		return categoria;
	}
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	public void setCategoria(String codigo, String descripcion, double recargo) throws RecargoNoValidoException {
		Categoria creada = new Categoria(codigo,descripcion,recargo);
		this.categoria = creada;
	}
	public boolean isAlquilado() {
		boolean si = this.alquilado; 
		return si;
		/*if ()
		{
			return true;
		}else {				//TODO metodo para saber si seencuentra alquilado
			return false;
		}*/
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
	public Vehiculo(Matricula matricula,String marca, String modelo, String color, Oficina oficina) {
		super();
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setColor(color);
		this.setMatricula(matricula);
		this.setOficina(oficina);
	}
	
	public Vehiculo(Matricula matricula, String marca, String modelo, String color,GregorianCalendar fecha_alta,Oficina oficina ) {
		super();	

		this.setMarca(marca);
		this.setModelo(modelo);
		this.setColor(color);
		this.setFechaAlta(fecha_alta);
		this.setMatricula(matricula);
		this.setOficina(oficina);
	}
	/**
	 * Constructor de copia para vehículo
	 * @param vehiculo el vehículo a copiar
	 * @throws NumeroMatriculaNoValidoException 
	 * @throws LetrasMatriculaNoValidasException 
	 */
	public Vehiculo(Vehiculo vehiculo) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException {
		
		this.setModelo(vehiculo.getModelo());
		this.setMarca(vehiculo.getMarca());
		
		this.setCategoria(vehiculo.getCategoria());
		this.setFechaAlta(vehiculo.getFechaAlta());
		this.setKms(vehiculo.getKms());
		//this.setUbi(vehiculo.getUbi());
		this.setOficina(vehiculo.getOficina());
		this.setColor(vehiculo.getColor());
		this.setMatricula(vehiculo.getMatricula());
	}
	
	
	
	public Vehiculo(Matricula matricula, String marca, String modelo,Categoria categoria, String color,  Oficina oficina, int num_km) {
	
		this.setMatricula(matricula);
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setOficina(oficina);
		this.setColor(color);
		this.setCategoria(categoria);
		//this.setUbi(oficina);
		this.setKms(num_km);
	}
/**
 * Constructor completo de vehículo
 * @param matricula	 La matrícula del vehículo del tipo {@code Matricula}
 * @param marca	La marca del vehículo del tipo {@code String}. Ejemplo: "Skoda"
 * @param modelo El modelo del vehículo del tipo {@code String}. Ejemplo: "R8"
 * @param categoria La categor�a del vehículo del tipo {@code Categoria}
 * @param color	El color del vehículo del tipo {@code String} puede ser nulo (""). Ejemplo: "Naranja"
 * @param fecha_alta	La fecha en la que el vehículo entr� en la base de datos, tipo {@code GregorianCalendar}
 * @param alquilado El buleano que identifica si el vehículo se encuentra alquilado {@value true} o no {@value false}
 * @param oficina La oficina o ubicaci�n en la que se encuentra el vehículo si NO est� alquilado {@value false}, de tipo {@code Direccion}
 * @param num_km El nº de kilómetros que tiene el coche de forma entera, es decir, de tipo {@code int}
 */
public Vehiculo(Matricula matricula, String marca, String modelo,Categoria categoria, String color, GregorianCalendar fecha_alta, Oficina oficina, int num_km) {
	
	this.setMatricula(matricula);
	this.setMarca(marca);
	this.setModelo(modelo);
	
	this.setColor(color);
	this.setCategoria(categoria);
	this.setOficina(oficina);
	//this.setUbi(oficina);
	this.setKms(num_km);
	this.setFechaAlta(fecha_alta);
}

/**
 * 	Constructor completo para un vehículo que va a ser alquilado
 * @param matricula	 La matrícula del vehículo del tipo {@code Matricula}
 * @param marca	La marca del vehículo del tipo {@code String}. Ejemplo: "Skoda"
 * @param modelo El modelo del vehículo del tipo {@code String}. Ejemplo: "R8"
 * @param categoria La categor�a del vehículo del tipo {@code Categoria}
 * @param color	El color del vehículo del tipo {@code String} puede ser nulo (""). Ejemplo: "Naranja"
 * @param fecha_alta	La fecha en la que el vehículo entró en la base de datos, tipo {@code GregorianCalendar}
 * @param oficina La oficina o ubicaci�n en la que se encuentra el vehículo si NO está alquilado {@value false}, de tipo {@code Direccion}
 * @param num_km El n� de kilómetros que tiene el coche de forma entera, es decir, de tipo {@code int}
 */
public Vehiculo(Matricula matricula, String marca, String modelo, Categoria categoria, String color, GregorianCalendar fecha_alta , Direccion oficina, int num_km) {
	
	this.setMatricula(matricula);
	this.setMarca(marca);
	this.setModelo(modelo);
	this.setOficina(Oficina);

	this.setColor(color);
	this.setCategoria(categoria);
	//this.setUbi(oficina);
	this.setKms(num_km);
	this.setFechaAlta(fecha_alta);
	
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
		
			//Comparamos sus matrículas
			return (copia.matricula==this.matricula);
			
		
	
			
	}

	@Override
	public String toString()
	{
		return ""+this.matricula + " - " + this.getMarca() + " " + this.getModelo();
	}
	
	
	@Override
	public int compareTo(Vehiculo otro) 
	{
		int resultado = 0;
		Vehiculo copia = (Vehiculo) otro; //Hacemos una copia
		resultado = this.getNombreVehiculo().compareToIgnoreCase(copia.getNombreVehiculo()); //Comparamos sus nombres completos
		return resultado;
	}
	
	public abstract double PrecioAlquiler();
	/*@SuppressWarnings("static-access")
	public double PrecioAlquiler(int base)
	{
		double precio = base;	//El total a pagar por el cliente
		double porcentaje = 0;	//El porcentaje que se le aplica si es el�ctrico/de combusti�n
		double recargo = 0; //El recargo que se le aplica en base a su categor�a
		
		if (this.esElectrico )
		{
			porcentaje = (15 * this.precioBase)/100; //Calculamos el 15% para luego sumarlo
		}
		recargo = this.categoria.getRecargo();	//Averiguamos cuanto es el porcentaje: 10,15,30 ...
		recargo= (recargo*this.precioBase)/100;	//Calculamos el porcentaje
		
		precio+=porcentaje; //Le sumamos el 0 o el 15% 
		precio+=recargo; //Le sumamos el recargo
		
		if (this.Oficina.isDeAeropuerto())
		{
			precio += (this.precioBase*porcentaje*10)/100; //Calculamos el 10% de la nueva cantidad
		}
		return precio; 
	}*/
}
