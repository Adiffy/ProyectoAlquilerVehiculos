package InterfazUsuario;

import java.util.GregorianCalendar;
import java.util.Scanner;

import clasesObjetos.*;
import exceptions.LongitudNoValidaException;
import exceptions.PlantaNoValidaException;

public class PideDato {

	/**
	 * M�todo que pide un n�mero entero al usuario mediante consola. Necesita:
	 * @param mensaje Un mensaje de tipo {@code String} que ser� la petici�n al usuario. Ejemplo: "Escriba su edad"
	 * @param mensajeError	Otro mensaje de tipo {@code String} que aparecer� cuando el usuario escriba un dato incorrecto
	 * @param limiteBajo	El l�mite por debajo del dato que se debe introducir, tipo {@code int}.
	 * Sirve para delimitar el m�nimo admitido.
	 * @param limite	El {@code int} que indica el m�ximo valor permitido del dato introducido por el usuario. 
	 * @param l	 El Scanner que se utilizar� para recibir el dato que escriba el usuario.
	 * @return	El dato validado que ha escrito el usuario
	 */
	public static int numerico( String mensaje,String mensajeError,int limiteBajo, int limite,Scanner l) {
		int num = -1;
		boolean DatoMalo = true;
		l.nextLine(); //Limpiamos el Scanner
		do {
			System.out.println(mensaje);
			num = l.nextInt();
			if (num <= limite && num >= limiteBajo)
			{
				DatoMalo = false;
			}else {
				System.out.println(mensajeError);
			}
			l.nextLine();
		}while (DatoMalo);
		
		return num;
	}
	
	public static int numerico( String mensaje,Scanner l) {
		return PideDato.numerico(mensaje,"Debe ser un n�mero entero. Como lo son: 1,2,3", 0, 100, l);
	}

	public static int numerico(Scanner l) {
		return PideDato.numerico("Introduzca un dato real", l);
	}
	
	/**
	 * Pide dato num�rico de tipo {@code int} (entero) 
	 * Este constructor se caracter�stica de pedir el dato sin necesidad de nada
	 * No requiere de ning�n mensaje ni {@code Scanner}.
	 * No cuenta con ning�n mensaje de petici�n as� que �ste debe realizarse antes de llamar a este m�todo.
	 * 
	 * Ejemplo de uso:
	 * 		sysout("Cu�ntos a�os tiene?")
	 * 			int num = PideDato.numerico();
	 * 
	 * @return	 El n�mero entero escrito por el usuario
	 */
	public static int numerico() {
		Scanner l = new Scanner(System.in);
		return PideDato.numerico("", l);
	}
	
	public static double real(String mensaje, int limiteBajo,int limite,Scanner l) {
		double num = 0.0;
		boolean DatoMalo = true;
		l.nextLine(); //Limpiamos el Buffer
		do {
			System.out.println(mensaje);
			num = l.nextDouble();
			l.nextLine();
		}while (DatoMalo);
		
		return num;
	}
	
	public static double real(String mensaje, Scanner l)
	{
		return PideDato.real(mensaje,0,10 ,l);
	}
	
	/**
	 * Pide un dato de tipo {@code String} 
	 * Necesita: 
	 * @param mensaje El mensaje de petici�n del dato
	 * @param mensajeError	El mensaje que se muestra cuando el usuario introduce un valor no-v�lido
	 * @param minLong	El {@code int} que determina el m�nimo de caracteres que puede introducir el usuario
	 * @param maxLong	El {@code int} que ser� el m�ximo de caracteres que puede escribir el usuario sin que d� error
	 * @param l	El {@code Scanner} que se utiliza para recibir el dato
	 * @return	 Devuelve el dato introducido por el usuario
	 */
	public static String cadena(String mensaje, String mensajeError, int minLong, int maxLong,Scanner l)
	{
		String resultado;
		boolean DatoMalo = true;
		l.nextLine(); //Limpiamos el Buffer
		do {
			System.out.println(mensaje);
			resultado = l.nextLine();
			if (resultado.length() >= minLong && resultado.length()<=maxLong)
			{
				DatoMalo=false;
			}else {
				System.out.println(mensajeError);
			}
		}while (DatoMalo);
		
		return resultado;
	}
	
