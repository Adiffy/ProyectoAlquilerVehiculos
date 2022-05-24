package clasesObjetos;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.util.GregorianCalendar;

import exceptions.CodigoNoValidoException;
import exceptions.DNInoValidoException;
import exceptions.FechaNoValidaException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;

public class Alquiler implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
		//Variables que Se usarán en caso de alquiler
		private String codigo ; 
		private Date FechaInicioAlquiler; 
		private Date FechaPrevistaFinAlquiler; 
		private Date FechaDevolucion; 
		private Cliente cliente;
		private Empleado encargado;
		private Vehiculo aAlquilar;
		private double PrecioAlquiler ;
		
		private Oficina OficinaRecogida;
		private Oficina OficinaEntrega;
		
		//Getters & Setters
		
		

		/**
		 * @return el vehículo aAlquilar
		 */
		public Vehiculo getaAlquilar() {
			return aAlquilar;
		}

		/**
		 * @param aAlquilar el vehículo del alquiler
		 */
		public void setaAlquilar(Vehiculo aAlquilar) {
			this.aAlquilar = aAlquilar;
		}

		/**
		 * @return el encargado
		 */
		public Empleado getEncargado() {
			return encargado;
		}

		/**
		 * @param encargado el empleado encargado adjunto al alquiler
		 */
		public void setEncargado(Empleado encargado) {
			this.encargado = encargado;
		}

		/**
		 * @return el cliente que hizo el alquiler
		 * @throws LongitudCadenaNoValidaException 
		 * @throws DNInoValidoException 
		 * @throws LicenciaNoValidaException 
		 */
		public Cliente getCliente() throws LicenciaNoValidaException, DNInoValidoException, LongitudCadenaNoValidaException {
			return new Cliente(cliente);
		}

		/**
		 * @param cliente Será el cliente que alquila
		 */
		public void setCliente(Cliente cliente) {
			this.cliente = cliente;
		}

		/**
		 * 
		 * @return El código alfanumérico del alquiler
		 */
		public String getCodigo() {
			String copia = codigo;
			return copia;
		}

		public double getPrecioAlquiler() {
			//tipo primitivo
			return this.PrecioAlquiler;
		}

		public void setPrecioAlquiler(double precio) 
			{
				this.PrecioAlquiler = precio;
			}
		{	//TODO
//			int dias = this.getNumDias();
//			PrecioAlquiler = dias*coche.PrecioAlquiler();
		}

		public void setCodigo(String codigo) throws CodigoNoValidoException {
			if (codigo.length()>0 && codigo.length()<5)
			{
				String code = codigo;
				this.codigo = code;
			}else {
				throw new CodigoNoValidoException();
			}
		}

		public Date getFechaInicioAlquiler() {
			return (Date) FechaInicioAlquiler.clone();
		}
		
		@SuppressWarnings({ "static-access", "deprecation" })
		public void setFechaInicioAlquiler(Date fechaInicioAlquiler) throws FechaNoValidaException {
			if ((fechaInicioAlquiler.getMonth() >= GregorianCalendar.getInstance().MONTH || fechaInicioAlquiler.getDate() >= GregorianCalendar.getInstance().DAY_OF_YEAR))
			{
				FechaInicioAlquiler = (Date) fechaInicioAlquiler.clone();
			}else {
				throw new FechaNoValidaException("Fecha de inicio de alquiler no v�lida");
			}
			
		}
		public Date getFechaPrevistaFinAlquiler() {
			return (Date) FechaPrevistaFinAlquiler.clone();
		}
		

		public void setFechaPrevistaFinAlquiler(Date fechaPrevistaFinAlquiler) throws FechaNoValidaException {
//			LocalDate fin = LocalDate.of(fechaPrevistaFinAlquiler.YEAR, fechaPrevistaFinAlquiler.MONTH, fechaPrevistaFinAlquiler.DAY_OF_MONTH);
//			LocalDate inicio = LocalDate.of(FechaInicioAlquiler.YEAR, FechaInicioAlquiler.MONTH, FechaInicioAlquiler.DAY_OF_MONTH);
			
//			if ((fin.compareTo(inicio)>0))
//			{
				FechaPrevistaFinAlquiler = fechaPrevistaFinAlquiler;
//			}else 
//			{
//				throw new FechaNoValidaException("Fecha anterior a la fecha de inicio");
//			}
			
		}
		@SuppressWarnings({ "deprecation" })
		public Date getFechaDevolucion() {
			//Creamos y devolvemos una copia
			return new Date (FechaDevolucion.getYear(), FechaDevolucion.getMonth(), FechaDevolucion.getDate());
		}
		public Oficina getOficinaRecogida() {
			Oficina copia = new Oficina(OficinaRecogida);
			return copia;
		}

		public void setOficinaRecogida(Oficina oficinaRecogida) {
			Oficina copia = new Oficina(oficinaRecogida); //Copiamos la oficina dada
			OficinaRecogida = copia;	//Metemos la copia
		}

		public Oficina getOficinaEntrega() {
			Oficina copia = new Oficina(this.OficinaEntrega); //Copiamos
			return copia;	//Damos la copia
		}

		public void setOficinaEntrega(Oficina oficinaEntrega) {
			Oficina copia = new Oficina(oficinaEntrega);
			OficinaEntrega = copia ;
		}

		@SuppressWarnings({ "deprecation" })
		public void setFechaDevolucion(Date fechaDevolucion) throws FechaNoValidaException {
			
			//O es mayor el mes o el año de la fecha de devolución respecto la de inicio alquiler
			if (fechaDevolucion.getMonth() > this.FechaInicioAlquiler.getMonth() || fechaDevolucion.getYear() > this.FechaInicioAlquiler.getYear())	
			{
				FechaDevolucion = fechaDevolucion;
			}else {
				throw new FechaNoValidaException("Fecha de devolución anterior a la fecha de inicio del alquiler");
			}
			
		}
		public void setAlquilado(Vehiculo aAlquilar,boolean si)
		{
			this.aAlquilar = aAlquilar;
			aAlquilar.setAlquilado(si);
		}
		
		//Constructores
		
		/**
		 * Constructor básico para un alquiler
		 * ni siquiera te pide la fecha DEFINITIVA del fin de alquiler
		 * @param codigo	El código de alquiler
		 * @param date	La fecha en la que inicia el alquiler
		 * @param date2	La fecha que se espera que el cliente entregue el vehículo
		 * @param aAlquilar	El Vehículo en cuestión
		 * @param cliente	El Cliente que realiza el alquiler
		 * @param oficinaRecogida	Oficina de la que sale el vehículo
		 * @param oficinaDejada		Oficina donde se piensa dejar el vehículo
		 * @throws FechaNoValidaException 
		 * @throws CodigoNoValidoException 
		 */
		public Alquiler(String codigo, Date fechaInicio,Date fechaFin,double precio,Vehiculo aAlquilar,Cliente cliente, Oficina oficinaRecogida,Oficina oficinaDejada) throws FechaNoValidaException, CodigoNoValidoException 
		{
			super();
			this.setCodigo(codigo);
			this.setFechaInicioAlquiler(fechaInicio);
			this.setFechaPrevistaFinAlquiler(fechaFin);
			this.setPrecioAlquiler(precio);	
			this.setaAlquilar(aAlquilar);
			this.setCliente(cliente);
			this.setOficinaRecogida(oficinaRecogida);
			this.setOficinaEntrega(oficinaDejada);
		}
		
		public Alquiler(String codigo, Date fechaInicio,Date fechaFin,double precio, Vehiculo aAlquilar,Cliente cliente,Empleado emple, Oficina oficinaRecogida,Oficina oficinaDejada)throws FechaNoValidaException, CodigoNoValidoException {
			super();
			this.setCodigo(codigo);
			this.setFechaInicioAlquiler(fechaInicio);
			this.setFechaPrevistaFinAlquiler(fechaFin);	//La fecha que se preveé que acabe el alquiler
			this.setaAlquilar(aAlquilar);
			this.setOficinaRecogida(OficinaRecogida);
			this.setOficinaEntrega(OficinaEntrega);
			this.setAlquilado(aAlquilar, true); //Cuando se construye un alquiler el vehículo es alquilado
			this.setCliente(cliente);
			this.setEncargado(emple);
		}
		

		public Alquiler(String codigo, Date fechaInicio, Date fechaPrevistaFin, Date fechaFinAlquiler, double precio,
				Vehiculo auto, Cliente cli, Empleado emple, Oficina oficinaRecogida, Oficina oficinaDejada) throws FechaNoValidaException, CodigoNoValidoException {
			this.setCodigo(codigo);
			this.setFechaInicioAlquiler(fechaInicio);
			this.setFechaPrevistaFinAlquiler(fechaPrevistaFin);
			this.setFechaDevolucion(fechaFinAlquiler);
			this.setPrecioAlquiler(precio);	
			this.setaAlquilar(auto);
			this.setCliente(cli);
			this.setEncargado(emple);
			this.setOficinaRecogida(oficinaRecogida);
			this.setOficinaEntrega(oficinaDejada);
		}

		//Metodos
		/**
		 * @return El nº de días que el vehículo está alquilado
		 */
		@SuppressWarnings({ "deprecation" })
		public int getNumDias()
		{
			LocalDate inicio = LocalDate.of(this.FechaInicioAlquiler.getYear(), this.FechaInicioAlquiler.getMonth(), this.FechaInicioAlquiler.getDate());
			LocalDate fin = LocalDate.of(this.FechaDevolucion.getYear(), this.FechaDevolucion.getMonth(), this.FechaDevolucion.getDate());
			LocalDate resultado = fin.minusDays(inicio.getDayOfYear());
			return resultado.getDayOfYear();
		}
		
		public boolean isAcabado()
		{
			if (this.getFechaDevolucion() != null && this.getOficinaRecogida() != null)
			{
				return true;
			}else {
				return false;
			}
		}
		
		@Override
		public String toString()
		{
			return codigo+" - "+this.getPrecioAlquiler()+" | "+this.getFechaInicioAlquiler() ;
			
		}
}
