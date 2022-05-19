package clasesObjetos;

public class Provincia {

	private String nombre;
	private int codigo;
	
	
	
	public int getCodigo() {
		//Tipo primitivo
		return codigo;
	}
	public void setCodigo(int codigo) {
		int code = codigo;	//Copiamos el número
		this.codigo = code;
	}
	public String getNombre() {
		String nombreCop = nombre;
		return nombreCop;
	}
	public void setNombre(String nombre) {
		String nombreCop = nombre;
		this.nombre = nombreCop;
	}
	
	
}
