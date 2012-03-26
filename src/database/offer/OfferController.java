/**
 * @author Patryk Boczon
 * @author Oemer Sahin
 */

package database.offer;

/**
 * Verwaltet alle Datenbankzugriffe auf Angebots-bezogene Daten.
 */
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import database.DatabaseController;
import database.application.Application;

public class OfferController {

	/**
	 * Diese Methode prueft ob ein OfferController-Objekt existiert. Falls nicht
	 * wird eine neue OfferController-Instanz angelegt, zurueckgegeben und in
	 * dem Klassenattribut "offcontr" abgespeichert. Dies dient zur
	 * Gewaehrleistung, dass nur eine Instanz von OfferController existiert.
	 * 
	 * @return Es wird eine Instanz zurueckgegeben.
	 */
	public static OfferController getInstance() {
		if (offcontr == null)
			offcontr = new OfferController();
		return offcontr;
	}

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
	 * Diese Methode erstellt ein neues Jobangebot in der Datenbank mit Daten
	 * aus dem uebergebenen Offer-Objekt.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen noetigen
	 *            Attributen.
	 */
	public void createOffer(Offer offer) {

		
		Object[] values = {offer.getAid(), offer.getAuthor(), offer.getName(), offer.getNote(),
				offer.isChecked(), offer.getSlots(), offer.getHoursperweek(), offer.getDescription(),
				offer.getStartdate(), offer.getEnddate(), offer.getWage(), offer.getInstitute(), offer.getModificationdate()};
		
		dbc.insert("angebote", values);
	}

	/**
	 * Diese Methode loescht ein Jobangebot aus der Datenbank, welches mit den
	 * Daten des uebergebenem Offer-Objekts uebereinstimmt.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen noetigen
	 *            Attributen. Uebergebene Instanz wird komplett vom System
	 *            entfernt.
	 */
	public void deleteOffer(Offer offer) {
		
		dbc.delete("angebote", "AID = "+offer.getAid());
		
	}

	/**
	 * Diese Methode aendert die Attribute eines Jobangebots beziehungsweise
	 * aktualisiert diese in der Datenbank.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            dazugehoerigen Attributen.
	 */
	public void updateOffer(Offer offer) {
		
		String[] columns = {"Ersteller", "Name", "Notiz", "Geprueft", "Plaetze", "Stundenprowoche", "Beschreibung", 
				"Beginn", "Ende", "Stundenlohn", "Institut", "aenderungsdatum"};
		
		Object[] values = {offer.getAuthor(), offer.getName(), offer.getNote(),
				offer.isChecked(), offer.getSlots(), offer.getHoursperweek(), offer.getDescription(),
				offer.getStartdate(), offer.getEnddate(), offer.getWage(), offer.getInstitute(), offer.getModificationdate()};
		
		String where = "AID = "+offer.getAid();
		
		dbc.update("angebote", columns, values, where);
		
	}

	/**
	 * Diese Methode sammelt alle Jobangebote aus der Datenbank und speichert
	 * diese in einem Vector.
	 * 
	 * @return Es wird ein Vector mit allen vorhanden Jobangeboten aus der
	 *         Datenbank zurueckgegeben.
	 */
	public Vector<Offer> getAllOffers() {

		Vector<Offer> offervec = new Vector<Offer>(50,10);
		
		String[] select = {"*"};
		String[] from = {"angebote"};
		
		ResultSet rs = dbc.select(select, from, null);
		try {
			while(rs.next()){
				Offer currentoff;
				currentoff = new Offer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5),
						rs.getInt(6),rs.getDouble(7),rs.getString(8),rs.getDate(9),rs.getDate(10),rs.getDouble(11),
						rs.getInt(12),rs.getDate(13));

				offervec.add(currentoff);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		
		return offervec;
	}

	/**
	 * Diese Methode sammelt alle ueberprueften Jobangebote aus der Datenbank
	 * und speichert diese in einem Vector.
	 * 
	 * @param offer
	 *            Parameter "offer" ist ein Offer-Objekt mit allen
	 *            dazugehoerigen Attributen. (enthaelt boolean "checked"
	 *            Variable, die angibt, ob ein Jobangebot schon ueberprueft
	 *            wurde)
	 * @return Alle Jobangebote in der Datenbank, die dem Wert von "checked"
	 *         entsprechen werden in Form eines Vectors zurueckgegeben.
	 */
	public Vector<Offer> getOffersByCheck(Offer offer) {

		Vector<Offer> offervec = new Vector<Offer>(50,10);
		
		String[] select = {"*"};
		String[] from = {"angebote"};
		String where = "Geprueft = '"+offer.isChecked()+"'";
		
		ResultSet rs = dbc.select(select, from, where);
		try {
			while(rs.next()){
				Offer currentoff;
				currentoff = new Offer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5),
						rs.getInt(6),rs.getDouble(7),rs.getString(8),rs.getDate(9),rs.getDate(10),rs.getDouble(11),
						rs.getInt(12),rs.getDate(13));

				offervec.add(currentoff);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		
		return offervec;
		
	}

	/**
	 * Diese Methode sammelt alle Jobangebote mit freien Stellen aus der
	 * Datenbank und speichert diese in einem Vector.
	 * 
	 * @return Es wird ein Vector mit allen noch freien Jobangeboten aus der
	 *         Datenbank zurueckgegeben. Frei entspricht freie plaetze (slots)
	 *         >= 1.
	 */
	public Vector<Offer> getOffersWithFreeSlots() {
		
		Vector<Offer> offervec = new Vector<Offer>(50,10);
		
		String[] select = {"*"};
		String[] from = {"angebote"};
		String where = "Plaetze > 0";
		
		ResultSet rs = dbc.select(select, from, where);
		try {
			while(rs.next()){
				Offer currentoff;
				currentoff = new Offer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5),
						rs.getInt(6),rs.getDouble(7),rs.getString(8),rs.getDate(9),rs.getDate(10),rs.getDouble(11),
						rs.getInt(12),rs.getDate(13));

				offervec.add(currentoff);
			}
			
			rs.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
		
		return offervec;
	}

	/**
	 * Diese Methode sammelt alle Jobangebote, fuer die Sich ein Bewerber
	 * beworben hat, aus der Datenbank und speichert diese in einem Vector.
	 * 
	 * @param applications
	 *            Parameter "applications" ist ein Vector aus
	 *            Application-Objekten (Bewerbungen) von einem Bewerber
	 * @return Es wird ein Vector mit allen Jobangeboten zurueckgegeben, auf die
	 *         sich ein Bewerber beworben hat.
	 */
	public Vector<Offer> getOffersByApplicatiot(Vector<Application> applications) {
	
		Vector<Offer> offervec = new Vector<Offer>(50,10);
		String[] select = {"*"};
		String[] from = {"angebote"};
		String where;
		
		for(int i = 0; i<applications.size(); i++){
			
			where = "AID = "+applications.elementAt(i).getAid();
			
			ResultSet rs = dbc.select(select, from, where);
			try {
				while(rs.next()){
					Offer currentoff;
					currentoff = new Offer(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4),rs.getBoolean(5),
							rs.getInt(6),rs.getDouble(7),rs.getString(8),rs.getDate(9),rs.getDate(10),rs.getDouble(11),
							rs.getInt(12),rs.getDate(13));
	
					offervec.add(currentoff);
				}
				
				rs.close();
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return offervec;
	}
}