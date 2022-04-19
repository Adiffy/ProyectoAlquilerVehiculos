package principal;

import javax.swing.*;
import accesoADatos.EmpresaDB;
import gui.VentanaPrincipal;

public class PrincipalGrafico {

	public static void main(String[] args) {
		
		//Abrimos conexión
		EmpresaDB.abreConexion();
		
		// Iniciamos la primera ventana
		try {
			VentanaPrincipal frame = new VentanaPrincipal();
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);	//La ventana se abre maximizada
			frame.setVisible(true);
		} catch (Exception e) {			//Si no puede abrir la ventana muestra error
			e.printStackTrace();
		}
	}

}
