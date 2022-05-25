package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clasesObjetos.Vehiculo;
import manejadoresEventos.filtroTablas;
import metodos.Handlers;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

/**
 * Clase que sirve para crear una ventana con la tabla de Vehiculos
 * @author Víctor J. Esquinas García
 *
 */
public class TablaVehiculos extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal;
	private JTable tableBusqueda;

	private JTextField tbDni = new JTextField();
	private static TablaVehiculos yo;
	private static Vehiculo VehiculoElegido;
	//	private JComboBox<Oficina> comboBox;



	/**
	 * Constructor
	 */
	public TablaVehiculos() {
		creaPanelPrincipal();
		JPanel panelTabla = creaTabla();
		creaPanelBotones();
		creaScrollPane(panelTabla);
		creaFiltro();
		MetodosGUI.centraFormulario(yo);
		filtroTablas.filtraEnTabla(tableBusqueda, tbDni);
	}


	private void creaFiltro() {
		{
			JPanel panel = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panelPrincipal.add(panel, BorderLayout.NORTH);
			{
				JLabel lblNewLabel = new JLabel("Buscar: ");
				panel.add(lblNewLabel);
			}
			{
				tbDni = new JTextField();
				panel.add(tbDni);
				tbDni.setColumns(10);
			}
		}
	}


	private void creaPanelBotones() {
		JPanel abajo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) abajo.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelPrincipal.add(abajo, BorderLayout.SOUTH);

		creaBotonCancelar(abajo);

		creaBotonAceptar(abajo);
	}


	private void creaScrollPane(JPanel panelTabla) {
		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);
		scrollPane.setViewportView(tableBusqueda);
	}


	private JPanel creaTabla() {
		JPanel panelTabla = new JPanel();
		panelPrincipal.add(panelTabla, BorderLayout.CENTER);
		panelTabla.setLayout(new BorderLayout(0, 0));

		tableBusqueda = Handlers.creaTablaVehiculos();
		return panelTabla;
	}


	private void creaBotonAceptar(JPanel abajo) {
		JButton btnAceptar = new JButton("Seleccionar");
		btnAceptar.setIcon(new ImageIcon(TablaVehiculos.class.getResource("/16/check_mark.png")));
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si ha elegido alguna fila
				if (tableBusqueda.getSelectedRow()>=0)
				{
					int num_fila  = tableBusqueda.getSelectedRow();

					VehiculoElegido = (Vehiculo) tableBusqueda.getValueAt(num_fila, 1);
					yo.setVisible(false);
				}
			}
		});
		btnAceptar.setToolTipText("Guarda los cambios realizados");
		abajo.add(btnAceptar);


	}



	private void creaBotonCancelar(JPanel abajo) {
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.setIcon(new ImageIcon(TablaVehiculos.class.getResource("/16/cancel.png")));
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yo.setVisible(false);	//Lo ocultamos
			}
		});
		abajo.add(btnCancelar);
	}


	private void creaPanelPrincipal() {
		setTitle("Vehiculos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TablaVehiculos.class.getResource("/icons/miniLogo.png")));
		yo=this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 356);
		setResizable(false);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
	}


	public static Vehiculo showDialog() {
		// Llamamos al constructor
		TablaVehiculos v = new TablaVehiculos();
		//La mostramos
		v.setVisible(true);
		return VehiculoElegido;
	}

	public static Vehiculo showDialogModal() {
		// Llamamos al constructor
		TablaVehiculos v = new TablaVehiculos();
		v.setModal(true);
		//La mostramos
		v.setVisible(true);
		return VehiculoElegido;
	}

}
