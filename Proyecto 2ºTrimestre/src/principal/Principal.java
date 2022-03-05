package principal;

import java.util.Scanner;

import metodosMenu.Metodos;

public class Principal {

	public static void main(String[] args) {
		
		Scanner lector = new Scanner(System.in);
	String[] opciones = {"1.- Ficheros maestros", "2.- Gestión de empresa", "3.- ", "4.- Salir"};
	String opcValidas = "1234";
	String menError = "Seleccione una opción válida. Puede ser 1, 2, 3 ó 4"; //El mensaje que aparecerá si el usuario elige una opción inválida
		
		switch (Metodos.menu(opciones, opcValidas, "MENÚ PRINCIPAL", menError, lector))
		{
		case "1":
			switch (Metodos.menu(opciones, opcValidas, "Ficheros maestros", menError, lector))
			{
			case "1":
				String[] opc = {"1.- Añadir vehículos a la flota","2.- Modificar listado de clientes", "3.- Modificar listado de empleados", "4.- Modificar listado de oficinas"};
				switch (Metodos.menu(opc, opcValidas, "Gestión de ficheros maestros", menError, lector))
				{
				case "1":
					
				case "2":
					
				case "3":
					
				case "4":
					
				}
			case "2":
				
			case "3":
				
			case "4":
				
			}
		case "2":
			
		case "3":
			
		case "4":
			System.out.println("Gracias por el uso de nuestro software");
			System.out.println("Vuelva pronto");
			break;
		}
		
		
	}

}
