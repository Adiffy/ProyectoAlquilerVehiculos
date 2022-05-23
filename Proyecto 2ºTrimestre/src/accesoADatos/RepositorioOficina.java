package accesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import clasesObjetos.Oficina;

public class RepositorioOficina {

	
	/**
	 * Método que devuelve un ArrayList de Oficinas sacadas de la base de datos
	 * @return	el {@code ArrayList<Oficina>} extraído de la consulta
	 * @throws SQLException	Error al realizar la consulta
	 */
	public static ArrayList<Oficina> listaOficinas() 
	{
		String Instruccionsql = "SELECT * FROM OFICINA";		//La consulta para obtener todas las oficinas
		ArrayList<Oficina> lista = new ArrayList<Oficina>();
		Statement st;
		try {
			st = EmpresaDB.conn.prepareStatement(Instruccionsql);
			ResultSet resultados = st.executeQuery(Instruccionsql);
			
			while (resultados.next())	//Mientras avance el cursor
			{
				//Cogemos una oficina
				String codigo = resultados.getString("codigo");
				//La a�adimos al ArrayList
				lista.add(leeOficina(codigo));
			}
			st.close(); 	//Cerramos la conexion
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return lista;
	}
	
	/**
	 * Devuelve una oficina de la base de datos a partir de su c�digo
	 * Por ejemplo: JA01, MA01...
	 * @param codigo El codigo de la oficina. Es una {@code String}
	 * @return	La oficina WHERE codigo = codigo escrito
	 */
	public static Oficina leeOficina(String codigo)
	{
		PreparedStatement st = null;
		ResultSet rs;
		Oficina ofi = null;
		String sql="SELECT * FROM Oficina WHERE codigo=UPPER(?)";
		boolean deAeropuerto = false;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, codigo);
			rs = st.executeQuery();
			
			while (rs.next())
			{
				//Solo va a devolver 1(porque estamos preguntando por la PK) asi q no hay problema de sobre escritura
//				rs.getBoolean("DEAEROPUERTO")
				switch (rs.getString("DEAEROPUERTO"))
				{
				case "T":
					deAeropuerto = true;
					break;
				case "F":
					deAeropuerto = false;
				}
				
				ofi = new Oficina(rs.getString("CODIGO"), rs.getString("DESCRIPCION"), rs.getString("PROVINCIA"), rs.getString("LOCALIDAD"), deAeropuerto);
			}
			//Cerramos la conexi�n
			rs.close();
			st.close();
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null,
				    "Error al intentar leer oficina(de c�digo "+codigo+" de Base de Datos.",
				    "DataBase error",
				    JOptionPane.ERROR_MESSAGE);			
			e.printStackTrace();
		}
		
		return ofi;
	}
	
	public static boolean borraOficina(String codigo)
	{
		boolean borrado = false;	//A priori no est� borrado
		PreparedStatement st = null;
		ResultSet rs;
		String sql="DELETE FROM Oficina WHERE codigo=UPPER(?)";

		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, codigo);
			rs = st.executeQuery();
			
			if (rs.next())
			{
				borrado = true;
			}
			//Guardamos los cambios
			st.executeUpdate("COMMIT");
			//Cerramos la conexi�n
			rs.close();
			st.close();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return borrado;
	}
	
	public static boolean insertOficina(Oficina Ofi)
	{
		boolean ejecutado=false;	//A priori suponemos que no ha podido ejecutarse
		
		//Averiguamos si existe para hacer UPDATE / INSERT
		if (leeOficina(Ofi.getCódigo())!=null)
		{
			update(Ofi);	//Si existe se Actualiza la ya existente
			ejecutado = true;
		}else {		
			insert(Ofi);	//Si no existe la operaci�n ser� un insert
			ejecutado = true;
		}
		return ejecutado;
	}
	
	private static void update(Oficina Ofi)
	{
		String sql="UPDATE oficina SET "+ tuplaDif(Ofi) + " = "+ cambioTupla(Ofi);
		PreparedStatement st = null;
		try {	//Ejecutamos la consulta
			st = EmpresaDB.conn.prepareStatement(sql);
			//Hallamos los datos de la oficina y los metemos al Statement
			st = datosOficina(Ofi,sql,st);
			st.executeUpdate();
			st.execute("COMMIT");
			st.close();
		}catch (SQLException e) {
			// Imprimimos error en la consola y sacamos ventana de error
			JOptionPane.showMessageDialog(null, "Error al insertar la oficina de c�digo "+Ofi.getCódigo(),"DataBase Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	private static String cambioTupla(Oficina Ofi) {
		//Devolvemos la columna a priori
		Object devolver =  getTupla(Ofi);
		//Pero si es la propiedad buleana deAeropuerto
		if (devolver.getClass().getSimpleName().compareToIgnoreCase("boolean")==0)
		{
			if (Ofi.isDeAeropuerto())
			{
				devolver='T';
			}else {
				devolver='F';
			}
		}
		return devolver.toString();
	}
	
	/**
	 * M�todo que devuelve el nombre de la tupla
	 * que es diferente en base de datos
	 * Se le da el c�digo de la Oficina, consulta en base de datos y 
	 * el m�todo devuelve el nombre de la primera columna que tenga 
	 * un valor diferente
	 */
	private static String tuplaDif(Oficina oficina)
	{
		String nombColumn = null;
		String cod = oficina.getCódigo();	//Hallamos la oficina en BD
		Oficina ofi = leeOficina(cod);
		//Comparamos su atributo diferente
		if (ofi.getDescripción().compareToIgnoreCase(oficina.getDescripción())!=0)
		{
			nombColumn = "Descripcion";
		}else if (ofi.getLocalidad().compareToIgnoreCase(oficina.getLocalidad())!=0)
		{
			nombColumn = "Localidad";
		}else if (ofi.getProvincia().compareToIgnoreCase(oficina.getProvincia())!=0)
		{
			nombColumn = "Provincia";
		}else if(ofi.isDeAeropuerto()==oficina.isDeAeropuerto())
		{
			nombColumn = "DeAeropuerto";
		}
		return nombColumn;
	}
	
	/**
	 * M�todo que devuelve el getter de Oficina que se corresponde
	 * a la tupla diferente
	 * @param oficina la oficina
	 * @return	el nombre del getter
	 */
	private static Object getTupla(Oficina oficina)
	{
		//Lo que vamos a devolver
		Object geter = null;
		switch(tuplaDif(oficina))
		{
		case "Descripcion":
			geter = oficina.getDescripción();
			break;
		case "Localidad":
			geter = oficina.getLocalidad();
			break;
		case "Provincia":
			geter = oficina.getProvincia();
			break;
		case "DeAeropuerto":
			geter = oficina.isDeAeropuerto();
			break;
		}
		return geter;
	}
	
	private static void insert(Oficina Ofi)
	{
		String sql="INSERT INTO oficina VALUES (?,?,?,?,?)";
		PreparedStatement st = null;
		try {	//Ejecutamos la consulta
			st = EmpresaDB.conn.prepareStatement(sql);
			//Hallamos los datos de la oficina y los metemos al Statement
			st = datosOficina(Ofi,sql,st);
			st.execute();
			//Guardamos los cambios
			st.execute("COMMIT");
			//Cerramos la conexion
			st.close();
		}catch (SQLException e) {
			// Imprimimos error en la consola y sacamos ventana de error
			JOptionPane.showMessageDialog(null, "Error al insertar la oficina de c�digo "+Ofi.getCódigo(),"DataBase Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}
	
	private static PreparedStatement datosOficina(Oficina ofi, String sql, PreparedStatement st)
	{
		try {
			st.setString(1, ofi.getCódigo());
			st.setString(2, ofi.getDescripción());
			st.setString(3, ofi.getProvincia());
			st.setString(4, ofi.getLocalidad());
			st.setBoolean(5, ofi.isDeAeropuerto());
		} catch (SQLException e) {
			// Error por consola
			e.printStackTrace();
		}
		return st;
	}
}
