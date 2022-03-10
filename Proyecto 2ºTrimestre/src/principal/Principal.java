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

public class Principal {

	@SuppressWarnings("unused")
	public static void main(String[] args) throws TipoNoValidoException, RecargoNoValidoException, LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException, EmisionesNoValidasException, NumPlazasNoValidoException, ConsumoNoValidoException, PotenciaNoValidaException, LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException, TiempoRecargaNoValidoException {
		
		Empresa empresa = new Empresa();
		Scanner lector = new Scanner(System.in);
	String[] opciones = {"1.- Ficheros maestros", "2.- Gestión de empresa", "3.- ", "4.- Salir"};
	String opcValidas = "1234";
	String menError = "Seleccione una opción válida. Puede ser 1, 2, 3 ó 4"; //El mensaje que aparecerá si el usuario elige una opción inválida
		boolean noSale = true; //buleano para el bucle 
	
		Empresa EuropCar = new Empresa();
	
		do {
			switch (metodosMenu.Metodos.menu(opciones, opcValidas, "MENÚ PRINCIPAL", menError, lector))
			{
			case "1":
				switch (metodosMenu.Metodos.menu(opciones, opcValidas, "Ficheros maestros", menError, lector))
				{
				case "1":
					String[] opc = {"1.- Añadir / eliminar vehículos a la flota","2.- Modificar listado de clientes", "3.- Modificar listado de empleados", "4.- Modificar listado de oficinas"};
					switch (metodosMenu.Metodos.menu(opc, opcValidas, "Gestión de ficheros maestros", menError, lector))
					{
					case "1": //Añadir || eliminar vehículos de la flota
						String[] elec = {"A.- AÑADIR VEHÍCULO","B.- ELIMINAR VEHÍCULO","C.- SALIR"}; //elec de Elección
						String validas = "ABC";
						//mensaje de error cuando el menú funciona con letras A, B, C
						String errorLetras = "Debe elegir una opción válida pulsando A,B,C ..."; 
						String[] tipos = {"A.- Combustión","B.- Eléctrico","C.- Volver" };
						String[] combustion = {"1.- Coche","2.- Furgoneta", "3.- Volver"};
						String[] electrico = {"1.- Coche","2.- Moto", "3.- Volver"};
						
						switch (metodosMenu.Metodos.menu(elec, validas, "FLOTA DE VEHÍCULOS",errorLetras, lector))
						{				
						case "A","a": //Lo hacemos tolerante a mayúsculas y minúsculas
							switch (metodosMenu.Metodos.menu(elec, validas, "TIPO DE VEHÍCULO",errorLetras, lector))
							{
							case "A","a":	//Combustion
								
								switch (metodosMenu.Metodos.menu(combustion, opcValidas, "Vehiculos de combustión", menError, lector))
								{
								case "1":	//Coche
									
									try {
										CocheCombustion nuevo = PideDato.cocheCombustion(lector);
										empresa.addAlGaraje(nuevo);
									} catch (RecargoNoValidoException | LetrasMatriculaNoValidasException
											| NumeroMatriculaNoValidoException | EmisionesNoValidasException
											| NumPlazasNoValidoException | ConsumoNoValidoException
											| PotenciaNoValidaException | LongitudNoValidaException
											| PlantaNoValidaException | CodigoPostalException
											| LongitudCadenaNoValidaException e) {
										System.out.println("Error al crear vehículo - Coche de combustión");
										e.printStackTrace();
									}
																		
								case "2":	//Furgoneta
									try {
										Furgoneta furgo = PideDato.furgoneta(lector);
										empresa.addAlGaraje(furgo);
									} catch (EmisionesNoValidasException | ConsumoNoValidoException
											| PotenciaNoValidaException | RecargoNoValidoException
											| LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException e) {
										System.out.println("Error al crear vehículo - Furgoneta");
										e.printStackTrace();
									}
								case "3":	//Salir
									break;
								}
								
							case "B","b":	//Electrico
							
								switch (metodosMenu.Metodos.menu(electrico, opcValidas, "Vehiculos eléctricos", menError, lector))
								{
								case "1":	//Coche
									CocheElectrico nuevo = PideDato.cocheElectrico(lector);
									empresa.addAlGaraje(nuevo);
								case "2":	//Moto
									Moto nueva;
									try {
										nueva = PideDato.moto(lector);
										empresa.addAlGaraje(nueva);
									} catch (LetrasMatriculaNoValidasException | NumeroMatriculaNoValidoException
											| RecargoNoValidoException | EmisionesNoValidasException
											| ConsumoNoValidoException | PotenciaNoValidaException
											| CilindradaNoValidaException | CarnetRequeridoInvalidoException e) {
										System.out.println("Error al crear la moto");
										e.printStackTrace();
									}
								case "3":	//Salir
									break;
								}
							case "C","c": //SALIR
								break;
							}
						case "B","b":	//ELIMINAR VEHICULO
							
						case "C","c": //SALIR
							break;
						}
					case "2":
						
					case "3":
						
					case "4":
						
					}
				case "2":
					
				case "3":
					
				case "4":
					noSale= true; //reafirmamos que se queda en el menú principal
					break;
				}
			case "2":
				
			case "3":
				
			case "4":
				BarraDeCarga.pintar();
				noSale = false;
				break;
			}
		}while(noSale);
		
		
		
	}

}
