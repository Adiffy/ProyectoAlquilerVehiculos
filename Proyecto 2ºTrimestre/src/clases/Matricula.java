package clases;

import java.util.Objects;

public class Matricula {

	//Propiedades
	private int numeros; //Los nº de la matrícula 
	private String letras; //Las letras al final de cada matrícula 
	
	
	//Getters & Setters
	public int getNumeros() {
		return numeros;
	}
	
	private void setNumeros(int numeros) {
		if (String.valueOf(numeros).length()<5 && String.valueOf(numeros).length()>2) //TODO revisar cuantos nº hay en matrícula
		{
			this.numeros = numeros;
		}
	}
	
	public String getLetras() {
		return letras;
	}
	
	private void setLetras(String letras) {
		if (letras.length()==3)
		{
			this.letras = letras;
		}
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(letras, numeros);
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
		{
			return false;
		}
		Matricula otro = (Matricula) obj;
		return Objects.equals(letras, otro.letras) && numeros == otro.numeros;
	}

	@Override
	public String toString() {
		return "Matricula: " + numeros + letras;
	}


	//Constructores
	
	/**
	 * Constructor de copia
	 * @param otra	 La matrícula a copiar
	 */
	public Matricula(Matricula otra) {
		this.setLetras(otra.letras);
		this.setNumeros(otra.numeros);
	}
	
	/**
	 * Constructor completo
	 * @param numeros	Los números que tiene una matrícula al principio generalmente
	 * @param letras	Las letras que acompañan a los números y que también componen la matrícula
	 */
	public Matricula(int numeros, String letras) {
		super();
		this.setLetras(letras);
		this.setNumeros(numeros);
	}
	
	
	
	
}
