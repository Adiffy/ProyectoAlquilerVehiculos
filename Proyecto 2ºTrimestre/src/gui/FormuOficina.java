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
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import accesoADatos.RepositorioOficina;
import clasesObjetos.Oficina;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

public class FormuOficina extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private static JComboBox<String> tbProv;
	private static Oficina OfiElegida;
	private JPanel panelLateral;
	private static JCheckBox chckbxDeAeropuerto;
	private static JTextField tbCodOfi;
	private JPanel panelCodOfi;
	private JLabel lblCodOfi;
	private JPanel panelContenedorCentral;
	private JPanel panelSuperior;
	private static JTextField tbLoc;
	private JPanel panelLoc;
	private JPanel panelContenedorInferior;
	private JButton btnBorrar;
	private static JPanel buttonPane_1;
	private JPanel panelDesc_1;
	private static JTextArea tbDesc;
	private JPanel panel_relleno1;
	private JPanel panel_Prov;
	private JLabel lblProv;



	/**
	 * Construye el JDialog.
	 */
	private FormuOficina() {
		rellenaAtributosGenerales();
		creaPanelPrincipal();
		//Creamos los paneles y ponemos los componentes
		creaPanelContenedor3();
		creaPanelCodOfi();
		creaPanelDescripcion();
		creaPaneldeAeropuerto();
		
		creaPanelProvincia();
		creaPanelBotones();
		//Aóadimos el titulo
		creaTitulo();
		//Iniciamos la ventana con la configuracion inicial
		inicial();
	}

	private void creaPanelBotones() {
		JPanel buttonPane = creaButtonPane();
		creaBotonAceptar(buttonPane);
		creaBotonBorrar(buttonPane);
		creaBotonCancelar(buttonPane);
	}

	private JPanel creaButtonPane() {
		buttonPane_1 = new JPanel();
		buttonPane_1.setLayout(new FlowLayout(FlowLayout.RIGHT));
		getContentPane().add(buttonPane_1, BorderLayout.SOUTH);
		return buttonPane_1;
	}

	private void creaBotonCancelar(JPanel buttonPane) {
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setIcon(new ImageIcon("recursos\\16\\cross.png"));
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

	private void creaBotonAceptar(JPanel buttonPane) {
		JButton okButton = new JButton("Aceptar");
		okButton.setIcon(new ImageIcon("recursos\\16\\diskette.png"));
		okButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Leemos lo que ha escrito el usuario
				rellenaOficina();
				boolean insertada = RepositorioOficina.insertOficina(OfiElegida);
				//Si no se puede insertar
				if (!insertada)
				{
					JOptionPane.showMessageDialog(okButton, "No se pudo insertar la oficina de código "+OfiElegida.getCódigo(),"DataBase Error",JOptionPane.ERROR_MESSAGE);
				}
				MetodosGUI.estadoInicial(panelLateral);
			}
		});
		okButton.setActionCommand("Aceptar");
		buttonPane.add(okButton);
		getRootPane().setDefaultButton(okButton);
	}

	private void creaBotonBorrar(JPanel panel) {
		{
			btnBorrar = new JButton("Borrar");
			btnBorrar.setIcon((new ImageIcon("recursos\\16\\recycle_bin.png")));
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Borrar Oficina
					rellenaOficina();
					RepositorioOficina.borraOficina(OfiElegida.getCódigo());
				}
			});
			panel.add(btnBorrar);
		}
	}

	private void rellenaAtributosGenerales() {
		setResizable(false);
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos\\Logo.png"));
		setTitle("Oficinas");
		setBounds(100, 100, 502, 357);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
	}

	private void creaPanelPrincipal() {
		panelLateral = new JPanel();
		contentPanel.add(panelLateral);
		panelLateral.setLayout(new BorderLayout(0, 0));
	}



	private void creaTitulo() {
		panelSuperior = new JPanel();
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelLateral.add(panelSuperior, BorderLayout.NORTH);
		JLabel lblTitulo = new JLabel("Editor de Oficinas");
		lblTitulo.setIcon(new ImageIcon("recursos\\16\\pencil.png"));
		panelSuperior.add(lblTitulo);
	}

