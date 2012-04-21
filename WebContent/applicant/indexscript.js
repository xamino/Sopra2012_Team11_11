/**
 * Script for applicant webpages.
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
function loadOffers() {
	alert("eins"); 																//ohne alerts funktionierts nicht =( ... wieso??
	// reset selectedID (account could have been deleted in meantime)
	selectedOffer = null;
	connect("/hiwi/Applicant/js/loadOffers", "", handleLoadOffersResponse);
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
	alert("zwei");
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "application/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table = document.getElementById("offerTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Beginn</th><th>Bezeichnung</th><th>Beschreibung</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].startdate
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].startdate + "\');\"><td>"
					+ JSONarray[i].startdate + "</td><td>" + JSONarray[i].name
					+ "</td><td><div class=\"float2\">" + JSONarray[i].description + "</div><div class=\"float\"><input type=\"button\" value=\"Bewerben\"	onclick=\"togglePopup('application',true);\" /> </div><div class=\"clear\"></div></td></tr>";
		}
		loadMyOffers();
		alert("fertig");
	}
}

/**
 * Stores the selected Offer:
 */
var selectedMyOffer;

/**
 * This function loads all the offers of the applicant in the system from the database and
 * displays them.
 */
function loadMyOffers() {
	alert("drin");
	// reset selectedID (account could have been deleted in meantime)
	selectedMyOffer = null;
	connect("/hiwi/Applicant/js/loadMyOffers", "", handleLoadMyOffersResponse);
}

/**
 * This function displays all the offers of the applicant in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadMyOffersResponse(mime, data) {
	alert("drindrin");
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "myapplication/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table2 = document.getElementById("myofferTable");
		// Write table – probably replaces old data!
		table2.innerHTML = "<tr><th>Beginn</th><th>Bezeichnung</th><th>Beschreibung</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table2.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].startdate
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].startdate + "\');\"><td>"
					+ JSONarray[i].startdate + "</td><td>" + JSONarray[i].name
					+ "</td><td><div class=\"float2\">" + JSONarray[i].description + "</div><div class=\"float\"><input type=\"submit\" value=\"Bewerbung ansehen\" onclick=\"window.location='status.jsp'\" /></div><div class=\"clear\"></div></td></tr>";
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


// noch nicht funktionsf�hig!!

/**
 * Stores the selected Offer:
 */
var selectedApplication;

/**
 * This function loads all information about one application in the system from the database and
 * displays them.
 */
function loadMyApplications() {
	alert("eins"); 																//ohne alerts funktionierts nicht =( ... wieso??
	// reset selectedID (account could have been deleted in meantime)
	selectedApplication = null;
	connect("/hiwi/Applicant/js/loadMyApplications", "", handleLoadMyApplicationsResponse);
}

/**
 * This function displays all the information about one application in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadMyApplicationsResponse(mime, data) {
	alert("zwei");
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "application/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table = document.getElementById("applicationsTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<h4>Bewerbung f�r:" +    JSONarray[i].description  +"</h4><form class=\"listform\"><table>";	//WOW-TUT
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML +="<tr><td><input type=\"checkbox\" /></td><td>" +    JSONarray[i].description  + "</td></tr>"; //Superheldenbescheinigung
		}
		table.innerHTML += "</table></form>";
		alert("fertig");
	}
}
