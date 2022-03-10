package clases;

public abstract class Vehiculo {

	//Propiedades
	int ID;		//ID del coche. A cargo de la empresa. PRIMARY KEY
	String modelo; 	//Modelo del coche (puede ser numérico y/o contener letras. Por ejemplo--> Audi R8)
	Bastidor num_Bast; 	//Nº de bastidor 
	String Color; //Color del vehículo
	Matricula matricula; //Matrícula del vehículo
	
	//Getters y Setters 
	
	public int getID() {
		return ID;
	}
	private void setID(int iD) { //El id será PRIVATE, ya que será obligatorio introducirlo en el constructor
		if (String.valueOf(iD).length()<0)
		{
			ID = iD;
		}
	}
	public String getModelo() {
		return modelo;
	}
	private void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public Bastidor getNum_Bast() {
		Bastidor copia = new Bastidor(this.num_Bast);
		return copia;
	}
	private void setNum_Bast(Bastidor num_Bast) {
		//Se supone que ya está comprobado que está bien porque ya ha pasado el constructor de Bastidor
		this.num_Bast = num_Bast;
	}
	private void setNum_Bast(String letras, int numero) {
		this.num_Bast = new Bastidor(letras, numero); //Lo crearemos
	}
	public String getColor() {
		return Color;
	}
	public void setColor(String color) {
		Color = color;
	}
	public Matricula getMatricula() {
		Matricula copia = new Matricula(this.matricula);
		return copia;
	}
	public void setMatricula(Matricula matricula) {
		this.matricula = matricula;
	}
	
	//Constructores
	public Vehiculo(int iD, String modelo, Bastidor num_Bast, String color, Matricula matricula) {
		super();
		this.setID(iD);
		this.setModelo(modelo);
		this.setNum_Bast(num_Bast);
		this.setColor(color);
		this.setMatricula(matricula);
	}
	
	public Vehiculo(int iD, String modelo, String letras_bastidor, int num_bastidor, String color, Matricula matricula) {
		super();
		this.setID(iD);
		this.setModelo(modelo);
		this.setNum_Bast(letras_bastidor, num_bastidor);
		this.setColor(color);
		this.setMatricula(matricula);
	}
	
	public Vehiculo(Vehiculo vehiculo) {
		this.setID(vehiculo.getID());
		this.setModelo(vehiculo.getModelo());
		this.setNum_Bast(vehiculo.getNum_Bast());
		this.setColor(vehiculo.getColor());
		this.setMatricula(vehiculo.getMatricula());
	}
	
	
}
