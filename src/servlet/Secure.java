/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
 * @author Manuel Güntzel
 */
package servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import database.account.Account;
import database.account.AccountController;

import userManagement.LoggedInUsers;
import userManagement.UserFactory;

import logger.Log;

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
			Account acc = AccountController.getInstance().getAccountByUsername(userName);
			if(acc==null){
				response.setContentType("text/plain");
				response.getWriter().write("false");
				log.write("Secure", "Login failed: Wrong username or password");
			}
			else if(!(userPassword.equals(acc.getPasswordhash()))){
				response.setContentType("text/plain");
				response.getWriter().write("false");
				log.write("Secure", "Login failed: Wrong username or password");
			}
			else{
				log.write("Secure", "Login successful");
				HttpSession session = request.getSession();
				session.setAttribute("userName", new String(userName));
				session.setMaxInactiveInterval(15*60);
				UserFactory.getUserInstance(acc, session);
				response.sendRedirect(Helper.D_ADMIN_USERINDEX);
			}
			
		}
		// If register is asked:
		else if (path.equals("/js/doRegister")) {
			// First get parameters:
			String realName = request.getParameter("realName");
			String email = request.getParameter("email");
			String userName = request.getParameter("userName");
			String password = request.getParameter("userPassword");
			// Check for null or empty (against hacks& errors)
			if (realName.isEmpty() || email.isEmpty() || userName.isEmpty()
					|| password.isEmpty()) {
				System.out.println("Secure: data for register invalid!");
				response.setContentType("text/plain");
				response.getWriter().write("false");
				return;
			}
			log.write("Secure", "Register: " + realName + ", " + email + " as "
					+ userName + " with " + password + " as password.");
			// TODO: The response will be written accordingly to what we need.
			// That means that if the register was successful, write the URL to
			// redirect to into the packet. Otherwise write false into it. If
			// sending an URL the MIME type should be set accordingly
			// ("text/url").
			response.setContentType("text/plain");
			response.getWriter().write("true");
		}
		// If unknown path:
		else {
			log.write("Secure", "Unknown operation!");
			response.sendRedirect(Helper.D_INDEX);
		}
	}
}