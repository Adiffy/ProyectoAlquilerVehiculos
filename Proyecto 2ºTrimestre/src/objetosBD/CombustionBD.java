package objetosBD;

public class CombustionBD {

	
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
		String emi = emisiones;
		return emi;
	}
	public void setEmisiones(String emisiones) {
		String emi = emisiones;
		this.emisiones = emi;
	}
	
	public CombustionBD(double consumo, int potencia, String emisiones) {
		super();
		this.setConsumo(consumo);
		this.setPotencia(potencia);
		this.setEmisiones(emisiones);
	}
	
}
