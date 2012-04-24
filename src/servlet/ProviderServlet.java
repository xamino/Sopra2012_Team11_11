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

import logger.Log;

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
	 * Log instanz für Serverausgaben
	 */
	private Log log = Helper.log;
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
	// Delete own account:
	else if (path.equals("/js/deleteAccount")) {
		String name = provider.getUserData().getUsername();
		if(provider.deleteOwnAccount()){
			log.write("ApplicantServlet", name + " has deleted his account.");
			// Simply now for debugging:
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_INDEX);
		}else{
			response.setContentType("text/error");
			response.getWriter().write("Error while deleting account!");
		}
	}
	// change  own account data
	else if(path.equals("/js/changeAccount")){
		String name = request.getParameter("name");
		String email = request.getParameter("mail");
		String pw = request.getParameter("pw");
		if(pw.equals(""))pw=null; //falls leeres pw-> null damit die editOwnAccount funktion das pw nicht auf "" setzt!
		if(provider.editOwnAccount(name, email, pw)){
			log.write("ApplicantServlet", provider.getUserData().getUsername() + " has modified his account.");
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
		}else{
			response.setContentType("text/error");
			response.getWriter().write("Fehler beim ändern der Daten.");
		}
	}
	else 	// Do loadAccount:
		if (path.equals("/js/loadAccount")) {
			String realName = provider.getUserData().getName();
			String email = provider.getUserData().getEmail();
			String JsonString = Helper.jsonAtor(new String[] { "realName",
					"email" }, new String[] { realName, email});
			response.setContentType("application/json");
			response.getWriter().write(JsonString);
		}
	}
}