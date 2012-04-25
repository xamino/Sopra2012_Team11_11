/**
 * Script for provider webpages.
 * 
 * @author: Laura Irlinger
 * @author: Oemer Sahin
 */

/**
 * Stores the selected Offer:
 */
var selectedOffer;

/**
 * This function loads all the offers in the system from the database and
 * displays them.
 */

function loadOffers() { // ohne alerts funktionierts nicht =( ... wieso??
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
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table = document.getElementById("providerTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Meine Stellenangebote:</th><th>Bewerber/Stelle</th><th>Aendern</th><th>Widerrufen</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\""
					+ JSONarray[i].name
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].name
					+ "\');\"><td>"
					+ JSONarray[i].name
					+ "</td><td>hier fehlt die Anzahl!!<br><input type=\"submit\" value=\"Bewerberauswahl\" onclick=\"window.location='applicantlist.jsp'\" /></td><td><br><input type=\"submit\" value=\"Angebot aendern\" onclick=\"window.location='editoffer.jsp'\"/></td><td><br><input type=\"button\" value=\"Angebot zurueckziehen\" onclick=\"togglePopup('offer_cancel',true);\" /> </td></tr>";
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
	//alert("alte id: " + selectedOffer);
	// Remove marking from previous selected, if applicable:
	if (selectedOffer != null)
		document.getElementById(selectedOffer).setAttribute("class", "");
	// If clicked again, unselect:
	if (selectedOffer == id) {
		selectedOffer = null;
		return;
	}
	// Else save & mark new one:
	selectedOffer = id;

	//alert("aktuelle id: " + selectedOffer);

	document.getElementById(id).setAttribute("class", "selected");

	// updating 'Angebot pruefen' button
	prepareButton();

}

/**
 * Function updates the 'Angebot pruefen' button by setting its onclick
 * reference to the AID of the last marked offer
 * 
 */
function prepareButton() {
	alert("preparing button von Provider SCRIPT");
//	document.getElementById("angebotpruefen").onclick = function() {
//		window.location = 'editoffer.jsp?AID=' + selectedOffer;
		document.getElementById(selectedOffer).onclick = function() {
			window.location = 'editoffer.jsp?AID=' + selectedOffer;
	};
}

/**
 * This function adds an new Offer from the provider account
 * in createoffer.jsp
 * 
 * @param form is the HTML formular which is being filled with significant values of an offer-object.
 */
function addOffer(form) {
	// alert("ICH BIN IN ADD OFFER");
	if (form == null)
		return;
	var error = false;

	var titel = form.titel.value;
	if (titel == null || titel == "") {
		toggleWarning("error_titel", true, "Bitte ausfuellen!");
		error = true;
	} else
		toggleWarning("error_titel", false, "");

	var std = form.std.value;
	if (std == null || std == "") {
		toggleWarning("error_std", true, "Bitte ausfuellen!");
		error = true;
	} else
		toggleWarning("error_std", false, "");

	var stellen = form.stellen.value;
	if (stellen == null || stellen == "") {
		toggleWarning("error_stellen", true, "Bitte ausfuellen!");
		error = true;
	} else
		toggleWarning("error_stellen", false, "");

	var beschreibung = form.beschreibung.value;
	if (beschreibung == null || beschreibung == "") {
		toggleWarning("error_beschreibung", true, "Bitte ausfuellen!");
		error = true;
	} else {
		toggleWarning("error_beschreibung", false, "");

		var notiz = form.notiz.value;
		if (notiz == null || notiz == "") {
			toggleWarning("error_notiz", true, "Bitte ausfuellen!");
			error = true;
		} else
			toggleWarning("error_notiz", false, "");

	}
	connect("/hiwi/Provider/js/addOffer", "titel=" + titel + "&std=" + std
			+ "&stellen=" + stellen + "&beschreibung=" + beschreibung
			+ "&notiz=" + notiz, handleCreateOfferResponse);
}


/**
 * Deletes an offer if one is selected.
 */
function deleteOffer() {
	//alert("delete Offer in progress");
	if (selectedOffer == null) {
		toggleWarning("error_selection", true, "Kein Dokument ausgewaehlt! ");
		togglePopup("offer_del", false);
		return;
	}
	connect("/hiwi/Provider/js/deleteOffer", "id="+selectedOffer,
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
	alert("handledeleteOfferResponse!");
	if (mime == "text/error")
		alert(data);
	else if (mime == "text/url")
		window.location = data;
}

/**
 * Loads the selected offer's data and displays it, if selection is valid.
 */
function loadSelectedOfferEdit() {
	if (selectedOfferDocument == null) {
		toggleWarning("error_selection", true, "Kein Dokument ausgewaehlt! ");
		return;
	}
	connect("/hiwi/Provider/js/getOffer", "id=" + selectedOffer,
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
	alert("handleLoadEditOfferResponse");
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "text/error") {
		alert(data);
	} else if (mime == "offer/json") {
		//TODO write DB data in form
//		var adminDocument = eval("(" + data + ")");
//		editDocumentForm.uid.value = adminDocument.uid;
//		editDocumentForm.title.value = adminDocument.name;
//		editDocumentForm.description.value = adminDocument.description;
//		togglePopup('document_edit', true);
	}
}


/**
 * This function works with the response of the ProviderServlet to create an offer.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleCreateOfferResponse(mime, data) {
	// alert("ANTWORT VOM SERVLET ADD OFFER");
	if (mime == "text/url") { // im Servlet:
								// response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
								// veranlasst nach dem Anlegen eines neues Offer
								// Objekts die weiterleitung auf die hauptseite
								// des providers
		window.location = data;
		return;
	} else if (mime == "text/error") {
		alert(data);
		return;
	}
}
