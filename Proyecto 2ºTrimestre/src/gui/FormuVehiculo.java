package gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.toedter.calendar.JDateChooser;

import clasesObjetos.Categoria;
import metodos.Handlers;

/**
 * Formulario 'genérico' para todos los vehículos
 * @author Victor
 *
 */
public class FormuVehiculo extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private JTextField tbMat;
	private JTextField tbMarca;
	private JTextField tbMod;
	private JTextField tbColor;
	private JDateChooser tbFechaAlta;
	//	private ButtonGroup gEspecializacion; //Grupo de radioButtons 
	private JTextField textField;
	private ButtonGroup gcarnetMoto;
	protected CardLayout cardLayout;
	private JComboBox<Integer> tbCilindrada;
	private JTextField tbNumPlazasComb;
	private JTextField textField_1;
	private JTextField textField_2;

	private JComboBox<String> cbTipo;
	private JTextField tbNumPlazasCocheElectrico;
	private JTextField tbAutonomiaCoche;
	private JTextField tbTiempoRecargaCoche;

	private JComboBox<Categoria> comboBoxCategoriaCocheElectrico;
	private JComboBox<Categoria> comboBoxCategoriaCocheCombustion;



	/**
	 * Constructor del dialog.
	 */
	public FormuVehiculo() {
		setTitle("Nuevo veh\u00EDculo");
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos\\Logo.png"));
		setBounds(100, 100, 579, 362);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		pideAtributosGeneralesDeVehiculo();
		{
			//			{
			//				gEspecializacion = new ButtonGroup();
			//			}
		}
		{
			comboBoxCategoriaCocheElectrico = Handlers.creaDesplegableCategoria();
			comboBoxCategoriaCocheCombustion = Handlers.creaDesplegableCategoria();
			tbCilindrada = Handlers.creaDesplegableCilindrada();

			creaTabbedPane();

		}
		creaButtonPane();
	}



	private void creaButtonPane() {
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("Aceptar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}



	private void pideAtributosGeneralesDeVehiculo() {
		{
			JPanel panel = new JPanel();
			panel.setBounds(10, 10, 181, 40);
			contentPanel.add(panel);
			{
				JLabel lblMat = new JLabel("Matr\u00EDcula");
				panel.add(lblMat);
			}
			{
				tbMat = new JTextField();
				panel.add(tbMat);
				tbMat.setColumns(10);
			}
		}
		{
			JPanel panelGenerico = new JPanel();
			panelGenerico.setBounds(167, 10, 388, 40);
			contentPanel.add(panelGenerico);
			{
				JLabel lbMarca = new JLabel("Marca");
				panelGenerico.add(lbMarca);
			}
			{
				tbMarca = new JTextField();
				panelGenerico.add(tbMarca);
				tbMarca.setColumns(10);
			}
			{
				JLabel lblMod = new JLabel("Modelo");
				panelGenerico.add(lblMod);
			}
			{
				tbMod = new JTextField();
				panelGenerico.add(tbMod);
				tbMod.setColumns(10);
			}
		}
		{
			JPanel panelgenerico2 = new JPanel();
			panelgenerico2.setBounds(10, 51, 545, 40);
			contentPanel.add(panelgenerico2);
			{
				JLabel lblColor = new JLabel("Color");
				panelgenerico2.add(lblColor);
			}
			{
				tbColor = new JTextField();
				panelgenerico2.add(tbColor);
				tbColor.setColumns(10);
			}
			{
				JLabel lblFechaAlta = new JLabel("Fecha de alta");
				panelgenerico2.add(lblFechaAlta);
			}
			{
				tbFechaAlta = new JDateChooser();
				panelgenerico2.add(tbFechaAlta);
			}

			JPanel panelkms = new JPanel();
			panelgenerico2.add(panelkms);

			JLabel lblkm = new JLabel("Kil\u00F3metros");
			panelkms.add(lblkm);

			textField = new JTextField();
			textField.setToolTipText("\u00BFCu\u00E1ntos kil\u00F3metros tiene recorridos?");
			textField.setColumns(10);
			panelkms.add(textField);
		}
	}



	private void creaTabbedPane() {
		JTabbedPane EspecialidadVehiculos = new JTabbedPane(JTabbedPane.TOP);
		EspecialidadVehiculos.setBounds(10, 92, 545, 198);
		contentPanel.add(EspecialidadVehiculos);

		creaFormularioMoto(EspecialidadVehiculos);

		creaFormularioFurgoneta(EspecialidadVehiculos);

		creaFormularioCocheCombustion(EspecialidadVehiculos);

		creaFormularioCocheElectrico(EspecialidadVehiculos);
	}



	private void creaFormularioCocheElectrico(JTabbedPane EspecialidadVehiculos) {
		
		JPanel panelCocheElectrico = new JPanel();
		EspecialidadVehiculos.addTab("Coche eléctrico", null, panelCocheElectrico, null);
		panelCocheElectrico.setLayout(null);
		JLabel lblAutonomia = new JLabel("Autonom\u00EDa");
		lblAutonomia.setBounds(10, 123, 77, 24);
		panelCocheElectrico.add(lblAutonomia);

		JLabel lblNumPlazasCocheElectrico = new JLabel("N\u00FAmero de plazas");
		lblNumPlazasCocheElectrico.setBounds(10, 28, 130, 14);
		panelCocheElectrico.add(lblNumPlazasCocheElectrico);

		tbNumPlazasCocheElectrico = new JTextField();
		tbNumPlazasCocheElectrico.setColumns(5);
		tbNumPlazasCocheElectrico.setBounds(111, 25, 46, 20);
		panelCocheElectrico.add(tbNumPlazasCocheElectrico);

		tbAutonomiaCoche = new JTextField();
		tbAutonomiaCoche.setBounds(82, 125, 46, 20);
		panelCocheElectrico.add(tbAutonomiaCoche);
		tbAutonomiaCoche.setColumns(10);

		JLabel lblTiempoRecarga = new JLabel("Tiempo de carga");
		lblTiempoRecarga.setBounds(10, 78, 93, 14);
		panelCocheElectrico.add(lblTiempoRecarga);

		tbTiempoRecargaCoche = new JTextField();
		tbTiempoRecargaCoche.setToolTipText("El tiempo que tarda el veh\u00EDculo en cargar (en minutos)");
		tbTiempoRecargaCoche.setBounds(111, 75, 46, 20);
		panelCocheElectrico.add(tbTiempoRecargaCoche);
		tbTiempoRecargaCoche.setColumns(10);

		comboBoxCategoriaCocheElectrico.setBounds(372, 124, 143, 22);
		panelCocheElectrico.add(comboBoxCategoriaCocheElectrico);

		JLabel lblcategoriaCocheElectrico = new JLabel("Categoria");
		lblcategoriaCocheElectrico.setBounds(372, 99, 102, 14);
		panelCocheElectrico.add(lblcategoriaCocheElectrico);
	
	}



	private void creaFormularioMoto(JTabbedPane EspecialidadVehiculos) {
		JPanel panelMoto = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelMoto.getLayout();
		flowLayout.setHgap(10);
		flowLayout.setVgap(25);
		EspecialidadVehiculos.addTab("Moto", null, panelMoto, null);

		JLabel lblCilindrada = new JLabel("Cilindrada");
		panelMoto.add(lblCilindrada);
		panelMoto.add(tbCilindrada);

		JLabel lblCarnetRequerido = new JLabel("Carnet de conducir necesario");
		panelMoto.add(lblCarnetRequerido);

		JPanel panelCarnetReqMoto = new JPanel();
		panelMoto.add(panelCarnetReqMoto);
		panelCarnetReqMoto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		gcarnetMoto = new ButtonGroup();
		creaRadioButtonsTipoCarnetMoto(panelCarnetReqMoto);
	}



	private void creaFormularioFurgoneta(JTabbedPane EspecialidadVehiculos) {
		JPanel panelFurgo = new JPanel();
		FlowLayout flowLayout = (FlowLayout) panelFurgo.getLayout();
		flowLayout.setVgap(25);
		EspecialidadVehiculos.addTab("Furgoneta", null, panelFurgo, null);

		JLabel Carga = new JLabel("Capacidad de carga");
		panelFurgo.add(Carga);

		textField_1 = new JTextField();
		textField_1.setColumns(10);
		panelFurgo.add(textField_1);

		JLabel lblCarnetReq = new JLabel("Carnet requerido");
		panelFurgo.add(lblCarnetReq);

		textField_2 = new JTextField();
		textField_2.setColumns(10);
		panelFurgo.add(textField_2);
	}



	private void creaFormularioCocheCombustion(JTabbedPane EspecialidadVehiculos) {
		cbTipo = Handlers.DesplegableTipoCoche();
		cbTipo.setBounds(372, 52, 97, 22);
		cbTipo.setMaximumRowCount(4);
		JPanel panelCocheCom = new JPanel();
		panelCocheCom.setLayout(null);
		EspecialidadVehiculos.addTab("Coche a combustión", null, panelCocheCom, null);

		JLabel lblNumPlazas = new JLabel("N\u00FAmero de plazas");
		lblNumPlazas.setBounds(32, 34, 130, 14);
		panelCocheCom.add(lblNumPlazas);

		tbNumPlazasComb = new JTextField();
		tbNumPlazasComb.setColumns(5);
		tbNumPlazasComb.setBounds(157, 31, 46, 20);
		panelCocheCom.add(tbNumPlazasComb);

		JLabel lblTipoCoche = new JLabel("Tipo");
		lblTipoCoche.setBounds(372, 34, 31, 14);
		panelCocheCom.add(lblTipoCoche);
		panelCocheCom.add(cbTipo);
		
		JLabel lblcategoriaCocheCombustion = new JLabel("Categoria");
		lblcategoriaCocheCombustion.setBounds(372, 99, 102, 14);
		panelCocheCom.add(lblcategoriaCocheCombustion);
		
		comboBoxCategoriaCocheCombustion.setBounds(372, 124, 143, 22);
		panelCocheCom.add(comboBoxCategoriaCocheCombustion);
		
	}



	private void creaRadioButtonsTipoCarnetMoto(JPanel panelCarnetReqMoto) {
		JRadioButton rdbtnAM = new JRadioButton("AM");
		panelCarnetReqMoto.add(rdbtnAM);
		gcarnetMoto.add(rdbtnAM);

		JRadioButton rdbtnA1 = new JRadioButton("A1");
		panelCarnetReqMoto.add(rdbtnA1);
		gcarnetMoto.add(rdbtnA1);

		JRadioButton rdbtnA2 = new JRadioButton("A2");
		panelCarnetReqMoto.add(rdbtnA2);
		gcarnetMoto.add(rdbtnA2);
	}
}
