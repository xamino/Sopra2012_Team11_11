/**
 * @author Anatoli Brill
 */

package user;

import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import mail.Mailer;
import database.HilfsDatenClerk;
import database.account.Account;
import database.document.AppDocument;
import database.document.Document;
import database.document.OfferDocument;
import database.offer.Offer;
import file.ExcelExport;

/**
 * Verwaltet alle Aufgaben und Daten eines Verwalters.
 */
public class Clerk extends User {

	/**
	 * Instanz des Mailers.
	 */
	private Mailer mail;

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
	 * @param representative
	 *            Name des Repraesentanten.
	 * @param session
	 *            Session des Benutzers
	 */
	public Clerk(String username, String email, String name,
			String representative, HttpSession session) {
		super(username, email, name, representative, session);
		mail = Mailer.getInstance();
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
	 * 
	 * @return Beim erfolgreichen Entfernen wird ein TRUE zurückgegeben. Falls
	 *         irgendwo ein Fehler aufgetretten ist wird ein FALSE
	 *         zurückgegeben.
	 */
	public boolean deleteOwnAccount() {
		invalidate();
		return acccon.deleteClerkAccount(this);
	}

	/**
	 * Gibt ein Angebot anhand der AID zurueck.
	 * 
	 * @param AID
	 *            Die AID des Angebots.
	 * @return Das Angebot.
	 */
	public Offer getOfferByAID(int AID) {
		return offcon.getOfferById(AID);
	}

	/**
	 * Aktualisiert ein Angebot in der Datenbank und schickt entsprechend emails
	 * an die Betroffenen.
	 * 
	 * @param off
	 *            Das zu aendernde Angebot.
	 * @return Flag fuer Fehler.
	 */
	public boolean updateOffer(Offer off) {
		Account author = acccon.getAccountByUsername(off.getAuthor());
		String address = author.getEmail();
		if (off.isChecked() && !off.isFinished())
			mail.sendMail(
					address,
					"Freischaltung des Angebots \"" + off.getName() + "\"",
					"Hiermit teilen wir ihnen mit, dass ihr Angebot \""
							+ off.getName()
							+ "\" für Bewerber freigeschaltet wurde.");
		if (!off.isChecked() && off.isFinished())
			mail.sendMail(address, "Ablehnen des Angebots \"" + off.getName()
					+ "\"", "Hiermit teilen wir ihnen mit, dass ihr Angebot \""
					+ off.getName()
					+ "\" durch einen Verwalter abgelehnt wurde.");
		return offcon.updateOffer(off);
	}

	/**
	 * Liefert alle Dokumente zu einem Angebot zurueck.
	 * 
	 * @param aid
	 *            Die AID des Angebots.
	 * @return Die Dokumente.
	 */
	public Vector<Document> getDocumentsFromOffer(int aid) {
		Vector<Offer> offersid = offcon.getAllOffers();
		Vector<OfferDocument> offerdocuments = new Vector<OfferDocument>();
		for (Offer offer : offersid) {
			if (aid == offer.getAid())
				offerdocuments = doccon.getDocumentsByOffer(aid);
		}
		Vector<Document> documents = new Vector<Document>();
		for (OfferDocument offDoc : offerdocuments) {
			documents.add(doccon.getDocumentByUID(offDoc.getDocumentid()));
		}
		return documents;
	}

	/**
	 * Methode zum entfernen von Dokumenten.
	 */
	public void delDoc(Document doc) {
		doccon.deleteDocument(doc);
	}

	/**
	 * Export fuer die Excel-File
	 * 
	 * @return Fileobjekt des ExcelFiles
	 * @throws IOException
	 * @throws WriteException
	 * @throws RowsExceededException
	 */
	public File doExport() throws IOException, RowsExceededException,
			WriteException {
		return ExcelExport.export(this.getUserData());

	}

	/**
	 * Prueft ob ein Bewerber alle Dokumente abgegeben hat.
	 * 
	 * @return True falls alles abgegeben wurde, sonst False.
	 */
	public boolean checkAllDocFromApplicant(String username, int offerID) {
		Account acc = acccon.getAccountByUsername(username);
		Offer off = offcon.getOfferById(offerID);

		Vector<AppDocument> vec = doccon.getDocumentsByUserAndOffer(acc, off);

		Iterator<AppDocument> it = vec.iterator();

		AppDocument aktuellesDokument;
		int i = 0;
		// Falls nur ein Dokument fehlt wird der Vorgang abgebrochen.
		// Da dem Clerk eh die Dokumente angezeigt werden die noch fehlen muss
		// man keine weiteren Informationen
		// rauslesen (oder doch?)
		while (it.hasNext()) {
			aktuellesDokument = vec.get(i);
			if (!aktuellesDokument.getPresent()) {
				return false;
			}
		}

		return true;

	}

	/**
	 * Diese Methode holt alle ungeprueften Angebote aus der Datenbank und gibt
	 * sie als Vector<Offer> zurueck. Dabei werden nur die aus dem
	 * entsprechenden Institut geholt und eventuell die des Stellvertreters
	 * auch, wenn einer eingetragen ist.
	 * 
	 * @return Denn Vector<Offer> mit den entsprechenden Angeboten.
	 */
	public Vector<Offer> getAllUncheckedOffers() {
		Vector<Offer> offers = new Vector<Offer>();
		Account rep = acccon.getRepresentativeAccount(this.getUserData()
				.getUsername());
		// Check if field is used:
		if (rep != null) {
			// Cancel if rep is null (error somewhere), wrong accounttype, or is
			// own name.
			if (rep == null || rep.getAccounttype() != Account.VERWALTER
					|| rep.getUsername() == getUserData().getUsername()) {
				log.write("Clerk",
						"ERROR getting representative, is the value valid?");
			} else {
				log.write("Clerk", "<" + this.getUserData().getUsername()
						+ "> is representative for <" + rep.getUsername()
						+ ">, allowing cross-editing of offers.");
				offers = offcon.getUncheckedOffersByClerk(rep);
			}
		}
		// Load own offers:
		log.write("Clerk", "Getting unchecked offers applyable to <"
				+ this.getUserData().getUsername() + ">.");
		Account ownAccount = acccon.getAccountByUsername(this.getUserData()
				.getUsername());
		offers.addAll(offcon.getUncheckedOffersByClerk(ownAccount));
		return offers;
	}

	/**
	 * Gibt alle Dokumente zurueck, welche in einem Angebot noch nicht verwendet
	 * werden.
	 * 
	 * @param aid
	 *            Die AID des Angebots.
	 * @return Die unverwendeten Dokumente.
	 */
	public Vector<Document> getUnusedDocForOffer(int aid) {
		return doccon.getDocumentsToAddToOffer(aid);
	}

	/**
	 * Gibt alle zu vergebende Dokumente auf eine Bewerbung zurueck.
	 * 
	 * @param aid
	 *            Die AID des Angebots.
	 * @param username
	 *            Der Benutzername des Bewerbers.
	 * @return Die Dokumente verpackt in einen wunderschoenen Vektor.
	 */
	public Vector<Document> getDocsForApplication(int aid, String username) {
		return doccon.getDocumentsToAddToApp(aid, username);
	}

	/**
	 * Gibt Bewerbername und Angebotsname aller angenommenen Bewerbungen des
	 * uebergebenen Instituts in Form eines Vectors des Datentyps
	 * HilfsDatenClerk zurueck.
	 * 
	 * @param clerkAccount
	 * @return
	 */
	public Vector<HilfsDatenClerk> getVoodoo(Account clerkAccount) {
		return appcon.getChosenApplicationDataByInstitute(clerkAccount
				.getInstitute());
	}
}