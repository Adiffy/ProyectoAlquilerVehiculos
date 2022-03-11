package clases.Objetos;

public class Direccion {

	
	//Propiedades
				//private String tipoVia;
	private String nombreVia;
	private String numVia;
	private String planta;
	private String codigoPostal;
	private String localidad;
	
	
	
	//Getters & Setters 
	public String getNombreVia() {
		return nombreVia;
	}
	
	public void setNombreVia(String nombreVia) {
		if(nombreVia.length()<=25 && nombreVia.length()>=2) {
			this.nombreVia = nombreVia;
		}else {
			//TODO mensaje error
		}
	}
	public String getNumVia() {
		return numVia;
	}
	
	public void setNumVia(String numVia) {
		if (numVia.length()>=2 && numVia.length()<=25) {
			String num=numVia;
			this.numVia=num;
		}
	}
	public String getPlanta() {
		return planta;
	}
	public void setPlanta(String planta) {
		if (planta.length()<4)
		{
			String plant = planta;
			this.planta = plant;
		}else {
			//TODO Mensaje error
		}
	}
	public String getCodigoPostal() {
		return codigoPostal;
	}
	public void setCodigoPostal(String codigoPostal) {
		if (codigoPostal.length()==5)
		{
			String CP = codigoPostal;
			this.codigoPostal = CP;
		}else {
			//TODO mensaje error
		}
	}
	public String getLocalidad() {
		return localidad;
	}
	public void setLocalidad(String localidad) {
		if (localidad.length()>3 && localidad.length()<25)
		{
			String loc = localidad;
			this.localidad = loc;
		}else {
			//TODO mensaje error
		}
	}
	
	//Constructores
	
	/**
	 * <strong>Constructor completo</strong>
	 * Genera una dirección con todos sus parámetros. Incluye el parámetro {@code planta} que al igual que los demás
	 * es un {@code String}.
	 * @param nombreVia Nombre de la vía (<strong>Calle, Avenida, ...</strong>).
	 * @param numVia Número de la vía (<strong>Calle, Avenida, ...</strong>).
	 * @param planta La planta del edificio en el que vive.
	 * @param codigoPostal	Código Postal. Ejemplo: {@code 23001} en el caso de {@code Vitoria-gasteiz}.
	 * @param localidad	El nombre de la localidad. Ejemplo: "{@code Jaén}".
	 */
	public Direccion(String nombreVia, String numVia, String planta, String codigoPostal, String localidad) {
		super();
		this.setNombreVia(nombreVia);
		this.setNumVia(numVia);
		this.setPlanta(planta);
		this.setCodigoPostal(codigoPostal);
		this.setLocalidad(localidad);
	}
	
	/**
	 * Constructor hecho para el caso 
	 *  de vivir en una casa unifamiliar (sin planta/piso)
	 * @param nombreVia El nombre de la vía (<strong>Calle, Avenida, ...</strong>).
	 * @param numVia	El número de la <em>vía</em> (<strong>Calle, Avenida, ...</strong>).
	 * @param codigoPostal Código Postal. Ejemplo: {@code 23009} en el caso de {@code Jaén}.
	 * @param localidad El nombre de la localidad. Ejemplo: "{@code Vitoria-gasteiz}".
	 */
	public Direccion(String nombreVia, String numVia, String codigoPostal, String localidad) {
		super();
		this.setNombreVia(nombreVia);
		this.setNumVia(numVia);
		this.setCodigoPostal(codigoPostal);
		this.setLocalidad(localidad);
	}

	/**
	 * Constructor de copia
	 * Se le da una direccón ya creada y se crea una idéntica
	 * @param ubi	un Objeto tipo {@code Direccion}.
	 */
	public Direccion(Direccion ubi) {
		String nombreV = ubi.getNombreVia();
		String numV = ubi.getNumVia();
		String plant = ubi.getPlanta();
		String cp = ubi.getCodigoPostal();
		String loc = ubi.getLocalidad();
		
//		this = new Direccion(nombreV,numV,plant,cp,loc);	No funciona así
		this.codigoPostal = cp;
		this.nombreVia = nombreV;
		this.numVia = numV;
		this.localidad = loc;
		this.planta = plant;
	}
	
	/**
	 * Valida una {@code Direccion} válida, devuelve un verdadero si es <strong>válido</strong> o un falso
	 * si <strong>NO</strong> lo es.
	 * @param domicilio
	 * @return
	 */
/*	public boolean DireccionValida(Direccion domicilio) {
		boolean valido=false;
			
		return valido;
	}
*/
	
}
