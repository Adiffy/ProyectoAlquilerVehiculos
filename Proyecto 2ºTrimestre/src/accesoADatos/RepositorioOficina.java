package accesoADatos;

import java.sql.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import clasesObjetos.Oficina;

public class RepositorioOficina {

	
	/**
	 * Método que devuelve un ArrayList de Oficinas sacadas de la base de datos
	 * @return	el {@code ArrayList<Oficina>} extraído de la consulta
	 * @throws SQLException	Error al realizar la consulta
	 */
	public static ArrayList<Oficina> listaOficinas() throws SQLException
	{
		String Instruccionsql = "SELECT * FROM OFICINA";		//La consulta para obtener todas las oficinas
		ArrayList<Oficina> lista = new ArrayList<Oficina>();
		Statement st = EmpresaDB.conn.prepareStatement(Instruccionsql);
		ResultSet resultados = st.executeQuery(Instruccionsql);
		
		while (resultados.next())	//Mientras avance el cursor
		{
			//Cogemos una oficina
			String codigo = resultados.getString("codigo");
			//La añadimos al ArrayList
			lista.add(leeOficina(codigo));
		}
		st.close(); 	//Cerramos la conexion
		return lista;
	}
	
	/**
	 * Devuelve una oficina de la base de datos a partir de su código
	 * Por ejemplo: JA01, MA01...
	 * @param codigo El codigo de la oficina. Es una {@code String}
	 * @return	La oficina WHERE codigo = codigo escrito
	 */
	public static Oficina leeOficina(String codigo)
	{
		PreparedStatement st = null;
		ResultSet rs;
		Oficina ofi = null;
		String sql="SELECT * FROM Oficina WHERE codigo=?";
		
		//TODO averiguar si existe para hacer UPDATE / INSERT
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, codigo);
			rs = st.executeQuery();
			
			while (rs.next())
			{
				//Solo va a devolver 1(porque estamos preguntando por la PK) asi q no hay problema de sobre escritura
				ofi = new Oficina(rs.getString("CODIGO"), rs.getString("DESCRIPCION"), rs.getString("PROVINCIA"), rs.getString("LOCALIDAD"), rs.getBoolean("DEAEROPUERTO"));
			}
			//Cerramos la conexión
			st.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		
		return ofi;
	}
}
