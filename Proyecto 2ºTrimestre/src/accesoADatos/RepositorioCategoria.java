package accesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import clasesObjetos.Categoria;
import exceptions.RecargoNoValidoException;

public class RepositorioCategoria {

	/**
	 * M�todo que lee la categoria de c�digo dado desde BD
	 * @param codigo	El c�digo de la categor�a. Por ejemplo: "S"
	 * @return	La categor�a encontrada en BD o null si no existe ninguna
	 */
	public static Categoria leeCategoria(String codigo)
	{
		//La categor�a a devolver
		Categoria cat = null;		//Por defecto ser� nula
		String sql = "SELECT * FROM CATEGORIA WHERE CODIGO=UPPER(?)";
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, codigo);
			rs = st.executeQuery();
			
			//El codigo ya lo tenemos as� que no preguntar� por �l
			while (rs.next())
			{
				String desc = rs.getString("descripcion");
				double rec = rs.getInt("recargo");	//El porcentaje de recargo inerte a la categor�a
				cat = new Categoria(codigo, desc, rec);
			}
			//cerramos las conexiones
			st.close();
			rs.close();
		} catch (SQLException | RecargoNoValidoException e) {
			// Mostramos el error
			JOptionPane.showMessageDialog(null,
				    "Error al intentar leer categor�a"+" ["+codigo+"]"+" de Base de Datos.",
				    "DataBase error",
				    JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		//Devolvemos la categor�a
		return cat;
	}
	
	public static boolean insertCategoria(Categoria cat)
	{
		boolean operacionRealizada = false;
		if (RepositorioCategoria.leeCategoria(cat.getCodigo()) != null)	//Si existe	
		{
			operacionRealizada = update(cat);
		}else {
			operacionRealizada = insert(cat);
		}
		return operacionRealizada;
	}
	
	private static boolean update(Categoria cat) {
		boolean operacionRealizada = false; 	//A priori no podemos ejecutar la sentencia
		String sql = "UPDATE categoria SET ? = ?";
		PreparedStatement st;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			while (tuplaDif(cat) != null)	//Mientras que haya una columna diferente en BD
			{
				//Primero se introduce el nombre del par�metro a modificar
					st.setString(1, tuplaDif(cat));
				//Ahora introducimos el valor 
					if (tuplaDif(cat).compareToIgnoreCase("descripcion")==0)
					{
						st.setString(2, cat.getDescripcion());
					}else {
						st.setDouble(2, cat.getRecargo());					
			}
					st.execute();	//Realizamos el insert
					st.execute("COMMIT"); //Guardamos los cambios
					
					//Cerramos la conexi�n
					st.close();
			}
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al intentar actualizar la categoria de c�digo "+cat.getCodigo(),"DataBase Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		return operacionRealizada;
	}

	private static String tuplaDif(Categoria aComprobar) {
		String tuplaEncontrada = null; //A priori son iguales
		// Primero consultamos en BD la categor�a en cuesti�n
		Categoria original = leeCategoria(aComprobar.getCodigo());
		//Ahora comprobamos sus atributos
		if (original.getDescripcion().compareToIgnoreCase(aComprobar.getDescripcion())!=0)
		{ //Si su descripci�n es diferente
			tuplaEncontrada = "descripcion";
		}else if (original.getRecargo()!=aComprobar.getRecargo())
		{//Si su recargo es diferente
			tuplaEncontrada = "recargo";
		}
		return tuplaEncontrada;
	}

	public static boolean insert(Categoria cat)
	{
		boolean operacionRealizada = false;
		String sql = "INSERT INTO categoria VALUES (?,?,?)";
		PreparedStatement st;
		
		try {	//Empezamos la conexion
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, cat.getCodigo());
			st.setString(2, cat.getDescripcion());
			st.setInt(3, (int) cat.getRecargo());
			
			//Ejecutamos la sentencia
			if (st.execute())
			{
				operacionRealizada = true;
			}
			st.execute("COMMIT"); //Guardamos los cambios
			//Cerramos la conexi�n
			st.close();
			
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, "Error al intentar insertar categoria de c�digo "+cat.getCodigo(),"DataBase Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
		return operacionRealizada;
	}

	public static void borraCategoria(String codigo) {
		PreparedStatement st = null;
		String sql ="DELETE FROM categoria WHERE codigo=Upper(?)";
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, codigo);
			
			st.execute();
			st.execute("COMMIT");
			
			//Cerramos la conexion
			st.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
	}

	public static ArrayList<Categoria> leeCategorias() {
		ArrayList<Categoria> lista = new ArrayList<Categoria>();
		String sql="SELECT codigo FROM Categoria";

		ResultSet rs;
		try {
			//Iniciamos la conexi�n
			PreparedStatement st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			//Intentamos conectar
			while (rs.next())	//Mientras avance el cursor
			{
				//Cogemos una oficina
				String codigo = rs.getString("codigo");
				//La a�adimos al ArrayList
				lista.add(leeCategoria(codigo));
			}
			st.close(); 	//Cerramos la conexion
			rs.close();
		} catch (SQLException e1) {
			// Error

			e1.printStackTrace();
		}

		return lista;
	}
}
