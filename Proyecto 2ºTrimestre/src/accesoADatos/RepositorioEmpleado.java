package accesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.sql.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import exceptions.DNInoValidoException;
import exceptions.LongitudCadenaNoValidaException;

/**
 * Clase que contiene métodos públicos estáticos, encargados del manejo de la base de datos
 * especializado en las tablas: Empleado, Persona
 * @author Victor
 *
 */
public class RepositorioEmpleado {

	/**
	 * 
	 * @param dni El {@code String} que es el dni del empleado
	 * @return El empleado cuyo DNI es el escrito
	 */
	public static Empleado leeEmpleado(String dni) 
	{
		PreparedStatement st = null;
		ResultSet rs;
		Empleado emple = null;	//Sacamos todos los atributos
		//Los generales de -> PERSONA
		//Y los específicos de -> EMPLEADO
		String sql="SELECT * FROM Persona P JOIN EMPLEADO E ON P.DNI=E.PERSONA_DNI "
				+ "WHERE DNI=UPPER(?)";


		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, dni);
			rs = st.executeQuery();

			while (rs.next())
			{
				//Solo va a devolver 1(porque estamos preguntando por la PK) asi q no hay problema de sobre escritura
				String Dni = rs.getString("dni");
				String nombre = rs.getString("Nombre");
				String ap1 = rs.getString("Ap1");
				String ap2 = rs.getString("Ap2");
				//Obtenemos la oficina
				String o = rs.getString("Oficina");	//Obtenemos el código de la oficina
				Oficina Ofi = RepositorioOficina.leeOficina(o);	//Encontramos la oficina
				//Obtenemos la fecha de alta del trabajador
				Date fechaAlta = rs.getDate("Fecha_alta");
				//Lo pasamos a GregorianCalendar
				GregorianCalendar fechAlta = new GregorianCalendar();
				fechAlta.setTime(fechaAlta);


				try {		/* Nos cubrimos de los errores de la creación de Empleado */
					emple = new Empleado(nombre, ap1, ap2, Dni, fechAlta, Ofi);
				} catch (LongitudCadenaNoValidaException e) {
					// Longitud inválida
					JOptionPane.showMessageDialog(null, "Longitud de cadena no válida","Error de creación",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (DNInoValidoException e) {
					// DNI no válido 
					JOptionPane.showMessageDialog(null, "DNI no válido: "+dni,"Error de creación",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} 
			}

			//Cerramos la conexión
			rs.close();
			st.close();
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null,
					"Error al intentar leer empleado de Base de Datos. "+"Empleado: "+dni,
					"DataBase error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		return emple;
	}

	/**
	 * Lee directamente TODOS los empleados de la Base de Datos
	 * y devuelve: 
	 * @return Un {@code ArrayList<Empleado>} con todos ellos
	 */
	public static ArrayList<Empleado> leeEmpleados()
	{
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		String sql="SELECT persona_dni FROM Empleado";

		ResultSet rs;
		try {
			PreparedStatement st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			//Intentamos conectar
			while (rs.next())	//Mientras avance el cursor
			{
				//Cogemos una oficina
				String dni = rs.getString("persona_dni");
				//La añadimos al ArrayList
				lista.add(leeEmpleado(dni));
			}
			st.close(); 	//Cerramos la conexion
			rs.close();
		} catch (SQLException e1) {
			// Error

			e1.printStackTrace();
		}

		return lista;
	}

	public static ArrayList<Empleado> leeEmpleados(Oficina ofi)
	{
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		String FK = ofi.getCódigo();	
		String sql="SELECT persona_dni FROM EMPLEADO WHERE oficina = ?";
		//		sql+=FK; //concatenamos el cod_ofi
		PreparedStatement st;
		ResultSet rs;
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, FK);	//Le ponemos el código de la oficina donde el ?
			rs = st.executeQuery();
			//Intentamos conectar
			while (rs.next())	//Mientras avance el cursor
			{
				//Cogemos un empleado
				String dni = rs.getString("persona_dni");
				//Lañadimos al ArrayList
				lista.add(leeEmpleado(dni));
			}
			st.close(); 	//Cerramos la conexion
			rs.close();
		} catch (SQLException e1) {
			// Error
			JOptionPane.showMessageDialog(null,
					"Error al intentar leer los empleados de "+ofi+" de Base de Datos.",
					"DataBase error",
					JOptionPane.ERROR_MESSAGE);
			e1.printStackTrace();
		}

		return lista;
	}

