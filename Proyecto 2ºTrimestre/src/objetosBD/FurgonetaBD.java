package objetosBD;

/**
 * Clase para crear objetos de tipo Furgoneta según los campos de la base de datos
 * @author Victor
 *
 */
public class FurgonetaBD {

	//Propiedades
	private int capacidadCarga;
	private String carnetRequerido;
	
	
	public int getCapacidadCarga() {
		return capacidadCarga;
	}
	public void setCapacidadCarga(int capacidadCarga) {
		this.capacidadCarga = capacidadCarga;
	}
	public String getCarnetRequerido() {
		String carne = carnetRequerido;
		return carne;
	}
	public void setCarnetRequerido(String carnetRequerido) {
		String carnet = carnetRequerido;
		this.carnetRequerido = carnet;
	}
	public FurgonetaBD(int capacidadCarga, String carnetRequerido) {
		super();
		this.setCapacidadCarga(capacidadCarga);
		this.setCarnetRequerido(carnetRequerido);
	}
	
	
}
