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
	public static final String D_INDEX = "/hiwi/public/index.jsp";
	/**
	 * Pfad zu /admin/accountsmanagement.jsp.
	 */
	public static final String D_ADMIN_ACCOUNTSMANAGEMENT = "/hiwi/admin/accountsmanagement.jsp";
	/**
	 * Pfad zu /admin/documentsmanagement.jsp.
	 */
	public static final String D_ADMIN_DOCUMENTSMANAGEMENT = "/hiwi/admin/documentsmanagement.jsp";
	/**
	 * Pfad zu /admin/editaccount.jsp.
	 */
	public static final String D_ADMIN_EDITACCOUNT = "/hiwi/admin/editaccount.jsp";
	/**
	 * Pfad zu /admin/userindex.jsp.
	 */
	public static final String D_ADMIN_USERINDEX = "/hiwi/admin/userindex.jsp";
	/**
	 * Pfad zu /hiwi/clerk/userindex.jsp.
	 */
	public static final String D_CLERK_USERINDEX = "/hiwi/clerk/userindex.jsp";
	/**
	 * Pfad zu /hiwi/clerk/accountmanagement.jsp.
	 */
	public static final String D_CLERK_ACCOUNTMANAGEMENT = "/hiwi/clerk/accountmanagement.jsp";
	/**
	 * Pfad zu /hiwi/clerk/offermanagement.jsp.
	 */
	public static final String D_CLERK_OFFERMANAGEMENT = "/hiwi/clerk/offermanagement.jsp";
	/**
	 * Pfad zu /hiwi/clerk/applicationmanagement.jsp.
	 */
	public static final String D_CLERK_APPLICATIONMANAGEMENT = "/hiwi/clerk/applicationmanagement.jsp";
	/**
	 * Pfad zu /hiwi/applicant/userindex.jsp.
	 */
	public static final String D_APPLICANT_USERINDEX = "/hiwi/applicant/userindex.jsp";
	/**
	 * Pfad zu /hiwi/applicant/status.jsp.
	 */
	public static final String D_APPLICANT_STATUS = "/hiwi/applicant/status.jsp";
	/**
	 * Pfad zu /hiwi/applicant/accountmanagement.jsp.
	 */
	public static final String D_APPLICANT_ACCOUNTMANAGEMENT = "/hiwi/applicant/accountmanagement.jsp";
	
	
	/**
	 * All servlets within the servlet package should use this instance of Log.
	 */
	public static final Log log = Log.getInstance();
}
