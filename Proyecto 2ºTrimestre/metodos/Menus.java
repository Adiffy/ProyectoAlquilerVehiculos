package metodos;

import java.util.ArrayList;
import java.util.Scanner;

import InterfazUsuario.BarraDeCarga;
import InterfazUsuario.PideDato;
import accesoADatos.Serializar;
import clasesObjetos.Cliente;
import clasesObjetos.CocheCombustion;
import clasesObjetos.CocheElectrico;
import clasesObjetos.Empresa;
import clasesObjetos.Furgoneta;
import clasesObjetos.Moto;
import clasesObjetos.Vehiculo;
import exceptions.CarnetRequeridoInvalidoException;
import exceptions.CilindradaNoValidaException;
import exceptions.CodigoPostalException;
import exceptions.ConsumoNoValidoException;
import exceptions.EmisionesNoValidasException;
import exceptions.LetrasMatriculaNoValidasException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;
import exceptions.LongitudNoValidaException;
import exceptions.NumPlazasNoValidoException;
import exceptions.NumeroMatriculaNoValidoException;
import exceptions.PlantaNoValidaException;
import exceptions.PotenciaNoValidaException;
import exceptions.RecargoNoValidoException;
import exceptions.TiempoRecargaNoValidoException;
import exceptions.TipoNoValidoException;
import metodosMenu.Metodos;

public class Menus {

	
	
	public static void principal(Empresa empresa, Scanner lector)
	{
		
		String[] opciones = {"1.- Ficheros maestros", "2.- Gesti�n de oficinas", "3.- Mostrar listados", "4.- Salir"};
		String opcValidas = "1234";
		String menError = "Seleccione una opci�n v�lida. Puede ser:"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
		
		boolean noSale = true; //buleano para el bucle 
		
		do
		{
			switch (metodosMenu.Metodos.menu(opciones, opcValidas, "MEN� PRINCIPAL", menError, lector))
			{
			case "1":
				
			case "2"://Gesti�n de oficinas
				
			case "3":	//Listados
				
			case "4": //SALIR DE VERDAD
				BarraDeCarga.pintar();
				noSale = false;
				break;
			}
		}while(noSale);
	}
	
