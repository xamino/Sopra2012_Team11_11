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

import database.account.Account;
import database.account.AccountController;
import database.application.Application;
import database.application.ApplicationController;
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
		/* noch nicht funktionsfähig */
		else if (path.equals("/js/showApplication")) {
			Clerk clerk2 = Helper.checkAuthenticity(request.getSession(),
					Clerk.class);
			String username = clerk2.getUserData().getUsername();
			Account clerka = AccountController.getInstance().getAccountByUsername(username);
			Vector<Account> provaccounts = AccountController.getInstance().getProviderAccountsByInstitute(clerka.getInstitute());
			
			Vector<Offer> alloffers = new Vector<Offer>(); //alle offers aller provider in provaccounts
			Vector<Offer> currentoffers = new Vector<Offer>(); //alle offers eines providers in provaccounts
			for(int i = 0; i < provaccounts.size(); i++){
				currentoffers = OfferController.getInstance().getOffersByProvider(new Provider(provaccounts.elementAt(i).getUsername(), null, null, null));
				for(int k = 0; k < currentoffers.size(); k++){
					alloffers.add(currentoffers.elementAt(k));
				}
			}
			System.out.println(alloffers);
			
			Vector<Application> allapplications = new Vector<Application>();
			Vector<Application> currentapplications = new Vector<Application>();
			
			for(int l = 0; l < alloffers.size(); l++){
				currentapplications = ApplicationController.getInstance().getApplicationsByOffer(alloffers.elementAt(l).getAid());
				for(int q = 0; q < currentapplications.size(); q++){
					allapplications.add(currentapplications.elementAt(q));
				}
			}
			
			response.setContentType("showapplication/json");
			response.getWriter().write(gson.toJson(alloffers, alloffers.getClass()));
			
		}
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
		else if (path.equals("/js/deleteDocument")) {
			int uid = -1;
			try {
				uid = Integer.parseInt(request.getParameter("uid"));
			} catch (NumberFormatException e) {
				log.write("AdminServlet",
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
		

	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
