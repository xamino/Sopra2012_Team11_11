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
@SuppressWarnings("serial")
public class UnknownOptionException extends Exception {

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
