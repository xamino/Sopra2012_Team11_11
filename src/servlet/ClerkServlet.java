/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
 * @author Patryk Boczon
 */
package servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import logger.Log;
import user.Clerk;

import com.google.gson.Gson;

import database.DatabaseController;
import database.HilfsDatenClerk;
import database.account.Account;
import database.account.AccountController;
import database.application.Application;
import database.application.ApplicationController;
import database.document.AppDocument;
import database.document.Document;
import database.document.DocumentController;
import database.document.OfferDocument;
import database.institute.InstituteController;
import database.offer.Offer;
import database.offer.OfferController;

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
	 * Variable zum speichern einer Instanz vom DocumentController;
	 */
	private DocumentController doccon;

	/**
	 * Variable zum speichern einer Instanz vom ApplicationController;
	 */
	private ApplicationController appcon;

	/**
	 * Variable zum speichern einer Instanz vom OfferController;
	 */
	private OfferController offcon;
	/**
	 * Variable zum speichern einer Instanz vom AccountController
	 */
	private AccountController acccon;

	/**
	 * Variable zum speichern einer Instanz vom InstituteController
	 */
	private InstituteController instcon;
	
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
		doccon = DocumentController.getInstance();
		appcon = ApplicationController.getInstance();
		acccon = AccountController.getInstance();
		offcon = OfferController.getInstance();
		instcon=InstituteController.getInstance();
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
		path = (path == null) ? "" : path;
		// log.write("ClerkServlet", "Received request: " + path);
		// Load the offers of the clerk:
		if (path.equals("/js/showMyOffers")) {
			// Load all correct offers:
			Vector<Offer> myoffers = clerk.getAllUncheckedOffers();
			response.setContentType("offers/json");
			response.getWriter().write(
					gson.toJson(myoffers, myoffers.getClass()));
		} else if (path.equals("/js/editOneOffer")) {
			int aid = Integer.parseInt(request.getParameter("aid"));
			Offer offertoedit = OfferController.getInstance().getOfferById(aid);
			response.setContentType("offers/json");
			response.getWriter().write(
					gson.toJson(offertoedit, offertoedit.getClass()));
			return;
		} else if (path.equals("/js/approveOffer")) {

			int aid = Integer.parseInt(request.getParameter("aid"));
			double hoursperweek = Double.parseDouble(request
					.getParameter("hoursperweek"));
			double wage = Double.parseDouble(request.getParameter("wage"));

			Offer offertoapprove = OfferController.getInstance().getOfferById(
					aid);
			offertoapprove.setChecked(true);
			offertoapprove.setWage(wage);
			offertoapprove.setHoursperweek(hoursperweek);

			OfferController.getInstance().updateOffer(offertoapprove);
			// wir wollten doch einen String als date?
			// Antwort von Tamino: ist es auch... aber irgendwie müssen wir das
			// Datum auch holen um es abspeichern zu können, bzw. irgendwo geht
			// da was schief.
			// OfferController.getInstance().getOfferById(aid).setModificationdate(getDateTime());

			response.setContentType("offers/json");
			response.getWriter().write(
					gson.toJson(offertoapprove, offertoapprove.getClass()));
			return;
		} else if (path.equals("/js/saveOffer")) {

			int aid = Integer.parseInt(request.getParameter("aid"));
			double hoursperweek = Double.parseDouble(request
					.getParameter("hoursperweek"));
			double wage = Double.parseDouble(request.getParameter("wage"));

			Offer offertosave = OfferController.getInstance().getOfferById(aid);
			offertosave.setWage(wage);
			offertosave.setHoursperweek(hoursperweek);

			OfferController.getInstance().updateOffer(offertosave);
			// wir wollten doch einen String als date?
			// Antwort von Tamino: ist es auch... aber irgendwie müssen wir das
			// Datum auch holen um es abspeichern zu können, bzw. irgendwo geht
			// da was schief.
			// OfferController.getInstance().getOfferById(aid).setModificationdate(getDateTime());

			response.setContentType("offers/json");
			response.getWriter().write(
					gson.toJson(offertosave, offertosave.getClass()));
			return;
		} else if (path.equals("/js/rejectOffer")) {
			int aid = Integer.parseInt(request.getParameter("aid"));

			Offer offertoreject = OfferController.getInstance().getOfferById(
					aid);
			offertoreject.setChecked(false);

			OfferController.getInstance().updateOffer(offertoreject);
			// wir wollten doch einen String als date? -> s.o.
			// OfferController.getInstance().getOfferById(aid).setModificationdate(getDateTime());

			response.setContentType("offers/json");
			response.getWriter().write(
					gson.toJson(offertoreject, offertoreject.getClass()));
			return;
		} else if (path.equals("/js/documentsFromOffer")) {
			String aid = request.getParameter("aid");
			int aid1 = Integer.parseInt(aid);
			Vector<Offer> offersid = OfferController.getInstance()
					.getAllOffers();
			// String offername;
			Vector<OfferDocument> offerdocuments = new Vector<OfferDocument>();
			for (int i = 0; i < offersid.size(); i++) {
				if (aid1 == offersid.elementAt(i).getAid()) {
					offerdocuments = DocumentController.getInstance()
							.getDocumentsByOffer(Integer.parseInt(aid));
				}
			}
			Vector<Document> documents = new Vector<Document>();
			for (int i = 0; i < offerdocuments.size(); i++) {
				documents.add(DocumentController.getInstance()
						.getDocumentByUID(
								offerdocuments.elementAt(i).getDocumentid()));
			}
			response.setContentType("documentsoffer/json");
			response.getWriter().write(
					gson.toJson(documents, documents.getClass()));

		}

		// Creates a Vector of Documents which can be added to an offer
		else if (path.endsWith("/js/documentsToAddToOffer")) {

			int aid = Integer.parseInt(request.getParameter("aid"));

			Vector<Document> docsToAdd = DocumentController.getInstance()
					.getDocumentsToAddToOffer(aid);

			response.setContentType("documentstoaddoffer/json");
			response.getWriter().write(
					gson.toJson(docsToAdd, docsToAdd.getClass()));
		}

		// Creates an Vector for the table in applicationmanagement.jsp
		else if (path.equals("/js/showApplication")) {
			String username = clerk.getUserData().getUsername();
			Account clerka = AccountController.getInstance()
					.getAccountByUsername(username);
			Vector<HilfsDatenClerk> daten = DatabaseController.getInstance()
					.getChosenApplicationDataByInstitute(clerka.getInstitute());
			if (daten == null || daten.isEmpty()) {
				response.setContentType("text/error");
				response.getWriter().write("Keine Einträge in der DB!");
				return;
			}
			// System.out.println("Ergebnis: "+daten.size());
			response.setContentType("showapplication/json");
			response.getWriter().write(gson.toJson(daten, daten.getClass()));

		}
		// Creates an Vector for the table in editapplication.jsp
		else if (path.equals("/js/applicationDocuments")) {
			String user = request.getParameter("User");
			// System.out.println("User:"+user);
			String aid = request.getParameter("AID");
			// System.out.println("Aid:"+aid);
			int aid1 = Integer.parseInt(aid);
			Account acc = AccountController.getInstance().getAccountByUsername(
					user); // Account vom ausgew�hlten User
			Offer off = OfferController.getInstance().getOfferById(aid1); // Offer
																			// des
																			// ausgew�hlten
																			// User
			Vector<AppDocument> docs = DocumentController.getInstance()
					.getDocumentsByUserAndOffer(acc, off);
			Vector<AppDocument> docs2 = new Vector<AppDocument>();
			for (int i = 0; i < docs.size(); i++) {
				docs2.add(DocumentController.getInstance().getAppDocumentByUID(
						docs.elementAt(i).getdID()));
				
			}
			// System.out.println("Ergebnis: "+docs2);
			response.setContentType("showthedocuments/json");
			response.getWriter().write(gson.toJson(docs2, docs2.getClass()));

		}
		// Creates an String for the table in editapplication.jsp
		else if (path.equals("/js/showApplicationTable2")) {
			String user = request.getParameter("User");
			// System.out.println("User:"+user);
			String aid = request.getParameter("AID");
			// System.out.println("Aid:"+aid);
			int aid1 = Integer.parseInt(aid);

			String richtigername = AccountController.getInstance()
					.getAccountByUsername(user).getName();
			String angebotsname = OfferController.getInstance()
					.getOfferById(aid1).getName();
			// = Name des bewebers, Angebotsname, Benutzername des Bewerbers,
			// AngebotsID
			String[] datanamen = { richtigername, angebotsname, user, aid };
			response.setContentType("showapplicationtable2/json");
			response.getWriter().write(
					gson.toJson(datanamen, datanamen.getClass()));
		}
		// Funktion zum hinzufuegen eines Dokuments (aehnlich wie beim Admin).
		else if (path.equals("/js/addDocument")) {
			String title = request.getParameter("title");
			String description = request.getParameter("description");
			int uid = -1;
			try {
				uid = Integer.parseInt(request.getParameter("uid"));
			} catch (NumberFormatException e) {
				log.write("ClerkServlet",
						"NumberFormatException while parsing URL!");
				response.setContentType("text/error");
				response.getWriter()
						.write("Fehler bei Eingabe! Nur ganze Zahlen erlaubt für die UID.");
				return;
			}
			if (title == null || title.isEmpty() || description == null
					|| description.isEmpty() || uid < 0) {
				log.write("ClerkServlet", "Error in parameters!");
				response.setContentType("text/error");
				response.getWriter().write(
						"Fehler bei Eingabe! Fehlende Eingaben.");
				return;
			}
			// all okay... continue:
			if (!clerk.addDoc(new Document(uid, title, description))) {
				response.setContentType("text/error");
				response.getWriter()
						.write("Fehler beim erstellen des Dokuments! Ist die UID eineindeutig?");
				return;
			}
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_CLERK_EDITAPPLICATION);
			return;
		}
		// Funktion zum entfernen eines Dokuments (aehnlich wie beim Admin).
		else if (path.equals("/js/deleteDocument")) {
			int uid = -1;
			try {
				uid = Integer.parseInt(request.getParameter("uid"));
			} catch (NumberFormatException e) {
				log.write("ClerkServlet",
						"NumberFormatException while parsing URL!");
				response.setContentType("text/error");
				response.getWriter().write("Fehlerhafte uid!");
				return;
			}
			Document doc = doccon.getDocumentByUID(uid);
			clerk.delDoc(doc);
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_CLERK_EDITAPPLICATION);
			return;
		}
		// Delete own account:
		else if (path.equals("/js/deleteAccount")) {
			String name = request.getParameter("name");

			if (clerk.deleteOwnAccount()) {
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
			String rep = request.getParameter("rep");
			if (pw.equals(""))
				pw = null; // falls leeres pw-> null damit die editOwnAccount
							// funktion das pw nicht auf "" setzt!
			if (rep == null)
				rep = "";
			if (clerk.editOwnAccount(name, email, pw, rep)) {
				log.write("ApplicantServlet", clerk.getUserData().getUsername()
						+ " has modified his account.");
				response.setContentType("text/url");
				response.getWriter().write(Helper.D_CLERK_USERINDEX);
			} else {
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim ändern der Daten.");
			}
		}
		// Do loadAccount:
		else if (path.equals("/js/loadAccount")) {
			String realName = clerk.getUserData().getName();
			String email = clerk.getUserData().getEmail();
			String rep = clerk.getUserData().getRepresentant();
			String JsonString = Helper.jsonAtor(new String[] { "realName",
					"email", "rep" }, new String[] { realName, email, rep });
			response.setContentType("application/json");
			response.getWriter().write(JsonString);
		}
		// TODO
		// Ich bekomme noch keine Daten vom Server (username,AID). --> Unchecked
		else if (path.equals("/js/doApplicationCompletion")) {
			int AID = 0;
			String username;
			try {
				AID = Integer.parseInt(request.getParameter("aid"));
			} catch (NumberFormatException e) {
				log.write("ClerkServlet",
						"NumberFormatException while parsing URL!");
				response.setContentType("text/error");
				response.getWriter().write(
						"Error while parsing String into int");
				return;
			}
			username = request.getParameter("username");
			// Prueft ob alle Dokumente abgegeben wurden.
			// Die einzige Bedingung die wir and den Vertragsabschluss-Button
			// gestellt haben
			// war das er nur dann erfolgreich ist wen alles vorhanden ist und
			// nicht das er
			// die fehlenden Dokumente mitschickt(Name des Dokuments) oder doch?
			if (clerk.checkAllDocFromApplicant(username, AID)) {
				response.setContentType("text/url");
				// Soll jetzt ab hier den Bewerber als "angenommen" markiert
				// werden oder wird das dan endgueltig vom
				// Anbieter bestimmt? (Tabelle: Bewerbungen Zeile: ausgewaehlt)
			} else {
				response.setContentType("error/url");
				response.getWriter().write(Helper.D_CLERK_EDITAPPLICATION);
			}
		}
		// Funktion zum entfernen eines OfferDocuments des gewaehlten Offers
		else if (path.equals("/js/deleteOfferDocument")) {

			int uid = Integer.parseInt(request.getParameter("uid"));
			int aid = Integer.parseInt(request.getParameter("aid"));
			DocumentController.getInstance().deleteOfferDocument(
					new OfferDocument(aid, uid));
			return;
		}

		// Funktion zum hinzufuegen eines OfferDocuments des gewaehlten Offers
		else if (path.equals("/js/addOfferDocument")) {
			int uid;
			int aid;
			try {
				uid = Integer.parseInt(request.getParameter("uid"));
				aid = Integer.parseInt(request.getParameter("aid"));
			} catch (NumberFormatException e) {
				log.write("ClerkServlet",
						"Error add offer document! UID or AID invalid!");
				return;
			}
			DocumentController.getInstance().createOfferDocument(
					new OfferDocument(aid, uid));
			return;
		}

		// TO DO!
		// Ich bekomme noch keine Daten vom Server (username,AID). --> Unchecked
		else if (path.equals("/js/doApplicationCompletion")) {
			int AID = 0;
			String username;
			try {
				AID = Integer.parseInt(request.getParameter("aid"));
			} catch (NumberFormatException e) {
				log.write("ClerkServlet",
						"NumberFormatException while parsing URL!");
			}
			username = request.getParameter("username");
			// Prueft ob alle Dokumente abgegeben wurden.
			// Die einzige Bedingung die wir and den Vertragsabschluss-Button
			// gestellt haben
			// war das er nur dann erfolgreich ist wen alles vorhanden ist und
			// nicht das er
			// die fehlenden Dokumente mitschickt(Name des Dokuments) oder doch?
			if (clerk.checkAllDocFromApplicant(username, AID)) {
				response.setContentType("test/url");
				// Soll jetzt ab hier den Bewerber als "angenommen" markiert
				// werden oder wird das dan endgueltig vom
				// Anbieter bestimmt? (Tabelle: Bewerbungen Zeile: ausgewahlt)
			} else {
				response.setContentType("error/url");
				response.getWriter().write(Helper.D_CLERK_EDITAPPLICATION);
			}

		} else if (path.equals("/js/loadInfo")) {
			Vector<Offer> off = new Vector<Offer>();
			Vector<Integer>institutes= instcon.getAllRepresentingInstitutes(clerk.getUserData().getUsername());
			institutes.add(0);
			for(Integer i:institutes)off.addAll(offcon.getUncheckedOffersByInstitute(i));
			int unchecked = off.size();
			int apps = 0;
			for (Offer o : off) {
				if (!o.isFinished()) {
					Vector<Application> aps = appcon.getApplicationsByOffer(o
							.getAid());
					for (Application a : aps) {
						if (a.isChosen() && !a.isFinished())
							apps++;
					}
				}
			}
			String gsonny = Helper.jsonAtor(new String[] { "offers", "apps" },
					new Object[] { unchecked, apps });
			response.setContentType("application/json");
			response.getWriter().write(gsonny);
			return;
		} else {
			log.write("ClerkServlet", "Unknown path <" + path + ">");
		}
	}

	protected void doGet(HttpServletRequest request,
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
		if (path.equals("/js/doExcelExport")) {
			File file = null;
			try {
				file = clerk.doExport();
			} catch (RowsExceededException e) {
				response.setContentType("text/error");
				response.getWriter().write("RowsExceededExcetion!");
			} catch (WriteException e) {
				response.setContentType("text/error");
				response.getWriter().write("Error while writing File");
			}
			FileInputStream fileToDownload = new FileInputStream(file);
			ServletOutputStream output = response.getOutputStream();
			response.setContentType("application/msexcel");
			response.setHeader("Content-Disposition",
					"attachment; filename=excelExport.xls");
			response.setContentLength(fileToDownload.available());
			int c;
			while ((c = fileToDownload.read()) != -1) {
				output.write(c);
			}
			output.flush();
			output.close();
			fileToDownload.close();
		}
	}

	private String getDateTime() {
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();
		return dateFormat.format(date);
	}
}