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
import user.Applicant;

import com.google.gson.Gson;

import database.application.ApplicationController;
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
	@SuppressWarnings("null")
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
			String realName = applicant.getUserData().getName();
			String email = applicant.getUserData().getEmail();
			String JsonString = Helper.jsonAtor(new String[] { "realName",
					"email" }, new String[] { realName, email });
			response.setContentType("application/json");
			response.getWriter().write(JsonString);
		}
		// Delete an application:
		else if (path.equals("/js/deleteApplication")) {
			int aid = Integer.parseInt(request.getParameter("AID"));
			int uid = Integer.parseInt(request.getParameter("UID"));

			if (!applicant.deleteApplication(uid, aid)) {
				response.setContentType("text/text");
				response.getWriter().write(
						"Application " + aid + "/" + uid + " deleted");
				return;
			}
			response.setContentType("text/error");
			response.getWriter().write("Failed to delete application!");

		}
		// Load my offers:
		else if (path.equals("/js/loadMyOffers")) {
			// Offer vom User geholt
			Vector<Offer> myoffers = OfferController.getInstance()
					.getOffersByApplicant(
							ApplicationController.getInstance()
									.getApplicationsByApplicant(
											applicant.getUserData()
													.getUsername()));
			response.setContentType("myapplication/json");
			response.getWriter().write(
					gson.toJson(myoffers, myoffers.getClass()));
		}
		// Load offers:
		else if (path.equals("/js/loadOffers")) {
			Vector<Offer> offers = OfferController.getInstance()
					.getCheckedOffers(); // nur gepr�fte Angebote
			// bereits beworbene Stellen entfernen
			// Offer vom User geholt
			Vector<Offer> myoffers1 = OfferController.getInstance()
					.getOffersByApplicant(
							ApplicationController.getInstance()
									.getApplicationsByApplicant(
											applicant.getUserData()
													.getUsername()));
			boolean entfernen;
			for (int i = 0; i < offers.size(); i++) {
				entfernen = false;
				for (int j = 0; j < myoffers1.size(); j++) {
					if (offers.elementAt(i).getAid() == myoffers1.elementAt(j)
							.getAid()) {
						entfernen = true;
					}
					if (entfernen) {
						offers.remove(i);
					}
				}
			}
			response.setContentType("application/json");
			response.getWriter().write(gson.toJson(offers, offers.getClass()));
		}

		// Load my information about one application:
		else if (path.equals("/js/selectApplication")) {
			String aid = request.getParameter("id");
			int aid1 = Integer.parseInt(request.getParameter("id"));
			Vector<Offer> offersid = OfferController.getInstance()
					.getAllOffers();
			String offername;
			// Vector<OfferDocument> documents;
			for (int i = 0; i < offersid.size(); i++) {
				if (aid1 == offersid.elementAt(i).getAid()) {
					offername = offersid.elementAt(i).getName();
					// documents =
					// DocumentController.getInstance().getDocumentsByOffer(Integer.parseInt(aid));
					// System.out.println(documents);
					response.setContentType("offer/json");
					response.getWriter().write(
							gson.toJson(offername, offername.getClass()));
					return;
				}
			}
		}
		// Load my information about one application(documents):
		else if (path.equals("/js/selectDocuments")) {
			String aid = request.getParameter("id");
			int aid1 = Integer.parseInt(request.getParameter("id"));
			Vector<Offer> offersid = OfferController.getInstance()
					.getAllOffers();
			// String offername;
			Vector<OfferDocument> documents = null;
			// System.out.println("hier?");
			for (int i = 0; i < offersid.size(); i++) {
				if (aid1 == offersid.elementAt(i).getAid()) {
					documents = DocumentController.getInstance()
							.getDocumentsByOffer(Integer.parseInt(aid));
				}
			}
			// Create JSON version of custom data:
			String documentsObject = "[";
			for (int j = 0; j < documents.size(); j++) {
				int UID = documents.elementAt(j).getDocumentid();
				String dataObject = "{name:\"";
				// Write name to dataObject:
				dataObject += DocumentController.getInstance()
						.getDocumentByUID(UID).getName();
				dataObject += "\",isChecked:";
				// Write if it has been checked:
				dataObject += (DocumentController.getInstance()
						.getAppDocumentByUID(UID).getPresent()) ? 1 : 0;
				dataObject += "}";
				documentsObject += dataObject
						+ ((j == documents.size() - 1) ? "" : ",");
			}
			documentsObject += "]";
			response.setContentType("application/json");
			response.getWriter().write(documentsObject);
		}
		// Delete own account:
		else if (path.equals("/js/deleteAccount")) {
			String name = request.getParameter("username");
			if (applicant.deleteOwnAccount()) {
				log.write("ApplicantServlet", name
						+ " has deleted his account.");
				// Simply now for debugging:
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_INDEX);
			} else {
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
			if (applicant.editOwnAccount(name, email, pw, null)) {
				log.write("ApplicantServlet", applicant.getUserData()
						.getUsername() + " has modified his account.");
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_APPLICANT_USERINDEX);
			} else {
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim ändern der Daten.");
			}
		} else {
			log.write("ApplicantServlet", "Unknown path <" + path + ">");
		}
	}
}