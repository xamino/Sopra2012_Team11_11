/**
 * @author: Patryk Boczon
 * @author: Laura Irlinger
**/

//!!!!!!!ohne alerts funktionierts nicht =( also drin lassen!!!!... weiß jmd wieso??!!!!!!!!!!!!

function doExcelExport() {
	connect("/hiwi/Clerk/js/doExcelExport", "", handleExport);
}

function handleExport(mime, data) {
	if (mime == "text/url")
		window.location = data;
}

/**
 * Stores the selected Offer:
 */
var selectedOffer;

/**
 * This function loads all the offers of the applicant in the system from the database and
 * displays them.
 */
function showMyOffers() {
	// reset selectedID (account could have been deleted in meantime)
	selectedOffer = null;
	prepareButton();
	connect("/hiwi/Clerk/js/showMyOffers", "", handleShowMyOffersResponse);
}



/**
 * This function displays all the offers of the clerk in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleShowMyOffersResponse(mime, data) {

	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "offers/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table2 = document.getElementById("clerkTable");
		// Write table â€“ probably replaces old data!
		table2.innerHTML = "<tr><th>Name des Zuständigen</th><th>Angebot</th><th>Plätze</th><th>Stunden pro Woche</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table2.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].aid
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].aid + "\');\"><td>" 
					+ JSONarray[i].author + "</td><td>"
					+ JSONarray[i].name + "</td><td>"
					+ JSONarray[i].slots + "</td><td>"
					+ JSONarray[i].hoursperweek + "</td></tr>";
		}
	}
}


function editOneOffer() {
	var aid = getURLParameter("AID");
	alert(aid);
	connect("/hiwi/Clerk/js/editOneOffer", "aid="+aid, handleEditOneOfferResponse);
}


function handleEditOneOfferResponse(mime, data){
	var aid = getURLParameter("AID");
	alert("handleEditOneOfferResponse "+aid);
	
	if (mime == "text/url") {
		window.location = data;
	}
	else if (mime == "offers/json") {
		/*
		 * Attribute der Offers mit 'aid' anzeigen
		 */ 
		documentsFromOffer();
	}
}

function documentsFromOffer(){
	var aid = getURLParameter("AID");
	connect("/hiwi/Clerk/js/documentsFromOffer", "aid="+aid, handledocumentsFromOfferResponse);
	alert("ohne alert funzt es ned =( ");
}


function handledocumentsFromOfferResponse(mime, data){
	//alert("drin");
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "documentsoffer/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table = document.getElementById("documentsTable");
		// Write table â€“ probably replaces old data!
		table.innerHTML = "<tr><th>Benoetigte Documente</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i]
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i] + "\');\"><td>" +    JSONarray[i]  + "</td><br></tr>";
		}
	}
}

/**
 * Function updates the 'Angebot pruefen' button by setting its onclick reference
 * to the AID of the last marked offer
 * 
 */
function prepareButton()
{
	alert("preparing button");
    document.getElementById("angebotpruefen").onclick = function(){
        window.location='editoffer.jsp?AID='+selectedOffer;
    }
}

function angebotbestaetigen(){
	var aid = getURLParameter("AID");
	alert("Angebot "+aid+" bestaetigen");
	connect("/hiwi/Clerk/js/approveOffer", "aid="+aid, null);
}

function angebotablehnen(){
	var aid = getURLParameter("AID");
	alert("Angebot "+aid+" ablehnen");
	connect("/hiwi/Clerk/js/rejectOffer", "aid="+aid, null);
}

/**
 * Function remembers which account has been clicked.
 * 
 * @param username
 *            The username ID of the clicked entry.
 */
function markOfferSelected(id) {
	alert("alte id: "+selectedOffer);
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

	alert("aktuelle id: "+selectedOffer);

	document.getElementById(id).setAttribute("class", "selected");
	
	//updating 'Angebot pruefen' button
	prepareButton();

}
