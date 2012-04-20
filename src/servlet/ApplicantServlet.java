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

import com.google.gson.Gson;

import user.Applicant;
import user.Provider;
import database.account.Account;
import database.account.AccountController;

/**
 * Das <code>Applicant</code> Servlet behandelt alle Aktionen von angemeldeten
 * Bewerbern (Applicants).
 */



@WebServlet("/Applicant/*")
public class ApplicantServlet extends HttpServlet {
	
	/**
	 * Variable zum speicher der Instanz des AccountController.
	 */
	private AccountController accountController;
	
	private static final long serialVersionUID = 1L;

	
	/**
	 * Variable zum speichern der GSON Instanz.
	 */
	private Gson gson;
	
	
	public ApplicantServlet() {
		super();
		accountController = AccountController.getInstance();
		gson = new Gson();
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Check authenticity:
		Applicant applicant = Helper.checkAuthenticity(request.getSession(),
				Applicant.class);
		if (applicant == null) {
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_INDEX);
			return;
		}
		// Switch action on path:
		String path = request.getPathInfo();
		// Only activate this if you need to debug the path:
		// log.write("AdminServlet", "Received request <" + path+">.");
		if (path.equals("/js/loadAccount")) {
			Account currentaccount = Helper.checkAuthenticity(request.getSession(),
					Account.class);
			response.setContentType("application/json");
			response.getWriter().write(gson.toJson(currentaccount, currentaccount.getClass()));
		}
		
		// Switch action on path:
		path = request.getPathInfo();
		System.out.println(path);
	}
}
