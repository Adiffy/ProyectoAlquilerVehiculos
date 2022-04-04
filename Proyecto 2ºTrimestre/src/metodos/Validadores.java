package metodos;

import clasesObjetos.Vehiculo;

public class Validadores {

	public static boolean codigoValidado(String code)
	{
		//if (!code.contains("JA")) //Si no tiene JA delante
		//{
		//	return false;
		//}else 
		if (Integer.parseInt(code.substring(2))!=0) //Si tiene datos numéricos después de las dos letras. Ejemplo: JA01
		{
			return true;
		}else {
			return false;
		}	
	}
	/**
	 * Devuelve un buleano que indica si el vehículo se encuentra disponible
	 * @param vehiculo	El vehículo el cuál queremos saber si está disponible
	 * @return	Verdadero si el vehículo está disponible (no alquilado) y falso si se encuentra alquilado
	 */
	public static boolean vehiculoDisponible(Vehiculo vehiculo)
	{
		if (vehiculo.isAlquilado())
		{
			return false;
		}else {
			return true;
		}
	}
	
	public static boolean carnetValido(String carnet)
	{
		if (( carnet.compareToIgnoreCase("A")==0 || carnet.compareToIgnoreCase("B")==0 || carnet.compareToIgnoreCase("C")==0 || carnet.compareToIgnoreCase("D")==0 ) || carnet.compareToIgnoreCase("AM")==0 || carnet.compareToIgnoreCase("A1")==0 || carnet.compareToIgnoreCase("A2")==0)
		{
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean carnetValidoFurgoneta(String carnet)
	{
		if (( carnet.compareToIgnoreCase("B")==0 || carnet.compareToIgnoreCase("C")==0 || carnet.compareToIgnoreCase("D")==0 ))
		{
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean carnetValidoMoto(String carnetRequerido)
	{
		if (carnetRequerido.compareToIgnoreCase("AM")==0 || carnetRequerido.compareToIgnoreCase("A1")==0 || carnetRequerido.compareToIgnoreCase("A2")==0)
		{
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean tipoCocheValido(String Tipo)
	{
		if (Tipo.equalsIgnoreCase("deportivo")||Tipo.equalsIgnoreCase("familiar")||Tipo.equalsIgnoreCase("todoterreno") ||Tipo.equalsIgnoreCase("4x4"))
		{
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean tipoEmisionesValidas(String emisiones)
	{
		if(emisiones.compareToIgnoreCase("A")==0 || emisiones.compareToIgnoreCase("B")==0 || emisiones.compareToIgnoreCase("C")==0)
		{
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean longituNumMatricula(int numeros)
	{
		//Lo pasamos a STRING para hacer el .length 
		if(String.valueOf(numeros).length()<5 && String.valueOf(numeros).length()>2)
		{
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean longituLetrasMatricula(String letras)
	{
		if(letras.length()==3)	//3 letras
		{
			return true;
		}else {
			return false;
		}
	}
	
	public static boolean cilindradaValida (int cilindrada)
	{
		if(cilindrada==49 || cilindrada==50 || cilindrada==125 || cilindrada==250 || cilindrada==500)
		{
			return true;
		}else {
			return false;
		}
	}
	
	
}
