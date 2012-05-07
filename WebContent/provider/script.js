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
 * Wird in function markOfferSelected(id) initialisiert und
 * braucht man, damit man auf den username des bewerbers fuer die berwerberauswahl in function takeSelectedApplicant() zugreifen kann
 */
var tmpApplicantUserName;

/**
 * This function loads all the offers in the system from the database and
 * displays them.
 */

function loadOffers() {															//ohne alerts funktionierts nicht =( ... wieso??
	// reset selectedID (account could have been deleted in meantime)
	//selectedOffer = null;
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
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].aid+ "\"><td>"
					+ JSONarray[i].name + "</td><td><br><input id=\""+JSONarray[i].aid+"\" type=\"button\" value=\"Bewerberauswahl\"  onclick=\"prepareButton(\'"
					+ JSONarray[i].aid + "\');\"/></td><td><br><input type=\"submit\" value=\"Angebot aendern\" onclick=\"prepareButtonUpdateOffer(\'"
					+ JSONarray[i].aid + "\');\"/></td><td><br><input type=\"button\" value=\"Angebot zurueckziehen\" onclick=\"prepareButtonDeleteOffer(\'"
					+ JSONarray[i].aid + "\');\" /> </td></tr>";
		}
	}
}
//Anzahl der Bewerber ... wie implementieren??

/**
 * Function updates the 'Bewerberauswahl' button by setting its onclick reference
 * to the AID of the last marked offer.
 * 
 */
//Button Bewerbauswahl
function prepareButton(id)
{
	//alert("preparing button3");
	if (document.getElementById(id) != null){		//userindex.jsp --> applicantlist.jsp, wenn Button gedr�ckt wurde
		//alert("idneuneu= "+id);
		document.getElementById(id).onclick = function(){
			window.location='applicantlist.jsp?AID='+id;
	    };
    }
}

/**
 * Function updates the 'Bewerberauswahl' button by setting its onclick reference
 * to the AID of the last marked offer.
 * 
 */
function prepareButtonUpdateOffer(aid)
{
	offerToUpdate = aid;
	//alert("prepareButtonUpdateOffer "+offerToUpdate);
	window.location='editoffer.jsp?aid='+aid;
	
}

/**
 * Function updates the 'Bewerberauswahl' button by setting its onclick reference
 * to the AID of the last marked offer.
 * 
 */
function prepareButtonDeleteOffer(aid)
{
	offerToDelete = aid;
	alert(offerToDelete);
	togglePopup('offer_cancel',true);

}

/**
 * This function loads all the applicants from one offer in the system from the database and
 * displays them.
 */
function applicantChoice(){
	var aid = getURLParameter("AID"); //
	//alert("id= "+aid);
	// reset selectedID (account could have been deleted in meantime)
	selectedOffer = null;
	connect("/hiwi/Provider/js/applicantChoice", "aid="+aid, handleApplicantChoiceResponse);
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
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table = document.getElementById("applicantsTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Name:</th><th>Hier fehlen weiter Daten (nicht in DB)</th></tr>";
		/* orginal von lau lau:
		 * for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].name
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].name + "\');\"><td>"
					+ JSONarray[i].name + "</td></tr>";
		brauche username f�r berwerber annehmen
		}
		 * */
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].username
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].username + "\');\"><td>"
					+ JSONarray[i].name + "</td></tr>";
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
	
	tmpApplicantUserName =id; //braucht man, damit man auf den username des bewerbers fuer die berwerberauswahl in function takeSelectedApplicant() zugreifen kann
	
	//alert("alte id: "+selectedOffer);
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

	//alert("aktuelle id: "+selectedOffer);

	document.getElementById(id).setAttribute("class", "selected");
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
	is(!error)connect("/hiwi/Provider/js/addOffer", "titel=" + titel + "&std=" + std
			+ "&stellen=" + stellen + "&beschreibung=" + beschreibung
			+ "&notiz=" + notiz, handleCreateOfferResponse);
}


/**
 * Deletes an offer if one is selected.
 */
function deleteOffer() {
	alert("delete Offer in progress");
//	if (selectedOffer == null) {
//		toggleWarning("error_selection", true, "Kein Angebot ausgewaehlt! ");
//		togglePopup("offer_del", false);
//		return;
//	}
	alert("id="+offerToDelete);
	connect("/hiwi/Provider/js/deleteOffer", "aid="+offerToDelete,
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
	//alert("handledeleteOfferResponse!");
	if (mime == "text/error")
		alert(data);
	else if (mime == "text/url")
		window.location = data;
}

/**
 * Loads the selected offer's data and displays it in editoffer.jsp?aid=....
 */
function loadSelectedOfferEdit() {
	
	offerToUpdate= getURLParameter("aid");
	//alert(offerToUpdate);
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
	//alert("handleLoadEditOfferResponse");
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
		// hier fehler als html errormessage einbauen
		alert("TODO: HTML ERRORMESSAGE für die falscheingabe fehler!");
		return;
	}
}

/**
 * This function updates an offer.
 */

function updateOfferChanges(form) {
	//alert("UpdateOfferChanges aktiviert");
	
	offerToUpdate = getURLParameter("aid");
	if (form == null)
		return;
	var error = false;
	var titelFeld = form.titelFeld.value;
	
	if (titelFeld == null || titelFeld == "") {
		toggleWarning("error_titelFeld", true, "Bitte ausfuellen!");
		error = true;
	} else
		toggleWarning("error_titelFeld", false, "");
	
	var beschreibungsFeld = form.beschreibungsFeld.value;
	
	if (beschreibungsFeld == null || beschreibungsFeld == "") {
		toggleWarning("error_beschreibungsFeld", true, "Bitte ausfuellen!");
		error = true;
	} else
		toggleWarning("error_beschreibungsFeld", false, "");
	if (error){
		return;
	}
		
	connect("/hiwi/Provider/js/updateOffer","aid="+offerToUpdate+ "&titel=" + titelFeld + "&beschreibung=" + beschreibungsFeld, handleUpdateOfferChangesResponse);
	
}

function handleUpdateOfferChangesResponse(mime, data) {
	//alert("Servlet update response");
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
	
	var username = tmpApplicantUserName; // wird in function markOfferSelected(id) initialisiert
	
	//alert("username: "+username);
	
	connect("/hiwi/Provider/js/takeSelectedApplicant","aid="+offerToApplicant+"&usernameTakenApplicant="+username, handleTakeSelectedApplicantResponse);
	
}

/**
 * This function works with the response of the ProviderServlet. Operation: select an applicant for an offer.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleTakeSelectedApplicantResponse(mime, data) {
	//alert("Applicant selected funzt net weil ich net weiss, wie ich auf den Benutzernamen vom selektierten Bewerber zugreifen soll.");
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/error") {
		alert(data);
		return;
	}
}


