package clasesObjetos;

import java.util.GregorianCalendar;

import exceptions.EmisionesNoValidasException;

public abstract class DeCombustion extends Vehiculo {

	//Propiedades
	private double consumo; //Litros por cada 100km
	private int potencia; //medida en CV
	private String emisiones; //Puede ser A, B o C
	
	public double getConsumo() {
		return consumo;
	}

	public void setConsumo(double consumo) {
		if (consumo>0)
		{
			this.consumo = consumo;
		}
	}

	public int getPotencia() {
		return potencia;
	}

	public void setPotencia(int potencia) {
		if (potencia>=50) //Potencia mínima
		{
			this.potencia = potencia;
		}
	}

	public String getEmisiones() {
		String aDevolver = this.emisiones;
		return aDevolver; //Devolvemos una copia
	}

	public void setEmisiones(String emisiones) throws EmisionesNoValidasException {
		if (emisiones.equals("A") || emisiones.equals("B") || emisiones.equals("C"))
		{
			this.emisiones = emisiones;
		} else {
			throw new EmisionesNoValidasException("Escriba el nivel de emisiones de su vehículo 'A','B' o 'C'");
		}
	}


	public DeCombustion(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta, boolean alquilado, Direccion oficina, int num_km, double consumo, int caballaje, String emisiones) throws EmisionesNoValidasException {
		super(matricula, marca, modelo, categoria, color, fecha_alta, alquilado, oficina, num_km);
		this.setConsumo(consumo);
		this.setPotencia(caballaje);
		this.setEmisiones(emisiones);
		//this.setElectrico(false); //No son electricos
	}
	
	

	@Override
	public String toString()
	{
		return this.getMarca()+" "+this.getModelo() + "| Consumo: " + this.getConsumo() + " Potencia: "+this.getPotencia();
	}
}
