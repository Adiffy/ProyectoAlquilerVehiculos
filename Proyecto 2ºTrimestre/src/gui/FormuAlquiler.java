package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesObjetos.Cliente;
import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import clasesObjetos.Vehiculo;

import javax.swing.JLabel;
import javax.swing.JComboBox;
import javax.swing.JTextField;

public class FormuAlquiler extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;
	private JTextField tbCodAlquiler;

	private JComboBox<Cliente> cbClientes;
	private JComboBox<Empleado> cbEmpleados;
	private JComboBox<Oficina> cbOfi;
	private JComboBox<Vehiculo> cbVehiculo;

	

	/**
	 * Crea el Jframe.
	 */
	public FormuAlquiler() {
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
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
			
			JLabel lblCliente = new JLabel("Cliente");
			lblCliente.setBounds(10, 56, 52, 14);
			panel.add(lblCliente);
			
			cbClientes = new JComboBox<Cliente>();
			cbClientes.setBounds(10, 81, 110, 22);
			panel.add(cbClientes);
			
			JLabel lblEmpleado = new JLabel("Empleado");
			lblEmpleado.setBounds(10, 114, 71, 14);
			panel.add(lblEmpleado);
			
			cbEmpleados = new JComboBox<Empleado>();
			cbEmpleados.setBounds(10, 139, 110, 22);
			panel.add(cbEmpleados);
			
			JLabel lblOfcina = new JLabel("Oficina");
			lblOfcina.setBounds(253, 56, 46, 14);
			panel.add(lblOfcina);
			
			cbOfi = new JComboBox<Oficina>();
			cbOfi.setBounds(253, 81, 110, 22);
			panel.add(cbOfi);
			
			tbCodAlquiler = new JTextField();
			tbCodAlquiler.setBounds(169, 30, 86, 20);
			panel.add(tbCodAlquiler);
			tbCodAlquiler.setColumns(10);
			
			JLabel lblCodAlquiler = new JLabel("C\u00F3digo del alquiler");
			lblCodAlquiler.setBounds(126, 11, 129, 14);
			panel.add(lblCodAlquiler);
			
			JButton btnCreaCliente = new JButton("");
			btnCreaCliente.setBounds(130, 81, 33, 23);
			panel.add(btnCreaCliente);
			
			JButton btnCreaOficina = new JButton("");
			btnCreaOficina.setBounds(373, 81, 33, 23);
			panel.add(btnCreaOficina);
			
			JButton btnCreaEmpleado = new JButton("");
			btnCreaEmpleado.setBounds(130, 139, 33, 23);
			panel.add(btnCreaEmpleado);
			
			JLabel lblVehiculo = new JLabel("Veh\u00EDculo");
			lblVehiculo.setBounds(253, 114, 46, 14);
			panel.add(lblVehiculo);
			
			cbVehiculo = new JComboBox<Vehiculo>();
			cbVehiculo.setBounds(253, 139, 110, 22);
			panel.add(cbVehiculo);
			
			JButton btnCreaVehiculo = new JButton("");
			btnCreaVehiculo.setBounds(373, 139, 33, 23);
			panel.add(btnCreaVehiculo);
		}
		
	}



	private void creaButtonPane(JPanel buttonPane) {
		{
			JButton okButton = new JButton("Aceptar");
			okButton.setActionCommand("Aceptar");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		{
			JButton cancelButton = new JButton("Cancelar");
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
