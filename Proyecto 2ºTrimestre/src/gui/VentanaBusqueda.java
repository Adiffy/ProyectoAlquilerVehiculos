package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import accesoADatos.RepositorioEmpleado;
import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
import enums.BusquedaPor;

/**
 * Clase que sirve para crear una ventana de b�squeda 'gen�rica'
 * @author V�ctor J. Esquinas Garc�a
 *
 */
public class VentanaBusqueda extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panelPrincipal;
	private JTable tableBusqueda;
	private VentanaBusqueda yo;
	private DefaultTableModel model;
	public Empleado Emplelegido;
	private JTable tablaDatos;
	private JTextField textFieldDNI;
	private JTextField textFieldAp1;
	private JTextField textFieldAp2;
	private JTextField textFieldNombre;
	private JComboBox<Oficina> comboBox;
	private JSpinner spinnerFechaAlta;
	

	/**
	 * Constructor
	 */
	public VentanaBusqueda(BusquedaPor a) {
		yo=this;
		setModal(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 513, 320);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
		
		JPanel abajo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) abajo.getLayout();
		flowLayout.setAlignment(FlowLayout.RIGHT);
		panelPrincipal.add(abajo, BorderLayout.SOUTH);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				yo.setVisible(false);	//Lo ocultamos
			}
		});
		abajo.add(btnCancelar);
		
		JButton btnAceptar = new JButton("Seleccionar");
		btnAceptar.setToolTipText("Guarda los cambios realizados");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//TODO INSERT / UPDATE Empleado/cliente..
			}
		});
		abajo.add(btnAceptar);
		
		//Según la tabla que se quiera 
		switch (a)
		{
		case EMPLEADO:
			model = new DefaultTableModel();
			tableBusqueda = MetodosGUI.creaTablaEmpleado();
			break;
		case CLIENTE:	
			
			break;
		case VEHICULO_COMBUSTION:
			
			break;
		case VEHICULO_ELECTRICO:
			
			break;
		default:
			//Imposible que esto pase
			break;
		
		}
		
		//Ponemos la tabla vis�ble en el JPanel
		panelPrincipal.add(tableBusqueda, BorderLayout.CENTER);
		
		JPanel panel_superior = new JPanel();
		panelPrincipal.add(panel_superior, BorderLayout.NORTH);
		
		JLabel lblNewLabel_1 = new JLabel("Empleado");
		panel_superior.add(lblNewLabel_1);
		
		JPanel lateral_izq = new JPanel();
		panelPrincipal.add(lateral_izq, BorderLayout.WEST);
		lateral_izq.setLayout(new BorderLayout(0, 0));
		
		JPanel panelDNI = new JPanel();
		lateral_izq.add(panelDNI, BorderLayout.NORTH);
		
		JLabel lblDni = new JLabel("DNI");
		panelDNI.add(lblDni);
		
		textFieldDNI = new JTextField();
		textFieldDNI.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cuando presiona el Intro buscamos el empleado por su DNI
				Emplelegido = RepositorioEmpleado.leeEmpleado(textFieldDNI.getText());
				
				if (Emplelegido != null)	//Si existe
				{
					textFieldNombre.setText(Emplelegido.getNombre());
					textFieldAp1.setText(Emplelegido.getApellido1());
					textFieldAp2.setText(Emplelegido.getApellido2());
					spinnerFechaAlta.setModel(null);
					comboBox.setSelectedItem((Oficina) Emplelegido.getOficina());
				}
			}
		});
		textFieldDNI.setToolTipText("Escriba un DNI v\u00E1lido");
		panelDNI.add(textFieldDNI);
		textFieldDNI.setColumns(12);
		
		JPanel panel = new JPanel();
		lateral_izq.add(panel, BorderLayout.CENTER);
		panel.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNombre = new JPanel();
		panel.add(panelNombre, BorderLayout.NORTH);
		
		JLabel lblNombre = new JLabel("Nombre");
		panelNombre.add(lblNombre);
		
		textFieldNombre = new JTextField();
		textFieldNombre.setEnabled(false);
		panelNombre.add(textFieldNombre);
		textFieldNombre.setColumns(15);
		
		JPanel panelContApellidos = new JPanel();
		panel.add(panelContApellidos, BorderLayout.CENTER);
		panelContApellidos.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_Apellidos = new JPanel();
		panelContApellidos.add(panel_Apellidos, BorderLayout.NORTH);
		
		JLabel lblAp1 = new JLabel("Primer apellido");
		panel_Apellidos.add(lblAp1);
		
		textFieldAp1 = new JTextField();
		textFieldAp1.setEnabled(false);
		panel_Apellidos.add(textFieldAp1);
		textFieldAp1.setColumns(10);
		
		JLabel lblAp2 = new JLabel("Segundo apellido");
		panel_Apellidos.add(lblAp2);
		
		textFieldAp2 = new JTextField();
		textFieldAp2.setEnabled(false);
		panel_Apellidos.add(textFieldAp2);
		textFieldAp2.setColumns(10);
		
		JPanel panelContOfiFechaAlta = new JPanel();
		panelContApellidos.add(panelContOfiFechaAlta, BorderLayout.CENTER);
		panelContOfiFechaAlta.setLayout(new BorderLayout(0, 0));
		
		JPanel panelFechaAlta = new JPanel();
		panelContOfiFechaAlta.add(panelFechaAlta, BorderLayout.NORTH);
		
		JLabel lblFechaAlta = new JLabel("Fecha de alta");
		panelFechaAlta.add(lblFechaAlta);
		
		spinnerFechaAlta = new JSpinner();	//TODO cambiar por JCalendar
		spinnerFechaAlta.setToolTipText("Fecha de alta del trabajador");
		spinnerFechaAlta.setModel(new SpinnerDateModel(new Date(1650232800000L), new Date(9241200000L), null, Calendar.DAY_OF_YEAR));
		spinnerFechaAlta.setEnabled(false);
		panelFechaAlta.add(spinnerFechaAlta);
		
		JPanel panel_Oficina = new JPanel();
		panelContOfiFechaAlta.add(panel_Oficina, BorderLayout.CENTER);
		panel_Oficina.setLayout(new BorderLayout(0, 0));
		
		JPanel panelOfi = new JPanel();
		panel_Oficina.add(panelOfi, BorderLayout.CENTER);
		panelOfi.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JLabel lblOficina = new JLabel("Oficina");
		panelOfi.add(lblOficina);
		
		comboBox = MetodosGUI.creaDesplegable();
		comboBox.setToolTipText("Elija la oficina correspondiente");
		panelOfi.add(comboBox);
		comboBox.setEnabled(false);
		comboBox.setMaximumRowCount(15);
		
		JPanel panel_FechaAlta = new JPanel();
		panelOfi.add(panel_FechaAlta);
		
		JPanel panelTabla = new JPanel();
		panelPrincipal.add(panelTabla, BorderLayout.CENTER);
		
		tablaDatos  = tableBusqueda;	//TODO hacer bien la tabla con datos de BD
	
		panelTabla.add(tablaDatos);
		
		//Cogemos la fila obtenida
		int fila = tableBusqueda.getSelectedRow();
		//Si no ha elegido ninguna
		if (fila<0)
		{
			JOptionPane.showMessageDialog(getParent(), "Debes seleccionar una opci�n", "Error", JOptionPane.ERROR_MESSAGE);
		}else //Si elige una opci�n v�lida
		{
			//La �ltima columna tiene el objeto
			Emplelegido= (Empleado) model.getValueAt(fila, tableBusqueda.getColumnCount()-1);
		}
	}

}
