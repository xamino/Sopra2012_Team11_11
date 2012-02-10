/**
 * @author Patryk Boczon
 * @author Oemer Sahin
 */

package database.application;

/**
 * Verwaltet alle Datenbankzugriffe auf Bewerbungs-bezogene Daten
 */
import java.util.Vector;

import database.DatabaseController;
import database.offer.OfferController;

public class ApplicationController {

	/**
	 * Privater Konstruktor, da die Klasse selbst ein Singleton ist.
	 */
	private ApplicationController() {

	}

	/**
	 * Klassenattribut "appcontr" beinhaltet eine ApplicationController-Instanz,
	 * falls keine vorhanden war und mit der Methode getInstance angelegt wird.
	 * Dies dient um zu gewaehrleisten, dass nur eine Instanz von
	 * ApplicationController existiert.
	 */
	private static ApplicationController appcontr;

	/**
	 * Diese Instanz dient zum Zugang in die Datenbank.
	 */
	public DatabaseController dbc;

	/**
	 * Diese Methode erstellt ein Bewerbungsobjekt in der Datenbank. Mit
	 * ubergebenem Bewerbungsobjekt.
	 * 
	 * @param application
	 *            Parameter "application" ist ein Application-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void createApplication(Application application) {

	}

	/**
	 * Diese Methode loescht ein Bewerbungsobjekt aus der Datenbank. Mit
	 * ubergebenem Bewerbungsobjekt.
	 * 
	 * @param application
	 *            Parameter "application" ist ein Application-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void deleteApplication(Application application) {

	}

	/**
	 * Diese Methode aendert die Attribute einer Bewerbung bzw. aktualisiert
	 * diese in der Datenbank.
	 * 
	 * @param application
	 *            Parameter "application" ist ein Application-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void updateApplication(Application application) {

	}

	/**
	 * Diese Methode sammelt alle Bewerbungen aus der Datenbank und speichert
	 * diese in einem Vektor.
	 * 
	 * @return Es wird ein Vektor mit allen vorhanden Bewerbungen in der
	 *         Datenbank zurueckgegeben.
	 */
	public Vector<Application> getAllApplications() {
		return null;

	}

	/**
	 * Diese Methode sammelt alle Bewerbungen eines bestimmten Bewerbers aus der
	 * Datenbank und speichert diese in einem Vektor.
	 * 
	 * @param username
	 *            Parameter "username" gibt den Namen dese Bewerbers an, dessen
	 *            Bewerbungen angezeigt werden sollen.
	 * @return Alle Bewerbungen zu einem Bewerber aus der Datenbank in Form
	 *         eines Vektors werden zurueckgegeben.
	 */
	public Vector<Application> getApplicationsByApplicant(String username) {
		return null;

	}

	/**
	 * Diese Methode sammelt alle Bewerbungen zu einem bestimmten Jobangebot aus
	 * der Datenbank und speichert diese in einem Vektor.
	 * 
	 * @param aid
	 *            Parameter "aid" (Angebots-Id) ist die Id des Jobangebots.
	 * @return Es wird ein Vektor mit allen Bewerbungen zu einem bestimmten
	 *         Jobangebot aus der Datenbank zurueckgegeben.
	 */
	public Vector<Application> getApplicationsByOffer(int aid) {
		return null;

	}

	/**
	 * Diese Methode prueft ob ein ApplicationController-Objekt existiert. Falls
	 * nicht wird eine neue ApplicationOffer-Instanz angelegt, zurueckgegeben
	 * und in dem Klassenattribut "appcontr" abgespeichert. Dies dient um zu
	 * gewaehrleisten, dass nur eine Instanz von ApplicationController
	 * existiert.
	 * 
	 * @return Es wird die Instanz zurueckgegeben.
	 */
	public static ApplicationController getInstance() {
		if (appcontr == null)
			appcontr = new ApplicationController();
		return appcontr;

	}

}
