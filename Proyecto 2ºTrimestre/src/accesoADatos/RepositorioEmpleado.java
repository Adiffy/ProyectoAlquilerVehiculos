package accesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;

import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import exceptions.DNInoValidoException;
import exceptions.LongitudCadenaNoValidaException;

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
		//Y los espec�ficos de -> EMPLEADO
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
				String o = rs.getString("Oficina");	//Obtenemos el c�digo de la oficina
				Oficina Ofi = RepositorioOficina.leeOficina(o);	//Encontramos la oficina
				//Obtenemos la fecha de alta del trabajador
				Date fechaAlta = rs.getDate("Fecha_alta");
				//Lo pasamos a GregorianCalendar
				GregorianCalendar fechAlta = new GregorianCalendar();
				fechAlta.setTime(fechaAlta);


				try {		/* Nos cubrimos de los errores de la creaci�n de Empleado */
					emple = new Empleado(nombre, ap1, ap2, Dni, fechAlta, Ofi);
				} catch (LongitudCadenaNoValidaException e) {
					// Longitud inv�lida
					JOptionPane.showMessageDialog(null, "Longitud de cadena no v�lida","Error de creaci�n",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (DNInoValidoException e) {
					// DNI no v�lido 
					JOptionPane.showMessageDialog(null, "DNI no v�lido: "+dni,"Error de creaci�n",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} 
			}

			//Cerramos la conexi�n
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
				//La a�adimos al ArrayList
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
		String sql="SELECT dni FROM Empleado WHERE Oficina = ";
		sql+=FK; //concatenamos el cod_ofi

		ResultSet rs;
		try {
			Statement st = EmpresaDB.conn.prepareStatement(sql);
			//			((PreparedStatement) st).setString(1, FK);	//Le ponemos el c�digo de la oficina donde el ?
			rs = st.executeQuery(sql);
			//Intentamos conectar
			while (rs.next())	//Mientras avance el cursor
			{
				//Cogemos una oficina
				String dni = rs.getString("dni");
				//La a�adimos al ArrayList
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
	
	private static boolean update(Empleado emple) {
		boolean hecho = false; //A priori no se ha ejecutado la operaci�n
		PreparedStatement st = null;
		String sql = "UPDATE "+ tablaDif(emple)+ " SET ? = ? "
					+ "WHERE ? = ?";
		
		try {//establecemos la conexi�n
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, tuplaDif(emple));
			st.setString(2, "");//TODO);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hecho;
	}
	
	private static String tablaDif(Empleado emple)
	{
		Empleado original = leeEmpleado(emple.getDni());
		if (comparaTuplaPersona(emple, original).compareToIgnoreCase("iguales")!=0)
		{
			return "persona";
		}else {
			return "empleado";
		}
	}

	private static String tuplaDif(Empleado emple) {
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
	}

	private static String comparaTuplaPersona(Empleado emple, Empleado original) {
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
	}

	public static boolean insert(Empleado emple)
	{
		boolean ejecutado=false;	//A priori suponemos que no ha podido ejecutarse
		PreparedStatement st = null;
		String sql="INSERT INTO empleado VALUES (?,?,?)";

		//TODO averiguar si existe para hacer UPDATE / INSERT



		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			RepositorioPersona.insertPersona(emple);	//Insertamos primero la persona

			GregorianCalendar aux = emple.getFechaAlta();	//Acortamos la ruta a escribir
			//Componemos la fecha
			st.setString(1, aux.get(Calendar.YEAR)+"/"+aux.get(Calendar.MONTH)+"/"+aux.get(Calendar.DAY_OF_MONTH));
			st.setString(2, emple.getDni());
			st.setString(3, emple.getOficina().getCódigo());

			st.executeUpdate();
			st.executeUpdate("COMMIT");

			//Cerramos la conexi�n
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
	 * @return	El n�mero de filas modificadas
	 */
	public static int borraEmpleado(String dni)
	{
		int numFilas = -1;	//A priori no se modifica ninguna fila
		PreparedStatement st;
		String sql="DELETE FROM EMPLEADO WHERE PERSONA_DNI =?";

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, dni);
			st.execute();	//Al ejecutar la instrucci�n ya borramos

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
