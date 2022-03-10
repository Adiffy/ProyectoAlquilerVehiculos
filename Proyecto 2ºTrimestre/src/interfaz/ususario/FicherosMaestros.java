package interfaz.ususario;

import java.util.Scanner;

import metodos.menu.Metodos;

public class FicherosMaestros {

	/**
	 * El menú que se encarga de los FICHEROS MAESTROS cuando el usuario lo selecciona en el 1er menú del programa.
	 * Sirve para añadir o eliminar Vehiculos, oficinas, empleados y clientes.
	 */
	public static void menu()
	{
		Scanner l = new Scanner (System.in);
		String[] opc = {"1.- Gestión de Vehículos","2.- Gestión de oficinas","3.- Gestión de Empleados", "4.- Clientes", "5.- Salir"};
		String opcionesValidas = "12345";
		menu: //etiqueta
		switch (metodos.menu.Metodos.menu(opc, opcionesValidas, "FICHEROS MAESTROS", "Seleccione una opción válida", l))
		{
		case "1":
			FicherosMaestros.opc1(l);
		case "2":
			
		case "3":
			
		case "4":
			
		case "5":
			break menu;
				//TODO rellenar todo el menú (que a su vez llamará a otros métodos
		}
	}
	
	private static void opc1(Scanner lector)
	{
		String[] opciones = {"1.- Dar de alta un vehículo", "2.- Dar de baja un vehículo", "3.- Salir"};
		String opcVal = "123";
		lector.nextLine(); //Limpiamos el Scanner por si acaso
		
		switch (Metodos.menu(opciones, opcVal, "Gestión de Vehículos", "Elija una opción válida", lector))
		{
		case "1":
			
		case "2":
			
		case "3":
			return; //Return vacío para salir 
		}
	}

}
