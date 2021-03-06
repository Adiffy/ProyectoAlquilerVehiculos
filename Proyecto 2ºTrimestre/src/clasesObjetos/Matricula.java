package clasesObjetos;

import java.io.Serializable;
import java.util.Objects;

import exceptions.LetrasMatriculaNoValidasException;
import exceptions.NumeroMatriculaNoValidoException;

public class Matricula implements Comparable<Matricula>, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	//Propiedades
	private int numeros; //Los n? de la matr?cula 
	private String letras; //Las letras al final de cada matr?cula 
	
	
	//Getters & Setters
	public int getNumeros() {
		return numeros;
	}
	
	private void setNumeros(int numeros) throws NumeroMatriculaNoValidoException {
		if (String.valueOf(numeros).length()<5 && String.valueOf(numeros).length()>2) //TODO revisar cuantos n? hay en matr?cula
		{
			this.numeros = numeros;
		}else {
			throw new NumeroMatriculaNoValidoException("Se esperaba ?nicamente la parte num?rica de su matr?cula. Por ejemplo: 3025");
		}
	}
	
	public String getLetras() {
		return letras;
	}
	
	private void setLetras(String letras) throws LetrasMatriculaNoValidasException {
		if (letras.length()==3)
		{
			this.letras = letras;
		}else {
			throw new LetrasMatriculaNoValidasException("Se esperaba solo la cadena final de matr?cula. Ejemplo: 'DML'");
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
	 * @param otra	 La matr?cula a copiar
	 * @throws LetrasMatriculaNoValidasException Las letras de la matr?cula no pueden ser de otro tipo que no sea {@code String}
	 * @throws NumeroMatriculaNoValidoException El tama?o y tipo de los n?meros de la matr?cula. {@code int}
	 */
	public Matricula(Matricula otra) throws LetrasMatriculaNoValidasException, NumeroMatriculaNoValidoException {
		this.setLetras(otra.letras);
		this.setNumeros(otra.numeros);
	}
	
	/**
	 * Constructor completo
	 * @param numeros	Los n?meros que tiene una matr?cula al principio generalmente
	 * @param letras	Las letras que acompa?an a los n?meros y que tambi?n componen la matr?cula
	 * @throws LetrasMatriculaNoValidasException Las letras de la matr?cula s?lo pueden ser tipo {@code String} y respetar su tama?o 
	 * @throws NumeroMatriculaNoValidoException Los n?meros de la matr?cula se escriben por separado, por tanto s?lo pueden ser tipo {@code int}
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
