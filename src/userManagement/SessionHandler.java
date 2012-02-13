package userManagement;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Verwaltet das Ablaufen von Sessions
 * 
 */
public class SessionHandler implements HttpSessionListener {

	/**
	 * Nur da weil alle Funktionen implementiert werden muessen, wird nicht
	 * gebraucht!.
	 */
	@Override
	public void sessionCreated(HttpSessionEvent arg0) {
		// does nothing

	}

	/**
	 * Kuemmert sich um die User mit abgelaufenen Sessions um diese dan
	 * abzumelden. Wenn eine Session zerstoert wird (abgelaufen oder ungueltig
	 * gemacht), wird der User aus der Liste der eingeloggten User entfernt.
	 * 
	 */
	@Override
	public void sessionDestroyed(HttpSessionEvent se) {

	}

}
