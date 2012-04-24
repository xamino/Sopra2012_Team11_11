/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
 * @author Patryk Boczon
 */
package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Enumeration;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import logger.Log;
import user.Clerk;
import user.Provider;

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
import database.institute.Institute;
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
	
	private int offerid;

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
		System.out.println(path);
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
		
		else if (path.equals("/js/editOneOffer")){
			System.out.println("pathequals editOneOffer");
			
			int aid = Integer.parseInt(request.getParameter("aid"));
			
			System.out.println(aid);
			
			response.setContentType("offers/json");
		}
		else if(path.equals("/js/approveOffer")){
			int aid = Integer.parseInt(request.getParameter("aid"));
			System.out.println("setting offer "+aid+" to true");
			Offer offertoapprove = OfferController.getInstance().getOfferById(aid);
			System.out.println("before: "+offertoapprove.isChecked());
			offertoapprove.setChecked(true);
			System.out.println("after: "+offertoapprove.isChecked());
			OfferController.getInstance().updateOffer(offertoapprove);
			
			//wir wollten doch einen String als date?
//			OfferController.getInstance().getOfferById(aid).setModificationdate(getDateTime());
		}
		else if(path.equals("/js/rejectOffer")){
			int aid = Integer.parseInt(request.getParameter("aid"));
			System.out.println("setting offer "+aid+" to false");
			
			Offer offertoreject = OfferController.getInstance().getOfferById(aid);
			System.out.println("before: "+offertoreject.isChecked());
			offertoreject.setChecked(false);
			System.out.println("after: "+offertoreject.isChecked());
			OfferController.getInstance().updateOffer(offertoreject);
			
			//wir wollten doch einen String als date?
//			OfferController.getInstance().getOfferById(aid).setModificationdate(getDateTime());
		}
		else if (path.equals("/js/documentsFromOffer")) {
			String aid = request.getParameter("aid");
			int aid1 = Integer.parseInt(aid);
			Vector<Offer> offersid = OfferController.getInstance().getAllOffers();
			//String offername;
			Vector<OfferDocument> documents = new Vector<OfferDocument>();
			System.out.println("hier?");
			for(int i=0; i<offersid.size(); i++){
				if(aid1 == offersid.elementAt(i).getAid()){
					documents = DocumentController.getInstance().getDocumentsByOffer(Integer.parseInt(aid));
				}}
					Vector<String> documentsname = new Vector<String>();
					
					for(int j=0; j<documents.size(); j++){
						documentsname.add(DocumentController.getInstance().getDocumentByUID(documents.elementAt(j).getDocumentid()).getName());
					}
					System.out.println(documents);
					System.out.println(documentsname);
					response.setContentType("documentsoffer/json");
					response.getWriter().write(gson.toJson(documentsname, documentsname.getClass()));
			
		}	


		// Creates an Vector for the table in applicationmanagement.jsp
		else if (path.equals("/js/showApplication")) {
			String username = clerk.getUserData().getUsername();
			Account clerka = AccountController.getInstance().getAccountByUsername(username);
			Vector<HilfsDatenClerk> daten = DatabaseController.getInstance().getChosenApplicationDataByInstitute(clerka.getInstitute());
			//System.out.println("Ergebnis: "+daten.size());
			response.setContentType("showapplication/json");
			response.getWriter().write(gson.toJson(daten, daten.getClass()));
			
		}
		// Creates an Vector for the table in editapplication.jsp 
		else if (path.equals("/js/applicationDocuments")) {
			String user = request.getParameter("User");
			String aid = request.getParameter("Aid");
			int aid1 = Integer.parseInt(aid);
			Account acc = AccountController.getInstance().getAccountByUsername(user); //Account vom ausgewählten User
			Offer off = OfferController.getInstance().getOfferById(aid1); // Offer des ausgewählten User
			Vector<AppDocument> docs = DocumentController.getInstance().getDocumentsByUserAndOffer(acc, off);
			Vector<Document> docs2 = new Vector<Document>();
			for(int i = 0; i < docs.size(); i++){
				docs2.add(DocumentController.getInstance().getDocumentByUID(docs.elementAt(i).getdID()));
			}
			//System.out.println("Ergebnis: "+docs2);
			response.setContentType("showthedocuments/json");
			response.getWriter().write(gson.toJson(docs2, docs2.getClass()));
			
		}
		//Funktion zum hinzufuegen eines Dokuments (aehnlich wie beim Admin).
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
						.write("Fehler bei Eingabe! Nur ganze Zahlen erlaubt fÃ¼r die UID.");
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
		//Funktion zum entfernen eines Dokuments (aehnlich wie beim Admin).
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
					String name = clerk.getUserData().getUsername();
					if(clerk.deleteOwnAccount()){
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
					if(clerk.editOwnAccount(name, email, pw)){
						log.write("ApplicantServlet", clerk.getUserData().getUsername() + " has modified his account.");
						response.setContentType("text/url");
						response.getWriter().write(Helper.D_CLERK_USERINDEX);
					}else{
						response.setContentType("text/error");
						response.getWriter().write("Fehler beim Ã¤ndern der Daten.");
					}
				}
				else 	// Do loadAccount:
					if (path.equals("/js/loadAccount")) {
						String realName = clerk.getUserData().getName();
						String email = clerk.getUserData().getEmail();
						String rep = clerk.getRepresentant();
						String JsonString = Helper.jsonAtor(new String[] { "realName",
								"email" ,"rep"}, new String[] { realName, email , rep});
						response.setContentType("application/json");
						response.getWriter().write(JsonString);
					}
		//TODO
				//Ich bekomme noch keine Daten vom Server (username,AID). --> Unchecked
				else if(path.equals("/js/doApplicationCompletion")){
					int AID = 0;
					String username;
					try {
						AID = Integer.parseInt(request.getParameter("aid"));
					} catch (NumberFormatException e) {
						log.write("ClerkServlet", "NumberFormatException while parsing URL!");
						response.setContentType("text/error");
						response.getWriter().write("Error while parsing String into int");
						return;
					}
					username = request.getParameter("username");
					//Prueft ob alle Dokumente abgegeben wurden.
					//Die einzige Bedingung die wir and den Vertragsabschluss-Button gestellt haben 
					//war das er nur dann erfolgreich ist wen alles vorhanden ist und nicht das er 
					//die fehlenden Dokumente mitschickt(Name des Dokuments) oder doch?
					if (clerk.checkAllDocFromApplicant(username, AID)) {
						response.setContentType("text/url");
					//Soll jetzt ab hier den Bewerber als "angenommen" markiert werden oder wird das dan endgueltig vom
					//Anbieter bestimmt? (Tabelle: Bewerbungen Zeile: ausgewaehlt)
					}
					else {
						response.setContentType("error/url");
						
					}
					response.getWriter().write(Helper.D_CLERK_EDITAPPLICATION);
				}
				else {
					log.write("ClerkServlet", "Unknown path <" + path + ">");
				}
		
		
		

	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
