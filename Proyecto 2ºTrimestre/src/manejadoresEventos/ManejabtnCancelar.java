package manejadoresEventos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Clase para manejar el evento del bot�n cancelar
 * que ser� siempre igual
 */
public class ManejabtnCancelar implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// Averiguamos qui�n nos ha llamado con getActionCommand()

		String boton = e.getActionCommand();	

		if (boton=="Cancelar")
		{
			
		}
		// Ocultamos el JFrame / JDialog
//		setVisible(false);
	}

}
