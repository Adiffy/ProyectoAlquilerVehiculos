package accesoADatos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import clasesObjetos.Categoria;
import clasesObjetos.CocheCombustion;
import clasesObjetos.CocheElectrico;
import clasesObjetos.DeCombustion;
import clasesObjetos.Electrico;
import clasesObjetos.Furgoneta;
import clasesObjetos.Matricula;
import clasesObjetos.Moto;
import clasesObjetos.Oficina;
import clasesObjetos.Vehiculo;
import exceptions.CarnetRequeridoInvalidoException;
import exceptions.CilindradaNoValidaException;
import exceptions.ConsumoNoValidoException;
import exceptions.EmisionesNoValidasException;
import exceptions.LetrasMatriculaNoValidasException;
import exceptions.NumPlazasNoValidoException;
import exceptions.NumeroMatriculaNoValidoException;
import exceptions.PotenciaNoValidaException;
import exceptions.RecargoNoValidoException;
import exceptions.TiempoRecargaNoValidoException;
import exceptions.TipoNoValidoException;
import objetosBD.CombustionBD;
import objetosBD.DeCombustionDB;
import objetosBD.ElectricoBD;
import objetosBD.FurgonetaBD;
import objetosBD.MotoBD;
import objetosBD.VehiculoDB;

public class RepositorioVehiculo {

