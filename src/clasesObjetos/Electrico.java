package clasesObjetos;

import java.util.GregorianCalendar;

public abstract class Electrico extends Vehiculo {

	//Propiedades 
	private int autonomia; //Autonomia del vehiculo (tiempo en horas)
	private int tiempoRecarga; //Tiempo que tarda en cargarse por completo (tiempo en minutos)
	
	
	public int getTiempoRecarga() {
		return tiempoRecarga;
	}

	public void setTiempoRecarga(int tiempoRecarga) {
		if (tiempoRecarga>0 && tiempoRecarga<55)
		{
			this.tiempoRecarga = tiempoRecarga;
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
			GregorianCalendar fecha_alta, boolean alquilado, Direccion oficina, int num_km, int autonomia, int tiempoDeRecarga) {
		super(matricula, marca, modelo, categoria, color, fecha_alta, alquilado, oficina, num_km);
		this.setAutonomia(autonomia);
		this.setTiempoRecarga(tiempoDeRecarga);
	//	this.setElectrico(true); //Todos los que hereden de electrico son electricos (para el cálculo del alquiler)
	}

	@Override
	public String toString()
	{
		return this.getMarca()+" "+this.getModelo() + "| Autonomía: " + this.getAutonomia() + " Tiempo de Recarga: "+this.getTiempoRecarga();
	}
	
}
