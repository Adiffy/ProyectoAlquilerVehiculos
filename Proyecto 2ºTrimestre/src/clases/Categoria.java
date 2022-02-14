package clases;

public class Categoria {

	//Propiedades
	private String codigo;
	private String descripcion;
	private double recargo; //PORCENTAJE de recargo
	
	
	//Getters & Setters
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		if (codigo.equalsIgnoreCase("A") || codigo.equalsIgnoreCase("B") || codigo.equalsIgnoreCase("C"))
		{
			String code=codigo; //Creamos la copia
			this.codigo = code;
		}
	}
	public String getDescripcion() {
		String desc = this.descripcion;
		return desc;
	}
	public void setDescripcion(String descripcion) {
		if (descripcion.equalsIgnoreCase("ECONOMICO") || descripcion.equalsIgnoreCase("MEDIO") || descripcion.equalsIgnoreCase("PREMIUM"))
		{
			String desc = descripcion; //clonamos
			this.descripcion = desc;
			//Ahora seg�n la descripci�n pondremos un porcentaje de recargo u otro
			
			switch (desc.toUpperCase()) { //TODO revisar los porcentajes 
			case "A":
				this.recargo=15;
			case "B":
				this.recargo=20;
			case "C":
				this.recargo=25;
			} 
		}
	}
	private void setRecargo(double recargo) {
		if (recargo>=0 && recargo<=100)
		{
			double cop = recargo; //Copia
			this.recargo = cop;
		}
	}
	public double getRecargo() {
		double copia = this.recargo;
		return copia;
	}
	@Override
	public String toString() {
		return "Categoria " + codigo + "-" + descripcion;
	}
	
	/*
	 * Constructores
	 * (incluido el contructor de copia)
	 */
	
	/**
	 * Constructor completo, pide todos los par�metros y construye una <em>Categor�a</em>
	 * @param codigo	El c�digo de la categoria. Puede ser "A", "B", "C" ...
	 * @param descripcion	{@code String} que puede ser "ECONOMICO", "MEDIO" o "PREMIUM".
	 * @param recargo	Entero referido al porcentaje de recargo (necesario para luego calcular la factura)
	 */
	public Categoria(String codigo, String descripcion, double recargo) {
		super();
		this.setCodigo(codigo);
		this.setDescripcion(descripcion);
		this.setRecargo(recargo);
	}
	/**
	 * Constructor que asigna el % de recargo seg�n su descripci�n por tanto s�lo necesita 2 par�metros.
	 * @param codigo	El c�digo de la categoria. Puede ser "A", "B", "C" ...
	 * @param descripcion	Cadena que puede ser "ECONOMICO", "MEDIO" o "PREMIUM".
	 */
	public Categoria(String codigo, String descripcion) {
		super();
		this.setCodigo(codigo);
		this.setDescripcion(descripcion);
	}
	
	/**
	 * Constructor de copia
	 * @param otro	El Objeto tipo {@code Categoria} a copiar.
	 */
	public Categoria(Categoria otro) {
		//Obtenemos los par�metros que necesitamos
		String codcop = otro.getCodigo();
		String desccop = otro.getDescripcion();
		double recargocop = otro.getRecargo();
		//Hacemos set con la copia de los par�metros
		this.setCodigo(codcop);
		this.setDescripcion(desccop);
		this.setRecargo(recargocop);
	}
	
}
