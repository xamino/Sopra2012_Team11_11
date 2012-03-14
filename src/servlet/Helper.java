/**
 * @author Tamino Hartmann
 */
package servlet;

import logger.Log;

/**
 * Hilfsklasse fuer Statische Variablen und Methoden der Servlets.
 */
public final class Helper {
	/**
	 * Leerer, privater Konstruktor. Es sollte von dieser Klasse keine Instanzen
	 * geben.
	 */
	private Helper() {

	}

	// Notiz fuer alle Pfadnamen: ist ein Link ein direkter Link (zeigt also
	// direkt auf eine Datei und nich auf einen Servlet), so beginnt dieser mit
	// "D_".

	/**
	 * Systemunabhaengiger Pfad zum Projektordner.
	 */
	public static final String PROJECT_PATH = "/hiwi/";
	/**
	 * Pfad zum Index direkt.
	 */
	public static final String D_INDEX = "/hiwi/index.jsp";

	/**
	 * All servlets within the servlet package should use this instance of Log.
	 */
	public static final Log log = Log.getInstance();
}
