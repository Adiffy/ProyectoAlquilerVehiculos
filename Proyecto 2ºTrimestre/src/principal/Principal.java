package principal;

import java.util.Scanner;

import metodosMenu.Metodos;

public class Principal {

	public static void main(String[] args) {
		
		Scanner lector = new Scanner(System.in);
	String[] opciones = {"1.- Ficheros maestros", "2.- Gesti�n de empresa", "3.- ", "4.- Salir"};
	String opcValidas = "1234";
	String menError = "Seleccione una opci�n v�lida. Puede ser 1, 2, 3 � 4"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
		
		switch (Metodos.menu(opciones, opcValidas, "MEN� PRINCIPAL", menError, lector))
		{
		case "1":
			switch (Metodos.menu(opciones, opcValidas, "Ficheros maestros", menError, lector))
			{
			case "1":
				String[] opc = {"1.- A�adir veh�culos a la flota","2.- Modificar listado de clientes", "3.- Modificar listado de empleados", "4.- Modificar listado de oficinas"};
				switch (Metodos.menu(opc, opcValidas, "Gesti�n de ficheros maestros", menError, lector))
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
