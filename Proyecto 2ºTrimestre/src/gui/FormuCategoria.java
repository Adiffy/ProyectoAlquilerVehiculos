package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoADatos.RepositorioCategoria;
import clasesObjetos.Categoria;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JSlider;
import javax.swing.ImageIcon;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class FormuCategoria extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private final JPanel contentPanel = new JPanel();
	private static JTextField tbCod;
	private static JTextArea tbDesc;
	private static JSlider sliderRecargo;
	private static Categoria CatElegida;

	private JButton okButton;


	/**
	 * Constructor privado
	 */
	private FormuCategoria() {
		declaraAtributosGenerales();
		creaPanelCodigo();
		creaPanelDesc();
		creaSliderRecargo();
		creaButtonPane();
		//Lo ponemos en su estado inicial
		MetodosGUI.estadoInicial(contentPanel);
		tbCod.setEnabled(true);
		tbDesc.setEnabled(false);

	}

	private void creaButtonPane() {
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			creaBotonAceptar(buttonPane);
			creaBotonBorrar(buttonPane);
			creaBotonCancelar(buttonPane);
			okButton = new JButton("Aceptar");
			okButton.setIcon(new ImageIcon(FormuCategoria.class.getResource("/16/diskette.png")));
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//Realizamos el insert / update
					if (RepositorioCategoria.insertCategoria(CatElegida))
					{
						//Devolvemos el formulario a su estado inicial
						MetodosGUI.estadoInicial(contentPanel);

						vaciaTbDesc();
					}else {
						JOptionPane.showMessageDialog(okButton, "Error al intentar crear / actualizar la oficina con código "+CatElegida.getCodigo()+" en la base de datos.","DataBase Error",JOptionPane.ERROR_MESSAGE);
					}
				}
			});

			okButton.setActionCommand("Aceptar");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);

		}
	}

	private void creaBotonCancelar(JPanel buttonPane) {
		JButton cancelButton = new JButton("Cancelar");
		cancelButton.setIcon(new ImageIcon(FormuCategoria.class.getResource("/16/cross.png")));
		cancelButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//Quitamos todos los parametros de los campos de texto y volvemos al estado inicial
				MetodosGUI.estadoInicial(contentPanel);
				vaciaTbDesc();
			}

		});
		cancelButton.setActionCommand("Cancelar");
		buttonPane.add(cancelButton);
	}

	private void creaBotonAceptar(JPanel buttonPane) {
	}

	private void creaBotonBorrar(JPanel buttonPane) {
		{
			JButton btnBorrar = new JButton("Borrar");
			btnBorrar.setIcon(new ImageIcon(FormuCategoria.class.getResource("/16/recycle_bin.png")));
			btnBorrar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rellenaCategoria();

					RepositorioCategoria.borraCategoria(CatElegida.getCodigo());
				}
			});
			//					btnBorrar.setIcon(new ImageIcon("recursos\\garbage_can.png"));
			buttonPane.add(btnBorrar);
		}
	}

	private void creaPanelDesc() {
		JScrollPane panelDesc = new JScrollPane();
		panelDesc.setBounds(104, 64, 195, 57);
		contentPanel.add(panelDesc);

		creaTextAreaDescripcion(panelDesc);
		creaLabelDescripcion();
	}

	private void creaLabelDescripcion() {
		{
			JLabel lblDesc = new JLabel("Descripci\u00F3n");
			lblDesc.setIcon(new ImageIcon(FormuCategoria.class.getResource("/16/clipboard.png")));
			lblDesc.setBounds(10, 64, 89, 24);
			contentPanel.add(lblDesc);
		}
	}

	private void creaTextAreaDescripcion(JScrollPane panelDesc) {
		tbDesc = new JTextArea();
		panelDesc.setViewportView(tbDesc);
	}

	private void creaPanelCodigo() {
		{
			JPanel panelCat = new JPanel();
			panelCat.setBounds(10, 11, 427, 42);
			contentPanel.add(panelCat);
			creaLabelCodigo(panelCat);
			creaTbCodCategoria(panelCat);

			JButton btnBuscar = new JButton("");
			btnBuscar.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					rellenaCategoria();
					CatElegida = TablaCategoria.showDialogModal();
				}
			});
			btnBuscar.setIcon(new ImageIcon(FormuCategoria.class.getResource("/16/zoom.png")));
			panelCat.add(btnBuscar);
		}
	}

	private void creaLabelCodigo(JPanel panelCat) {
		{
			JLabel lblCod = new JLabel("C\u00F3digo");
			lblCod.setIcon(new ImageIcon(FormuCategoria.class.getResource("/16/pencil.png")));
			panelCat.add(lblCod);
		}
	}

	private void creaTbCodCategoria(JPanel panelCat) {
		{
			tbCod = new JTextField();
			tbCod.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						//Si existe la categoría
						if (RepositorioCategoria.leeCategoria(tbCod.getText())!=null)
						{
							//Rellenamos los componentes
							rellenaCategoria();
						}	//Exista o no (Sí o sí)
						//Preparamos los componentes 
						MetodosGUI.estadoEditar(contentPanel);
						tbDesc.setEnabled(true);
						okButton.setEnabled(true);
					}
				}
			});
			panelCat.add(tbCod);
			tbCod.setColumns(10);
		}
	}

	private void declaraAtributosGenerales() {
		setTitle("Nueva categoría");
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormuCategoria.class.getResource("/icons/miniLogo.png")));
		setBounds(100, 100, 463, 304);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
	}

	private void creaSliderRecargo() {
		JLabel lblRecargo = new JLabel("Porcentaje de recargo");
		lblRecargo.setIcon(new ImageIcon(FormuCategoria.class.getResource("/16/calculator.png")));
		lblRecargo.setBounds(10, 132, 195, 26);
		contentPanel.add(lblRecargo);

		sliderRecargo = new JSlider();
		sliderRecargo.setPaintTicks(true);
		sliderRecargo.setPaintLabels(true);
		sliderRecargo.setMajorTickSpacing(10);
		sliderRecargo.setToolTipText("Elija el porcentaje de recargo\r\n");
		sliderRecargo.setValue(0);
		sliderRecargo.setBounds(134, 169, 290, 52);
		contentPanel.add(sliderRecargo);
	}

	private static void rellenaCategoria()
	{
		//Obtenemos el código
		String cod = tbCod.getText();
		//Vemos si la categoría existe
		CatElegida = RepositorioCategoria.leeCategoria(cod);
		if (CatElegida!=null)
		{
			tbDesc.setText(CatElegida.getDescripcion());
			sliderRecargo.setValue((int) CatElegida.getRecargo());
		}
	}

	private static void vaciaTbDesc() {
		tbDesc.setText("");
		tbDesc.setEnabled(false);
	}

	public static void showFormuCat()
	{
		FormuCategoria v = new FormuCategoria();
		v.setVisible(true);		
	}
}
