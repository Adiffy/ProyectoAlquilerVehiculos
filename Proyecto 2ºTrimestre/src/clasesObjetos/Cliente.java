package clasesObjetos;

import java.util.Objects;

import exceptions.DNInoValidoException;
import exceptions.LicenciaNoValidaException;
import exceptions.LongitudCadenaNoValidaException;

public class Cliente extends Persona{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
		if (( licencia.compareToIgnoreCase("A")==0 || licencia.compareToIgnoreCase("B")==0 || licencia.compareToIgnoreCase("C")==0 || licencia.compareToIgnoreCase("D")==0 ) || licencia.compareToIgnoreCase("AM")==0 || licencia.compareToIgnoreCase("A1")==0 || licencia.compareToIgnoreCase("A2")==0
				|| licencia.compareToIgnoreCase("C1")==0 || licencia.compareToIgnoreCase("C2")==0 || licencia.compareToIgnoreCase("B1")==0 || licencia.compareToIgnoreCase("B2")==0)
		{
			String license = licencia;
			this.licencia = license;
		}else {
			throw new LicenciaNoValidaException("Licencia no válida, debe ser: AM, A1 o A2, A, B, B1, B2, C, C1, C2, D");
		}
	}

	public Cliente(String nombre, String apellido1, String apellido2, String dni, String carnet, int tarjeta) throws LicenciaNoValidaException, LongitudCadenaNoValidaException, DNInoValidoException {
		super(nombre, apellido1, apellido2, dni);
		this.setLicencia(carnet);
		this.setTarjeta(tarjeta);
	}

	public Cliente(Cliente cliente) throws LicenciaNoValidaException, DNInoValidoException, LongitudCadenaNoValidaException {
		super(cliente.getNombre(), cliente.getApellido1(), cliente.getApellido2(), cliente.getDni());
		this.setLicencia(cliente.getLicencia());
		this.setTarjeta(cliente.getTarjeta());
	}

	@Override
	public String toString() {
		return getNombreCompleto() ;
	}

	@Override
	public int hashCode() {
		return Objects.hash(licencia, tarjeta);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cliente otro = (Cliente) obj;
		return (otro.getDni().compareToIgnoreCase(this.getDni())==0) || tarjeta == otro.tarjeta;
	}

	
	

}
