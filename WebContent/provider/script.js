/**
 * Script for provider webpages.
 * 
 * @author: Laura Irlinger
 * @author Oemer Sahin
 */

/**
 * Stores the selected Offer:
 */
var selectedOffer;

/**
 * Stores the Offer to delete:
 */
var offerToDelete;

/**
 * Stores the Offer to update:
 */
var offerToUpdate;

/**
 * Stores the Offer to select to an applicant
 */
var offerToApplicant;

/**
 * Wird in function markOfferSelected(id) initialisiert und braucht man, damit
 * man auf den username des bewerbers fuer die berwerberauswahl in function
 * takeSelectedApplicant() zugreifen kann
 */
var tmpApplicantUserName;

/**
 * This function loads all the offers in the system from the database and
 * displays them.
 */

function loadOffers() { // ohne alerts funktionierts nicht =( ... wieso??
	// reset selectedID (account could have been deleted in meantime)
	// selectedOffer = null;
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
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table = document.getElementById("providerTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Meine Stellenangebote:</th><th>Bewerber</th><th>Ändern</th><th>Widerrufen</th><th>Bestätigt?</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			var obj = JSONarray[i];
			table.innerHTML += "<tr class=\"\" id=\""
					+ obj.aid
					+ "\"><td>"
					+ obj.name
					+ "</td><td><br><input id=\""
					+ obj.aid
					+ "OfferApplicants\" type=\"button\" value=\"Bewerberauswahl\"  onclick=\"prepareButton(\'"
					+ obj.aid
					+ "\');\"/></td><td><br><input type=\"submit\" value=\"Angebot aendern\" onclick=\"prepareButtonUpdateOffer(\'"
					+ obj.aid
					+ "\');\"/></td><td><br><input type=\"button\" value=\"Angebot zurueckziehen\" onclick=\"prepareButtonDeleteOffer(\'"
					+ obj.aid + "\');\" /> </td><td>"
					+ ((obj.checked) ? "Ja" : "Nein") + "</td></tr>";
			// Logic to disable button if not checked:
			if (!obj.checked) {
				document.getElementById(obj.aid + "OfferApplicants").disabled = true;
			}
		}
	}
}
// Anzahl der Bewerber ... wie implementieren??

/**
 * Function updates the 'Bewerberauswahl' button by setting its onclick
 * reference to the AID of the last marked offer.
 * 
 */
// Button Bewerbauswahl
function prepareButton(id) {
	// alert("preparing button3");
	if (document.getElementById(id) != null) { // userindex.jsp -->
		// applicantlist.jsp, wenn
		// Button gedrueckt wurde
		// alert("idneuneu= "+id);
		document.getElementById(id).onclick = function() {
			window.location = 'applicantlist.jsp?AID=' + id;
		};
	}
}

/**
 * Function updates the 'Bewerberauswahl' button by setting its onclick
 * reference to the AID of the last marked offer.
 * 
 */
function prepareButtonUpdateOffer(aid) {
	offerToUpdate = aid;
	window.location = 'editoffer.jsp?aid=' + aid;

}

/**
 * Function updates the 'Bewerberauswahl' button by setting its onclick
 * reference to the AID of the last marked offer.
 * 
 */
function prepareButtonDeleteOffer(aid) {
	offerToDelete = aid;
	togglePopup('offer_cancel', true);

}

/**
 * This function loads all the applicants from one offer in the system from the
 * database and displays them.
 */
function applicantChoice() {
	var aid = getURLParameter("AID"); //
	// alert("id= "+aid);
	// reset selectedID (account could have been deleted in meantime)
	selectedOffer = null;
	toggleWarning("error_noOfferSelected", true, "Kein Angebot selektiert!");
	connect("/hiwi/Provider/js/applicantChoice", "aid=" + aid,
			handleApplicantChoiceResponse);
}

