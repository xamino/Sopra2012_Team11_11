/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Vector;

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
import database.offer.Offer;
import database.offer.OfferController;

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
		if(path.equals("/js/loadAccount")){
			String username = applicant.getUserData().getUsername();
			String email = applicant.getUserData().getEmail();
			String JsonString = Helper.jsonAtor(new String[]{"username","email"}, new String[]{username,email});
			response.setContentType("application/json");
			response.getWriter().write(JsonString);
		} 
		
		else if (path.equals("/js/deleteApplication")) {
			int aid = Integer.parseInt(request.getParameter("aid"));
			try {
				applicant.deleteApplication(aid);
				response.setContentType("text/text");
				response.getWriter().write("deleted");
			} catch (SQLException e) {
				response.setContentType("text/error");
				response.getWriter().write("Failed to delete application!");
			}
		}
		else if (path.equals("/js/loadOffers")) {
			Vector<Offer> offers = OfferController.getInstance().getAllOffers();
			response.setContentType("application/json");
			response.getWriter().write(
					gson.toJson(offers, offers.getClass()));
		}
		else if(path.equals("/js/deleteAccount")){
			applicant.deleteOwnAccount();		
		}
		
		else {
			//log.write("ApplicantServlet", "Unknown path <" + path + ">");
		}

	}
}
