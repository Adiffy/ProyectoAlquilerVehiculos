package accesoADatos;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import clasesObjetos.Alquiler;
import clasesObjetos.Cliente;
import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import clasesObjetos.Vehiculo;
import exceptions.CodigoNoValidoException;
import exceptions.DNInoValidoException;
import exceptions.FechaNoValidaException;
import exceptions.LetrasMatriculaNoValidasException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;
import exceptions.NumeroMatriculaNoValidoException;
import exceptions.RecargoNoValidoException;

/**
 * Clase más importante del programa, es la encargada de alquilar y de hacer todo lo relacionado 
 * con la clase / entidad ALQUILER
 * @author Victor
 *
 */
public class RepositorioAlquiler {

	/**
	 * Realiza el insert en la tabla Alquiler en la base de datos
	 * @return realizado: Si ha insertado/actualizado el alquiler o no
	 */
	public static boolean alquilar(Alquiler alquiler) {
		//Realizado (SI O NO)
		boolean realizado;
		// Si ya existe hacemos Update, sino INSERT
		if (leeAlquiler(alquiler.getCodigo())!=null)
		{
			realizado = update(alquiler);
		}else {
			realizado = insert(alquiler);
		}
		return realizado;
	}

	private static boolean update(Alquiler alquiler) {
		//Hecho: A priori suponemos que NO se ha podido hacer
		boolean hecho = false;
		String sql = "UPDATE alquiler SET fecha_inicio=?,fechaprevistafin=?,precio=?,dni_cliente=?,dni_empleado=?,matricula_vehiculo=?,oficinaRecogida=?,oficinaDejada=? WHERE codigo ="+alquiler.getCodigo();
		PreparedStatement st;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setDate(1, (Date) alquiler.getFechaInicioAlquiler());
			st.setDate(2, (Date) alquiler.getFechaPrevistaFinAlquiler());
			st.setDouble(3, alquiler.getPrecioAlquiler());
			st.setString(4, alquiler.getCliente().getDni());
			st.setString(5, alquiler.getEncargado().getDni());
			st.setString(6, alquiler.getaAlquilar().getMatricula().toString());
			st.setString(7, alquiler.getOficinaRecogida().getCódigo());
			st.setString(8, alquiler.getOficinaEntrega().getCódigo());
			
			st.executeUpdate();
			st.execute("COMMIT");
			
			//cerramos la conexión
			st.close();
			//Hemos terminado
			hecho = true;
		} catch (SQLException | LicenciaNoValidaException | DNInoValidoException | LongitudCadenaNoValidaException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
			// Error en la base de datos / leer cliente o matricula
			e.printStackTrace();
		}
		return hecho;
	}

	private static boolean insert(Alquiler alquiler) {
		//hecho: A priori NO
		boolean hecho = false;
		String sql = "INSERT INTO alquiler "
				+ "(codigo,fecha_inicio,fechaprevistafin,precio,dni_cliente,matricula_vehiculo,oficinaRecogida,oficinaDejada)"
				+ " VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement st = null;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st =rellenaStatementAlquiler(alquiler, st);
			//Ejecutamos la orden y guardamos los cambios
			st.executeUpdate();
			st.execute("COMMIT");
			
			//Cerramos la conexión
			st.close();
			//Todo hecho
			hecho = true;
		} catch (SQLException | LicenciaNoValidaException | DNInoValidoException 
				| LongitudCadenaNoValidaException | LetrasMatriculaNoValidasException 
				| NumeroMatriculaNoValidoException e) {
			// error al insertar el alquiler
			e.printStackTrace();
		}
		return hecho;
	}

	private static PreparedStatement rellenaStatementAlquiler(Alquiler alquiler, PreparedStatement st)
			throws SQLException, LicenciaNoValidaException, DNInoValidoException, LongitudCadenaNoValidaException,
			LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException {
		st.setString(1, alquiler.getCodigo());
		st.setDate(2, (Date) alquiler.getFechaInicioAlquiler());
		st.setDate(3, (Date) alquiler.getFechaPrevistaFinAlquiler());
		st.setDouble(4, alquiler.getPrecioAlquiler());
		st.setString(5, alquiler.getCliente().getDni());
		st.setString(6, alquiler.getaAlquilar().getMatricula().toString());
		st.setString(7, alquiler.getOficinaRecogida().getCódigo());
		st.setString(8, alquiler.getOficinaEntrega().getCódigo());
		
		return st;
	}

	public static Alquiler leeAlquiler(String codigo) {
		// Hacemos la consulta
		String sql ="SELECT * FROM alquiler WHERE codigo = ?";
		PreparedStatement st;
		ResultSet rs;
		Alquiler al = null;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			st.setString(1, codigo);
			rs = st.executeQuery();
			
			while (rs.next())
			{	//Hallamos los atributos y creamos el Alquiler
				Date fechaInicio = rs.getDate("fecha_inicio");
				Date fechaPrevistaFin = rs.getDate("fechaprevistafin");
				Date fechaFinAlquiler = rs.getDate("fechadevolucion");
				double precio = rs.getDouble("precio");
				String dni = rs.getString("dni_cliente");
				Cliente cli = RepositorioCliente.leeCliente(dni);
				dni = rs.getString("dni_empleado");
				Empleado emple = RepositorioEmpleado.leeEmpleado(dni);
				String matricula = rs.getString("matricula_vehiculo");
				Vehiculo auto = RepositorioVehiculo.lee(RepositorioVehiculo.leeVehiculo(matricula));
				Oficina ofi = RepositorioOficina.leeOficina(rs.getString("oficinaRecogida"));
				Oficina ofiEntrega = RepositorioOficina.leeOficina(rs.getString("oficinaDejada"));
				
				if (fechaFinAlquiler != null)
				{	//Si el alquiler ya terminó
					al = new Alquiler(codigo, fechaInicio, fechaPrevistaFin,fechaFinAlquiler,precio, auto, cli, emple, ofi, ofiEntrega);
				}else {
					al = new Alquiler(codigo, fechaInicio, fechaPrevistaFin,precio, auto, cli, emple, ofi, ofiEntrega);
				}
			}
		} catch (SQLException | FechaNoValidaException | CodigoNoValidoException | RecargoNoValidoException e) {
			// error en la consulta
			e.printStackTrace();
		}
		return al;
	}

	public static ArrayList<Alquiler> leeAlquileres() {
		// Preparamos el ArrayList
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		String sql = "SELECT codigo FROM alquiler";
		PreparedStatement st;
		ResultSet rs;
		
		try {
			st = EmpresaDB.conn.prepareStatement(sql);
			rs = st.executeQuery();
			while (rs.next())
			{
				String cod = rs.getString("codigo");
				alquileres.add(leeAlquiler(cod));
			}
			//cerramos la conexión
			st.close();
			rs.close();
		} catch (SQLException e) {
			// error
			e.printStackTrace();
		}
		
		return alquileres;
	}

	
}
