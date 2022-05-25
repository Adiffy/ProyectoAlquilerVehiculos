package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel panelPrincipal;



	/**
	 * Constructor
	 * Crea el Jframe.
	 */
	public VentanaPrincipal() {
		
		setTitle("Alquiler de vehículos");
		setIconImage(Toolkit.getDefaultToolkit().getImage(VentanaPrincipal.class.getResource("/icons/miniLogo.png")));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuPrincipal = new JMenuBar();
		setJMenuBar(menuPrincipal);
		
		JMenu mnFicherosMaestros = new JMenu("Ficheros maestros");
		mnFicherosMaestros.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/file.png")));
		menuPrincipal.add(mnFicherosMaestros);
		
		JMenuItem mnVehiculos = new JMenuItem("Vehiculos");
		mnVehiculos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/network.png")));
		mnVehiculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				FormuVehiculo ventana = new FormuVehiculo();
//				ventana.setVisible(true);
				FormuVehiculos.showDialog();
			}
		});
		mnFicherosMaestros.add(mnVehiculos);
		
//		JMenu mncoche = new JMenu("Coches");
//		mnVehiculos.add(mncoche);
		
//		JMenuItem mntmDeCombustion = new JMenuItem("De combusti\u00F3n");
//		mncoche.add(mntmDeCombustion);
//		
//		JMenuItem mntmElectrico = new JMenuItem("El\u00E9ctricos");
//		mncoche.add(mntmElectrico); 
		
//		JMenuItem mntmFurgo = new JMenuItem("Furgonetas");
//		mnVehiculos.add(mntmFurgo);
//		
//		JMenuItem mntmMoto = new JMenuItem("Motos");
//		mnVehiculos.add(mntmMoto);
		
		JMenu mnPersonas = new JMenu("Personas");
		mnPersonas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/group.png")));
		mnFicherosMaestros.add(mnPersonas);
		
		JMenuItem mntmEmpleado = new JMenuItem("Empleados");
		mntmEmpleado.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/chat.png")));
		mnPersonas.add(mntmEmpleado);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/clients.png")));
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormuCliente.showDialog();
			}
		});
		mnPersonas.add(mntmClientes);
		
		JMenuItem mntmOficinas = new JMenuItem("Oficinas");
		mntmOficinas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/home.png")));
		mnFicherosMaestros.add(mntmOficinas);
		
		JMenuItem mntmCategorias = new JMenuItem("Categor\u00EDas");
		mntmCategorias.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/paste.png")));
		mntmCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormuCategoria.showFormuCat();;
				
			}
		});
		mnFicherosMaestros.add(mntmCategorias);
		mntmOficinas.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
						FormuOficinas.showDialog();
					}
				});
		
		JMenu mnListados = new JMenu("Listados");
		mnListados.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/folder.png")));
		menuPrincipal.add(mnListados);
		
		JMenuItem mntmTablaEmpleados = new JMenuItem("Empleados");
		mntmTablaEmpleados.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/chat.png")));
		mntmTablaEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaEmpleado.showDialog();
			}
		});
		
		JMenuItem mntmTablaOficinas = new JMenuItem("Oficinas");
		mntmTablaOficinas.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/home.png")));
		mntmTablaOficinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaOficina.showTablaOficinas();
			}
		});
		
		JMenuItem mntmListadoAlquileres = new JMenuItem("Alquileres");
		mntmListadoAlquileres.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/shopping_cart.png")));
		mntmListadoAlquileres.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaAlquiler.showDialog();
			}
		});
		mnListados.add(mntmListadoAlquileres);
		mnListados.add(mntmTablaOficinas);
		mnListados.add(mntmTablaEmpleados);
		
		JMenuItem mntmCliente = new JMenuItem("Clientes");
		mntmCliente.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/clients.png")));
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaCliente.showDialogModal();
			}
		});
		mnListados.add(mntmCliente);
		
		JMenuItem mntmListadoCategorias = new JMenuItem("Categorias");
		mntmListadoCategorias.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/paste.png")));
		mntmListadoCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Mostramos el listado de las categorías
				TablaCategoria.showDialog();
			}
		});
		mnListados.add(mntmListadoCategorias);
		
		JMenu mnVehiculosGeneral = new JMenu("Vehículos");
		mnVehiculosGeneral.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/network.png")));
		mnListados.add(mnVehiculosGeneral);
		
		JMenuItem mntmTablaVehiculos = new JMenuItem("Vehiculos");
		mntmTablaVehiculos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/book.png")));
		mntmTablaVehiculos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Mostramos el listado de un vehículo
				TablaVehiculos.showDialog();
			}
		});
		mnVehiculosGeneral.add(mntmTablaVehiculos);
		
		JMenuItem mntmTablaCocheComb = new JMenuItem("Coche de combustión");
		mntmTablaCocheComb.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/clock.png")));
		mntmTablaCocheComb.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Listamos la tabla
				TablaCocheComb.showDialog();
			}
		});
		mnVehiculosGeneral.add(mntmTablaCocheComb);
		
		JMenuItem mntmTablaCocheElectrico = new JMenuItem("Coche eléctrico");
		mntmTablaCocheElectrico.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/webcam.png")));
		mntmTablaCocheElectrico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaCocheElectrico.showDialog();
			}
		});
		mnVehiculosGeneral.add(mntmTablaCocheElectrico);
		
		JMenuItem mntmListaMotos = new JMenuItem("Moto");
		mntmListaMotos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaMoto.showDialog();
			}
		});
		mntmListaMotos.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/clip.png")));
		mnVehiculosGeneral.add(mntmListaMotos);
		
		JMenuItem mntmAlquiler = new JMenuItem("Realizar alquiler");
		mntmAlquiler.setIcon(new ImageIcon(VentanaPrincipal.class.getResource("/16/windows.png")));
		mntmAlquiler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormuAlquiler.showFrame();
			}
		});
		
		JMenu mnNewMenu_1 = new JMenu("");
		menuPrincipal.add(mnNewMenu_1);
		menuPrincipal.add(mntmAlquiler);
		
		JMenu mnNewMenu = new JMenu("");
		menuPrincipal.add(mnNewMenu);
		mntmEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				FormuEmpleado.showDialog();				
			}
		});
		panelPrincipal = new JPanel();
		panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		
		//Creamos el logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("recursos\\Europcar-Logo.svg.png"));
		//Lo ponemos de fondo
		panelPrincipal.add(lblLogo);
		//Ponemos color de fondo igual que el de la imagen
		panelPrincipal.setBackground(new Color(14,129,2,255));
		setContentPane(panelPrincipal);

	}
	
	public static void showFrame()
	{
		VentanaPrincipal alquileres = new VentanaPrincipal();
		alquileres.setExtendedState(JFrame.MAXIMIZED_BOTH); //La ventana se abre maximizada
		alquileres.setVisible(true);
	}

}
