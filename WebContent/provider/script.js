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
					+ JSONarray[i].name + "</td><td>hier fehlt die Anzahl!!<br><input id=\""+JSONarray[i].aid+"\" type=\"button\" value=\"Bewerberauswahl\"  onclick=\"prepareButton(\'"
					+ JSONarray[i].aid + "\');\"/></td><td><br><input type=\"submit\" value=\"Angebot aendern\" onclick=\"window.location='editoffer.jsp'\"/></td><td><br><input type=\"button\" value=\"Angebot zurueckziehen\" onclick=\"togglePopup('offer_cancel',true);\" /> </td></tr>";
		}
	}
}

/**
 * Function updates the 'Bewerberauswahl' button by setting its onclick reference
 * to the AID of the last marked offer.
 * 
 */
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
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].name
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].name + "\');\"><td>"
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

	//("aktuelle id: "+selectedOffer);

	document.getElementById(id).setAttribute("class", "selected");
}