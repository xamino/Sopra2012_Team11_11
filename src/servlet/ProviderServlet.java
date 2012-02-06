/**
 * @author Laura Irlinger
 * @author Tamino Hartmann
 */
package servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Das <code>Provider</code> Servlet behandelt alle Aktionen von
 * angemeldeten Anbietern / Mitgliedern (Provider).
 */

@WebServlet("/Provider/*")
public class ProviderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ProviderServlet() {
		super();
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
