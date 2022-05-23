package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import accesoADatos.RepositorioOficina;
import clasesObjetos.Oficina;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormuOficinas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final static JPanel contentPanel = new JPanel();
	private static FormuOficinas yo;
	private static JTextField tbCodOfi;
	private static JComboBox<String> tbProv;
	private JLabel lblCodOfi;
	private static Oficina OfiElegida;
	private JPanel panelArriba;
	private JPanel panelSuperior;
	private JPanel buttonPane;
	private JPanel panelDerecho;
	private JButton okButton;
	private JButton btnBorrar;
	private JButton cancelButton;
	private JPanel panelLoc;
	private JLabel lblLoc;
	private static JTextField tbLoc;
	private JPanel panelIzquierdo;
	private JPanel panelDesc_1;
	private JLabel lblDesc;
	private static JTextField tbDesc;
	private JPanel panelDeAeropuerto_1;
	private static JCheckBox chckbxDeAeropuerto;
	private JPanel panelCodOfi;
	private JButton btnBuscar;


	/**
	 * Construye el JDialog.
	 */
	private FormuOficinas() {

		declaraAtributosGenerales();
		creaTitulo();
		JPanel panelPrincipal = creaPanelCodOfi();

		creaPanelIzquierdo();
		creaPanelDerecho(panelPrincipal);
		creaPanelInferior(panelPrincipal);

		creaButtonPane();

	}

	private void creaButtonPane() {
		buttonPane = new JPanel();
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane, BorderLayout.SOUTH);
//		creaBotonDesbloquear();
		creaBotonBorrar();
		creaBotonCancelar();
		creaBotonAceptar();
		
	}

	

	private void creaBotonCancelar() {
		cancelButton = new JButton("Cancelar");
		cancelButton.setIcon(new ImageIcon(FormuOficinas.class.getResource("/16/cross.png")));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Borramos lo que haya escrito en cada uno de los TextFields
				MetodosGUI.estadoInicial(contentPanel);
				tbCodOfi.setEnabled(true);
			}
		});
		cancelButton.setActionCommand("Cancelar");
		buttonPane.add(cancelButton);
	}

	private void creaBotonAceptar() {
		okButton = new JButton("Aceptar");
		okButton.setIcon(new ImageIcon(FormuOficinas.class.getResource("/16/diskette.png")));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Leemos lo que ha escrito el usuario
				rellenaOficinaElegida();
				boolean insertada = RepositorioOficina.insertOficina(OfiElegida);
				//Si no se puede insertar
				if (!insertada)
				{
					JOptionPane.showMessageDialog(okButton, "No se pudo insertar la oficina de código "+OfiElegida.getCódigo(),"DataBase Error",JOptionPane.ERROR_MESSAGE);
				}
				MetodosGUI.estadoInicial(contentPanel);
			}
		});
		okButton.setActionCommand("Aceptar");
		buttonPane.add(okButton);
	}

	private void creaBotonBorrar() {
		{
			btnBorrar = new JButton("Borrar");
			btnBorrar.setIcon(new ImageIcon(FormuOficinas.class.getResource("/16/recycle_bin.png")));
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Borrar Oficina
					rellenaOficinaElegida();
					RepositorioOficina.borraOficina(OfiElegida.getCódigo());
				}
			});
			buttonPane.add(btnBorrar);
		}
	}

	private void creaPanelIzquierdo() {
		{
			panelDesc_1 = new JPanel();
			panelIzquierdo.add(panelDesc_1);
			creaLabelDesc(panelCodOfi);
			creaTbDesc();
		}
	}

	private void creaLabelDesc(JPanel panelDesc) {
		{
			lblDesc = new JLabel("Descripción");
			panelDesc_1.add(lblDesc);
		}
	}

	private void creaTbDesc() {
		{
			tbDesc = new JTextField();
			tbDesc.setEnabled(false);
			tbDesc.setColumns(10);
			panelDesc_1.add(tbDesc);
		}
	}

	private void pideDeAeropuerto() {
		{
			panelDeAeropuerto_1 = new JPanel();
			panelIzquierdo.add(panelDeAeropuerto_1);
			checkBoxDeAeropuerto(panelDeAeropuerto_1);
		}
	}

	private void checkBoxDeAeropuerto(JPanel panelDeAeropuerto) {
		{
			chckbxDeAeropuerto = new JCheckBox("Oficina de aeropuerto");
			chckbxDeAeropuerto.setEnabled(false);
			panelDeAeropuerto_1.add(chckbxDeAeropuerto);
		}
	}

	private void creaPanelDerecho(JPanel panelPrincipal) {
		panelDerecho = new JPanel();
		panelPrincipal.add(panelDerecho, BorderLayout.EAST);

		pideProvinciaYlocalidad(panelDerecho);
	}

	private void pideProvinciaYlocalidad(JPanel panelDerecho) {
		creaTbProv();

		creaPanelLocalidad(panelDerecho);
	}

	private void creaTbProv() {
		tbProv = MetodosGUI.creaDesplegableProv();
		tbProv.setEnabled(false);
	}


	private void creaPanelLocalidad(JPanel panelDerecho) {
		panelDerecho.setLayout(new BorderLayout(0, 0));
	}


	private JPanel creaPanelCodOfi() {
		JPanel panelPrincipal = new JPanel();
		contentPanel.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));

		panelArriba = new JPanel();
		panelPrincipal.add(panelArriba, BorderLayout.NORTH);

		panelCodOfi = new JPanel();
		panelArriba.add(panelCodOfi);

		lblCodOfi = new JLabel("C\u00F3digo de la oficina");
		panelCodOfi.add(lblCodOfi);

		tbCodOfi = new JTextField();
		tbCodOfi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//Si la tecla pulsada es el enter
				if (e.getKeyCode()==KeyEvent.VK_ENTER)
				{
					//Rellenamos los campos segón la base de datos
					rellenaOficinaFromBD();
					//Preparamos los campos para que pueda modificarlos
					MetodosGUI.estadoEditar(contentPanel);
					tbDesc.setEnabled(true);
				}

			}
		});

		tbCodOfi.setToolTipText("El c\u00F3digo de una oficina\r\nPor ejemplo: 'MA01'");
		lblCodOfi.setLabelFor(tbCodOfi);
		tbCodOfi.setColumns(10);
		panelCodOfi.add(tbCodOfi);
		{
			btnBuscar = new JButton("");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Mostramos el listado de Oficinas
					OfiElegida = TablaOficina.showTablaOficinasModal();
					rellenaCampoOficina();
					MetodosGUI.estadoEditar(panelPrincipal);
				}
			});
			btnBuscar.setIcon(new ImageIcon(FormuOficinas.class.getResource("/16/zoom.png")));
			panelArriba.add(btnBuscar);
		}
		{
			panelIzquierdo = new JPanel();
			panelPrincipal.add(panelIzquierdo, BorderLayout.CENTER);
			
			pideDeAeropuerto();
		}
		return panelPrincipal;
	}

	private void creaPanelInferior(JPanel panelPrincipal) {

		JPanel panelAbajo = new JPanel();
		panelPrincipal.add(panelAbajo, BorderLayout.SOUTH);
		{
			panelLoc = new JPanel();
			panelAbajo.add(panelLoc);
			{
				lblLoc = new JLabel("Localidad");
				panelLoc.add(lblLoc);
			}
			{
				tbLoc = new JTextField();
				tbLoc.setEnabled(false);
				tbLoc.setColumns(15);
				panelLoc.add(tbLoc);
			}
		}

		JLabel lblProv = new JLabel("Provincia");
		panelAbajo.add(lblProv);
		tbProv = MetodosGUI.creaDesplegableProv();
		panelAbajo.add(tbProv);


	}
	private void creaTitulo() {
		panelSuperior = new JPanel();
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		contentPanel.add(panelSuperior, BorderLayout.NORTH);
		JLabel lblTitulo = new JLabel("Editor de Oficinas");
		lblTitulo.setIcon(new ImageIcon(FormuOficinas.class.getResource("/16/pencil.png")));
		panelSuperior.add(lblTitulo);
	}

	private void declaraAtributosGenerales() {
		setTitle("Oficinas");
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormuOficinas.class.getResource("/icons/miniLogo.png")));
		setBounds(100, 100, 705, 385);
		yo = this;
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		panelSuperior = new JPanel();
		MetodosGUI.centraFormulario(yo);
	}

	public static void rellenaOficinaFromBD()
	{
		if (RepositorioOficina.leeOficina(tbCodOfi.getText())!=null)	//Si existe
		{
			OfiElegida = RepositorioOficina.leeOficina(tbCodOfi.getText());
			rellenaCampoOficina();
		}
		MetodosGUI.estadoEditar(contentPanel);
	}

	private static void rellenaCampoOficina() {
		// Rellenamos los campos del formulario
		tbCodOfi.setText(OfiElegida.getCódigo());
		tbDesc.setText(OfiElegida.getDescripción());
		chckbxDeAeropuerto.setSelected(OfiElegida.isDeAeropuerto());
		tbProv.setSelectedItem(OfiElegida.getProvincia().toUpperCase());
		tbLoc.setText(OfiElegida.getLocalidad());
	}
	private void rellenaOficinaElegida() {
		OfiElegida = new Oficina(tbCodOfi.getText(), tbDesc.getText(), (String) tbProv.getSelectedItem(), tbLoc.getText(), chckbxDeAeropuerto.isSelected());
		rellenaCampoOficina();
	}

	public static Oficina showDialog()
	{
		try {
			FormuOficinas dialog = new FormuOficinas();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OfiElegida;
	}

	public static Oficina showDialogModal()
	{
		try {
			FormuOficinas dialog = new FormuOficinas();
			dialog.setModal(true);
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OfiElegida;
	}


}
