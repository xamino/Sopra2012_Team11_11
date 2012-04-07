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

		}
		// Delete an account:
		else if (path.equals("/js/deleteAccount")) {
			if (!checkAuthenticity(request.getSession())) {
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_INDEX);
				return;
			}
			String username = request.getParameter("name");
			if (username == null || username.isEmpty()) {
				log.write("AdminServlet", "Username invalid!");
				response.setContentType("text/error");
				response.getWriter().write("Username invalid!");
				return;
			}
			// If user is currently logged in, we do not allow deletion:
			if (LoggedInUsers.getUserByUsername(username) != null) {
				log.write("AdminServlet", "Can not delete <" + username
						+ "> as currently logged in!");
				response.setContentType("text/error");
				response.getWriter()
						.write("Dieser Benutzer ist aktuell angemeldet! Kann nicht gelöscht werden!");
				return;
			}
			Account account = accountController.getAccountByUsername(username);
			if (account == null) {
				log.write("AdminServlet", "Can not delete <" + username
						+ "> as no account exists!");
				response.setContentType("text/error");
				response.getWriter().write("This user doesn't seem to exist!");
				return;
			}
			log.write("AdminServlet", "Deleting account with username <"
					+ username + ">");
			accountController.deleteAccount(account);
		}
		// Get the information of an account:
		else if (path.equals("/js/getAccountData")) {
			if (!checkAuthenticity(request.getSession())) {
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_INDEX);
				return;
			}
			String username = request.getParameter("name");
			if (username == null) {
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_ADMIN_ACCOUNTSMANAGEMENT);
				return;
			}
			log.write("AdminServlet", "Getting data of account with username <"
					+ username + ">");
			Account account = accountController.getAccountByUsername(username);
			response.setContentType("application/json");
			response.getWriter().write(gson.toJson(account, Account.class));
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
		// TODO: Comment when done debugging:
		log.write("AdminServlet", "Admin authenticated.");
		return true;
	}
}