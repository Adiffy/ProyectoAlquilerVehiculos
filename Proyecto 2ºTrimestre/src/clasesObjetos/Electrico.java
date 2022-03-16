package clasesObjetos;

import java.util.GregorianCalendar;

import exceptions.TiempoRecargaNoValidoException;

public abstract class Electrico extends Vehiculo {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Propiedades 
	private int autonomia; //Autonomia del vehiculo (tiempo en horas)
	private int tiempoRecarga; //Tiempo que tarda en cargarse por completo (tiempo en minutos)
	
	
	//Máximos y mínimos
	private int maxTiempoRecarga=200;
	private int minTiempoRecarga=0;
	
	public int getTiempoRecarga() { //Tipo primitivo, no hace falta clonar
		return tiempoRecarga;
	}

	public void setTiempoRecarga(int tiempoRecarga) throws TiempoRecargaNoValidoException {
		if (tiempoRecarga>minTiempoRecarga && tiempoRecarga<maxTiempoRecarga)
		{
			this.tiempoRecarga = tiempoRecarga;
		}else {
			throw new TiempoRecargaNoValidoException("Tiempo de recarga menor o igual a"+ minTiempoRecarga+ " ó mayor que"+ maxTiempoRecarga);
		}
	}

	public int getAutonomia() {
		return autonomia;
	}

	public void setAutonomia(int autonomia) {
		if (autonomia>0)
		{
			this.autonomia = autonomia;
		}
	}

	public Electrico(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta, Oficina oficina, int num_km, int autonomia, int tiempoDeRecarga) throws TiempoRecargaNoValidoException {
		super(matricula, marca, modelo, categoria, color, fecha_alta,  oficina, num_km);
		this.setAutonomia(autonomia);
		this.setTiempoRecarga(tiempoDeRecarga);
	//	this.setElectrico(true); //Todos los que hereden de electrico son electricos (para el cálculo del alquiler)
	}

	@Override
	public String toString()
	{
		return this.matricula +" - " +this.getMarca()+" "+this.getModelo() + "| Autonomía: " + this.getAutonomia() + " Tiempo de Recarga: "+this.getTiempoRecarga();
	}
	
}
