/**
 * Script for admin webpages.
 * 
 * @author: Laura Irlinger
 * @author: Patryk Boczon
 * @author: Oemer Sahin
 */

// All public, non-xmlhttp variables here:
/**
 * Stores the selected Account:
 */
var selectedAccount;



/**
 * This function loads the account in the system from the database and
 * displays the data.
 */
function loadAccount() {
	// reset selectedID (account could have been deleted in meantime)
	selectedAccount = null;
	connect("/hiwi/Applicant/js/loadAccount", "", handleLoadAccountsResponse);
}



/**
 * This function displays the account's data in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadAccountsResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "application/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval(data);
		// Get the table:
		var table = document.getElementById("accountTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Benutzer Name</th><th>Name</th><th>Emailaddresse</th><th>Account Typ</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].username
					+ "\" onclick=\"markAccountSelected(\'"
					+ JSONarray[i].username + "\');\"><td>"
					+ JSONarray[i].username + "</td><td>" + JSONarray[i].name
					+ "</td><td>" + JSONarray[i].email + "</td><td>"
					+ getTypeString(JSONarray[i].accounttype) + "</td></tr>";
		}
	}
}