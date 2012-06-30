/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
 * @author Oemer Sahin
 */
package servlet;

import static servlet.Helper.validate;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logger.Log;
import user.Provider;

import com.google.gson.Gson;

import database.account.Account;
import database.account.AccountController;
import database.application.Application;
import database.application.ApplicationController;
import database.offer.Offer;
import database.offer.OfferController;

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
	 * OfferController instanz
	 */
	private OfferController offcon;

	/**
	 * Konstruktor.
	 */
	public ProviderServlet() {
		super();
		gson = new Gson();
		offcon = OfferController.getInstance();
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
					.getOffersByProvider(
							AccountController.getInstance()
									.getAccountByUsername(
											provider.getUserData()
													.getUsername())); // Offer
																		// vom
																		// Provider
																		// geholt
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
				log.write("ApplicantServlet",
						"There was an error while deleting account with username:"
								+ username);
				response.setContentType("text/error");
				response.getWriter().write("Error while deleting account!");
			}
		}
		// change own account data
		else if (path.equals("/js/changeAccount")) {
			String name = request.getParameter("name");
			String email = request.getParameter("mail");
			String pw = request.getParameter("pw");
			String rep = request.getParameter("rep");
			if (pw.equals(""))
				pw = null; // falls leeres pw-> null damit die editOwnAccount
							// funktion das pw nicht auf "" setzt!
			if (rep == null)
				rep = "";
			if (provider.editOwnAccount(name, email, pw, rep)) {
				log.write("ApplicantServlet", provider.getUserData()
						.getUsername() + " has modified his account.");
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
			} else {
				log.write("ApplicantServlet",
						"There was an error while modifying account with username:"
								+ provider.getUserData().getUsername());
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim ändern der Daten.");
			}
		} else // Do loadAccount:
		if (path.equals("/js/loadAccount")) {
			String realName = provider.getUserData().getName();
			String email = provider.getUserData().getEmail();
			String rep = AccountController.getInstance()
					.getAccountByUsername(provider.getUserData().getUsername())
					.getRepresentative();
			String JsonString = Helper.jsonAtor(new String[] { "realName",
					"email", "rep" }, new String[] { realName, email, rep });
			response.setContentType("application/json");
			response.getWriter().write(JsonString);
		}
		// loads potential representatives for this account
		else if (path.equals("/js/loadRepresentatives")) {
			String username = provider.getUserData().getUsername();
			Vector<String> representatives = AccountController.getInstance()
					.getPotentialRepresentatives(username);
			response.setContentType("application/json");
			response.getWriter().write(
					gson.toJson(representatives, representatives.getClass()));
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
			log.write("ProviderServlet",
					"Creating vector with all applicants from selected offer in progress...");
			response.setContentType("showtheapplicants/json");
			response.getWriter().write(gson.toJson(acc, acc.getClass()));

		}

		// Creating a new Offer
		else if (path.equals("/js/addOffer")) {
			// Generating AID
			int aid = offcon.getNewOffID("Angebote");
			if (aid < 0) {
				log.write(
						"ProviderServlet",
						"The received AID from generator was invalid after several attempts. Loop has been interrupted!");
				response.setContentType("text/error");
				response.getWriter().write(
						"Bei der AID-Generierung ist ein Problem aufgetreten.");
				return;
			}

			// Getting the data from delivered connection content to save it as
			// a new offer-object in the db.
			String ersteller = provider.getUserData().getUsername();
			String name = request.getParameter("titel");
			String notiz = request.getParameter("notiz");
			String beschreibung = request.getParameter("beschreibung");
			String startDate = request.getParameter("startDate");
			String endDate = request.getParameter("endDate");
			// wird vom clerk gesetzt defaultwert ist 0.0
			double lohn = 0.0;
			boolean checked = false;
			int stellen;
			try {
				stellen = Integer.parseInt(request.getParameter("stellen"));
			} catch (NumberFormatException e) {
				// System.out.println("ERROR WHILE PARSING DOUBLE IN ProviderServlet");
				log.write("ProviderServlet",
						"There was an error while PARSING double-value(stellen) in: "
								+ path.toString());
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
				log.write("ProviderServlet",
						"There was an error while PARSING double-value(std) in: "
								+ path.toString());
				response.setContentType("text/error");
				response.getWriter()
						.write("Fehler beim Parsen! Kein/ungueltiger Wert eingegeben [DOUBLE Wert von 'Std' pruefen]");
				return;
			}
			// Set modify date:
			java.util.Date aenderungsdatum_1 = new java.util.Date();
			java.sql.Date aenderungsdatum = new java.sql.Date(
					aenderungsdatum_1.getTime());
			// Load institute:
			int institut = AccountController.getInstance()
					.getAccountByUsername(ersteller).getInstitute();
			// in log schreiben
			if (!validate(name) || stunden == 0 || stellen == 0
					|| !validate(beschreibung) || !validate(notiz)
					|| !validate(startDate) || !validate(endDate)
					|| aenderungsdatum == null || institut < 0) {
				log.write("ProviderServlet", "Error in parameters!");
				response.setContentType("text/error");
				response.getWriter().write("Werte illegal!");
				return;
			}
			// If already exists:
			// TODO: Can this really happen? Is this required?
			Vector<Offer> allOffers = OfferController.getInstance()
					.getAllOffers();
			for (int i = 0; i < allOffers.size(); i++) {
				if (allOffers.elementAt(i).getName().equals(name)) {
					log.write("ProviderServlet",
							"Error while creating new offer! -->Offer (name) already exists!");
					response.setContentType("text/error");
					response.getWriter().write(
							"Angebot ist bereits vorhanden (NAME)!");
					return;
				} else if (allOffers.elementAt(i).getAid() == aid) {
					log.write("ProviderServlet",
							"Error while creating new offer! -->Offer (AID) already exists!");
					response.setContentType("text/error");
					response.getWriter()
							.write("Angebot ist bereits vorhanden!");
					return;
				}
			}

			// Save new Offer in the DB and response
			Offer offer = new Offer(aid, ersteller, name, notiz, checked,
					stellen, stunden, beschreibung, startDate, endDate, lohn,
					institut, aenderungsdatum, false);
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

			} catch (NumberFormatException e) {
				log.write("ProviderServlet",
						"There was an error while PARSING int-value(AID) in: "
								+ path.toString());
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen der AID!");
				return;
			}
			// System.out.println("DELETE OFFER by aid: "+aid);
			Offer offtodel = OfferController.getInstance().getOfferById(aid);
			log.write("ProviderServlet", "Deleting offer in progress...");
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
				log.write("ProviderServlet",
						"There was an error while PARSING int-value(AID) in: "
								+ path.toString());
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen der AID!");
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
				log.write("ProviderServlet",
						"There was an error while PARSING int-value(AID) in: "
								+ path.toString());
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen der AID!");
				return;
			}
			// System.out.println("Update OFFER by aid: "+aid);

			Offer offUp = OfferController.getInstance().getOfferById(aid);

			// set modificationdate to current date
			java.util.Date aenderungsdatum_2 = new java.util.Date();
			java.sql.Date aenderungsdatum_toUp = new java.sql.Date(
					aenderungsdatum_2.getTime());

			offUp.setName(request.getParameter("titel"));
			offUp.setDescription(request.getParameter("beschreibung"));
			// sets modificationdate and updates it
			offUp.setModificationdate(aenderungsdatum_toUp);
			log.write("ProviderServlet", "Updating offer in progress...");
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
				log.write("ProviderServlet",
						"There was an error while PARSING int-value(AID) in: "
								+ path.toString());
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen der AID!");
				return;
			}

			// System.out.println("Applicant:" + username + " for OfferID = " +
			// aid + " selected/taken");

			Vector<Application> appliVect = ApplicationController.getInstance()
					.getApplicationsByOffer(aid);

			Application applicationToChange;

			for (int i = 0; i < appliVect.size(); i++) {

				applicationToChange = appliVect.elementAt(i);

				// System.out.println("Bewerbername: "+applicationToChange.getUsername()+" AID="+applicationToChange.getAid());

				if ((aid == applicationToChange.getAid())
						&& (username.equals(applicationToChange.getUsername()))) {
					System.out.println("Bewerbername: "
							+ applicationToChange.getUsername() + " AID="
							+ applicationToChange.getAid());
					if (applicationToChange.isChosen() == true) {
						log.write("ProviderServlet",
								"ERROR: This applicant is already selected/taken! PATH: "
										+ path.toString());
						response.setContentType("text/error");
						response.getWriter().write(
								"Bewerber wurde schon selektiert!");
						return;
					} else {
						applicationToChange.setChosen(true);

						Offer offertoSetSlots = OfferController.getInstance()
								.getOfferById(aid);
						int freeSlots = offertoSetSlots.getSlots();

						log.write("ProviderServlet",
								" Setting free slots for offer in progress...");

						// No free slots
						if (OfferController.getInstance().getFreeSlotsOfOffer(
								aid) < 1) {
							log.write("ProviderServlet",
									"No free slots available for: "
											+ offertoSetSlots.getName());
							response.setContentType("text/error");
							response.getWriter().write(
									"Keine freien PLaetze mehr!");
							return;
						}
						// reduce freeSlots and update it
						else {
							offertoSetSlots.setSlots(freeSlots - 1);
							OfferController.getInstance().updateOffer(
									offertoSetSlots);
						}
						log.write("ProviderServlet",
								"'Bewerber annehmen' in progress...");
						ApplicationController.getInstance().updateApplication(
								applicationToChange);
					}
				}
			}
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
			return;
		}
		// Path for reading standard values from db for creating new offers.
		else if (path.equals("/js/getDefValues")) {
			String obj = provider.readDefValues();
			if (obj == null || obj.isEmpty()) {
				response.setContentType("text/error");
				response.getWriter()
						.write("Fehler in der Datenbank!\nWerte konnten nicht ausgelesen werden.");
				return;
			}
			response.setContentType("application/json");
			response.getWriter().write(obj);
			return;
		} else {
			log.write("ProviderServlet", "Unknown path <" + path + ">");
		}
	}
}