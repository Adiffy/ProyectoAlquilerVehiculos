package interfaz.ususario;

import java.util.Scanner;

import metodos.menu.Metodos;

public class FicherosMaestros {

	/**
	 * El men� que se encarga de los FICHEROS MAESTROS cuando el usuario lo selecciona en el 1er men� del programa.
	 * Sirve para a�adir o eliminar Vehiculos, oficinas, empleados y clientes.
	 */
	public static void menu()
	{
		Scanner l = new Scanner (System.in);
		String[] opc = {"1.- Gesti�n de Veh�culos","2.- Gesti�n de oficinas","3.- Gesti�n de Empleados", "4.- Clientes", "5.- Salir"};
		String opcionesValidas = "12345";
		menu: //etiqueta
		switch (metodos.menu.Metodos.menu(opc, opcionesValidas, "FICHEROS MAESTROS", "Seleccione una opci�n v�lida", l))
		{
		case "1":
			FicherosMaestros.opc1(l);
		case "2":
			
		case "3":
			
		case "4":
			
		case "5":
			break menu;
				//TODO rellenar todo el men� (que a su vez llamar� a otros m�todos
		}
	}
	
	private static void opc1(Scanner lector)
	{
		String[] opciones = {"1.- Dar de alta un veh�culo", "2.- Dar de baja un veh�culo", "3.- Salir"};
		String opcVal = "123";
		lector.nextLine(); //Limpiamos el Scanner por si acaso
		
		switch (Metodos.menu(opciones, opcVal, "Gesti�n de Veh�culos", "Elija una opci�n v�lida", lector))
		{
		case "1":
			
		case "2":
			
		case "3":
			return; //Return vac�o para salir 
		}
	}

}
