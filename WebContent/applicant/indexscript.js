/**
 * Script for applicant webpages.
 * 
 * @author: Laura Irlinger
 * @author: Oemer Sahin
 */

/**
 * This variable stores which offer has been selected to apply to.
 */
var selectedOfferToApply;

/**
 * This function loads all the offers in the system from the database and
 * displays them. (userindex.jsp)
 */
function loadOffers() {//getestet durch Anatoli Brill
	// reset selectedID (account could have been deleted in meantime)
	selectedOffer = null;
	connect("/hiwi/Applicant/js/loadOffers", "", handleLoadOffersResponse);
	// alert("ohne alert funzt es ned =( 2");
}

/**
 * This function displays all the offers in the system. (userindex.jsp)
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadOffersResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "application/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table = document.getElementById("offerTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Beginn</th><th>Bezeichnung</th><th>Beschreibung</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\""
					+ JSONarray[i].aid
					+ "\"><td>"
					+ JSONarray[i].startdate
					+ "</td><td>"
					+ JSONarray[i].name
					+ "</td><td><div class=\"float2\">"
					+ JSONarray[i].description
					+ "</div><div class=\"float\"><input type=\"button\" value=\"Bewerben\"	onclick=\"prepareApply('"
					+ JSONarray[i].aid
					+ "')\" /> </div><div class=\"clear\"></div></td></tr>";
		}
		// loadMyOffers();
	}
}

/**
 * This function loads all the offers of the applicant (who is logged in) in the
 * system from the database and displays them. (userindex.jsp)
 */
function loadMyOffers() {//etestet durch Anatoli Brill
	connect("/hiwi/Applicant/js/loadMyOffers", "", handleLoadMyOffersResponse);
}

/**
 * This function displays all the offers of the applicant in the
 * system.(userindex.jsp)
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadMyOffersResponse(mime, data) {
	// alert("ohne alert funzt es ned =(");
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "myapplication/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table2 = document.getElementById("myofferTable");
		// Write table – probably replaces old data!
		table2.innerHTML = "<tr><th>Beginn</th><th>Bezeichnung</th><th>Beschreibung</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table2.innerHTML += "<tr class=\"\" id=\""
					+ JSONarray[i].aid
					+ "\"><td>"
					+ JSONarray[i].startdate
					+ "</td><td>"
					+ JSONarray[i].name
					+ "</td><td><div class=\"float2\">"
					+ JSONarray[i].description
					+ "</div><div class=\"float\"><input type=\"submit\" value=\"Bewerbung ansehen\" id=\""
					+ JSONarray[i].aid + "\"onclick=\"selectApplication2(\'"
					+ JSONarray[i].aid
					+ "\');\" \></div><div class=\"clear\"></div></td></tr>";
		} // �ber die onclick methode wird mit der id zur status.jsp weiter
		// geleitet
		loadOffers();
	}
}

/**
 * Function sends aid to status.jsp. (Button "Bewerbung ansehen"--> status.jsp)
 * 
 * @param id
 *            The aid of the clicked entry.
 */
function selectApplication2(aid) {
	window.location = "status.jsp?AID=" + aid;
}

/**
 * This function displays the name about the clicked application in the system.
 * (status.jsp)
 * 
 * @param id
 *            The aid of the clicked entry.
 */
function selectApplication() {
	var id = getURLParameter("AID");
	// reset selectedID (account could have been deleted in meantime)
	// selectedOffer = null;
	connect("/hiwi/Applicant/js/selectApplication", "id=" + id,
			handleLoadMyApplicationResponse);
}

/**
 * This function displays the name about one application in the system.
 * (status.jsp)
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadMyApplicationResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "offer/json") {
		// alert(data);
		// Erstelle Array aus JSON array:
		// var JSONarray = eval("("+data+")");
		// Get the table:
		var table = document.getElementById("applications");
		// Write table – probably replaces old data!
		table.innerHTML = "<h4>Bewerbung für " + data + "</h4>";
		selectDocuments();
	}
}

/**
 * This function displays the documents of the clicked application in the
 * system. (status.jsp)
 * 
 * @param id
 *            The aid of the clicked entry.
 */
function selectDocuments() {
	var id = getURLParameter("AID");
	// alert("die id ist"+id);
	// reset selectedID (account could have been deleted in meantime)
	// selectedOffer = null;
	// alert("ohne alert funzt es ned =( ");
	connect("/hiwi/Applicant/js/selectDocuments", "id=" + id,
			handleselectDocumentsResponse);
	// alert("ohne alert funzt es ned =( 2");
}

/**
 * This function displays the documents of one application in the system.
 * (status.jsp)
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleselectDocumentsResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "application/json") {
		// alert("richtig="+data);
		// data=[{"offerID":101,"documentID":2},{"offerID":101,"documentID":5}]
		// //richtig
		// Erstelle Array aus JSON array:
		var JSONarray = eval("(" + data + ")");
		// alert("data= "+JSONarray);
		// Get the table:
		var table2 = document.getElementById("applicationsTable");
		// Write table – probably replaces old data!
		table2.innerHTML = "<th>Status</th><th>Unterlage</th>";
		for ( var i = 0; i < JSONarray.length; i++) {
			var isChecked;
			if (JSONarray[i].isChecked == 0)
				isChecked = "Fehlt";
			else
				isChecked = "Vorhanden";
			table2.innerHTML += "<tr><td>" + isChecked + "</td><td>"
					+ JSONarray[i].name + "</td>";
		}
	}
}

/**
 * This function deletes an application which the applicant wants to abandon in the system.
 */
function deleteApplication() {
	// alert("deleteApplication");
	var aidToDelete = getURLParameter("AID");
	connect("/hiwi/Applicant/js/deleteApplication", "AID="+ aidToDelete, handleDeleteApplication);

}

/**
 * This function handles the response of the ApplicantServlet after deleteing an application
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleDeleteApplication(mime, data) {
	if (mime == "text/error")
		alert(data);
	else if (mime == "text/url")
		window.location = data;
}

function prepareApply(aid) {
	// alert(aid);
	selectedOfferToApply = aid;
	togglePopup('application', true);
}

/**
 * Funktion zum Bewerben auf ein Angebot
 */
function apply() {
	if (selectedOfferToApply == null || selectedOfferToApply == "")
		return;
	// alert(selectedOfferToApply);
	connect("/hiwi/Applicant/js/apply", "aid=" + selectedOfferToApply,
			handleApply);
}
/**
 * Callback funktion von apply
 * 
 * @param mime
 *            MimeType der Antwort
 * @param data
 *            Antwortdaten
 */
function handleApply(mime, data) {
	if (mime == "text/url")
		window.location = data;
	else
		alert("Es ist ein Fehler beim Bewerben aufgetreten!");
}