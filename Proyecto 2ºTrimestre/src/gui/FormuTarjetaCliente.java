package gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import accesoADatos.RepositorioCliente;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * Clase Minúscula, para crear un formulario que ingresa un nuevo número de tarjeta para
 * los clientes
 * @author Victor
 *
 */
public class FormuTarjetaCliente extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel contentPanel = new JPanel();
	private static JTextField tbNum;
	private static int numTarjeta;
	private static FormuTarjetaCliente yomismo;
	

	/**
	 * Crea el JDialog.
	 */
	public FormuTarjetaCliente() {
		setIconImage(Toolkit.getDefaultToolkit().getImage(FormuTarjetaCliente.class.getResource("/icons/miniLogo.png")));
		setBounds(100, 100, 325, 116);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel lblNewLabel = new JLabel("Número de tarjeta de socio: ");
			contentPanel.add(lblNewLabel);
		}
		{
			tbNum = new JTextField();
			tbNum.addKeyListener(new KeyAdapter() {
				@Override
				public void keyPressed(KeyEvent e) {
					if (e.getKeyCode()==KeyEvent.VK_ENTER)
					{
						int num = Integer.parseInt(tbNum.getText());
								if (RepositorioCliente.leeTarjeta(num)==-1)	//Si no existe
								{
									//Creamos el número
									numTarjeta = num;
									yomismo.setVisible(false);
									
								}else {
									JOptionPane.showMessageDialog(
											null, "Longitud del DNI invólida",
											"Error",JOptionPane.ERROR_MESSAGE);
								}
							}
				}
			});
			tbNum.setToolTipText("Un número de cliente");
			contentPanel.add(tbNum);
			tbNum.setColumns(10);
		}
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("Aceptar");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancelar");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
	
	public static int showDialog()
	{
		FormuTarjetaCliente v = new FormuTarjetaCliente();
		v.setVisible(true);
		yomismo = v;
		return numTarjeta;
	}

	public static int showDialogModal()
	{
		FormuTarjetaCliente v = new FormuTarjetaCliente();
		v.setVisible(true);
		v.setModal(true);
		yomismo = v;
		return numTarjeta;
	}
}
