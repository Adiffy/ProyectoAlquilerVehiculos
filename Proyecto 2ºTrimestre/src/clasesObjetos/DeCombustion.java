package clasesObjetos;

import java.util.GregorianCalendar;

import exceptions.ConsumoNoValidoException;
import exceptions.EmisionesNoValidasException;
import exceptions.PotenciaNoValidaException;

public abstract class DeCombustion extends Vehiculo {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	private double consumo; //Litros por cada 100km
	private int potencia; //medida en CV
	private String emisiones; //Puede ser A, B o C
	
	public double getConsumo() {
		double cons = consumo;
		return cons;
	}

	public void setConsumo(double consumo) throws ConsumoNoValidoException {
		if (consumo>0)
		{
			this.consumo = consumo;
		}else {
//			throw new ConsumoNoValidoException("Consumo inferior o igual a 0, no admitido");
		}
	}

	public int getPotencia() {
		//No hay que impedir el tampering
		return potencia;
	}

	public void setPotencia(int potencia) throws PotenciaNoValidaException {
		if (potencia>=50) //Potencia m�nima
		{
			this.potencia = potencia;
		}else {
//			throw new PotenciaNoValidaException("Potencia inferior o igual a 50");
		}
	}

	public String getEmisiones() {
		String aDevolver = this.emisiones;
		return aDevolver; //Devolvemos una copia
	}

	public void setEmisiones(String emisiones) throws EmisionesNoValidasException {
		if (emisiones==null || emisiones.compareToIgnoreCase("A")==0 || emisiones.compareToIgnoreCase("B")==0 || emisiones.compareToIgnoreCase("C")==0  || emisiones.compareToIgnoreCase("D")==0)
		{
			this.emisiones = emisiones;
		} else {
			throw new EmisionesNoValidasException("Escriba el nivel de emisiones de su veh�culo 'A','B' o 'C'");
		}
	}


	public DeCombustion(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta, Oficina oficina, int num_km, double consumo, int caballaje, String emisiones) throws EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException {
		super(matricula, marca, modelo, categoria, color, fecha_alta, oficina, num_km);
		this.setConsumo(consumo);
		this.setPotencia(caballaje);
		this.setEmisiones(emisiones);
		//this.setElectrico(false); //No son electricos
	}
	
	
	@Override
	public String toString()
	{
		return ""+this.matricula + " - " + this.getMarca() + " " + this.getModelo();
	}
}
