/**
 * @author Anatoli Brill
 */

package user;

import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;

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
	 * Private Instanz des Loggers.
	 */
	private Log log;
	/**
	 * Private Instanz des AccountController.
	 */
	private AccountController accountController;

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
		
		if (!accountController.updateAccount(acc)) {
			log.write("Clerk", "Error modifying account!");
			return false;
		}
		log.write("Clerk", "<" + getUserData().getUsername()
				+ "> modified account of <" + acc.getUsername() + ">.");
		return true;
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
	public void addDoc(int UID, String name, String description) {
		Document doc = new Document(UID, name, description);
		doccon.createDocument(doc);
	}

	/**
	 * Methode zum entfernen von Dokumenten.
	 */
	public void delDoc(int UID) {
		Document doc = doccon.getDocumentByUID(UID);
		doccon.deleteDocument(doc);
	}

	/**
	 * Methode zum annehmen eines Bewerbers.
	 */
	public void acceptApplication(int AID) {
		
	}

	/**
	 * Methode zum entfernen von Bewerber-Dokumenten. Dabei kann jedem Bewerber
	 * einzeln Dokumente entfernt werden.
	 */
	public void deleteAppDoc() {

	}

	/**
	 * Methode zum aktualisieren des Berwerbungs-Status.
	 */
	public void updateStatus() {

	}

	/**
	 * Export fuer die Excel-File
	 * 
	 * @return Die URL zu den Download des Excel-Files.
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */

	public String doExport() throws IOException, RowsExceededException, WriteException {
		return ExcelExport.export(this.getUserData());

	}

}
