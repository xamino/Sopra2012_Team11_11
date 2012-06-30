package config;
/**
 * 
 * @author Manuel Guentzel
 *
 */
/**
 * 
 * Exception Klasse fuer Zugriff auf nicht vorhandene Optionen
 *
 */
public class UnknownOptionException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2267238564959385228L;
	/**
	 * StandardKonstruktor
	 */
	public UnknownOptionException() {
		super();
	}
	/**
	 * Konstruktor mit Moeglichkeit der Stringuebergabe
	 * @param s String mit zusaetzlichen Informationen
	 */
	public UnknownOptionException(String s){
		super(s);
	}
}
