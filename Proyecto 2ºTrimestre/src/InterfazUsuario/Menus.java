package InterfazUsuario;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Scanner;
import java.util.TreeMap;
import accesoADatos.Serializar;
import clasesObjetos.Alquiler;
import clasesObjetos.Cliente;
import clasesObjetos.CocheCombustion;
import clasesObjetos.CocheElectrico;
import clasesObjetos.Empleado;
import clasesObjetos.Empresa;
import clasesObjetos.Furgoneta;
import clasesObjetos.Matricula;
import clasesObjetos.Moto;
import clasesObjetos.Oficina;
import clasesObjetos.Vehiculo;
import comparadores.ComparaVehiculoPorCategoria;
import exceptions.CarnetRequeridoInvalidoException;
import exceptions.CilindradaNoValidaException;
import exceptions.CodigoPostalException;
import exceptions.ConsumoNoValidoException;
import exceptions.EmisionesNoValidasException;
import exceptions.FechaNoValidaException;
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

	//Variables que se reutilizan en muchos de los m�todos de esta clase
	private static String menError = "Seleccione una opci�n v�lida. Puede ser:"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
	private static String mensaje = "Introduzca una de las siguientes opciones:";
	private static String errorLetras = "Debe elegir una opci�n v�lida pulsando:"; //Error en los men�s no-num�ricos
	
	public static void principal(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, LicenciaNoValidaException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException, FechaNoValidaException
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
				break;
			case "2"://Gesti�n de oficinas
				Menus.oficinas(empresa, lector);
				break;
			case "3":	//Listados
				Menus.MenuListados(empresa, lector);
				break;
			case "4": //SALIR DE VERDAD
				BarraDeCarga.pintar();
				noSale = false;
				break;
			}
		}while(noSale);
	}
	
	private static void ficherosMaestros(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, LicenciaNoValidaException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException, FechaNoValidaException 
	{
		String[] opciones = {"1.- Configurar garaje", "2.- Gestionar clientes", "3.- Mostrar listados", "4.- Configurar oficinas", "5.- Gestionar alquileres","6.- Salir"};
		String opcValidas = "123456";
			boolean sale= false ;
		do 
		{
			switch (metodosMenu.Metodos.menu(opciones, opcValidas, "Ficheros maestros", menError, lector))
			{
			case "1":
				String[] opc = {"1.- A�adir / eliminar veh�culos a la flota","2.- Modificar listado de clientes", "3.- Modificar listado de empleados", "4.- Modificar listado de oficinas","5.- Salir"};
				String OPC = "12345";
				boolean salir = false;
				do
				{
					switch (metodosMenu.Metodos.menu(opc, OPC, "Gesti�n de ficheros maestros", menError, lector))
					{
					case "1":
						Menus.flotaVehicular(empresa, lector);
						break;
					case "2":
						Menus.clientes(empresa, lector);
						break;
					case "3":
						Menus.MenuListados(empresa, lector);
						break;
					case "4":
						Menus.MenuOficinas(empresa, lector);
						break;
					case "5":
						salir = true;
						break;
					}
				}while (!salir);
				
			case "2":
				Menus.clientes(empresa, lector);
				break;
			case "3":
				Menus.MenuListados(empresa, lector);
				break;
			case "4":
				Menus.MenuEmpleado(empresa, lector);
				break;
			case "5":
				Menus.alquileres(empresa, lector);
				break;
			case "6":
				sale = true;
				break;
			}
		}while(!sale);
		
	}
	private static void alquileres(Empresa empresa, Scanner lector) throws FechaNoValidaException, LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, PlantaNoValidaException
	{
		String[] opcione = {"1.- Nuevo alquiler","2.- Modificar alquiler existente","3.- Eliminar alquiler","4.- Salir"};
		String validadas = "1234";
		String mensajeError = "Elija una opci�n v�lida:";
		boolean sale = false;
		
		do
		{
			switch (metodosMenu.Metodos.menu(opcione, validadas, "Gesti�n de alquileres", mensajeError, lector))
			{
			case "1"://crear un alquiler
				Alquiler nuevo =  PideDato.alquiler(lector);	//lo pedimos
//				new Alquiler(new GregorianCalendar(1995,12,25),new GregorianCalendar(1995,12,25)); 
				
				empresa.nuevoAlquiler(nuevo);
				Serializar.grabaEmpresa(empresa);
				break;
			case "2":	//Cambiar fechas y dem�s
				Menus.editaAlquiler(empresa, lector);
				break;
			case "3":	//Eliminar alquiler
				Alquiler alquil = PideDato.alquiler(lector);
				empresa.quitaAlquiler(alquil);
				break;
			case "4":
				sale = true;	//sale
				break;
			}
		}while(!sale);
	}
	private static void editaAlquiler(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, PlantaNoValidaException, FechaNoValidaException
	{
		String[] elecciones = {"1.- Mostrar precio alquiler","2.- Editar fecha de finalizaci�n prevista","3.- Finalizar alquiler","4.- Volver (salir)"};
		String val = "1234";
		boolean seSale = false;
		Matricula respuesta = null;
		
		do
		{
			switch (Metodos.menu(elecciones, val, errorLetras, mensaje, lector))
			{
			case "1":	//Listamos veh�culos y seg�n PK decimos precioAlquiler
				boolean valido = false;
				do 
				{
					metodos.TreeMapToArrayList.listarGaraje(empresa.getGaraje());	//Listamos
					System.out.println("Escribe la matr�cula del veh�culo a modificar");
					respuesta = PideDato.matricula(lector);
					//Si respuesta es una matr�cula v�lida se acaba el bucle
					if (empresa.getGaraje().containsKey(respuesta))
					{
						valido = true;
					}
				}while(!valido);
				//Metemos el precio del alquiler en un double para que quede m�s limpio
				double aMostrar = empresa.getGaraje().get(respuesta).PrecioAlquiler();
				//Mostramos resultados
				System.out.println(empresa.getGaraje().get(respuesta));
				System.out.println(aMostrar);
				break;
			case "2":
				String responde;
				boolean siVale = false;
				boolean sale = false;
				
				String[] amodificar = {"1.- Fecha inicio alquiler","2.- Fecha prevista fin","3.- Atr�s (salir)"};
				String vale = "123";
				
				do {
					metodos.TreeMapToArrayList.listarAlquileres(empresa.getAlquileres());	//Listamos
					System.out.println("Escribe el c�digo del alquiler a modificar");
					responde = PideDato.cadena(lector);
					//Si respuesta es una matr�cula v�lida se acaba el bucle
					if (empresa.getAlquileres().containsKey(responde))
					{
						siVale = true;
					}
				}while(!siVale);
				
				do
				{
					switch (Metodos.menu(amodificar, vale,"Fecha de alquiler a modificar", menError, lector))
					{
					case "1":
						GregorianCalendar nuevaFechaIni = PideDato.fecha("Nueva fecha de devoluci�n prevista", lector);
						empresa.getAlquileres().get(responde).setFechaInicioAlquiler(nuevaFechaIni);
						Serializar.grabaEmpresa(empresa); 
						break;
					case "2":
						GregorianCalendar nuevaFechaDev = PideDato.fecha("Nueva fecha de devoluci�n prevista", lector);
						empresa.getAlquileres().get(responde).setFechaPrevistaFinAlquiler(nuevaFechaDev);
						Serializar.grabaEmpresa(empresa);
						break;
					case "3":
						sale = true;
						break;
					}
				}while (!sale);
				break;
			case "3":	//Devolver
				String contesta;
				boolean valida = false;
				do 
				{
					metodos.TreeMapToArrayList.listarAlquileres(empresa.getAlquileres());	//Listamos
					System.out.println("Escribe el c�digo del alquiler a modificar");
					contesta = PideDato.cadena(lector);
					//Si respuesta es una matr�cula v�lida se acaba el bucle
					if (empresa.getAlquileres().containsKey(contesta))
					{
						valida = true;
					}
				}while(!valida);
				
				GregorianCalendar nuevaFecha = PideDato.fecha("Fecha de devoluci�n", lector);
				empresa.getAlquileres().get(contesta).setFechaDevolucion(nuevaFecha);	//Set Fecha
				Serializar.grabaEmpresa(empresa);
				break;
			case "4":
				seSale = true;
				break;
			}
		}while(!seSale);
	}
	protected static void ListadoOficinas(Empresa empresa, Scanner l)	//Protegido porque lo queremos usar en PideDato
	{
//		String elegida = null;
		
//		String claves = metodos.TreeMapToArrayList.CodigoOficinas(empresa.getOficinas());
		ArrayList<Oficina>ofis = new ArrayList<Oficina>(empresa.getOficinas().values());//Metemos los valores del treeMap en el ArrayList
		Metodos.pintaSubrayado("Listado de oficinas");
		for (Oficina ofi:ofis)
		{
//			opc[i]=ofi.toString();
//			i++;
			System.out.println(ofi);
		}
//		elegida = l.nextLine();
//		switch (Metodos.menu(opc, claves, "Escribe un c�digo de oficina de la siguiente lista", errorLetras, l))
		
//		return elegida;
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
		boolean adios = false;
		
		do
		{
			switch (metodosMenu.Metodos.menu(tipo, opcio, "LISTAR VEH�CULOS EL�CTRICOS", errorLetras, lector))
			{
			case "1":	//Coche el�ctrico
				
					Menus.listadosElectricos(empresa, "CocheElectrico", lector);
					break;
			case "2":	//Moto
				
					Menus.listadosElectricos(empresa, "Moto", lector);
					break;
			case "3":
				adios = true;	//se va
				break;
			}
		}while(!adios);
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
			break;
			
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
				break;
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
				break;
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
			break;
		case "B":	//Eliminar
			Menus.eliminaOficina(empresa, lector);
			break;
		case "C": //Salir
			break;
		}
		
	}
	private static void eliminar(Empresa empresa, Scanner lector)
	{
		String elegida;
		System.out.println("Elija la matr�cula del veh�culo a eliminar:");
		Menus.listarVehiculos(empresa);//Los listamos
		elegida = lector.nextLine();
		
		if (empresa.getOficinas().containsKey(elegida))
		{
			empresa.eliminarOficina(elegida);
			Serializar.grabaEmpresa(empresa);
		}
		
		
	}
	
	private static  void combustion(Empresa empresa, Scanner lector) throws TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		
		String[] combustion = {"1.- Coche","2.- Furgoneta", "3.- Volver"};
		String opcValidas = "1234";
		
			
		switch (metodosMenu.Metodos.menu(combustion, opcValidas, "Vehiculos de combusti�n", menError, lector))
		{
		case "1":	//Coche
			Menus.creaCocheCombustion(empresa, lector);		
			break;
		case "2":	//Furgoneta
			Menus.creaFurgoneta(empresa, lector);
			break;
		case "3":	//Salir
			break;
		}
			
	}
	
	private static void listarVehiculos(Empresa empresa)
	{
		
		ArrayList<Vehiculo> flota = new ArrayList<Vehiculo>(empresa.getGaraje().values());
		for (Vehiculo a:flota)
		{
			System.out.println(a);
		}
	}
	
	private static void electrico(Empresa empresa, Scanner lector) throws TiempoRecargaNoValidoException, TipoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, RecargoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		
		String[] electrico = {"1.- Coche","2.- Moto", "3.- Volver"};
		String opcValidas = "1234";
		boolean adios = false;
		
		do
		{
			switch (metodosMenu.Metodos.menu(electrico, opcValidas, "Vehiculos el�ctricos", menError, lector))
			{
			case "1":	//Coche
				Menus.creaCocheElectrico(empresa, lector);
				break;
			case "2":	//Moto
				Menus.creaMoto(empresa, lector);
				break;
			case "3":	//Salir
				adios = true;
				break;
			}
		}while(!adios);
		
	}
	private static void add(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
	{
		String[] elec = {"A.- De combusti�n","B.- El�ctrico","C.- Salir"}; //elec de Elecci�n
		String validas = "ABC";
		boolean sale = false;
		
		do
		{
			switch (metodosMenu.Metodos.menu(elec, validas, "TIPO DE VEH�CULO",errorLetras, lector))
			{
			case "A","a":	//Combustion
				Menus.combustion(empresa, lector);
				break;
			case "B","b":	//Electrico
				Menus.electrico(empresa, lector);
				break;
			case "C","c": //SALIR
				sale = true;
				break;
			}
		}while (!sale);
	}
	private static void flotaVehicular(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
	{
		String[] elec = {"A.- A�ADIR VEH�CULO","B.- ELIMINAR VEH�CULO","C.- SALIR"}; //elec de Elecci�n
		String validas = "ABC";
		boolean sale = false;
		
		do
		{
			switch (metodosMenu.Metodos.menu(elec, validas, "FLOTA DE VEH�CULOS",errorLetras, lector))
			{				
			case "A","a": //Lo hacemos tolerante a may�sculas y min�sculas
				Menus.add(empresa, lector);
			break;
			case "B","b":	//Eliminar veh�culo
				Menus.eliminar(empresa, lector);
			break;
			case "C","c": //SALIR
				sale = true;
				break;
			}
		}while(!sale);
	}
	private static void clientes(Empresa empresa, Scanner lector) throws LicenciaNoValidaException
	{
		String[] opcioness = {"A.- A�adir cliente", "B.- Dar de baja", "C.- Salir"};
		String Validas = "ABC";
		boolean sale = false;
		
		do
		{
			switch (metodosMenu.Metodos.menu(opcioness, Validas, "GESTI�N DE CLIENTES",errorLetras, lector))
			{
			case "A","a": 	//Damos de alta nuevos clientes
				Cliente nuevo =InterfazUsuario.PideDato.cliente(lector);
				empresa.nuevoCliente(nuevo);
				Serializar.grabaEmpresa(empresa);
				break;
			case "B","b":	//Eliminamos clientes de la empresa
				Menus.eliminaCliente(empresa, lector);
				/*
				 * 1- Listar empleados
				 * 2- Buscar por clave principal
				 * 3- Eliminar
				 */
				break;
			case "C","c":
				break;
			}
		}while(!sale);
		
	}
	private static void ListarClientes(Empresa empresa)
	{
		ArrayList<Cliente> clientes = new ArrayList<Cliente>(empresa.getClientes().values());
		for (Cliente nuevo:clientes)
		{
			System.out.printf("Cliente:"+nuevo+"DNI:"+nuevo.getDni());
		}
	}
	private static void eliminaCliente(Empresa empresa, Scanner lector)
	{
		String elegida;
		boolean bien = false;
		System.out.println("Elija el DNI del cliente a eliminar:");
		
		do{
			Menus.ListarClientes(empresa);
			elegida = lector.nextLine();
			if (empresa.getOficinas().containsKey(elegida))
			{
				empresa.eliminarOficina(elegida);
				Serializar.grabaEmpresa(empresa);
				bien = true;
			}
		}while(!bien);
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
				break;
			case "B","b":	//A�adir o eliminar oficinas
				Menus.MenuOficinas(empresa, lector);
				break;
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
		boolean sale = false;
		
		respuesta = metodosMenu.Metodos.menu(opcion, metodos.TreeMapToArrayList.DNIsEmpleados(empresa.getEmpleados()),"Escriba el DNI del empleado a borrar", errorLetras, lector);
		if (empresa.getEmpleados().containsKey(respuesta))	//Si alguien usa esa clave principal
		{
			String[] lista = {"S.- S�", "N.- No"};
			String valor = "SN";
			do
			{
				switch (metodosMenu.Metodos.menu(lista, valor,"El empleado se borrar� �Est� seguro?", errorLetras,  lector))
				{
				case "S":
					empresa.despedirEmpleado(respuesta);
					Serializar.grabaEmpresa(empresa); 	//Guardamos 
					break;
				case "N":
					sale = true;
					break; 	//Sale sin tocar nada
				}
			}while(!sale);
		}
	}
	
	private static void MenuEmpleado(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		String[] opcc = {"A.- A�adir nuevo empleado","B.- Dar de baja un empleado","C.- Salir"};
		String Validas = "ABC";

		
		switch (metodosMenu.Metodos.menu(opcc, Validas, "GESTOR DE EMPLEADOS", errorLetras, lector))
		{
		case "A","a": //A�adir empleado 
			Empleado emple = PideDato.empleado(empresa, lector); //Creamos empleado
			empresa.nuevoEmpleado(emple);	//A�adimos el empleado al TreeMap
			Serializar.grabaEmpresa(empresa);
			break;
		case "B","b":	//Eliminar empleado
			Menus.eliminaEmpleado(empresa, lector);
			break;
		case "C","c":
			break;	//Salir
		}
	}
	
	private static void creaCocheCombustion(Empresa empresa, Scanner lector) throws TipoNoValidoException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		
			CocheCombustion nuevo = PideDato.cocheCombustion(empresa, lector);
			empresa.addAlGaraje(nuevo);
			Serializar.grabaEmpresa(empresa);//Guardamos cambios
		
	}
	
	private static void creaCocheElectrico(Empresa empresa, Scanner lector) throws TiempoRecargaNoValidoException, TipoNoValidoException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
	{
		
			CocheElectrico nuevo = PideDato.cocheElectrico(empresa, lector);
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