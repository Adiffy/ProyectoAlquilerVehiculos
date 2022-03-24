package accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class EmpresaDB {

	//Propiedades est�ticas
	static String cadenaConexion = "jdbc:oracle:thin:@localhost:1521:xe";
	static Connection conn;
	
	/**
	 * M�todo que crea una conexi�n con la base de datos
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
