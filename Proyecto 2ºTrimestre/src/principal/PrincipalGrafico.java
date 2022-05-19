package principal;

import accesoADatos.EmpresaDB;
import gui.VentanaPrincipal;

public class PrincipalGrafico {

	public static void main(String[] args) {
		
		//Abrimos conexi�n
		EmpresaDB.abreConexion();
		
		// Iniciamos la primera ventana
		VentanaPrincipal.showFrame();
			
	}

}
