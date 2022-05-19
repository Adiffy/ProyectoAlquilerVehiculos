package objetosBD;

public class DeCombustionDB {

	//Propiedades
	private double consumo;
	private int potencia;
	private String emisiones;
	
	public double getConsumo() {
		return consumo;
	}
	public void setConsumo(double consumo) {
		this.consumo = consumo;
	}
	public int getPotencia() {
		return potencia;
	}
	public void setPotencia(int potencia) {
		this.potencia = potencia;
	}
	public String getEmisiones() {
		String trab = emisiones;
		return trab;
	}
	public void setEmisiones(String emisiones) {
		String trab = emisiones;
		this.emisiones = trab;
	}
	public DeCombustionDB(double consumo, int potencia, String emisiones) {
		super();
		this.setConsumo(consumo);
		this.setPotencia(potencia);
		this.setEmisiones(emisiones);
	}
	
}
