/**
 * @author Tamino Hartmann
 */
package servlet;

import javax.servlet.http.HttpSession;

import com.sun.org.apache.bcel.internal.generic.Type;

import user.Admin;
import user.Applicant;
import user.Clerk;
import user.Provider;
import user.User;
import userManagement.LoggedInUsers;
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
	 * Pfad zu Datenschutzerklärung.
	 */
	public static final String D_PUBLIC_DATAAGREEMENT = "/hiwi/public/dataagreement.jsp";
	/**
	 * Pfad zu /hiwi/public/help.jsp.
	 */
	public static final String D_PUBLIC_HELP = "/hiwi/public/help.jsp";
	/**
	 * Pfad zu /hiwi/public/register.jsp.
	 */
	public static final String D_PUBLIC_REGISTER = "/hiwi/public/register.jsp";
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
	 * Pfad zu /hiwi/admin/help.jsp.
	 */
	public static final String D_ADMIN_HELP = "/hiwi/admin/help.jsp";
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
	 * Pfad zu /hiwi/clerk/help.jsp.
	 */
	public static final String D_CLERK_HELP = "/hiwi/clerk/help.jsp";
	/**
	 * Pfad zu /hiwi/applicant/userindex.jsp.
	 */
	public static final String D_APPLICANT_USERINDEX = "/hiwi/applicant/userindex.jsp";
	/**
	 * Pfad zu /hiwi/applicant/help.jsp.
	 */
	public static final String D_APPLICANT_HELP = "/hiwi/applicant/help.jsp";
	/**
	 * Pfad zu /hiwi/applicant/status.jsp.
	 */
	public static final String D_APPLICANT_STATUS = "/hiwi/applicant/status.jsp";
	/**
	 * Pfad zu /hiwi/applicant/accountmanagement.jsp.
	 */
	public static final String D_APPLICANT_ACCOUNTMANAGEMENT = "/hiwi/applicant/accountmanagement.jsp";
	/**
	 * Pfad zu /hiwi/provider/accountmanagement.jsp.
	 */
	public static final String D_PROVIDER_ACCOUNTMANAGEMENT = "/hiwi/provider/accountmanagement.jsp";
	/**
	 * Pfad zu /hiwi/provider/applicantlist.jsp.
	 */
	public static final String D_PROVIDER_APPLICANTLIST = "/hiwi/provider/applicantlist.jsp";
	/**
	 * Pfad zu /hiwi/provider/createoffer.jsp.
	 */
	public static final String D_PROVIDER_CREATEOFFER = "/hiwi/provider/createoffer.jsp";
	/**
	 * Pfad zu /hiwi/provider/editoffer.jsp.
	 */
	public static final String D_PROVIDER_EDITOFFER = "/hiwi/provider/editoffer.jsp";
	/**
	 * Pfad zu /hiwi/provider/userindex.jsp.
	 */
	public static final String D_PROVIDER_USERINDEX = "/hiwi/provider/userindex.jsp";
	/**
	 * Pfad zu /hiwi/provider/help.jsp.
	 */
	public static final String D_PROVIDER_HELP = "/hiwi/provider/help.jsp";

	/**
	 * All servlets within the servlet package should use this instance of Log.
	 */
	public static final Log log = Log.getInstance();

	/**
	 * Diese Hilfsmethode gibt an, ob eine Session eine gueltige Admin session
	 * ist.
	 * 
	 * @param session
	 *            Die session zum ueberpruefen.
	 * @return Das Admin Object wenn korrekt, sonst null.
	 */
	public static <U> U checkAuthenticity(HttpSession session,
			Class<U> c) {
		User user = LoggedInUsers.getUserBySession(session);
		if (user == null || !(user.getClass() == c)) {
			log.write("Helper", "User not authentic.");
			return null;
		}
		return (U) user;
	}
}
