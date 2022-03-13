package InterfazUsuario;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

import accesoADatos.Serializar;
import clasesObjetos.Cliente;
import clasesObjetos.CocheCombustion;
import clasesObjetos.CocheElectrico;
import clasesObjetos.Empleado;
import clasesObjetos.Empresa;
import clasesObjetos.Furgoneta;
import clasesObjetos.Moto;
import clasesObjetos.Oficina;
import clasesObjetos.Vehiculo;
import comparadores.ComparaVehiculoPorCategoria;
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

public class Menus {

	//Variables que se reutilizan en muchos de los m�todos de esta clase
	private static String menError = "Seleccione una opci�n v�lida. Puede ser:"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
	private static String mensaje = "Introduzca una de las siguientes opciones:";
	private static String errorLetras = "Debe elegir una opci�n v�lida pulsando:"; //Error en los men�s no-num�ricos
	
	public static void principal(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, LicenciaNoValidaException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
	{
		
		String[] opciones = {"1.- Ficheros maestros", "2.- Gesti�n de oficinas", "3.- Mostrar listados", "4.- Salir"};
		String opcValidas = "1234";
		
		
		boolean noSale = true; //buleano para el bucle 
		
		do
		{
			switch (metodosMenu.Metodos.menu(opciones, opcValidas, "MEN� PRINCIPAL", menError, lector))
			{
			case "1":
				Menus.ficherosMaestros(empresa, lector);
			case "2"://Gesti�n de oficinas
				Menus.oficinas(empresa, lector);
			case "3":	//Listados
				Menus.MenuListados(empresa, lector);
			case "4": //SALIR DE VERDAD
				BarraDeCarga.pintar();
				noSale = false;
				break;
			}
		}while(noSale);
	}
	
	private static void ficherosMaestros(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, LicenciaNoValidaException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException 
	{
		String[] opciones = {"1.- Configurar garaje", "2.- Gestionar clientes", "3.- Mostrar listados", "4.- Configurar oficinas", "5.- Salir"};
		String opcValidas = "12345";
			boolean sale= false ;
		do 
		{
			switch (metodosMenu.Metodos.menu(opciones, opcValidas, "Ficheros maestros", menError, lector))
			{
			case "1":
				String[] opc = {"1.- A�adir / eliminar veh�culos a la flota","2.- Modificar listado de clientes", "3.- Modificar listado de empleados", "4.- Modificar listado de oficinas","5.- Salir"};
				String OPC = "12345";
				switch (metodosMenu.Metodos.menu(opc, OPC, "Gesti�n de ficheros maestros", menError, lector))
				{
				case "1":
					Menus.flotaVehicular(empresa, lector);
				case "2":
					Menus.clientes(empresa, lector);
				case "3":
					Menus.MenuListados(empresa, lector);
				case "4":
					Menus.MenuOficinas(empresa, lector);
				case "5":
					sale = true;
					break;
				}
			}
		}while(!sale);
		
	}
	private static void MenuListados(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, PlantaNoValidaException, TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException
	{
		String[] list = {"1.- Listar veh�culos","2.- Listar Personas", "3.- Listar Oficinas","4.- Salir"};
		String posibilidades = "1234";
		boolean seVa = false;
		
		do 
		{
			switch (metodosMenu.Metodos.menu(list, posibilidades, "LISTADOS",mensaje, lector))
			{
			case "1": 	//Vehiculos
				Menus.listadoVehiculos(empresa, lector);
				break;
			case "2":	//Personas
				Menus.listadoPersonas(empresa, lector);
				break;
			case "3":	//Oficinas
				Menus.listadoOficinas(empresa, lector);
				break;
			case "4": //Salir
				seVa = true;
				break;
			}
		}while(!seVa);
		
	}
	private static void listadoPersonas(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		String[] elecc = {"1.- Empleados","2.- Clientes","3.- Atr�s (Salir)"};
		String posib = "123";
		String error = "Seleccione una opci�n de este conjunto:";
		boolean sale = false;
		
		do
		{
			switch (metodosMenu.Metodos.menu(elecc, posib, "TIPOS DE PERSONAS A LISTAR", error, lector))
			{
			case "1":	//Empleados
				Menus.listadoPersonasPor(empresa, "Empleado", lector);
				break;
			case "2":	//Clientes
				Menus.listadoPersonasPor(empresa, "Clientes", lector);
				break;
			case "3": //Salir 
				break;
			}
		}while(!sale);
	}
	
	private static void listadoVehiculos(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, PlantaNoValidaException
	{
		String[] elecc = {"1.- De combusti�n","2.- El�ctricos","3.- Atr�s (Salir)"};
		String posib = "123";
		String error = "Seleccione una opci�n de este conjunto:";
		boolean sale = false;
		
		do
		{
			switch (metodosMenu.Metodos.menu(elecc, posib, "TIPOS DE PERSONAS A LISTAR", error, lector))
			{
			case "1":	//De combusti�n
				Menus.combustion(empresa, lector);
				break;
			case "2":	//El�ctricos
				Menus.listaElectrico(empresa, lector);
				break;
			case "3": //Salir 
				sale = true;
				break;
			}
		}while (!sale);
	}
	
	private static void listadoOficinas(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		String[] elecc = {"1.- Empleados","2.- Clientes","3.- Atr�s (Salir)"};
		String posib = "123";
		String error = "Seleccione una opci�n de este conjunto:";
		boolean sale = false;
		
		do
		{	//No saldr� hasta que le d� expl�citamente
			switch (metodosMenu.Metodos.menu(elecc, posib, "TIPOS DE PERSONAS A LISTAR", error, lector))
			{
			case "1":	//Empleados
				Menus.listadoPersonasPor(empresa, "Empleado", lector);
				break;
			case "2":	//Clientes	
				Menus.listadoPersonasPor(empresa, "Clientes", lector);
				break;
			case "3": //Salir 
				sale = true;
				break;
			}
		}while(!sale);
	}
	private static void listaElectrico(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException
	{
		String[] tipo = {"1.- Coche el�ctrico", "2.- Moto el�ctrica", "3.- Atr�s (Salir)"};
		String opcio = "123";
		switch (metodosMenu.Metodos.menu(tipo, opcio, "LISTAR VEH�CULOS EL�CTRICOS", errorLetras, lector))
		{
		case "1":	//Coche el�ctrico
			
				Menus.listadosElectricos(empresa, "CocheElectrico", lector);
			
		case "2":	//Moto
				Menus.listadosElectricos(empresa, "Moto", lector);
		case "3":
			break;
		}
	}
	private static void listadosElectricos(Empresa empresa,String tipoVehiculo, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException
	{
		String error = "Seleccione una opci�n de este conjunto:";
		String[] a = {"1.- Por Matr�cula","2.- Por categor�a","3.- Atr�s (Salir)"};
		String b = "123";
		boolean sale = false;		//A priori no se sale
	
		do
		{
			switch (metodosMenu.Metodos.menu(a, b, "Listar"+ tipoVehiculo +"por", error, lector))
			{
			case "1":	//Listar por Matr�cula
				ArrayList <Moto> lista = null;
				ArrayList <CocheElectrico> lista2 = null;
				boolean Moto;	//Sabremos si es Moto o no 
				
				if (tipoVehiculo.compareToIgnoreCase("Moto")==0  )
				{
					Moto = true;
					lista = metodos.TreeMapToArrayList.listarMotos(empresa.getGaraje());
					
				}else {
					Moto = false;
					lista2 = metodos.TreeMapToArrayList.listarCocheElectrico(empresa.getGaraje());
				}
				//Ahora mostramos los listados
				if (Moto)
				{
					for (Vehiculo aux: lista)
					{
						System.out.printf("Con Matr�cula: "+ aux.getMatricula() +" - " + aux.toString());
					}
				}else {
					for (Vehiculo aux: lista2)
					{
						System.out.printf("Con Matr�cula: "+ aux.getMatricula() +" - " + aux.toString());
					}
				}
				break;
				
			case "2":	//Listar por categoria
				
				lista = null;
				lista2 = null;
				if (tipoVehiculo.compareToIgnoreCase("Moto")==0  )
				{
					Moto = true;
					lista = metodos.TreeMapToArrayList.listarMotos(empresa.getGaraje());
					
				}else {
					Moto = false;
					lista2 = metodos.TreeMapToArrayList.listarCocheElectrico(empresa.getGaraje());
				}			
				//Ahora mostramos los listados
				if (Moto)
				{
					ListarMotosPorCategoria(lista);
				}else {
					ListarCocheElectricoPorCategoria(lista2);;
				}
				break;
			case "3":	//SALIR
				sale = true;
				break;
			}
		}while(!sale);
	}
	private static void ListarCocheElectricoPorCategoria(ArrayList<CocheElectrico> flota) 
	{
		
		ComparaVehiculoPorCategoria c = new ComparaVehiculoPorCategoria(); 
		flota.sort(c);
		for (Vehiculo auxiliar: flota)
		{
			System.out.printf("Coche el�ctrico: "+auxiliar);
		}
	}
	private static void ListarMotosPorCategoria(ArrayList<Moto> lista) 
	{
		
		ComparaVehiculoPorCategoria c = new ComparaVehiculoPorCategoria(); 
		lista.sort(c);
		for (Vehiculo auxiliar: lista)
		{
			System.out.printf("Moto el�ctrica: "+auxiliar);
		}
	}
	private static void listadoPersonasPor(Empresa empresa,String tipoPersona, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		String error = "Seleccione una opci�n de este conjunto:";
		String[] a = {"1.- Por DNI","2.- Por nombre","3.- Atr�s (Salir)"};
		String b = "123";
		switch (metodosMenu.Metodos.menu(a, b, "Listar"+ tipoPersona +"por", error, lector))
		{
		case "1":	//Listar por DNI
			TreeMap<String, Cliente> lista = null;
			TreeMap<String, Empleado> lista2 = null;
			boolean Cliente;	//Sabremos si es Cliente o no 
			if (tipoPersona.compareToIgnoreCase("Cliente")==0 || tipoPersona.compareToIgnoreCase("Clientes")==0 )
			{
				Cliente = true;
				lista = empresa.getClientes();
			}else {
				Cliente = false;
				lista2 = empresa.getEmpleados();
			}
			//Ahora mostramos los listados
			if (Cliente)
			{
				Menus.ListarTreeMapClienteDNI(lista);
			}else {
				Menus.ListarTreeMapEmpleadoDNI(lista2);
			}
			
		case "2":	//Listar por nombre
			 ArrayList<Cliente> A = new ArrayList<>();
			 ArrayList<Empleado> E = new ArrayList<>();
			if (tipoPersona.compareToIgnoreCase("Cliente")==0 || tipoPersona.compareToIgnoreCase("Clientes")==0 )
			{
				Cliente = true;
				
			}else {
				Cliente = false;
				
			}
			//Ahora mostramos los listados
			if (Cliente)
			{
				A =empresa.ListarClienteNombre();
				for (Cliente clien:A)
				{
					System.out.println(clien);
				}
			}else {
				E = empresa.ListarEmpleadoNombre();
				for (Empleado emple:E)
				{
					System.out.println(emple);
				}
			}
		case "3":	//SALIR
			break;
		}
	}
	
	private static void eliminaOficina(Empresa empresa, Scanner lector)
	{
		Menus.ListarTreeMapOficinas(empresa.getOficinas());
		String[] opcion = {""};
		String respuesta;
		
		respuesta = metodosMenu.Metodos.menu(opcion, metodos.TreeMapToArrayList.DNIsEmpleados(empresa.getEmpleados()),"Escriba el DNI del empleado a borrar", menError, lector);
		if (empresa.getEmpleados().containsKey(respuesta))	//Si alguien usa esa clave principal
		{
			String[] lista = {"S.- S�", "N.- No"};
			String valor = "SN";
			switch (metodosMenu.Metodos.menu(lista, valor,"El empleado se borrar� �Est� seguro?", menError,  lector))
			{
			case "S":
				empresa.eliminarOficina(respuesta);
				Serializar.grabaEmpresa(empresa);
			case "N":
				break; 	//Sale sin tocar nada
			}
		}
	
	}
	
	private static void MenuOficinas(Empresa empresa, Scanner lector) throws LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		String[] opciom = {"A.- Nueva oficina", "B.- Eliminar oficina", "C.- Salir"};
		String validad = "ABC";
		

		switch (metodosMenu.Metodos.menu(opciom, validad, "GESTOR DE OFICINAS", menError, lector))
		{
		case "A":	//A�adir
			Oficina nueva = PideDato.oficina(lector);
			empresa.nuevaOficina(nueva);	//Metemos la oficina
			Serializar.grabaEmpresa(empresa); 	//Guardamos 
		case "B":	//Eliminar
			Menus.eliminaOficina(empresa, lector);
		case "C": //Salir
			break;
		}
		
	}
	private static void eliminar(Empresa empresa, Scanner lector)
	{
		/*
		String menError = "Seleccione una opci�n v�lida. Puede ser:"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
		ArrayList<Vehiculo> lista = TreeMapToArrayList.listar(((Empresa) empresa).getGaraje()); //Pasamos el TreeMap a ArrayList
		String[] listas = (String[]) lista.toArray();	//Pasamso el ArrayList a String[] (lo que necesita el menu)
		String validas = TreeMapToArrayList.numVehiculosCadena(((Empresa) empresa).getGaraje());	//Sacamos el n�mero de opciones 
		*/
		
		//TODO el problema es q no sabemos cuantos vehiculos hay por lo que no podemos usar un SWITCH
		
	}
	
	private static  void combustion(Empresa empresa, Scanner lector) throws TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		
		String[] combustion = {"1.- Coche","2.- Furgoneta", "3.- Volver"};
		String opcValidas = "1234";
		
			
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
	
	private static void electrico(Empresa empresa, Scanner lector) throws TiempoRecargaNoValidoException, TipoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, RecargoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		
		String[] electrico = {"1.- Coche","2.- Moto", "3.- Volver"};
		String opcValidas = "1234";
		
		
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
	private static void add(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
	{
		String[] elec = {"A.- De combusti�n","B.- El�ctrico","C.- Salir"}; //elec de Elecci�n
		String validas = "ABC";
		
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
	private static void flotaVehicular(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
	{
		String[] elec = {"A.- A�ADIR VEH�CULO","B.- ELIMINAR VEH�CULO","C.- SALIR"}; //elec de Elecci�n
		String validas = "ABC";
		 
		
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
		
		switch (metodosMenu.Metodos.menu(opcioness, Validas, "GESTI�N DE CLIENTES",errorLetras, lector))
		{
		case "A","a": 	//Damos de alta nuevos clientes
			Cliente nuevo =InterfazUsuario.PideDato.cliente(lector);
			empresa.nuevoCliente(nuevo);
			Serializar.grabaEmpresa(empresa);
		case "B","b":	//Eliminamos clientes de la empresa
			/*
			 * 1- Listar empleados
			 * 2- Buscar por clave principal
			 * 3- Eliminar
			 */
		case "C","c":
			break;
		}
	}
	
	private static void oficinas(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		String[] opcioness = {"A.- Empleados", "B.- Oficinas", "C.- Salir"};
		String Validas = "ABC";
		boolean sale = false;
		
		do 
		{
			switch (metodosMenu.Metodos.menu(opcioness, Validas, "GESTI�N DE OFICINAS",errorLetras, lector))
			{
			case "A","a":	//A�adir o eliminar empleados
				Menus.MenuEmpleado(empresa,lector);
			case "B","b":	//A�adir o eliminar oficinas
				Menus.MenuOficinas(empresa, lector);
			case "C","c":
				sale = true;
				break;	//Salir		
			}
		}while(!sale);
		
	}
	
	private static void ListarTreeMapEmpleadoDNI(TreeMap<String,Empleado> map)
	{
		map.entrySet().forEach((entry) -> {
            System.out.printf("Con DNI: %s - Empleado: %s %n", entry.getKey(), entry.getValue());
        });
	}
	private static void ListarTreeMapOficinas(TreeMap<String,Oficina> map)
	{
		map.entrySet().forEach((entry) -> {
            System.out.printf("Con C�digo: %s - Oficina: %s %n", entry.getKey(), entry.getValue());
        });
	}
	private static void ListarTreeMapClienteDNI(TreeMap<String,Cliente> map)
	{
		map.entrySet().forEach((entry) -> {
            System.out.printf("Con DNI: %s - Cliente: %s %n", entry.getKey(), entry.getValue());
        });
	}
	
	private static void eliminaEmpleado(Empresa empresa, Scanner lector)
	{
		String respuesta;
		/*
		 * 1- Listar empleados
		 * 2- Buscar por clave principal
		 * 3- Eliminar
		 */
		Menus.ListarTreeMapEmpleadoDNI(empresa.getEmpleados());
		String[] opcion = {""};
		
		
		respuesta = metodosMenu.Metodos.menu(opcion, metodos.TreeMapToArrayList.DNIsEmpleados(empresa.getEmpleados()),"Escriba el DNI del empleado a borrar", errorLetras, lector);
		if (empresa.getEmpleados().containsKey(respuesta))	//Si alguien usa esa clave principal
		{
			String[] lista = {"S.- S�", "N.- No"};
			String valor = "SN";
			switch (metodosMenu.Metodos.menu(lista, valor,"El empleado se borrar� �Est� seguro?", errorLetras,  lector))
			{
			case "S":
				empresa.despedirEmpleado(respuesta);
				Serializar.grabaEmpresa(empresa); 	//Guardamos 
			case "N":
				break; 	//Sale sin tocar nada
			}
		}
	}
	
	private static void MenuEmpleado(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		String[] opcc = {"A.- A�adir nuevo empleado","B.- Dar de baja un empleado","C.- Salir"};
		String Validas = "ABC";

		
		switch (metodosMenu.Metodos.menu(opcc, Validas, "GESTOR DE EMPLEADOS", errorLetras, lector))
		{
		case "A","a": //A�adir empleado 
			Empleado emple = PideDato.empleado(lector); //Creamos empleado
			empresa.nuevoEmpleado(emple);	//A�adimos el empleado al TreeMap
			Serializar.grabaEmpresa(empresa);
		case "B","b":	//Eliminar empleado
			Menus.eliminaEmpleado(empresa, lector);
		case "C","c":
			break;	//Salir
		}
	}
	
	private static void creaCocheCombustion(Empresa empresa, Scanner lector) throws TipoNoValidoException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		
			CocheCombustion nuevo = PideDato.cocheCombustion(lector);
			empresa.addAlGaraje(nuevo);
			Serializar.grabaEmpresa(empresa);//Guardamos cambios
		
	}
	
	private static void creaCocheElectrico(Empresa empresa, Scanner lector) throws TiempoRecargaNoValidoException, TipoNoValidoException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		
			CocheElectrico nuevo = PideDato.cocheElectrico(lector);
			empresa.addAlGaraje(nuevo);
			Serializar.grabaEmpresa(empresa);//Guardamos cambios
		
	}
	
	private static void creaMoto(Empresa empresa, Scanner lector) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, RecargoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
	{
		
			Moto nueva = PideDato.moto(lector);
			empresa.addAlGaraje(nueva);
			Serializar.grabaEmpresa(empresa);//Guardamos cambios
		
		
	}
	private static void creaFurgoneta(Empresa empresa, Scanner lector) throws EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException
	{
		
			Furgoneta furgo = PideDato.furgoneta(lector);
			empresa.addAlGaraje(furgo);
			Serializar.grabaEmpresa(empresa);//Guardamos cambios
		
	}
	
	
		
	
	
	}