package clasesObjetos;

import java.util.GregorianCalendar;

import exceptions.CarnetRequeridoInvalidoException;
import exceptions.CilindradaNoValidaException;
import exceptions.TiempoRecargaNoValidoException;

public class Moto extends Electrico {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades 
	private int cilindrada;
	private String carnetRequerido; //Carnet requerido (AM/A1/A2)

	private int precioBase = 10;
	
	//Getters & Setters 
	public int getCilindrada() {
		//Al ser entero no hace falta evitar el tampering
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) throws CilindradaNoValidaException {
		if (cilindrada==49 || cilindrada==50 || cilindrada==125 || cilindrada==250 || cilindrada==500)
		{
			int cop = cilindrada;
			this.cilindrada = cop;
		}else {
			throw new CilindradaNoValidaException("Hay una serie de cilindradas admitidas en el sistema, 45,50,125,250 o 500");
		}
	}

	public String getCarnetRequerido() {
		String copia = carnetRequerido;
		return copia;
	}

	public void setCarnetRequerido(String carnetRequerido) throws CarnetRequeridoInvalidoException {
		if (carnetRequerido.compareToIgnoreCase("AM")==0 || carnetRequerido.compareToIgnoreCase("A1")==0 || carnetRequerido.compareToIgnoreCase("A2")==0) {
			this.carnetRequerido = carnetRequerido;
		}else {
			throw new CarnetRequeridoInvalidoException("Carnet no v�lido. Pruebe con 'AM', 'A1' o 'A2'");
		}
	}



	public Moto(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta, clasesObjetos.Oficina oficina, int num_km, int autonomia, int tiempoDeRecarga,
			int cilindrada, String carnetRequerido) throws TiempoRecargaNoValidoException, CarnetRequeridoInvalidoException, CilindradaNoValidaException {
		super(matricula, marca, modelo, categoria, color, fecha_alta, oficina, num_km, autonomia, tiempoDeRecarga);
		this.setCilindrada(cilindrada);
		this.setCarnetRequerido(carnetRequerido);
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
		Moto dada = (Moto) a; //Hacemos que lo trate como MOTO y no OBJETO
		
		if (this.matricula == dada.matricula) { //Comparamos sus matr�culas
			return true;
		}else {
			return false;	//Ya a estas alturas ha fallado todos los controles por lo que, es falso
		}
	}
	
	@Override
	public double PrecioAlquiler() {
		int precioBase = this.precioBase;
		int porcentaje = 15;
		double recargo = 0;
		double precio;
		
		precio = precioBase + (porcentaje*precioBase)/100;
		
		recargo = this.getCategoria().getRecargo();	//Averiguamos cuanto es el porcentaje: 10,15,30 ...
		recargo= (recargo*precioBase)/100;	//Calculamos el porcentaje
		precio += recargo;
		if (this.getOficina().isDeAeropuerto())
		{
			precio += (precioBase*10)/100; 
		}
		
		return precio;
	}

}
