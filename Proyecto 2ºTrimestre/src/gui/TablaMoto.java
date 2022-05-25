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
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import clasesObjetos.Moto;
import manejadoresEventos.filtroTablas;
import metodos.Handlers;

public class TablaMoto extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private static TablaMoto yoMismo;
	private static Moto MotoElegida;
	private JTextField tbMat;
	private JTable TablaMoto;

	/**
	 * Crea el JDialog.
	 */
	public TablaMoto() {
		creaAtributosGenerales();
		creabuttonPane();
		yoMismo = this;
		MetodosGUI.centraFormulario(yoMismo);
		filtroTablas.filtraEnTabla(TablaMoto,tbMat);
	}

	private void creabuttonPane() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}


	private void creaAtributosGenerales() {
		setTitle("Moto");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TablaMoto.class.getResource("/icons/miniLogo.png")));
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
		TablaMoto = Handlers.creaTablaMoto();
		contentPanel.add(TablaMoto, BorderLayout.CENTER);

	}

	private void creaPanelBúsqueda() {

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel, BorderLayout.NORTH);

		JLabel lblBusqueda = new JLabel("Buscar: ");
		panel.add(lblBusqueda);

		creatbMat();
		panel.add(tbMat);
		tbMat.setColumns(10);
		{
			JLabel lblTitulo = new JLabel("Tabla de motos");
			lblTitulo.setIcon(new ImageIcon(TablaMoto.class.getResource("/16/edit.png")));
			panel.add(lblTitulo);
		}

		tbMat = new JTextField();
		tbMat.setPreferredSize(new Dimension(500, 19));
		tbMat.setBounds(new Rectangle(0, 0, 5000, 0));
		tbMat.setText("");
		panel.add(tbMat);
		tbMat.setColumns(50);
		tbMat.setColumns(50);
		
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
				if (TablaMoto.getSelectedRow()>=0)
				{
					int num_fila  = TablaMoto.getSelectedRow();
					
					MotoElegida = (Moto) TablaMoto.getValueAt(num_fila, 1);
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

	private void creatbMat() {
		tbMat = new JTextField();
		tbMat.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//Si la tecla pulsada es el ENTER
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{

						
							//Componemos el Moto
							//							eligeMotoConsultado();
							tbMat.setEnabled(false);

						
				}
			}

		});
	}

	public static Moto showDialog()
	{
		TablaMoto v = new TablaMoto();
		v.setVisible(true);
		return MotoElegida;
	}

	public static Moto showDialogModal()
	{
		TablaMoto v = new TablaMoto();
		v.setModal(true);
		v.setVisible(true);
		return MotoElegida;
	}
}
