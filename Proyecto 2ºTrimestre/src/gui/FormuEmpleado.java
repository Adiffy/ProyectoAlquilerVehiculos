package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import enums.BusquedaPor;
import exceptions.CarnetRequeridoInvalidoException;
import exceptions.LongitudCadenaNoValidaException;

import javax.swing.JSpinner;
import javax.swing.SpinnerDateModel;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Calendar;

public class FormuEmpleado extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tbDNI;
	private JTextField tbNombre;
	private JTextField tbAp1;
	private JTextField tbAp2;
	private JComboBox<Oficina> comboBox;
	private JSpinner spinner;
	public static Empleado elelegido;


	/**
	 * Constructor.
	 */
	private FormuEmpleado() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 403, 339);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 256, 367, 33);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		contentPane.add(panel);
		
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO borrar el empleado si existe
			}
		});
		btnBorrar.setIcon((new ImageIcon("recursos\\16\\recycle_bin.png")));
		panel.add(btnBorrar);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("recursos\\16\\cross.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO cerrar ventana
				
			}
		});
		panel.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Creamos el Empleado
				try {
					elelegido = new Empleado( tbNombre.getText(), tbAp1.getText(), tbAp2.getText(),tbDNI.getText(), (GregorianCalendar) spinner.getValue(), (Oficina) comboBox.getSelectedItem());
				} catch (CarnetRequeridoInvalidoException e1) {
					guiExcepciones.ErrorCarnet.showDialog();
				} catch (LongitudCadenaNoValidaException e1) {
					guiExcepciones.LongitudNoValida.showDialog();
				}
			}
		});
		btnAceptar.setIcon(new ImageIcon("recursos\\16\\diskette.png"));
		panel.add(btnAceptar);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(10, 32, 63, 14);
		contentPane.add(lblDni);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 69, 46, 14);
		contentPane.add(lblNombre);
		
		tbDNI = new JTextField();
		tbDNI.setBounds(50, 29, 86, 20);
		contentPane.add(tbDNI);
		tbDNI.setColumns(10);
		
		tbNombre = new JTextField();
		tbNombre.setEnabled(false);
		tbNombre.setBounds(60, 65, 97, 22);
		contentPane.add(tbNombre);
		tbNombre.setColumns(10);
		
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 94, 79, 14);
		contentPane.add(lblApellidos);
		
		tbAp1 = new JTextField();
		tbAp1.setEnabled(false);
		tbAp1.setBounds(70, 91, 110, 20);
		contentPane.add(tbAp1);
		tbAp1.setColumns(10);
		
		tbAp2 = new JTextField();
		tbAp2.setEnabled(false);
		tbAp2.setBounds(195, 91, 115, 20);
		contentPane.add(tbAp2);
		tbAp2.setColumns(10);
		
		JLabel lblOficina = new JLabel("Oficina");
		lblOficina.setBounds(10, 133, 46, 14);
		contentPane.add(lblOficina);
		
		//Creamos el ComboBox
		comboBox = MetodosGUI.creaDesplegable();
		comboBox.setBounds(70, 129, 133, 22);
		contentPane.add(comboBox);
		
		JButton btnBuscar = new JButton("");	//Botón sin mensaje solo icono de lupa (ICONO DE BUSCAR)
		btnBuscar.setIcon(new ImageIcon("recursos\\16\\zoom.png"));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				VentanaBusqueda lupa = new VentanaBusqueda(BusquedaPor.EMPLEADO);
				lupa.setVisible(true);
				
				@SuppressWarnings("unused")
				Empleado emple = lupa.Emplelegido; 
				
			}
		}); 
		btnBuscar.setBounds(185, 65, 46, 23);
		contentPane.add(btnBuscar);
		
		JLabel lblFechaAlta = new JLabel("Fecha de alta");
		lblFechaAlta.setBounds(10, 173, 97, 14);
		contentPane.add(lblFechaAlta);
		
		spinner = new JSpinner();
		spinner.setEnabled(false);
		spinner.setModel(new SpinnerDateModel(new Date(1650232800000L), new Date(9241200000L), null, Calendar.DAY_OF_YEAR));
		spinner.setBounds(106, 170, 97, 20);
		contentPane.add(spinner);
	}
	/**
	 * Sobrecarga del constructor que requiere:
	 * @param tituloVentana El título de la ventana (que queremos que tenga)
	 */
	private FormuEmpleado(String tituloVentana)
	{
		FormuEmpleado ventanilla = new FormuEmpleado();
		ventanilla.setTitle(tituloVentana);
	}
	
	public static Empleado showDialog()
	{
		FormuEmpleado ventana = new FormuEmpleado();
		ventana.setVisible(true);
		
		return elelegido;
	}
	public static Empleado showDialog(String tituloVentana)
	{
		FormuEmpleado ventana = new FormuEmpleado(tituloVentana);
		ventana.setVisible(true);
		
		return elelegido;
	}
}