/**
 * This function displays all the applicants from one offer in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleApplicantChoiceResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "showtheapplicants/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table = document.getElementById("applicantsTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Benutzername:</th><th>Name</th><th>E-Mail</th></tr>";
		/*
		 * orginal von lau lau: for ( var i = 0; i < JSONarray.length; i++) {
		 * table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].name + "\"
		 * onclick=\"markOfferSelected(\'" + JSONarray[i].name + "\');\"><td>" +
		 * JSONarray[i].name + "</td></tr>"; brauche username fuer berwerber
		 * annehmen }
		 */
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].username
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].username + "\');\">" + "<td>"
					+ JSONarray[i].username + "</td><td>" + JSONarray[i].name
					+ "</td><td>" + JSONarray[i].email + "</td></tr>";
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

	tmpApplicantUserName = id; // braucht man, damit man auf den username des
	// bewerbers fuer die berwerberauswahl in
	// function takeSelectedApplicant() zugreifen
	// kann

	// alert("alte id: "+selectedOffer);
	// Remove marking from previous selected, if applicable:
	if (selectedOffer != null)
		document.getElementById(selectedOffer).setAttribute("class", "");
	// If clicked again, unselect:
	if (selectedOffer == id) {
		selectedOffer = null;
		toggleWarning("error_noOfferSelected", true, "Kein Angebot selektiert!");
		return;
	}
	// Else save & mark new one:
	selectedOffer = id;
	toggleWarning("error_noOfferSelected", false, "Kein Angebot selektiert!");

	// alert("aktuelle id: "+selectedOffer);

	document.getElementById(id).setAttribute("class", "selected");
}

/**
 * Deletes an offer if one is selected.
 */
function deleteOffer() {
	connect("/hiwi/Provider/js/deleteOffer", "aid=" + offerToDelete,
			handleDeleteOfferResponse);
}

/**
 * Handles the response to a deletion request for an offer.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleDeleteOfferResponse(mime, data) {
	// alert("handledeleteOfferResponse!");
	if (mime == "text/error")
		alert(data);
	else if (mime == "text/url")
		window.location = data;
}

/**
 * Loads the selected offer's data and displays it in editoffer.jsp?aid=....
 */
function loadSelectedOfferEdit() {

	offerToUpdate = getURLParameter("aid");
	// alert(offerToUpdate);
	connect("/hiwi/Provider/js/getOffer", "aid=" + offerToUpdate,
			handleLoadEditOfferResponse);
}

/**
 * Handles the response selecting an offer for edit.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadEditOfferResponse(mime, data) {
	// alert("handleLoadEditOfferResponse");
	if (mime == "text/error")
		alert(data);
	else if (mime == "offer/json") {
		var offer = eval("(" + data + ")");
		// Set the values we have:
		document.getElementById("titelFeld").value = offer.name;
		document.getElementById("beschreibungsFeld").value = offer.description;

	}
}

/**
 * This function updates an offer.
 */

function updateOfferChanges(form) {
	// alert("UpdateOfferChanges aktiviert");

	offerToUpdate = getURLParameter("aid");
	if (form == null)
		return;

	var error = false;

	var titelFeld = form.titelFeld.value;
	if (titelFeld == null || titelFeld == "") {
		toggleWarning("error_titelFeld", true, "Bitte ausfuellen!");
		error = true;
	} else if (!checkText(titelFeld)) {
		toggleWarning("error_titelFeld", true, "Unerlaubtes Sonderzeichen!");
		error = true;
	} else
		toggleWarning("error_titelFeld", false, "");

	var beschreibungsFeld = form.beschreibungsFeld.value;
	if (beschreibungsFeld == null || beschreibungsFeld == "") {
		toggleWarning("error_beschreibungsFeld", true, "Bitte ausfuellen!");
		error = true;
	} else if (!checkText(beschreibungsFeld)) {
		toggleWarning("error_beschreibungsFeld", true,
				"Unerlaubtes Sonderzeichen!");
		error = true;
	} else
		toggleWarning("error_beschreibungsFeld", false, "");
	if (error)
		return;
	// alert("Sending...!");
	connect("/hiwi/Provider/js/updateOffer", "aid=" + offerToUpdate + "&titel="
			+ titelFeld + "&beschreibung=" + beschreibungsFeld,
			handleUpdateOfferChangesResponse);

}

function handleUpdateOfferChangesResponse(mime, data) {
	// alert("Servlet update response");
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/error") {
		alert(data);
		return;
	}
}

/**
 * This function selects an applicant from the applicantlist as selected/taken.
 * Following operation in the Servlet changes the value to "true" in the db.
 */
function takeSelectedApplicant() {
	offerToApplicant = getURLParameter("AID");

	var username = tmpApplicantUserName; // wird in function
	// markOfferSelected(id)
	// initialisiert

	// alert("username: "+username);

	connect("/hiwi/Provider/js/takeSelectedApplicant", "aid="
			+ offerToApplicant + "&usernameTakenApplicant=" + username,
			handleTakeSelectedApplicantResponse);

}

/**
 * This function works with the response of the ProviderServlet. Operation:
 * select an applicant for an offer.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleTakeSelectedApplicantResponse(mime, data) {
	// alert("Applicant selected funzt net weil ich net weiss, wie ich auf den
	// Benutzernamen vom selektierten Bewerber zugreifen soll.");
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/error") {
		alert(data);
		return;
	}
}
