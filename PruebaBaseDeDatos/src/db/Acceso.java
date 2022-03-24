package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Acceso {

	 static String cadenaConexion = "jdbc:oracle:thin:@localhost:1521:xe";
	 static Connection conn;
	 static String sql; 
	
	public static void abreConexion()
	{
		
		try {
			conn = DriverManager.getConnection(cadenaConexion, "Victor", "1234");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public static ArrayList<String> selectString(String sqlInstruccion, String columna) throws SQLException
	{
		Statement orden;
		ArrayList<String> resultado = new ArrayList<String>();
		orden = conn.createStatement();
		ResultSet resultados = orden.executeQuery(sqlInstruccion);
		
		while (resultados.next())
		{
			//Lo añadimos al ArrayList
			resultado.add(resultados.getString(columna));
		}
		
		orden.close();		//Cerramos el Statement para liberar recursos
		return resultado;
	}
	
	
}
