package gui;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import com.toedter.calendar.JDateChooser;

import accesoADatos.RepositorioAux;
import accesoADatos.RepositorioEmpleado;
import accesoADatos.RepositorioOficina;
import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import java.awt.*;
import java.util.ArrayList;

public class MetodosGUI {			

	
	public static void centraVentana(JFrame ventana)
	{
			//Leemos el tama�o de la pantalla donde se ejecuta el programa
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			//Hacemos los c�lculos para centrarlo y cambiamos su posici�n
		ventana.setLocation(dim.width/2-ventana.getSize().width/2, dim.height/2-ventana.getSize().height/2);
	}
	
	public static void centraFormulario(JDialog formulario)
	{
			//Leemos el tama�o de la pantalla donde se ejecuta el programa
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
			//Hacemos los c�lculos para centrarlo y cambiamos su posici�n
		formulario.setLocation(dim.width/2-formulario.getSize().width/2, dim.height/2-formulario.getSize().height/2);
	}
	
	// JCOMBOBOX
	
	public static JComboBox<Oficina> creaDesplegable()
	{
		//Creamos el ComboBox
		JComboBox<Oficina> comboBox = new JComboBox<>();
		DefaultComboBoxModel<Oficina> m = new DefaultComboBoxModel<Oficina>();	//Creamos el Model
		//Intenta meter al DefaultComboBoxModel el ArrayList<Oficina>
		m.addAll(RepositorioOficina.listaOficinas());
		comboBox.setModel(m);	//Metemos el model
		
		
		if (m.getSize()>0)	//Si hay alguno
		{
			comboBox.setSelectedIndex(-1);  	//Lo ponemos en -1 por defecto
		}else {
			comboBox.setSelectedIndex(-1);  //Lo ponemos en -1 (ninguno seleccionado) al no haber
		}
		//Devolvemos el ComboBox
		return comboBox;
	}
	
	public static JComboBox<String> creaDesplegableProv()
	{
		JComboBox<String> combo = new JComboBox<String>(); //Creamos el comboBox vac�o
		DefaultComboBoxModel<String> mod = new DefaultComboBoxModel<String>();	//Creamos el model vac�o
		//Rellenamos el model
		mod.addAll(RepositorioAux.leeProvincias());
		combo.setModel(mod);

		if (mod.getSize()>0)	//Si hay alguno
		{
			combo.setSelectedIndex(0);  	//Lo ponemos en 0 por defecto
		}else {
			combo.setSelectedIndex(-1);  //Lo ponemos en -1 (ninguno seleccionado) al no haber
		}
		//Devolvemos el ComboBox
		return combo;
	}
	
	//JTABLE
	
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
			//Hacemos un vector con el n�mero de columnas +1 (DATOS + OBJETO)
			Object[] datos = new Object[4];
			//Del empleado que toque rellenamos sus datos
			datos[0]=emple.getDni();
			datos[1]=emple.getNombreCompleto();
			datos[2]=emple.getOficina();
			//Una celda extra no-visible donde guardaremos el objeto
			datos[3]=emple;
		}
	
		tableTemp.setModel(model);
		//Le damos tama�o 0 a la columna que contiene el objeto
		tableTemp.getColumnModel().getColumn(3).setPreferredWidth(0);
		return tableTemp;
	}
	
	/*
	public static DefaultTableModel modelTableEmpleado()
	{
		DefaultTableModel model = new DefaultTableModel();
		return model;
	}*/
	
	public static boolean isSelected(Oficina ofi)
	{
		boolean resultado;
			
		resultado = ofi.isDeAeropuerto() ? true : false;
		
		return resultado;
	}
	
	public static void estadoInicial(JPanel panel)
	{
		for (Component co:panel.getComponents())
		{
			boolean jlabel = (co.getClass().getSimpleName().compareToIgnoreCase("JLabel")==0);
			boolean jbutton = (co.getClass().getSimpleName().compareToIgnoreCase("JButton")==0);
			boolean jpanel = (co.getClass().getSimpleName().compareToIgnoreCase("JPanel")==0);
			boolean jtextField = (co.getClass().getSimpleName().compareToIgnoreCase("JTextField")==0);
			boolean jdatechooser = (co.getClass().getSimpleName().compareToIgnoreCase("JDateChooser")==0);
			boolean jtabbedpane = (co.getClass().getSimpleName().compareToIgnoreCase("JTabbedPane")==0); 
			//Si no es un JLABEL ni un JButton ni otro JPanel
			if (!jlabel && !jbutton && !jpanel && !jtabbedpane)
			{
				//Lo deshabilitamos
				co.setEnabled(false);
				if (jtextField)  //Si es un JTextField lo vaciamos
				{
					((JTextField) co).setText("");	//Lo tratamos como JTextField
				}else if (jdatechooser)
				{
					((JDateChooser) co).setDate(null);	//Vaciamos los componentes de fecha
				}
			}
			if (jpanel)	//Si es un panel que se meta dentro 
			{
				estadoInicial((JPanel) co);	//Recursividad
			}
		}
	}
	
	public static void estadoEditar(JPanel panel)
	{
		for (Component co:panel.getComponents())
		{
			boolean jlabel = (co.getClass().getSimpleName().compareToIgnoreCase("JLabel")==0);
			boolean jbutton = (co.getClass().getSimpleName().compareToIgnoreCase("JButton")==0);
			boolean jpanel = (co.getClass().getSimpleName().compareToIgnoreCase("JPanel")==0);
			//Si no es un JLABEL ni un JButton ni otro JPanel
			if (!jlabel && !jbutton && !jpanel)
			{
				co.setEnabled(true);
			}
			if (jpanel)	//Si es un panel que se meta dentro 
			{
				estadoEditar((JPanel) co);	//Recursividad
			}
		}
	}
}
