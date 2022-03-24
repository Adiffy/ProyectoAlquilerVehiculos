package accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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
	
	/**
	 * Método que Consulta la base de datos en busca de parámetros tipo {@code String}
	 * @param Instruccionsql	El comando SQL que realiza la consulta a la base de datos. Tipo {@code String}
	 * @param columna	El nombre de la columna por la que se pregunta {@code String} Puede haber una o hasta 6
	 * Pon nombres de columnas "" para solo pedir 1
	 * @return	Un {@code ArrayList<String>} con los resultados obtenidos de la consulta
	 * @throws SQLException
	 */
	public static ArrayList<String> selectString(String Instruccionsql, String columna, String columna2, String columna3, String columna4, String columna5, String columna6) throws SQLException
	{
		Statement orden;
		ArrayList<String> resultado = new ArrayList<String>();
		orden = conn.createStatement();
		ResultSet resultados = orden.executeQuery(Instruccionsql);
		
		while (resultados.next())
		{
			//Lo añadimos al ArrayList
			resultado.add(resultados.getString(columna));
			resultado.add(resultados.getString(columna2));
			resultado.add(resultados.getString(columna3));
			resultado.add(resultados.getString(columna4));
			resultado.add(resultados.getString(columna5));
			resultado.add(resultados.getString(columna6));
		}
		
		orden.close();		//Cerramos el Statement para liberar recursos
		return resultado;
	}
	
	public static ArrayList<Integer> selectInt(String Instruccionsql, String columna) throws SQLException
	{
		Statement orden;
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		orden = conn.createStatement();
		ResultSet resultados = orden.executeQuery(Instruccionsql);
		
		while (resultados.next())
		{
			//Lo añadimos al ArrayList
			resultado.add(resultados.getInt(columna));
		}
		
		orden.close();		//Cerramos el Statement para liberar recursos
		return resultado;
	}
	public static ArrayList<Double> selectDouble(String Instruccionsql, String columna) throws SQLException
	{
		Statement orden;
		ArrayList<Double> resultado = new ArrayList<Double>();
		orden = conn.createStatement();
		ResultSet resultados = orden.executeQuery(Instruccionsql);
		
		while (resultados.next())
		{
			//Lo añadimos al ArrayList
			resultado.add(resultados.getDouble(columna));
		}
		
		orden.close();		//Cerramos el Statement para liberar recursos
		return resultado;
	}
}
