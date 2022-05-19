package manejadoresEventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para manejar el evento del botón cancelar
 * que será siempre igual
 */
public class ManejabtnCancelar implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// Averiguamos quién nos ha llamado con getActionCommand()

		String boton = e.getActionCommand();	

		if (boton=="Cancelar")
		{
			
		}
		// Ocultamos el JFrame / JDialog
//		setVisible(false);
	}

}
