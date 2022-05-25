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

import clasesObjetos.CocheElectrico;
import manejadoresEventos.filtroTablas;
import metodos.Handlers;
import metodos.MetodoDni;

public class TablaCocheElectrico extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private static TablaCocheElectrico yoMismo;
	private static CocheElectrico CocheElectricoElegido;
	private JTextField tbMatricula;
	private JTable TablaCocheElectricoustion;

	/**
	 * Crea el JDialog.
	 */
	public TablaCocheElectrico() {
		creaAtributosGenerales();
		creabuttonPane();
		yoMismo = this;
		MetodosGUI.centraFormulario(yoMismo);
		filtroTablas.filtraEnTabla(TablaCocheElectricoustion,tbMatricula);
	}

	private void creabuttonPane() {
		JPanel buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
	}


	private void creaAtributosGenerales() {
		setTitle("Coches eléctrico");
		setIconImage(Toolkit.getDefaultToolkit().getImage(TablaCocheElectrico.class.getResource("/icons/miniLogo.png")));
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
		TablaCocheElectricoustion = Handlers.creaTablaCocheElectrico();
		contentPanel.add(TablaCocheElectricoustion, BorderLayout.CENTER);

		filtroTablas.filtraEnTabla(TablaCocheElectricoustion, tbMatricula);
	}

	private void creaPanelBúsqueda() {

		JPanel panel = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panel.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		contentPanel.add(panel, BorderLayout.NORTH);

		JLabel lblmat = new JLabel("Buscar");
		panel.add(lblmat);

		creaTbDni();
		panel.add(tbMatricula);
		tbMatricula.setColumns(10);
		{
			JLabel lblTitulo = new JLabel("Tabla de coches eléctricos");
			lblTitulo.setIcon(new ImageIcon(TablaCocheElectrico.class.getResource("/16/edit.png")));
			panel.add(lblTitulo);
		}

		tbMatricula = new JTextField();
		tbMatricula.setPreferredSize(new Dimension(500, 19));
		tbMatricula.setBounds(new Rectangle(0, 0, 5000, 0));
		tbMatricula.setText("");
		panel.add(tbMatricula);
		tbMatricula.setColumns(50);
		tbMatricula.setColumns(50);
		
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
				if (TablaCocheElectricoustion.getSelectedRow()>=0)
				{
					int num_fila  = TablaCocheElectricoustion.getSelectedRow();
					
					CocheElectricoElegido = (CocheElectrico) TablaCocheElectricoustion.getValueAt(num_fila, 1);
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
		tbMatricula = new JTextField();
		tbMatricula.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//Si la tecla pulsada es el ENTER
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					if (tbMatricula.getText().length()==9)
					{
						if (MetodoDni.DNIvalido(tbMatricula.getText()))	//Si es un DNI válido
						{
							//Componemos el CocheElectrico
							//							eligeCocheElectricoConsultado();
							tbMatricula.setEnabled(false);

						}else {
							JOptionPane.showMessageDialog(null, "Longitud del DNI inválida");
						}
					}
				}
			}

		});
	}

	//	private void eligeCocheElectricoConsultado() {
	//		String dni = tbDni.getText();
	////		CocheElectrico cli = RepositorioCocheElectrico.leeCocheElectrico(dni);
	////		seleccionaCocheElectrico(cli);
	//
	//	}

	public static CocheElectrico showDialog()
	{
		TablaCocheElectrico v = new TablaCocheElectrico();
		v.setVisible(true);
		return CocheElectricoElegido;
	}

	public static CocheElectrico showDialogModal()
	{
		TablaCocheElectrico v = new TablaCocheElectrico();
		v.setModal(true);
		v.setVisible(true);
		return CocheElectricoElegido;
	}
}