	private static void ficherosMaestros(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, LicenciaNoValidaException 
	{
		String[] opciones = {"1.- Ficheros maestros", "2.- Gesti�n de oficinas", "3.- Mostrar listados", "4.- Salir"};
		String opcValidas = "1234";
		String menError = "Seleccione una opci�n v�lida. Puede ser:"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
	
		switch (metodosMenu.Metodos.menu(opciones, opcValidas, "Ficheros maestros", menError, lector))
		{
		case "1":
			String[] opc = {"1.- A�adir / eliminar veh�culos a la flota","2.- Modificar listado de clientes", "3.- Modificar listado de empleados", "4.- Modificar listado de oficinas"};
			switch (metodosMenu.Metodos.menu(opc, opcValidas, "Gesti�n de ficheros maestros", menError, lector))
			{
			case "1":
				Menus.flotaVehicular(empresa, lector);
		case "2":
				Menus.clientes(empresa, lector);
		case "3":
				
		case "4":
			break;
			}
		}
		
	}
	
	private static void empleados(Empresa empresa, Scanner lector)
	{
		
	}
	
	private static void eliminar(Empresa empresa, Scanner lector)
	{
		String menError = "Seleccione una opci�n v�lida. Puede ser:"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
		ArrayList<Vehiculo> lista = TreeMapToArrayList.listar(empresa.getGaraje()); //Pasamos el TreeMap a ArrayList
		String[] listas = (String[]) lista.toArray();	//Pasamso el ArrayList a String[] (lo que necesita el menu)
		String validas = TreeMapToArrayList.numVehiculosCadena(empresa.getGaraje());	//Sacamos el n�mero de opciones 
		switch (Metodos.menu(listas, validas, "�Qu� veh�culo le gustar�a eliminar?", menError, lector)){
		case "1":
			
		case "2":
			
		case "3":
		
		//TODO el problema es q no sabemos cuantos vehiculos hay
		}
	}
	
	private static  void combustion(Empresa empresa, Scanner lector) throws TipoNoValidoException
	{
		
		String[] combustion = {"1.- Coche","2.- Furgoneta", "3.- Volver"};
		String opcValidas = "1234";
		String menError = "Seleccione una opci�n v�lida. Puede ser:"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
			
		switch (metodosMenu.Metodos.menu(combustion, opcValidas, "Vehiculos de combusti�n", menError, lector))
		{
		case "1":	//Coche
			Menus.creaCocheCombustion(empresa, lector);												
		case "2":	//Furgoneta
			Menus.creaFurgoneta(empresa, lector);
		case "3":	//Salir
			break;
		}
			
	}
	
	private static void electrico(Empresa empresa, Scanner lector) throws TiempoRecargaNoValidoException, TipoNoValidoException
	{
		
		String[] electrico = {"1.- Coche","2.- Moto", "3.- Volver"};
		String opcValidas = "1234";
		String menError = "Seleccione una opci�n v�lida. Puede ser:"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
		
		switch (metodosMenu.Metodos.menu(electrico, opcValidas, "Vehiculos el�ctricos", menError, lector))
		{
		case "1":	//Coche
			Menus.creaCocheElectrico(empresa, lector);
		case "2":	//Moto
			Menus.creaMoto(empresa, lector);
		case "3":	//Salir
			break;
		}
	}
	public static void add(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException
	{
		String[] elec = {"A.- De combusti�n","B.- El�ctrico","C.- Salir"}; //elec de Elecci�n
		String validas = "ABC";
		String errorLetras = "Debe elegir una opci�n v�lida pulsando A,B,C ..."; 
		switch (metodosMenu.Metodos.menu(elec, validas, "TIPO DE VEH�CULO",errorLetras, lector))
		{
		case "A","a":	//Combustion
			Menus.combustion(empresa, lector);
			
		case "B","b":	//Electrico
			Menus.electrico(empresa, lector);
		case "C","c": //SALIR
			break;
		}
	}
	private static void flotaVehicular(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException
	{
		String[] elec = {"A.- A�ADIR VEH�CULO","B.- ELIMINAR VEH�CULO","C.- SALIR"}; //elec de Elecci�n
		String validas = "ABC";
		String errorLetras = "Debe elegir una opci�n v�lida pulsando A,B,C ..."; 
		
		switch (metodosMenu.Metodos.menu(elec, validas, "FLOTA DE VEH�CULOS",errorLetras, lector))
		{				
		case "A","a": //Lo hacemos tolerante a may�sculas y min�sculas
			Menus.add(empresa, lector);
		case "B","b":	//Eliminar veh�culo
			Menus.eliminar(empresa, lector);
		case "C","c": //SALIR
			break;
		}
	}
	private static void clientes(Empresa empresa, Scanner lector) throws LicenciaNoValidaException
	{
		String[] opcioness = {"A.- A�adir cliente", "B.- Dar de baja", "C.- Salir"};
		String Validas = "ABC";
		String errorLetras = "Debe elegir una opci�n v�lida pulsando:"; //Error en los men�s no-num�ricos
		switch (metodosMenu.Metodos.menu(opcioness, Validas, "GESTI�N DE CLIENTES",errorLetras, lector))
		{
		case "A","a": 	//Damos de alta nuevos clientes
			Cliente nuevo =InterfazUsuario.PideDato.cliente(lector);
			empresa.nuevoCliente(nuevo);
		case "B","b":	//Eliminamos clientes de la empresa
			
		case "C","c":
			break;
		}
	}
	
	private static void oficinas(Empresa empresa, Scanner lector)
	{
		String[] opcioness = {"A.- Empleados", "B.- Oficinas", "C.- ", "D.- Salir"};
		String Validas = "ABCD";
		String errorLetras = "Debe elegir una opci�n v�lida pulsando:"; //Error en los men�s no-num�ricos
		switch (metodosMenu.Metodos.menu(opcioness, Validas, "GESTI�N DE OFICINAS",errorLetras, lector))
		{
		case "A","a":
				
		case "B","b":
			
		case "C","c":
			
		case "D","d":
			break;	//Salir
		}
	}
	
	private static void creaCocheCombustion(Empresa empresa, Scanner lector) throws TipoNoValidoException
	{
		try {
			CocheCombustion nuevo = PideDato.cocheCombustion(lector);
			empresa.addAlGaraje(nuevo);
			Serializar.grabaEmpresa(empresa);//Guardamos cambios
		} catch (RecargoNoValidoException | LetrasMatriculaNoValidasException
				| NumeroMatriculaNoValidoException | EmisionesNoValidasException
				| NumPlazasNoValidoException | ConsumoNoValidoException
				| PotenciaNoValidaException | LongitudNoValidaException
				| PlantaNoValidaException | CodigoPostalException
				| LongitudCadenaNoValidaException e) {
			System.out.println("Error al crear veh�culo - Coche de combusti�n");
			e.printStackTrace();
		}
	}
	
	private static void creaCocheElectrico(Empresa empresa, Scanner lector) throws TiempoRecargaNoValidoException, TipoNoValidoException
	{
		try {
			CocheElectrico nuevo = PideDato.cocheElectrico(lector);
			empresa.addAlGaraje(nuevo);
			Serializar.grabaEmpresa(empresa);//Guardamos cambios
		} catch (RecargoNoValidoException | LetrasMatriculaNoValidasException
				| NumeroMatriculaNoValidoException | EmisionesNoValidasException
				| NumPlazasNoValidoException | ConsumoNoValidoException
				| PotenciaNoValidaException | LongitudNoValidaException
				| PlantaNoValidaException | CodigoPostalException
				| LongitudCadenaNoValidaException e) {
			System.out.println("Error al crear veh�culo - Coche el�ctrico");
			e.printStackTrace();
		}
	}
	
	private static void creaMoto(Empresa empresa, Scanner lector)
	{
		try {
			Moto nueva = PideDato.moto(lector);
			empresa.addAlGaraje(nueva);
			Serializar.grabaEmpresa(empresa);//Guardamos cambios
		} catch (LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException
				| RecargoNoValidoException | EmisionesNoValidasException
				| ConsumoNoValidoException | PotenciaNoValidaException
				| CilindradaNoValidaException | CarnetRequeridoInvalidoException e) {
			System.out.println("Error al crear veh�culo - Moto");
			e.printStackTrace();
		}
	}
	public static void creaFurgoneta(Empresa empresa, Scanner lector)
	{
		try {
			Furgoneta furgo = PideDato.furgoneta(lector);
			empresa.addAlGaraje(furgo);
			Serializar.grabaEmpresa(empresa);//Guardamos cambios
		} catch (EmisionesNoValidasException | ConsumoNoValidoException
				| PotenciaNoValidaException | RecargoNoValidoException
				| LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			System.out.println("Error al crear veh�culo - Furgoneta");
			e.printStackTrace();
		}
	}
	
	}