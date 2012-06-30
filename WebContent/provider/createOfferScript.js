/**
 * TODO!
 */
function loadDefValues() {

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
			+ "&notiz=" + notiz, handleCreateOfferResponse);
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
	// alert("ANTWORT VOM SERVLET ADD OFFER");
	if (mime == "text/url") { // im Servlet:
		// response.getWriter().write(Helper.D_PROVIDER_USERINDEX);
		// veranlasst nach dem Anlegen eines neues Offer
		// Objekts die weiterleitung auf die hauptseite
		// des providers
		window.location = data;
		return;
	} else if (mime == "text/error") {
		// hier fehler als html errormessage einbauen
		alert("TODO: HTML ERRORMESSAGE für die falscheingabe fehler!");
		return;
	}
}