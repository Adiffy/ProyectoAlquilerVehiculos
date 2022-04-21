package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import clasesObjetos.Oficina;

import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import accesoADatos.RepositorioOficina;

import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FormuOficinas extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField tbCodOfi;
	private JTextField tbDesc;
	private JTextField tbProv;
	private JLabel lblProv;
	private JLabel lblDesc;
	private JLabel lblCodOfi;
	private JTextField tbLoc;
	private JTable tablaOficinas;
	private static Oficina OfiElegida;
	private JCheckBox chckbxDeAeropuerto;


	/**
	 * Construye el JDialog.
	 */
	private FormuOficinas() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos\\Logo.png"));
		setTitle("Oficinas");
		setModal(true);
		setBounds(100, 100, 688, 336);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(new BorderLayout(0, 0));
		{
			JPanel panelLateral = new JPanel();
			contentPanel.add(panelLateral, BorderLayout.WEST);
			panelLateral.setLayout(new BorderLayout(0, 0));
			{
				JPanel panel = new JPanel();
				panelLateral.add(panel, BorderLayout.NORTH);
				{
					JPanel panelCodOfi = new JPanel();
					panel.add(panelCodOfi);
					{
						lblCodOfi = new JLabel("C\u00F3digo de la oficina");
						panelCodOfi.add(lblCodOfi);
					}
					{
						tbCodOfi = new JTextField();
						tbCodOfi.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								//Intro tras escribir un código de una oficina
								OfiElegida = RepositorioOficina.leeOficina(tbCodOfi.getSelectedText());
								//Comprobamos si existe
								if (OfiElegida != null)
								{
									//Si existe rellenamos los datos
									tbDesc.setText(OfiElegida.getDescripción());
									tbProv.setText(OfiElegida.getProvincia());
									tbLoc.setText(OfiElegida.getLocalidad());
									chckbxDeAeropuerto.setSelected(MetodosGUI.isSelected(OfiElegida));
								}
								//Exista o no activamos el resto de funciones
								tbDesc.setEnabled(true);
								chckbxDeAeropuerto.setEnabled(true);
								tbLoc.setEnabled(true);
								tbProv.setEnabled(true);
							}
						});
						tbCodOfi.setToolTipText("El c\u00F3digo de una oficina\r\nPor ejemplo: 'MA01'");
						lblCodOfi.setLabelFor(tbCodOfi);
						tbCodOfi.setColumns(10);
						panelCodOfi.add(tbCodOfi);
					}
				}
			}
			{
				JPanel panel = new JPanel();
				panelLateral.add(panel, BorderLayout.SOUTH);
				{
					JPanel panelAbajo = new JPanel();
					panel.add(panelAbajo);
					{
						lblProv = new JLabel("Provincia");
						panelAbajo.add(lblProv);
					}
					{
						tbProv = new JTextField();
						tbProv.setEnabled(false);
						lblProv.setLabelFor(tbProv);
						panelAbajo.add(tbProv);
						tbProv.setColumns(10);
					}
				}
				{
					JPanel panelLoc = new JPanel();
					panel.add(panelLoc);
					{
						JLabel lblLoc = new JLabel("Localidad");
						panelLoc.add(lblLoc);
					}
					{
						tbLoc = new JTextField();
						tbLoc.setEnabled(false);
						panelLoc.add(tbLoc);
						tbLoc.setColumns(10);
					}
				}
			}
			{
				JPanel panel = new JPanel();
				panelLateral.add(panel, BorderLayout.WEST);
				{
					JPanel panelDesc = new JPanel();
					panel.add(panelDesc);
					{
						lblDesc = new JLabel("Descripci\u00F3n");
						panelDesc.add(lblDesc);
					}
					{
						tbDesc = new JTextField();
						tbDesc.setEnabled(false);
						lblDesc.setLabelFor(tbDesc);
						tbDesc.setColumns(10);
						panelDesc.add(tbDesc);
					}
				}
				{
					JPanel panelDeAeropuerto = new JPanel();
					panel.add(panelDeAeropuerto);
					{
						chckbxDeAeropuerto = new JCheckBox("Oficina de aeropuerto");
						chckbxDeAeropuerto.setEnabled(false);
						panelDeAeropuerto.add(chckbxDeAeropuerto);
					}
				}
			}
		}
		{
			tablaOficinas = new JTable();
			tablaOficinas.setModel(new DefaultTableModel(
				new Object[][] {
					{null, null, null, null, null},
				},
				new String[] {
					"Codigo", "Descripci\u00F3n", "Aeropuerto", "Provincia", "Localidad"
				}
			));
			tablaOficinas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
			contentPanel.add(tablaOficinas, BorderLayout.CENTER);
		}
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

}
