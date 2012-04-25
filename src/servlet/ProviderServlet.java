/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
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
	 * Log instanz f√ºr Serverausgaben
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
			response.getWriter().write("Fehler beim √§ndern der Daten.");
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
	// Creates an Vector with all applicants from the selected Offer
	else if (path.equals("/js/applicantChoice")) {
		int aid = Integer.parseInt(request.getParameter("aid"));
		//System.out.println(aid);
		Vector<Application> app = ApplicationController.getInstance().getApplicationsByOffer(aid);
		Vector<Account> acc = new Vector<Account>();
		for(int i=0; i<app.size(); i++){
			acc.add(AccountController.getInstance().getAccountByUsername(app.elementAt(i).getUsername()));
		}
		//System.out.println("Ergebnis: "+docs2);
		response.setContentType("showtheapplicants/json");
		response.getWriter().write(gson.toJson(acc, acc.getClass()));
				
	}
	
	
	//Creating a new Offer
		else if (path.equals("/js/addOffer")){
			//System.out.println("PROVIDER_SERVLET, PATH: ADD OFFER");
			
			Provider provi = Helper.checkAuthenticity(request.getSession(),Provider.class);
			int aid = 789;
			
			/* TODO Woher kommt die aid ?
			try {
				aid = Integer.parseInt(request.getParameter("????????"));
			} catch (NumberFormatException e) {
				//log.write("AdminServlet","NumberFormatException while parsing URL!");
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen! AutragsID checken!");
				return;
			}*/
			
			String ersteller = provi.getUserData().getUsername();
			String name = request.getParameter("titel");
			String notiz = request.getParameter("notiz");
			boolean checked = false;
			int stellen;
			try{ 
			stellen = Integer.parseInt(request.getParameter("stellen"));
			} catch(NumberFormatException e){
				System.out.println("ERROR WHILE PARSING DOUBLE IN ProviderServlet");
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen! Kein/ung¸ltiger Wert eingegeben [INT Wert von 'Stellen' pr¸fen]");
				return;
			}
			
			double stunden;
			try{
				
			stunden = Double.parseDouble(request.getParameter("std")); // in der DB Std/Woche, in der HTML Std/Monat
			}catch (NumberFormatException e){
				System.out.println("ERROR WHILE PARSING DOUBLE IN ProviderServlet");
				response.setContentType("text/error");
				response.getWriter().write("Fehler beim Parsen! Kein/ung¸ltiger Wert eingegeben [DOUBLE Wert von 'Std' pr¸fen]");
				return;
			}
			
			String beschreibung = request.getParameter("beschreibung");
			
			Date startdatum,enddatum,aenderungsdatum ; //TODO
			startdatum = new  Date();
			enddatum = new  Date();
			aenderungsdatum = new  Date();
			double lohn= 10.7;//TODO
			
			int institut = 0; //TODO
			
			/* in log schreiben noetig hier?
			if (titel == null || titel.isEmpty() || std == null || std.isEmpty()
					|| stellen == null || stellen.isEmpty() || beschreibung.isEmpty()|| beschreibung==null
					|| notiz == null || notiz.isEmpty()
					) {
				log.write("AdminServlet", "Error in parameters!");
				response.setContentType("text/error");
				response.getWriter().write("Werte illegal!");
				return;
			}*/
			
			// If already exists:
			Vector<Offer> allOffers = OfferController.getInstance().getAllOffers();
			for (int i=0;i<allOffers.size();i++){
				if(allOffers.elementAt(i).getName().equals(name)){
					//System.out.println("ANGEBOT EXSISTIERT BEREITS, NAME VORHANDEN!");
					response.setContentType("text/error");
					response.getWriter().write("Angebot ist bereits vorhanden (NAME)!");
					return;
				}
				else if(allOffers.elementAt(i).getAid()==aid){
					//System.out.println("ANGEBOT EXSISTIERT BEREITS, AID VORHANDEN!");
					response.setContentType("text/error");
					response.getWriter().write("Angebot ist bereits vorhanden (AID ist festgesetzt im Code)!");
					return;
				}
			}
			
			/*
			 * TODO
			 * log.write("ProviderServlet","Error creating offer. Offer already exists!");
				response.setContentType("text/error");
				response.getWriter().write("Jobangebot ist bereits vorhanden!");
				
				int aid, String author, String name, String note,
			boolean checked, int slots, double hoursperweek,
			String description, Date startdate, Date enddate, double wage,
			int institute, Date modificationdate
			 */
			
			//Save new Offer in the DB and response		
			Offer offer = new Offer(aid,ersteller,name,notiz,checked,stellen,stunden,beschreibung,startdatum,enddatum,lohn,institut,aenderungsdatum);
			OfferController.getInstance().createOffer(offer);
			response.setContentType("text/url");
			response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
			return;
			}	
		//Angebot zurueckziehen	
		else if(path.equals("/js/deleteOffer")){
			int aid = Integer.parseInt(request.getParameter("aid"));
			System.out.println("DELETE OFFER by aid: "+aid);
			Offer offtodel = OfferController.getInstance().getOfferById(aid);
			OfferController.getInstance().deleteOffer(offtodel);

			response.setContentType("text/url");
			response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
			return;
			
		}
		
		else if (path.equals("/js/getOffer")) {
			
			int aid = Integer.parseInt(request.getParameter("aid"));
			System.out.println("Update OFFER by aid: "+aid);
			
			Offer offtoup = OfferController.getInstance().getOfferById(aid);
			OfferController.getInstance().updateOffer(offtoup);

			
			//else response error
			response.setContentType("text/error");
			response.getWriter().write("Angebot existiert nicht in der DB!");
			return;
		}
	
	
	
}
}