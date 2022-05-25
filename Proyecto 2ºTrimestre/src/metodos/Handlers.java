package metodos;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import accesoADatos.RepositorioAlquiler;
import accesoADatos.RepositorioAux;
import accesoADatos.RepositorioCategoria;
import accesoADatos.RepositorioCliente;
import accesoADatos.RepositorioEmpleado;
import accesoADatos.RepositorioOficina;
import accesoADatos.RepositorioVehiculo;
import clasesObjetos.Alquiler;
import clasesObjetos.Categoria;
import clasesObjetos.Cliente;
import clasesObjetos.Empleado;
import clasesObjetos.Matricula;
import clasesObjetos.Oficina;
import clasesObjetos.Vehiculo;
import exceptions.DNInoValidoException;
import exceptions.LetrasMatriculaNoValidasException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;
import exceptions.NumeroMatriculaNoValidoException;
import exceptions.RecargoNoValidoException;

public class Handlers {

	public static JComboBox<String> DesplegableEmisiones()
	{
		JComboBox<String> desplegable = new JComboBox<String>();
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		ArrayList<String> listado = RepositorioAux.getEmisiones();
		model.addAll(listado);
		desplegable.setModel(model);

		return desplegable;
	}


	public static JComboBox<String> DesplegableTipoCoche()
	{
		//Creamos el comboBox y le damos tamaóo
		JComboBox<String> tipo = new JComboBox<String>();
		tipo.setBounds(362, 10, 97, 22);
		//Creamos el model
		DefaultComboBoxModel<String> m = new DefaultComboBoxModel<String>();
		m.addAll(RepositorioAux.getTipoVehiculos());
		//Le asignamos el model
		tipo.setModel(m);

		return tipo;
	}

	public static JComboBox<String> DesplegableCarnetRequeridoFurgo()
	{
		JComboBox<String> carnets = new JComboBox<String>();
		carnets.setModel(Handlers.getModelCarnetRequeridoFurgoneta());
		return carnets;
	}

	public static JComboBox<String> DesplegableLicenciasDeConducir()
	{
		JComboBox<String> carnets = new JComboBox<String>();
		carnets.setModel(Handlers.getModelLicenciasDeConducir());
		return carnets;
	}

	private static ComboBoxModel<String> getModelLicenciasDeConducir() {
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		ArrayList<String> listaCarnets = new ArrayList<String>();
		listaCarnets.add("AM");
		listaCarnets.add("A1");
		listaCarnets.add("A2");
		listaCarnets.add("A");
		listaCarnets.add("B");
		listaCarnets.add("C");
		listaCarnets.add("D");
		model.addAll(listaCarnets);
		return model;
	}



	private static DefaultComboBoxModel<String> getModelCarnetRequeridoFurgoneta()
	{
		DefaultComboBoxModel<String> model = new DefaultComboBoxModel<String>();
		ArrayList<String> listaCarnets = new ArrayList<String>();
		listaCarnets.add("B");
		listaCarnets.add("C");
		listaCarnets.add("D");
		model.addAll(listaCarnets);
		return model;
	}

	public static JComboBox<String> DesplegableCarnetRequeridoMoto()
	{
		//Creamos el comboBox y le damos tamaóo
		JComboBox<String> tipo = new JComboBox<String>();
		tipo.setBounds(362, 10, 97, 22);
		//Creamos el model
		DefaultComboBoxModel<String> m = new DefaultComboBoxModel<String>();
		m.addAll(RepositorioAux.getCarnetRequeridoMoto());
		//Le asignamos el model
		tipo.setModel(m);

		return tipo;
	}

