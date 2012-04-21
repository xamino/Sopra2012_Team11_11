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

import user.Admin;
import user.Applicant;
import user.Provider;

/**
 * Das <code>Provider</code> Servlet behandelt alle Aktionen von angemeldeten
 * Anbietern / Mitgliedern (Provider).
 */

@WebServlet("/Provider/*")
public class ProviderServlet extends HttpServlet {
	/**
	 * Standard serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Variable zum speichern der GSON Instanz.
	 */
	private Gson gson;
	
	/**
	 * Konstruktor.
	 */
	public ProviderServlet() {
		super();
		gson = new Gson();
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	// Check authenticity:
	Provider provider = Helper.checkAuthenticity(request.getSession(),
			Provider.class);
	if (provider == null) {
		response.setContentType("text/url");
		response.getWriter().write(Helper.D_INDEX);
		return;
	}
	// Switch action on path:
	String path = request.getPathInfo();
	// Load my offers:
	if (path.equals("/js/loadOffers")) {
		Provider provi = Helper.checkAuthenticity(request.getSession(),
				Provider.class);
		Vector<Offer> myoffers = OfferController.getInstance().getOffersByProvider(provi); //Offer vom Provider geholt
		response.setContentType("offer/json");
		response.getWriter().write(gson.toJson(myoffers, myoffers.getClass()));
			}
	}
}