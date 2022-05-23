package gui;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import accesoADatos.RepositorioCliente;
import clasesObjetos.Cliente;
import exceptions.DNInoValidoException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;
import guiExcepciones.ErrorCarnet;
import metodos.Handlers;
import metodos.MetodoDni;
import javax.swing.JComboBox;

public class FormuCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static Cliente elelegido;
//	private final JPanel contentPanel = new JPanel();
	private static JPanel panelPrincipal = new JPanel();
	private static JPanel panelPrincipal_1;
//	private static JTable TablaCliente;	//Sacamos la tabla cliente	
	
	private static JTextField tbDNI;
	private static JTextField tbNombre;
	private static JTextField tbAp1;
	private static JTextField tbAp2;
	private static JTextField tbtarjeta;
	private static JTextField tblicencia;
	private JComboBox<String> cbCarnet;
	private JComboBox<Integer> cbTarjeta;
	

	/**
	 * Constructor.
	 */
	private FormuCliente() {
		//		yo = this;
		ceclaraAtributosGenerales();
		creaPanelBotones();
		//Creamos los campos que son iguales (TextFields)
		creaCampos_a_rellenar();

		JButton btnBuscar = creaBotonConsulta();
		btnBuscar.setBounds(167, 43, 46, 25);
		panelPrincipal_1.add(btnBuscar);

		creaLabelLicencia();
		creaTbLicencia();
		creaLabelTarjeta();
		creaTbTarjeta();


	}

	private void creaLabelLicencia() {
		JLabel lblLicencia = new JLabel("Carnet de conducir");
		lblLicencia.setBounds(10, 167, 110, 14);
		panelPrincipal_1.add(lblLicencia);
	}

	private void creaTbLicencia() {
		cbCarnet = Handlers.DesplegableLicenciasDeConducir();
		cbCarnet.setBounds(119, 163, 46, 22);
		panelPrincipal_1.add(cbCarnet);
	}

	private void creaLabelTarjeta() {
		JLabel lbltarjeta = new JLabel("Tarjeta de socio");
		lbltarjeta.setBounds(10, 131, 97, 14);
		panelPrincipal_1.add(lbltarjeta);
	}

	private void creaTbTarjeta() {
		cbTarjeta = Handlers.DesplegableTarjetasClientes();
		cbTarjeta.setBounds(117, 127, 46, 22);
		panelPrincipal_1.add(cbTarjeta);
		
		JButton btnNuevaTarjeta = new JButton("");
		btnNuevaTarjeta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO crear una nueva tarjeta   (int)
			}
		});
		btnNuevaTarjeta.setIcon(new ImageIcon(FormuCliente.class.getResource("/16/edit.png")));
		btnNuevaTarjeta.setBounds(167, 126, 24, 23);
		panelPrincipal_1.add(btnNuevaTarjeta);
	}

	private void creaCampos_a_rellenar() {
		creaLabelDNI();		
		creaLabelNombre();
		creaTbDNI();
		creaTbNombre();
		creaLabelApellidos();
		creaTbApellido1();
		creaApellido2();
	}



	private JButton creaBotonConsulta() {
		JButton btnBuscar = new JButton("");	//Botón sin mensaje solo icono de lupa (ICONO DE BUSCAR)
		btnBuscar.setIcon(new ImageIcon("recursos\\\\16\\\\zoom.png"));
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodosGUI.estadoEditar(panelPrincipal);
				Cliente cliente =   TablaCliente.showDialogModal();
				rellenaCliente(cliente);
			}
		});
		return btnBuscar;
	}


	private void ceclaraAtributosGenerales() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 411, 291);
		panelPrincipal_1 = new JPanel();
		panelPrincipal_1.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(panelPrincipal_1);
		panelPrincipal_1.setLayout(null);
	}

	private void creaPanelBotones() {
		JPanel panel = new JPanel();
		panel.setBounds(10, 209, 350, 33);
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelPrincipal_1.add(panel);
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
				//Borramos el Cliente si existe
				rellenaCliente();	//Componemos el Cliente
				switch (JOptionPane.showConfirmDialog(btnBorrar,
						"óEstó seguro de que desea eliminar al Cliente "+elelegido.getNombreCompleto()+"?",
						"Confirme para borrar", JOptionPane.WARNING_MESSAGE))
				{
				case 0:
					if (RepositorioCliente.borraCliente(elelegido.getDni())<0) //Lo mandamos a borrar
					{
						//Si sale -1 es que no ha podido borrar
						JOptionPane.showMessageDialog(btnBorrar, "No se pudo borrar el Cliente de DNI "+elelegido.getDni(),"Cliente al borrar",JOptionPane.ERROR_MESSAGE);
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
				//Creamos el Cliente
				rellenaCliente();
//				RepositorioCliente.insertCliente(elelegido);
			}
			});
	}

	private void creaLabelDNI() {
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(22, 11, 63, 14);
		panelPrincipal_1.add(lblDni);
	}

	private void creaLabelNombre() {
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(10, 47, 46, 14);
		panelPrincipal_1.add(lblNombre);
	}


	private void creaApellido2() {
		tbAp2 = new JTextField();
		tbAp2.setEnabled(false);
		tbAp2.setBounds(204, 76, 110, 24);
		panelPrincipal_1.add(tbAp2);
		tbAp2.setColumns(25);
	}

	private void creaTbApellido1() {
		tbAp1 = new JTextField();
		tbAp1.setEnabled(false);
		tbAp1.setBounds(68, 76, 110, 24);
		panelPrincipal_1.add(tbAp1);
		tbAp1.setColumns(25);
	}

	private void creaLabelApellidos() {
		JLabel lblApellidos = new JLabel("Apellidos");
		lblApellidos.setBounds(10, 79, 79, 14);
		panelPrincipal_1.add(lblApellidos);
	}

	private void creaTbNombre() {
		tbNombre = new JTextField();
		tbNombre.setEnabled(false);
		tbNombre.setBounds(58, 43, 97, 25);
		panelPrincipal_1.add(tbNombre);
		tbNombre.setColumns(25);
	}

	private void creaTbDNI() {
		tbDNI = new JTextField();
		tbDNI.setBounds(83, 8, 110, 24);
		panelPrincipal_1.add(tbDNI);
		tbDNI.setColumns(10);
		tbDNI.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// Si la tecla pulsada es Enter
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if (tbDNI.getText().length()==9)
						{
							if (MetodoDni.DNIvalido(tbDNI.getText()))	//Si es un DNI vólido
							{
								//Componemos el Cliente
								rellenaClienteConsultado();
								MetodosGUI.estadoEditar(panelPrincipal);
								tbDNI.setEnabled(false);
								rellenaTextFields();
							}else {
								JOptionPane.showMessageDialog(null, "Longitud del DNI invólida");
							}
						}
				}
			}

		});
	}
	
	/**
	 * El mótodo que rellena emplelegido 
	 * Pero desde la base de datos utilizando el campo DNI
	 */
	private void rellenaClienteConsultado()
	{
		elelegido = RepositorioCliente.leeCliente(tbDNI.getText());
	}
	
	/**
	 * El mótodo que rellena emplelegido (variable utilizada para componer el Cliente que compone el usuario)
	 */
	private void rellenaCliente()
	{
		try {
			//Pasamos el contenido de tbTarjeta a int
			int tarjeta = Integer.parseInt(tbtarjeta.getText());
			elelegido = new Cliente( tbNombre.getText(), tbAp1.getText(), tbAp2.getText(),tbDNI.getText(), tblicencia.getText(),tarjeta);
		} catch (DNInoValidoException e1) {
			ErrorCarnet.showDialog();
		} catch (LongitudCadenaNoValidaException e1) {
			guiExcepciones.LongitudNoValida.showDialog();
		} catch (LicenciaNoValidaException e) {
			//Carnet inválido
			e.printStackTrace();
		}
	}
	private void rellenaCliente(Cliente emple)
	{
		elelegido = RepositorioCliente.leeCliente(emple.getDni());
		rellenaTextFields();
	}
	
	/**
	 * Mótodo que rellena todos los textFields segón el objeto
	 * Cliente (que es estótico) de la clase
	 */
	private void rellenaTextFields() {
		tbDNI.setText(elelegido.getDni());
		tbNombre.setText(elelegido.getNombre());
		tbAp1.setText(elelegido.getApellido1());
		tbAp2.setText(elelegido.getApellido2());
		tblicencia.setText(elelegido.getLicencia());
		tbtarjeta.setText(""+elelegido.getTarjeta());
		
	}
	
	/**
	 * Sobrecarga del constructor que requiere:
	 * @param tituloVentana El tótulo de la ventana (que queremos que tenga)
	 */
	private FormuCliente(String tituloVentana)
	{
		FormuCliente ventanilla = new FormuCliente();
		ventanilla.setTitle(tituloVentana);
	}
	
	public static Cliente showDialog()
	{
		FormuCliente ventana = new FormuCliente();
		ventana.setVisible(true);
	
		return elelegido;
	}
	
	public static Cliente showDialogModal()
	{
		FormuCliente ventana = new FormuCliente();
		ventana.setVisible(true);
		ventana.setModal(true);
	
		return elelegido;
	}
	
	public static Cliente showDialog(String tituloVentana)
	{
		FormuCliente ventana = new FormuCliente(tituloVentana);
		ventana.setVisible(true);
	
		return elelegido;
	}
}
