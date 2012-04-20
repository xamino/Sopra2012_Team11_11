/**
 * @author Tamino Hartmann
 */

package servlet;

import java.io.IOException;
import java.util.Vector;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import database.offer.Offer;
import database.offer.OfferController;

import logger.Log;

/**
 * Dieses Servlet ist für alle öffentlich zugängliche Daten zuständig.
 * Insbesondere betrifft dies den Abruf aller verfügbaren Angebote aus der
 * Datenbank für den Index.
 */

@WebServlet("/Servlet/*")
public class Servlet extends HttpServlet {

	/**
	 * Private Instanz des Loggers.
	 */
	private Log log;
	/**
	 * Private Instanz des OfferController.
	 */
	private OfferController offController;
	/**
	 * Variable zum speichern der GSON Instanz.
	 */
	private Gson gson;
	/**
	 * Standard serialVersionUID.
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Konstruktor.
	 */
	public Servlet() {
		super();
		gson = new Gson();
		log = Log.getInstance();
		offController = OfferController.getInstance();
		log.write("Servlet", "Instance created.");
	}

	/**
	 * Diese Methode handhabt die Abarbeitung von Aufrufen.
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String path = request.getPathInfo();
		path = (path == null) ? "" : path;
		log.write("Servlet", "Received request <" + path + ">.");
		if (path.equals("/js/loadOffers")) {
			Vector<Offer> offers = offController.getOffersWithFreeSlots();
			if (offers == null || offers.isEmpty()) {
				// If no offers are in the DB:
				response.setContentType("text/plain");
				response.getWriter().write("null");
				return;
			}
			// TODO: what information do we have to filter here? Can/should it
			// all be transmitted?
			response.setContentType("application/json");
			response.getWriter().write(gson.toJson(offers, offers.getClass()));
			return;
		}
	}
}