package accesoADatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Clase principal desde la que podemos realizar una conexión con la base de datos.
 * Esta {@code Clase madre} es clave en el acceso a la base de datos puesto que es la encargada de conectarse con ella.
 * @author Victor
 *
 */
public class EmpresaDB {

	//Propiedades estáticas
	static String cadenaConexion = "jdbc:oracle:thin:@localhost:1521:xe";
	static Connection conn;
	static Statement st;
	
	/**
	 * M�todo que crea una conexi�n con la base de datos
	 */
	public static void abreConexion()
	{
		try {
			conn = DriverManager.getConnection(cadenaConexion, "Victor", "1234");
			st = EmpresaDB.conn.createStatement();	//Lanzamos la orden
		} catch (SQLException e) {
			//TODO sacar ventana error
			e.printStackTrace();
		}
	}
	
	/**
	 * M�todo que Consulta la base de datos en busca de par�metros tipo {@code String}
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
			//Lo a�adimos al ArrayList
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
	
	public static ArrayList<Integer> selectInt(String Instruccionsql, String columna)
	{
		Statement orden;
		ArrayList<Integer> resultado = new ArrayList<Integer>();
		try {
			orden = conn.createStatement();
		
		ResultSet resultados = orden.executeQuery(Instruccionsql);
		
		while (resultados.next())
		{
			//Lo a�adimos al ArrayList
			resultado.add(resultados.getInt(columna));
		}
		
		
		orden.close();		//Cerramos el Statement para liberar recursos
		} catch (SQLException e) {
			//  Sacar ventana error
			JOptionPane.showMessageDialog(null, "No se ha podido conectar con la base de datos",
					"DataBase Error",JOptionPane.ERROR);
			e.printStackTrace();
		}
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
			//Lo a�adimos al ArrayList
			resultado.add(resultados.getDouble(columna));
		}
		
		orden.close();		//Cerramos el Statement para liberar recursos
		return resultado;
	}
}
