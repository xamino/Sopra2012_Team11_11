package config;
/**
 * 
 * @author Manuel Guentzel
 *
 */
/**
 * 
 * Exception Klasse fuer fehlerhaften Zugriff auf eine Option
 *
 */
@SuppressWarnings("serial")
public class IllegalTypeException extends Exception {

	/**
	 * StandardKonstruktor
	 */
	public IllegalTypeException() {
		super();
	}
	/**
	 * Konstruktor mit Moeglichkeit der Stringuebergabe
	 * @param s 
	 * 			String mit zusaetzlichen Informationen
	 */
	public IllegalTypeException(String s){
		super(s);
	}
}
