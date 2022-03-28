package principal;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class VentanaResultado extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	private final JPanel contentPanel = new JPanel();

	/*		SI FUESE MAIN
	public static void main(String[] args) {
		try {
			VentanaResultado dialog = new VentanaResultado();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}*/

	/**
	 * Crea la ventana de diálogo.
	 */
	public VentanaResultado() {
		setAlwaysOnTop(true);
		setTitle("RESULTADO");
		setResizable(false);
		setBounds(100, 100, 381, 152);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		{
			JLabel labelResultado = new JLabel("");
			labelResultado.setHorizontalAlignment(SwingConstants.CENTER);
			contentPanel.add(labelResultado);
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

}
