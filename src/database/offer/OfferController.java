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
	 * dient um zu gewaehrleisten, dass nur eine Instanz von OfferController
	 * existiert.
	 */
	private static OfferController offcontr;

	/**
	 * Attribut dbc ist eine DatabaseController Instanz und wird fuer den
	 * Datenbankzugang benoetigt.
	 */
	private DatabaseController dbc;

	/**
	 * Diese Methode erstellt eine neue Jobangebots-Instanz mit uebergebenem
	 * Offer-Objekt.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void createOffer(Offer offer) {

	}

	/**
	 * Diese Methode loescht eine Jobangebots-Instanz mit uebergebenem
	 * Offer-Objekt.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            dazugehoerigen Attributen. Uebergebene Instanz wird komplett
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
	 * diese in einem Vektor.
	 * 
	 * @return Es wird ein Vektor mit allen vorhanden Jobangeboten aus der
	 *         Datenbank zurueckgegeben.
	 */
	public Vector<Offer> getAllOffers() {
		return null;

	}

	/**
	 * Diese Methode sammelt alle ueberprueften Jobangebote aus der Datenbank
	 * und speichert diese in einem Vektor.
	 * 
	 * @param checked
	 *            Parameter "checked" ist von Typ boolean und gibt an, ob ein
	 *            Jobangebot schon ueberprueft wurde.
	 * @return Alle Jobangebote in der Datenbank, die dem Wert von "checked"
	 *         entsprechen werden in Form eines Vektors zurueckgegeben.
	 */
	public Vector<Offer> getOffersByCheck(boolean checked) { // nochmal pruefen,
																// ob diese
																// Methodenbeschreibung
																// stimmt!
		return null;

	}

	/**
	 * Diese Methode sammelt alle Jobangebote mit freien Stellen aus der
	 * Datenbank und speichert diese in einem Vektor.
	 * 
	 * @return Es wird ein Vektor mit allen noch freien Jobangeboten aus der
	 *         Datenbank zurueckgegeben.
	 */
	public Vector<Offer> getOffersWithFreeSlots() {
		return null;

	}

	/**
	 * Diese Methode sammelt alle Jobangebote einer bestimmten Bewerbung aus der
	 * Datenbank und speichert diese in einem Vektor.
	 * 
	 * @param application
	 *            Parameter "application" ist eine Application-Objekt
	 *            (Bewerbungs-Objekt).
	 * @return Es wird ein Vektor mit allen Jobangeboten einer bestimmten
	 *         Bewerbung aus der Datenbank zurueckgegeben.
	 */
	public Vector<Offer> getOffersByApplication(Application application) {
		return null;

	}

	/**
	 * Diese Methode prueft ob ein OfferController-Objekt existiert. Falls nicht
	 * wird eine neue OfferController-Instanz angelegt, zurueckgegeben und in
	 * dem Klassenattribut "offcontr" abgespeichert. Dies dient um zu
	 * gewaehrleisten, dass nur eine Instanz von OfferController existiert.
	 * 
	 * @return Es wird die Instanz zurueckgegeben.
	 */
	public static OfferController getInstance() {
		if (offcontr == null)
			offcontr = new OfferController();
		return offcontr;

	}

}
