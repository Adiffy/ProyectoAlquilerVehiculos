package accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmpresaDB {

	//Propiedades estáticas
	static String cadenaConexion = "jdbc:oracle:thin:@localhost:1521:xe";
	static Connection conn;
	
	/**
	 * Método que crea una conexión con la base de datos
	 */
	public static void abreConexion()
	{
		try {
			conn = DriverManager.getConnection(cadenaConexion, "Victor", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
