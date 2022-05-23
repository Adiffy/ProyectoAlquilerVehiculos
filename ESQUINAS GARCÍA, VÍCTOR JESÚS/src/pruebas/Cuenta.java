package pruebas;

public class Cuenta {

	private String nombreCliente;
	private int saldo;
	private int limiteMensual;
	
	public String getNombreCliente() {
		String  copia = nombreCliente;
		return copia;
	}
	public void setNombreCliente(String nombreCliente) {
		String nombre = nombreCliente.trim();	//Quitamos espacios a ambos lados
		if (nombre!=null && nombre!="" && nombre.length()<=30)
		{
			this.nombreCliente = nombre;
		}
		
	}
	public int getSaldo() {
		//tipo primitivo
		return saldo;
	}
	public void setSaldo(int saldo) {
		int dinero = saldo;
		if (dinero>=0)
		{
			this.saldo = saldo;
		}
	}
	public int getLimiteMensual() {
		//tipo primitivo
		return limiteMensual;
	}
	public void setLimiteMensual(int limiteMensual) {
		int limite = limiteMensual;
		if (limite >0 && limite<2000)
		{	//ayor que 0 y menor que 2000
			this.limiteMensual = limiteMensual;
		}
	}
	
	public Cuenta(String nombreCliente, int saldo, int limiteMensual) {
		super();
		this.setNombreCliente(nombreCliente);
		this.setSaldo(saldo);
		this.setLimiteMensual(limiteMensual);
	}
 
}
