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
import javax.swing.border.EmptyBorder;

import clasesObjetos.Empleado;
import metodos.Handlers;

/**
 * Clase que sirve para crear una ventana con la tabla de empleados
 * @author Víctor J. Esquinas García
 *
 */
public class TablaEmpleado extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal;
	private JTable tableBusqueda;
	private static TablaEmpleado yo;
	private static Empleado Emplelegido;
//	private JComboBox<Oficina> comboBox;



	/**
	 * Constructor
	 */
	public TablaEmpleado() {
		creaPanelPrincipal();
		creaPanelBotones();
//		creaComboBox();
		JPanel panelTabla = creaTabla();
		creaScrollPane(panelTabla);
		MetodosGUI.centraFormulario(yo);
		}

		
		
		//Cogemos la fila obtenida
		/*int fila = tableBusqueda.getSelectedRow();
		//Si no ha elegido ninguna
		if (fila<0)
		{
			JOptionPane.showMessageDialog(getParent(), "Debes seleccionar una opci�n", "Error", JOptionPane.ERROR_MESSAGE);
		}else //Si elige una opci�n v�lida
		{
			//La �ltima columna tiene el objeto
			Emplelegido= (Empleado) model.getValueAt(fila, tableBusqueda.getColumnCount()-1);
		}*/


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

		tableBusqueda = Handlers.creaTablaEmpleado();
		return panelTabla;
	}


//	private void creaComboBox() {
//		comboBox = MetodosGUI.creaDesplegable();
//		comboBox.setToolTipText("Elija la oficina correspondiente");
//		comboBox.setEnabled(false);
//		comboBox.setMaximumRowCount(15);
//	}


	private void creaBotonAceptar(JPanel abajo) {
		JButton btnAceptar = new JButton("Seleccionar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Si ha elegido alguna fila
				if (tableBusqueda.getSelectedRow()>=0)
				{
					int num_fila  = tableBusqueda.getSelectedRow();
					
					Emplelegido = (Empleado) tableBusqueda.getValueAt(num_fila, 1);
					yo.setVisible(false);
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
				yo.setVisible(false);	//Lo ocultamos
			}
		});
		abajo.add(btnCancelar);
	}


	private void creaPanelPrincipal() {
		setTitle("Empleados");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TablaEmpleado.class.getResource("/icons/miniLogo.png")));
		yo=this;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 596, 356);
		setResizable(false);
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		setContentPane(panelPrincipal);
	}


	public static Empleado showDialog() {
		// Llamamos al constructor
		TablaEmpleado v = new TablaEmpleado();
		//La mostramos
		v.setVisible(true);
		return Emplelegido;
	}
	
	public static Empleado showDialogModal() {
		// Llamamos al constructor
		TablaEmpleado v = new TablaEmpleado();
		v.setModal(true);
		//La mostramos
		v.setVisible(true);
		return Emplelegido;
	}

}
