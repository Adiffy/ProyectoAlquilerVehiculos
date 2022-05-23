package metodos;

public class MetodoDni {

	/**
	 * <p>
	 * Devuelve un verdadero o falso dependiendo de la autenticidad del DNI.
	 * Para ello comprueba que la longitud es correcta y luego si la letra dada coincide con 
	 * la letra correspondiente seg�n el n�mero.
	 * 
	 * {@code Ejemplo de uso:}
	 *  if (DNIvalido("78280774k))
	 *  {...}
	 * </p>
	 * @param dnicompleto	El DNI completo con los n�meros y la letra
	 * @return Verdadero si es v�lido o Falso si no lo es.
	 */
	public static boolean DNIvalido(String dnicompleto)
	{
		//Variables
		boolean valido=false;	//A priori el DNI no es v�lido
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
	 * Calcula la letra correspondiente a un n�mero de DNI
	 * <blockquote>Por ejemplo:</blockquote>
	 * CalculaLetra(78280774) nos devuelve "K"
	 * @param n Dni sin letra
	 * @return Letra que corresponde al n�mero
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
			case 0:
				letra="T";
				break;
			case 1:
				letra="R";
				break;
			case 2:
				letra="W";
				break;
			case 3:
				letra="A";
				break;
			case 4:
				letra="G";
				break;
			case 5:
				letra="M";
				break;
			case 6:
				letra="Y";
				break;
			case 7:
				letra="F";
				break;
			case 8:
				letra="P";
				break;
			case 9:
				letra="X";
				break;
			case 11:
				letra="B";
				break;
			case 12:
				letra="N";
				break;
			case 13:
				letra="J";
				break;
			case 14:
				letra="Z";
				break;
			case 15:
				letra="S";
				break;
			case 16:
				letra="Q";
				break;
			case 17:
				letra="V";
				break;
			case 18:
				letra="H";
				break;
			case 19:
				letra="L";
				break;
			case 20:
				letra="C";
				break;
			case 21:
				letra="K";
				break;
			case 22:
				letra="E";
				break;
			default: 
				letra = "Ñ"; //Si no es ninguna devolverá la Ñ
				break;
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
