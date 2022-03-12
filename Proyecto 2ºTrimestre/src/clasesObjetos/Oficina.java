package clasesObjetos;

import java.util.TreeMap;

import metodos.Validadores;

public class Oficina {

	//Propiedades
	private Direccion ubicacion;
	private TreeMap<String, Empleado> personal;
	  
	 
	private String C�digo; //(es un alfanum�rico de 4 letras : JA01, MA10�)
	private String Descripci�n; //(�Ja�n estaci�n de trenes�, �M�laga principal�
	private String Localidad;
	private String Provincia;
	private boolean deAeropuerto; //Si es o no oficina de aeropuerto (porque esto supone un recargo en los alquileres, seg�n se explica m�s adelante)

	
	
	//Getters Y setters
	public Direccion getUbicacion() {
		return new Direccion(ubicacion); //Devolveremos una copia
	}
	public void setUbicacion(Direccion ubicacion) {
		//Suponemos que al haber creado la ubicaci�n ya ser� correcta
		//A�n as�, evitaremos el tampering mediante una copia
		this.ubicacion = new Direccion(ubicacion);
	}
	
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
	public String getDescripci�n() {
		String desc = this.Descripci�n;
		return desc ;
	}
	public void setDescripci�n(String descripcion) {
		if (descripcion.length()>0)
		{
			String desc = descripcion;
			Descripci�n = desc;
		}
	}
	public String getProvincia() {
		String clon = Provincia;
		return clon;
	}
	public void setProvincia(String provincia) {
		if (provincia.length()>0 && provincia.length()<30)
		{
			String jaen = provincia; //Creamos una nueva provincia clon
			Provincia = jaen;
		}
	}
	/**
	 * Constructor de copia de oficina
	 * @param oficina	La oficina a clonar
	 */
	public Oficina(Oficina oficina) {
		this.setC�digo(oficina.getC�digo());
		this.setDeAeropuerto(oficina.isDeAeropuerto());
		this.setDescripci�n(oficina.getDescripci�n());
		this.setLocalidad(oficina.getLocalidad());
		this.setProvincia(oficina.getProvincia());
		this.setPersonal(oficina.getPersonal());
		this.setUbicacion(oficina.getUbicacion());
	}
	
	
	public Oficina(String c�digo, String descripci�n, Direccion ubicacion, TreeMap<String, Empleado> personal,  
			 String provincia, String localidad, boolean deAeropuerto) {
		super();
		this.setUbicacion(ubicacion);
		this.setPersonal(personal);
		this.setC�digo(localidad);
		this.setDescripci�n(descripci�n);
		this.setLocalidad(localidad);
		this.setProvincia(provincia);
		this.setDeAeropuerto(deAeropuerto);
	}
	
	public Oficina(String c�digo, String descripci�n, Direccion ubicacion,   
			 String provincia, String localidad, boolean deAeropuerto) {
		super();
		this.setUbicacion(ubicacion);
		this.setC�digo(localidad);
		this.setDescripci�n(descripci�n);
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
	public String getC�digo() {
		String copiaCod = this.C�digo;
		return copiaCod;
	}
	public void setC�digo(String code) {
		if (Validadores.codigoValidado(code))
		{
			String cop = code;
			C�digo = cop;
		}
		
	}
	@SuppressWarnings("unchecked")
	public TreeMap<String, Empleado> getPersonal() {
		return (TreeMap<String, Empleado>) personal.clone() ;
	}
	public void setPersonal(TreeMap<String, Empleado> personal) {
		this.personal = personal;
	}
	public void addEmpleado(Empleado contratado)
	{
		String clavePrincipal = contratado.getDni() ;
		this.personal.put(clavePrincipal, new Empleado(contratado)); //Lo a�adimos
	}
}
