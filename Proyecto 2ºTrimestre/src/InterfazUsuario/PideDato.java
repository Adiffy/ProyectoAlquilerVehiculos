package InterfazUsuario;
//
//import java.util.ArrayList;
//import java.util.GregorianCalendar;
//import java.util.Scanner;
//import java.util.TreeMap;
//
//import accesoADatos.Serializar;
//import clasesObjetos.Alquiler;
//import clasesObjetos.Categoria;
//import clasesObjetos.Cliente;
//import clasesObjetos.CocheCombustion;
//import clasesObjetos.CocheElectrico;
//import clasesObjetos.Direccion;
//import clasesObjetos.Empleado;
//import clasesObjetos.Empresa;
//import clasesObjetos.Furgoneta;
//import clasesObjetos.Matricula;
//import clasesObjetos.Moto;
//import clasesObjetos.Oficina;
//import clasesObjetos.Vehiculo;
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
//import metodos.Validadores;
//import metodosMenu.Metodos;
//
//public class PideDato {
//
//	
//	public static String menu(String[] opciones, String[] opcionesvalidas, String titulo,String mensajeError, Scanner l)
//	{
//		String opcionesval="";
//		for (int i=0;i<opcionesvalidas.length;i++) //recorremos el vector
//		{
//			opcionesval+=opcionesvalidas[i];
//		}
//		//String opcionesval=opcionesvalidas.toString(); No funciona
//		return Metodos.menu(opciones, opcionesval, titulo, mensajeError, l);
//	}
//	public static String menuProvincias(Scanner s)
//	{
//		String[] ListaProv= {"1.- �lava","2.- Albacete","3.- Alicante","4.- Almer�a","5.- Asturias", "6.- �vila", "7.- Badajoz", "8.- Barcelona", "9.- Burgos", "10.- C�ceres", "11.- C�diz", "12.- Cantabria", "13.- Castell�n", 
//			"14.- Ciudad Real", "15.- C�rdoba", "16.- Cuenca", "17.- Gerona (Girona)", "18.- Guadalajara", "19.- Guip�zcoa (Gipuzkoa)", "20.- Huelva", "21.- Huesca", "22.- Islas Baleares", "23.- Ja�n", "24.- La coru�a (A Coru�a)",
//			"25.- La Rioja", "26.- Las Palmas", "27.- Le�n", "28.- L�rida (Lleida)", "29.- Lugo", "30.- Madrid", "31.- M�laga", "32.- Murcia", "33.- Navarra", "34.- Orense (Ourense)", "35.- Palencia", "36.- Pontevedra", "37.- Salamanca",
//			"38.- Santa Cruz de Tenerife", "39.- Segovia", "40.- Sevilla", "41.- Soria","42.- Tarragona", "43.- Teruel","44.- Toledo","45.- Valencia", "46.- Valladolid", "47.- Vizcaya (Bizkaia)", "48.- Zamora", "49.- Zaragoza", "50.- Granada", 
//			"51.- Ceuta", "52.- Melilla"};
//		
//		
//		String[] ListaOpciones= {"1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31","32","33","34","35","36","37","38","39","40","41",
//				"42","43","44","45","46","47","48","49","50","51","52"};
//		
//		//comunidad=menu ("COMUNIDADES AUT�NOMAS");
//		
//		return menu(ListaProv,ListaOpciones, "MENU DE PROVINCIAS", "Seleccione el n�mero de su provincia", s);
//	
//	}
//	
//	/**
//	 * M�todo que pide un n�mero entero al usuario mediante consola. Necesita:
//	 * @param mensaje Un mensaje de tipo {@code String} que ser� la petici�n al usuario. Ejemplo: "Escriba su edad"
//	 * @param mensajeError	Otro mensaje de tipo {@code String} que aparecer� cuando el usuario escriba un dato incorrecto
//	 * @param limiteBajo	El l�mite por debajo del dato que se debe introducir, tipo {@code int}.
//	 * Sirve para delimitar el m�nimo admitido.
//	 * @param limite	El {@code int} que indica el m�ximo valor permitido del dato introducido por el usuario. 
//	 * @param l	 El Scanner que se utilizar� para recibir el dato que escriba el usuario.
//	 * @return	El dato validado que ha escrito el usuario
//	 */
//	public static int numerico( String mensaje,String mensajeError,int limiteBajo, int limite,Scanner l) {
//		int num = -1;
//		boolean DatoMalo = true;
////		l.next(); //Limpiamos el Scanner
//		do {
//			System.out.println(mensaje);
//			num = l.nextInt();
//			if (num <= limite && num >= limiteBajo)
//			{
//				DatoMalo = false;
//			}else {
//				System.out.println(mensajeError);
//			}
//			l.nextLine();
//		}while (DatoMalo);
//		
//		return num;
//	}
//	
//	public static int numerico( String mensaje,Scanner l) {
//		return PideDato.numerico(mensaje,"Debe ser un n�mero entero. Como lo son: 1,2,3", 0, 100, l);
//	}
//
//	public static int numerico(Scanner l) {
//		return PideDato.numerico("Introduzca un dato real", l);
//	}
//	
//	/**
//	 * Pide dato num�rico de tipo {@code int} (entero) 
//	 * Este constructor se caracter�stica de pedir el dato sin necesidad de nada
//	 * No requiere de ning�n mensaje ni {@code Scanner}.
//	 * No cuenta con ning�n mensaje de petici�n as� que �ste debe realizarse antes de llamar a este m�todo.
//	 * 
//	 * Ejemplo de uso:
//	 * 		sysout("Cu�ntos a�os tiene?")
//	 * 			int num = PideDato.numerico();
//	 * 
//	 * @return	 El n�mero entero escrito por el usuario
//	 */
//	public static int numerico() {
//		Scanner l = new Scanner(System.in);
//		return PideDato.numerico("", l);
//	}
//	
//	public static double real(String mensaje, int limiteBajo,int limite,Scanner l) {
//		double num = 0.0;
//		boolean DatoMalo = true;
////		l.nextLine(); //Limpiamos el Buffer
//		do {
//			System.out.println(mensaje);
//			num = l.nextDouble();
//			l.nextLine();
//			if (num>limiteBajo && num<limite)
//			{
//				DatoMalo = false;	//el dato es v�lido
//			}
//		}while (DatoMalo);
//		
//		return num;
//	}
//	
//	/**
//	 * El n�mero estar� entre 0 y 100
//	 * @param mensaje	El {@code String} que se imprime por pantalla antes que el usuario escriba
//	 * @param l	El {@code Scanner}
//	 * @return	El {@code double} que escribe el usuario 
//	 */
//	public static double real(String mensaje, Scanner l)
//	{
//		return PideDato.real(mensaje,0,100 ,l);
//	}
//	
//	/**
//	 * Pide un dato de tipo {@code String} 
//	 * Necesita: 
//	 * @param mensaje El mensaje de petici�n del dato
//	 * @param mensajeError	El mensaje que se muestra cuando el usuario introduce un valor no-v�lido
//	 * @param minLong	El {@code int} que determina el m�nimo de caracteres que puede introducir el usuario
//	 * @param maxLong	El {@code int} que ser� el m�ximo de caracteres que puede escribir el usuario sin que d� error
//	 * @param l	El {@code Scanner} que se utiliza para recibir el dato
//	 * @return	 Devuelve el dato introducido por el usuario
//	 */
//	public static String cadena(String mensaje, String mensajeError, int minLong, int maxLong,Scanner l)
//	{
//		String resultado;
//		boolean DatoMalo = true;
////		l.next(); //Limpiamos el Buffer
//		do {
//			System.out.println(mensaje);
//			resultado = l.nextLine();
//			if (resultado.length() >= minLong && resultado.length()<=maxLong)
//			{
//				DatoMalo=false;
//			}else {
//				System.out.println(mensajeError);
//			}
//		}while (DatoMalo);
//		
//		return resultado;
//	}
//	
//	/**
//	 * Pide dato de Cadena ({@code string}).
//	 * La longitud m�nima del dato introducido por el usuario ser� 0 y la m�xima ser� 25 (en caracteres).
//	 * @param mensaje	El mensaje que viene antes de la petici�n del dato de forma aclaratoria. Ejemplo: "Introduce tu nombre:"
//	 * @param l	El Scanner para leer el dato introducido por el usuario
//	 * @return	El dato escrito por el usuario
//	 */
//	public static String cadena(String mensaje, Scanner l)
//	{
//		String error = "Longitud no v�lida / Tipo de dato no v�lido";
//		return PideDato.cadena(mensaje, error, 0, 25, l);
//	}
//	
//	/**
//	 * Pide dato cadena ({@code String}) que no pide nada al usuario
//	 * El mensaje de petici�n del dato deber� ir antes
//	 * @param l	{@code Scanner} que se usa para pedir el dato
//	 * @return	el dato de tipo {@code String} escrito por el usuario
//	 */
//	public static String cadena(Scanner l)
//	{
//		return PideDato.cadena("", l);
//	}
//	
//	/**
//	 * Pide una matr�cula al usuario
//	 * @param l El {@code Scanner} utilizado para la petici�n de datos
//	 * @return	Un objeto de tipo {@code Matricula}
//	 * @throws NumeroMatriculaNoValidoException El conjunto num�rico que compone la matr�cula no puede ser de otro tipo que no sea {@code int}
//	 * @throws LetrasMatriculaNoValidasException El conjunto de letras que componen a su vez la matr�cula deben ser de tipo {@code String}
//	 */
//	public static Matricula matricula(Scanner l) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException 
//	{
////		l.next(); //Limpiamos el buffer por si acaso
//		System.out.println("Escribe:");
//		System.out.print("1.- Los n�meros de la matr�cula:");
//		int numeros= PideDato.numerico("", "S�lo datos num�ricos admitidos", 0, 99999999, l);
//		System.out.print("2.- Las letras de la matr�cula:");
//		String letras = PideDato.cadena(l);
//		
//		Matricula a;
//		
//			a = new Matricula(numeros, letras);
//			return a;
//		
//	}
//	
//	/**
//	 * Pide un dato de tipo GregorianCalendar
//	 * Pide una fecha paso a paso (d�a,mes y a�o) y con esos datos
//	 * compone un objeto tipo {@code GregorianCalendar} y lo devuelve
//	 * @param mensaje	El {@code String} que leer� el usuario antes de escribir los datos
//	 * Por ejemplo: "Fecha de cumplea�os" 
//	 * @param l El {@code Scanner} con el que se leer�n los datos introducidos por el usuario
//	 * @return	El {@code GregorianCalendar} escrito por el usuario
//	 */
//	public static GregorianCalendar fecha(String mensaje, Scanner l)
//	{
//		System.out.println(mensaje);
////		l.next(); //Limpiamos el Scanner para evitar errores
//		System.out.print("D�a:");
//		int dia = l.nextInt();
//		l.nextLine();
//		System.out.println("Mes:");
//		int mes = l.nextInt();
//		l.nextLine();
//		System.out.println("A�o:");
//		int anno = l.nextInt();
//		l.nextLine();
//		return  new GregorianCalendar(anno,mes,dia);	//Creamos el GregorianCalendar con los datos introducidos
//	}
//	public static Categoria nuevaCategoria(Empresa empresa, Scanner l) throws RecargoNoValidoException
//	{
//		String titulo = "Nueva Categor�a";
//		metodosMenu.Metodos.pintaSubrayado(titulo.toUpperCase());
//			String cod = PideDato.cadena("C�digo",l);
//			String desc = PideDato.cadena("Descripci�n","La descripci�n debe ser breve",0,30,l);
//			double recargo = PideDato.real("Porcentaje de recargo", l);
//			return new Categoria(cod,desc,recargo);
//		
//	}
//	
//	public static Categoria categoria(Empresa empresa, Scanner l) throws RecargoNoValidoException
//	{
//		String titulo = "Categor�a";
//		metodosMenu.Metodos.pintaSubrayado(titulo.toUpperCase());
//		String[] opciones = {"A.- Elegir una categor�a existente","B.- Nueva categor�a"};
//		String elegible = "AB";
//		switch(Metodos.menu(opciones, elegible,"�Crear categor�a o elegir una existente?", titulo, l))
//		{
//		case "A","a":
//			ArrayList<Categoria> categorias = new ArrayList<Categoria>(empresa.getCategorias().values());	//Pasamos el TreeMap a ArrayList
//			Categoria b = null;
//			boolean DatoMalo = true;
//			String elige;
//		do
//		{
//			for (Categoria a:categorias)	//Listamos las categor�as recorriendo el ArrayList
//			{
//				System.out.println(a);
//			}
//			
//			elige = PideDato.cadena("C�digo de categor�a", l);	//El usuario escribe el c�digo
//			if (empresa.getCategorias().containsKey(elige))	//Si el c�digo existe DatoMalo = false (rompe el bucle)
//			{
//				DatoMalo = false;
//				b = empresa.getCategorias().get(elige);	//Seleccionamos la categor�a que buscamos
//			}
//		}while(DatoMalo);
//			return b;	//Devolvemos la categor�a
//			
//		case "B","b":
//			return PideDato.nuevaCategoria(empresa, l);
//			
//		default:	//imposible que pase esto
//			return null;
//			
//		}
//		
//		
//		
//	}
//	
//	public static GregorianCalendar fecha(Scanner l)
//	{
//		return PideDato.fecha("", l);
//	}
//	
//	
//	public static Direccion direccion(Scanner l) throws LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException 
//	{
////		l.next(); //Eliminamos un posible Enter
//		
//		boolean DatoBueno = false; //A priori es falso
//		String nombreVia;
//		String numVia;
////		String planta;	No tendremos en cuenta la planta
//		String codigoPostal;
////		String localidad;	Construiremos su direcci�n sin localidad para no pedirla 2 veces
//		
//		do {
//			System.out.println("Direcci�n:");
//			System.out.println("Tipo de v�a y nombre de la misma:"+"          Por ejemplo: Calle Francisco Soriano");
//			nombreVia = PideDato.cadena(l);
//			
//			System.out.println("N�mero de v�a:");
//			numVia = PideDato.cadena(l);
////			System.out.println("Planta");
////			planta = PideDato.cadena(l);
//			System.out.println("C�digo Postal:");
//			codigoPostal = PideDato.cadena("", "C�digo no v�lido", 5, 5, l); //Un CP tiene 5 d�gitos
////			System.out.println("Nombre de localidad:");
////			localidad = PideDato.cadena(l);																	&&(planta.length()<4)
//			if((nombreVia.length()<=25 && nombreVia.length()>=2)&&(numVia.length()>=1 && numVia.length()<=25)&&(codigoPostal.length()==5))// && (localidad.length()>3 && localidad.length()<25)) {
//			{
//				DatoBueno = true;
//			}
//		}while (!DatoBueno);
//		Direccion home = new Direccion(nombreVia,numVia,codigoPostal); //Al crear una nueva direccion podemos lanzar dos Exceptions
//		return home;
//	}
//	public static Oficina oficina(Scanner l) throws LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException
//	{
////		l.next(); //Limpieamos el buffer
//		String codigo; //(es un alfanum�rico de 4 letras : JA01, MA10�)
//		String Descripcion; //(�Ja�n estaci�n de trenes�, �M�laga principal�
//		String Localidad;
//		String Provincia;
//		boolean deAeropuerto = false; //Si no es false
////		Direccion ubi;
//		
//		Metodos.pintaSubrayado("Nueva oficina");
//		
////		ubi = PideDato.direccion(l);
//		do 
//		{
//			codigo = PideDato.cadena("C�digo | Con estructura: JA01", l);
//		}while (!Validadores.codigoValidado(codigo)); //Pedir� el codigo mientras sea inv�lido
//		Descripcion = PideDato.cadena("Descripci�n de la oficina","Tiene un l�mite de 40 caracteres",0,40,l);
//		//Provincia y localidad
//		Provincia = menuProvincias(l);
//		Localidad = PideDato.cadena("Localidad", l);
//		
//		String[] opc = {"S.- Si", "N.- No"};
//		String respuesta = "SN";
//		switch (metodosMenu.Metodos.menu(opc, respuesta, "�Se trata de una oficina de aeropuerto?", "Elija una opci�n escribiendo S o N", l))
//		{
//		case "S","s":
//			deAeropuerto = true;
//		case "N","n":
//			deAeropuerto = false;
//		}
//		TreeMap<String,Empleado>personal = new TreeMap<String, Empleado>();
//		return new Oficina(codigo, Descripcion, personal, Localidad,Provincia, deAeropuerto);
//	}
//	public static Empleado empleado(Empresa empresa,Scanner l) throws LicenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, CarnetRequeridoInvalidoException, DNInoValidoException
//	{
//		GregorianCalendar fechaAlta;
////		Oficina trabajo;	//Oficina en la que trabaja
//		
//		String nombre;
//		String ap1;
//		String ap2;
//		String dni;
//		String elegida;
//		boolean bien = false;
//		
//		Metodos.pintaSubrayado("Nuevo empleado");
//		
//		dni = PideDato.cadena("DNI asociado:", l);
//		nombre = PideDato.cadena("Nombre:", l);
//		ap1 = PideDato.cadena("Primer apellido:",l);
//		ap2 = PideDato.cadena("Segundo apellido:", l);
//		
//		do {
//			Menus.ListadoOficinas(empresa, l);	//Listamos las oficinas
//			elegida = l.nextLine();
//			if (empresa.getOficinas().containsKey(elegida))		//Cuando elige una clave v�lida
//			{
//				
//				bien = true;
//			}
//			
//		}while (!bien);
//		
//		
////			trabajo = empresa.getOficinas().get(elegida);//Metemos la oficina correspondiente
//		fechaAlta = PideDato.fecha("Fecha de alta del trabajador:", l);
//		
//		Empleado nuevo = new Empleado(nombre, ap1, ap2, dni,fechaAlta, empresa.getOficinas().get(elegida));
//		empresa.getOficinas().get(elegida).addEmpleado(nuevo);
//		Serializar.grabaEmpresa(empresa);
//		return nuevo;
//	}
//	public static Cliente cliente(Scanner l) throws LicenciaNoValidaException, LongitudCadenaNoValidaException, DNInoValidoException
//	{
//		String licencia;
//		int tarjeta;	//N� de tarjeta de cliente habitual
//		
//		String nombre;
//		String ap1;
//		String ap2;
//		String dni;
//		
//		Metodos.pintaSubrayado("Nuevo Cliente");
//		
//		dni = PideDato.cadena("DNI asociado:", l);
//		nombre = PideDato.cadena("Nombre:", l);
//		ap1 = PideDato.cadena("Primer apellido:",l);
//		ap2 = PideDato.cadena("Segundo apellido:", l);
//		
//		licencia = PideDato.cadena("Licencia",l);
//		tarjeta = PideDato.numerico("N� de tarjeta de cliente habitual", l);
//		
//		return new Cliente(nombre, ap1, ap2, dni, licencia, tarjeta);
//	}
//	
//	public static CocheCombustion cocheCombustion(Empresa empresa,Scanner l) throws RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TipoNoValidoException 
//	{
//		Oficina oficina;		
////		l.next(); //Limpiamos el Scanner
//		System.out.println("Matr�cula del coche");
//		Matricula mat = null;
//		
//			mat = PideDato.matricula(l);
//		
//		System.out.println("Marca del coche");
//		String marca = l.nextLine();	
//		String model = PideDato.cadena("Modelo del coche", "Longitud no v�lida", 0, 25, l);
//		Categoria cat = null;
//		String elegida;
//		boolean bien=false;
//		
//			cat = PideDato.categoria(empresa, l);
//		
//		String color = PideDato.cadena("Color del coche", l);
//		GregorianCalendar fechaAlta = PideDato.fecha("Fecha de alta del veh�culo", l);
////		boolean alquilado = true;	//A priori est� alquilado as� no habr� que asignarle una oficina a�n
////		String[] opc = {"S.- Si", "N.- No"};
////		String respuesta = "SN";
////		switch (metodosMenu.Metodos.menu(opc, respuesta, "�Se encuentra alquilado ahora mismo?", "Elija una opci�n escribiendo S o N", l))
////		{
////		case "S","s":
////			alquilado = true;
////		case "N","n":
////			alquilado = false;
////		}
//		int kms = PideDato.numerico("�Cu�ntos kil�metros tiene?",l);
//		int plazas = PideDato.numerico("�Cu�ntas plazas tiene?",l);
//		double consumo = PideDato.real("�Cu�nto consumo (Litros por cada 100 kil�metros) tiene?", l);
//		String tipoCoche = PideDato.cadena("Tipo de coche: Familiar, deportivo o 4x4", l);
//		int potencia = PideDato.numerico("Potencia del coche (en CV)", l);
//		String emisiones = PideDato.cadena("Emisiones (A,B o C)", l);
////		
////		if(alquilado)
////		{
////			oficina = null;
////		}else {
//		do {
//			Menus.ListadoOficinas(empresa, l);	//Listamos las oficinas
//			elegida = l.nextLine();
//			if (empresa.getOficinas().containsKey(elegida))		//Cuando elige una clave v�lida
//			{
//				bien = true;
//				
//			}
//			
//		}while (!bien);
//		
//		
//			oficina = empresa.getOficinas().get(elegida);//Metemos la oficina correspondiente;
//		//}
//		
//		return new CocheCombustion(mat, marca, model, cat, color, fechaAlta, oficina, kms, plazas, consumo, tipoCoche, potencia, emisiones);
//		
//		
//	}
//	public static Alquiler alquiler(Empresa empresa, Scanner l) throws FechaNoValidaException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, CodigoNoValidoException
//	{
//		GregorianCalendar inicio;
//		GregorianCalendar fin;
//		String cod;
//		boolean sale = false;
//		
//		Metodos.pintaSubrayado("Datos de alquiler");
//	
//		cod = PideDato.cadena("C�digo del alquiler", l);
//		
//		inicio = PideDato.fecha("Fecha inicio", l);
//		fin = PideDato.fecha("Fecha final prevista", l);
//		
//		Vehiculo auto = null;
//		boolean seVa = false;
//		String contesta;
//		Oficina ofi = null;
//		
//		do		//Primero pedimos las oficinas y luego listamos los veh�culos en esas oficinas
//		{
//			//Listamos las oficinas
//			Menus.ListadoOficinas(empresa, l);
//			contesta = PideDato.cadena("Oficina donde se recoger� el veh�culo", l);
//			if (empresa.getOficinas().get(contesta) != null)
//			{
//				ofi = empresa.getOficinas().get(contesta);
//				seVa = true;
//			}
//		}while (!seVa);
//		
//		do
//		{
//			//Pasamos el TreeMap a un ArrayList
//			ArrayList<Vehiculo> lista = new ArrayList<Vehiculo>(empresa.getGaraje().values());
//			//Listamos los vehiculos disponibles
//			for (Vehiculo a:lista)
//			{
//				if (!a.isAlquilado()) 	//Si NO est� alquilado
//				{
//					if (a.getOficina()==empresa.getOficinas().get(contesta))	//Si esta en la oficina elegida
//					{
//						System.out.println(a);
//					}		
//				}
//			}
//			System.out.println("Matr�cula del veh�culo a alquilar");
//			Matricula vehiculo = PideDato.matricula( l);
//			if (empresa.getGaraje().get(vehiculo) != null)
//			{
//				auto = empresa.getGaraje().get(vehiculo);
//				
//				sale = true;
//			}
//		}while(!sale);
//		
//		//Oficina donde se devuelve
//		boolean adios = false;
//		Oficina ofi2 = null;
//		do
//		{
//			//Listamos las oficinas 
//			Menus.ListadoOficinas(empresa, l);
//			contesta = PideDato.cadena("Oficina donde se devolver� el veh�culo", l);	//Reutilizamos la variable contesta
//			if (empresa.getOficinas().get(contesta) != null)
//			{
//				ofi2 = empresa.getOficinas().get(contesta);
//				adios = true;
//			}
//		}while(!adios);
//		
////		System.out.println("Empleado:");
////		Menus.listadoPersonas(empresa, l);
//
//		
//		return new Alquiler(auto,cod, inicio,fin,ofi,ofi2);
//	}
//	
//	public static CocheElectrico cocheElectrico(Empresa empresa,Scanner l) throws RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TiempoRecargaNoValidoException, TipoNoValidoException 
//	{
//		Oficina oficina;		
////		l.nextLine(); //Limpiamos el Scanner
//		System.out.println("Matr�cula del coche");
//		Matricula mat = null;
//		int autonomia; //(kms)
//		int TiempoRecarga; //(min)
//		int numPlazas;
//		String elegida;
//		
//			mat = PideDato.matricula(l);
//		
//		System.out.println("Marca del coche");
//		String marca = l.nextLine();	
//		String model = PideDato.cadena("Modelo del coche", "Longitud no v�lida", 0, 25, l);
//		Categoria cat = null;
//		boolean bien = false; 
//		
//			cat = PideDato.categoria(empresa, l);
//		
//		String color = PideDato.cadena("Color del coche", l);
//		GregorianCalendar fechaAlta = PideDato.fecha("Fecha de alta del veh�culo", l);
//		
////		String[] opc = {"S.- Si", "N.- No"};
////		String respuesta = "SN";
////		switch (metodosMenu.Metodos.menu(opc, respuesta, "�Se encuentra alquilado ahora mismo?", "Elija una opci�n escribiendo S o N", l))
////		{
////		case "S","s":
////			alquilado = true;
////		case "N","n":
////			alquilado = false;
////		}
//		int kms = PideDato.numerico("�Cu�ntos kil�metros tiene?",l);
//		numPlazas = PideDato.numerico("�Cu�ntas plazas tiene?",l);
//		
//		String tipoCoche = PideDato.cadena("Tipo de coche: Familiar, deportivo o 4x4", l);
//		
//		autonomia = PideDato.numerico("Autonom�a en kil�metros:", l);
//		TiempoRecarga = PideDato.numerico("Tiempo de recarga promedio (en minutos):", l);
//		
//		
////		if(alquilado)
////		{
////			oficina = null;
////		}else 
//		do {
//			Menus.ListadoOficinas(empresa, l);	//Listamos las oficinas
//			elegida = l.nextLine();
//			if (empresa.getOficinas().containsKey(elegida))		//Cuando elige una clave v�lida
//			{
//				bien = true;
//				
//			}
//			
//		}while (!bien);
//		
//		
//			oficina = empresa.getOficinas().get(elegida);//Metemos la oficina correspondiente
//		//}
//		
//		return new CocheElectrico(mat, marca, model, cat, color, fechaAlta, oficina, kms, autonomia, TiempoRecarga, tipoCoche, numPlazas);
//		
//		
//	}
//	
//	public static Moto moto(Empresa empresa, Scanner l) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, RecargoNoValidoException, EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, CilindradaNoValidaException, CarnetRequeridoInvalidoException
//	{
//		int cilindrada;
//		String carnet; //Carnet requerido (AM/A1/A2)
////		l.next(); //Limpiamos el Scanner
//		
//		Metodos.pintaSubrayado("Nueva Moto");
//		
//		System.out.println("Matr�cula de la moto");
//		Matricula mat = null;
//		
//			mat = PideDato.matricula(l);
//		
//		System.out.println("Marca de la moto");
//		String marca = l.nextLine();	
//		String model = PideDato.cadena("Modelo de la moto", "Longitud no v�lida", 0, 25, l);
//		Categoria cat = null;
//		
//			cat = PideDato.categoria(empresa, l);
//		
//		String color = PideDato.cadena("Color:", l);
//		GregorianCalendar fechaAlta = PideDato.fecha("Fecha de alta del veh�culo", l);
//
//		int kms = PideDato.numerico("�Cu�ntos kil�metros tiene?",l);
//		
//		double consumo = PideDato.real("�Cu�nto consumo (Litros por cada 100 kil�metros) tiene?", l);
//		
//		int potencia = PideDato.numerico("Potencia del veh�culo (en CV)", l);
//		String emisiones = PideDato.cadena("Emisiones (A,B o C)", l);
//		
//		cilindrada = PideDato.numerico("Cilindrada de la moto", l);
//		
//		do {
//			System.out.println("Carnet requerido:");
//			carnet = l.nextLine();
//			//Lo repetiremos mientras sea distinto de AM, A1 o A2
//		}while (carnet.compareToIgnoreCase("AM")!=0 || carnet.compareToIgnoreCase("A1")==0 || carnet.compareToIgnoreCase("A2")==0 );
//		
//		
//			return new Moto(mat, marca, model, cat, color, fechaAlta, null, kms, consumo, potencia, emisiones, cilindrada, carnet);
//	}
//	
//	public static Furgoneta furgoneta(Empresa empresa, Scanner l) throws EmisionesNoValidasException, ConsumoNoValidoException, PotenciaNoValidaException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException 
//	{
///*
// * * @param matricula	Objeto tipo {@code Matricula} 
//	 * @param marca	Marca de la furgoneta, por ejemplo "skoda" en forma de {@code String}
//	 * @param modelo	Modelo de la furgoneta, tipo {@code String}
//	 * @param categoria	Categor�a de la furgoneta, tipo {@code Categoria}
//	 * @param color	Color de la furgoneta, por ejemplo "blanco". Tipo {@code String}
//	 * @param fecha_alta	Fecha de alta del veh�culo, del tipo {@code GregorianCalendar}
//	 * @param oficina	{@code Direccion} que indica donde se encuentra la furgoneta (si no est� alquilada)
//	 * @param num_km	Los kil�metros que tiene el veh�culo en forma de {@code int}
//	 * @param consumo	El consumo (Litros por cada 100km) de la furgoneta, es un {@code double}
//	 * @param caballaje	La potencia medida en {@code int} (medida en CV)
//	 * @param emisiones	El tipo de emisiones ({@code String} Puede ser A, B o C
//	 * @param capacidadCarga	El {@code int} 
//	 * @param carnetRequerido
// */
////		l.next(); //Limpiamos el Scanner
//		Metodos.pintaSubrayado("Nueva furgoneta");
//		System.out.println("Matr�cula de la furgoneta");
//		Matricula mat = null;
//		
//			mat = PideDato.matricula(l);
//		
//		System.out.println("Marca:");
//		String marca = l.nextLine();	
//		String model = PideDato.cadena("Modelo de la furgoneta", "Longitud no v�lida", 0, 25, l);
//		Categoria cat = null;
//		
//			cat = PideDato.categoria(empresa, l);
//		
//		String color = PideDato.cadena("Color:", l);
//		GregorianCalendar fechaAlta = PideDato.fecha("Fecha de alta del veh�culo", l);
////		boolean alquilado = true;	//A priori est� alquilado as� no habr� que asignarle una oficina a�n
////		String[] opc = {"S.- Si", "N.- No"};
////		String respuesta = "SN";
////		switch (metodosMenu.Metodos.menu(opc, respuesta, "�Se encuentra alquilado ahora mismo?", "Elija una opci�n escribiendo S o N", l))
////		{
////		case "S","s":
////			alquilado = true;
////		case "N","n":
////			alquilado = false;
////		}
//		int kms = PideDato.numerico("�Cu�ntos kil�metros tiene?",l);
//		
//		double consumo = PideDato.real("�Cu�nto consumo (Litros por cada 100 kil�metros) tiene?", l);
//		
//		int potencia = PideDato.numerico("Potencia de la furgoneta (en CV)", l);
//		String emisiones = PideDato.cadena("Emisiones (A,B o C)", l);
//		
//		int capacidad = PideDato.numerico("Capacidad de carga (m�):",l)	;
//		System.out.println("Carnet requerido:");
//		String carnet = l.nextLine();
//		
//			return new Furgoneta(mat, marca, model, cat, color, fechaAlta, null, kms, consumo, potencia, emisiones, capacidad, carnet);
//		
//		}
//		
//	}
//	
//
