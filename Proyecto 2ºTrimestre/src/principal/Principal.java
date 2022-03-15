package principal;

import java.util.Scanner;

import InterfazUsuario.Menus;
import accesoADatos.Serializar;
import clasesObjetos.Empresa;
import exceptions.CarnetRequeridoInvalidoException;
import exceptions.CilindradaNoValidaException;
import exceptions.CodigoPostalException;
import exceptions.ConsumoNoValidoException;
import exceptions.EmisionesNoValidasException;
import exceptions.FechaNoValidaException;
import exceptions.LetrasMatriculaNoValidasException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;
import exceptions.LongitudNoValidaException;
import exceptions.NumPlazasNoValidoException;
import exceptions.NumeroMatriculaNoValidoException;
import exceptions.PlantaNoValidaException;
import exceptions.PotenciaNoValidaException;
import exceptions.RecargoNoValidoException;
import exceptions.TiempoRecargaNoValidoException;
import exceptions.TipoNoValidoException;

public class Principal {

	
	public static void main(String[] args) throws TipoNoValidoException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TiempoRecargaNoValidoException {
		
		
		Empresa empresa = Serializar.LeeEmpresa();	//intenta leer y si no puede crea la empresa
		
//			Empresa empresa = new Empresa();	//Creamos una empresa
		
		Scanner lector = new Scanner(System.in);
	
		//	String errorCrear = "Error al crear vehículo - "+ Object.class;
		
		try {
			Menus.principal(empresa, lector);
		} catch (TipoNoValidoException | TiempoRecargaNoValidoException | LicenciaNoValidaException
				| EmisionesNoValidasException | ConsumoNoValidoException | PotenciaNoValidaException
				| RecargoNoValidoException | LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException
				| NumPlazasNoValidoException | LongitudNoValidaException | PlantaNoValidaException
				| CodigoPostalException | LongitudCadenaNoValidaException | CilindradaNoValidaException
				| CarnetRequeridoInvalidoException | FechaNoValidaException e) {
			
			e.printStackTrace();
		}catch (NullPointerException a)	//Error de TreeMap vacio
		{
			System.out.println("ERROR | Contenido de la lista nulo");
			a.printStackTrace();
//			a.getLocalizedMessage();
		}
		
		
		
		
	}

}