	/**
	 * Pide dato de Cadena ({@code string}).
	 * La longitud m�nima del dato introducido por el usuario ser� 0 y la m�xima ser� 25 (en caracteres).
	 * @param mensaje	El mensaje que viene antes de la petici�n del dato de forma aclaratoria. Ejemplo: "Introduce tu nombre:"
	 * @param l	El Scanner para leer el dato introducido por el usuario
	 * @return	El dato escrito por el usuario
	 */
	public static String cadena(String mensaje, Scanner l)
	{
		return PideDato.cadena(mensaje, "Longitud no v�lida / Tipo de dato no v�lido", 0, 25, l);
	}
	
	/**
	 * Pide dato cadena ({@code String}) que no pide nada al usuario
	 * El mensaje de petici�n del dato deber� ir antes
	 * @param l	{@code Scanner} que se usa para pedir el dato
	 * @return	el dato de tipo {@code String} escrito por el usuario
	 */
	public static String cadena(Scanner l)
	{
		return PideDato.cadena("", l);
	}
	
	/**
	 * Pide una matr�cula al usuario
	 * @param l El {@code Scanner} utilizado para la petici�n de datos
	 * @return	Un objeto de tipo {@code Matricula}
	 */
	public static Matricula matricula(Scanner l)
	{
		System.out.println("Escribe:");
		System.out.print("2.- Los n�meros de la matr�cula:");
		int numeros= PideDato.numerico("", "S�lo datos num�ricos admitidos", 0, 99999999, l);
		System.out.print("2.- Las letras de la matr�cula:");
		String letras = PideDato.cadena(l);
		
		Matricula a = new Matricula(numeros, letras);
		return a;
	}
	
	/**
	 * Pide un dato de tipo GregorianCalendar
	 * Pide una fecha paso a paso (d�a,mes y a�o) y con esos datos
	 * compone un objeto tipo {@code GregorianCalendar} y lo devuelve
	 * @param mensaje	El {@code String} que leer� el usuario antes de escribir los datos
	 * Por ejemplo: "Fecha de cumplea�os" 
	 * @param l El {@code Scanner} con el que se leer�n los datos introducidos por el usuario
	 * @return	El {@code GregorianCalendar} escrito por el usuario
	 */
	public static GregorianCalendar fecha(String mensaje, Scanner l)
	{
		System.out.println(mensaje);
		l.next(); //Limpiamos el Scanner para evitar errores
		System.out.print("D�a:");
		int dia = l.nextInt();
		l.nextLine();
		System.out.println("Mes:");
		int mes = l.nextInt();
		l.nextLine();
		int anno = l.nextInt();
		l.nextLine();
		return  new GregorianCalendar(anno,mes,dia);	//Creamos el GregorianCalendar con los datos introducidos
	}
	
	public static GregorianCalendar fecha(Scanner l)
	{
		return PideDato.fecha("", l);
	}
	
	public static Direccion direccion(Scanner l) throws LongitudNoValidaException, PlantaNoValidaException 
	{
		boolean DatoBueno = false; //A priori es falso
		String nombreVia;
		String numVia;
		String planta;
		String codigoPostal;
		String localidad;
		
		do {
			System.out.println("Direcci�n:");
			System.out.println("Tipo de v�a:");
			nombreVia = PideDato.cadena(l);
			System.out.println("N�mero de v�a:");
			numVia = PideDato.cadena(l);
			System.out.println("Planta");
			planta = PideDato.cadena(l);
			System.out.println("C�digo Postal:");
			codigoPostal = PideDato.cadena("", "C�digo no v�lido", 5, 5, l); //Un CP tiene 5 d�gitos
			System.out.println("Nombre de localidad:");
			localidad = PideDato.cadena(l);
			if((nombreVia.length()<=25 && nombreVia.length()>=2)&&(numVia.length()>=2 && numVia.length()<=25)&&(planta.length()<4)&&(codigoPostal.length()==5) && (localidad.length()>3 && localidad.length()<25)) {
				DatoBueno = true;
			}
		}while (!DatoBueno);
		Direccion home = new Direccion(nombreVia,numVia,planta,codigoPostal,localidad); //Al crear una nueva direccion podemos lanzar dos Exceptions
		return home;
	}
}
