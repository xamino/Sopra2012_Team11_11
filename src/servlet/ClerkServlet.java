/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
 */
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logger.Log;

/**
 * Das <code>Clerk</code> Servlet behandelt alle Aktionen von angemeldeten
 * (Sach-)Bearbeitern (Clerk).
 */

@WebServlet("/Clerk/*")
public class ClerkServlet extends HttpServlet {
	/**
	 * Standard serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variable zum speicher der Log Instanz.
	 */
	private Log log;

	/**
	 * Konstruktor.
	 */
	public ClerkServlet() {
		super();
		log = Helper.log;
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		log.write("ClerkServlet", "Received request: " + path);

		if (path.equals("/js/doExcelExport")) {
			log.write("ClerkServlet", "Excel export requested.");
			response.sendRedirect(Helper.D_CLERK_USERINDEX);
		}
	}
}
