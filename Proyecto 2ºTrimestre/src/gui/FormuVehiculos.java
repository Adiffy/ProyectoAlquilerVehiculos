package gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.toedter.calendar.JDateChooser;

import accesoADatos.RepositorioVehiculo;
import clasesObjetos.Categoria;
import clasesObjetos.CocheCombustion;
import clasesObjetos.CocheElectrico;
import clasesObjetos.Matricula;
import clasesObjetos.Oficina;
import exceptions.ConsumoNoValidoException;
import exceptions.EmisionesNoValidasException;
import exceptions.LetrasMatriculaNoValidasException;
import exceptions.NumPlazasNoValidoException;
import exceptions.NumeroMatriculaNoValidoException;
import exceptions.PotenciaNoValidaException;
import exceptions.RecargoNoValidoException;
import exceptions.TipoNoValidoException;
import metodos.Handlers;
import metodos.Helpers;
import objetosBD.FurgonetaBD;
import objetosBD.VehiculoDB;

import javax.swing.JTabbedPane;
import javax.swing.JRadioButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Date;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

/**
 * Formulario 'genérico' para todos los vehículos
 * @author Victor
 *
 */
public class FormuVehiculos extends JDialog {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//Propiedades
	private JTextField tbMatricula;
	private JTextField tbMarca;
	private JTextField tbModelo;
	private JTextField tbColor;
	private JTextField tbKilometros;
	private JTextField tbCapacidadCarga;
	private JComboBox<String> tbCarnetRequerido;
	private JComboBox<Integer> tbCilindrada;
	private JTextField tbNumPlazasCocheComb;
	private JTextField tbNumPlazasCocheElectrico;
	private JTextField tbAutonomiaCocheElectrico;
	private JTextField tbTiempoRecarga;
	private ButtonGroup gcarnetMoto;
	private JComboBox<Categoria> comboBoxCategoriaCocheElectrico;
	private static JComboBox<Categoria> comboBoxCategoriaCocheCombustion;
	private static JComboBox<String> cbTipo;
	private static JComboBox<String> cbTipoCocheElectrico;
	private JPanel panelGenerico2;
	private JPanel panelCentral;
	private JDateChooser tbFechaAlta;
	private static JPanel panelCocheCom;
	private static JComboBox<Oficina> oficinasCocheElectrico;
	private static JComboBox<Oficina> oficinasCocheCombustion;
	private JTabbedPane especialidadVehiculos;
	private static JPanel panelCocheElectrico;
	private static JPanel panelFurgo;
	private static JPanel panelMoto;

	private JComboBox<String> cbEmisionesFurgo;
	private JComboBox<String> cbEmisiones;



	/**
	 * Constructor del dialog.
	 */
	private FormuVehiculos() {
		declaraAtributosVentana();
		//Creamos el panel inferior
		creaButtonPane();
		//creamos el panel y los campos generales a rellenar
		pideAtributosGenerales();
		//Formamos los paneles que vamos a utilizar y pedimos los atributos generales que no cabían arriba
		pideAtributosGenerales2();
		//Llamamos a todos los Handlers para que rellenen los comboBoxes y otras propiedades
		preparaComboBoxes();
		creaTabbedPane(panelCentral);
		
		//Lo ponemos en estado inicial a priori
		MetodosGUI.estadoInicial((JPanel) getContentPane());
		tbMatricula.setEnabled(true);
	}



	private void pideAtributosGenerales2() {
		formaPaneles();
		pideColor();
		pideFechaAlta();
		pideKilometros();
	}



	private void formaPaneles() {
		panelCentral = new JPanel();
		getContentPane().add(panelCentral, BorderLayout.CENTER);
		panelCentral.setLayout(new BorderLayout(0, 0));
		panelGenerico2 = new JPanel();
		panelCentral.add(panelGenerico2, BorderLayout.NORTH);
	}



	private void preparaComboBoxes() {
		comboBoxCategoriaCocheElectrico = Handlers.creaDesplegableCategoria();
		comboBoxCategoriaCocheCombustion = Handlers.creaDesplegableCategoria();
		tbCilindrada = Handlers.creaDesplegableCilindrada();
		cbTipo = Handlers.DesplegableTipoCoche();
		cbTipo.setBounds(32, 95, 90, 18);
		cbTipo.setMaximumRowCount(4);
		cbTipoCocheElectrico = Handlers.DesplegableTipoCoche();
		cbTipoCocheElectrico.setBounds(654, 37, 90, 18);
		cbTipoCocheElectrico.setMaximumRowCount(4);
	}



