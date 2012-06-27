package userManagement;

import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Vector;

import javax.servlet.http.HttpSession;

import logger.Log;
import user.User;
import file.FileController;

/**
 * Verwaltet alle eingelogten User.
 */
public abstract class LoggedInUsers {

	/**
	 * Statische Variable für den Logger
	 */
	private static Log log = Log.getInstance();

	/**
	 * Hashmap mit der eingeloggten session ids mit zugehörigem User Objekt
	 */
	private static HashMap<String, User> userMap = new HashMap<String, User>();
	

	/**
	 * Gibt einen Vector mit den eingeloggten Usern zurueck.
	 * 
	 * @return Vector mit eingeloggten Usern
	 */
	public static Vector<User> getUsers() {
		return new Vector<User>(userMap.values());
	
	}

	/**
	 * Fuegt einen User zum Vektor der eingeloggten User hinzu und behebt
	 * eventuelle Konflikte. Versucht man einen User einzuloggen der bereits
	 * eine Session im System hat, so wird die alte Session beendet.Versucht man
	 * sich als ein User anzumelden,der bereits im System angemeldet ist, so
	 * wird der bereits vorhandene Kandidat abgemeldet.
	 * 
	 * @param u
	 *            hinzuzufuegender User
	 * 
	 * 
	 */
	static public void addUser(User u) {
		User tempuserbyname = getUserByUsername(u.getUserData().getUsername());
		User tempuserbysession = getUserBySession(u.getUserData().getSession());
		String name = u.getUserData().getUsername();
		if (tempuserbyname == null && tempuserbysession == null) {
			userMap.put(u.getUserData().getSession().getId(), u);
			log.write("LoggedInUsers", name
					+ " has logged in.");

		} else if (tempuserbysession == null && tempuserbyname != null) {
			log.write("LoggedInUsers", "Killing User: "+tempuserbyname.getUserData().getUsername());
			tempuserbyname.invalidate();
			userMap.put(u.getUserData().getSession().getId(), u);
			log.write("LoggedInUsers", name
					+ " has logged in.");

		}
		else if (tempuserbyname == null && tempuserbysession!=null) {
			log.write("LoggedInUsers", "Killing User: "+tempuserbysession.getUserData().getUsername());
			removeUserBySession(u.getUserData().getSession());
			userMap.put(u.getUserData().getSession().getId(), u);
			log.write("LoggedInUsers", name
					+ " has logged in.");
		}
		else if(tempuserbyname !=null && tempuserbysession!=null){
			if(tempuserbyname==tempuserbysession)return;
			log.write("LoggedInUsers", "Killing User: "+tempuserbysession.getUserData().getUsername());
			removeUserBySession(u.getUserData().getSession());
			log.write("LoggedInUsers", "Killing User: "+tempuserbyname.getUserData().getUsername());
			tempuserbyname.invalidate();
			userMap.put(u.getUserData().getSession().getId(), u);
			log.write("LoggedInUsers", name
					+ " has logged in.");
		}

	}

	/**
	 * Gibt ein User-Objekt mit dem übergebenen Namen zurueck.
	 * 
	 * @param username
	 *            zu suchender Benutzername
	 * @return Das gefundene User-Objekt wird zurueckgegeben, ansonsten
	 *         <code>NULL</code>
	 * @see User
	 */
	public static User getUserByUsername(String username) {
		User u;
		for(Entry<String,User> e : userMap.entrySet()){
			u=e.getValue();
			if(u.getUserData().getUsername().equals(username))return u;
		}
		return null;
	}

	/**
	 * Gibt ein User-Objekt mit der übergebenen Session zurueck
	 * 
	 * @param session
	 *            zu suchende Session
	 * @return Das gefundene User-Objekt wird zurueckgegeben, ansonsten
	 *         <code>Null</code>
	 **/
	public static User getUserBySession(HttpSession session) {
		return userMap.get(session.getId());
	}

	/**
	 * Entfernt das User-Objekt mit der ID der angegebenen Session aus der Liste
	 * der eingeloggten User. Ist die Session unbenannt, so wird nichts
	 * entfernt.
	 * 
	 * @param session
	 *            Session des zu loeschenden <code>User</code>-Objekts
	 * 
	 */
	static void removeUserBySession(HttpSession session) {
		String id = session.getId();
		String name="";
		try{
			name = userMap.get(id).getUserData().getUsername();
		}catch(NullPointerException n){
			return;
		}
		userMap.remove(id);
		FileController.deleteFile(name);
		log.write("LoggedInUsers", name+ " has logged out.");
	}
}