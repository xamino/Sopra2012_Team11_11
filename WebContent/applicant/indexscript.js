/**
 * Script for admin webpages.
 * 
 * @author: Oemer Sahin
 * @author: Laura Irlinger
 */

/**
 * Stores the selected Account:
 */
var selectedAccount;

/**
 * This function loads all the accounts in the system from the database and
 * displays them.
 */
function loadOffers() {
	// reset selectedID (account could have been deleted in meantime)
	selectedAccount = null;
	connect("/hiwi/Applicant/js/loadOffers", "", handleLoadOffersResponse);
}

/**
 * This function displays all the accounts in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadOffersResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		alert("1. if");
	} else if (mime == "application/json") {
		alert("2. if");
		// Erstelle Array aus JSON array:
		var JSONarray = eval(data);
		// Get the table:
		var table = document.getElementById("offerTable");
		// Write table – probably replaces old data!
		alert("lala");
		table.innerHTML = "<tr><th>Beginn</th><th>Bezeichnung</th><th>Beschreibung</th></tr>";
		alert("mhhh");
		for ( var i = 0; i < JSONarray.length; i++) {
			alert("juhu");
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].Beginn
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].Beginn + "\');\"><td>"
					+ JSONarray[i].Beginn + "</td><td>" + JSONarray[i].Name
					+ "</td><td>" + JSONarray[i].Beschreibung + "</td></tr>";
		}
	}
}

/**
 * Function remembers which account has been clicked.
 * 
 * @param username
 *            The username ID of the clicked entry.
 */
function markOfferSelected(id) {
	// Remove marking from previous selected, if applicable:
	if (selectedAccount != null)
		document.getElementById(selectedAccount).setAttribute("class", "");
	// If clicked again, unselect:
	if (selectedAccount == id) {
		selectedAccount = null;
		return;
	}
	// Else save & mark new one:
	selectedAccount = id;
	document.getElementById(id).setAttribute("class", "selected");
	// alert(selectedID + " is selected.");
}
