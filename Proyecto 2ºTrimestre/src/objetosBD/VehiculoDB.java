package objetosBD;

import java.util.Calendar;
import java.util.GregorianCalendar;

import clasesObjetos.Categoria;
import clasesObjetos.Matricula;
import clasesObjetos.Oficina;
import exceptions.RecargoNoValidoException;
/**
 * Clase diseñada para crear objetos de tipo Vehiculo para agilizar la devolución
 * de datos desde la base de datos.
 * @author Victor
 *
 */
public class VehiculoDB {

	//Propiedades
	private Matricula matricula; //Matrícula del vehículo
	private String marca;	//Marca del coche. Ejemplo: "Renault"
	private String modelo; 	//Modelo del coche (puede ser numérico y/o contener letras. Por ejemplo--> Audi "R8") 
	private String Color; //Color del vehículo
	private GregorianCalendar fechaAlta;
	private int kms; //Los kilómetros que tiene
	private Categoria categoria;
	private Oficina oficina;
	
	
	public Matricula getMatricula() {
		Matricula cop = matricula;
		return cop;
	}
	public void setMatricula(Matricula matricula) {
		Matricula mat = matricula;
		this.matricula = mat;
	}
	public String getMarca() {
		String marc  = marca;
		return marc;
	}
	public void setMarca(String marca) {
		String marc = marca;
		this.marca = marc;
	}
	public String getModelo() {
		String mod = modelo;
		return mod;
	}
	public void setModelo(String modelo) {
		String mod = modelo;
		this.modelo = mod;
	}
	public String getColor() {
		String col = Color;
		return col;
	}
	public void setColor(String color) {
		String col = color;
		Color = col;
	}
	public GregorianCalendar getFechaAlta() {
//		GregorianCalendar nuevaFecha = new GregorianCalendar();
		int anio = fechaAlta.get(Calendar.YEAR);
		int mes = fechaAlta.get(Calendar.MONTH);
		int dia = fechaAlta.get(Calendar.DAY_OF_MONTH);
		return new GregorianCalendar(anio,mes,dia);
	}
	public void setFechaAlta(GregorianCalendar fechaAlta) {
		int anio = fechaAlta.get(Calendar.YEAR);
		int mes = fechaAlta.get(Calendar.MONTH);
		int dia = fechaAlta.get(Calendar.DAY_OF_MONTH);
		this.fechaAlta = new GregorianCalendar(anio,mes,dia);
	}
	public int getKms() {
		return kms;
	}
	public void setKms(int kms) {
		this.kms = kms;
	}
	public Categoria getCategoria() throws RecargoNoValidoException {
		return new Categoria(categoria);
	}
	public void setCategoria(Categoria categoria) throws RecargoNoValidoException {
		this.categoria = new Categoria(categoria);
	}
	
	public VehiculoDB(Matricula matricula, String marca, String modelo, String color, GregorianCalendar fechaAlta,
			int kms, Categoria categoria, Oficina oficina) throws RecargoNoValidoException {
		super();
		this.setMatricula(matricula);
		this.setMarca(marca);
		this.setModelo(modelo);
		this.setColor(color);
		this.setFechaAlta(fechaAlta);
		this.setKms(kms);
		this.setCategoria(categoria);
		this.setOficina(oficina);
	}
	public Oficina getOficina() {
		return new Oficina(oficina);
	}
	public void setOficina(Oficina oficina) {
		this.oficina = new Oficina(oficina);
	}
}