	/**
	 * Obtiene las matriculas de la tabla VEHICULO y se las pasa al Método leeVehiculo mediante un bucle
	 * @return un ArrayList<VehiculoDB>
	 * @throws LetrasMatriculaNoValidasException
	 * @throws NumeroMatriculaNoValidoException
	 * @throws RecargoNoValidoException
	 */
	public static ArrayList<VehiculoDB> leeVehiculos() throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, RecargoNoValidoException
	{
		ArrayList<VehiculoDB> lista = new ArrayList<VehiculoDB>();
		String sql = "SELECT matricula FROM VEHICULO";
		PreparedStatement st;
		ResultSet rs;
		String matricula;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next())
			{
				matricula = rs.getString("matricula");
				lista.add(leeVehiculo(leeMatricula(matricula)));

			}

			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException e) {
			// Mostramos la traza
			e.printStackTrace();
		}
		return lista;
	}

	public static ArrayList<? extends Vehiculo> leeTodosLosVehiculos() throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, RecargoNoValidoException
	{
		ArrayList<Vehiculo> lista2 = new ArrayList<Vehiculo>();
		String sql = "SELECT matricula FROM VEHICULO";
		PreparedStatement st;
		ResultSet rs;
		String matricula;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next())
			{
				matricula = rs.getString("matricula");
				if (Electrico(matricula))
				{
					if (cocheElectrico(matricula))
					{
						lista2.add(leeCocheElectrico(leeMatricula(matricula)));
					}else {
						lista2.add(leeMotocicleta(leeMatricula(matricula)));
					}
				}else {
					if (CocheCombustion(matricula))
					{
						lista2.add(leeCocheComb(leeMatricula(matricula)));
					}else {
						lista2.add(leeFurgoneta(leeMatricula(matricula)));
					}
				}
			}			
			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException | EmisionesNoValidasException | NumPlazasNoValidoException | ConsumoNoValidoException | PotenciaNoValidaException | TipoNoValidoException e) {
			// Mostramos la traza
			e.printStackTrace();
		}
		return lista2;
	}
	public static ArrayList<? extends Vehiculo> leeTodosLosVehiculos(Oficina ofi) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, RecargoNoValidoException
	{
		ArrayList<Vehiculo> lista2 = new ArrayList<Vehiculo>();
		String sql = "SELECT matricula FROM VEHICULO WHERE oficina = ?";
		PreparedStatement st;
		ResultSet rs;
		String matricula;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, ofi.getCódigo());
			rs = st.executeQuery();
			while (rs.next())
			{
				matricula = rs.getString("matricula");
				if (Electrico(matricula))
				{
					if (cocheElectrico(matricula))
					{
						lista2.add(leeCocheElectrico(leeMatricula(matricula)));
					}else {
						lista2.add(leeMotocicleta(leeMatricula(matricula)));
					}
				}else {
					if (CocheCombustion(matricula))
					{
						lista2.add(leeCocheComb(leeMatricula(matricula)));
					}else {
						lista2.add(leeFurgoneta(leeMatricula(matricula)));
					}
				}
			}			
			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException | EmisionesNoValidasException | NumPlazasNoValidoException | ConsumoNoValidoException | PotenciaNoValidaException | TipoNoValidoException e) {
			// Mostramos la traza
			e.printStackTrace();
		}
		return lista2;
	}

	public static boolean CocheCombustion(String matricula) {
		// Miramos si la matrícula se encuentra en la tabla de cocheCombustión
		boolean encontrado;
		String sql = "SELECT * FROM CocheCombustion WHERE combustionMatricula = ?";
		PreparedStatement st;
		ResultSet rs;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1,matricula);
			rs = st.executeQuery(sql);
			if (rs.next())
			{
				encontrado = true;
			}else {
				encontrado = false;
			}

			//cerramos la conexión
			st.close();
		} catch (SQLException e) {
			encontrado = false;
		}
		return encontrado;
	}

	public static boolean Electrico(String matricula) {
		// Miramos si la matrícula se encuentra en la tabla de Electrico
		boolean encontrado;
		String sql = "SELECT * FROM ELECTRICO WHERE VEHICULO_MATRICULA = ?";
		PreparedStatement st;
		ResultSet rs;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, matricula);
			rs = st.executeQuery(sql);
			if (rs.next())
			{
				encontrado = true;
			}else {
				encontrado = false;
			}
			//cerramos la conexión
			st.close();
		} catch (SQLException e) {
			encontrado = false;
		}
		return encontrado;
	}

	public static boolean cocheElectrico(String matricula) {
		// Miramos si la matrícula se encuentra en la tabla de coche Electrico
		boolean encontrado;
		String sql = "SELECT * FROM COCHEELECTRICO WHERE VEHICULO_MATRICULA = ?";
		PreparedStatement st;
		ResultSet rs;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, matricula);
			rs = st.executeQuery(sql);
			if (rs.next())
			{
				encontrado = true;
			}else {
				encontrado = false;
			}
			//cerramos la conexión
			st.close();
		} catch (SQLException e) {
			encontrado = false;
		}
		return encontrado;
	}

	public static void insertUpdate(Vehiculo a)
	{
		try {
			switch (RepositorioVehiculo.tipoVehiculo(a))
			{
			case "Moto":
				if (RepositorioVehiculo.leeMoto(a.getMatricula())!=null)
				{	//Si ya existe realizaremos un Update

				}else  {	//Insertamos
					insertMoto(a);
				}
				break;
			case "Furgoneta":
				if (RepositorioVehiculo.leeFurgo(a.getMatricula())!=null)
				{	//Si ya existe realizaremos un Update

				}else  {	//Insertamos
					insertFurgo(a);
				}
				break;
			case "CocheCombustion":
				if (RepositorioVehiculo.leeCocheComb(a.getMatricula())!=null)
				{	//Si ya existe realizaremos un Update

				}else  {	//Insertamos
					insertCocheComb(a);
				}
				break;
			case "CocheElectrico":
				if (RepositorioVehiculo.leeCocheElectrico(a.getMatricula())!=null)
				{	//Si ya existe realizaremos un Update

				}else  {	//Insertamos
					insertCocheElectrico(a);
				}
				break;
			}
		} catch (EmisionesNoValidasException | NumPlazasNoValidoException | ConsumoNoValidoException
				| PotenciaNoValidaException | TipoNoValidoException | RecargoNoValidoException
				| LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			// Imprimimos la traza del error
			e.printStackTrace();
		}
	}

	private static void insertCocheElectrico(Vehiculo a) {
		// Insertamos el coche en la base de datos
		String sql = "INSERT INTO CocheElectrico VALUES (?,?,?)";
		PreparedStatement st;

		try {
			//Ejecutamos la primera instrucción (VEHICULO)
			insertVehiculo(a);
			//Ejecutamos la segunda instrucción (ELECTRICO)
			insertElectrico((Electrico)a);
			//Ejecutamos la tercera instruccion (CocheElectrico)
			st = EmpresaDB.conn.prepareStatement(sql);
			CocheCombustion b = (CocheCombustion) a ;
			st.setInt(1, b.getNumPlazas());
			st.setString(2, b.getTipo());
			st.setString(3, b.getMatricula().toString());
			st.execute();
			st.execute("COMMIT");

			//Cerramos la conexión
			st.close();
		} catch (SQLException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			// Imprimimos el error en la consola
			e.printStackTrace();
		}
	}

	private static void insertElectrico(Electrico a) {
		String sql = "INSERT INTO electrico VALUES (?,?,?)";
		PreparedStatement st;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setInt(1, a.getAutonomia());
			st.setInt(2, a.getTiempoRecarga());
			st.setString(3, a.getMatricula().toString());
		} catch (SQLException e) {
			// error
			e.printStackTrace();
		} catch (LetrasMatriculaNoValidasException e) {
			// error
			e.printStackTrace();
		} catch (NumeroMatriculaNoValidoException e) {
			// error
			e.printStackTrace();
		}
	}

	private static void insertCocheComb(Vehiculo a) {
		// Insertamos el coche en la base de datos
		String sql = "INSERT INTO CocheCombustion VALUES (?,?,?)";
		PreparedStatement st;

		try {
			//Ejecutamos la primera instrucción (VEHICULO)
			insertVehiculo(a);

			//Ejecutamos la segunda instrucción (DE COMBUSTION)
			insertDeCombustion(a);
			st = EmpresaDB.conn.prepareStatement(sql);
			CocheCombustion b = (CocheCombustion) a ;
			st.setInt(1, b.getNumPlazas());
			st.setString(2, b.getTipo());
			st.setString(3, b.getMatricula().toString());
			st.execute();
			st.execute("COMMIT");

			//Cerramos la conexión
			st.close();
		} catch (SQLException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			// Imprimimos el error en la consola
			e.printStackTrace();
		}
	}

	private static void insertDeCombustion(Vehiculo a) throws SQLException {
		String sql = "INSERT INTO deCombustion VALUES (?,?,?,?)";
		PreparedStatement st;
		CocheCombustion b = (CocheCombustion) a; 

		st= EmpresaDB.conn.prepareStatement(sql);
		st.setDouble(1, b.getConsumo());
		st.setInt(2, b.getPotencia());
		st.setString(3, ((DeCombustion) b).getEmisiones());
		try {
			st.setString(4, b.getMatricula().toString());
		} catch (SQLException e) {
			// Imprimimos el error
			e.printStackTrace();
		} catch (LetrasMatriculaNoValidasException e) {
			// Imprimimos el error
			e.printStackTrace();
		} catch (NumeroMatriculaNoValidoException e) {
			// Imprimimos el error
			e.printStackTrace();
		}
	}

	private static void insertFurgo(Vehiculo a) {
		// Insertamos la furgoneta en la base de datos
		String sql = "INSERT INTO furgoneta VALUES (?,?)";
		PreparedStatement st;

		try {
			//Ejecutamos la primera instrucción (VEHICULO)
			insertVehiculo(a);

			//Ejecutamos la segunda instrucción (DE COMBUSTION)
			insertDeCombustion(a);
			//Ejecutamos la tercera instrucción (FURGONETA)
			st = EmpresaDB.conn.prepareStatement(sql);
			Furgoneta b = (Furgoneta) a ;
			st.setInt(1,  b.getCapacidadCarga());
			st.setString(2, b.getCarnetRequerido());
			st.execute();
			st.execute("COMMIT");

			//Cerramos la conexión
			st.close();
		} catch (SQLException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			// Imprimimos el error en la consola
			e.printStackTrace();
		}
	}

	private static void insertMoto(Vehiculo a) {
		// Insertamos la moto en la base de datos

		String sql2 = "INSERT INTO moto VALUES (?,?)";
		PreparedStatement st;

		try {
			//Ejecutamos la primera instrucción (VEHICULO)
			insertVehiculo(a);
			//Ejecutamos la segunda instrucción (ELECTRICO)
			insertElectrico((Electrico)a);
			//Ejecutamos la tercera instrucción (MOTO)
			st = EmpresaDB.conn.prepareStatement(sql2);
			Moto b = (Moto) a ;
			st.setInt(1,  b.getCilindrada());
			st.setString(2, b.getCarnetRequerido());
			st.execute();
			st.execute("COMMIT");

			//Cerramos la conexión
			st.close();
		} catch (SQLException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			// Imprimimos el error en la consola
			e.printStackTrace();
		}

	}

	/**
	 * Método que realiza el INSERT INTO VEHICULO de cualquier vehiculo dado
	 * Utilizado para los atributos generales del insert de todo nuevo veh�culo 
	 * <p>
	 * Este Método además realiza la instrucción de COMMIT 
	 * (guardar los cambios en la base de datos)
	 * </p>
	 * @param a la clase que extiende de Vehiculo. Por ejemplo: Coche, Moto ...
	 * @throws SQLException
	 * @throws LetrasMatriculaNoValidasException
	 * @throws NumeroMatriculaNoValidoException
	 */
	private static void insertVehiculo(Vehiculo a)
			throws SQLException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException {
		PreparedStatement st;
		String sql1 = "INSERT INTO VEHICULO VALUES (?,?,?,?,?,?,?,?)";

		st = EmpresaDB.conn.prepareStatement(sql1);
		st.setString(1,a.getMatricula().toString());
		st.setString(2, a.getMarca());
		st.setString(3, a.getModelo());
		st.setString(4, a.getColor());
		GregorianCalendar aux = a.getFechaAlta();	//Acortamos la ruta a escribir
		st.setString(5, aux.get(Calendar.YEAR)+"/"+aux.get(Calendar.MONTH)+"/"+aux.get(Calendar.DAY_OF_MONTH));
		st.setInt(6, a.getKms());
		st.setString(7, a.getCategoria().getCodigo());
		st.setString(8, a.getOficina().getCódigo());
		st.execute();
		st.execute("COMMIT");
	}

	public static VehiculoDB leeVehiculo(Matricula mat) throws RecargoNoValidoException
	{
		String sql = "SELECT * FROM VEHICULO WHERE MATRICULA=UPPER(?)";
		PreparedStatement st;
		ResultSet rs;
		// Declaramos las variables que utilizaremos para crear el vehiculo
		String marca = null;
		String mod = null;
		String color = null;
		Date fecha = null;
		String cod_categoria = null;
		String cod_oficina = null;
		GregorianCalendar fechaAlta = new GregorianCalendar();
		int km = 0;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, mat.toString());
			rs = st.executeQuery();
			while (rs.next())
			{
				marca = rs.getString("marca");
				mod = rs.getString("modelo");
				color = rs.getString("color");
				fecha = rs.getDate("fechaalta");
				fechaAlta.setTime(fecha);
				km = rs.getInt("kms");
				cod_categoria = rs.getString("categoria");	//Debemos obtener toda la categoria
				cod_oficina = rs.getString("oficina");
			}
			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException e) {
			// Mostramos la traza
			e.printStackTrace();
		}
		return new VehiculoDB(mat, marca, mod, color, fechaAlta, km, new Categoria(RepositorioCategoria.leeCategoria(cod_categoria)),new Oficina(RepositorioOficina.leeOficina(cod_oficina)));
	}

	public static Vehiculo lee(Vehiculo vehiculo) throws RecargoNoValidoException
	{
		Matricula mat = null;
		try {
			mat = vehiculo.getMatricula();
		} catch (LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e1) {
			// error con la matrícula
			e1.printStackTrace();
		}
		CocheCombustion coche = null;
		CocheElectrico tesla = null;
		Furgoneta furgo = null;
		Moto moto = null;
		String sql = "SELECT * FROM VEHICULO WHERE MATRICULA=UPPER(?)";
		PreparedStatement st;
		ResultSet rs;
		// Declaramos las variables que utilizaremos para crear el vehiculo
		String marca = null;
		String mod = null;
		Categoria cat = null;
		String color = null;
		Date fecha = null;
		String cod_categoria = null;
		String cod_oficina = null;
		Oficina ofi = null;
		GregorianCalendar fechaAlta = new GregorianCalendar();
		int km = 0;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, mat.toString());
			rs = st.executeQuery();
			while (rs.next())
			{
				marca = rs.getString("marca");
				mod = rs.getString("modelo");
				color = rs.getString("color");
				fecha = rs.getDate("fechaalta");
				fechaAlta.setTime(fecha);
				km = rs.getInt("kms");
				cod_categoria = rs.getString("categoria");	//Debemos obtener toda la categoria
				cat = RepositorioCategoria.leeCategoria(cod_categoria);
				cod_oficina = rs.getString("oficina");// También obtenemos toda la Oficina
				ofi = RepositorioOficina.leeOficina(cod_oficina);
			}
			//Cerramos la conexión
			st.close();
			rs.close();

			// Atributos propios de la clase DeCombustion
			double consumo = 0.0;
			int potencia = 0;
			String emisiones = null;
			// Atributos propios de la clase Electrico
			int autonomia = 0;
			int tiempoRecarga = 0;
			// Atributos característicos de Coche
			int numPlazas = 0;
			String tipo = null;
			//Atributo de furgoneta
			int capacidadCarga = 0;
			String licenciaRequerida = null;	//Atributo también de moto
			// Atributo exclusivo de moto
			int cilindrada = 0;
			Matricula mat1 =null;
			
			if (RepositorioVehiculo.leeDeCombustion(mat)!=null)
			{	//Si es de combustión extraemos sus atributos
				st.close();
				rs.close();
				sql = "SELECT * FROM decombustion WHERE vehiculo_matricula=?";
				st = EmpresaDB.conn.prepareStatement(sql);
				st.setString(1, mat.toString());
				rs = st.executeQuery();

				while (rs.next())
				{
					consumo = rs.getDouble("consumo");
					potencia = rs.getInt("potencia");
					emisiones = rs.getString("emisiones");
				}
				switch (RepositorioVehiculo.tipoVehiculo(vehiculo))
				{
				case "CocheCombustion":
					st.close();
					rs.close();
					mat1 = vehiculo.getMatricula();
					sql = "SELECT * FROM cochecombustion WHERE combustionmatricula=?";
					st = EmpresaDB.conn.prepareStatement(sql);
					st.setString(1, mat1.toString());
					rs = st.executeQuery();

					while (rs.next())
					{
						numPlazas = rs.getInt("numPlazas");
						tipo = rs.getString("tipo");
					}
					try {
						coche = new CocheCombustion(mat1, marca, mod, cat, color, fechaAlta, ofi, km, numPlazas, consumo, tipo, potencia, emisiones);
					} catch (EmisionesNoValidasException | NumPlazasNoValidoException | ConsumoNoValidoException
							| PotenciaNoValidaException | TipoNoValidoException e) {
						// no se pudo crear el coche
						e.printStackTrace();
					}
					break;

				case "Furgoneta":
					st.close();
					rs.close();
					mat1 = vehiculo.getMatricula();
					sql = "SELECT * FROM furgoneta WHERE matricula=?";
					st = EmpresaDB.conn.prepareStatement(sql);
					st.setString(1, mat1.toString());
					rs = st.executeQuery();
					
					while (rs.next())
					{
						capacidadCarga = rs.getInt("capacidadcarga");
						licenciaRequerida = rs.getString("carnetRequeridoFurgo");
					}
					
					furgo = new Furgoneta(mat1, marca, mod, cat, color, fechaAlta, ofi, km, consumo, potencia, emisiones, capacidadCarga, licenciaRequerida);
					break;
				}
			}else {
				//Si no es de combustión será eléctrico
				st.close();
				rs.close();
				sql = "SELECT * FROM electrico WHERE vehiculo_matricula=?";
				st = EmpresaDB.conn.prepareStatement(sql);
				st.setString(1, mat.toString());
				rs = st.executeQuery();

				while (rs.next())
				{
					autonomia = rs.getInt("autonomia");
					tiempoRecarga = rs.getInt("tiempoRecarga");
				}
				switch (RepositorioVehiculo.tipoVehiculo(vehiculo))
				{
				case "CocheElectrico":

					st.close();
					rs.close();
					mat = vehiculo.getMatricula();
					sql = "SELECT * FROM cocheelectrico WHERE vehiculo_matricula=?";
					st = EmpresaDB.conn.prepareStatement(sql);
					st.setString(1, mat.toString());
					rs = st.executeQuery();

					while (rs.next())
					{
						numPlazas = rs.getInt("numPlazas");
						tipo = rs.getString("tipo");
					}
					tesla = new CocheElectrico(mat, marca, mod, cat, color, fechaAlta, ofi, km, autonomia, tiempoRecarga, tipo, numPlazas);
					break;
				case "Moto":
					st.close();
					rs.close();
					Matricula mat11 = vehiculo.getMatricula();
					sql  ="SELECT * FROM moto WHERE vehiculo_matricula=?";
					st = EmpresaDB.conn.prepareStatement(sql);
					st.setString(1, mat11.toString());
					rs = st.executeQuery();
					
					while (rs.next())
					{
						cilindrada = rs.getInt("cilindrada");
						licenciaRequerida = rs.getString("carnet");
					}
					
					moto = new Moto(mat11, marca, mod, cat, color, fechaAlta, ofi, km, autonomia, tiempoRecarga, cilindrada, licenciaRequerida);
					break;
				}
			}

			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException | TiempoRecargaNoValidoException 
				| EmisionesNoValidasException | NumPlazasNoValidoException | TipoNoValidoException 
				| ConsumoNoValidoException | PotenciaNoValidaException | CarnetRequeridoInvalidoException 
				| CilindradaNoValidaException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			// Mostramos la traza
			e.printStackTrace();
		}

		switch (RepositorioVehiculo.tipoVehiculo(vehiculo))
		{
		case "CocheCombustion":
			return coche;
		case "CocheElectrico":
			return tesla;
		case "Furgoneta":
			return furgo;
		case "Moto":
			return moto;
		default:
			return moto;
		}

	}
	
	

	private static String tipoVehiculo(Vehiculo vehiculo) {
		// Consultamos la matricula en las distintas tablas
			if (vehiculo.getClass()==CocheCombustion.class)
			{
				return "CocheCombustion";
			}else if (vehiculo.getClass()==CocheElectrico.class) {
				return "CocheElectrico";
			}else if (vehiculo.getClass()==Furgoneta.class) {
				return "Furgoneta";
			}else if(vehiculo.getClass()==Moto.class) {
				return "Moto";
			}else {
				return null;
			}
			
	}

	public static CocheCombustion leeCocheComb(Matricula matricula) throws EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, TipoNoValidoException, RecargoNoValidoException
	{
		String sql = "SELECT * FROM cocheCombustion WHERE combustionmatricula = ?";
		PreparedStatement st;
		ResultSet rs;
		String tipo = null;
		int numPlazas = 0;
		VehiculoDB general = null;
		try {
			general = RepositorioVehiculo.leeVehiculo(matricula);
		} catch (RecargoNoValidoException e1) {
			// Imprimimos la traza
			e1.printStackTrace();
		}
		CombustionBD deComb = RepositorioVehiculo.atributosDeCombustion(matricula);

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, matricula.toString());
			rs = st.executeQuery();

			while (rs.next())
			{
				numPlazas = rs.getInt("numplazas");
				tipo = rs.getString("tipo");
			}
		} catch (SQLException e) {
			// Imprimimos la traza del error
			e.printStackTrace();
		}

		return new CocheCombustion(matricula, general.getMarca(), general.getModelo(), general.getCategoria(), general.getColor(),  general.getFechaAlta(), general.getOficina(), general.getKms(),numPlazas, deComb.getConsumo(), tipo, deComb.getPotencia(),deComb.getEmisiones());

	}

	/**
	 * Con un algoritmo de fuerza bruta que busca en todos los tipos de veh�culo posibles
	 * sabemos qu� tipo de veh�culo es el de la matr�cula dada
	 * @param matricula	La matricula del veh�culo en cuesti�n
	 * @return	El tipo de vehiculo en forma de {@code String}
	 * <p>
	 * Este puede ser "CocheCombustion", "CocheElectrico", "Furgoneta", "Moto"
	 * 					    � {@code null} si no es ninguno de los anteriores.
	 * </p> 
	 * @throws RecargoNoValidoException 
	 * @throws TipoNoValidoException 
	 * @throws PotenciaNoValidaException 
	 * @throws ConsumoNoValidoException 
	 * @throws NumPlazasNoValidoException 
	 * @throws EmisionesNoValidasException 
	 */
	public static String tipoVehiculoBD(Matricula matricula) throws EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, TipoNoValidoException, RecargoNoValidoException
	{
		if (leeCocheComb(matricula)!=null)
		{
			return "CocheCombustion";
		}else if (leeCocheElectrico(matricula)!=null) {
			return "CocheElectrico";
		}else if (leeFurgo(matricula)!=null) {
			return "Furgoneta";
		}else if(leeMoto(matricula)!=null) {
			return "Moto";
		}else {
			return null;
		}
	}
