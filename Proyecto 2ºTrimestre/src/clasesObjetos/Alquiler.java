package clasesObjetos;

import java.util.GregorianCalendar;

import exceptions.FechaNoValidaException;

public class Alquiler {

	//Variables que Se usar�n en caso de alquiler
		private GregorianCalendar FechaInicioAlquiler = new GregorianCalendar();
		private GregorianCalendar FechaPrevistaFinAlquiler = new GregorianCalendar();
		private GregorianCalendar FechaDevolucion = new GregorianCalendar();
		
		private Oficina OficinaRecogida;
		private Oficina OficinaEntrega;
		
		//Getters & Setters
		
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
		private void setFechaPrevistaFinAlquiler(GregorianCalendar fechaPrevistaFinAlquiler) throws FechaNoValidaException {
			if (fechaPrevistaFinAlquiler.DATE > GregorianCalendar.getInstance().DATE && (fechaPrevistaFinAlquiler.MONTH > GregorianCalendar.getInstance().MONTH || fechaPrevistaFinAlquiler.DAY_OF_YEAR > GregorianCalendar.getInstance().DAY_OF_YEAR))
			{
				FechaPrevistaFinAlquiler = fechaPrevistaFinAlquiler;
			}else 
			{
				throw new FechaNoValidaException("Fecha anterior a la actual");
			}
			
		}
		@SuppressWarnings("static-access")
		public GregorianCalendar getFechaDevolucion() {
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

		public void setFechaDevolucion(GregorianCalendar fechaDevolucion) {
			FechaDevolucion = fechaDevolucion;
		}
		
		//Constructores
		
		public Alquiler(GregorianCalendar fechaInicioAlquiler, GregorianCalendar fechaPrevistaFinAlquiler) throws FechaNoValidaException {
			super();
			this.setFechaInicioAlquiler(fechaInicioAlquiler);
			this.setFechaPrevistaFinAlquiler(fechaPrevistaFinAlquiler);
		}
		
		//Metodos
		/**
		 * @return El n� de d�as que el veh�culo est� alquilado
		 */
		public int getNumDias()
		{
			@SuppressWarnings("static-access")
			int num = ((this.FechaDevolucion.MONTH - this.FechaInicioAlquiler.MONTH) + (this.FechaDevolucion.DATE - this.FechaInicioAlquiler.DATE));
			return num;
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