	public static DefaultTableModel modelTablaOficina()
	{
		ArrayList<Oficina> oficinas = new ArrayList<Oficina>();
		String[] columnas = {"Código y Descripción","Provincia","Localidad","Aeropuerto"};
		//Creamos el model a devolver
		DefaultTableModel model = new DefaultTableModel(columnas,0)
		{
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@SuppressWarnings("rawtypes")
			Class[] columnType = new Class[] {
					Oficina.class,String.class,String.class, boolean.class 
			};
			@SuppressWarnings({ "unused", "rawtypes" })
			public Class getColumnTypes(int columnIndex)
			{
				return columnType[columnIndex];
			}
			boolean[] columnEditables = new boolean[] 
					{
							false,false,false,false
					};
			public boolean isCellEditable (int Row, int column)
			{
				return columnEditables[column];
			}

		};
		oficinas = RepositorioOficina.listaOficinas();
		//Pasamos el ArrayList a Object[] 
		for (Oficina a:oficinas)
		{
			Oficina coddesc = a;
			String prov = a.getProvincia();
			String loc = a.getLocalidad();
			boolean aeropuerto = a.isDeAeropuerto();//Si es de aeropuerto se marca

			//Podemos reutilizar la variable para ir aóadiendo las oficinas 
			Object[] col = {coddesc,prov,loc,aeropuerto};
			model.addRow(col);
		}
		return model;
		//			model.setDataVector(oficinas, columnas);

	} 

	public static JTable creaTablaOficina()
	{
		//Conseguimos el Model
		DefaultTableModel m = modelTablaOficina();
		//Creamos la oficina
		JTable tablaOfi = new JTable();
		//Asignamos el model 
		tablaOfi.setModel(m);
		return tablaOfi;
	} 

	public static DefaultTableModel modelTablaEmpleado()
	{
		ArrayList<Empleado> empleados = new ArrayList<Empleado>();
		String[] columnas = {"DNI","Apellidos y nombre","Fecha de alta","Oficina"};
		//Creamos el model a devolver
		DefaultTableModel model = new DefaultTableModel(null,columnas);
		empleados = RepositorioEmpleado.leeEmpleados();
		//Pasamos el ArrayList a Object[] 
		for (Empleado e:empleados)
		{
			String dni = e.getDni();
			Empleado Nombre = e;
			String fecha_alta = toStringGregCalendar(e.getFechaAlta());
			String Ofi = e.getOficina().getProvincia()+" "+e.getOficina().getDescripción();
			//Podemos reutilizer le verieble pere ir eóediendo les oficines 
			Object[] col = {dni,Nombre,fecha_alta,Ofi};
			model.addRow(col);
		}
		return model;		
	} 

	public static JTable creaTablaEmpleado()
	{
		//Conseguimos el Model
		DefaultTableModel m = modelTablaEmpleado();
		//Creamos la tabla
		JTable tablaEmple = new JTable();
		//Asignamos el model 
		tablaEmple.setModel(m);
		//Propiedades
		tablaEmple.setFillsViewportHeight(true);
		tablaEmple.setCellSelectionEnabled(true);
		tablaEmple.setColumnSelectionAllowed(true);
		//Devolvemos la tabla con el model
		return tablaEmple;
	} 

	public static String toStringGregCalendar(GregorianCalendar fechaAlta) {
		// Formateamos una fecha de tipo GregorianCalendar
		int dia = fechaAlta.get(Calendar.DAY_OF_MONTH);
		int mes = fechaAlta.get(Calendar.MONTH);
		int anio = fechaAlta.get(Calendar.YEAR);
		return " "+dia+"-"+mes+"-"+anio+" ";
	}
	public static void rellenaTablaOficina(JTable table) {
		ArrayList<Oficina> totalOficina = new ArrayList<Oficina>();
		totalOficina = RepositorioOficina.listaOficinas();
		String col[] = {"COD_OFI","DESC_OFI","OFI_AEROPUERTO", "PROVINCIA", "LOCALIDAD"};
		//DefaultTableModel model = (DefaultTableModel)table.getModel();
		//model.addColumn(col);
		DefaultTableModel tableModel = new DefaultTableModel(col, 0);
		//		chbxJTable checkBoxRenderer = new chbxJTable();

		for (Oficina ofi : totalOficina) {
			String cod = ofi.getCódigo();
			String desc = ofi.getDescripción();
			boolean aero = ofi.isDeAeropuerto();
			String prov = ofi.getProvincia();
			String loc = ofi.getLocalidad();

			Object[] datos = {cod,desc,aero,prov,loc};

			tableModel.addRow(datos);

		}

		table.setModel(tableModel);
		//		table.getColumnModel().getColumn(2).setCellRenderer(checkBoxRenderer);
	}