/*
	public static String tipoVehiculo(Vehiculo auto) throws EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, TipoNoValidoException, RecargoNoValidoException
	{
		if (auto.getClass().getSimpleName()=="CocheCombustion")
		{
			return "CocheCombustion";
		}else if (auto.getClass().getSimpleName()=="CocheElectrico") {
			return "CocheElectrico";
		}else if (auto.getClass().getSimpleName()=="Furgoneta") {
			return "Furgoneta";
		}else if(auto.getClass().getSimpleName()=="Moto") {
			return "Moto";
		}else {
			return null;
		}
	}*/


	public static Moto leeMotocicleta(Matricula matricula) {

		String sql = "SELECT * FROM moto M JOIN vehiculo V ON V.matricula=M.VEHICULO_MATRICULA "
				+ "WHERE V.matricula=?";
		PreparedStatement st;
		ResultSet rs;
		Moto motomami = null;
		int cilindrada = 0;
		String carnet = null; //El carnet requerido para conducir la moto

		VehiculoDB a = null;
		try {
			a = leeVehiculo(matricula);
		} catch (RecargoNoValidoException e1) {
			// error
			e1.printStackTrace();
		}
		//		DeCombustionDB b = leeDeCombustion(matricula);
		ElectricoBD b = leeElectricoBD(matricula);

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, matricula.toString());
			rs = st.executeQuery();

			while (rs.next())
			{
				cilindrada = rs.getInt("cilindrada");
				carnet = rs.getString("carnet");
			}
			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException e) {
			// Imprimimos la trazabilidad
			e.printStackTrace();
		}

		try {
			motomami = new Moto(matricula,a.getMarca(),a.getModelo(),a.getCategoria(),a.getColor(),a.getFechaAlta(),a.getOficina(),
					a.getKms(),b.getAutonomia(),b.getTiempoRecarga(),cilindrada,carnet);
		} catch (CilindradaNoValidaException | CarnetRequeridoInvalidoException | TiempoRecargaNoValidoException
				| RecargoNoValidoException e) {
			//error
			e.printStackTrace();
		}

		return motomami;
	}

	private static ElectricoBD leeElectricoBD(Matricula matricula) {
		String sql ="SELECT * FROM ELECTRICO WHERE Vehiculo_matricula = UPPER(?)";
		PreparedStatement st;
		ResultSet rs;
		int tiempoRecarga = 0;
		int autonomia = 0;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, matricula.toString());
			rs = st.executeQuery();

			while (rs.next())
			{
				autonomia = rs.getInt("autonomia");
				tiempoRecarga = rs.getInt("tiemporecarga");
			}
			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException e) {
			// Error
			e.printStackTrace();
		}	
		//devolvemos los atributos adyacentes a ELECTRICO
		return new ElectricoBD(autonomia, tiempoRecarga);
	}

	public static MotoBD leeMoto(Matricula matricula) {

		String sql = "SELECT * FROM moto WHERE vehiculo_matricula=?";
		PreparedStatement st;
		ResultSet rs;
		int cilindrada = 0;
		String carnet = null; //El carnet requerido para conducir la moto

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, matricula.toString());
			rs = st.executeQuery();

			while (rs.next())
			{
				cilindrada = rs.getInt("cilindrada");
				carnet = rs.getString("carnet");
			}
			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException e) {
			// Imprimimos la trazabilidad
			e.printStackTrace();
		}

		return new MotoBD(cilindrada,carnet);
	}
	public static FurgonetaBD leeFurgo(Matricula matricula) {
		// Buscamos la furgoneta de la matricula dada
		String sql = "SELECT * FROM furgoneta WHERE matricula=?";
		PreparedStatement st;
		ResultSet rs;
		int carga=0; //La capacidad de carga del veh�culo
		String carnet= null; //El carnet necesario para conducirlo

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, matricula.toString());
			rs = st.executeQuery();

			while (rs.next())
			{
				carga = rs.getInt("capacidadcarga");
				carnet = rs.getString("carnetRequeridoFurgo");
			}
			//Cerramos la conexión para liberar la carga 
			st.close();
			rs.close();
		} catch (SQLException e) {
			// Imprimimos la traza
			e.printStackTrace();
		}

		return new FurgonetaBD(carga, carnet);
	}

	public static Furgoneta leeFurgoneta(Matricula matricula)  {
		// Buscamos la furgoneta de la matricula dada
		VehiculoDB a = null;
		DeCombustionDB b = null;
		FurgonetaBD c = null;
		Furgoneta Furgo = null;
		try {
			a = leeVehiculo(matricula);
			b = leeDeCombustion(matricula);
			c = leeFurgo(matricula);

			Furgo = new Furgoneta(matricula, a.getMarca(), a.getModelo(), a.getCategoria(), a.getColor(),  a.getFechaAlta(), a.getOficina(), a.getKms(), b.getConsumo(), b.getPotencia(), b.getEmisiones(), c.getCapacidadCarga(), c.getCarnetRequerido());

		} catch (RecargoNoValidoException | EmisionesNoValidasException | ConsumoNoValidoException | PotenciaNoValidaException e) {
			// error
			e.printStackTrace();
		}

		return Furgo;
	}

	private static DeCombustionDB leeDeCombustion(Matricula matricula) {
		// 
		String sql ="SELECT * FROM deCombustion WHERE vehiculo_matricula=?";
		PreparedStatement st;
		ResultSet rs;
		double consumo = 0;
		int potencia = 0;
		String emisiones = null;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, matricula.toString());
			rs = st.executeQuery();

			while(rs.next())
			{
				consumo = rs.getDouble("consumo");
				potencia = rs.getInt("potencia");
				emisiones = rs.getString("emisiones");
				//				mat = leeMatricula(rs.getString("vehiculo_matricula"));
			}

			//Cerramos la conexion
			st.close();
		} catch (SQLException e) {
			//error
			e.printStackTrace();
		}

		return new DeCombustionDB(consumo, potencia, emisiones);
	}

	public static CocheElectrico leeCocheElectrico(Matricula matricula) {
		// creamos el objeto con los atributos heredados de electrico
		String sql = "SELECT * FROM ELECTRICO WHERE vehiculo_matricula=?";
		PreparedStatement st;
		ResultSet rs;
		int autonomia=0;
		int tiempoRecarga=0;
		int numPlazas=0;
		String tipo = null;
		VehiculoDB general = null;
		CocheElectrico coche = null;

		try {
			general = RepositorioVehiculo.leeVehiculo(matricula);
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, matricula.toString());
			rs = st.executeQuery();

			while(rs.next())
			{
				numPlazas = rs.getInt("numplazas");
				tipo = rs.getString("tipo");
				autonomia = rs.getInt("autonomia");
				tiempoRecarga = rs.getInt("tiemporecarga");
			}
			//Cerramos la conexion
			rs.close();
			st.close();
		} catch (SQLException | RecargoNoValidoException e) {
			// Imprimimos la traza
			e.printStackTrace();
		}

		try {
			coche = new CocheElectrico(matricula,general.getMarca(),general.getModelo(),general.getCategoria(), general.getColor(),general.getFechaAlta(),general.getOficina(),general.getKms(),autonomia, tiempoRecarga,tipo, numPlazas);
		} catch (TiempoRecargaNoValidoException | EmisionesNoValidasException | NumPlazasNoValidoException
				| TipoNoValidoException | RecargoNoValidoException e) {
			// Imprimimos la traza
			e.printStackTrace();
		}
		return coche;
	}
	private static CombustionBD atributosDeCombustion(Matricula mat)
	{
		String sql = "SELECT * FROM deCombustion where vehiculo_matricula =?";
		double consumo = 0;
		int potencia = 0;
		String emisiones = null;
		PreparedStatement st;
		ResultSet rs;

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, mat.toString());
			rs = st.executeQuery();
			while (rs.next())	//S�lo nos devuelve un resultado pq preguntamos por la PK
			{
				potencia = rs.getInt("potencia");
				consumo = rs.getDouble("consumo");
				emisiones = rs.getString("emisiones");
			}
			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException e) {
			// Imprimimos la traza
			e.printStackTrace();
		}
		return new CombustionBD(consumo,potencia,emisiones);
	}

	public static String tipoVehiculo(VehiculoDB auto) throws EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, TipoNoValidoException, RecargoNoValidoException
	{
		Matricula mat = auto.getMatricula();

		return tipoVehiculoBD(mat);
	}

	public static Matricula leeMatricula(String matriculaCompleta) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException {
		String parte_numerica = matriculaCompleta.substring(0,4);
		int numeros = Integer.parseInt(parte_numerica);
		String letras = matriculaCompleta.substring(4);

		return new Matricula(numeros, letras);
	}

	public static ArrayList<CocheCombustion> leeCochesComb() {
		ArrayList<CocheCombustion> lista = new ArrayList<CocheCombustion>();
		// Leemos las matriculas 
		String sql = "SELECT combustionmatricula FROM cochecombustion";
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next())
			{
				String mat = rs.getString("combustionmatricula");
				Matricula matricula = leeMatricula(mat);
				lista.add(leeCocheComb(matricula));
			}
		} catch (SQLException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException | EmisionesNoValidasException | NumPlazasNoValidoException | ConsumoNoValidoException | PotenciaNoValidaException | TipoNoValidoException | RecargoNoValidoException e) {
			// Posibles errores
			e.printStackTrace();
		}
		return lista;
	}

	public static ArrayList<CocheElectrico> leeCochesElectricos() {
		ArrayList<CocheElectrico> lista = new ArrayList<CocheElectrico>();
		// Leemos las matriculas 
		String sql = "SELECT vehiculo_matricula FROM cocheelectrico";
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next())
			{
				String mat = rs.getString("vehiculo_matricula");
				Matricula matricula = leeMatricula(mat);
				lista.add(leeCocheElectrico(matricula));
			}
		} catch (SQLException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			// Posibles errores
			e.printStackTrace();
		}
		return lista;
	}

	public static ArrayList<Moto> leeMotos() {
		//Creamos el arrayList
		ArrayList<Moto> motos = new ArrayList<Moto>();
		//Consulta
		String sql = "SELECT vehiculo_matricula FROM moto";
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next())
			{
				String mat = rs.getString("vehiculo_matricula");
				Matricula matricula = leeMatricula(mat);
				motos.add(leeMotocicleta(matricula));
			}
		} catch (SQLException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			// errores
			e.printStackTrace();
		}
		
		return motos;
	}

	@SuppressWarnings("deprecation")
	public static Vehiculo leeVehiculo(String matricula) {
			Vehiculo vehiculo=null;
			Categoria categoria=null;
			Oficina oficina=null;
			ResultSet resultadoSql=null;
			ResultSet tipo=null;
			ResultSet tipo2=null;
			boolean hay=false;
			boolean hay2=false;
			int tipofinal=-1;
			Matricula mat = null;
			GregorianCalendar fechaAlta = null;
			
			try {
				PreparedStatement ps=EmpresaDB.conn.prepareStatement("SELECT * FROM vehiculo WHERE matricula=?");
				ps.setString(1, matricula);
				resultadoSql=ps.executeQuery();
				
				
				if(resultadoSql.next()) {
					
					
					ps=EmpresaDB.conn.prepareStatement("SELECT * FROM decombustion WHERE vehiculo_matricula=?");
					ps.setString(1, matricula);
					tipo=ps.executeQuery();
					mat = leeMatricula(resultadoSql.getString("matricula"));
					Date fecha = resultadoSql.getDate("fechaalta");
					fechaAlta = new GregorianCalendar(fecha.getYear(),fecha.getMonth(),fecha.getDate());
					categoria=RepositorioCategoria.leeCategoria(resultadoSql.getString("categoria"));
					oficina=RepositorioOficina.leeOficina(resultadoSql.getString("oficina"));
			
					if(tipo.next()) {
						hay=true;
						ps=EmpresaDB.conn.prepareStatement("SELECT * FROM cocheCombustion where combustionmatricula=?");
						ps.setString(1, matricula);
						tipo2=ps.executeQuery();
						
						if(tipo2.next()) {
							hay2=true;
							tipofinal=1;
						}
						
						if(!hay2) {
							ps=EmpresaDB.conn.prepareStatement("SELECT * FROM furgoneta where matricula=?");
							ps.setString(1, matricula);
							tipo2=ps.executeQuery();
							tipofinal=2;
						}
					}
					
					if(!hay) {
						ps=EmpresaDB.conn.prepareStatement("SELECT * FROM electrico where vehiculo_matricula=?");
						ps.setString(1, matricula);
						tipo=ps.executeQuery();
						
						if(tipo.next()) {
							ps=EmpresaDB.conn.prepareStatement("SELECT * FROM cocheelectrico where vehiculo_matricula=?");
							ps.setString(1, matricula);
							tipo2=ps.executeQuery();
							
							if(tipo2.next()) {
								hay2=true;
								tipofinal=3;
							}
							if(!hay2) {
								ps=EmpresaDB.conn.prepareStatement("SELECT * FROM moto where vehiculo_matricula=?");
								ps.setString(1, matricula);
								tipo2=ps.executeQuery();
								tipofinal=4;
							}
						}
					}
					
				}
				
				
				switch(tipofinal) {
				case -1:
					break;
				case 1:				
					vehiculo=new CocheCombustion(mat,resultadoSql.getString("marca"),resultadoSql.getString("modelo"),categoria,
							resultadoSql.getString("color"),fechaAlta,oficina,resultadoSql.getInt("kms"),tipo2.getInt("numPlazas"),
							tipo.getDouble("consumo"),tipo2.getString("tipo"),tipo.getInt("potencia"),tipo.getString("emisiones"));
					break;
				case 2:
					if(tipo2.next()) {
												
								
						vehiculo=new Furgoneta(mat,resultadoSql.getString("marca"),resultadoSql.getString("modelo"),categoria,
								resultadoSql.getString("color"),fechaAlta,oficina,resultadoSql.getInt("kms")
								,tipo.getDouble("consumo"),tipo.getInt("potencia"),tipo.getString("emisiones"),
								tipo2.getInt("capacidadcarga"),
								tipo2.getString("carnetRequeridoFurgo"));
					}
					
					break;
				case 3:
										
					vehiculo= new CocheElectrico(mat,resultadoSql.getString("marca"),resultadoSql.getString("modelo"),categoria,
							resultadoSql.getString("color"),fechaAlta,oficina,resultadoSql.getInt("kms"),
							tipo.getInt("autonomia"),tipo.getInt("tiemporecarga"),
							tipo2.getString("tipo"), tipo2.getInt("numPlazas")	);
					break;
				case 4:
					if (tipo2.next()) {
						
						vehiculo= new Moto(mat,resultadoSql.getString("marca"),resultadoSql.getString("modelo"),
								categoria,resultadoSql.getString("color"),fechaAlta,RepositorioOficina.leeOficina(resultadoSql.getString("oficina")),resultadoSql.getInt("kms"),
								tipo.getInt("autonomia"),tipo.getInt("tiemporecarga"),
								tipo2.getInt("cilindrada"),
								tipo2.getString("carnet"));
					}
					
					
				}
			}catch(Exception e) {
				e.printStackTrace();
				vehiculo=null;
			}
			return vehiculo;
		}
	

}
