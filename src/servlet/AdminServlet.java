/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
 */
package servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import database.account.Account;
import database.account.AccountController;

import user.Admin;
import user.User;
import userManagement.LoggedInUsers;

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
	/**
	 * Variable zum speicher der Instanz des AccountController.
	 */
	private AccountController accountController;
	/**
	 * Variable zum speichern der GSON Instanz.
	 */
	private Gson gson = new Gson();

	public AdminServlet() {
		super();
		log = Helper.log;
		accountController = AccountController.getInstance();
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		log.write("AdminServlet", "Received request: " + path);
		// Switch action on path:
		if (path.equals("/js/loadAccounts")) {
			if (!checkAuthenticity(request.getSession())) {
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_INDEX);
				return;
			}
			Vector<Account> accounts = new Vector<Account>();
			accounts = accountController.getAllAccounts();
			response.setContentType("application/json");
			response.getWriter().write(
					gson.toJson(accounts, accounts.getClass()));

		} else {
			response.sendRedirect(Helper.D_INDEX);
		}
	}

	/**
	 * Diese Hilfsmethode gibt an, ob eine Session eine gueltige Admin session
	 * ist.
	 * 
	 * @param session
	 *            Die session zum ueberpruefen.
	 * @return <code>True</code> wenn ja, sonst <code>false</code>.
	 */
	private boolean checkAuthenticity(HttpSession session) {
		User user = LoggedInUsers.getUserBySession(session);
		if (user == null || !(user instanceof Admin)) {
			log.write("AdminServlet", "Admin NOT authenticate.");
			return false;
		}
		log.write("Admin Servlet", "Admin authenticated.");
		return true;
	}
}