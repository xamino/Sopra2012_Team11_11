package config;
/**
 * 
 * @author Manuel Guentzel
 *
 */
/**
 * 
 * Exception Klasse fuer Fehlerhaften zugriff auf eine Optrion
 *
 */
public class IllegalTypeException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7679246204885544615L;
	/**
	 * StandardKonstruktor
	 */
	public IllegalTypeException() {
		super();
	}
	/**
	 * Konstruktor mit Moeglichkeit der Stringuebergabe
	 * @param s String mit zusaetzlichen Informationen
	 */
	public IllegalTypeException(String s){
		super(s);
	}
}
