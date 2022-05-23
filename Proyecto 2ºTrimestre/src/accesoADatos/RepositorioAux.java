package accesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class RepositorioAux {

	/**
	 * Devuelve todas las provincias de la BD
	 * @return todos los nombres de las provincias almacenadas en la base de datos
	 */
	public static ArrayList<String> leeProvincias()
	{
		ArrayList<String> lista = new ArrayList<String>();
		PreparedStatement st = null;
		ResultSet rs;
		String sql="SELECT * FROM provincia";
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			int i =1;
			while (rs.next())
			{
				
				lista.add(leeProvincia(i).toUpperCase());
				i++;
			}
			//Cerramos las conexiones
			rs.close();
			st.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}		
		
		/*for (int i=1;i<51;i++)
		{
			lista.add(leeProvincia(i));
		}*/
		return lista;
	}
	/**
	 *  Devuelve el nombre de la provincia 
	 *  o {@code null} si no hay ninguna encontrada
	 * @param cod El c�digo de la provincia. Por ejemplo: �lava/Araba es 1 o Ja�n es 23
	 * @return	El nombre de la provincia. {@code String}
	 */
	public static String leeProvincia(int cod)
	{
		PreparedStatement st = null;
		ResultSet rs;
		String nombreprov = null;	
		String sql="SELECT * FROM provincia WHERE codprov=?"; //El nombre no es PK pero tmb es �nico
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setInt(1, cod);
			rs = st.executeQuery();
			
			while (rs.next())
			{
				nombreprov = rs.getString("nombre");
	
			}
			//Cerramos las conexiones
			rs.close();
			st.close();
		} catch (SQLException e) {
			// Sacamos mensaje de error
//			JOptionPane.showMessageDialog(null, "Ninguna provincia fue encontrada");
			e.printStackTrace();
		}
		
		
		return nombreprov;
	}
	
	/**
	 * Devuelve el c�digo de la oficina de la que le damos el nombre
	 * o {@code -1} si no lo encuentra
	 * @param nombre	El nombre de la provincia
	 * @return	El c�digo de la provincia
	 */
	public static int CodProvincia(String nombre)
	{
		PreparedStatement st = null;
		ResultSet rs;
		int cod=-1;	
		String sql="SELECT * FROM provincia WHERE nombre=?"; //El nombre no es PK pero tmb es �nico
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, nombre);
			rs = st.executeQuery();
			
			while (rs.next())
			{
				cod = rs.getInt("codprov");
	
			}
			//Cerramos las conexiones
			rs.close();
			st.close();
		} catch (SQLException e) {
			// Sacamos mensaje de error
//			JOptionPane.showMessageDialog(null, "Ninguna provincia fue encontrada");
			e.printStackTrace();
		}
		
		
		return cod;
	}
	/**
	 * M�todo que devuelve los tipos de vehiculo que existen
	 * Nota: no se saca de base de datos en este momento
	 * @return	Familiar, 4x4 o deportivo
	 */
	public static ArrayList<String> getTipoVehiculos() {
		//Creamos el arrayList
		ArrayList<String> coleccion = new ArrayList<String>();
		//A�adimos los valores al ArrayList
		coleccion.add("4x4");
		coleccion.add("Familiar");
		coleccion.add("Deportivo");
		// Devolvemos el arrayList
		return coleccion;
	}
	public static ArrayList<String> getCarnetRequeridoMoto() {
		//Creamos el arrayList
		ArrayList<String> coleccion = new ArrayList<String>();
		//A�adimos los valores al ArrayList
		coleccion.add("AM");
		coleccion.add("A1");
		coleccion.add("A2");
		// Devolvemos el arrayList
		return coleccion;
	}
	public static ArrayList<String> getEmisiones() {
		// se leer�an las emisiones de la base de datos
		ArrayList<String> emisiones = new ArrayList<String>();
		emisiones.add("A");
		emisiones.add("B");
		emisiones.add("C");
		return emisiones;
	}
	public static ArrayList<Integer> leeTarjetasClientes() {
		ArrayList<Integer> lista = new ArrayList<Integer>();
		String sql = "SELECT tarjeta FROM cliente";
		PreparedStatement st;
		ResultSet rs;
		int tarjeta;
		
		try {
			// Conectamos con la base de datos
			st = EmpresaDB.conn.prepareStatement(sql);
			//Ejecutamos la instrucción SQL
			rs = st.executeQuery();
			
			while (rs.next())
			{
				tarjeta = rs.getInt("tarjeta");
				lista.add(tarjeta);
			}
			//Cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException e) {
			// Imprimimos la traza del error
			e.printStackTrace();
		}
		return lista;
	}
}
