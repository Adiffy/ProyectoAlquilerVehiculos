package gui;

import javax.swing.JTextField;
import javax.swing.text.Document;

public class MiJTextField extends JTextField {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public MiJTextField() {
		// Constructor vac�o
	}

	public MiJTextField(String text) {
		super(text);
		// Constructor con una {@Code String} como PlaceHolder
	}

	public MiJTextField(int columns) {
		super(columns);
		// El {@Code int} que ind�ca el m�ximo de columnas
	}

	public MiJTextField(String text, int columns) {
		super(text, columns);
		// El constructor al que se le da el PlaceHolder y el m�ximo de caract�res
	}

	public MiJTextField(Document doc, String text, int columns) {
		super(doc, text, columns);
		// Constructor completo de TextField
	}

}
