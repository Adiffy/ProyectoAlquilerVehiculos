package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesObjetos.Empleado;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel contentPane;



	/**
	 * Constructor
	 * Crea el Jframe.
	 */
	public VentanaPrincipal() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		
		JMenuBar menuPrincipal = new JMenuBar();
		setJMenuBar(menuPrincipal);
		
		JMenu mnFicherosMaestros = new JMenu("Ficheros maestros");
		menuPrincipal.add(mnFicherosMaestros);
		
		JMenu mnVehiculos = new JMenu("Vehiculos");
		mnFicherosMaestros.add(mnVehiculos);
		
		JMenu mncoche = new JMenu("Coches");
		mnVehiculos.add(mncoche);
		
		JMenuItem mntmDeCombustion = new JMenuItem("De combusti\u00F3n");
		mncoche.add(mntmDeCombustion);
		
		JMenuItem mntmElectrico = new JMenuItem("El\u00E9ctricos");
		mncoche.add(mntmElectrico);
		
		JMenuItem mntmFurgo = new JMenuItem("Furgonetas");
		mnVehiculos.add(mntmFurgo);
		
		JMenuItem mntmMoto = new JMenuItem("Motos");
		mnVehiculos.add(mntmMoto);
		
		JMenu mnPersonas = new JMenu("Personas");
		mnFicherosMaestros.add(mnPersonas);
		
		JMenuItem mntmEmpleado = new JMenuItem("Empleados");
		mnPersonas.add(mntmEmpleado);
		
		JMenuItem mntmClientes = new JMenuItem("Clientes");
		mnPersonas.add(mntmClientes);
		
		JMenuItem mntmOficinas = new JMenuItem("Oficinas");
		mnFicherosMaestros.add(mntmOficinas);
		
		JMenu mnListados = new JMenu("Listados");
		menuPrincipal.add(mnListados);
		mntmEmpleado.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				@SuppressWarnings("unused")
				Empleado Emp = FormuEmpleado.showDialog();				
			}
		});
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		
		
		//Creamos el logo
		JLabel lblLogo = new JLabel("");
		lblLogo.setIcon(new ImageIcon("recursos\\Europcar-Logo.svg.png"));
		//Lo ponemos de fondo
		contentPane.add(lblLogo);
		//Ponemos color de fondo igual que el de la imagen
		contentPane.setBackground(new Color(14,129,2,255));
		setContentPane(contentPane);

	}

}
