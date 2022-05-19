package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import accesoADatos.RepositorioEmpleado;
import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import exceptions.DNInoValidoException;
import exceptions.LongitudCadenaNoValidaException;
import metodos.MetodoDni;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import com.toedter.calendar.JDateChooser;

public class FormuEmpleado extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelPrincipal;
	private JTextField tbDNI;
	private JTextField tbNombre;
	private JTextField tbAp1;
	private JTextField tbAp2;
	private JComboBox<Oficina> comboBox;
	public static Empleado elelegido;
//	private JFrame yo;
	private JDateChooser fechaAlta;


	/**
	 * Constructor.
	 */
	private FormuEmpleado() {
//		yo = this;
		ceclaraAtributosGenerales();
		creaPanelBotones();
		//Creamos los campos que son iguales (TextFields)
		creaCampos_a_rellenar();
		//Creamos el ComboBox
		creaComboBoxOficinas();
	JButton btnBuscar = creaBotonConsulta();
	
	btnBuscar.setBounds(167, 43, 46, 25);
	panelPrincipal.add(btnBuscar);
	creaLabelFecha_alta();
	creaTbFechaAlta();

	}

	private void creaCampos_a_rellenar() {
		creaLabelDNI();
		
		creaLabelNombre();

		creaTbDNI();

		creaTbNombre();

		creaLabelApellidos();

		creaTbApellido1();

		creaApellido2();

		creaLabelOficina();
	}

	private void creaTbFechaAlta() {
		fechaAlta = new JDateChooser();
		fechaAlta.setDateFormatString("dd mm yyyy\r\n");
		fechaAlta.setBounds(97, 157, 102, 22);
		panelPrincipal.add(fechaAlta);
	}

	private void creaLabelFecha_alta() {
		JLabel lblFechaAlta = new JLabel("Fecha de alta");
		lblFechaAlta.setBounds(10, 157, 97, 14);
		panelPrincipal.add(lblFechaAlta);
	}

	private JButton creaBotonConsulta() {
		JButton btnBuscar = new JButton("");	//Botón sin mensaje solo icono de lupa (ICONO DE BUSCAR)
		btnBuscar.setIcon(new ImageIcon("recursos\\\\16\\\\zoom.png"));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosGUI.estadoEditar(panelPrincipal);
				Empleado emple = TablaEmpleado.showDialogModal();
				rellenaEmpleado(emple);
			}
		});
		return btnBuscar;
	}

	private void creaComboBoxOficinas() {
		comboBox = MetodosGUI.creaDesplegable();
		comboBox.setBounds(70, 109, 165, 22);
		comboBox.setToolTipText("Elija la oficina correspondiente");
		comboBox.setSelectedIndex(-1);
		panelPrincipal.add(comboBox);
	}

	private void ceclaraAtributosGenerales() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 411, 291);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);

		panelPrincipal.setLayout(null);
	}

	private void creaPanelBotones() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 209, 350, 33);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelPrincipal.add(panel);
		//Creamos los botones
		creaBotonBorrar(panel);
		creaBotonCancelar(panel);
		creaBotonAceptar(panel);
	}

	private void creaBotonCancelar(JPanel panel) {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon("recursos\\16\\cross.png"));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Volvemos a poner el formulario en su estado inicial
				MetodosGUI.estadoInicial(panelPrincipal);
				tbDNI.setEnabled(true);
			}
		});
		panel.add(btnCancelar);
	}

	private void creaBotonBorrar(JPanel panel) {
		JButton btnBorrar = new JButton("Borrar");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Borramos el empleado si existe
				rellenaEmpleado();	//Componemos el Empleado
				switch (JOptionPane.showConfirmDialog(btnBorrar,
						"¿Está seguro de que desea eliminar al empleado "+elelegido.getNombreCompleto()+"?",
						"Confirme para borrar", JOptionPane.WARNING_MESSAGE))
				{
				case 0:
					if (RepositorioEmpleado.borraEmpleado(elelegido.getDni())<0) //Lo mandamos a borrar
					{
						//Si sale -1 es que no ha podido borrar
						JOptionPane.showMessageDialog(btnBorrar, "No se pudo borrar el empleado de DNI "+elelegido.getDni(),"Error al borrar",JOptionPane.ERROR_MESSAGE);
					}else {
						//Lo vaciamos para que se note que lo hemos borrado
						MetodosGUI.estadoInicial(panelPrincipal);
					}
					break;
				case 1:	
					break;
				}
			}
		});
		btnBorrar.setIcon((new ImageIcon("recursos\\16\\recycle_bin.png")));
		panel.add(btnBorrar);
	}

	private void creaBotonAceptar(JPanel panel) {
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.setIcon(new ImageIcon("recursos\\16\\diskette.png"));
		panel.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Creamos el Empleado
				rellenaEmpleado();
//				RepositorioEmpleado.insertEmpleado(elelegido);
			}
			});
	}

	private void creaLabelDNI() {
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(22, 11, 63, 14);
		panelPrincipal.add(lblDni);
	}

	private void creaLabelNombre() {
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 47, 46, 14);
		panelPrincipal.add(lblNombre);
	}

	private void creaLabelOficina() {
		JLabel lblOficina = new JLabel("Oficina");
		lblOficina.setBounds(10, 119, 46, 14);
		panelPrincipal.add(lblOficina);
	}

	private void creaApellido2() {
		tbAp2 = new JTextField();
		tbAp2.setEnabled(false);
		tbAp2.setBounds(204, 76, 110, 24);
		panelPrincipal.add(tbAp2);
		tbAp2.setColumns(25);
	}

	private void creaTbApellido1() {
		tbAp1 = new JTextField();
		tbAp1.setEnabled(false);
		tbAp1.setBounds(68, 76, 110, 24);
		panelPrincipal.add(tbAp1);
		tbAp1.setColumns(25);
	}

	private void creaLabelApellidos() {
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 79, 79, 14);
		panelPrincipal.add(lblApellidos);
	}

	private void creaTbNombre() {
		tbNombre = new JTextField();
		tbNombre.setEnabled(false);
		tbNombre.setBounds(58, 43, 97, 25);
		panelPrincipal.add(tbNombre);
		tbNombre.setColumns(25);
	}

	private void creaTbDNI() {
		tbDNI = new JTextField();
		tbDNI.setBounds(83, 8, 110, 24);
		panelPrincipal.add(tbDNI);
		tbDNI.setColumns(10);
		tbDNI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Si la tecla pulsada es Enter
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if (tbDNI.getText().length()==9)
						{
							if (MetodoDni.DNIvalido(tbDNI.getText()))	//Si es un DNI válido
							{
								//Componemos el empleado
								rellenaEmpleadoConsultado();
								MetodosGUI.estadoEditar(panelPrincipal);
								tbDNI.setEnabled(false);
								rellenaTextFields();
							}else {
								JOptionPane.showMessageDialog(null, "Longitud del DNI inválida");
							}
						}
				}
			}

		});
	}
	
	/**
	 * El método que rellena emplelegido 
	 * Pero desde la base de datos utilizando el campo DNI
	 */
	private void rellenaEmpleadoConsultado()
	{
		elelegido = RepositorioEmpleado.leeEmpleado(tbDNI.getText());
	}
	
	/**
	 * El método que rellena emplelegido (variable utilizada para componer el empleado que compone el usuario)
	 */
	private void rellenaEmpleado()
	{
		try {
			Calendar fecha =fechaAlta.getCalendar();
			GregorianCalendar fecha_alta = new GregorianCalendar(fecha.get(Calendar.YEAR),fecha.get(Calendar.MONTH),fecha.get(Calendar.DAY_OF_MONTH));
			elelegido = new Empleado( tbNombre.getText(), tbAp1.getText(), tbAp2.getText(),tbDNI.getText(), fecha_alta, (Oficina) comboBox.getSelectedItem());
		} catch (DNInoValidoException e1) {
			guiExcepciones.ErrorCarnet.showDialog();
		} catch (LongitudCadenaNoValidaException e1) {
			guiExcepciones.LongitudNoValida.showDialog();
		}
	}
	private void rellenaEmpleado(Empleado emple)
	{
		elelegido = RepositorioEmpleado.leeEmpleado(emple.getDni());
		rellenaTextFields();
	}
	/**
	 * Método que rellena todos los textFields según el objeto
	 * Empleado (que es estático) de la clase
	 */
	private void rellenaTextFields() {
		tbDNI.setText(elelegido.getDni());
		tbNombre.setText(elelegido.getNombre());
		tbAp1.setText(elelegido.getApellido1());
		tbAp2.setText(elelegido.getApellido2());
		int dia = elelegido.getFechaAlta().get(Calendar.DAY_OF_MONTH);
		int mes = elelegido.getFechaAlta().get(Calendar.MONTH);
		int anio = elelegido.getFechaAlta().get(Calendar.YEAR);
		Date fecha = new GregorianCalendar(anio,mes,dia).getTime();
		fechaAlta.setDate(fecha);
		comboBox.setSelectedItem((Oficina) elelegido.getOficina());
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

		