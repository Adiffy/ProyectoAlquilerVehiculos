package metodos;

public class MetodoDni {

	/**
	 * <p>
	 * Devuelve un verdadero o falso dependiendo de la autenticidad del DNI.
	 * Para ello comprueba que la longitud es correcta y luego si la letra dada coincide con 
	 * la letra correspondiente según el número.
	 * 
	 * {@code Ejemplo de uso:}
	 *  if (DNIvalido("78280774k))
	 *  {...}
	 *  
	 * @param dnicompleto	El DNI completo con los números y la letra
	 * @return Verdadero si es válido o Falso si no lo es.
	 */
	public static boolean DNIvalido(String dnicompleto)
	{
		//Variables
		boolean valido=false;	//A priori el DNI no es válido
		String numeros;
		String[] dniDiv = new String[2];	//El dni dividido
		int num;
		String letra;
		
		//Dividimos el dni en numeros y letra
		dniDiv=divideDNI(dnicompleto);
		letra=dniDiv[0];
		numeros=dniDiv[1];
		num=Integer.parseInt(numeros); //Pasamos de String a numero
		
		//Comprobamos que la letra es la correcta 
		if (letra.compareToIgnoreCase(CalculaLetra(num))==0)
		{
			valido=true;	//Cambiamos el valor a true
		}
		return valido;
	} 
	
	
	/**
	 * <p>
	 * Calcula la letra correspondiente a un número de DNI
	 * <blockquote>Por ejemplo:</blockquote>
	 * CalculaLetra(78280774) nos devuelve "K"
	 * @param n Dni sin letra
	 * @return Letra que corresponde al número
	 */
	public static String CalculaLetra(int n)
	{
		String letra = null;
		int num=n%23;
		letra=calculaLetra(num);
		return letra;
	}
	
	private static String calculaLetra(int num)
	{
		String letra; 
		switch (num)
		{
			case 0:letra="T";
			case 1:letra="R";
			case 2:letra="W";
			case 3:letra="A";
			case 4:letra="G";
			case 5:letra="M";
			case 6:letra="Y";
			case 7:letra="F";
			case 8:letra="P";
			case 9:letra="X";
			case 11:letra="B";
			case 12:letra="N";
			case 13:letra="J";
			case 14:letra="Z";
			case 15:letra="S";
			case 16:letra="Q";
			case 17:letra="V";
			case 18:letra="H";
			case 19:letra="L";
			case 20:letra="C";
			case 21:letra="K";
			case 22:letra="E";
			default: letra = "Ñ"; //Si no es ninguna devolverá la Ñ
		}
		return letra;
	}
	private static String[] divideDNI(String dniCompleto)
	{
		String dniDividido[]= new String[2];
		dniDividido[0]=dniCompleto.substring(8);	//letra
		dniDividido[1]=dniCompleto.substring(0,8);	//numeros
		return dniDividido;
	}
}
