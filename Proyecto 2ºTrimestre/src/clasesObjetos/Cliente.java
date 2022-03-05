package clasesObjetos;

public class Cliente extends Persona{

	//Propiedades
	private String licencia;
	private String tarjeta;
	
	//Getters y Setters
	public String getTarjeta() {
		String tar = tarjeta;
		return tar;
	}

	public void setTarjeta(String tarjeta) {
		
		this.tarjeta = tarjeta;
	}

	public String getLicencia() {
		return licencia;
	}

	public void setLicencia(String licencia) {
		this.licencia = licencia;
	}

	public Cliente(String nombre, String apellido1, String apellido2, String dni, String carnet, String tarjeta) {
		super(nombre, apellido1, apellido2, dni);
		this.setLicencia(carnet);
		this.setTarjeta(tarjeta);
	}

	

}
