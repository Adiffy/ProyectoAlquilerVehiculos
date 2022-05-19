package InterfazUsuario;
//
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//import java.util.Scanner;
//import java.util.TreeMap;
//import accesoADatos.Serializar;
//import clasesObjetos.Alquiler;
//import clasesObjetos.Categoria;
//import clasesObjetos.Cliente;
//import clasesObjetos.CocheCombustion;
//import clasesObjetos.CocheElectrico;
//import clasesObjetos.DeCombustion;
//import clasesObjetos.Empleado;
//import clasesObjetos.Empresa;
//import clasesObjetos.Furgoneta;
//import clasesObjetos.Matricula;
//import clasesObjetos.Moto;
//import clasesObjetos.Oficina;
//import clasesObjetos.Persona;
//import clasesObjetos.Vehiculo;
//import comparadores.ComparaVehiculoPorCategoria;
//import exceptions.CarnetRequeridoInvalidoException;
//import exceptions.CilindradaNoValidaException;
//import exceptions.CodigoNoValidoException;
//import exceptions.CodigoPostalException;
//import exceptions.ConsumoNoValidoException;
//import exceptions.DNInoValidoException;
//import exceptions.EmisionesNoValidasException;
//import exceptions.FechaNoValidaException;
//import exceptions.LetrasMatriculaNoValidasException;
//import exceptions.LicenciaNoValidaException;
//import exceptions.LongitudCadenaNoValidaException;
//import exceptions.LongitudNoValidaException;
//import exceptions.NumPlazasNoValidoException;
//import exceptions.NumeroMatriculaNoValidoException;
//import exceptions.PlantaNoValidaException;
//import exceptions.PotenciaNoValidaException;
//import exceptions.RecargoNoValidoException;
//import exceptions.TiempoRecargaNoValidoException;
//import exceptions.TipoNoValidoException;
//import metodos.TreeMapToArrayList;
//import metodosMenu.Metodos;
//
//public class Menus {
//
//	//Variables que se reutilizan en muchos de los m�todos de esta clase
//	private static String menError = "Seleccione una opci�n v�lida. Puede ser:"; //El mensaje que aparecer� si el usuario elige una opci�n inv�lida
//	private static String mensaje = "Introduzca una de las siguientes opciones:";
//	private static String errorLetras = "Debe elegir una opci�n v�lida pulsando:"; //Error en los men�s no-num�ricos
//	private static String opcValidas ="123S";
//	
//	public static void principal(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, LicenciaNoValidaException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException, FechaNoValidaException, DNInoValidoException
//	{
//		
//		String[] opciones = {"1.- Configuraci�n general", "2.- Gesti�n de oficinas", "3.- Mostrar listados", "S.- Salir"};
//		
//		
//		boolean noSale = true; //buleano para el bucle 
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(opciones, opcValidas, "MEN� PRINCIPAL", menError, lector))
//			{
//			case "1":
//				try {
//					Menus.ficherosMaestros(empresa, lector);
//				} catch (TipoNoValidoException e) {
//					System.out.println("Escriba tipo v�lido");
//					e.printStackTrace();
//				} catch (TiempoRecargaNoValidoException e) {
//					System.out.println("Tiempo de recarga no v�lido (menor o igual a 0)");
//					e.printStackTrace();
//				} catch (LicenciaNoValidaException e) {
//					System.out.println("Licencia no v�lida");
//					e.printStackTrace();
//				} catch (EmisionesNoValidasException e) {
//					System.out.println("Emisiones no v�lidas");
//					e.printStackTrace();
//				} catch (ConsumoNoValidoException e) {
//					System.out.println("Tipo de consumo no v�lido");
//					e.printStackTrace();
//				} catch (PotenciaNoValidaException e) {
//					System.out.println("Potencia/caballaje no v�lido");
//					e.printStackTrace();
//				} catch (RecargoNoValidoException e) {
//					System.out.println("Porcentaje de recargo no v�lido");
//					e.printStackTrace();
//				} catch (LetrasMatriculaNoValidasException e) {
//					System.out.println("Demasiadas letras de matr�cula o muy pocas");
//					e.printStackTrace();
//				} catch (NumeroMatriculaNoValidoException e) {
//					System.out.println("Longitud de n�mero de la matr�cula inv�lida");
//					e.printStackTrace();
//				} catch (NumPlazasNoValidoException e) {
//					System.out.println("N�mero de plazas no v�alido");
//					e.printStackTrace();
//				} catch (LongitudNoValidaException e) {
//					System.out.println("Longitud no v�lida");
//					e.printStackTrace();
//				} catch (PlantaNoValidaException e) {
//					System.out.println("Planta demasiado alta o planta menor que 0");
//					e.printStackTrace();
//				} catch (CodigoPostalException e) {
//					System.out.println("Codigo postal inv�lido");
//					e.printStackTrace();
//				} catch (LongitudCadenaNoValidaException e) {
//					System.out.println("Longitud de palabra incorrecta");
//					e.printStackTrace();
//				} catch (CilindradaNoValidaException e) {
//					System.out.println("Cilindrada no permitida");
//					e.printStackTrace();
//				} catch (CarnetRequeridoInvalidoException e) {
//					System.out.println("Carnet requerido no v�lido");
//					e.printStackTrace();
//				} catch (FechaNoValidaException e) {
//					System.out.println("Fecha no v�lida");
//					e.printStackTrace();
//				} catch (CodigoNoValidoException e) {
//					System.out.println("C�digo no v�lido");
//					e.printStackTrace();
//				}
//				break;
//			case "2"://Gesti�n de oficinas
//				Menus.oficinas(empresa, lector);
//				break;
//			case "3":	//Listados
//				Menus.MenuListados(empresa, lector);
//				break;
//			case "S": //SALIR DE VERDAD
//				BarraDeCarga.pintar();
//				noSale = false;
//				break;	
//			}
//		}while(noSale);
//	}
//	
//	private static void ficherosMaestros(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, LicenciaNoValidaException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException, FechaNoValidaException, CodigoNoValidoException, DNInoValidoException 
//	{
//		String[] opciones = {"1.- Configurar garaje", "2.- Gestionar clientes", "3.- Configurar oficinas", "4.- Gestionar alquileres","S.- Salir"};
//		String opcValidas = "1234S";
//			boolean sale= false ;
//		do 
//		{
//			switch (metodosMenu.Metodos.menu(opciones, opcValidas, "Configuraci�n general", menError, lector))
//			{
//			case "1":
//				modificaGaraje(empresa,lector);
//				break;
//			case "2":
//				Menus.clientes(empresa, lector);
//				break;
//			case "3":
//				Menus.oficinas(empresa, lector);
//				break;
//			case "4":
//				Menus.alquileres(empresa, lector);
//				break;
//			case "S":
//				sale = true;
//				break;
//			}
//		}while(!sale);
//		
//	}
//	private static void modificaGaraje(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, PlantaNoValidaException, TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, TiempoRecargaNoValidoException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
//	{
//		String[] opc = {"1.- A�adir / eliminar veh�culos a la flota","2.- A�adir categor�as", "S.- Salir"};
//		String OPC = "12S";
//		boolean salir = false;
//		do
//		{
//			switch (metodosMenu.Metodos.menu(opc, OPC, "Gesti�n de la flota de veh�culos", menError, lector))
//			{
//			case "1":
//				Menus.flotaVehicular(empresa, lector);
//				break;
//			case "2":
//				Menus.categorias(empresa, lector);
//				break;
//			case "S":
//				salir = true;
//				break;
//			}
//		}while (!salir);
//	}
//	private static void categorias(Empresa empresa, Scanner lector) throws RecargoNoValidoException
//	{
//		Categoria nueva = PideDato.nuevaCategoria(empresa, lector);
//		empresa.addCategoria(nueva);
//		Serializar.grabaEmpresa(empresa);
//	}
//	private static void alquileres(Empresa empresa, Scanner lector) throws FechaNoValidaException, LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, PlantaNoValidaException, CodigoNoValidoException
//	{
//		String[] opcione = {"1.- Nuevo alquiler","2.- Modificar alquiler existente","3.- Eliminar alquiler","S.- Salir"};
//		String mensajeError = "Elija una opci�n v�lida:";
//		boolean sale = false;
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(opcione, opcValidas, "Gesti�n de alquileres", mensajeError, lector))
//			{
//			case "1"://crear un alquiler
//				Alquiler nuevo =  PideDato.alquiler(empresa, lector);	//lo pedimos
////				new Alquiler(new GregorianCalendar(1995,12,25),new GregorianCalendar(1995,12,25)); 
//				
//				empresa.nuevoAlquiler(nuevo);
//				Serializar.grabaEmpresa(empresa);
//				break;
//			case "2":	//Cambiar fechas y dem�s
//				Menus.editaAlquiler(empresa, lector);
//				break;
//			case "3":	//Eliminar alquiler
//				boolean bien = false;
//				do
//				{
//					Menus.listarAlquileres(empresa.getAlquileres());  //Listamos los alquileres existentes
//					String respuesta = PideDato.cadena("C�digo de alquiler a eliminar", lector);
//					
//					if (empresa.getAlquileres().containsKey(respuesta))	//Si existe
//					{
//						empresa.quitaAlquiler(empresa.getAlquileres().get(respuesta));	//La eliminamos
//						Serializar.grabaEmpresa(empresa);
//						bien = true;
//					}
//				}while(!bien);
//				
//				break;
//			case "S":
//				sale = true;	//sale
//				break;
//			}
//		}while(!sale);
//	}
//	private static void editaAlquiler(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, PlantaNoValidaException, FechaNoValidaException
//	{
//		String[] elecciones = {"1.- Mostrar precio alquiler","2.- Editar fecha de finalizaci�n prevista","3.- Finalizar alquiler","S.- Volver (salir)"};
//		boolean seSale = false;
//		Matricula respuesta = null;
//		
//		do
//		{
//			switch (Metodos.menu(elecciones, opcValidas, errorLetras, mensaje, lector))
//			{
//			case "1":	//Listamos veh�culos y seg�n PK decimos precioAlquiler
//				boolean valido = false;
//				do 
//				{
//					ArrayList<Vehiculo> aMostrar = metodos.TreeMapToArrayList.Garaje(empresa.getGaraje());	//Pasamos a ArrayList
//					listarVehiculos(aMostrar);	//Listamos
//					System.out.println("Escribe la matr�cula del veh�culo a modificar");
//					respuesta = PideDato.matricula(lector);
//					//Si respuesta es una matr�cula v�lida se acaba el bucle
//					if (empresa.getGaraje().containsKey(respuesta))
//					{
//						valido = true;
//					}
//				}while(!valido);
//				//Metemos el precio del alquiler en un double para que quede m�s limpio
//				double aMostrar = empresa.getGaraje().get(respuesta).PrecioAlquiler();
//				//Mostramos resultados
//				System.out.println(empresa.getGaraje().get(respuesta));
//				System.out.println(aMostrar);
//				break;
//			case "2":
//				String responde;
//				boolean siVale = false;
//				boolean sale = false;
//				
//				String[] amodificar = {"1.- Fecha inicio alquiler","2.- Fecha prevista fin","S.- Atr�s (salir)"};
//				String vale = "12S";
//				
//				do {
//					listarAlquileres(empresa.getAlquileres());	//Listamos
//					System.out.println("Escribe el c�digo del alquiler a modificar");
//					responde = PideDato.cadena(lector);
//					//Si respuesta es una matr�cula v�lida se acaba el bucle
//					if (empresa.getAlquileres().containsKey(responde))
//					{
//						siVale = true;
//					}
//				}while(!siVale);
//				
//				do
//				{
//					switch (Metodos.menu(amodificar, vale,"Fecha de alquiler a modificar", menError, lector))
//					{
//					case "1":
//						GregorianCalendar nuevaFechaIni = PideDato.fecha("Nueva fecha de devoluci�n prevista", lector);
//						empresa.getAlquileres().get(responde).setFechaInicioAlquiler(nuevaFechaIni);
//						Serializar.grabaEmpresa(empresa); 
//						break;
//					case "2":
//						GregorianCalendar nuevaFechaDev = PideDato.fecha("Nueva fecha de devoluci�n prevista", lector);
//						empresa.getAlquileres().get(responde).setFechaPrevistaFinAlquiler(nuevaFechaDev);
//						Serializar.grabaEmpresa(empresa);
//						break;
//					case "S":
//						sale = true;
//						break;
//					}
//				}while (!sale);
//				break;
//			case "3":	//Devolver
//				String contesta;
//				boolean valida = false;
//				do 
//				{
//					listarAlquileres(empresa.getAlquileres());	//Listamos
//					System.out.println("Escribe el c�digo del alquiler a modificar");
//					contesta = PideDato.cadena(lector);
//					//Si respuesta es una matr�cula v�lida se acaba el bucle
//					if (empresa.getAlquileres().containsKey(contesta))
//					{
//						valida = true;
//					}
//				}while(!valida);
//				
//				GregorianCalendar nuevaFecha = PideDato.fecha("Fecha de devoluci�n", lector);
//				empresa.getAlquileres().get(contesta).setFechaDevolucion(nuevaFecha);	//Set Fecha
//				Vehiculo a = empresa.getAlquileres().get(contesta).getaAlquilar();
//				empresa.getAlquileres().get(contesta).setAlquilado( a, false); //Deja de estar alquilado
//				Serializar.grabaEmpresa(empresa);
//				break;
//			case "S":
//				seSale = true;
//				break;
//			}
//		}while(!seSale);
//	}
//	protected static void ListadoOficinas(Empresa empresa, Scanner l)	//Protegido porque lo queremos usar en PideDato
//	{
////		String elegida = null;
//		
////		String claves = metodos.TreeMapToArrayList.CodigoOficinas(empresa.getOficinas());
//		ArrayList<Oficina>ofis = new ArrayList<Oficina>(empresa.getOficinas().values());//Metemos los valores del treeMap en el ArrayList
//		Metodos.pintaSubrayado("Listado de oficinas");
//		for (Oficina ofi:ofis)
//		{
////			opc[i]=ofi.toString();
////			i++;
//			System.out.println(ofi);
//		}
////		elegida = l.nextLine();
////		switch (Metodos.menu(opc, claves, "Escribe un c�digo de oficina de la siguiente lista", errorLetras, l))
//		
////		return elegida;
//	}
//	private static void MenuListados(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, PlantaNoValidaException, TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException
//	{
//		String[] list = {"1.- Listar veh�culos","2.- Listar Personas", "3.- Listar Oficinas","4.- Listar Alquileres","S.- Salir"};
//		String validas = "1234S";
//		boolean seVa = false;
//		
//		do 
//		{
//			switch (metodosMenu.Metodos.menu(list, validas, "LISTADOS",mensaje, lector))
//			{
//			case "1": 	//Vehiculos
//				Menus.listadoVehiculos(empresa, lector);
//				break;
//			case "2":	//Personas
//				Menus.listadoPersonas(empresa, lector);
//				break;
//			case "3":	//Oficinas
//				Menus.listadoOficinas(empresa, lector);
//				break;
//			case "4":
//				Menus.listarAlquileres(empresa.getAlquileres()); 	//Listamos los alquileres 
//				break;
//			case "S": //Salir
//				seVa = true;
//				break;
//			}
//		}while(!seVa);
//		
//	}
//	protected static void listadoPersonas(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
//	{
//		String[] elecc = {"1.- Empleados","2.- Clientes","3.- Todas las personas","S.- Atr�s (Salir)"};
//		String posib = "123";
//		String error = "Seleccione una opci�n de este conjunto:";
//		boolean sale = false;
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(elecc, posib, "TIPOS DE PERSONAS A LISTAR", error, lector))
//			{
//			case "1":	//Empleados
//				Menus.listadoPersonasPor(empresa, "Empleado", lector);
//				break;
//			case "2":	//Clientes
//				Menus.listadoPersonasPor(empresa, "Clientes", lector);
//				break;
//			case "3":	//Todas las personas
//				ArrayList<Persona> lista = TreeMapToArrayList.Personas(empresa);	//Obtenemos el ArrayList con todas las personas
//				for (Persona a:lista)	//Listamos
//				{
//					System.out.println(a);
//				}
//				break;
//			case "S": //Salir 
//				break;
//			}
//		}while(!sale);
//	}
//	
//	private static void listarVehiculos(ArrayList <Vehiculo> aMostrar) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException
//	{
//		for (Vehiculo a:aMostrar)
//		{
//			System.out.printf("Matr�cula:"+a.getMatricula()+" - "+a);
//		}
//	}
//	
//	private static void listadoVehiculos(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, PlantaNoValidaException
//	{
//		String[] elecc = {"1.- De combusti�n","2.- El�ctricos","3.- Todos","S.- Atr�s (Salir)"};
//		
//		String error = "Seleccione una opci�n de este conjunto:";
//		boolean sale = false;
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(elecc, Menus.opcValidas, "TIPOS DE VEH�CULOS A LISTAR", error, lector))
//			{
//			case "1":	//De combusti�n
//				Menus.listaCombustion(empresa, lector);
//				break;
//			case "2":	//El�ctricos
//				Menus.listaElectrico(empresa, lector);
//				break;
//			case "3":	//Listar todos
//				ArrayList<Vehiculo> aMostrar = TreeMapToArrayList.Garaje(empresa.getGaraje());
//				listarVehiculos(aMostrar);
//				break;
//			case "S": //Salir 
//				sale = true;
//				break;
//			}
//		}while (!sale);
//	}
//	private static void listaCombustion(Empresa empresa, Scanner lector) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException
//	{
//		String[] posibilidades = {"1.- Listar  s","2.- Listar Furgonetas","3.- Listar todos los veh�culos de combusti�n","S.- Volver atr�s"};
//		boolean sale = false;
//		
//		do
//		{
//			switch (Metodos.menu(posibilidades, opcValidas, "LISTAR VEH�CULOS DE COMBUSTI�N", mensaje, lector))
//			{
//			case "1":	//S�lo coches
//				ArrayList<DeCombustion> cocheraCoches = TreeMapToArrayList.CocheCombustion(empresa.getGaraje());
//				//Listamos
//				for (Vehiculo a:cocheraCoches)
//				{
//					System.out.println(a);
//				}
//				break;
//			case "2":	//S�lo furgonetas
//				ArrayList<DeCombustion> cocheraFurgonetas = TreeMapToArrayList.Furgoneta(empresa.getGaraje());
//				//Listamos
//				for (Vehiculo a:cocheraFurgonetas)
//				{
//					System.out.println(a);
//				}
//				break;
//			case "3":	//Listar todos los veh�culos
//				ArrayList<DeCombustion> cochera = TreeMapToArrayList.Combustion(empresa.getGaraje());
//				for (Vehiculo a:cochera)
//				{
//					System.out.printf("Matr�cula: "+a.getMatricula()+" | "+a);
//					System.out.println("");
//				}
//				break;
//			case "S":
//				sale = true;
//				break;
//			}
//		}while(!sale);
//	}
//	
//	private static void listadoOficinas(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
//	{
//		String[] elecc = {"1.- Listar todas las oficinas","2.- Empleados","S.- Atr�s (Salir)"};
//		String posib = "12S";
//		String error = "Seleccione una opci�n de este conjunto:";
//		boolean sale = false;
//		
//		do
//		{	//No saldr� hasta que le d� expl�citamente
//			
//			switch (metodosMenu.Metodos.menu(elecc, posib, "LISTAR OFICINAS", error, lector))
//			{
//			case "1":	//Todas las oficinas
//				ArrayList<Oficina> ofis = TreeMapToArrayList.Oficinas(empresa.getOficinas());	//Lo pasamos a ArrayList
//				for (Oficina a:ofis)
//				{
//					System.out.printf("Oficina: "+a.getC�digo()+" - "+a.getDescripci�n());
//					System.out.println(""); 	//Antes de listar la siguiente oficina hacemos espacio con salto de l�nea
//				}
//				break;
//			case "2":	//Empleados
//				boolean correcto = false;
//				String codigo;
//				do
//				{
//					//Primero listamos las oficinas
//					ArrayList<Oficina> ofi = TreeMapToArrayList.Oficinas(empresa.getOficinas());	//Lo pasamos a ArrayList
//					for (Oficina a:ofi)
//					{
//						System.out.printf("Oficina: "+a.getC�digo()+" - "+a.getDescripci�n());
//						System.out.println(""); //Salto de linea
//					}
//					//Elige la clave
//					codigo = PideDato.cadena("C�digo de la oficina:", lector);
//					if (empresa.getOficinas().containsKey(codigo))	//Si existe alguna con ese c�digo
//					{
//						correcto = true;
//					}else {
//						System.out.println("Oficina no encontrada");
//					}
//				}while(!correcto);
//				Menus.listadoEmpleadosPorOficina(empresa, codigo, lector);	//Listamos los empleados de dicha oficina
//				break;
//			case "S": //Salir 
//				sale = true;
//				break;
//			}
//		}while(!sale);
//	}
//	private static void listadoEmpleadosPorOficina(Empresa empresa, String string, Scanner lector) {
//
//			Oficina a = empresa.getOficinas().get(string);	//Si existe una oficina con esa clave
//			ArrayList<Empleado> personal = TreeMapToArrayList.Empleados(a.getPersonal());	//Lo pasamos a ArrayList
//			for (Empleado emple:personal)
//			{
//				System.out.println(emple); 	//Imprimimos por pantalla el personal
//			}
//
//	}
//
//	private static void listaElectrico(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException
//	{
//		String[] tipo = {"1.- Coche el�ctrico", "2.- Moto el�ctrica", "S.- Atr�s (Salir)"};
//		String opcio = "12S";
//		boolean adios = false;
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(tipo, opcio, "LISTAR VEH�CULOS EL�CTRICOS", errorLetras, lector))
//			{
//			case "1":	//Coche el�ctrico
//				
//					Menus.listadosElectricos(empresa, "CocheElectrico", lector);
//					break;
//			case "2":	//Moto
//				
//					Menus.listadosElectricos(empresa, "Moto", lector);
//					break;
//			case "S":
//				adios = true;	//se va
//				break;
//			}
//		}while(!adios);
//	}
//	private static void listadosElectricos(Empresa empresa,String tipoVehiculo, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException
//	{
//		String error = "Seleccione una opci�n de este conjunto:";
//		String[] a = {"1.- Por Matr�cula","2.- Por categor�a","S.- Atr�s (Salir)"};
//		String b = "12S";
//		boolean sale = false;		//A priori no se sale
//	
//		do
//		{
//			switch (metodosMenu.Metodos.menu(a, b, "Listar"+ tipoVehiculo +"por", error, lector))
//			{
//			case "1":	//Listar por Matr�cula
//				ArrayList <Moto> lista = null;
//				ArrayList <CocheElectrico> lista2 = null;
//				boolean Moto;	//Sabremos si es Moto o no 
//				
//				if (tipoVehiculo.compareToIgnoreCase("Moto")==0  )
//				{
//					Moto = true;	//Lo pasamos a ArrayList
//					lista = TreeMapToArrayList.Motos(empresa.getGaraje());
//					
//				}else {
//					Moto = false;	//Lo pasamos a ArrayList
//					lista2 = TreeMapToArrayList.CocheElectrico(empresa.getGaraje());
//				}
//				//Ahora mostramos los listados
//				if (Moto)
//				{
//					for (Vehiculo aux: lista)
//					{
//						System.out.printf(">>" + aux.toString());
//						System.out.println("");
//					}
//				}else {
//					for (Vehiculo aux: lista2)
//					{
//						System.out.printf( ">>" + aux.toString());
//						System.out.println("");
//					}
//				}
//				break;
//				
//			case "2":	//Listar por categoria
//				
//				lista = null;
//				lista2 = null;
//				if (tipoVehiculo.compareToIgnoreCase("Moto")==0  )
//				{
//					Moto = true;
//					lista = TreeMapToArrayList.Motos(empresa.getGaraje());
//					
//				}else {
//					Moto = false;
//					lista2 = TreeMapToArrayList.CocheElectrico(empresa.getGaraje());
//				}			
//				//Ahora mostramos los listados
//				if (Moto)
//				{
//					ListarMotosPorCategoria(lista);
//				}else {
//					ListarCocheElectricoPorCategoria(lista2);;
//				}
//				break;
//			case "S":	//SALIR
//				sale = true;
//				break;
//			}
//		}while(!sale);
//	}
//	private static void ListarCocheElectricoPorCategoria(ArrayList<CocheElectrico> flota) 
//	{
//		
//		ComparaVehiculoPorCategoria c = new ComparaVehiculoPorCategoria(); 
//		flota.sort(c);
//		for (Vehiculo auxiliar: flota)
//		{
//			System.out.printf("Coche el�ctrico: "+auxiliar);
//		}
//	}
//	private static void ListarMotosPorCategoria(ArrayList<Moto> lista) 
//	{
//		
//		ComparaVehiculoPorCategoria c = new ComparaVehiculoPorCategoria(); 
//		lista.sort(c);
//		for (Vehiculo auxiliar: lista)
//		{
//			System.out.printf("Moto el�ctrica: "+auxiliar);
//		}
//	}
//	private static void listadoPersonasPor(Empresa empresa,String tipoPersona, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
//	{
//		String error = "Seleccione una opci�n de este conjunto:";
//		String[] a = {"1.- Por DNI","2.- Por nombre","S.- Atr�s (Salir)"};
//		String b = "12S";
//		switch (metodosMenu.Metodos.menu(a, b, "Listar "+ tipoPersona, error, lector))
//		{
//		case "1":	//Listar por DNI
//			TreeMap<String, Cliente> lista = null;
//			TreeMap<String, Empleado> lista2 = null;
//			boolean Cliente;	//Sabremos si es Cliente o no 
//			if (tipoPersona.compareToIgnoreCase("Cliente")==0 || tipoPersona.compareToIgnoreCase("Clientes")==0 )
//			{
//				Cliente = true;
//				lista = empresa.getClientes();
//			}else {
//				Cliente = false;
//				lista2 = empresa.getEmpleados();
//			}
//			//Ahora mostramos los listados
//			if (Cliente)
//			{
//				Menus.ListarTreeMapClienteDNI(lista);
//			}else {
//				Menus.ListarTreeMapEmpleadoDNI(lista2);
//			}
//			break;
//			
//		case "2":	//Listar por nombre
//			 ArrayList<Cliente> A = new ArrayList<>();
//			 ArrayList<Empleado> E = new ArrayList<>();
//			if (tipoPersona.compareToIgnoreCase("Cliente")==0 || tipoPersona.compareToIgnoreCase("Clientes")==0 )
//			{
//				Cliente = true;
//				
//			}else {
//				Cliente = false;
//				
//			}
//			//Ahora mostramos los listados
//			if (Cliente)
//			{
//				A =empresa.ListarClienteNombre();
//				for (Cliente clien:A)
//				{
//					System.out.println(clien);
//				}
//			}else {
//				E = empresa.ListarEmpleadoNombre();
//				for (Empleado emple:E)
//				{
//					System.out.println(emple);
//				}
//			}
//			break;
//		case "S":	//SALIR
//			break;
//		}
//	}
//	
//	private static void eliminaOficina(Empresa empresa, Scanner lector)
//	{
//		Menus.ListarTreeMapOficinas(empresa.getOficinas());
//		String[] opcion = {""};
//		String respuesta;
//		
//		respuesta = metodosMenu.Metodos.menu(opcion, metodos.TreeMapToArrayList.DNIsEmpleados(empresa.getEmpleados()),"Escriba el identificador de la oficina a eliminar", menError, lector);
//		if (empresa.getEmpleados().containsKey(respuesta))	//Si alguien usa esa clave principal
//		{
//			String[] lista = {"S.- S�", "N.- No"};
//			String valor = "SN";
//			switch (metodosMenu.Metodos.menu(lista, valor,"El empleado se borrar� �Est� seguro?", menError,  lector))
//			{
//			case "S":
//				empresa.eliminarOficina(respuesta);
//				Serializar.grabaEmpresa(empresa);
//				break;
//			case "N":
//				break; 	//Sale sin tocar nada
//			}
//		}
//	
//	}
//	
//	private static void MenuOficinas(Empresa empresa, Scanner lector) throws LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
//	{
//		String[] opciom = {"A.- Nueva oficina", "B.- Eliminar oficina", "S.- Salir"};
//		String validad = "ABS";
//		boolean sale = false;
//
//		do
//		{
//			switch (metodosMenu.Metodos.menu(opciom, validad, "GESTOR DE OFICINAS", menError, lector))
//			{
//			case "A":	//A�adir
//				Oficina nueva = PideDato.oficina(lector);
//				empresa.nuevaOficina(nueva);	//Metemos la oficina
//				Serializar.grabaEmpresa(empresa); 	//Guardamos 
//				break;
//			case "B":	//Eliminar
//				Menus.eliminaOficina(empresa, lector);
//				break;
//			case "S": //Salir
//				sale = true;	//Termina el bucle
//				break;
//			}
//		}while(!sale);
//		
//	}
//	private static void eliminar(Empresa empresa, Scanner lector)
//	{
//		String elegida;
//		boolean encontrado = false;
//		do 
//		{
//			System.out.println("Elija la matr�cula del veh�culo a eliminar:");
//			Menus.listarVehiculos(empresa);//Los listamos
//			elegida = PideDato.cadena(lector);
//
//			if (empresa.getOficinas().containsKey(elegida))
//			{
//				empresa.eliminarOficina(elegida);
//				Serializar.grabaEmpresa(empresa);
//				encontrado = true;
//			}
//		}while (!encontrado);
//		
//		
//	}
//	
//	private static  void combustion(Empresa empresa, Scanner lector) throws TipoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
//	{
//		
//		String[] combustion = {"1.- Coche","2.- Furgoneta", "S.- Volver"};
//		String opcValidas = "12S";
//		
//			
//		switch (metodosMenu.Metodos.menu(combustion, opcValidas, "Vehiculos de combusti�n", menError, lector))
//		{
//		case "1":	//Coche
//			Menus.creaCocheCombustion(empresa, lector);		
//			break;
//		case "2":	//Furgoneta
//			Menus.creaFurgoneta(empresa, lector);
//			break;
//		case "S":	//Salir
//			break;
//		}
//			
//	}
//	
//	private static void listarVehiculos(Empresa empresa)
//	{
//		
//		ArrayList<Vehiculo> flota = new ArrayList<Vehiculo>(empresa.getGaraje().values());
//		for (Vehiculo a:flota)
//		{
//			System.out.println(a);
//		}
//	}
//	
//	private static void electrico(Empresa empresa, Scanner lector) throws TiempoRecargaNoValidoException, TipoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, RecargoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
//	{
//		
//		String[] electrico = {"1.- Coche","2.- Moto", "S.- Volver"};
//		String opcValidas = "12S";
//		boolean adios = false;
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(electrico, opcValidas, "Vehiculos el�ctricos", menError, lector))
//			{
//			case "1":	//Coche
//				Menus.creaCocheElectrico(empresa, lector);
//				break;
//			case "2":	//Moto
//				Menus.creaMoto(empresa, lector);
//				break;
//			case "S","s": //SALIR
//				adios = true;
//				break;
//			}
//		}while(!adios);
//		
//	}
//	private static void add(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
//	{
//		String[] elec = {"A.- De combusti�n","B.- El�ctrico","S.- Salir"}; //elec de Elecci�n
//		String validas = "ABS";
//		boolean sale = false;
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(elec, validas, "TIPO DE VEH�CULO",errorLetras, lector))
//			{
//			case "A","a":	//Combustion
//				Menus.combustion(empresa, lector);
//				break;
//			case "B","b":	//Electrico
//				Menus.electrico(empresa, lector);
//				break;
//			case "S","s": //SALIR
//				sale = true;
//				break;
//			}
//		}while (!sale);
//	}
//	private static void flotaVehicular(Empresa empresa, Scanner lector) throws TipoNoValidoException, TiempoRecargaNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, NumPlazasNoValidoException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
//	{
//		String[] elec = {"A.- A�ADIR VEH�CULO","B.- ELIMINAR VEH�CULO","S.- SALIR"}; //elec de Elecci�n
//		String validas = "ABS";
//		boolean sale = false;
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(elec, validas, "FLOTA DE VEH�CULOS",errorLetras, lector))
//			{				
//			case "A","a": //Lo hacemos tolerante a may�sculas y min�sculas
//				Menus.add(empresa, lector);
//				break;
//			case "B","b":	//Eliminar veh�culo
//				Menus.eliminar(empresa, lector);
//				break;
//			case "S","s": //SALIR
//				sale = true;
//				break;
//			}
//		}while(!sale);
//	}
//	private static void clientes(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, CarnetRequeridoInvalidoException, LongitudCadenaNoValidaException, DNInoValidoException
//	{
//		String[] opcioness = {"A.- A�adir cliente", "B.- Dar de baja", "S.- Salir"};
//		String Validas = "ABS";
//		boolean sale = false;
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(opcioness, Validas, "GESTI�N DE CLIENTES",errorLetras, lector))
//			{
//			case "A","a": 	//Damos de alta nuevos clientes
//				Cliente nuevo =InterfazUsuario.PideDato.cliente(lector);
//				empresa.nuevoCliente(nuevo);
//				Serializar.grabaEmpresa(empresa);
//				break;
//			case "B","b":	//Eliminamos clientes de la empresa
//				Menus.eliminaCliente(empresa, lector);
//				/*
//				 * 1- Listar empleados
//				 * 2- Buscar por clave principal
//				 * 3- Eliminar
//				 */
//				break;
//			case "S","s": //SALIR
//				sale = true;
//				break;
//			}
//		}while(!sale);
//		
//	}
//	private static void ListarClientes(Empresa empresa)
//	{
//		ArrayList<Cliente> clientes = new ArrayList<Cliente>(empresa.getClientes().values());
//		for (Cliente nuevo:clientes)
//		{
//			System.out.printf("Cliente:"+nuevo+"DNI:"+nuevo.getDni());
//		}
//	}
//	private static void eliminaCliente(Empresa empresa, Scanner lector)
//	{
//		String elegida;
//		boolean bien = false;
//		System.out.println("Elija el DNI del cliente a eliminar:");
//		
//		do{
//			Menus.ListarClientes(empresa);
//			elegida = lector.nextLine();
//			if (empresa.getOficinas().containsKey(elegida))
//			{
//				empresa.eliminarOficina(elegida);
//				Serializar.grabaEmpresa(empresa);
//				bien = true;
//			}
//		}while(!bien);
//	}
//	
//	private static void oficinas(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CarnetRequeridoInvalidoException, DNInoValidoException
//	{
//		String[] opcioness = {"A.- Oficinas", "B.- Empleados", "S.- Salir"};
//		String Validas = "ABS";
//		boolean sale = false;
//		
//		do 
//		{
//			switch (metodosMenu.Metodos.menu(opcioness, Validas, "GESTI�N DE OFICINAS",errorLetras, lector))
//			{
//			case "A","a":	//A�adir o eliminar oficinas
//				Menus.MenuOficinas(empresa, lector);
//				break;
//			case "B","b":	//A�adir o eliminar empleados
//				Menus.MenuEmpleado(empresa,lector);
//				break;
//			case "S","s": //SALIR
//				sale = true;
//				break;	
//			}
//		}while(!sale);
//		
//	}
//	
//	private static void ListarTreeMapEmpleadoDNI(TreeMap<String,Empleado> map)
//	{
////		map.entrySet().forEach((entry) -> {
////            System.out.printf("- Empleado: %s"+" %n", entry.getKey(), entry.getValue());
////        });
//		ArrayList<Empleado> lista = new ArrayList<Empleado>(map.values());
//		for (Empleado e:lista)
//		{
//			System.out.println(e);
//		}
//	}
//	private static void ListarTreeMapOficinas(TreeMap<String,Oficina> map)
//	{
////		map.entrySet().forEach((entry) -> {
////            System.out.printf("Con C�digo: %s - Oficina: %s %n", entry.getKey(), entry.getValue());
////        });
//		ArrayList<Oficina> lista = new ArrayList<Oficina>(map.values());
//		for (Oficina i:lista)
//		{
//			System.out.println(i);
//		}
//	}
//	private static void ListarTreeMapClienteDNI(TreeMap<String,Cliente> map)
//	{
////		map.entrySet().forEach((entry) -> {
////            System.out.printf("- Cliente: %s %n", entry.getKey(), entry.getValue());
////        });
//		ArrayList<Cliente> lista = new ArrayList<Cliente>(map.values());
//		for (Cliente a:lista)
//		{
//			System.out.println(a);
//		}
//	}
//	
//	private static void eliminaEmpleado(Empresa empresa, Scanner lector)
//	{
//		String respuesta;
//		/*
//		 * 1- Listar empleados
//		 * 2- Buscar por clave principal
//		 * 3- Eliminar
//		 */
//		
//		String[] opcion = {""};
//		boolean claveBuena = false;
//		
//		do
//		{
//			Menus.ListarTreeMapEmpleadoDNI(empresa.getEmpleados());
//			respuesta = metodosMenu.Metodos.menu(opcion, metodos.TreeMapToArrayList.DNIsEmpleados(empresa.getEmpleados()),"Escriba el DNI del empleado a borrar", errorLetras, lector);
//			if (empresa.getEmpleados().containsKey(respuesta))	//Si alguien usa esa clave principal
//			{
//				String[] lista = {"S.- S�", "N.- No"};
//				String valor = "SN";
//				claveBuena = true;
//				
//					switch (metodosMenu.Metodos.menu(lista, valor,"El empleado se borrar� �Est� seguro?", errorLetras,  lector))
//					{
//					case "S":
//						empresa.despedirEmpleado(respuesta);
//						Serializar.grabaEmpresa(empresa); 	//Guardamos 
//						break;
//					case "N":
//						
//						break; 	//Sale sin tocar nada
//					}
//			}else {
//				System.out.println("DNI no v�lido");
//			}
//		}while(!claveBuena);
//	}
//	
//	private static void MenuEmpleado(Empresa empresa, Scanner lector) throws LicenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CarnetRequeridoInvalidoException, DNInoValidoException
//	{
//		String[] opcc = {"A.- A�adir nuevo empleado","B.- Dar de baja un empleado","S.- Salir"};
//		String Validas = "ABS";
//		boolean sale = false;
//		
//		do
//		{
//			switch (metodosMenu.Metodos.menu(opcc, Validas, "GESTOR DE EMPLEADOS", errorLetras, lector))
//			{
//			case "A","a": //A�adir empleado 
//				Empleado emple = PideDato.empleado(empresa, lector); //Creamos empleado
//				empresa.nuevoEmpleado(emple);	//A�adimos el empleado al TreeMap
//				Serializar.grabaEmpresa(empresa);
//				break;
//			case "B","b":	//Eliminar empleado
//				Menus.eliminaEmpleado(empresa, lector);
//				break;
//			case "S","s": //SALIR
//				sale = true;
//				break;
//			}
//		}while(!sale);
//	}
//	
//
//	
//	public static void listarAlquileres(TreeMap<String, Alquiler> treemap) {
//		
//		ArrayList<Alquiler> listado = new ArrayList<Alquiler>(treemap.values());
//		for (Alquiler a:listado)
//		{	//Imprimimos alquileres
//				
//			System.out.printf("C�digo: "+a.getCodigo()+" - Alquiler "+a);
//			System.out.println(""); //Salto de l�nea
//				
//			
//		}
//		
//		
//	}
//	
//	private static void creaCocheCombustion(Empresa empresa, Scanner lector) throws TipoNoValidoException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
//	{
//		
//			CocheCombustion nuevo = PideDato.cocheCombustion(empresa, lector);
//			empresa.addAlGaraje(nuevo);	//A�adimos el veh�culo
//			Serializar.grabaEmpresa(empresa);//Guardamos cambios
//		
//	}
//	
//	private static void creaCocheElectrico(Empresa empresa, Scanner lector) throws TiempoRecargaNoValidoException, TipoNoValidoException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
//	{
//		
//			CocheElectrico nuevo = PideDato.cocheElectrico(empresa, lector);
//			empresa.addAlGaraje(nuevo);	//A�adimos el veh�culo
//			Serializar.grabaEmpresa(empresa);//Guardamos cambios
//		
//	}
//	
//	private static void creaMoto(Empresa empresa, Scanner lector) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, RecargoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
//	{
//		
//			Moto nueva = PideDato.moto(empresa, lector);
//			empresa.addAlGaraje(nueva);	//A�adimos el veh�culo
//			Serializar.grabaEmpresa(empresa);//Guardamos cambios
//		
//		
//	}
//	private static void creaFurgoneta(Empresa empresa, Scanner lector) throws EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException
//	{
//		
//			Furgoneta furgo = PideDato.furgoneta(empresa, lector);
//			empresa.addAlGaraje(furgo);
//			Serializar.grabaEmpresa(empresa);//Guardamos cambios
//		
//	}
//	
//	
//		
//	
//	
//	}