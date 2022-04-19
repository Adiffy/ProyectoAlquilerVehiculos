package accesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;

import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import exceptions.CarnetRequeridoInvalidoException;
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
		Empleado emple = null;	//Primero sacamos atributos generales de -> PERSONA
		String sql="SELECT * FROM Persona WHERE DNI=?";
		
		//TODO averiguar si existe para hacer UPDATE / INSERT
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, dni);
			rs = st.executeQuery();
			
			while (rs.next())
			{
				//Solo va a devolver 1(porque estamos preguntando por la PK) asi q no hay problema de sobre escritura
				 String Dni = rs.getString("Dni");
				 String nombre = rs.getString("Nombre");
				 String ap1 = rs.getString("Ap1");
				 String ap2 = rs.getString("Ap2");
				 
				 String o = rs.getString("Oficina");	//Obtenemos el código de la oficina
				 Oficina Ofi = RepositorioOficina.leeOficina(o);	//Encontramos la oficina
			
				 rs.close();
				 //Ahora Extraemos los datos de EMPLEADO
				 sql = "SELECT * FROM Empleado WHERE DNI_PERSONA=?";
				 st = EmpresaDB.conn.prepareStatement(sql);
				 st.setString(1, dni);
				 rs = st.executeQuery();
				 
				 while (rs.next())
				 {
					 //Obtenemos la fecha de alta del trabajador
					 Date fechaAlta = rs.getDate("Fecha_alta");
					 //Lo pasamos a GregorianCalendar
					 GregorianCalendar fechAlta = new GregorianCalendar();
					 fechAlta.setTime(fechaAlta);
					 try {
						emple = new Empleado(Dni, nombre, ap1, ap2, fechAlta, Ofi);
					} catch (CarnetRequeridoInvalidoException e) {
						// Carnet Inválido
						e.printStackTrace();
					} catch (LongitudCadenaNoValidaException e) {
						// Longitud inválida
						e.printStackTrace();
					} 
				 }
				
			}
			//Cerramos la conexión
			st.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return emple;
	}
	
	public static ArrayList<Empleado> leeEmpleados()
	{
		ArrayList<Empleado> lista = new ArrayList<Empleado>();
		String sql="SELECT dni FROM Empleado";
		
		ResultSet rs;
		try {
			Statement st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery(sql);
			//Intentamos conectar
			while (rs.next())	//Mientras avance el cursor
			{
				//Cogemos una oficina
				String dni = rs.getString("dni");
				//La añadimos al ArrayList
				lista.add(leeEmpleado(dni));
				st.close(); 	//Cerramos la conexion
			}
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
//			((PreparedStatement) st).setString(1, FK);	//Le ponemos el código de la oficina donde el ?
			rs = st.executeQuery(sql);
			//Intentamos conectar
			while (rs.next())	//Mientras avance el cursor
			{
				//Cogemos una oficina
				String dni = rs.getString("dni");
				//La añadimos al ArrayList
				lista.add(leeEmpleado(dni));
				st.close(); 	//Cerramos la conexion
			}
		} catch (SQLException e1) {
			// Error
			e1.printStackTrace();
		}
		
		return lista;
	}
	
}
