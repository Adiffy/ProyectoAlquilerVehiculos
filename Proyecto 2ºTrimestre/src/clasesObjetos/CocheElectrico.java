package clasesObjetos;

import java.io.Serializable;
import java.util.GregorianCalendar;

import clasesObjetosInterfaces.Coche;
import exceptions.EmisionesNoValidasException;
import exceptions.NumPlazasNoValidoException;
import exceptions.TiempoRecargaNoValidoException;
import exceptions.TipoNoValidoException;

public class CocheElectrico extends Electrico implements Serializable, Coche {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	int autonomia; //(kms)
	int TiempoRecarga; //(min)
	int numPlazas; //N? de personas que caben
	String tipo; //"Familiar, Deportivo, 4x4"
	
	private int precioBase = 50;
	
	//Getters & Setters
	
	@Override
	public void setNumPlazas(int num) throws NumPlazasNoValidoException {
		if (num>0 && num<=8) //Suponemos que en un coche no caben m?s de 8 personas
		{
			this.numPlazas = num;
		}else {
			throw new NumPlazasNoValidoException("N?mero de plazas no v?lido");
		}
	}
	@Override
	public void setTipo(String Tipo) throws TipoNoValidoException 
	{
		if (Tipo.equalsIgnoreCase("deportivo")||Tipo.equalsIgnoreCase("familiar")||Tipo.equalsIgnoreCase("todoterreno") ||Tipo.equalsIgnoreCase("4x4")) //si es A o B o C
		{
			String trab = Tipo;
			this.tipo = trab;
		}else {
			throw new TipoNoValidoException("El tipo puede ser ?nicamente 'familiar', 'deportivo' o 'todoterreno(4x4)'");
		}
	}
public int getNumPlazas()
{
	int cop = this.numPlazas; //clonamos en variable de trabajo
	return cop;
}
public String getTipo()
{
	String cop = this.tipo; //Clonamos la string
	return cop;
}
	
	public CocheElectrico(Matricula matricula, String marca, String modelo, Categoria categoria, String color,
			GregorianCalendar fecha_alta,  Oficina oficina, int num_km, int autonomia,
			int tiempoDeRecarga, String tipo, int num_plazas) throws TiempoRecargaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, TipoNoValidoException {
		super(matricula, marca, modelo, categoria, color, fecha_alta, oficina, num_km, autonomia,
				tiempoDeRecarga);
		this.setTipo(tipo);
		this.setNumPlazas(num_plazas);
		
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
		CocheElectrico dado = (CocheElectrico) a; //Hacemos que lo trate como COCHE y no OBJETO
		
		if (this.matricula == dado.matricula) { //Comparamos sus matr?culas
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
		recargo= (recargo*this.precioBase)/100;	//Calculamos el porcentaje
		precio += recargo;
		if (this.getOficina().isDeAeropuerto())
		{
			precio += (precioBase*10)/100; 
		}
		
		return precio;
	}
	
	
	
	
	
		
	

}