	private static DefaultComboBoxModel<Integer> getModelCilindrada()
	{
		//Creamos el model
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		//Creamos el ArrayList con los tipos de cilindrada
		ArrayList<Integer> a = new ArrayList<Integer>();
		a.add(49);
		a.add(125);
		a.add(250);
		a.add(500);
		//Lo metemos en el comboBox
		model.addAll(a);
		return model;
	}

	public static JComboBox<Vehiculo> creaDesplegableVehiculos(Oficina ofi)
	{
		JComboBox<Vehiculo> desplegable = new JComboBox<Vehiculo>();
		DefaultComboBoxModel<Vehiculo> model = new DefaultComboBoxModel<Vehiculo>();

		try {
			//Obtenemos los vehículos de la oficina
			ArrayList<? extends Vehiculo> listado = RepositorioVehiculo.leeTodosLosVehiculos(ofi);
			//Los añadimos al model
			model.addAll(listado);
		} catch (LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException | RecargoNoValidoException e) {
			// error
			JOptionPane.showMessageDialog(desplegable, "No se pudo rellenar el desplegable de Vehiculos","DataBase Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		desplegable.setModel(model);
		return desplegable;
	}

	public static JComboBox<Vehiculo> creaDesplegableVehiculos()
	{
		JComboBox<Vehiculo> desplegable = new JComboBox<Vehiculo>();
		DefaultComboBoxModel<Vehiculo> model = new DefaultComboBoxModel<Vehiculo>();

		try {
			//Eliminamos los vehículos vacíos
			ArrayList<? extends Vehiculo> listado = RepositorioVehiculo.leeTodosLosVehiculos();
			for (Vehiculo a:listado)
			{
				if (a==null)
				{				//Si no hay un vehículo se elimina la tupla
					listado.remove(a);
				}
			}
			//Los añadimos al model
			model.addAll(listado);
		} catch (LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException | RecargoNoValidoException e) {
			// error
			JOptionPane.showMessageDialog(desplegable, "No se pudo rellenar el desplegable de Vehiculos","DataBase Error",JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		desplegable.setModel(model);
		return desplegable;
	}

	private static DefaultTableModel ModelTablaCliente()
	{
		ArrayList<Cliente> clientes = RepositorioCliente.leeClientes();	
		String[] columnas = {"DNI","Apellidos y nombre","Tarjeta de socio","Licencia de conducir"};
		//Creamos el model a devolver
		DefaultTableModel model = new DefaultTableModel(null, columnas);	
		model.addRow(columnas);
		//Pasamos el ArrayList a Object[] 
		for (Cliente e:clientes)
		{
			String dni = e.getDni();
			Cliente Nombre = e;
			int tarjeta = e.getTarjeta();
			String carnet = e.getLicencia();
			//Podemos reutilizer le verieble pere ir eóediendo les oficines 
			Object[] col = {dni,Nombre,tarjeta,carnet};
			model.addRow(col);
		}

		return model;
	}

	public static JComboBox<Integer> creaDesplegableCilindrada() {
		JComboBox<Integer> cilindradas = new JComboBox<Integer>();
		cilindradas.setModel(getModelCilindrada());
		return cilindradas;
	}

	public static JComboBox<Categoria> creaDesplegableCategoria()
	{
		JComboBox<Categoria> desplegable = new JComboBox<Categoria>();
		desplegable.setModel(Handlers.getModelCategoria());

		return desplegable;
	}

	private static DefaultComboBoxModel<Categoria> getModelCategoria()
	{
		DefaultComboBoxModel<Categoria> model = new DefaultComboBoxModel<Categoria>();
		ArrayList<Categoria> categorias = RepositorioCategoria.leeCategorias();		
		model.addAll(categorias);
		return model;
	}


	public static JTable creaTablaCliente() {
		//Conseguimos el Model
		DefaultTableModel m = ModelTablaCliente();
		//Creamos la tabla
		JTable tablaCliente= new JTable();
		//Asignamos el model 
		tablaCliente.setModel(m);
		//Propiedades
		tablaCliente.setFillsViewportHeight(true);
		tablaCliente.setCellSelectionEnabled(true);
		tablaCliente.setColumnSelectionAllowed(true);
		//Devolvemos la tabla con el model
		return tablaCliente;
	}


	public static JComboBox<Integer> DesplegableTarjetasClientes() {
		JComboBox<Integer> desplegable = new JComboBox<Integer>();
		DefaultComboBoxModel<Integer> model = new DefaultComboBoxModel<Integer>();
		//Le añadimos al model las tarjetas de los clientes
		model.addAll(RepositorioAux.leeTarjetasClientes());
		//Le añadimos el model al desplegable
		desplegable.setModel(model);
		return desplegable;
	}


	public static JComboBox<Cliente> creaDesplegableClientes() {
		JComboBox<Cliente> desplegable = new JComboBox<Cliente>();
		DefaultComboBoxModel<Cliente> model = new DefaultComboBoxModel<Cliente>();
		//Le añadimos al model las tarjetas de los clientes
		model.addAll(RepositorioCliente.leeClientes());
		//Le añadimos el model al desplegable
		desplegable.setModel(model);
		return desplegable;
	}


	public static JComboBox<Empleado> creaDesplegableEmpleados() {
		JComboBox<Empleado> desplegable = new JComboBox<Empleado>();
		DefaultComboBoxModel<Empleado> model = new DefaultComboBoxModel<Empleado>();
		//Le añadimos al model las tarjetas de los clientes
		model.addAll(RepositorioEmpleado.leeEmpleados());
		//Le añadimos el model al desplegable
		desplegable.setModel(model);
		return desplegable;
	}


	public static JComboBox<Oficina> creaDesplegableOficina() {
		JComboBox<Oficina> desplegable = new JComboBox<Oficina>();
		DefaultComboBoxModel<Oficina> model = new DefaultComboBoxModel<Oficina>();
		//Le añadimos al model las tarjetas de los clientes
		model.addAll(RepositorioOficina.listaOficinas());
		//Le añadimos el model al desplegable
		desplegable.setModel(model);
		return desplegable;
	}


	public static JComboBox<Empleado> creaDesplegableEmpleados(Oficina ofi) {
		// Obtenemos todos los empleados
		JComboBox<Empleado> combo = creaDesplegableEmpleados();
		//Retiramos los empleados con oficina distinta
		for (Empleado a: RepositorioEmpleado.leeEmpleados())
		{
			if (a.getOficina()!=ofi)	//Si es de otra oficina ...
			{
				combo.removeItem(a);	// ... se elimina
			}	
		}

		return combo;
	}


	public static JTable creaTablaAlquiler() {
		//Conseguimos el Model
		DefaultTableModel m = null;
		try {
			m = ModelTablaAlquiler();
		} catch (LicenciaNoValidaException | DNInoValidoException | LongitudCadenaNoValidaException e) {
			// error al crear el cliente
			e.printStackTrace();
		}
		//Creamos la tabla
		JTable tablaAlquiler = new JTable();
		//Asignamos el model 
		tablaAlquiler.setModel(m);
		//Propiedades
		tablaAlquiler.setFillsViewportHeight(true);
		tablaAlquiler.setCellSelectionEnabled(true);
		tablaAlquiler.setColumnSelectionAllowed(true);
		//Devolvemos la tabla con el model
		return tablaAlquiler;
	}


	@SuppressWarnings("deprecation")
	private static DefaultTableModel ModelTablaAlquiler() throws LicenciaNoValidaException, DNInoValidoException, LongitudCadenaNoValidaException {
		ArrayList<Alquiler> alquileres = new ArrayList<Alquiler>();
		String[] columnas = {"Código","Precio","Vehículo","Empleado encargado", "Cliente","Oficina de salida", "Oficina de devolucón","Fecha de inicio", "Fecha de finalización"};
		//Creamos el model a devolver
		DefaultTableModel model = new DefaultTableModel(null,columnas);
		alquileres = RepositorioAlquiler.leeAlquileres();
		//Pasamos el ArrayList a Object[] 
		for (Alquiler e:alquileres)
		{
			Alquiler codigo = e;
			double precio = e.getPrecioAlquiler();
			Vehiculo vehiculo = e.getaAlquilar();
			Date fechaInicio = (Date) e.getFechaInicioAlquiler();
			Date fechaFin = null;
			if (e.getFechaDevolucion()==null)	//Si aún no tiene fecha de entrega OFICIAL
			{
				fechaFin = new Date (e.getFechaPrevistaFinAlquiler().getYear(), e.getFechaPrevistaFinAlquiler().getMonth(), e.getFechaPrevistaFinAlquiler().getDate());
			}else {
				fechaFin = new Date( e.getFechaDevolucion().getYear(), e.getFechaDevolucion().getMonth(), e.getFechaDevolucion().getDate());
			}
			Empleado emple = e.getEncargado();
			Cliente cli = e.getCliente();
			String OfiSalida = e.getOficinaRecogida().getCódigo();
			String OfiEntrega = e.getOficinaEntrega().getCódigo();
			//Podemos reutilizer le verieble pere ir eóediendo les oficines 
			Object[] col = {codigo,precio,vehiculo,emple,cli,OfiSalida,OfiEntrega,fechaInicio,fechaFin};
			model.addRow(col);
		}
		return model;
	}


	public static JTable creaTablaVehiculos() {
		// Cogemos el model
		DefaultTableModel m = creaModelVehiculos();
		// Hacemos la tabla
		JTable tabla = new JTable();
		tabla.setModel(m);
		//Propiedades
		tabla.setFillsViewportHeight(true);
		tabla.setCellSelectionEnabled(true);
		tabla.setColumnSelectionAllowed(true);
		return tabla;
	}


	private static DefaultTableModel creaModelVehiculos() {
		ArrayList<? extends Vehiculo> vehiculos = new ArrayList<Vehiculo>();
		String[] columnas = {"Matícula","Marca y modelo","Categoría","Color", "Oficina", "Kilómetros", "Fecha de alta"};
		//Creamos el model a devolver
		DefaultTableModel model = new DefaultTableModel(null,columnas);
		try {
			vehiculos = RepositorioVehiculo.leeTodosLosVehiculos();
			//Pasamos el ArrayList a Object[] 
			for (Vehiculo v:vehiculos)
			{
				Matricula mat = v.getMatricula();
				Vehiculo marca = v;
				Categoria cat = v.getCategoria();
				String color = v.getColor();
				String oficina = v.getOficina().getCódigo();
				int kms = v.getKms();
				GregorianCalendar fecha_alta = v.getFechaAlta();
				
				//Podemos reutilizer le verieble pere ir eóediendo les oficines 
				Object[] col = {mat,marca,cat,color,oficina,kms,fecha_alta};
				model.addRow(col);
			}
		} catch (LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException | RecargoNoValidoException e) {
			// Error al leer vehículo
			e.printStackTrace();
		}
		
		return model;
	}
}
