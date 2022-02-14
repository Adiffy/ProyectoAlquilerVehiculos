package clases;

import java.util.Objects;

public class Bastidor {

	//Propiedades
	private String letras;
	private int num;
	
	//Getters & Setters
	
	private void setNum(int numero) {
		if (String.valueOf(numero).length()==5) //5 n�meros al final del n� de bastidor
		{
			this.num = numero;
		}
	}
	
	public int getNum() {
		return num;
	}
	
	public String getLetras() {
		return letras;
	}

	private void setLetras(String letras) {
		String letra = letras;
		this.letras = letra;
	}

	//Metodos
	
	@Override
	public int hashCode() {
		return Objects.hash(letras, num);
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
		Bastidor otro = (Bastidor) obj;
		return Objects.equals(letras, otro.letras) && num == otro.num;
	}

	@Override
	public String toString() {
		return "N�mero de bastidor: "+ letras + num ;
	}
	
	//Constructores

	/**
	 * Constructor completo de un {@code Objeto} del tipo <em>N�mero de bastidor</em>
	 * Este contiene letras y un n�mero entero.
	 * @param letras	Las letras del n�mero de bastidor. Tipo	({@code String})
	 * @param num		El n�mero que viene justo depu�s de las letras. Tipo ({@code int})
	 */
	public Bastidor(String letras, int num) {
		super();
		this.setLetras(letras);
		this.setNum(num);
	}
	
	/**
	 * Constructor de copia
	 * Se le introduce un n� de bastidor (Objeto tipo {@code Bastidor}) y este
	 * lo copia creando uno nuevo
	 * @param otro El objeto 'Bastidor' a copiar
	 */
	public Bastidor(Bastidor otro) {
		this.setLetras(otro.letras);
		this.setNum(otro.num);
	}
	
	

}
