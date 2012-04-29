/**
 * @author Anatoli Brill
 */

package user;

import javax.servlet.http.HttpSession;

import java.io.File;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Vector;

import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import logger.Log;
import database.document.Document;
import database.account.Account;
import database.account.AccountController;
import database.application.Application;
import database.document.AppDocument;
import database.document.OfferDocument;
import database.offer.Offer;
import file.ExcelExport;

/**
 * Verwaltet alle Aufgaben und Daten eines Verwalters.
 */
public class Clerk extends User {
	/**
	 * Stellvertreter
	 */
	private String representant;

	/**
	 * Konstruktor. Erstellte Objekte werden automatisch in der LoggedInUsers
	 * klasse aufgenommen.
	 * 
	 * @param username
	 *            Benutzername im System
	 * @param email
	 *            E-Mail Adresse
	 * @param name
	 *            Realer Name
	 * @param session
	 *            Session des Benutzers
	 */
	public Clerk(String username, String email, String name, HttpSession session) {
		super(username, email, name, session);
		userManagement.LoggedInUsers.addUser(this);
	}

	/**
	 * Editiert den eigenen Account. Der Benutzername ist dabei nicht aenderbar
	 * und identifiziert den zu aendernden Account in der Datenbank
	 * 
	 * @param acc
	 *            geaenderter Account
	 */
	public boolean editAccount(Account acc) {
		
		if (!acccon.updateAccount(acc)) {
			log.write("Clerk", "Error modifying account!");
			return false;
		}
		log.write("Clerk", "<" + getUserData().getUsername()
				+ "> modified account of <" + acc.getUsername() + ">.");
		return true;
	}
	
	/**
	 * Methode zum Löschen seines Accounts
	 * @return	Beim erfolgreichen Entfernen wird ein TRUE zurückgegeben.
	 * 			Falls irgendwo ein Fehler aufgetretten ist wird ein FALSE zurückgegeben.
	 */
	public boolean deleteOwnAccount(){
		invalidate();
		return acccon.deleteClerkAccount(this);
	}

	/**
	 * Methode zum bearbeiten von Bewerbungen.
	 */
	public void editApplication() {

	}

	/**
	 * Methode zum ablehnen eines Angebots.
	 * @throws SQLException 
	 */
	public void rejectOffer(int offerID) throws SQLException {
		Offer off = offcon.getOfferById(offerID);
		offcon.deleteOffer(off);
		//TODO Wen ein Angebot abgelehnt wird muss der Anbieter informiert werden.

	}

	/**
	 * Methode zum aktualiesieren eines Angebots.
	 */
	public void updateOffer(Offer off) {
		offcon.updateOffer(off);

	}

	/**
	 * Methode zum hinzufuegen von Bewerber-Dokumenten. Dabei kann jedem
	 * Bewerber einzeln Dokumente hinzugefuegt werden.
	 * @param username
	 * 			Benutzername wird zur eindeutigen Zuordnung des Dokuments benoetigt.
	 * @param aID
	 * 			ID des Bewerbers
	 */
	public void addAppDoc(String username, int aID, int uID, boolean present) {
		AppDocument doc = new AppDocument(username, aID, uID, present);
		doccon.createAppDocument(doc);

	}

	/**
	 * Methode zum hinzufuegen von Dokumenten.
	 * @param UID
	 * 			ID der Unterlage
	 * @param name
	 * 			Name der Unterlage
	 * @param description
	 * 			Beschreibung zur Unterlage
	 */
	public boolean addDoc(Document doc) {
		if (!doccon.createDocument(doc)) {
			log.write("Clerk", "Error adding a document!");
			return false;
		} else {
			log.write("Clerk", "<" + getUserData().getUsername()
					+ "> added document <" + doc.getName() + ">.");
			return true;
		}
	} 
	// Nur der Admin kann Unterlagen erstellen

	/**
	 * Methode zum entfernen von Dokumenten.
	 */
	public void delDoc(Document doc) {
		
		doccon.deleteDocument(doc);
	}
//Siehe oben

//	/**
//	 * Methode zum annehmen eines Bewerbers.
//	 * @param AID
//	 * 			ID der Bewerbung
//	 * @throws SQLException 
//	 */
//	public void acceptApplication(int AID) throws SQLException {
//		Application app = appcon.getApplicationById(AID);
//		app.setChosen(true);
//		appcon.updateApplication(app);
//	}
// Der Provider nimmt bewerbungen an

	/**
	 * Methode zum entfernen von Bewerber-Dokumenten. Dabei kann jedem Bewerber
	 * einzeln Dokumente entfernt werden.
	 * @return TRUE falls das Löschen erfolgreich war. Ansonten FALSE
	 */
	public boolean deleteAppDoc(AppDocument doc) {
		return doccon.deleteAppDocument(doc);
	}

	/**
	 * Methode zum beenden des Berwerbungsvorgangs.
	 * @param AID
	 * 			ID der Bewerbung
	 * @throws SQLException 
	 */
	public void finishApplication(int AID) throws SQLException {
		Application app = appcon.getApplicationById(AID);
		app.setFinished(true);
		appcon.updateApplication(app);
	}

	/**
	 * Export fuer die Excel-File
	 * 
	 * @return Die URL zu den Download des Excel-Files.
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */

	public File doExport() throws IOException, RowsExceededException, WriteException {
		return ExcelExport.export(this.getUserData());

	}
/**
 * Gibt den Stellvertreter zurueck
 * @return Stellvertreter
 */
	public String getRepresentant() {
		return representant;
	}
/**
 * Setzt den Stellvertreter
 * @param representant Stellvertreter
 */
	public void setRepresentant(String representant) {
		this.representant = representant;
	}
	
	/**
	 * Prueft ob ein Bewerber alle Dokumente abgegeben hat.
	 * @return
	 * 		True falls alles abgegeben wurde,
	 * 		sonst False.
	 */
	public boolean checkAllDocFromApplicant(String username, int offerID){
		Account acc = acccon.getAccountByUsername(username);
		Offer off = offcon.getOfferById(offerID);
		
		Vector<AppDocument> vec = doccon.getDocumentsByUserAndOffer(acc, off);
		
		Iterator<AppDocument> it = vec.iterator();
		
		AppDocument aktuellesDokument;
		int i = 0;
		//Falls nur ein Dokument fehlt wird der Vorgang abgebrochen.
		//Da dem Clerk eh die Dokumente angezeigt werden die noch fehlen muss man keine weiteren Informationen 
		//rauslesen (oder doch?)
		while(it.hasNext()){
			aktuellesDokument = vec.get(i);
			if (!aktuellesDokument.getPresent()) {
				return false;
			}
		}
		
		return true;
		
	}

}
