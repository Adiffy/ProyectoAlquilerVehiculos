package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import clasesObjetos.Vehiculo;
import metodos.Handlers;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.Toolkit;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FormuAlquiler extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField tbCodAlquiler;

//	private JComboBox<Cliente> cbClientes;
	private JComboBox<Empleado> cbEmpleados;
	private JComboBox<Oficina> cbOfi;
	private JComboBox<Vehiculo> cbVehiculo;
	private JComboBox<Oficina> cbOficinaSalida;

	

	/**
	 * Crea el Jframe.
	 */
	public FormuAlquiler() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormuAlquiler.class.getResource("/icons/miniLogo.png")));
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 530, 325);
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
			
//			cbClientes = Handlers.creaDesplegableClientes();
//			cbClientes.setBounds(10, 81, 154, 22);
//			panel.add(cbClientes);
			
			JLabel lblEmpleado = new JLabel("Empleado");
			lblEmpleado.setBounds(10, 114, 71, 14);
			panel.add(lblEmpleado);
			
			cbEmpleados = Handlers.creaDesplegableEmpleados();
			cbEmpleados.setBounds(10, 139, 154, 22);
			panel.add(cbEmpleados);
			
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
			cbOficinaSalida.addItemListener(new ItemListener() {
				public void itemStateChanged(ItemEvent e) {
					cbVehiculo = Handlers.creaDesplegableVehiculos((Oficina) cbOficinaSalida.getSelectedItem());
					cbVehiculo.setBounds(253, 139, 200, 22);
					panel.add(cbVehiculo);
				}
			});
			
			cbOficinaSalida.setBounds(10, 81, 154, 22);
			panel.add(cbOficinaSalida);
			
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
		}
		
	}



	private void creaButtonPane(JPanel buttonPane) {
		{
			JButton okButton = new JButton("Aceptar");
			okButton.setIcon(new ImageIcon(FormuAlquiler.class.getResource("/16/check_mark.png")));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//TODO realizar el alquiler
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
	
	public static void showFrame()
	{
		FormuAlquiler ventana = new FormuAlquiler();
		ventana.setVisible(true);
	}
}
