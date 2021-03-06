package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clasesObjetos.Cliente;
import manejadoresEventos.filtroTablas;
import metodos.Handlers;
import metodos.MetodoDni;

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
		creaFiltro();
		yoMismo = this;
		MetodosGUI.centraFormulario(yoMismo);
		filtroTablas.filtraEnTabla(tablaCliente,tbDni);
	}

	private void creabuttonPane() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}

	private void creaFiltro() {
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			contentPanel.add(panel, BorderLayout.NORTH);
			{
				JLabel lblBusqueda = new JLabel("Buscar: ");
				panel.add(lblBusqueda);
			}
			{
				tbDni = new JTextField();
				panel.add(tbDni);
				tbDni.setColumns(10);
			}
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
		creaPanelB??squeda();
		creaTabla();
	}

	private void creaTabla() {
		tablaCliente = Handlers.creaTablaCliente();
		contentPanel.add(tablaCliente, BorderLayout.CENTER);

	}

	private void creaPanelB??squeda() {

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel, BorderLayout.NORTH);

		JLabel lblDNI = new JLabel("DNI");
		panel.add(lblDNI);

		creaTbDni();
		panel.add(tbDni);
		tbDni.setColumns(10);
		{
			JLabel lblTitulo = new JLabel("Tabla de clientes");
			lblTitulo.setIcon(new ImageIcon(TablaCliente.class.getResource("/16/edit.png")));
			panel.add(lblTitulo);
		}

		tbDni = new JTextField();
		tbDni.setPreferredSize(new Dimension(500, 19));
		tbDni.setBounds(new Rectangle(0, 0, 5000, 0));
		tbDni.setText("");
		panel.add(tbDni);
		tbDni.setColumns(50);
		tbDni.setColumns(50);
		
		JPanel buttonPane = new JPanel();
		contentPanel.add(buttonPane, BorderLayout.SOUTH);
		creaBotonAceptar(buttonPane);
		creaBotonCancelar(buttonPane);

	}
	private void creaBotonAceptar(JPanel abajo) {
		JButton btnAceptar = new JButton("Seleccionar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si ha elegido alguna fila
				if (tablaCliente.getSelectedRow()>=0)
				{
					int num_fila  = tablaCliente.getSelectedRow();
					
					ClienteElegido = (Cliente) tablaCliente.getValueAt(num_fila, 1);
					yoMismo.setVisible(false);
				}
			}
		});
		btnAceptar.setToolTipText("Guarda los cambios realizados");
		abajo.add(btnAceptar);
			
	}



	private void creaBotonCancelar(JPanel abajo) {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yoMismo.setVisible(false);	//Lo ocultamos
			}
		});
		abajo.add(btnCancelar);
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
						if (MetodoDni.DNIvalido(tbDni.getText()))	//Si es un DNI v??lido
						{
							//Componemos el cliente
							//							eligeClienteConsultado();
							tbDni.setEnabled(false);

						}else {
							JOptionPane.showMessageDialog(null, "Longitud del DNI inv??lida");
						}
					}
				}
			}

		});
	}

	//	private void eligeClienteConsultado() {
	//		String dni = tbDni.getText();
	////		Cliente cli = RepositorioCliente.leeCliente(dni);
	////		seleccionaCliente(cli);
	//
	//	}

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
