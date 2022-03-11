package clasesObjetos;

import java.io.Serializable;
import java.util.GregorianCalendar;

import clasesObjetosInterfaces.Coche;
import exceptions.EmisionesNoValidasException;
import exceptions.NumPlazasNoValidoException;
@SuppressWarnings("serial")
public class CocheElectrico extends Electrico implements Serializable, Coche {


	//Propiedades
	int autonomia; //(kms)
	int TiempoRecarga; //(min)
	int numPlazas; //Nº de personas que caben
	String tipo; //"Familiar, Deportivo, 4x4"
	
	//Getters & Setters
	
	@Override
	public void setNumPlazas(int num) throws NumPlazasNoValidoException {
		if (num>0 && num<=8) //Suponemos que en un coche no caben más de 8 personas
		{
			this.numPlazas = num;
		}else {
			throw new NumPlazasNoValidoException("Número de plazas no válido");
		}
	}
	@Override
	public void setTipo(String Tipo) throws EmisionesNoValidasException 
	{
		if (Tipo.equalsIgnoreCase("deportivo")||Tipo.equalsIgnoreCase("familiar")||Tipo.equalsIgnoreCase("todoterreno") ||Tipo.equalsIgnoreCase("4x4")) //si es A o B o C
		{
			String trab = Tipo;
			this.tipo = trab;
		}else {
			throw new EmisionesNoValidasException("El tipo puede ser únicamente 'familiar', 'deportivo' o 'todoterreno(4x4)'");
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
			GregorianCalendar fecha_alta, boolean alquilado, Direccion oficina, int num_km, int autonomia,
			int tiempoDeRecarga) {
		super(matricula, marca, modelo, categoria, color, fecha_alta, alquilado, oficina, num_km, autonomia,
				tiempoDeRecarga);
		
	}
	@Override
	public double precioAlquiler() {
		int porcentaje = 0;
		
		
		//Aplicamos el 15% (Eléctrico)
		porcentaje = (15*Coche.precioBase)/100;
	
		return ((Coche.precioBase+porcentaje)*this.getNumDias());
	}
	
	
	
		
	

}
