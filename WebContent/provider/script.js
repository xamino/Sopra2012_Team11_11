/**
 * Script for provider webpages.
 * 
 * @author: Laura Irlinger
 */

/**
 * Stores the selected Offer:
 */
var selectedOffer;

/**
 * This function loads all the offers in the system from the database and
 * displays them.
 */
function loadOffers() {															//ohne alerts funktionierts nicht =( ... wieso??
	// reset selectedID (account could have been deleted in meantime)
	selectedOffer = null;
	connect("/hiwi/Provider/js/loadOffers", "", handleLoadOffersResponse);
}

/**
 * This function displays all the offers in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadOffersResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "offer/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table = document.getElementById("providerTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Meine Stellenangebote:</th><th>Bewerber/Stelle</th><th>Aendern</th><th>Widerrufen</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].name
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].name + "\');\"><td>"
					+ JSONarray[i].name + "</td><td>hier fehlt die Anzahl!!<br><input type=\"submit\" value=\"Bewerberauswahl\" onclick=\"window.location='applicantlist.jsp'\" /></td><td><br><input type=\"submit\" value=\"Angebot aendern\" onclick=\"window.location='editoffer.jsp'\"/></td><td><br><input type=\"button\" value=\"Angebot zurueckziehen\" onclick=\"togglePopup('offer_cancel',true);\" /> </td></tr>";
		}
	}
}