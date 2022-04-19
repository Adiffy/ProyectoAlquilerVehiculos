package guiExcepciones;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class LongitudNoValida extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel panel = new JPanel();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		
	}

	/**
	 * Crea el JDialog.
	 */
	private LongitudNoValida() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("recursos\\Europcar-Logo.svg.png"));
		setModal(true);
		setBounds(100, 100, 328, 167);
		getContentPane().setLayout(new BorderLayout());
		setTitle("Error");
		setResizable(false);
		panel.setLayout(new FlowLayout());
		panel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(panel, BorderLayout.CENTER);
		{
			JLabel lblMensajeError = new JLabel("Longitud máxima superada");
			lblMensajeError.setIcon(new ImageIcon("recursos\\16\\cross.png"));
			
			JPanel buttonPane = new JPanel();
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton okButton = new JButton("OK");
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public static void showDialog()
	{
		try {
			LongitudNoValida dialog = new LongitudNoValida();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
