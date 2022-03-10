package interfaz.ususario;

import java.util.Scanner;

public class PideDato {

	public static int numerico(Scanner l)
	{
		int num = 0; //No hace falta devolver otra cosa porque estar� en bucle hasta que sea v�lido
		boolean valido;
		do {
			valido=true; //A priori confiamos en el usuario
			try {
				System.out.println("Introduzca un n�");
				num = l.nextInt();
			} catch (Exception e) {
				System.out.println("El dato debe ser num�rico (entero)");
				valido=false;
			}finally {
				l.nextLine();
			}
		} while (!valido);
		return num;
	}

	public static int numerico(Scanner l, String mensaje)
	{
		int num = 0; //No hace falta inicializar la variable
		boolean valido;
		do {
			valido=true; //A priori confiamos en el usuario
			try {
				System.out.println(mensaje);
				num = l.nextInt();
			} catch (Exception e) {
				System.out.println("El dato debe ser num�rico (entero)");
				valido=false;
			}finally {
				l.nextLine();
			}
		} while (!valido);
		return num;
	}
	
	public static double real(Scanner l)
	{
		double num = 0.0; //No hace falta devolver otra cosa porque estar� en bucle hasta que sea v�lido
		boolean valido;
		do {
			valido=true; //A priori confiamos en el usuario
			try {
				System.out.println("Introduzca un n�");
				num = l.nextDouble();
			} catch (Exception e) {
				System.out.println("El dato debe ser num�rico");
				valido=false;
			}finally {
				l.nextLine();
			}
		} while (!valido);
		return num;
	}
}
