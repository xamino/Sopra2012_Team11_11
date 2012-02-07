package userManagement;

import javax.servlet.http.HttpSession;
/**
 *Verwaltet alle Aufgaben und Daten eines Anbieters. 
 */
public class Provider extends User {

	/**
	 * Konstruktor. Erstellte Objekte werden automatisch in der LoggedInUsers-Klasse aufgenommen.
	 * 
	 * @param username Benutzername im System
	 * @param email	E-Mail Adresse
	 * @param name	Realer Name
	 * @param session Session des Benutzers
	 */
	public Provider(String username, String email, String name, HttpSession session) {

	}
	//hier werden im fertigen projekt beziehungen zu anderen klassen modelliert

}
