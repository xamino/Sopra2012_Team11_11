/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
 * @author Oemer Sahin
 */
package servlet;

import java.io.IOException;
import java.util.Date;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logger.Log;

import com.google.gson.Gson;

import database.account.Account;
import database.account.AccountController;
import database.application.Application;
import database.application.ApplicationController;
import database.document.AppDocument;
import database.document.Document;
import database.document.DocumentController;
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
			Vector<Offer> myoffers = OfferController.getInstance()
					.getOffersByProvider(provider); // Offer vom Provider geholt
			response.setContentType("offer/json");
			response.getWriter().write(
					gson.toJson(myoffers, myoffers.getClass()));
		}
		// Delete own account:
		else if (path.equals("/js/deleteAccount")) {
			String username = provider.getUserData().getUsername();
			if (provider.deleteOwnAccount()) {
				log.write("ApplicantServlet", username
						+ " has deleted his account.");
				// Simply now for debugging:
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_INDEX);
			} else {
				log.write("ApplicantServlet","There was an error while deleting account with username:"+ username);
				response.setContentType("text/error");
				response.getWriter().write("Error while deleting account!");
			}
		}
		// change own account data
		else if (path.equals("/js/changeAccount")) {
			String name = request.getParameter("name");
			String email = request.getParameter("mail");
			String pw = request.getParameter("pw");
			if (pw.equals(""))
				pw = null; // falls leeres pw-> null damit die editOwnAccount
							// funktion das pw nicht auf "" setzt!
			if (provider.editOwnAccount(name, email, pw)) {
				log.write("ApplicantServlet", provider.getUserData()
						.getUsername() + " has modified his account.");
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
			} else {
				log.write("ApplicantServlet","There was an error while modifying account with username:"+ provider.getUserData().getUsername());
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim ändern der Daten.");
			}
		} else // Do loadAccount:
		if (path.equals("/js/loadAccount")) {
			String realName = provider.getUserData().getName();
			String email = provider.getUserData().getEmail();
			String JsonString = Helper.jsonAtor(new String[] { "realName",
					"email" }, new String[] { realName, email });
			response.setContentType("application/json");
			response.getWriter().write(JsonString);
		}
		// Creates an Vector with all applicants from the selected Offer
		else if (path.equals("/js/applicantChoice")) {
			int aid = Integer.parseInt(request.getParameter("aid"));
			// System.out.println(aid);
			Vector<Application> app = ApplicationController.getInstance()
					.getApplicationsByOffer(aid);
			Vector<Account> acc = new Vector<Account>();
			for (int i = 0; i < app.size(); i++) {
				acc.add(AccountController.getInstance().getAccountByUsername(
						app.elementAt(i).getUsername()));
			}
			// System.out.println("Ergebnis: "+docs2);
			log.write("ProviderServlet","Creating vector with all applicants from selected offer in progress...");
			response.setContentType("showtheapplicants/json");
			response.getWriter().write(gson.toJson(acc, acc.getClass()));

		}

		// Creating a new Offer
		else if (path.equals("/js/addOffer")) {
			// System.out.println("PROVIDER_SERVLET, PATH: ADD OFFER");
			//Provider provi = Helper.checkAuthenticity(request.getSession(),Provider.class);   --> provider von oben benutzen!!
								
			//Generating AID
			Vector<Offer> allOffers = OfferController.getInstance().getAllOffers();
						
			double aidRandom = 100+Math.random()*( Math.pow(999, Math.random())+100);
			int aid = (int)aidRandom;	
			
			//check if AID is already existing
			for (int i = 0; i < allOffers.size(); i++) {			
				if (allOffers.elementAt(i).getAid() == aid) {
					aidRandom = 100+Math.random()*( Math.pow(999, Math.random())+100);
					aid = (int)aidRandom;
					i=0;//restart checking
				}
			}
			//Getting the data from delivered connection content to save it as a new offer-object in the db.
			String ersteller = provider.getUserData().getUsername();
			String name = request.getParameter("titel");
			String notiz = request.getParameter("notiz");
			boolean checked = false;
			int stellen;
			try {
				stellen = Integer.parseInt(request.getParameter("stellen"));
			} catch (NumberFormatException e) {
				// System.out.println("ERROR WHILE PARSING DOUBLE IN ProviderServlet");
				log.write("ProviderServlet","There was an error while PARSING double-value(stellen) in: "+path.toString());
				response.setContentType("text/error");
				response.getWriter()
						.write("Fehler beim Parsen! Kein/ungueltiger Wert eingegeben [INT Wert von 'Stellen' pruefen]");
				return;
			}

			double stunden;
			try {
				// in der DB Std/Woche, in der HTML Std/Monat
				stunden = Double.parseDouble(request.getParameter("std")); 
				
			} catch (NumberFormatException e) {
				// System.out.println("ERROR WHILE PARSING DOUBLE IN ProviderServlet");
				log.write("ProviderServlet","There was an error while PARSING double-value(std) in: "+path.toString());
				response.setContentType("text/error");
				response.getWriter()
						.write("Fehler beim Parsen! Kein/ungueltiger Wert eingegeben [DOUBLE Wert von 'Std' pruefen]");
				return;
			}

			String beschreibung = request.getParameter("beschreibung");

			Date startdatum, enddatum, aenderungsdatum; // TODO
			startdatum = new Date();
			enddatum = new Date();
			aenderungsdatum = new Date();
			double lohn = 10.7;// TODO

			int institut = 0; // TODO
			
			//in log schreiben 
			if (name== null ||
			  name.isEmpty() || stunden == 0 || stellen ==0 || beschreibung.isEmpty()||
			  beschreibung==null || notiz == null || notiz.isEmpty() ||startdatum == null || 
			  enddatum==null || aenderungsdatum==null ||lohn == 0 || institut<0 ) 
			{
			  log.write("ProviderServlet", "Error in parameters!Path: "+path.toString());
			  response.setContentType("text/error");
			  response.getWriter().write("Werte illegal!"); return; }
			 

			// If already exists:
			//Vector<Offer> allOffers = OfferController.getInstance().getAllOffers();

			for (int i = 0; i < allOffers.size(); i++) {
				if (allOffers.elementAt(i).getName().equals(name)) {
					// System.out.println("ANGEBOT EXSISTIERT BEREITS, NAME VORHANDEN!");
					log.write("ProviderServlet", "Error while creating new offer! -->Offer (name) is already exisitng! PATH: "+path.toString());
					response.setContentType("text/error");
					response.getWriter().write("Angebot ist bereits vorhanden (NAME)!");
					return;
				} else if (allOffers.elementAt(i).getAid() == aid) {
					// System.out.println("ANGEBOT EXSISTIERT BEREITS, AID VORHANDEN!");
					log.write("ProviderServlet", "Error while creating new offer! -->Offer (AID) is already exisitng! PATH: "+path.toString());
					response.setContentType("text/error");
					response.getWriter().write("Angebot ist bereits vorhanden!");
					return;
				}
			}

			// Save new Offer in the DB and response
			Offer offer = new Offer(aid, ersteller, name, notiz, checked,
					stellen, stunden, beschreibung, startdatum, enddatum, lohn,
					institut, aenderungsdatum);
			log.write("ProviderServlet", "Creating new offer in progress...");
			OfferController.getInstance().createOffer(offer);
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
			return;
		}
		// Angebot zurueckziehen
		else if (path.equals("/js/deleteOffer")) {
			int aid;
			
			try {
				aid = Integer.parseInt(request.getParameter("aid"));

			} catch (NumberFormatException e){
				log.write("ProviderServlet","There was an error while PARSING int-value(AID) in: "+path.toString());
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen der AID!");
				return;
			}
			// System.out.println("DELETE OFFER by aid: "+aid);
			Offer offtodel = OfferController.getInstance().getOfferById(aid);
			log.write("ProviderServlet","Deleting offer in progress...");
			OfferController.getInstance().deleteOffer(offtodel);

			response.setContentType("text/url");
			response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
			return;

		}
		// Loads selected Offer into the Form elements
		else if (path.equals("/js/getOffer")) {

			// String test= request.getParameter("aid");
			// System.out.println("TEST: "+test);
			int aid;
			try {
				aid = Integer.parseInt(request.getParameter("aid"));

			} catch (NumberFormatException e) {
				log.write("ProviderServlet","There was an error while PARSING int-value(AID) in: "+path.toString());
				response.setContentType("text/error");
				response.getWriter().write(	"Fehler beim Parsen der AID!");
				return;
			}
			// System.out.println("LOAD OFFER by aid: "+aid);

			Offer offtoup = OfferController.getInstance().getOfferById(aid);
			// OfferController.getInstance().updateOffer(offtoup);

			response.setContentType("offer/json");
			response.getWriter().write(gson.toJson(offtoup, Offer.class));
			return;

		}
		// Saves changes from selected Offer and updates it in the db
		else if (path.equals("/js/updateOffer")) {

			// String test= request.getParameter("aid");
			// System.out.println("TEST: "+test);
			int aid;
			try {
				aid = Integer.parseInt(request.getParameter("aid"));

			} catch (NumberFormatException e) {
				log.write("ProviderServlet","There was an error while PARSING int-value(AID) in: "+path.toString());
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen der AID!");
				return;
			}
			// System.out.println("Update OFFER by aid: "+aid);

			Offer offUp = OfferController.getInstance().getOfferById(aid);

			offUp.setName(request.getParameter("titel"));
			offUp.setDescription(request.getParameter("beschreibung"));
			log.write("ProviderServlet","Updating offer in progress...");
			OfferController.getInstance().updateOffer(offUp);

			response.setContentType("text/url");
			response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
			return;

		}
		// Sets the boolean value of "ausgewaehlt" in the db table
		// "berwerbungen" to "true"
		else if (path.equals("/js/takeSelectedApplicant")) {

			String username = request.getParameter("usernameTakenApplicant");
			
			int aid;
			try {
				aid = Integer.parseInt(request.getParameter("aid"));

			} catch (NumberFormatException e) {
				log.write("ProviderServlet","There was an error while PARSING int-value(AID) in: "+path.toString());
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen der AID!");
				return;
			}
			System.out.println("Applicant:" + username + " for OfferID = " + aid
					+ " selected/taken");

			Vector<Application> appliVect = ApplicationController.getInstance()
					.getApplicationsByOffer(aid);
			
			Application applicationToChange;
			
			for (int i = 0; i < appliVect.size(); i++) {

				applicationToChange = appliVect.elementAt(i);

				 //System.out.println("Bewerbername: "+applicationToChange.getUsername()+" AID="+applicationToChange.getAid());

				if ((aid == applicationToChange.getAid()) && (username.equals(applicationToChange.getUsername()))) {
					System.out.println("Bewerbername: "+applicationToChange.getUsername()+" AID="+applicationToChange.getAid());
					if (applicationToChange.isChosen() == true) {
						log.write("ProviderServlet","ERROR: This applicant is already selected/taken! PATH: "+path.toString());
						response.setContentType("text/error");
						response.getWriter().write("Bewerber wurde schon selektiert!");
						return;
					} else {
						applicationToChange.setChosen(true);
						log.write("ProviderServlet","'Bewerber annehmen' in progress...");
						ApplicationController.getInstance().updateApplication(applicationToChange);
					}

				}

			}

			response.setContentType("text/url");
			response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
			return;

		}

	}
}