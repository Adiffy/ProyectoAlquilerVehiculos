package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoADatos.RepositorioAlquiler;
import clasesObjetos.Alquiler;
import clasesObjetos.Cliente;
import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import clasesObjetos.Vehiculo;
import exceptions.CodigoNoValidoException;
import exceptions.FechaNoValidaException;
import metodos.Handlers;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.sql.Date;
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
	private JComboBox<Cliente> cbClientes;
	private JComboBox<Empleado> cbEmpleados;
	private JComboBox<Oficina> cbOfi;
	private JComboBox<Vehiculo> cbVehiculo;
	private JComboBox<Oficina> cbOficinaSalida;

	private JDateChooser tbFechaInicioAlquiler;
	private JTextField tbPrecio = new JTextField();;
	private JDateChooser tbFechaPrevistaFinAlquiler;



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
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		creaButtonPane(buttonPane);
		{
			JPanel panel = new JPanel();
			contentPane.add(panel, BorderLayout.CENTER);
			panel.setLayout(null);

			cbClientes = Handlers.creaDesplegableClientes();
			cbClientes.setBounds(10, 194, 154, 22);
			panel.add(cbClientes);

			JLabel lblEmpleado = new JLabel("Empleado");
			lblEmpleado.setBounds(10, 114, 71, 14);
			panel.add(lblEmpleado);


			JLabel lblOfcina_llegada = new JLabel("Oficina a devolver");
			lblOfcina_llegada.setBounds(253, 56, 154, 14);
			panel.add(lblOfcina_llegada);

			cbOfi = Handlers.creaDesplegableOficina();
			cbOfi.setBounds(253, 81, 154, 22);
			panel.add(cbOfi);

			tbCodAlquiler = new JTextField();
			tbCodAlquiler.setBounds(178, 8, 86, 20);
			panel.add(tbCodAlquiler);
			tbCodAlquiler.setColumns(10);

			JLabel lblCodAlquiler = new JLabel("C\u00F3digo del alquiler");
			lblCodAlquiler.setBounds(79, 11, 129, 14);
			panel.add(lblCodAlquiler);

			JButton btnCreaOficina = new JButton("");
			btnCreaOficina.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbOfi.setSelectedItem(FormuOficinas.showDialogModal());
				}
			});
			btnCreaOficina.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/add.png")));
			btnCreaOficina.setBounds(461, 81, 33, 23);
			panel.add(btnCreaOficina);

			JButton btnCreaEmpleado = new JButton("");
			btnCreaEmpleado.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					cbEmpleados.setSelectedItem(FormuEmpleado.showDialog());
				}
			});
			btnCreaEmpleado.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/add.png")));
			btnCreaEmpleado.setBounds(175, 139, 33, 23);
			panel.add(btnCreaEmpleado);

			JLabel lblVehiculo = new JLabel("Veh\u00EDculo");
			lblVehiculo.setBounds(253, 114, 138, 14);
			panel.add(lblVehiculo);

			JButton btnCreaVehiculo = new JButton("");
			btnCreaVehiculo.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbVehiculo.setSelectedItem(FormuVehiculos.showDialogModal());
				}
			});
			btnCreaVehiculo.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/add.png")));
			btnCreaVehiculo.setBounds(461, 139, 33, 23);
			panel.add(btnCreaVehiculo);

			JButton btnListaAlquileres = new JButton("");
			btnListaAlquileres.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/zoom.png")));
			btnListaAlquileres.setBounds(274, 8, 25, 20);
			panel.add(btnListaAlquileres);

			cbOficinaSalida = Handlers.creaDesplegableOficina(); 
			cbOficinaSalida.setBounds(10, 81, 154, 22);
			panel.add(cbOficinaSalida);
			cbOficinaSalida.addItemListener(new ItemListener() {
				//Cuando se elige una oficina de trabajo
				public void itemStateChanged(ItemEvent e) {
					//ComboBox de Vehiculos
					cbVehiculo = Handlers.creaDesplegableVehiculos((Oficina) cbOficinaSalida.getSelectedItem());
					cbVehiculo.setBounds(253, 139, 200, 22);
					panel.add(cbVehiculo);
					//ComboBox de empleados
					cbEmpleados = Handlers.creaDesplegableEmpleados((Oficina) cbOficinaSalida.getSelectedItem());
					cbEmpleados.setBounds(10, 139, 154, 22);
					panel.add(cbEmpleados);
				}
			});

			JLabel lblOficinaDeSalida = new JLabel("Oficina de salida");
			lblOficinaDeSalida.setBounds(10, 56, 154, 14);
			panel.add(lblOficinaDeSalida);

			JButton btnCreaOficina_1 = new JButton("");
			btnCreaOficina_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					cbOfi.setSelectedItem(FormuOficinas.showDialogModal());
				}
			});
			btnCreaOficina_1.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/add.png")));
			btnCreaOficina_1.setBounds(175, 81, 33, 23);
			panel.add(btnCreaOficina_1);

			tbFechaInicioAlquiler = new JDateChooser();
			tbFechaInicioAlquiler.setBounds(10, 253, 94, 20);
			panel.add(tbFechaInicioAlquiler);

			tbFechaPrevistaFinAlquiler = new JDateChooser();
			tbFechaPrevistaFinAlquiler.setBounds(253, 253, 94, 20);
			panel.add(tbFechaPrevistaFinAlquiler);

			JLabel lblFechaInicio = new JLabel("Fecha de inicio del alquiler");
			lblFechaInicio.setBounds(10, 228, 124, 14);
			panel.add(lblFechaInicio);

			JLabel lblFechaFin = new JLabel("Fecha estimada finalizacion");
			lblFechaFin.setBounds(253, 217, 154, 36);
			panel.add(lblFechaFin);
			
			tbPrecio.setBounds(434, 23, 60, 20);
			panel.add(tbPrecio);
			tbPrecio.setColumns(6);
			
			JLabel lblClientes = new JLabel("Clientes");
			lblClientes.setBounds(10, 169, 71, 14);
			panel.add(lblClientes);
		}

	}



	private void creaButtonPane(JPanel buttonPane) {
		{
			JButton okButton = new JButton("Aceptar");
			okButton.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/check_mark.png")));
			okButton.addActionListener(new ActionListener() {
				

				public void actionPerformed(ActionEvent e) {
					//TODO realizar el alquiler
					if (RepositorioAlquiler.leeAlquiler(tbCodAlquiler.getText())==null)
					{
						rellenaAlquiler();
						RepositorioAlquiler.alquilar(alquilerRealizado);
					}

				}

				private void rellenaAlquiler() {
					try {
						alquilerRealizado = new Alquiler(tbCodAlquiler.getText(),(Date) tbFechaInicioAlquiler.getDate() , (Date) tbFechaPrevistaFinAlquiler.getDate(),Double.parseDouble(tbPrecio.getText()), (Vehiculo) cbVehiculo.getSelectedItem(), (Cliente) cbClientes.getSelectedItem(), (Oficina) cbOficinaSalida.getSelectedItem(), (Oficina) cbOfi.getSelectedItem());
					} catch (FechaNoValidaException | CodigoNoValidoException e1) {
						// error al crear el alquiler
						e1.printStackTrace();
					}
				}

			});
			okButton.setActionCommand("Aceptar");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO todo vacío
				}
			});
			cancelButton.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/cancel.png")));
			cancelButton.setActionCommand("Cancelar");
			buttonPane.add(cancelButton);
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
