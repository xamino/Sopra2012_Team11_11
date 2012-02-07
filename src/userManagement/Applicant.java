package userManagement;

import javax.servlet.http.HttpSession;


/**
 *Verwaltet alle Aufgaben und Daten eines Bewerbers. 
 */
public class Applicant extends User {

	
	/**
	 * Konstruktor. Erstellte Objekte werden automatisch in der LoggedInUsers klasse aufgenommen.
	 * 
	 * @param benutzername Benutzername im System
	 * @param email	E-Mail Adresse
	 * @param name	Realer Name
	 * @param session Session des Benutzers
	 */
	public Applicant(String benutzername, String email, String name, HttpSession session) {
		
	}
	//hier werden im fertigen projekt beziehungen zu anderen klassen modelliert

}
