/**
 *  @author Patryk Boczon
*/

package database;


/**
 * Hilfsklasse zur Speicherung eines Datensatzes einer Datenbankabfrage.
 * Wird vom Clerk, zur Anzeige von angenommenen Bewerbungen eines bestimmten Instituts, benoetigt.
 */
public class HilfsDatenClerk {

	/**
	 *  Name des Bewerbers
	 */
	private String bewerbername;
	/**
	 *  Name des Angebots
	 */
	private String angebotsname;
	
	/**
	 * Konstruktor fuer den Datensatz
	 * 
	 * @param bewerbername
	 * 		Uebergibt den Bewerbernamen
	 * @param angebotsname
	 * 		Uebergibt den Angebotsnamen
	 */
	public HilfsDatenClerk(String bewerbername, String angebotsname){
		this.bewerbername = bewerbername;
		this.angebotsname = angebotsname;
	}
}
