package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;

import accesoADatos.RepositorioOficina;
import clasesObjetos.Oficina;
import metodos.Handlers;
import javax.swing.JScrollPane;

/**
 * Clase que sirve para crear una ventana con la tabla de oficinas
 * @author Víctor J. Esquinas García
 *
 */
public class TablaOficina extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel panelPrincipal;
	private JTable tableBusqueda;
	private TablaOficina yo;
	public static Oficina Ofielegida;
	
	/**
	 * Constructor
	 */
	private TablaOficina() {
		setTitle("Oficinas registradas");
		yo=this;
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
				if (tableBusqueda.getSelectedRow()>=0)
				{
					int num_fila  = tableBusqueda.getSelectedRow();
					
					Ofielegida = (Oficina) tableBusqueda.getValueAt(num_fila, 0);
					
					if (RepositorioOficina.insertOficina(Ofielegida))
					{	//Lo ponemos en el estado inicial para que se note que lo hemos insertado / actualizado
						MetodosGUI.estadoInicial(panelPrincipal);
					}
					yo.setVisible(false);
				}
			}
		});
		abajo.add(btnAceptar);

		JPanel panel_superior = new JPanel();
		panelPrincipal.add(panel_superior, BorderLayout.NORTH);

		JLabel lblTitulo = new JLabel("Tabla de Oficinas");
		panel_superior.add(lblTitulo);

		

		JPanel panelTabla = new JPanel();
		panelPrincipal.add(panelTabla, BorderLayout.CENTER);
		panelTabla.setLayout(new BorderLayout(0, 0));

		tableBusqueda = new JTable() {
            /**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
            public boolean isCellEditable(int row, int column)
            {
                return false;
            }
        }; 
		tableBusqueda = Handlers.creaTablaOficina();
		
		JScrollPane scrollPane = new JScrollPane();
		panelTabla.add(scrollPane, BorderLayout.CENTER);
		
		scrollPane.setViewportView(tableBusqueda);

	}
	/**
	 * Abre el {@code JDialog} y lo hace visible para el usuario
	 * @return	El JDialog en estado no-modal
	 */
	public static Oficina showTablaOficinas()
	{
		//Creamos la ventana
		TablaOficina nuevaVentana = new TablaOficina();		
		//La ponemos visible
		nuevaVentana.setVisible(true);
		
		return Ofielegida;
	}
	/**
	 * Abre el {@code JDialog} en estado {@code modal}
	 * @return	El JDialog en estado modal
	 */
	public static Oficina showTablaOficinasModal()
	{
		//Creamos la ventana
		TablaOficina nuevaVentana = new TablaOficina();
		//Lo ponemos modal
		nuevaVentana.setModal(true);
		//La ponemos visible
		nuevaVentana.setVisible(true);
		
		return Ofielegida;
	}
}