/*	private void creaPanelContenedor2() {
		panelContenedorInferior = new JPanel();
		panelContenedorInferior.setBounds(33, 33, 194, 30);
		panelLateral.add(panelContenedorInferior, BorderLayout.SOUTH);
		creaPanelInferior();
	}*/

	private void creaPanelInferior() {
		panelContenedorInferior = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelContenedorInferior.getLayout();
		flowLayout.setAlignment(FlowLayout.LEFT);
		panelContenedorInferior.setBounds(10, 159, 225, 63);
		panelContenedorCentral.add(panelContenedorInferior);
		panelContenedorInferior.setLayout(null);
		{
			
			creaLabelProv();
			creaCampoProv(contentPanel);

		}
	}





	private void creaPanelContenedor3() {
		panelContenedorCentral = new JPanel();
		panelLateral.add(panelContenedorCentral, BorderLayout.CENTER);
	}

	private void creaPanelCodOfi() {
		panelCodOfi = new JPanel();
		panelCodOfi.setBounds(10, 11, 456, 72);
		panelContenedorCentral.add(panelCodOfi);
		
		creaLabelCodOfi();
		creaCampoCodOfi();
		
		creaPanelInferior();
		creaPanelLoc();
	}

	private void creaPanelLoc() {
		panelLoc = new JPanel();
		FlowLayout flowLayout_1 = (FlowLayout) panelLoc.getLayout();
		flowLayout_1.setAlignment(FlowLayout.LEFT);
		panelLoc.setBounds(300, 159, 166, 63);
		panelContenedorCentral.add(panelLoc);
		creaLabelLocalidad();
		creaTbLocalidad();
	}

	private void creaLabelProv() {
		{
			JLabel lblProv = new JLabel("Provincia");
			panelContenedorInferior.add(lblProv);
		}
	}

	private void creaTbLocalidad() {
		{
			panel_relleno1 = new JPanel();
			panelLoc.add(panel_relleno1);
		}
		
		JPanel panel_relleno2 = new JPanel();
		panelLoc.add(panel_relleno2);
		
		JPanel panel_relleno3 = new JPanel();
		panelLoc.add(panel_relleno3);
		
		JPanel panel_relleno4 = new JPanel();
		panelLoc.add(panel_relleno4);
		
		JPanel panel_relleno5 = new JPanel();
		panelLoc.add(panel_relleno5);
		{
			tbLoc = new JTextField();
			tbLoc.setText("");
			tbLoc.setEnabled(false);
			tbLoc.setColumns(15);
			panelLoc.add(tbLoc);
		}
	}

	private void creaLabelLocalidad() {
		{
			JLabel lblLoc = new JLabel("Localidad");
			panelLoc.add(lblLoc);
		}
	}

	private void creaLabelCodOfi() {
		lblCodOfi = new JLabel("C\u00F3digo de la oficina");
		panelCodOfi.add(lblCodOfi);
	}

	private void creaCampoCodOfi() {
		tbCodOfi = new JTextField();
//		tbCodOfi.setFocusAccelerator('\n');	//Para la tecla ENTER tiene el foco
		tbCodOfi.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
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
		tbCodOfi.setToolTipText("El c\u00F3digo de una oficina\r\nPor ejemplo: 'MA001'");
		tbCodOfi.setText("");
		tbCodOfi.setEnabled(true);
		tbCodOfi.setColumns(10);
		panelCodOfi.add(tbCodOfi);
	}

	private void creaPaneldeAeropuerto() {
		{
			JPanel panelDeAeropuerto = new JPanel();
			panelDeAeropuerto.setBounds(10, 129, 470, 33);
			panelContenedorCentral.add(panelDeAeropuerto);
			creaCheckboxdeAeropuerto(panelDeAeropuerto);
		}
		
	}

	private void creaCheckboxdeAeropuerto(JPanel panelDeAeropuerto) {
		{
			chckbxDeAeropuerto = new JCheckBox("Oficina de aeropuerto");
			chckbxDeAeropuerto.setSelected(false);
			chckbxDeAeropuerto.setEnabled(false);
			panelDeAeropuerto.add(chckbxDeAeropuerto);
		}
	}

	private void creaPanelDescripcion() {
		panelContenedorCentral.setLayout(null);
		{
			panelDesc_1 = new JPanel();
			panelDesc_1.setBounds(10, 83, 456, 48);
			panelContenedorCentral.add(panelDesc_1);
			creaLabelDesc(panelDesc_1);
			creaCampoDesc(panelDesc_1);
			
					
		}
	}

	private void creaLabelDesc(JPanel panelDesc) {
		JScrollPane scrollPane = new JScrollPane();
		panelDesc_1.add(scrollPane, BorderLayout.CENTER);
		JLabel lblDesc = new JLabel("Descripci\u00F3n");
		lblDesc.setHorizontalAlignment(SwingConstants.RIGHT);
		scrollPane.setRowHeaderView(lblDesc);
		scrollPane.setViewportView(tbDesc);
	}

	private void creaCampoDesc(JPanel panelDesc) {
		panelDesc_1.setLayout(new BorderLayout(0, 0));	
		tbDesc = new JTextArea();
		tbDesc.setText("");
		tbDesc.setEnabled(false);
		tbDesc.setColumns(30);
	}


	private void creaPanelProvincia() {
		
			panel_Prov = new JPanel();
			FlowLayout flowLayout = (FlowLayout) panel_Prov.getLayout();
			flowLayout.setAlignment(FlowLayout.LEFT);
			panel_Prov.setBounds(10, 159, 204, 81);
			panelContenedorCentral.add(panel_Prov);
			{
				lblProv = new JLabel("Provincia");
				panel_Prov.add(lblProv);
			}
			creaCampoProv(panel_Prov);
	}

	
	private void creaCampoProv(JPanel panel) {
		tbProv = MetodosGUI.creaDesplegableProv();
		panel.add(tbProv);
	}

	public static void rellenaOficinaFromBD()
	{
		if (RepositorioOficina.leeOficina(tbCodOfi.getText())!=null)	//Si existe
		{
			OfiElegida = RepositorioOficina.leeOficina(tbCodOfi.getText());
		}
		rellenaCampoOficina();
	}

	private static void rellenaCampoOficina() {
		// Rellenamos los campos del formulario
		tbCodOfi.setText(OfiElegida.getCódigo());
		tbDesc.setText(OfiElegida.getDescripción());
		chckbxDeAeropuerto.setSelected(OfiElegida.isDeAeropuerto());
		tbProv.setSelectedItem(OfiElegida.getProvincia());
		tbLoc.setText(OfiElegida.getLocalidad());
	}

	public static void rellenaOficina()
	{
			OfiElegida = new Oficina(tbCodOfi.getText(), tbDesc.getText(), (String) tbProv.getSelectedItem(), tbLoc.getText(), chckbxDeAeropuerto.isSelected());
			rellenaCampoOficina();
	}

	public static Oficina showDialog()
	{
		try {
			FormuOficina dialog = new FormuOficina();
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
			FormuOficina dialog = new FormuOficina();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setModal(true);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return OfiElegida;
	}

	private static void inicial()
	{
		tbProv.setSelectedIndex(0);
		tbProv.setEnabled(false);
	}
}
