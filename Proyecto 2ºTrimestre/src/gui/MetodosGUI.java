package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import accesoADatos.RepositorioEmpleado;
import accesoADatos.RepositorioOficina;
import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MetodosGUI {			

	
	public static void centraVentana(JFrame ventana)
	{
			//Leemos el tamaño de la pantalla donde se ejecuta el programa
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			//Hacemos los cálculos para centrarlo y cambiamos su posición
		ventana.setLocation(dim.width/2-ventana.getSize().width/2, dim.height/2-ventana.getSize().height/2);
	}
	
	// JCOMBOBOX
	
	public static JComboBox<Oficina> creaDesplegable()
	{
		//Creamos el ComboBox
		JComboBox<Oficina> comboBox = new JComboBox<>();
		DefaultComboBoxModel<Oficina> m = new DefaultComboBoxModel<Oficina>();	//Creamos el Model
		try {
			//Intenta meter al DefaultComboBoxModel el ArrayList<Oficina>
			m.addAll(RepositorioOficina.listaOficinas());
		} catch (SQLException e) {
			// Error
			e.printStackTrace();
		}
		comboBox.setModel(m);	//Metemos el model
		
		
		if (m.getSize()>0)	//Si hay alguno
		{
			comboBox.setSelectedIndex(0);  	//Lo ponemos en 0 por defecto
		}else {
			comboBox.setSelectedIndex(-1);  //Lo ponemos en -1 (ninguno seleccionado) al no haber
		}
		//Devolvemos el ComboBox
		return comboBox;
	}
	
	public static JTable creaTablaEmpleado()
	{
		JTable tableTemp = new JTable();
		tableTemp.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DefaultTableModel model =  new DefaultTableModel();
		model.addColumn("DNI");
		model.addColumn("Apellidos y nombre");
		model.addColumn("Oficina");
		model.addColumn("");	//Columna para el objeto
		ArrayList<Empleado> listado = RepositorioEmpleado.leeEmpleados();
		for (Empleado emple:listado)
		{
			//Hacemos un vector con el número de columnas +1 (DATOS + OBJETO)
			Object[] datos = new Object[4];
			//Del empleado que toque rellenamos sus datos
			datos[0]=emple.getDni();
			datos[1]=emple.getNombreCompleto();
			datos[2]=emple.getOficina();
			//Una celda extra no-visible donde guardaremos el objeto
			datos[3]=emple;
		}
	
		tableTemp.setModel(model);
		//Le damos tamaño 0 a la columna que contiene el objeto
		tableTemp.getColumnModel().getColumn(3).setPreferredWidth(0);
		return tableTemp;
	}
	
	/*
	public static DefaultTableModel modelTableEmpleado()
	{
		DefaultTableModel model = new DefaultTableModel();
		return model;
	}*/

}
