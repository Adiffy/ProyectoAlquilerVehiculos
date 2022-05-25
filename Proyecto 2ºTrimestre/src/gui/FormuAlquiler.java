package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoADatos.RepositorioAlquiler;
import accesoADatos.RepositorioEmpleado;
import accesoADatos.RepositorioVehiculo;
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
import metodos.Handlers;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Toolkit;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Date;
import java.awt.event.ItemEvent;
import com.toedter.calendar.JDateChooser;

public class FormuAlquiler extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private JTextField tbCodAlquiler;
	private Alquiler alquilerRealizado;
	private Vehiculo vehiculo;
	private Cliente cliente;
	private Empleado emple;
	private JComboBox<Cliente> cbClientes;
	private JComboBox<Empleado> cbEmpleados;
	private JComboBox<Oficina> cbOfi;
	private JComboBox<Vehiculo> cbVehiculo;
	private JComboBox<Oficina> cbOficinaSalida;

	private JDateChooser tbFechaInicioAlquiler;
	private JLabel tbPrecio;
	private JDateChooser tbFechaPrevistaFinAlquiler;
	private JPanel buttonPane_1;



	/**
	 * Crea el Jframe.
	 */
	public FormuAlquiler() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormuAlquiler.class.getResource("/icons/miniLogo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 401);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		buttonPane_1 = new JPanel();
		buttonPane_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane_1, BorderLayout.SOUTH);
		creaButtonPane(buttonPane_1);
		{
			JButton okButton = new JButton("Aceptar");
			okButton.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/check_mark.png")));
			okButton.addActionListener(new ActionListener() {


				public void actionPerformed(ActionEvent e) {
					//  Realizar el alquiler
						rellenaAlquiler();
						if (RepositorioAlquiler.alquilar(alquilerRealizado))
						{
							MetodosGUI.estadoEditar(contentPane);
							//Lo vaciamos para que se note que se insertó
						}
					
				}

				

			});
			okButton.setActionCommand("Aceptar");
			buttonPane_1.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			creaComboBoxClientes(panel);

			creaLabelEmpleado(panel);

			pideOficinaDevolucion(panel);

			creaTbCodAlquiler(panel);

			creaLabelCodAlquiler(panel);

			creaBotonBusquedaOficina(panel);

			botonCreaEmpleado(panel);

			creaLabelVehiculo(panel);

			botonNuevoVehiculo(panel);

			creaBotonListado(panel);

			creaComboBoxOficinaSalida();
			
			creaComboBoxVehiculo(panel);
			creaComboBoxEmpleadso(panel);
			cbOficinaSalida.setBounds(10, 81, 154, 22);
			panel.add(cbOficinaSalida);

			labelOficina(panel);

			JButton btnCreaOficina_1 = new JButton("");
			btnCreaOficina_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbOfi.setSelectedItem(FormuOficinas.showDialogModal());
				}
			});
			btnCreaOficina_1.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/add.png")));
			btnCreaOficina_1.setBounds(175, 81, 33, 23);
			panel.add(btnCreaOficina_1);

			pideFechas(panel);
			
			creaTbPrecio(panel);

			creaLabelsClientesyPrecio(panel);

			botonNuevoCliente(panel);
		}

	}

	private void botonNuevoCliente(JPanel panel) {
		JButton btnCreaCliente = new JButton("");
		btnCreaCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cliente = FormuCliente.showDialogModal();
			}
		});
		btnCreaCliente.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/add.png")));
		btnCreaCliente.setBounds(175, 192, 33, 25);
		panel.add(btnCreaCliente);
	}

	private void creaLabelsClientesyPrecio(JPanel panel) {
		JLabel lblClientes = new JLabel("Clientes");
		lblClientes.setBounds(10, 169, 71, 14);
		panel.add(lblClientes);

		JLabel lblPrecio = new JLabel("Precio");
		lblPrecio.setLabelFor(tbPrecio);
		lblPrecio.setBounds(434, 228, 60, 14);
		panel.add(lblPrecio);
	}

	private void pideFechas(JPanel panel) {
		tbFechaInicioAlquiler = new JDateChooser();
		tbFechaInicioAlquiler.setDateFormatString("dd-MM-yyyy");
		tbFechaInicioAlquiler.setBounds(10, 253, 105, 20);
		panel.add(tbFechaInicioAlquiler);

		tbFechaPrevistaFinAlquiler = new JDateChooser();
		tbFechaPrevistaFinAlquiler.setDateFormatString("dd-MM-yyyy");
		tbFechaPrevistaFinAlquiler.setBounds(253, 253, 105, 20);
		panel.add(tbFechaPrevistaFinAlquiler);

		JLabel lblFechaInicio = new JLabel("Fecha de inicio del alquiler");
		lblFechaInicio.setBounds(10, 228, 154, 14);
		panel.add(lblFechaInicio);

		JLabel lblFechaFin = new JLabel("Fecha estimada finalizacion");
		lblFechaFin.setBounds(253, 217, 154, 36);
		panel.add(lblFechaFin);
	}

	private void labelOficina(JPanel panel) {
		JLabel lblOficinaDeSalida = new JLabel("Oficina de salida");
		lblOficinaDeSalida.setBounds(10, 56, 154, 14);
		panel.add(lblOficinaDeSalida);
	}

	private void creaComboBoxEmpleadso(JPanel panel) {
		cbEmpleados = new JComboBox<Empleado>();
		cbEmpleados.setBounds(10, 139, 155, 22);
		panel.add(cbEmpleados);
		cbEmpleados.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// Rellenamos el empleado
				emple = (Empleado) cbEmpleados.getSelectedItem();
			}
		});
	}

	private void creaComboBoxVehiculo(JPanel panel) {
		cbVehiculo = new JComboBox<Vehiculo>();
		cbVehiculo.setBounds(253, 139, 154, 22);
		panel.add(cbVehiculo);
		cbVehiculo.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				vehiculo = (Vehiculo) cbVehiculo.getSelectedItem();			
			}

		});
	}

	private void creaComboBoxOficinaSalida() {
		cbOficinaSalida = Handlers.creaDesplegableOficina(); 
		cbOficinaSalida.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//ComboBox de Vehículos
				DefaultComboBoxModel<Vehiculo> model = null;
				try {
					model = new DefaultComboBoxModel<Vehiculo>();
					model.addAll(RepositorioVehiculo.leeTodosLosVehiculos((Oficina) cbOficinaSalida.getSelectedItem()));
					cbVehiculo.setModel(model);
				} catch (LetrasMatriculaNoValidasException 
						| NumeroMatriculaNoValidoException
						| RecargoNoValidoException e1) {
					//
					e1.printStackTrace();
				}
				//ComboBox de empleados
				DefaultComboBoxModel<Empleado> m = null;
				m = new DefaultComboBoxModel<Empleado>();
				m.addAll(RepositorioEmpleado.leeEmpleados((Oficina) cbOficinaSalida.getSelectedItem()));
				cbEmpleados.setModel(m);
			}
		});
	}

	private void creaBotonListado(JPanel panel) {
		JButton btnListaAlquileres = listaAlquileres();
		btnListaAlquileres.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/zoom.png")));
		btnListaAlquileres.setBounds(294, 8, 25, 20);
		panel.add(btnListaAlquileres);
	}

	private JButton listaAlquileres() {
		JButton btnListaAlquileres = new JButton("");
		btnListaAlquileres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				 alquilerRealizado = TablaAlquiler.showDialogModal();
				 rellenaCamposAlquiler();
			}
		});
		return btnListaAlquileres;
	}

	private void botonNuevoVehiculo(JPanel panel) {
		JButton btnCreaVehiculo = new JButton("");
		btnCreaVehiculo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbVehiculo.setSelectedItem(FormuVehiculos.showDialogModal());
			}
		});
		btnCreaVehiculo.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/add.png")));
		btnCreaVehiculo.setBounds(417, 139, 33, 23);
		panel.add(btnCreaVehiculo);
	}

	private void creaLabelVehiculo(JPanel panel) {
		JLabel lblVehiculo = new JLabel("Veh\u00EDculo");
		lblVehiculo.setBounds(253, 114, 138, 14);
		panel.add(lblVehiculo);
	}

	private void botonCreaEmpleado(JPanel panel) {
		JButton btnCreaEmpleado = creaBotonNuevoEmpleado();
		btnCreaEmpleado.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/add.png")));
		btnCreaEmpleado.setBounds(175, 139, 33, 23);
		panel.add(btnCreaEmpleado);
	}

	private JButton creaBotonNuevoEmpleado() {
		JButton btnCreaEmpleado = new JButton("");
		btnCreaEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				cbEmpleados.setSelectedItem(FormuEmpleado.showDialog());
			}
		});
		return btnCreaEmpleado;
	}

	private void creaBotonBusquedaOficina(JPanel panel) {
		JButton btnCreaOficina = creaBotonBusqueda();
		btnCreaOficina.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/add.png")));
		btnCreaOficina.setBounds(417, 81, 33, 23);
		panel.add(btnCreaOficina);
	}

	private void creaComboBoxClientes(JPanel panel) {
		cbClientes = Handlers.creaDesplegableClientes();
		cbClientes.setBounds(10, 194, 154, 22);
		panel.add(cbClientes);
		cbClientes.addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent e) {
				// rellenamos el Cliente
				cliente = (Cliente) cbClientes.getSelectedItem();
			}
		});
	}

	private void creaLabelEmpleado(JPanel panel) {
		JLabel lblEmpleado = new JLabel("Empleado");
		lblEmpleado.setBounds(10, 114, 71, 14);
		panel.add(lblEmpleado);
	}

	private void pideOficinaDevolucion(JPanel panel) {
		creaLabelOficinaDevuelta(panel);

		creaComboBoxOficinaDevuelta(panel);
	}

	private void creaLabelOficinaDevuelta(JPanel panel) {
		JLabel lblOfcina_llegada = new JLabel("Oficina a devolver");
		lblOfcina_llegada.setBounds(253, 56, 154, 14);
		panel.add(lblOfcina_llegada);
	}

	private void creaComboBoxOficinaDevuelta(JPanel panel) {
		cbOfi = Handlers.creaDesplegableOficina();
		cbOfi.setBounds(253, 81, 154, 22);
		panel.add(cbOfi);
	}

	private void creaTbCodAlquiler(JPanel panel) {
		tbCodAlquiler = new JTextField();
		tbCodAlquiler.setBounds(198, 8, 86, 20);
		panel.add(tbCodAlquiler);
		tbCodAlquiler.setColumns(10);
		tbCodAlquiler.addKeyListener(new KeyListener() {

			@Override
			public void keyTyped(KeyEvent e) {
				// Nada

			}

			@Override
			public void keyReleased(KeyEvent e) {
				// Nada

			}

			@Override
			public void keyPressed(KeyEvent e) {					
				// Si la tecla pulsada es Enter
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					//Componemos el alquiler
					rellenaAlquilerConsultado();
					MetodosGUI.estadoEditar(contentPane);
					tbCodAlquiler.setEnabled(false);

				}
			}

		});
	}

	private JButton creaBotonBusqueda() {
		JButton btnCreaOficina = new JButton("");
		btnCreaOficina.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cbOfi.setSelectedItem(FormuOficinas.showDialogModal());
			}
		});
		return btnCreaOficina;
	}

	private void creaLabelCodAlquiler(JPanel panel) {
		JLabel lblCodAlquiler = new JLabel("C\u00F3digo del alquiler");
		lblCodAlquiler.setBounds(58, 11, 150, 14);
		panel.add(lblCodAlquiler);
	}

	private void creaTbPrecio(JPanel panel) {
		tbPrecio = new JLabel();
		tbPrecio.setBounds(434, 253, 60, 20);
		panel.add(tbPrecio);
	}

	private void creaButtonPane(JPanel buttonPane) {
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Lo ponemos como al principio: todo vacío
					MetodosGUI.estadoInicial(contentPane);
					tbCodAlquiler.setEnabled(true);
				}
			});
			
			JButton btnNewButton = new JButton("Borrar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rellenaCamposAlquiler();
					if (RepositorioAlquiler.borraAlquiler(alquilerRealizado))
					{
						//Lo volvemos a poner todo como al principio
						MetodosGUI.estadoEditar(contentPane);
						tbCodAlquiler.setEnabled(true);
					}
				}
			});
			btnNewButton.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/recycle_bin.png")));
			buttonPane_1.add(btnNewButton);
			cancelButton.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/cancel.png")));
			cancelButton.setActionCommand("Cancelar");
			buttonPane.add(cancelButton);
		}
	}
	/**
	 * Rellena el alquiler, buscando en la base de datos el código escrito
	 * por el usuario.
	 */
	private void rellenaAlquilerConsultado() {
		// Buscamos el alquiler
		if (RepositorioAlquiler.leeAlquiler(tbCodAlquiler.getText()) != null)
		{//Si existe lo rellenamos
			alquilerRealizado = RepositorioAlquiler.leeAlquiler(tbCodAlquiler.getText());
			rellenaCamposAlquiler();
		}

	}
	/**
	 * Rellena los campos de alquiler en funcón al alquilerRealizado
	 */
	private void rellenaCamposAlquiler() {
		tbCodAlquiler.setText(alquilerRealizado.getCodigo());
		tbFechaInicioAlquiler.setDate(alquilerRealizado.getFechaInicioAlquiler());
		tbFechaPrevistaFinAlquiler.setDate(alquilerRealizado.getFechaPrevistaFinAlquiler());
		try {
			cbClientes.setSelectedItem((Cliente) alquilerRealizado.getCliente());
		} catch (LicenciaNoValidaException | DNInoValidoException | LongitudCadenaNoValidaException e) {
			// errores de creación del cliente
			e.printStackTrace();
		}
		cbOficinaSalida.setSelectedItem((Oficina) alquilerRealizado.getOficinaRecogida());
		cbOfi.setSelectedItem((Oficina) alquilerRealizado.getOficinaEntrega());
		cbEmpleados.setSelectedItem((Empleado) alquilerRealizado.getEncargado());
		cbVehiculo.setSelectedItem((Vehiculo) alquilerRealizado.getaAlquilar());
		tbPrecio.setText(String.valueOf(alquilerRealizado.getPrecioAlquiler()));
	}

	private void rellenaAlquiler() {
		try {

			alquilerRealizado = new Alquiler(tbCodAlquiler.getText(), (Date) tbFechaInicioAlquiler.getDate(), (Date) tbFechaPrevistaFinAlquiler.getDate(), Double.parseDouble(tbPrecio.getText()), vehiculo, cliente, emple, (Oficina) cbOficinaSalida.getSelectedItem(), (Oficina) cbOfi.getSelectedItem());
			
			int duracion = alquilerRealizado.getNumDiasAprox();
			double PrecioAlquiler = duracion*vehiculo.PrecioAlquiler();
			alquilerRealizado.setPrecioAlquiler(PrecioAlquiler);
			tbPrecio.setText(""+alquilerRealizado.getPrecioAlquiler());

		} catch (FechaNoValidaException | CodigoNoValidoException e1) {
			// error al crear el alquiler
			e1.printStackTrace();
		}
	}
	
	/**
	 * Coge la fecha escrita por el usuario y la convierte en GregorianCalendar
	 * @return	la fecha de inicio del alquiler de tipo {@code GregorianCalendar}
	 */
	/*	private GregorianCalendar getFechaInicioAlquiler() {

		return null;
	}
	 */
	public static void showFrame()
	{
		FormuAlquiler ventana = new FormuAlquiler();
		ventana.setVisible(true);
	}
}
