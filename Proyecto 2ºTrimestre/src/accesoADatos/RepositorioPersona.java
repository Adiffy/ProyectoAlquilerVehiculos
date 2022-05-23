package accesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import clasesObjetos.Persona;
import objetosBD.PersonaBD;

public class RepositorioPersona {

	 
	
	public static PersonaBD leePersona(String dni)
	{
		PreparedStatement st = null;
		ResultSet rs = null;
		PersonaBD p = null;
		String sql="SELECT * FROM Persona WHERE DNI=?";
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, dni);
			rs = st.executeQuery(sql);
			
			while (rs.next())
			{
				String nombre = rs.getString("NOMBRE");
				String ap1 = rs.getString("AP1");
				String ap2 = rs.getString("AP2");
				String DNI = rs.getString("DNI");
				p = new PersonaBD(DNI,nombre,ap1,ap2);
			}
			//Cerramos la conexion
			st.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//Devolvemos el resultado
		return p;
	}
	
	public static void insertPersona(Persona p)
	{
		PreparedStatement st = null;
		String sql="INSERT INTO Persona VALUES (?,?,?,?)";
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, p.getDni());
			st.setString(2, p.getNombre());
			st.setString(3, p.getApellido1());
			st.setString(4, p.getApellido2());
			st.executeUpdate();
		} catch (SQLException e) {
			// Error
			e.printStackTrace();
		}
		
	}
	
	public static void borraPersona(String dni)
	{
		PreparedStatement st;
		ResultSet rs;
		String sql="DELETE FROM PERSONA WHERE DNI =?";
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, dni);
			rs = st.executeQuery(sql);	//Al ejecutar la instrucci�n SQL se elimina la persona
			
			st.close();    // Cerramos la conexi�n
			rs.close();
		} catch (SQLException e) {
			// TODO Ventana error
			e.printStackTrace();
		}
		
	}
}
