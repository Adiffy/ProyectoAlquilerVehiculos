package objetosBD;

/**
 * Clase que permite crear objetos de tipo Moto con los atributos de la base de datos
 * @author Victor
 *
 */
public class MotoBD {

	//Propiedades
	private int cilindrada;
	private String carnetRequerido;

	public int getCilindrada() {
		return cilindrada;
	}

	public void setCilindrada(int cilindrada) {
		this.cilindrada = cilindrada;
	}

	public String getCarnetRequerido() {
		String carnet = carnetRequerido;
		return carnet;
	}

	public void setCarnetRequerido(String carnetRequerido) {
		String carnet = carnetRequerido;
		this.carnetRequerido = carnet;
	}

	public MotoBD(int cilindrada, String carnetRequerido) {
		super();
		this.setCilindrada(cilindrada);
		this.setCarnetRequerido(carnetRequerido);
	}
	
}
