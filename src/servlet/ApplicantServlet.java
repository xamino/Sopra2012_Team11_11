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

import user.Applicant;
import database.account.Account;
import database.application.Application;
import database.application.ApplicationController;
import database.document.Document;
import database.document.DocumentController;
import database.document.OfferDocument;
import database.offer.Offer;
import database.offer.OfferController;

/**
 * Das <code>Applicant</code> Servlet behandelt alle Aktionen von angemeldeten
 * Bewerbern (Applicants).
 */

@WebServlet("/Applicant/*")
public class ApplicantServlet extends HttpServlet {

	/**
	 * Standart default serial.
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * log
	 */
	private Log log = Helper.log;

	/**
	 * Variable zum speichern der GSON Instanz.
	 */
	private Gson gson;

	public ApplicantServlet() {
		super();
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
		// Do loadAccount:
		if (path.equals("/js/loadAccount")) {
			String username = applicant.getUserData().getUsername();
			String email = applicant.getUserData().getEmail();
			String JsonString = Helper.jsonAtor(new String[] { "username",
					"email" }, new String[] { username, email });
			response.setContentType("application/json");
			response.getWriter().write(JsonString);
		}
		// Delete an application:
		else if (path.equals("/js/deleteApplication")) {
			int aid = Integer.parseInt(request.getParameter("aid"));
			if (!applicant.deleteApplication(aid)) {
				response.setContentType("text/text");
				response.getWriter().write("deleted");
				return;
			}
			response.setContentType("text/error");
			response.getWriter().write("Failed to delete application!");
			
		}
		// Load offers:
		else if (path.equals("/js/loadOffers")) {
			Vector<Offer> offers = OfferController.getInstance().getAllOffers();
			//bereits beworbene Stellen entfernen
			Applicant appli1 = Helper.checkAuthenticity(request.getSession(),Applicant.class);
			Vector<Offer> myoffers1 = OfferController.getInstance().getOffersByApplicatiot(ApplicationController.getInstance().getApplicationsByApplicant(appli1.getUserData().getUsername())); //Offer vom User geholt
			boolean entfernen;
			for(int i = 0; i < offers.size(); i++){
				entfernen = false;
				for(int j = 0; j<myoffers1.size(); j++){
					if(offers.elementAt(i).getAid() == myoffers1.elementAt(j).getAid()){
						entfernen = true;
					}
					if(entfernen){
						offers.remove(i);
					}
				}
			}
			response.setContentType("application/json");
			response.getWriter().write(gson.toJson(offers, offers.getClass()));
		}
		// Load my offers:
		else if (path.equals("/js/loadMyOffers")) {
			Applicant appli = Helper.checkAuthenticity(request.getSession(),
					Applicant.class);
			Vector<Offer> myoffers = OfferController.getInstance().getOffersByApplicatiot(ApplicationController.getInstance().getApplicationsByApplicant(appli.getUserData().getUsername())); //Offer vom User geholt
			response.setContentType("myapplication/json");
			response.getWriter().write(gson.toJson(myoffers, myoffers.getClass()));
		}
		// Load my information about one application:
		//noch nicht funktionsf�hig!!!
		else if (path.equals("/js/selectApplication")) {
			String aid = request.getParameter("id");
			int aid1 = Integer.parseInt(request.getParameter("id"));
			System.out.println(aid);
			System.out.println(aid1 +"neu");
			Vector<Offer> offersid = OfferController.getInstance().getAllOffers();
			
			String offername;
			Vector<OfferDocument> documents;
			
			for(int i=0; i<offersid.size(); i++){
				if(aid1 == offersid.elementAt(i).getAid()){
					System.out.println("drin: ");
					offername = offersid.elementAt(i).getName();
					System.out.println("name: "+offername);
					documents = DocumentController.getInstance().getDocumentsByOffer(Integer.parseInt(aid));
					response.setContentType("angebotx/json");
					response.getWriter().write(gson.toJson(offername, offername.getClass()));
				}
				
			}

			//}
		}
		// Delete own account:
		else if (path.equals("/js/deleteAccount")) {
			String name = applicant.getUserData().getUsername();
			if(applicant.deleteOwnAccount()){
				log.write("ApplicantServlet", name + " has deleted his account.");
				// Simply now for debugging:
				response.setContentType("text/plain");
				response.getWriter().write("true");
			}else{
				response.setContentType("text/error");
				response.getWriter().write("Error while deleting account!");
			}
		}
		else if(path.equals("/js/changeAccount")){
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pw = request.getParameter("pw");
			if(applicant.editOwnAccount(new Account(name, pw, 3, email, name, 0, null))){
				response.setContentType("text/plain");
				response.getWriter().write("true");
			}else{
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim ändern der Daten.");
			}
		}
		else {
			log.write("ApplicantServlet", "Unknown path <" + path + ">");
		}

	}
}