	private void declaraAtributosVentana() {
		setTitle("Nuevo veh\u00EDculo");
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos\\jekeii7rp5hx8lfuz257.webp"));
		setBounds(100, 100, 873, 423);
		getContentPane().setLayout(new BorderLayout());
		//Centramos la ventana
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();	//Averiguamos el tamaño de la pantalla
		setLocation(dim.width/2-getSize().width/2,dim.height/2-getSize().height/2);	//Lo ponemos en la mitad
	}



	private void pideAtributosGenerales() {
		JPanel panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panelPrincipal, BorderLayout.NORTH);
		panelPrincipal.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pideMatriculaMarcayModelo(panelPrincipal);
	}



	private void pideMatriculaMarcayModelo(JPanel panelPrincipal) {
		{
			JPanel panelGenerico1 = new JPanel();
			panelGenerico1.setBorder(new EmptyBorder(5, 5, 5, 5));
			panelPrincipal.add(panelGenerico1);
			panelGenerico1.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
			{
				JPanel panel = new JPanel();
				panelGenerico1.add(panel);
				pideMatricula(panel);
				{
					JLabel lbMarca = new JLabel("Marca");
					panel.add(lbMarca);
				}
				{
					tbMarca = new JTextField();
					panel.add(tbMarca);
					tbMarca.setColumns(10);
				}
				{
					JLabel lblMod = new JLabel("Modelo");
					panel.add(lblMod);
				}
				{
					tbModelo = new JTextField();
					panel.add(tbModelo);
					tbModelo.setColumns(10);
				}
			}
		}
	}



	private void creaTabbedPane(JPanel panelCentral) {
		{
			especialidadVehiculos = new JTabbedPane(JTabbedPane.TOP);
			panelCentral.add(especialidadVehiculos, BorderLayout.CENTER);
			{
				creaFormuMoto(especialidadVehiculos);

				creaFormuFurgo(especialidadVehiculos);

				creaFormuCocheComb(especialidadVehiculos);

				creaFormuCocheElectrico(especialidadVehiculos);
			}
		}
	}



	private void creaFormuCocheElectrico(JTabbedPane EspecialidadVehiculos) {
		panelCocheElectrico = new JPanel();
		panelCocheElectrico.setLayout(null);
		EspecialidadVehiculos.addTab("Coche eléctrico", null, panelCocheElectrico, null);
		
			JLabel lblAutonomia = new JLabel("Autonom\u00EDa");
			lblAutonomia.setBounds(10, 123, 77, 24);
			panelCocheElectrico.add(lblAutonomia);

			JLabel lblNumPlazasCocheElectrico = new JLabel("N\u00FAmero de plazas");
			lblNumPlazasCocheElectrico.setBounds(10, 28, 130, 14);
			panelCocheElectrico.add(lblNumPlazasCocheElectrico);

			tbNumPlazasCocheElectrico = new JTextField();
			tbNumPlazasCocheElectrico.setColumns(5);
			tbNumPlazasCocheElectrico.setBounds(143, 25, 46, 20);
			panelCocheElectrico.add(tbNumPlazasCocheElectrico);

			tbAutonomiaCocheElectrico = new JTextField();
			tbAutonomiaCocheElectrico.setToolTipText("Horas que dura el coche en carretera");
			tbAutonomiaCocheElectrico.setColumns(10);
			tbAutonomiaCocheElectrico.setBounds(82, 125, 46, 20);
			panelCocheElectrico.add(tbAutonomiaCocheElectrico);
			JLabel lblTiempoRecarga = new JLabel("Tiempo de carga");
			lblTiempoRecarga.setBounds(10, 78, 138, 14);
			panelCocheElectrico.add(lblTiempoRecarga);
		
			tbTiempoRecarga = new JTextField();
			tbTiempoRecarga.setToolTipText("El tiempo que tarda el veh\u00EDculo en cargar (en minutos)");
			tbTiempoRecarga.setColumns(10);
			tbTiempoRecarga.setBounds(143, 75, 46, 20);
			panelCocheElectrico.add(tbTiempoRecarga);
		
		comboBoxCategoriaCocheElectrico.setBounds(649, 95, 143, 22);
		panelCocheElectrico.add(comboBoxCategoriaCocheElectrico);

		JLabel lblcategoriaCocheElectrico = new JLabel("Categoria");
		lblcategoriaCocheElectrico.setBounds(649, 78, 102, 14);
		panelCocheElectrico.add(lblcategoriaCocheElectrico);
		
		panelCocheElectrico.add(cbTipoCocheElectrico);
		
		JLabel lblTipoCocheElectrico = new JLabel("Tipo");
		lblTipoCocheElectrico.setBounds(649, 11, 115, 31);
		panelCocheElectrico.add(lblTipoCocheElectrico);
		
		JLabel lblOfiElectrico = new JLabel("Oficina");
		lblOfiElectrico.setBounds(649, 128, 68, 14);
		panelCocheElectrico.add(lblOfiElectrico);
		
		oficinasCocheElectrico = MetodosGUI.creaDesplegable();
		oficinasCocheElectrico.setBounds(649, 145, 200, 20);
		panelCocheElectrico.add(oficinasCocheElectrico);
	}



	private void creaFormuCocheComb(JTabbedPane EspecialidadVehiculos) {
		panelCocheCom = new JPanel();
		panelCocheCom.setLayout(null);
		EspecialidadVehiculos.addTab("Coche de combustión", null, panelCocheCom, null);
		
			JLabel lblNumPlazas = new JLabel("N\u00FAmero de plazas");
			lblNumPlazas.setBounds(32, 34, 130, 14);
			panelCocheCom.add(lblNumPlazas);
		
		
			tbNumPlazasCocheComb = new JTextField();
			tbNumPlazasCocheComb.setColumns(5);
			tbNumPlazasCocheComb.setBounds(157, 31, 46, 20);
			panelCocheCom.add(tbNumPlazasCocheComb);
		
		
			JLabel lblTipoCoche = new JLabel("Tipo");
			lblTipoCoche.setBounds(32, 78, 31, 14);
			panelCocheCom.add(lblTipoCoche);
			panelCocheCom.add(cbTipo);
		
		
			JLabel lblcategoriaCocheCombustion = new JLabel("Categoria");
			lblcategoriaCocheCombustion.setBounds(649, 78, 102, 14);
			panelCocheCom.add(lblcategoriaCocheCombustion);
		
			comboBoxCategoriaCocheCombustion.setBounds(649, 95, 143, 22);
			panelCocheCom.add(comboBoxCategoriaCocheCombustion);
			
			oficinasCocheCombustion = MetodosGUI.creaDesplegable();
			oficinasCocheCombustion.setBounds(649, 145, 200, 20);
			panelCocheCom.add(oficinasCocheCombustion);
			
			JLabel lblOfiElectrico = new JLabel("Oficina");
			lblOfiElectrico.setBounds(649, 128, 55, 14);
			panelCocheCom.add(lblOfiElectrico);
			
			JLabel lblEmisiones = new JLabel("Emisiones");
			lblEmisiones.setBounds(32, 125, 55, 20);
			panelCocheCom.add(lblEmisiones);
			
			cbEmisiones = Handlers.DesplegableEmisiones();
			cbEmisiones.setBounds(108, 124, 46, 22);
			panelCocheCom.add(cbEmisiones);
	}



	private void creaFormuFurgo(JTabbedPane EspecialidadVehiculos) {
		panelFurgo = new JPanel();
		EspecialidadVehiculos.addTab("Furgoneta", null, panelFurgo, null);
		
		{
			JLabel Carga = new JLabel("Capacidad de carga");
			panelFurgo.add(Carga);
		}
		{
			tbCapacidadCarga = new JTextField();
			tbCapacidadCarga.setColumns(10);
			panelFurgo.add(tbCapacidadCarga);
		}
		{
			JLabel lblCarnetReq = new JLabel("Carnet requerido");
			panelFurgo.add(lblCarnetReq);
		}
		{
			tbCarnetRequerido = Handlers.DesplegableCarnetRequeridoFurgo();
			panelFurgo.add(tbCarnetRequerido);
		}
		JLabel lblEmisionesFurgoneta = new JLabel("Emisiones");
		panelFurgo.add(lblEmisionesFurgoneta);
		
		cbEmisionesFurgo = Handlers.DesplegableEmisiones();
		cbEmisionesFurgo.setBounds(108, 124, 46, 22);
		panelFurgo.add(cbEmisionesFurgo);
	}



	private void creaFormuMoto(JTabbedPane EspecialidadVehiculos) {
		panelMoto = new JPanel();
		EspecialidadVehiculos.addTab("Moto", null, panelMoto, null);
		JLabel lblCilindrada = new JLabel("Cilindrada");
		panelMoto.add(lblCilindrada);
		panelMoto.add(tbCilindrada);
		pideCarnetRequeridoMoto(panelMoto);
	}



	private void pideCarnetRequeridoMoto(JPanel panelMoto) {
		{
			JLabel lblCarnetRequerido = new JLabel("Carnet de conducir necesario");
			panelMoto.add(lblCarnetRequerido);
		}
		{
			JPanel panelCarnetReqMoto = new JPanel();
			panelMoto.add(panelCarnetReqMoto);
			panelCarnetReqMoto.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

			creaRadioButtonsTipoCarnetMoto(panelCarnetReqMoto);
		}
	}



	private void pideKilometros() {
		{
			JLabel lblkm = new JLabel("Kil\u00F3metros");
			panelGenerico2.add(lblkm);
		}
		{
			tbKilometros = new JTextField();
			panelGenerico2.add(tbKilometros);
			tbKilometros.setToolTipText("\u00BFCu\u00E1ntos kil\u00F3metros tiene recorridos?");
			tbKilometros.setColumns(10);
		}
	}



	private void pideFechaAlta() {
		{
			JLabel lblFechaAlta = new JLabel("Fecha de alta");
			panelGenerico2.add(lblFechaAlta);
		}
		{
			tbFechaAlta = new JDateChooser();
			tbFechaAlta.setDateFormatString("dd MM yyyy");
			panelGenerico2.add(tbFechaAlta);
		}
	}



	private void pideColor() {
		
			JLabel lblColor = new JLabel("Color");
			panelGenerico2.add(lblColor);
			tbColor = new JTextField();
			tbColor.setColumns(10);
			panelGenerico2.add(tbColor);
	
	}





	private void pideMatricula(JPanel panel) {
		{
			JLabel lblMat = new JLabel("Matr\u00EDcula");
			panel.add(lblMat);
		}
		{
			tbMatricula = new JTextField();
			tbMatricula.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					//Si presiona Enter: 
					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						//Rellenamos los campos según la base de datos
						Matricula matricula = null;
						try {
							matricula = RepositorioVehiculo.leeMatricula(tbMatricula.getText());
						} catch (LetrasMatriculaNoValidasException e1) {
							// mostramos una ventana de error
							JOptionPane.showMessageDialog(null,
													"Matrícula incorrecta",
													"Letras incorrectas",
													JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						} catch (NumeroMatriculaNoValidoException e1) {
							// mostramos el error por pantalla
							JOptionPane.showMessageDialog(null,
									"Matrícula incorrecta",
									"Números incorrectas",
									JOptionPane.ERROR_MESSAGE);
							e1.printStackTrace();
						}
						rellenaVehiculoFromBD(matricula);
						//Preparamos los campos para que pueda modificarlos
						MetodosGUI.estadoEditar((JPanel) getContentPane());
					}
				}

			});
			tbMatricula.setColumns(10);
			panel.add(tbMatricula);
		}
	}



	private void creaButtonPane() {
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						//TODO actualizar 
						//TODO rellenar un vehiculo segun los tributos rellenados
						RepositorioVehiculo.insertUpdate(null);
						//Volvemos al estado Inicial
						MetodosGUI.estadoInicial(panelCentral);
						tbMatricula.setEnabled(true);
					}
				});
				okButton.setActionCommand("Aceptar");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						MetodosGUI.estadoInicial((JPanel) getContentPane());
						//Devolvemos el formulario a su estado inicial
						goToEstadoInicialPanelesEspecializados();
						tbMatricula.setEnabled(true);
					}
				});
				cancelButton.setActionCommand("Cancelar");
				buttonPane.add(cancelButton);
			}
		}
	}

	private static void goToEstadoInicialPanelesEspecializados() {
		MetodosGUI.estadoInicial(panelCocheCom);
		cbTipo.setSelectedIndex(-1);
		comboBoxCategoriaCocheCombustion.setSelectedIndex(-1);
		oficinasCocheCombustion.setSelectedIndex(-1);
		MetodosGUI.estadoInicial(panelCocheElectrico);
		cbTipoCocheElectrico.setSelectedItem(-1);
		oficinasCocheElectrico.setSelectedIndex(-1);
		cbTipoCocheElectrico.setSelectedIndex(-1);
		MetodosGUI.estadoInicial(panelFurgo);
		MetodosGUI.estadoInicial(panelMoto);
	}

	private void creaRadioButtonsTipoCarnetMoto(JPanel panelCarnetReqMoto) {
		
		gcarnetMoto = new ButtonGroup();
		
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
	
	public static void showDialog()
	{
		FormuVehiculos v = new FormuVehiculos();
		v.setVisible(true);
	}
	public static void showDialogModal()
	{
		FormuVehiculos v = new FormuVehiculos();
		v.setModal(true);
		v.setVisible(true);
	}
	private void rellenaVehiculoFromBD(Matricula mat) {
		// Primero realizamos la consulta según su matricula
		try {
			VehiculoDB automovil = RepositorioVehiculo.leeVehiculo(mat);
			if (automovil !=null)
			{
				tbMarca.setText(automovil.getMarca());
				tbModelo.setText(automovil.getModelo());
				tbColor.setText(automovil.getColor());
				String kms = ""+automovil.getKms();
				tbKilometros.setText(kms);
				Date fecha = Helpers.GregorianCalendarToDate(automovil.getFechaAlta());
				tbFechaAlta.setDate(fecha);
				try {
					switch (RepositorioVehiculo.tipoVehiculoBD(mat))
					{
					case "CocheCombustion":
						especialidadVehiculos.setSelectedIndex(2);
						rellenaPanelCocheCom(mat);
						break;
					case "CocheElectrico":
						especialidadVehiculos.setSelectedIndex(3);
						rellenaPanelCocheElectrico(mat);
						break;
					case "Furgoneta":
						especialidadVehiculos.setSelectedIndex(1);
						rellenaPanelFurgo(mat);
						break;
					case "Moto":
						especialidadVehiculos.setSelectedIndex(0);
						break;
					default:
						JOptionPane.showMessageDialog(cbTipo,
													  "Tipo de vehículo no reconocido",
													  "ERROR",JOptionPane.ERROR_MESSAGE);
						break;
					}
				} catch (EmisionesNoValidasException | NumPlazasNoValidoException | ConsumoNoValidoException
						| PotenciaNoValidaException | TipoNoValidoException e) {
					// Imprimimos la traza
					e.printStackTrace();
				}
			}
		} catch (RecargoNoValidoException e) {
			// Pintamos la traza
			e.printStackTrace();
		}
	}



	private void rellenaPanelFurgo(Matricula mat) {
		// Consultamos en la base de datos
				FurgonetaBD auto = RepositorioVehiculo.leeFurgo(mat);

				// Rellenamos sus textFields
				String carga = ""+auto.getCapacidadCarga();
				tbCapacidadCarga.setText(carga);
				tbCarnetRequerido.setSelectedItem(auto.getCarnetRequerido());
	//TODO emisiones y mas
	}



	private void rellenaPanelCocheElectrico(Matricula mat) {
		// Consultamos en la base de datos
		CocheElectrico auto = RepositorioVehiculo.leeCocheElectrico(mat);

		// Rellenamos sus textFields
		String numPlazas = ""+auto.getNumPlazas();
		tbNumPlazasCocheElectrico.setText(numPlazas);
		cbTipo.setSelectedItem(auto.getTipo());
		comboBoxCategoriaCocheCombustion.setSelectedItem(auto.getCategoria());
		oficinasCocheElectrico.setSelectedItem(auto.getOficina()); 
	}



	private void rellenaPanelCocheCom(Matricula mat) {
		//Obtenemos el coche de combustion
		CocheCombustion auto = null;
		try {
			auto = RepositorioVehiculo.leeCocheComb(mat);
		} catch (EmisionesNoValidasException | NumPlazasNoValidoException | ConsumoNoValidoException
				| PotenciaNoValidaException | TipoNoValidoException | RecargoNoValidoException e) {
			e.printStackTrace();
		}
		// Rellenamos sus textFields
		String numPlazas = ""+auto.getNumPlazas();
		tbNumPlazasCocheComb.setText(numPlazas);
		cbTipo.setSelectedItem(auto.getTipo());
		comboBoxCategoriaCocheCombustion.setSelectedItem(auto.getCategoria());
		oficinasCocheCombustion.setSelectedItem(auto.getOficina());
	}
}