	public static boolean insertEmpleado(Empleado emple)
	{
		//		boolean hecho = false; //A priori no se ha ejecutado la operaci�n
		if (leeEmpleado(emple.getDni())!=null)	//Si existe en la BD
		{
			return update(emple);
		}else {
			return insert(emple);
		}
	}

	/**
	 * M�todo que actualiza todas las tuplas de un empleado con un
	 * determinado DNI
	 * @param emple un empleado alterado
	 */
	private static boolean update(Empleado emple) {
		boolean hecho = false; //A priori no se ha ejecutado la operaci�n

		try {//establecemos la conexión
			//Primero modificamos las propiedades del objeto Persona

			updateNombre(emple);
			updateAp1(emple);
			updateAp2(emple);
			//Ahora vamos con los atributos del empleado
			updateFecha_alta(emple);
			updateCodOficina(emple);
			hecho=true;	//Ha podido ejecutar todo el c�digo dentro del TRY
		} catch (SQLException e) {
			// error
			e.printStackTrace();
		}
		return hecho;
	}

	private static void updateCodOficina(Empleado emple) throws SQLException {
		String sql = "UPDATE empleado SET oficina = ? "
				+ "WHERE persona_dni = ?";
		PreparedStatement st;
		st = EmpresaDB.conn.prepareStatement(sql);
		st.setString(1,emple.getOficina().getCódigo());
		st.setString(2, emple.getDni());
		st.execute();
		st.execute("COMMIT");
		st.close();
	}

	private static void updateFecha_alta(Empleado emple) throws SQLException {
		String sql = "UPDATE empleado SET fecha_alta = ? "
				+ "WHERE persona_dni = ?";
		PreparedStatement st;
		st = EmpresaDB.conn.prepareStatement(sql);
		GregorianCalendar fechaAlta = emple.getFechaAlta();
		int dia = fechaAlta.get(Calendar.DAY_OF_MONTH);
		int mes = fechaAlta.get(Calendar.MONTH);
		int anio = fechaAlta.get(Calendar.YEAR);
		@SuppressWarnings("deprecation")
		Date fecha = new Date (anio,mes,dia);
		st.setDate(1, (java.sql.Date) fecha);
		st.setString(2, emple.getDni());
		st.execute();
		st.execute("COMMIT");
		st.close();
	}

	private static void updateAp2(Empleado emple) throws SQLException {
		String sql = "UPDATE PERSONA SET AP2 = ? "
				+ "WHERE dni = ?";
		PreparedStatement st;
		st = EmpresaDB.conn.prepareStatement(sql);
		st.setString(1, emple.getApellido2());
		st.setString(2, emple.getDni());
		st.execute();
		st.execute("COMMIT");
		st.close();
	}

	private static void updateAp1(Empleado emple) throws SQLException {
		String sql = "UPDATE PERSONA SET AP1 = ? "
				+ "WHERE dni = ?";
		PreparedStatement st;
		st = EmpresaDB.conn.prepareStatement(sql);
		st.setString(1, emple.getApellido1());
		st.setString(2, emple.getDni());
		st.execute();
		st.execute("COMMIT");
		st.close();
	}

	private static void updateNombre(Empleado emple) throws SQLException {
		String sql = "UPDATE PERSONA SET NOMBRE = ? "
				+ "WHERE dni = ?";
		PreparedStatement st;
		st = EmpresaDB.conn.prepareStatement(sql);
		st.setString(1, emple.getNombre());
		st.setString(2, emple.getDni());
		st.execute();
		st.execute("COMMIT");
		st.close();
	}

	/*private static String tablaDif(Empleado emple)
	{
		Empleado original = leeEmpleado(emple.getDni());
		if (comparaTuplaPersona(emple, original).compareToIgnoreCase("iguales")!=0)
		{
			return "persona";
		}else {
			return "empleado";
		}
	}*/

