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
	 *  Username des Bewerbers
	 */
	private String username;
	
	/**
	 *  aid des Angebots
	 */
	private int aid;
	
	/**
	 * Konstruktor fuer den Datensatz
	 * 
	 * @param bewerbername
	 * 		Uebergibt den Bewerbernamen
	 * @param angebotsname
	 * 		Uebergibt den Angebotsnamen
	 * @param username
	 * 		Uebergibt den Username des Bewerbes
	 * @param aid
	 * 		Uebergibt die aid des Angebots
	 * 
	 */
	public HilfsDatenClerk(String bewerbername, String angebotsname, String username, int aid){
		this.bewerbername = bewerbername;
		this.angebotsname = angebotsname;
		this.username = username;
		this.aid = aid;
		
	}
}
