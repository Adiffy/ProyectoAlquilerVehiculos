package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoADatos.RepositorioCliente;
import clasesObjetos.Cliente;
import metodos.Handlers;
import metodos.MetodoDni;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTable;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class TablaCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private static TablaCliente yoMismo;
	private static Cliente ClienteElegido;
	private JTextField tbDni;
	private JTable tablaCliente;

	/**
	 * Crea el JDialog.
	 */
	public TablaCliente() {
		creaAtributosGenerales();
		creabuttonPane();
		yoMismo = this;
		MetodosGUI.centraFormulario(yoMismo);
	}

	private void creabuttonPane() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
		creaOkButton(buttonPane);
		creaCancelButton(buttonPane);
	}

	private void creaCancelButton(JPanel buttonPane) {
		{
			JButton cancelButton = new JButton("Cancelar");
			cancelButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Ocultamos el formulario
					yoMismo.setVisible(false);
				}
			});
			cancelButton.setActionCommand("Cancelar");
			buttonPane.add(cancelButton);
		}
	}

	private void creaOkButton(JPanel buttonPane) {
		{
			JButton okButton = new JButton("Seleccionar");
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Cuando selecciona un cliente
					ClienteElegido = (Cliente) tablaCliente.getValueAt(tablaCliente.getSelectedRow(),1);
					yoMismo.setVisible(false);
				}
			});
			okButton.setActionCommand("Seleccionar");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
	}

	private void creaAtributosGenerales() {
		setTitle("Clientes");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TablaCliente.class.getResource("/icons/miniLogo.png")));
		setBounds(100, 100, 596, 356);
		setResizable(false);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		creaPanelBúsqueda();
		creaTabla();
	}

	private void creaTabla() {
		tablaCliente = Handlers.creaTablaCliente();
		contentPanel.add(tablaCliente, BorderLayout.CENTER);
	}

	private void creaPanelBúsqueda() {

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel, BorderLayout.NORTH);

		JLabel lblDNI = new JLabel("DNI");
		panel.add(lblDNI);

		creaTbDni();
		panel.add(tbDni);
		tbDni.setColumns(10);
	}

	private void creaTbDni() {
		tbDni = new JTextField();
		tbDni.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//Si la tecla pulsada es el ENTER
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if (tbDni.getText().length()==9)
					{
						if (MetodoDni.DNIvalido(tbDni.getText()))	//Si es un DNI válido
						{
							//Componemos el cliente
							rellenaClienteConsultado();
							tbDni.setEnabled(false);

						}else {
							JOptionPane.showMessageDialog(null, "Longitud del DNI inválida");
						}
					}
				}
			}

		});
	}

	private void rellenaClienteConsultado() {
		String dni = tbDni.getText();
		Cliente cli = RepositorioCliente.leeCliente(dni);
		seleccionaCliente(cli);

	}

	private void seleccionaCliente(Cliente cliente) {
		// Seleccionamos el cliente
		
	}

	public static Cliente showDialog()
	{
		TablaCliente v = new TablaCliente();
		v.setVisible(true);
		return ClienteElegido;
	}

	public static Cliente showDialogModal()
	{
		TablaCliente v = new TablaCliente();
		v.setModal(true);
		v.setVisible(true);
		return ClienteElegido;
	}
}