	/*private static String tuplaDif(Empleado emple) {
		// Buscamos el empleado equivalente en la Base de Datos
		Empleado original = leeEmpleado(emple.getDni());
		if (RepositorioPersona.leePersona(emple.getDni()) != null)
		{
			if (comparaTuplaPersona(emple, original).compareToIgnoreCase("iguales")!=0)
			{	//Si no son iguales hay una tupla distinta l�gicamente
				return comparaTuplaPersona(emple, original);
			}	
		}
		if (original.getFechaAlta()!=emple.getFechaAlta())
		{
			return "fecha_alta";
		}else if (original.getOficina().equals(emple.getOficina()))
		{
			return "oficina";
		}else {
			return "";
		}
	}*/

	/*private static String comparaTuplaPersona(Empleado emple, Empleado original) {
		if (original.getNombre().compareToIgnoreCase(emple.getNombre())!=0)
		{
			return "nombre";
		}else if (original.getApellido1().compareToIgnoreCase(emple.getApellido1())!=0)
		{
			return "ap1";
		}else if (original.getApellido2().compareToIgnoreCase(emple.getApellido2())!=0)
		{
			return "ap2";
		}else {
			return "iguales";
		}
	}*/

	@SuppressWarnings("deprecation")
	public static boolean insert(Empleado emple)
	{
		boolean ejecutado=false;	//A priori suponemos que no ha podido ejecutarse
		PreparedStatement st = null;
		//		RepositorioPersona.insertPersona(emple);
		String sql="INSERT INTO Persona (dni,nombre,ap1,ap2) VALUES (?,?,?,?)";

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, emple.getDni());
			st.setString(2, emple.getNombre());
			st.setString(3, emple.getApellido1());
			st.setString(4, emple.getApellido2());
			st.executeUpdate();
			st.execute("COMMIT");

			sql="INSERT INTO empleado (fecha_alta,persona_dni,oficina) VALUES (?,?,?)";	

			st = EmpresaDB.conn.prepareStatement(sql);
			//			RepositorioPersona.insertPersona(emple);	//Insertamos primero la persona

			GregorianCalendar aux = emple.getFechaAlta();	//Acortamos la ruta a escribir
			//Componemos la fecha
			//			aux.get(Calendar.YEAR)+"/"+aux.get(Calendar.MONTH)+"/"+aux.get(Calendar.DAY_OF_MONTH)
			Date dat = new Date(aux.get(Calendar.YEAR),aux.get(Calendar.MONTH), aux.get(Calendar.DAY_OF_MONTH));
			st.setDate(1, (java.sql.Date) dat);
			st.setString(2, emple.getDni());
			st.setString(3, emple.getOficina().getCódigo());

			st.executeUpdate();
			st.execute("COMMIT");
			ejecutado = true;	//si llega hasta aquí sin errores se ejecutó
			//Cerramos la conexión
			st.close();
		}catch (SQLException e) {
			// Error
			JOptionPane.showMessageDialog(null,
					"Error al intentar introducir al empleado con DNI "+emple.getDni()+" de Base de Datos.",
					"DataBase error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		return ejecutado;
	}


	/**
	 * Borra un empleado 
	 * @param dni	El dni del empleado a borrar
	 * @return	El número de filas modificadas
	 */
	public static int borraEmpleado(String dni)
	{
		int numFilas = -1;	//A priori no se modifica ninguna fila
		PreparedStatement st;
		String sql="DELETE FROM PERSONA WHERE DNI =?";

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, dni);
			st.execute();	//Al ejecutar la instrucción ya borramos

			st.execute("COMMIT");	//Guardamos	
			if (leeEmpleado(dni) != null)
			{
				//numFilas ya es -1
			}else {
				RepositorioPersona.borraPersona(dni);
				numFilas += 1;
			}

			//Cerramos las conexiones
			st.close();
		} catch (SQLException e) {
			// Mostramos Ventana error NO SER� NECESARIO
			/*JOptionPane.showMessageDialog(null,
					"Error al intentar borrar al empleado con DNI "+dni+" de Base de Datos.",
					"DataBase error",
					JOptionPane.ERROR_MESSAGE);*/
			e.printStackTrace();
		}

		return numFilas;
	}

}
