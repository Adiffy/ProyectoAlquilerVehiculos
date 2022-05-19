package accesoADatos;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import clasesObjetos.Cliente;
import exceptions.DNInoValidoException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;

public class RepositorioCliente {

	
	/**
	 * 
	 * @param dni El {@code String} que es el dni del cliente
	 * @return El DNI cuyo DNI es dado
	 */
	public static Cliente leeCliente(String dni) 
	{
		PreparedStatement st = null;
		ResultSet rs;
		Cliente cli = null;	//Sacamos todos los atributos
		//Los generales de -> PERSONA
		//Y los espec�ficos de -> EMPLEADO
		String sql="SELECT * FROM Persona P JOIN Cliente C ON P.DNI=C.PERSONA_DNI "
				+ "WHERE P.DNI=UPPER(?)";


		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, dni);
			rs = st.executeQuery();

			while (rs.next())
			{
				//Solo va a devolver 1(porque estamos preguntando por la PK) asi q no hay problema de sobre escritura
				String Dni = rs.getString("dni");
				String nombre = rs.getString("Nombre");
				String ap1 = rs.getString("Ap1");
				String ap2 = rs.getString("Ap2");
				//
				int tarjeta = rs.getInt("tarjeta");
				String carnet = rs.getString("licencia");


				try {		/* Nos cubrimos de los errores de la creaci�n de Empleado */
					cli = new Cliente(nombre, ap1, ap2, Dni, carnet, tarjeta);
				} catch (LongitudCadenaNoValidaException e) {
					// Longitud inv�lida
					JOptionPane.showMessageDialog(null, "Longitud de cadena no v�lida","Error de creaci�n",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (DNInoValidoException e) {
					// DNI no v�lido 
					JOptionPane.showMessageDialog(null, "DNI no v�lido: "+dni,"Error de creaci�n",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} catch (LicenciaNoValidaException e) {
					// Carnet no valido
					JOptionPane.showMessageDialog(null, "Licencia de conducir no v�lida: "+carnet,"Error de creaci�n",JOptionPane.ERROR_MESSAGE);
					e.printStackTrace();
				} 
			}

			//Cerramos la conexi�n
			rs.close();
			st.close();
		} catch (SQLException e) {

			JOptionPane.showMessageDialog(null,
					"Error al intentar leer cliente de Base de Datos. "+"Cliente: "+dni,
					"DataBase error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}

		return cli;
	}
	
	public static int borraCliente(String dni) {
		// TODO 
		return 0;
	}

	public static ArrayList<Cliente> leeClientes() {
		ArrayList<Cliente> lista = new ArrayList<Cliente>();
		String sql = "SELECT persona_dni FROM CLIENTE";
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			
			while (rs.next())
			{
				String dni = rs.getString("persona_dni");
				lista.add(leeCliente(dni));
			}
			
		} catch (SQLException e) {
			// error al leer
			e.printStackTrace();
		}
		
		return lista;
	}

}
