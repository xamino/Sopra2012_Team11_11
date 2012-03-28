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
import login.LoginController;

/**
 * Das <code>Secure</code> Servlet behandelt den Login der Benutzer, die
 * Registrierung von neuen Bewerbern, und der Logout von Benutzern abgearbeitet.
 */

@WebServlet("/Secure/*")
public class Secure extends HttpServlet {
	/**
	 * Standard serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Private Instanz des Loggers. Alle Systemausgaben werden an den Logger
	 * gegeben.
	 */
	private Log log;

	/**
	 * Konstruktor.
	 */
	public Secure() {
		super();
		this.log = Log.getInstance();
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		log.write("Secure", "Received request <" + path + ">.");
		// If login is asked:
		if (path.equals("/js/doLogin")) {
			String userName = request.getParameter("userName");
			String userPassword = request.getParameter("userPassword");
			log.write("Secure", "Checking login: <" + userName + ">:<"
					+ userPassword + ">");
			
		}
		// If register is asked:
		else if (path.equals("/js/doRegister")) {
			log.write("Secure", "Register requested.");
		}
		// If unknown path:
		else {
			log.write("Secure", "Unknown operation!");
			response.sendRedirect(Helper.D_INDEX);
		}
	}
}