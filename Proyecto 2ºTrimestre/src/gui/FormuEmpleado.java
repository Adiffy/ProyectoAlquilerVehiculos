package gui;

import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class FormuEmpleado extends JDialog {

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
		declaraAtributosGenerales();
		creaPanelBotones();
		//Creamos los campos que son iguales (TextFields)
		creaCampos_a_rellenar();
		//Creamos el ComboBox
		creaComboBoxOficinas();
	JButton btnBuscar = creaBotonConsulta();
	
	btnBuscar.setBounds(180, 42, 46, 25);
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
		fechaAlta.setDateFormatString("yyyy/MM/dd");
		fechaAlta.setBounds(91, 163, 110, 24);
		panelPrincipal.add(fechaAlta);
		
		JButton btnAceptar = new JButton("");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Creamos el Empleado
				rellenaEmpleadoConsultado();
				if (elelegido != null)
				{
					rellenaEmpleado();
				}
			}
		});
		btnAceptar.setIcon(new ImageIcon(FormuEmpleado.class.getResource("/16/next.png")));
		btnAceptar.setBounds(203, 7, 23, 23);
		panelPrincipal.add(btnAceptar);
	}

	private void creaLabelFecha_alta() {
		JLabel lblFechaAlta = new JLabel("Fecha de alta");
		lblFechaAlta.setBounds(10, 165, 97, 14);
		panelPrincipal.add(lblFechaAlta);
	}

	private JButton creaBotonConsulta() {
		JButton btnBuscar = new JButton("");	//Bot�n sin mensaje solo icono de lupa (ICONO DE BUSCAR)
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

	private void declaraAtributosGenerales() {
		setTitle("Nuevo empleado");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormuEmpleado.class.getResource("/icons/miniLogo.png")));
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 473, 392);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal);

		panelPrincipal.setLayout(null);
	}

	private void creaPanelBotones() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 295, 437, 47);
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
						"Confirme para borrar", JOptionPane.ERROR_MESSAGE))
				{
				case 0:
					if (RepositorioEmpleado.borraEmpleado(elelegido.getDni())<0) //Lo mandamos a borrar
					{
						//Si sale -1 es que no ha podido borrar
						JOptionPane.showMessageDialog(btnBorrar,
								"No se pudo borrar el empleado de DNI "+elelegido.getDni(),
								"Error al borrar",JOptionPane.ERROR_MESSAGE);
					}else {
						//Lo vaciamos para que se note que lo hemos borrado
						MetodosGUI.estadoInicial(panelPrincipal);
					}
					break;
				case 1:	//No hacemos nada
					break;
				}
			}
		});
		btnBorrar.setIcon((new ImageIcon("recursos\\16\\recycle_bin.png")));
		panel.add(btnBorrar);
	}

	private void creaBotonAceptar(JPanel panel) {
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				rellenaEmpleado();
				if (RepositorioEmpleado.insertEmpleado(elelegido))
				{
					MetodosGUI.estadoEditar(panelPrincipal);
					//Lo vaciamos para que se note que se insertó
				}
			}
		});
		btnAceptar.setIcon(new ImageIcon("recursos\\16\\diskette.png"));
		panel.add(btnAceptar);
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rellenaEmpleado();
				if (RepositorioEmpleado.insertEmpleado(elelegido))
				{
					MetodosGUI.estadoEditar(panelPrincipal);
					//Lo vaciamos para que se note que se insertó
				}
				
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
		lblNombre.setBounds(10, 47, 63, 14);
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
		tbAp2.setBounds(204, 79, 125, 21);
		panelPrincipal.add(tbAp2);
		tbAp2.setColumns(25);
	}

	private void creaTbApellido1() {
		tbAp1 = new JTextField();
		tbAp1.setEnabled(false);
		tbAp1.setBounds(68, 76, 125, 24);
		panelPrincipal.add(tbAp1);
		tbAp1.setColumns(25);
	}

	private void creaLabelApellidos() {
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 81, 79, 14);
		panelPrincipal.add(lblApellidos);
	}

	private void creaTbNombre() {
		tbNombre = new JTextField();
		tbNombre.setEnabled(false);
		tbNombre.setBounds(60, 42, 110, 25);
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
							if (MetodoDni.DNIvalido(tbDNI.getText()))	//Si es un DNI v�lido
							{
								//Componemos el empleado
								rellenaEmpleadoConsultado();
								MetodosGUI.estadoEditar(panelPrincipal);
								tbDNI.setEnabled(false);
								if (elelegido !=null )
								{
									rellenaTextFields();
								}
							}else {
								JOptionPane.showMessageDialog(null, "Longitud del DNI inválida");
							}
						}
				}
			}

		});
	}
	
	/**
	 * El Método que rellena emplelegido 
	 * Pero desde la base de datos utilizando el campo DNI
	 */
	private void rellenaEmpleadoConsultado()
	{
		elelegido = RepositorioEmpleado.leeEmpleado(tbDNI.getText());
	}
	
	/**
	 * El Método que rellena emplelegido (variable utilizada para componer el empleado que compone el usuario)
	 */
	private void rellenaEmpleado()
	{
		try {
			Calendar fecha =fechaAlta.getCalendar();
			int anio = fecha.get(Calendar.YEAR);
			int mes = fecha.get(Calendar.MONTH);
			int dia = fecha.get(Calendar.DAY_OF_MONTH);
			GregorianCalendar fecha_alta = new GregorianCalendar(anio,mes,dia);
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
	 * @param tituloVentana El t�tulo de la ventana (que queremos que tenga)
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

		