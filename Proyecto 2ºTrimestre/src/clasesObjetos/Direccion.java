package clasesObjetos;

import java.io.Serializable;

import exceptions.CodigoPostalException;
import exceptions.LongitudCadenaNoValidaException;
import exceptions.LongitudNoValidaException;
import exceptions.PlantaNoValidaException;

public class Direccion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
				//private String tipoVia;
	private String nombreVia;
	private String numVia;
	private String planta;
	private String codigoPostal;
	private String localidad;
	
	
	
	//Getters & Setters 
	public String getNombreVia() {
		String copia = nombreVia; 
		return copia;	//Devolvemos un clon
	}
	
	public void setNombreVia(String nombreVia) throws LongitudNoValidaException {
		if(nombreVia.length()<=25 && nombreVia.length()>=2) 
		{
			String copia = nombreVia; //Hacemos una copia antes de añadirlo
			this.nombreVia = copia;
		}else {
			throw new LongitudNoValidaException("Nombre demasiado largo/corto");
		}
	}
	public String getNumVia() {
		String num = numVia;	//Evitamos el tampering
		return num;
	}
	
	public void setNumVia(String numVia) throws LongitudNoValidaException {
		if (numVia.length()>=1 && numVia.length()<=25) {
			String num=numVia;
			this.numVia=num;
		}else {
			throw new LongitudNoValidaException("Nombre demasiado largo/corto");
		}
	}
	public String getPlanta() {
		String copia = planta;	//Le hacemos una copia
		return copia;		//Devolvemos la copia
	}
	public void setPlanta(String planta) throws PlantaNoValidaException {
		if (planta.length()<4)
		{
			String plant = planta;		//Le hacemos un clon
			this.planta = plant;
		}else {
			throw new PlantaNoValidaException("Planta demasiado alta");
		}
	}
	public String getCodigoPostal() {
		String CP = codigoPostal; 
		return CP;	//Devolvemos la copia
	}
	public void setCodigoPostal(String codigoPostal) throws CodigoPostalException {
		if (codigoPostal.length()==5) //Los CP tienen 5 caracteres, por ejemplo: 23009
		{
			String CP = codigoPostal;
			this.codigoPostal = CP;	//Devolvemos una copia
		}else {
			throw new CodigoPostalException("Longitud del Codigo Postal inválido");
		}
	}
	public String getLocalidad() {
		String loc = localidad; 
		return loc;		//Evitamos el tampering devuelviendo un 'clon'
	}
	public void setLocalidad(String localidad) throws LongitudCadenaNoValidaException {
		if (localidad.length()>3 && localidad.length()<25)
		{
			String loc = localidad;
			this.localidad = loc;
		}else {
			throw new LongitudCadenaNoValidaException("Nombre de localidad muy largo");
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
	 * @throws LongitudNoValidaException Error cuando la longitud del dato introducido es inválida
	 * @throws PlantaNoValidaException Error cuando la planta tiene demasiados números (más de 4 dígitos). Ejemplo: planta nº 1000
	 * @throws CodigoPostalException Longitud del Codigo postal inváilida
	 * @throws LongitudCadenaNoValidaException 
	 */
	public Direccion(String nombreVia, String numVia, String planta, String codigoPostal, String localidad) throws LongitudNoValidaException, PlantaNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException {
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
	 * @throws LongitudNoValidaException Error cuando la longitud del dato introducido es inválida
	 * @throws CodigoPostalException Longitud del Codigo postal inváilida
	 * @throws LongitudCadenaNoValidaException 
	 */
	public Direccion(String nombreVia, String numVia, String codigoPostal, String localidad) throws LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException {
		super();
		this.setNombreVia(nombreVia);
		this.setNumVia(numVia);
		this.setCodigoPostal(codigoPostal);
		this.setLocalidad(localidad);
	}
	public Direccion(String nombreVia, String numVia, String codigoPostal) throws LongitudNoValidaException, CodigoPostalException, LongitudCadenaNoValidaException {
		super();
		this.setNombreVia(nombreVia);
		this.setNumVia(numVia);
		this.setCodigoPostal(codigoPostal);
		
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
	
	
	
/*				ELIMINAMOS EL JAVADOC
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
