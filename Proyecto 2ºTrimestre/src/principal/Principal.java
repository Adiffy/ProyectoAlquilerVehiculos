package principal;

import java.util.Scanner;

import InterfazUsuario.BarraDeCarga;
import InterfazUsuario.PideDato;
import clasesObjetos.*;
import exceptions.CarnetRequeridoInvalidoException;
import exceptions.CilindradaNoValidaException;
import exceptions.CodigoPostalException;
import exceptions.ConsumoNoValidoException;
import exceptions.EmisionesNoValidasException;
import exceptions.LetrasMatriculaNoValidasException;
import exceptions.LongitudCadenaNoValidaException;
import exceptions.LongitudNoValidaException;
import exceptions.NumPlazasNoValidoException;
import exceptions.NumeroMatriculaNoValidoException;
import exceptions.PlantaNoValidaException;
import exceptions.PotenciaNoValidaException;
import exceptions.RecargoNoValidoException;
import exceptions.TiempoRecargaNoValidoException;
import exceptions.TipoNoValidoException;
import metodos.Menus;

public class Principal {

	
	public static void main(String[] args) throws TipoNoValidoException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TiempoRecargaNoValidoException {
		
		Empresa empresa = new Empresa();	//Creamos una empresa
		Scanner lector = new Scanner(System.in);
	
		//	String errorCrear = "Error al crear vehículo - "+ Object.class;
		
		Menus.principal(empresa, lector);
		
		
		
		
	}

}
