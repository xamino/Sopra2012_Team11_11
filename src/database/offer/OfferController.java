/**
 * @author Patryk Boczon
 * @author Oemer Sahin
 */

package database.offer;

/**
 * Verwaltet alle Datenbankzugriffe auf Angebots-bezogene Daten
 */
import java.util.Vector;

import database.DatabaseController;
import database.application.Application;

public class OfferController {

	/**
	 * Privater Konstruktor, da die Klasse selbst ein Singleton ist.
	 */
	private OfferController() {

	}

	/**
	 * Klassenattribut "offcontr" beinhaltet eine OfferController-Instanz, falls
	 * keine vorhanden war und mit der Methode getInstance angelegt wird. Dies
	 * dient zur Gewaehrleistung, dass nur eine Instanz von OfferController
	 * existiert.
	 */
	private static OfferController offcontr;

	/**
	 * Attribut dbc ist eine DatabaseController Instanz und wird fuer den
	 * Datenbankzugang benoetigt.
	 */
	private DatabaseController dbc;

	/**
	 * Diese Methode erstellt ein neues Jobangebot in der Datenbank mit Daten aus dem uebergebenen
	 * Offer-Objekt.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            noetigen Attributen.
	 */
	public void createOffer(Offer offer) {

	}

	/**
	 * Diese Methode loescht ein Jobangebot aus der Datenbank, welches mit den Daten des uebergebenem
	 * Offer-Objekts uebereinstimmt.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            noetigen Attributen. Uebergebene Instanz wird komplett
	 *            vom System entfernt.
	 */
	public void deleteOffer(Offer offer) {

	}

	/**
	 * Diese Methode aendert die Attribute eines Jobangebots bzw. aktualisiert
	 * diese in der Datenbank.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void updateOffer(Offer offer) {

	}

	/**
	 * Diese Methode sammelt alle Jobangebote aus der Datenbank und speichert
	 * diese in einem Vector.
	 * 
	 * @return Es wird ein Vector mit allen vorhanden Jobangeboten aus der
	 *         Datenbank zurueckgegeben.
	 */
	public Vector<Offer> getAllOffers() {
		return null;

	}

	/**
	 * Diese Methode sammelt alle ueberprueften Jobangebote aus der Datenbank
	 * und speichert diese in einem Vector.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            dazugehoerigen Attributen. (enthaelt boolean "checked" Variable,
	 *            die angibt, ob ein Jobangebot schon ueberprueft wurde)
	 * @return Alle Jobangebote in der Datenbank, die dem Wert von "checked"
	 *         entsprechen werden in Form eines Vectors zurueckgegeben.
	 */
	public Vector<Offer> getOffersByCheck(Offer offer) {
		return null;

	}

	/**
	 * Diese Methode sammelt alle Jobangebote mit freien Stellen aus der
	 * Datenbank und speichert diese in einem Vector.
	 * 
	 * @return Es wird ein Vector mit allen noch freien Jobangeboten aus der
	 *         Datenbank zurueckgegeben. Frei entspricht freie plaetze (slots) >= 1.
	 */
	public Vector<Offer> getOffersWithFreeSlots() {
		return null;

	}

	/**
	 * Diese Methode sammelt alle Jobangebote, fuer die Sich ein Bewerber beworben hat, aus der
	 * Datenbank und speichert diese in einem Vector.
	 * 
	 * @param applications
	 *            Parameter "applications" ist ein Vector aus Application-Objekten
	 *            (Bewerbungen) von einem Bewerber
	 * @return Es wird ein Vector mit allen Jobangeboten zurueckgegeben, auf die sich
	 *         ein Bewerber beworben hat.
	 */
	public Vector<Offer> getOffersByApplicatiot(Vector<Application> applications) {
		return null;

	}

	/**
	 * Diese Methode prueft ob ein OfferController-Objekt existiert. Falls nicht
	 * wird eine neue OfferController-Instanz angelegt, zurueckgegeben und in
	 * dem Klassenattribut "offcontr" abgespeichert. Dies dient zur
	 * Gewaehrleistung, dass nur eine Instanz von OfferController existiert.
	 * 
	 * @return Es wird die Instanz zurueckgegeben.
	 */
	public static OfferController getInstance() {
		if (offcontr == null)
			offcontr = new OfferController();
		return offcontr;

	}

}
