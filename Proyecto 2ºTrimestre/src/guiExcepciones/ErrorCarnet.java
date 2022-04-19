package guiExcepciones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Toolkit;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

public class ErrorCarnet extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final JPanel panel = new JPanel();
	

	/**
	 * Constructor privado del JDialog.
	 */
	private ErrorCarnet() {
		setResizable(false);
		setTitle("Error");
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos\\Europcar-Logo.svg.png"));
		setModal(true);
		setBounds(100, 100, 328, 167);
		getContentPane().setLayout(new BorderLayout());
		panel.setLayout(new FlowLayout());
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		{
			JLabel lblMensajeError = new JLabel("Debe elegir un carnet v\u00E1lido");
			lblMensajeError.setIcon(new ImageIcon("recursos\\16\\cross.png"));
			panel.add(lblMensajeError);
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
	
	public static void showDialog()
	{
		try {
			ErrorCarnet Vdialog = new ErrorCarnet();
			Vdialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			Vdialog.setVisible(true);
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
