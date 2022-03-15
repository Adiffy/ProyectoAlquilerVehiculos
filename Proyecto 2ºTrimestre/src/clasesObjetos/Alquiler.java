package clasesObjetos;

import java.time.LocalDate;
import java.util.GregorianCalendar;

import exceptions.FechaNoValidaException;

public class Alquiler {

	//Variables que Se usar�n en caso de alquiler
		private String codigo ; 
		private GregorianCalendar FechaInicioAlquiler = new GregorianCalendar();
		private GregorianCalendar FechaPrevistaFinAlquiler = new GregorianCalendar();
		private GregorianCalendar FechaDevolucion = new GregorianCalendar();
		
		private double PrecioAlquiler ;
		
		private Oficina OficinaRecogida;
		private Oficina OficinaEntrega;
		
		//Getters & Setters
		
		public String getCodigo() {
			String copia = codigo;
			return copia;
		}

		public double getPrecioAlquiler() {
			//tipo primitivo
			return this.PrecioAlquiler;
		}

		public void setPrecioAlquiler(Vehiculo coche) {
			int dias = this.getNumDias();
			PrecioAlquiler = dias*coche.PrecioAlquiler();
		}

		public void setCodigo(String codigo) {
			
			this.codigo = codigo;
		}

		public GregorianCalendar getFechaInicioAlquiler() {
			return (GregorianCalendar) FechaInicioAlquiler.clone();
		}
		
		@SuppressWarnings("static-access")
		public void setFechaInicioAlquiler(GregorianCalendar fechaInicioAlquiler) {
			if (fechaInicioAlquiler.DATE >= GregorianCalendar.getInstance().DATE && (fechaInicioAlquiler.MONTH >= GregorianCalendar.getInstance().MONTH || fechaInicioAlquiler.DAY_OF_YEAR >= GregorianCalendar.getInstance().DAY_OF_YEAR))
			{
				FechaInicioAlquiler = (GregorianCalendar) fechaInicioAlquiler.clone();
			}
			
		}
		public GregorianCalendar getFechaPrevistaFinAlquiler() {
			return (GregorianCalendar) FechaPrevistaFinAlquiler.clone();
		}
		
		@SuppressWarnings("static-access")
		public void setFechaPrevistaFinAlquiler(GregorianCalendar fechaPrevistaFinAlquiler) throws FechaNoValidaException {
			if ((fechaPrevistaFinAlquiler.DAY_OF_YEAR > GregorianCalendar.getInstance().DAY_OF_YEAR || fechaPrevistaFinAlquiler.YEAR > GregorianCalendar.getInstance().YEAR))
			{
				FechaPrevistaFinAlquiler = fechaPrevistaFinAlquiler;
			}else 
			{
				throw new FechaNoValidaException("Fecha anterior a la actual");
			}
			
		}
		@SuppressWarnings("static-access")
		public GregorianCalendar getFechaDevolucion() {
			//Creamos y devolvemos una copia
			return new GregorianCalendar (FechaDevolucion.YEAR, FechaDevolucion.MONTH, FechaDevolucion.DAY_OF_MONTH);
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

		@SuppressWarnings("static-access")
		public void setFechaDevolucion(GregorianCalendar fechaDevolucion) throws FechaNoValidaException {
			
			//O es mayor el mes o el a�o de la fecha de devoluci�n respecto la de inicio alquiler
			if (fechaDevolucion.MONTH > this.FechaInicioAlquiler.MONTH || fechaDevolucion.YEAR > this.FechaInicioAlquiler.YEAR)	
			{
				FechaDevolucion = fechaDevolucion;
			}else {
				throw new FechaNoValidaException("Fecha de devoluci�n anterior a la fecha de inicio del alquiler");
			}
			
		}
		
		//Constructores
		
		public Alquiler(String codigo, GregorianCalendar fechaInicioAlquiler, GregorianCalendar fechaPrevistaFinAlquiler) throws FechaNoValidaException {
			super();
			this.setCodigo(codigo);
			this.setFechaInicioAlquiler(fechaInicioAlquiler);
			this.setFechaPrevistaFinAlquiler(fechaPrevistaFinAlquiler);
		}
		
		//Metodos
		/**
		 * @return El n� de d�as que el veh�culo est� alquilado
		 */
		@SuppressWarnings("static-access")
		public int getNumDias()
		{
			LocalDate inicio = LocalDate.of(this.FechaInicioAlquiler.YEAR, this.FechaInicioAlquiler.MONTH, this.FechaInicioAlquiler.DAY_OF_MONTH);
			LocalDate fin = LocalDate.of(this.FechaDevolucion.YEAR, this.FechaDevolucion.MONTH, this.FechaDevolucion.DAY_OF_MONTH);
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
}
