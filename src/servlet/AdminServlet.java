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

import com.google.gson.Gson;

import database.account.Account;
import database.account.AccountController;

import user.Admin;
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
		// Check authenticity:
		Admin admin = Helper.checkAuthenticity(request.getSession(),
				Admin.class);
		if (admin == null) {
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_INDEX);
			return;
		}
		// Switch action on path:
		String path = request.getPathInfo();
		log.write("AdminServlet", "Received request: " + path);
		if (path.equals("/js/loadAccounts")) {
			Vector<Account> accounts = accountController.getAllAccounts();
			response.setContentType("application/json");
			response.getWriter().write(
					gson.toJson(accounts, accounts.getClass()));
		}
		// Delete an account:
		else if (path.equals("/js/deleteAccount")) {
			// Get username parameter:
			String username = request.getParameter("name");
			// Check if legal:
			if (username == null || username.isEmpty()) {
				log.write("AdminServlet", "Username invalid!");
				response.setContentType("text/error");
				response.getWriter().write("Username invalid!");
				return;
			}
			// Do & check if all okay:
			if (!admin.deleteAccount(username)) {
				response.setContentType("text/error");
				response.getWriter()
						.write("Dieser Benutzer existiert nicht oder ist aktuell angemeldet. Kann nicht gelöscht werden!");
			}
		}
		// Get the information of an account:
		else if (path.equals("/js/getAccountData")) {
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
		} else if (path.equals("/js/addAccount")) {
			// /hiwi/Admin/js/addAccount?realName=&email=&userName=&userPassword=&accountType=&institute=
			String realName = request.getParameter("realName");
			String email = request.getParameter("email");
			String userName = request.getParameter("userName");
			String password = request.getParameter("userPassword");
			int accountType = -1;
			int institute = -1;
			try {
				institute = Integer.parseInt(request.getParameter("institute"));
				accountType = Integer.parseInt(request
						.getParameter("accountType"));
			} catch (NumberFormatException e) {
				log.write("AdminServlet",
						"NumberFormatException while parsing URL!");
				response.setContentType("text/error");
				response.getWriter()
						.write("Fehler bei Eingabe! Nur ganze Zahlen erlaubt für Institut und AccountType.");
				return;
			}
			if (realName == null || realName.isEmpty() || userName == null
					|| email == null || email.isEmpty() || userName.isEmpty()
					|| password == null || password.isEmpty()
					|| accountType < 0 || accountType > 3 || institute == -1) {
				log.write("AdminServlet", "Error in parameters!");
				response.setContentType("text/error");
				response.getWriter().write("Werte illegal!");
				return;
			}
			// If already exists:
			if (accountController.getAccountByUsername(userName) != null) {
				log.write("AdminServlet",
						"Error creating account – username alreay exists!");
				response.setContentType("text/error");
				response.getWriter()
						.write("Benutzername ist bereits vergeben!");
				return;
			}
			// Okay, all okay, continue:
			Account account = new Account(userName, password, accountType,
					email, realName, institute, null);
			if (!admin.createAccount(account)) {
				response.setContentType("text/error");
				response.getWriter()
						.write("Account konnte nicht erstellt werden! Existiert das Institut in der Datenbank?");
				return;
			}
			response.setContentType("text/plain");
			response.getWriter().write("true");
			return;
		} else if (path.equals("/js/getSystemInformation")) {
			response.setContentType("application/json");
			Runtime r = Runtime.getRuntime();
			response.getWriter().write(
					Helper.jsonAtor(
							new String[] { "loggedInUsers", "allUsers",
									"totalRAM", "maxRAM" },
							new Object[] { LoggedInUsers.getUsers().size(),
									accountController.accountCount(),
									r.totalMemory() / (1024 * 1024) + " MB",
									r.maxMemory() / (1024 * 1024) + " MB" }));
		} else {
			log.write("AdminServlet", "Unknown parameters <" + path + ">");
		}
	}
}