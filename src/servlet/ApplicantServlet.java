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
 * Das <code>Applicant</code> Servlet behandelt alle Aktionen von angemeldeten
 * Bewerbern (Applicants).
 */

@WebServlet("/Applicant/*")
public class ApplicantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ApplicantServlet() {
		super();
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
	}
}
