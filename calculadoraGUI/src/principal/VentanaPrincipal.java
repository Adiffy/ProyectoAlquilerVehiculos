package principal;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaPrincipal extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	private JPanel PanelContenedor;
	private JTextField textField;
	private int izrda;	//El número a la izquierda
	private int operador; 	//Segun el operador que elija

	/**
	 * Abre la aplicación.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaPrincipal frame = new VentanaPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Crea la ventana.
	 */
	public VentanaPrincipal() {
		setResizable(false);
		setTitle("CALCULADORA\r\n");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 356, 203);
		PanelContenedor = new JPanel();
		PanelContenedor.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(PanelContenedor);
		PanelContenedor.setLayout(null);
		
		textField = new JTextField();
		textField.setBounds(65, 11, 184, 20);
		PanelContenedor.add(textField);
		textField.setColumns(10);
		
		JButton btnSuma = new JButton("+");
		btnSuma.addActionListener(new ActionListener() {


			public void actionPerformed(ActionEvent e) {
				String izqrda = textField.getText();	//Metemos lo que hay escrito en una variable
				textField.setText(""); 	//Borramos lo que hay escrito
				izrda = Integer.parseInt(izqrda);
				operador = 1;
			}
		});
		btnSuma.setBounds(207, 66, 42, 34);
		PanelContenedor.add(btnSuma);
		
		JButton btnDivide = new JButton(":");
		btnDivide.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izqrda = textField.getText();		//Metemos lo que tenemos hasta ahora en una variable
				textField.setText(""); 	//Borramos 
				izrda = Integer.parseInt(izqrda);
				operador = 4;
			}
		});
		btnDivide.setBounds(207, 42, 42, 23);
		PanelContenedor.add(btnDivide);
		
		JButton btnRestar = new JButton("-");
		btnRestar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izqrda = textField.getText();		//Metemos lo que tenemos hasta ahora en una variable
				textField.setText(""); 	//Borramos lo que hay escrito
				izrda = Integer.parseInt(izqrda);
				operador=2;
			}
		});
		btnRestar.setBounds(259, 42, 42, 23);
		PanelContenedor.add(btnRestar);
		
		JButton btnIgual = new JButton("=");
		btnIgual.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Cuando se pulsa el igual	
				String drcha = textField.getText();
				int derecha = Integer.parseInt(drcha);
				String operacion = "";
				switch (operador)
				{
				case 1:
					operacion = ""+(izrda + derecha) ;
					break;
				case 2:
					operacion = ""+(izrda - derecha) ;
					break;
				case 3:
					operacion = ""+(izrda * derecha) ;
					break;
				case 4:
					operacion = ""+(izrda / derecha) ;
				}
				
				JOptionPane.showMessageDialog(null, "El resultado es "+operacion);
				
			}
		});
		btnIgual.setBounds(259, 66, 42, 77);
		PanelContenedor.add(btnIgual);
		
		JButton btnUNO = new JButton("1");
		btnUNO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Pulsamos la tecla del 1
				String escrito = textField.getText();
				textField.setText(escrito+1);
			}
		});
		btnUNO.setBounds(65, 42, 42, 23);
		PanelContenedor.add(btnUNO);
		
		JButton btnDOS = new JButton("2");
		btnDOS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escrito = textField.getText();
				textField.setText(escrito+2);
			}
		});
		btnDOS.setBounds(110, 42, 42, 23);
		PanelContenedor.add(btnDOS);
		
		JButton btnTRES = new JButton("3");
		btnTRES.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escrito = textField.getText();
				textField.setText(escrito+3);
			}
		});
		btnTRES.setBounds(155, 42, 42, 23);
		PanelContenedor.add(btnTRES);
		
		JButton btnCUATRO = new JButton("4");
		btnCUATRO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escrito = textField.getText();
				textField.setText(escrito+4);
			}
		});
		btnCUATRO.setBounds(65, 72, 42, 23);
		PanelContenedor.add(btnCUATRO);
		
		JButton btnCINCO = new JButton("5");
		btnCINCO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escrito = textField.getText();
				textField.setText(escrito+5);
			}
		});
		btnCINCO.setBounds(110, 72, 42, 23);
		PanelContenedor.add(btnCINCO);
		
		JButton btnSEIS = new JButton("6");
		btnSEIS.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escrito = textField.getText();
				textField.setText(escrito+6);
			}
		});
		btnSEIS.setBounds(155, 72, 42, 23);
		PanelContenedor.add(btnSEIS);
		
		JButton btnSIETE = new JButton("7");
		btnSIETE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escrito = textField.getText();
				textField.setText(escrito+7);
			}
		});
		btnSIETE.setBounds(65, 101, 42, 23);
		PanelContenedor.add(btnSIETE);
		
		JButton btnOCHO = new JButton("8");
		btnOCHO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escrito = textField.getText();
				textField.setText(escrito+8);
			}
		});
		btnOCHO.setBounds(110, 101, 42, 23);
		PanelContenedor.add(btnOCHO);
		
		JButton btnNUEVE = new JButton("9");
		btnNUEVE.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escrito = textField.getText();
				textField.setText(escrito+9);
			}
		});
		btnNUEVE.setBounds(155, 101, 42, 23);
		PanelContenedor.add(btnNUEVE);
		
		JButton btnCERO = new JButton("0");
		btnCERO.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String escrito = textField.getText();
				textField.setText(escrito+0);
			}
		});
		btnCERO.setBounds(110, 130, 42, 23);
		PanelContenedor.add(btnCERO);
		
		JButton btnMultiplicar = new JButton("X");
		btnMultiplicar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String izqrda = textField.getText();		//Metemos lo que tenemos hasta ahora en una variable
				textField.setText(""); 	//Borramos 
				izrda = Integer.parseInt(izqrda);
				operador = 3;
			}
		});
		btnMultiplicar.setBounds(207, 101, 42, 42);
		PanelContenedor.add(btnMultiplicar);
		
		JButton btnBorrar = new JButton("C");
		btnBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textField.setText(""); 		//Lo vaciamos
			}
		});
		btnBorrar.setBounds(259, 6, 42, 31);
		PanelContenedor.add(btnBorrar);
	}
}
