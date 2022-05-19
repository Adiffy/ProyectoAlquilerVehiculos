package clasesObjetos;

import java.io.Serializable;
import java.util.Objects;
import java.util.TreeMap;

import exceptions.DNInoValidoException;
import exceptions.LongitudCadenaNoValidaException;

public class Oficina implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
//	private Direccion ubicacion;
	private TreeMap<String, Empleado> personal;
	  
	 
	private String Codigo; //(es un alfanumórico de 4 letras : JA001, MA10ó)
	private String Descripción; //(óJaón estación de trenesó, óMólaga principaló
	private String Localidad;
	private String Provincia;
	private boolean deAeropuerto; //Si es o no oficina de aeropuerto (porque esto supone un recargo en los alquileres, segón se explica mós adelante)

	
	
	//Getters Y setters
//	public Direccion getUbicacion() {
//		return new Direccion(ubicacion); //Devolveremos una copia
//	}
//	public void setUbicacion(Direccion ubicacion) {
//		//Suponemos que al haber creado la ubicación ya seró correcta
//		//Aón asó, evitaremos el tampering mediante una copia
//		this.ubicacion = new Direccion(ubicacion);
//	}
	
	public String getLocalidad() {
		String copia = Localidad;
		return copia;
	}
	public void setLocalidad(String localidad) {
		if (localidad.length()>0 && localidad.length()<30)
		{
			String jaen = localidad; //Creamos una nueva localidad clon
			Localidad = jaen;
		}
	}
	public String getDescripción() {
		String desc = this.Descripción;
		return desc ;
	}
	public void setDescripción(String descripcion) {
		if (descripcion.length()>0)
		{
			String desc = descripcion;
			Descripción = desc;
		}
	}
	public String getProvincia() {
		String clon = Provincia;
		return clon;
	}
	public void setProvincia(String provincia) {
//		if (provincia.length()>0 && provincia.length()<30)
		{
			
			switch (provincia)
			{
			case "1":
				this.Provincia = "ólava";
				break;
			case "2":
				this.Provincia = "Albacete";
				break;
			case "4":
				this.Provincia = "Almeróa";
				break;
			case "5":
				this.Provincia = "Asturias";
				break;
			case "6":
				this.Provincia = "óvila";
				break;
			case "7":
				 this.Provincia = "Badajoz";
				 break;
			case "8":
				this.Provincia = "Barcelona";
				break;
			case "9":
				this.Provincia = "Burgos";
				break;
			case "10":
				this.Provincia = "Cóceres";
				break;
			case "11":
				this.Provincia = "Códiz";
				break;
			case "12":
				this.Provincia = "Cantabria";
				break;
			case "13":
				this.Provincia = "Castellón";
				break;
			case "14":
				this.Provincia = "Ciudad Real";
				break;
			case "15":
				this.Provincia = "Córdoba";
				break;
			case "16":
				this.Provincia = "Cuenca";
				break;
			case "17":
				this.Provincia = "Gerona (Girona)";
				break;
			case "18":
				this.Provincia = "Guadalajara";
				break;
			case "19":
				this.Provincia = "Guipózcoa (Gipuzkoa)";
				break;
			case "20":
				this.Provincia = "Huelva";
				break;
			case "21":
				this.Provincia = "Huesca";
				break;
			case "22":
				this.Provincia = "Islas Baleares";
				break;
			case "23":
				this.Provincia = "Jaón";
				break;
			case "24":
				this.Provincia = "La coruóa (A Coruóa)";
				break;
			case "25":
				this.Provincia = "La Rioja";
				break;
			case "26":
				this.Provincia = "Las Palmas";
				break;
			case "27":
				this.Provincia = "León";
				break;
			case "28":
				this.Provincia = "Lórida (Lleida)";
				break;
			case "29":
				this.Provincia = "Lugo";
				break;
			case "30":
				this.Provincia = "Madrid";
				break;
			case "31":
				this.Provincia = "Mólaga";
				break;
			case "32":
				this.Provincia = "Murcia";
				break;
			case "33":
				this.Provincia= "Navarra";
				break;
			case "34":
				this.Provincia = "Orense (Ourense)";
				break;
			case "35":
				this.Provincia = "Palencia";
				break;
			case "36":
				this.Provincia = "Pontevedra";
				break;
			case "37":
				this.Provincia = "Salamanca";
				break;
			case "38":
				this.Provincia = "Santa Cruz de Tenerife";
				break;
			case "39":
				this.Provincia = "Segovia";
				break;
			case "40":
				this.Provincia = "Sevilla";
				break;
			case "41":
				this.Provincia = "Soria";
				break;
			case "42":
				this.Provincia = "Tarragona";
				break;
			case "43":
				this.Provincia = "Teruel";
				break;
			case "44":
				this.Provincia = "Toledo";
				break;
			case "45":
				this.Provincia= "Valencia";
				break;
			case "46":
				this.Provincia= "Valladolid";
				break;
			case "47":
				this.Provincia = "Vizcaya (Bizkaia)";
				break;
			case "48":
				this.Provincia= "Zamora";
				break;
			case "49":
				this.Provincia= "Zaragoza";
				break;
			case "50":
				this.Provincia = "Granada";
				break;
			case "51":
				this.Provincia= "Ceuta";
				break;
			case "52":
				this.Provincia = "Melilla";
				break;
			default:	//Si no es numerico, nos han dado el nombre correcto
				this.Provincia = provincia;
			}
//			String jaen = provincia; //Creamos una nueva provincia clon
//			Provincia = jaen;
		}
	}
	/**
	 * Constructor de copia de oficina
	 * @param oficina	La oficina a clonar
	 */
	public Oficina(Oficina oficina) {
		this.setCódigo(oficina.getCódigo());
		this.setDeAeropuerto(oficina.isDeAeropuerto());
		this.setDescripción(oficina.getDescripción());
		this.setLocalidad(oficina.getLocalidad());
		this.setProvincia(oficina.getProvincia());
//		this.setPersonal(oficina.getPersonal());
//		this.setUbicacion(oficina.getUbicacion());
	}
	
	
	public Oficina(String código, String descripción,  TreeMap<String, Empleado> personal,  
			 String provincia, String localidad, boolean deAeropuerto) {
		super();
		this.setCódigo(código);
//		this.setUbicacion(ubicacion);
		this.setPersonal(personal);
		this.setCódigo(localidad);
		this.setDescripción(descripción);
		this.setLocalidad(localidad);
		this.setProvincia(provincia);
		this.setDeAeropuerto(deAeropuerto);
	}
	
	public Oficina(String código, String descripción,   
			 String provincia, String localidad, boolean deAeropuerto) {
		super();
		this.setCódigo(código);
//		this.setUbicacion(ubicacion);
		this.setDescripción(descripción);
		this.setLocalidad(localidad);
		this.setProvincia(provincia);
		this.setDeAeropuerto(deAeropuerto);
	}
	
	public boolean isDeAeropuerto() {
		boolean copia = deAeropuerto;
		return copia;
	}
	public void setDeAeropuerto(boolean deAeropuerto) {
		boolean SIoNO = deAeropuerto;	//Evitamos el tampering
		this.deAeropuerto = SIoNO;	//Introducimos el valor que queramos T / F
	}
	public String getCódigo() {
		String copiaCod = Codigo;
		return copiaCod;
	}
	public void setCódigo(String code) {
//		if (Validadores.codigoValidado(code))
		{
			String cop = code;
			Codigo = cop;
		}
		
	}
	
	
	@SuppressWarnings("unchecked")
	public TreeMap<String, Empleado> getPersonal() {
		return (TreeMap<String, Empleado>) personal.clone() ;
	}
	public void setPersonal(TreeMap<String, Empleado> personal) {
		this.personal = personal;
	}
	public void addEmpleado(Empleado contratado) throws LongitudCadenaNoValidaException
	{
		String clavePrincipal = contratado.getDni() ;
		try {
			this.personal.put(clavePrincipal, new Empleado(contratado));
		} catch (DNInoValidoException | LongitudCadenaNoValidaException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} //Lo aóadimos
	}
	
	@Override
	public String toString()
	{
		return ""+this.getCódigo()+" | "+this.getDescripción(); //+ " - "+this.getProvincia()+", "+this.getLocalidad();
	}
	@Override
	public int hashCode() {
		return Objects.hash(Codigo, Descripción, Localidad, Provincia, deAeropuerto, personal);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
			return false;
		Oficina otra = (Oficina) obj;
		return Objects.equals(Codigo, otra.Codigo);
	}
	
	
}
