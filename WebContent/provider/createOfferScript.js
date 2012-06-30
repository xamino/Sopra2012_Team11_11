/**
 * Loads the default values from the database.
 */
function loadDefValues() {
	connect("/hiwi/Provider/js/getDefValues", "", handleLoadResponse);
}

/**
 * Handles the response from loadDefValues. If correct values are sent, fills
 * them into the form at the correct locations.
 * 
 * @param mime
 *            The MIME-type of the data.
 * @param data
 *            The data.
 */
function handleLoadResponse(mime, data) {
	if (mime == "text/url")
		window.location = data;
	else if (mime == "text/error")
		alert(data);
	else if (mime == "application/json") {
		// alert(data);
		var obj = eval("(" + data + ")");
		angebotErstellen.std.value = obj.hoursMonth;
		angebotErstellen.startDate.value = obj.startDate;
		angebotErstellen.endDate.value = obj.endDate;
	}
}

/**
 * This function adds an new Offer from the provider account in createoffer.jsp
 * 
 * @param form
 *            is the HTML formular which is being filled with significant values
 *            of an offer-object.
 */
function addOffer(form) {
	if (form == null)
		return;
	var error = false;

	var titel = form.titel.value;
	if (titel == null || titel == "") {
		toggleWarning("error_titel", true, "Bitte ausfuellen!");
		error = true;
	} else if (!checkText(titel)) {
		toggleWarning("error_titel", true, "Unerlaubtes Sonderzeichen!");
	} else
		toggleWarning("error_titel", false, "");

	var std = form.std.value;
	if (std == null || std == "") {
		toggleWarning("error_std", true, "Bitte ausfuellen!");
		error = true;
	} else if (!checkInt(std)) {
		toggleWarning("error_std", true, "Bitte nur ganze Zahlen!");
		error = true;
	} else
		toggleWarning("error_std", false, "");

	var stellen = form.stellen.value;
	if (stellen == null || stellen == "") {
		toggleWarning("error_stellen", true, "Bitte ausfuellen!");
		error = true;
	} else if (!checkInt(stellen)) {
		toggleWarning("error_stellen", true, "Bitte nur ganze Zahlen!");
		error = true;
	} else
		toggleWarning("error_stellen", false, "");

	var startDate = form.startDate.value;
	if (startDate == null || startDate == "") {
		toggleWarning("error_startDate", true, "Bitte ausfuellen!");
		error = true;
	} else if (!checkText(startDate)) {
		toggleWarning("error_startDate", true, "Bitte nur ganze Zahlen!");
		error = true;
	} else
		toggleWarning("error_startDate", false, "");

	var endDate = form.endDate.value;
	if (endDate == null || endDate == "") {
		toggleWarning("error_endDate", true, "Bitte ausfuellen!");
		error = true;
	} else if (!checkText(endDate)) {
		toggleWarning("error_endDate", true, "Bitte nur ganze Zahlen!");
		error = true;
	} else
		toggleWarning("error_endDate", false, "");

	var beschreibung = form.beschreibung.value;
	if (beschreibung == null || beschreibung == "") {
		toggleWarning("error_beschreibung", true, "Bitte ausfuellen!");
		error = true;
	} else if (!checkText(beschreibung)) {
		toggleWarning("error_beschreibung", true, "Unerlaubtes Sonderzeichen!");
		error = true;
	} else {
		toggleWarning("error_beschreibung", false, "");
	}
	var notiz = form.notiz.value;
	if (notiz == null || notiz == "") {
		toggleWarning("error_notiz", true, "Bitte ausfuellen!");
		error = true;
	} else if (!checkText(notiz)) {
		toggleWarning("error_notiz", true, "Unerlaubtes Sonderzeichen!");
		error = true;
	} else
		toggleWarning("error_notiz", false, "");
	if (error)
		return;
	// alert("Sending...");
	connect("/hiwi/Provider/js/addOffer", "titel=" + titel + "&std=" + std
			+ "&stellen=" + stellen + "&beschreibung=" + beschreibung
			+ "&notiz=" + notiz + "&startDate=" + startDate + "&endDate="
			+ endDate, handleCreateOfferResponse);
}

/**
 * This function works with the response of the ProviderServlet to create an
 * offer.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleCreateOfferResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/error") {
		// TODO: hier fehler als html errormessage einbauen ?? Echt? Sind doch
		// alle da... (Tamino)
		// alert("HTML ERRORMESSAGE für die falscheingabe fehler!");
		alert(data);
		return;
	}
}