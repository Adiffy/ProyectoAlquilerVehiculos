package clasesObjetos;

import exceptions.CarnetRequeridoInvalidoException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;

public class Cliente extends Persona{

	//Propiedades
	private String licencia;
	private int tarjeta;	//Nº de tarjeta de cliente habitual
	
	//Getters y Setters
	public int getTarjeta() {
		int tar = tarjeta;
		return tar;
	}

	public void setTarjeta(int tarjeta) {
		//No hace falta evitar el tampering pero lo hacemos por costumbre
		int i = tarjeta;
		this.tarjeta = i;
	}

	public String getLicencia() {
		String license = licencia;
		return license;
	}

	public void setLicencia(String licencia) throws LicenciaNoValidaException {
		//Comprobamos que licencia es igual a una de las licencias permitidas
		if (( licencia.compareToIgnoreCase("A")==0 || licencia.compareToIgnoreCase("B")==0 || licencia.compareToIgnoreCase("C")==0 || licencia.compareToIgnoreCase("D")==0 ) || licencia.compareToIgnoreCase("AM")==0 || licencia.compareToIgnoreCase("A1")==0 || licencia.compareToIgnoreCase("A2")==0)
		{
			String license = licencia;
			this.licencia = license;
		}else {
			throw new LicenciaNoValidaException("Licencia no válida, debe ser: A, B, C, D, AM, A1 o A2");
		}
	}

	public Cliente(String nombre, String apellido1, String apellido2, String dni, String carnet, int tarjeta) throws LicenciaNoValidaException, CarnetRequeridoInvalidoException, LongitudCadenaNoValidaException {
		super(nombre, apellido1, apellido2, dni);
		this.setLicencia(carnet);
		this.setTarjeta(tarjeta);
	}

	

}
