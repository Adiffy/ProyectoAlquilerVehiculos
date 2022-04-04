package clasesObjetos;

import java.io.Serializable;
import java.util.Objects;

import exceptions.LetrasMatriculaNoValidasException;
import exceptions.NumeroMatriculaNoValidoException;
import metodos.Validadores;

public class Matricula implements Comparable<Matricula>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	private int numeros; //Los nº de la matrícula 
	private String letras; //Las letras al final de cada matrícula 
	
	
	//Getters & Setters
	public int getNumeros() {
		return numeros;
	}
	
	private void setNumeros(int numeros) throws NumeroMatriculaNoValidoException {
		if (Validadores.longituNumMatricula(numeros)) 
		{
			int num = numeros;
			this.numeros = num;
		}else {
			throw new NumeroMatriculaNoValidoException("Se esperaba únicamente la parte numérica de su matrícula. Por ejemplo: 3025");
		}
	}
	
	public String getLetras() {
		String let = this.letras; //Evitamos el tampering haciendo un clon
		return let;
	}
	
	private void setLetras(String letras) throws LetrasMatriculaNoValidasException {
		if (Validadores.longituLetrasMatricula(letras))
		{
			//Hacemos un clon para dar un valor a THIS.letras
			String copia = letras;
			this.letras = copia;
		}else {
			throw new LetrasMatriculaNoValidasException("Se esperaba solo la cadena final de matrícula. Ejemplo: 'DML'");
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
		return "" + numeros + letras + "";
	}


	//Constructores
	
	/**
	 * Constructor de copia
	 * @param otra	 La matrícula a copiar
	 * @throws LetrasMatriculaNoValidasException Las letras de la matrícula no pueden ser de otro tipo que no sea {@code String}
	 * @throws NumeroMatriculaNoValidoException El tamaño y tipo de los números de la matrícula. {@code int}
	 */
	public Matricula(Matricula otra) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException {
		this.setLetras(otra.letras);
		this.setNumeros(otra.numeros);
	}
	
	/**
	 * Constructor completo
	 * @param numeros	Los números que tiene una matrícula al principio generalmente
	 * @param letras	Las letras que acompañan a los números y que también componen la matrícula
	 * @throws LetrasMatriculaNoValidasException Las letras de la matrícula sólo pueden ser tipo {@code String} y respetar su tamaño 
	 * @throws NumeroMatriculaNoValidoException Los números de la matrícula se escriben por separado, por tanto sólo pueden ser tipo {@code int}
	 */
	public Matricula(int numeros, String letras) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException {
		super();
		this.setLetras(letras);
		this.setNumeros(numeros);
	}

	
	@Override
	public int compareTo(Matricula o) {
		String matricula1 = this.toString();
		String matricula2 = o.toString();
		return matricula1.compareToIgnoreCase(matricula2);
	}
	
	
	
	
	
}
