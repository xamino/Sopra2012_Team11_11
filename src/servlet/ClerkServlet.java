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

import database.application.ApplicationController;
import database.offer.Offer;
import database.offer.OfferController;

import user.Applicant;
import user.Clerk;

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
	 * Variable zum speichern der GSON Instanz.
	 */
	private Gson gson;
	
	/**
	 * Konstruktor.
	 */
	public ClerkServlet() {
		super();
		log = Helper.log;
		gson = new Gson();
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// Check authenticity:
		Clerk clerk = Helper.checkAuthenticity(request.getSession(),
				Clerk.class);
		if (clerk == null) {
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_INDEX);
			return;
		}
		String path = request.getPathInfo();
		log.write("ClerkServlet", "Received request: " + path);

		if (path.equals("/js/doExcelExport")) {
			log.write("ClerkServlet", "Excel export requested.");
			// For now, simply redirect to userindex:
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_CLERK_USERINDEX);
		}
		// Load the offers of the clerk:
		else if (path.equals("/js/showMyOffers")) {
			Clerk clerk1 = Helper.checkAuthenticity(request.getSession(),
					Clerk.class);					//wie wird definiert welche Angebote welcher clerk hat??
			Vector<Offer> myoffers = OfferController.getInstance().getAllOffers(); //Offer vom User geholt
			response.setContentType("offers/json");
			response.getWriter().write(gson.toJson(myoffers, myoffers.getClass()));
		}
	}
}
