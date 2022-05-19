package objetosBD;

/**
 * Clase para la recoleccion y manejo de los datos exclusivos de la entidad Electrico
 * de la base de datos
 * @author Victor
 *
 */
public class ElectricoBD {

	//Propiedades
	private int autonomia;
	private int tiempoRecarga;
	
	public int getAutonomia() {
		return autonomia;
	}
	public void setAutonomia(int autonomia) {
		this.autonomia = autonomia;
	}
	public int getTiempoRecarga() {
		return tiempoRecarga;
	}
	public void setTiempoRecarga(int tiempoRecarga) {
		this.tiempoRecarga = tiempoRecarga;
	}
	
	public ElectricoBD(int autonomia, int tiempoRecarga) {
		super();
		this.setAutonomia(autonomia);
		this.setTiempoRecarga(tiempoRecarga);;
	}
	
}
