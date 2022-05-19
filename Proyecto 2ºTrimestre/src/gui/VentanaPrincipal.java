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

import clasesObjetos.Empleado;
import clasesObjetos.Oficina;
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
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos\\Logo.png"));
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuPrincipal = new JMenuBar();
		setJMenuBar(menuPrincipal);
		
		JMenu mnFicherosMaestros = new JMenu("Ficheros maestros");
		menuPrincipal.add(mnFicherosMaestros);
		
		JMenuItem mnVehiculos = new JMenuItem("Vehiculos");
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
		mnFicherosMaestros.add(mnPersonas);
		
		JMenuItem mntmEmpleado = new JMenuItem("Empleados");
		mnPersonas.add(mntmEmpleado);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mntmClientes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormuCliente.showDialog();
			}
		});
		mnPersonas.add(mntmClientes);
		
		JMenuItem mntmOficinas = new JMenuItem("Oficinas");
		mnFicherosMaestros.add(mntmOficinas);
		
		JMenuItem mntmCategorias = new JMenuItem("Categor\u00EDas");
		mntmCategorias.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormuCategoria.showFormuCat();;
				
			}
		});
		mnFicherosMaestros.add(mntmCategorias);
		mntmOficinas.addActionListener(new ActionListener() 
				{
					public void actionPerformed(ActionEvent e) {
						@SuppressWarnings("unused")
						Oficina ofi = FormuOficina.showDialog();
					}
				});
		
		JMenu mnListados = new JMenu("Listados");
		menuPrincipal.add(mnListados);
		
		JMenuItem mntmTablaEmpleados = new JMenuItem("Empleados");
		mntmTablaEmpleados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaEmpleado.showDialog();
			}
		});
		
		JMenuItem mntmTablaOficinas = new JMenuItem("Oficinas");
		mntmTablaOficinas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaOficina.showTablaOficinas();
			}
		});
		mnListados.add(mntmTablaOficinas);
		mnListados.add(mntmTablaEmpleados);
		
		JMenuItem mntmCliente = new JMenuItem("Clientes");
		mntmCliente.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TablaCliente.showDialogModal();
			}
		});
		mnListados.add(mntmCliente);
		
		JMenuItem mntmAlquiler = new JMenuItem("Realizar alquiler");
		mntmAlquiler.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				FormuAlquiler.showFrame();
			}
		});
		menuPrincipal.add(mntmAlquiler);
		mntmEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				Empleado Emp = FormuEmpleado.showDialog();				
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
