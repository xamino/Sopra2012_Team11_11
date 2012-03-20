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
 * Das <code>Admin</code> Servlet behandelt alle Aktionen von angemeldeten
 * Administratoren.
 */

@WebServlet("/Admin/*")
public class AdminServlet extends HttpServlet {
	/**
	 * Standard serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * Variable zum speicher der Log Instanz.
	 */
	private Log log;

	public AdminServlet() {
		super();
		log = Helper.log;
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		log.write("AdminServlet", "Received request: " + path);
		// TODO: implement authenticity check – is user valid & logged in?
		// log.write("admin_servlet", "Checked authentication.");
		// Switch action on path:
		if (path.equals("/accountsmanagement"))
			response.sendRedirect(Helper.D_ADMIN_ACCOUNTSMANAGEMENT);
		else if (path.equals("/documentsmanagement"))
			response.sendRedirect(Helper.D_ADMIN_DOCUMENTSMANAGEMENT);
		else if (path.equals("/editaccount"))
			response.sendRedirect(Helper.D_ADMIN_EDITACCOUNT);
		else if (path.equals("/userindex"))
			response.sendRedirect(Helper.D_ADMIN_USERINDEX);
		else {
			log.write("AdminServlet", "Path not implemented! <" + path + ">");
			response.sendRedirect(Helper.D_INDEX);
		}
	}
}