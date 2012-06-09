/**
 * @author: Patryk Boczon
 * @author: Laura Irlinger
 * @author: Oemer Sahin
 * @author: Tamino Hartmann
 */

/**
 * Fragt mich nicht, warum das so funktioniert... :P
 */
function doExcelExport() {
	var elemIF = document.createElement("iframe");
	elemIF.src = "/hiwi/Clerk/js/doExcelExport";
	elemIF.style.display = "none";
	document.body.appendChild(elemIF);
}
/**
 * laedt die Informationen für die userindex seite
 */
function loadInfo(){
	connect("/hiwi/Clerk/js/loadInfo", "", handleLoadInfo);
}
/**
 * zeigt die Informationen an 
 */
function handleLoadInfo(mime, data){
	if(mime=="application/json"){
		var json = eval("("+data+")");
		document.getElementById("offers").innerHTML=""+json.offers;
		document.getElementById("apps").innerHTML=""+json.apps;
	}else
		alert("oh oh es ist ein fehler aufgetreten");
}

/**
 * Stores the selected Document:
 */
var selectedDocument;

/**
 * Stores the selected Offer:
 */
var selectedOffer;

/**
 * Stores the User in editapplication:
 */
var User;

/**
 * Stores the Offer in editapplication:
 */
var Aid;

/**
 * Stores the selected Document:
 */
var selectedDocument;

/**
 * This function loads all the offers of the applicant in the system from the
 * database and displays them.
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
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table2 = document.getElementById("clerkTable");
		// Write table – probably replaces old data!
		table2.innerHTML = "<tr><th>Name des Zuständigen</th><th>Angebot</th><th>Plätze</th><th>Stunden pro Woche</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table2.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].aid
					+ "\" onclick=\"markOfferSelected(\'" + JSONarray[i].aid
					+ "\');\"><td>" + JSONarray[i].author + "</td><td>"
					+ JSONarray[i].name + "</td><td>" + JSONarray[i].slots
					+ "</td><td>" + JSONarray[i].hoursperweek + "</td></tr>";
		}
	}
}

/**
 * Function to edit a single offer.
 */
function editOneOffer() {
	var aid = getURLParameter("AID");
	connect("/hiwi/Clerk/js/editOneOffer", "aid=" + aid,
			handleEditOneOfferResponse);
}

function handleEditOneOfferResponse(mime, data) {

	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "offers/json") {

		var offer = eval("(" + data + ")");
		var status;
		var angebotbestattribut;
		var angebotablattribut;
		if (offer.checked) {
			status = "bestätigt";
			angebotbestattribut = "disabled";
			angebotablattribut = "";
		} else {
			status = "abgelehnt";
			angebotbestattribut = "";
			angebotablattribut = "disabled";
		}

		var offertable = document.getElementById("offerinfotable");
		var angebotbestaetigenbutton = document
				.getElementById("angebotbestaetigen");
		var angebotablehnenbutton = document.getElementById("angebotablehnen");
		// TODO: Wieso wird hier die gesamte Tabelle in Strings gebaut wenn wir
		// einfach die Werte setzen könnten?
		var anbieternotiz = (offer.note == null || offer.note == "") ? "[Keine Notiz vorhanden.]"
				: offer.note;
		offertable.innerHTML = "<tr><td>Name des Veranstalters:</td>" + "<td>"
				+ offer.author + "</td></tr>"
				+ "<tr><td>Titel der Stelle:</td>" + "<td>" + offer.name
				+ "</td></tr>" + "<tr><td>Plätze:</td>" + "<td>" + offer.slots
				+ "</td></tr>" + "<tr><td>Stunden die Woche:</td>"
				+ "<td><input id=\"inputhoursperweek\" type=\"text\" value=\""
				+ offer.hoursperweek + "\" /> std. <div id=\"hours_error\"class=\"hiddenerror\"></div></td></tr>"
				+ "<tr><td>Lohn:</td>"
				+ "<td><input id=\"inputwage\" type=\"text\" value=\""
				+ offer.wage + "\" />€ <div id=\"gage_error\"class=\"hiddenerror\"></div></td></tr>"
				+ "<tr><td>Anbieternotiz:</td>"
				+ "<td style=\"background-color: lightgray;\">" + anbieternotiz
				+ "</td></tr>" + "<tr><td>Status:</td><td>" + status
				+ "</td></tr>";

		angebotbestaetigenbutton.disabled = angebotbestattribut;
		angebotablehnenbutton.disabled = angebotablattribut;

		document.getElementById("dokumentloeschenbutton").disabled = "disabled";

		documentsFromOffer();
	}
}

function angebotbestaetigen() {
	var aid = getURLParameter("AID");
	var hoursperweek = document.getElementById("inputhoursperweek").value;
	var wage = document.getElementById("inputwage").value;

	connect("/hiwi/Clerk/js/approveOffer", "aid=" + aid + "&hoursperweek="
			+ hoursperweek + "&wage=" + wage, handleEditOneOfferResponse);
}

function angebotspeichern() {
	var aid = getURLParameter("AID");
	var hoursperweek = document.getElementById("inputhoursperweek").value;
	var wage = document.getElementById("inputwage").value;
	var error =false;
    
	if (hoursperweek == null || hoursperweek == "") {
		toggleWarning("hours_error", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkInt(hoursperweek)) {
		toggleWarning("hours_error", true, "Bitte eine ganze Zahl angeben!");
		error = true;
	} 
	else
		toggleWarning("hours_error", false, "");
	if (wage == null || wage == "") {
		toggleWarning("gage_error", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkFloat(wage)) {
		toggleWarning("gage_error", true, "Bitte eine Zahl angeben!");
		error = true;
	} 
	else
		toggleWarning("gage_error", false, "");
	
	if(error)return;
	alert("Angebot aktualisiert:"+'\n'+"Stunden/Woche: "+hoursperweek+'\n'+"Lohn: "+wage);
	connect("/hiwi/Clerk/js/saveOffer", "aid=" + aid + "&hoursperweek="
			+ hoursperweek + "&wage=" + wage, handleEditOneOfferResponse);
}

function angebotablehnen() {
	var aid = getURLParameter("AID");

	connect("/hiwi/Clerk/js/rejectOffer", "aid=" + aid,
			handleEditOneOfferResponse);
}

function documentsFromOffer() {
	var aid = getURLParameter("AID");
	connect("/hiwi/Clerk/js/documentsFromOffer", "aid=" + aid,
			handledocumentsFromOfferResponse);
	// alert("ohne alert funzt es ned =( ");
}

function handledocumentsFromOfferResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "documentsoffer/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table = document.getElementById("documentsTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Benoetigte Documente</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].uid
					+ "\" onclick=\"markDocumentSelected(\'" + JSONarray[i].uid
					+ "\');\"><td>" + JSONarray[i].name + "</td></tr>";
		}

		// Zum Laden des DropDown-Menues des Pop-Ups bei "Dokument Hinzufuegen"
		var aid = getURLParameter("AID");
		connect("/hiwi/Clerk/js/documentsToAddToOffer", "aid=" + aid,
				handledocumentsToAddToOfferResponse);

	}
}

// Laedt die Dokumente in ein Drop Down Menue, welche nicht fuer das
// angezeigte Angebot benoetigt werden
function handledocumentsToAddToOfferResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "documentstoaddoffer/json") {

		var docs = eval("(" + data + ")");

		var select = document.getElementById("selectDocumentsToAdd");
		select.innerHTML = "";
		for ( var i = 0; i < docs.length; i++) {
			select.innerHTML += "<option value=\"" + docs[i].uid + "\">"
					+ docs[i].name + "</option>";
		}
	}

}

/**
 * Function updates the 'Angebot pruefen' button by setting its onclick
 * reference to the AID of the last marked offer (first if), or Function updates
 * the 'Bewerbung bearbeiten' button by setting its onclick reference to the
 * name of the applicant of the last marked offer (second if).
 * 
 */
function prepareButton() {
	// alert("preparing button3");

	if (document.getElementById("angebotpruefen") != null
			&& selectedOffer != null) { // offermanagement.jsp -->
		// editoffer.jsp, wenn etwas markiert
		// ist
		document.getElementById("angebotpruefen").onclick = function() {
			//wenn angebotpruefen geklickt und kein angebot selektiert
			if( selectedOffer==null){
				toggleWarning("error_noOfferSelected", true,
				"Kein Angebot selektiert!");
			}
			else{
				toggleWarning("error_noOfferSelected", false,
				"Kein Angebot selektiert!");
				window.location = 'editoffer.jsp?AID=' + selectedOffer;
			}
		};
	}	
	//wenn angebotpruefen geklickt und kein angebot selektiert
	
	// alert("preparing button");

	if (document.getElementById("editapplication") != null
			&& selectedOffer != null) { // applicationmanagement.jsp -->
		// editapplication.jsp, wenn etwas
		// markiert ist
		document.getElementById("editapplication").onclick = function() {
			var temp = selectedOffer.split("�%#%�");
			var user = temp[0];
			var aid = temp[1];
			window.location = 'editapplication.jsp?User=' + user + "&AID="
					+ aid;
		};
	}

}//end prepareButton

function deleteChosenDocument() {
	var aid = getURLParameter("AID");
	togglePopup('document_del', false);
	document.getElementById("dokumentloeschenbutton").disabled = "disabled";
	connect("/hiwi/Clerk/js/deleteOfferDocument", "uid=" + selectedDocument
			+ "&aid=" + aid, handleDocumentChangeResponse);
	selectedDocument = null;
	togglePopup("document_delete", false);
}

function addChosenDocument() {
	var aid = getURLParameter("AID");
	var selectedDocument = document.getElementById("selectDocumentsToAdd").value;

	document.getElementById("dokumentloeschenbutton").disabled = "disabled";
	connect("/hiwi/Clerk/js/addOfferDocument", "uid=" + selectedDocument
			+ "&aid=" + aid, handleDocumentChangeResponse);
	selectedDocument = null;
	togglePopup("document_add", false);
}

function handleDocumentChangeResponse(mime, data) {
	if (mime == "text/url")
		window.location = data;
	else if (mime == "text/error")
		alert(data);
	// Always reload document list AFTER the update on the servlet is done:
	else
		documentsFromOffer();
}

/**
 * Function remembers which document has been clicked.
 * 
 * @param id
 *            The ID of the clicked entry.
 */
//function markDocumentSelected(id) {
//
//	// alert("alte docid: "+selectedDocument);
//	// Remove marking from previous selected, if applicable:
//	if (selectedDocument != null)
//		document.getElementById(selectedDocument).setAttribute("class", "");
//	// If clicked again, unselect:
//	if (selectedDocument == id) {
//		selectedDocument = null;
//		document.getElementById("dokumentloeschenbutton").disabled = "disabled";
//		document.getElementById("dokumentHinzufuegenButton").disabled = "disabled";
//		return;
//	}
//	// Else save & mark new one:
//	selectedDocument = id;
//
//	
//	document.getElementById("dokumentloeschenbutton").disabled = "";
//	document.getElementById("dokumentHinzufuegenButton").disabled = "";
//	// alert("aktuelle docid: "+selectedDocument);
//
//	document.getElementById(id).setAttribute("class", "selected");
//
//
//	
//	// updating 'Angebot pruefen' button
//	prepareButton();
//
//}

/**
 * Function remembers which account has been clicked.
 * 
 * @param id
 *            The ID of the clicked entry.
 */
function markOfferSelected(id) {
	// alert("alte id: "+selectedOffer);
	// Remove marking from previous selected, if applicable:
	if (selectedOffer != null){
		document.getElementById(selectedOffer).setAttribute("class", "");
	}
	// If clicked again, unselect:
	if (selectedOffer == id) {
		selectedOffer = null;
		prepareButton();
		return;
	}
	// Else save & mark new one:
	selectedOffer = id;

	// ("aktuelle id: "+selectedOffer);

	document.getElementById(id).setAttribute("class", "selected");

	// updating 'Angebot pruefen' button
	prepareButton();

}

/**
 * Entfernt alle Eintraege.
 */
function clearAddDocumentPopup() {
	addDocumentForm.uid.value = "";
	addDocumentForm.title.value = "";
	addDocumentForm.description.value = "";
	toggleWarning("error_addDocument_uid", false, "");
	toggleWarning("error_addDocument_descr", false, "");
	toggleWarning("error_addDocument_title", false, "");
}
/**
 * Deletes a document if one is selected.
 */
function deleteDocument() {
	if (selectedDocument == null) {
		toggleWarning("error_selection", true, "Kein Dokument ausgewählt! ");
		togglePopup("document_del", false);
		
		return;
	}
	// alert("/hiwi/Admin/js/deleteDocument?uid=" + selectedDocument);
	connect("/hiwi/Clerk/js/deleteDocument", "uid=" + selectedDocument,
			handleDeleteDocumentResponse);
}

/**
 * Deletes a document from an application
 */
function deleteAppDocument(){
	if(selectedDocument != null){
		//alert("to delete: "+selectedDocument);
		togglePopup('document_del', false);
		connect("/hiwi/Clerk/js/deleteAppDocument","uid=" + selectedDocument + "&user="+User+"&aid="+Aid, deleteAppDocumentResponse);
	}
	
}

function deleteAppDocumentResponse(){
	document.getElementById("dokumentloeschenbutton").disabled = "disabled";
	selectedDocument = null;
	applicationDocuments();
	//TODO Dokument l�schen button ausgrauen
}

/**
 * Handles the response to a deletion request.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleDeleteDocumentResponse(mime, data) {
	if (mime == "text/error")
		alert(data);
	else if (mime == "text/url")
		window.location = data;
}

function createDocument() {
	
	var form = document.getElementById("adddocform");
	if (form == null)
		return;
	var error = false;
//	var uid = form.uid.value;
//	if (uid == null || uid == "") {
//		toggleWarning("error_addDocument_uid", true, "Bitte ausfüllen!");
//		error = true;
//	} else
//		toggleWarning("error_addDocument_uid", false, "");
	var title = form.title.value;
	if (title == null || title == "") {
		toggleWarning("error_addDocument_title", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_addDocument_title", false, "");
	var description = form.description.value;
	if (description == null || description == "") {
		toggleWarning("error_addDocument_descr", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_addDocument_descr", false, "");
	if (error)
		return;
	
	connect("/hiwi/Clerk/js/createDocument", /*"uid=" + uid + "&*/"title=" + title
			+ "&description=" + description, applicationDocuments);
}



function addDocument(){
	
	var chosenDocument = document.getElementById("selectAppDocumentsToAdd").value;
	
	document.getElementById("dokumentloeschenbutton").disabled = "disabled";
	selectedDocument = null;
	togglePopup("document_add", false);
	
	connect("/hiwi/Clerk/js/addAppDocument", "uid=" + chosenDocument+"&aid="+Aid+"&username="+User, applicationDocuments);

}


function handleAddDocumentResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/error") {
		alert(data);
	}
}

/**
 * This function loads all the applicants of all offers from the clerk's
 * institute in the system from the database and displays them.
 * (applicationmanagement.jsp)
 */
function showApplication() {
	selectedOffer = null;
	connect("/hiwi/Clerk/js/showApplication", "", handleShowApplicationResponse);
}

/**
 * This function displays all the applicants of all offers from the clerk's
 * institute in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleShowApplicationResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "showapplication/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table2 = document.getElementById("applicationTable");
		// Write table – probably replaces old data!
		table2.innerHTML = "<tr><th>Benutzername des Bewerbers</th><th>Bewirbt sich fuer</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table2.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].username
					+ "�%#%�" + JSONarray[i].aid
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].username + "�%#%�" + JSONarray[i].aid
					+ "\');\"><td>" + JSONarray[i].username + "</td><td>"
					+ JSONarray[i].angebotsname + "</td></tr>";
		}
	}
} // --> preparing Button --> editapplication.jsp --> applicationDocuments()

/**
 * This function loads all the documents of the chosen application in the system
 * from the database and displays them. (editapplication.jsp)
 */
function applicationDocuments() {
	User = getURLParameter("User");
	Aid = getURLParameter("AID");
	selectedOffer = null;
	selectedDocument = null;
	connect("/hiwi/Clerk/js/applicationDocuments", "User=" + User + "&AID="
			+ Aid, handleApplicationDocumentsResponse);
}

/**
 * This function displays all the documents of the chosen application in the
 * system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleApplicationDocumentsResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "showthedocuments/json") {
		
		document.getElementById("dokumentloeschenbutton").disabled = "disabled";
		
		// Erstelle Array aus JSON array:
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table2 = document.getElementById("documentsTable");
		// Betweensaves the 'present' value of the AppDocument element
		var checked;
		// Write table – probably replaces old data!
		table2.innerHTML = "<tr><th>Dokumente</th></tr>";
		for ( var i = 0; i < JSONarray.length; i=i+2) {
			if(JSONarray[i+1].present){
				checked = "checked";
			}else{
				checked = "";
			}
			table2.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].uid+ "\" onclick=\"setDocCheck('"+JSONarray[i+1].username+"',"+JSONarray[i+1].docID+","+JSONarray[i+1].offerID+")\" "+checked+"><td>"
					+ JSONarray[i].name + "</td></tr>";
		}
		showApplicationTable2();
		//alte vVersion mit checkbox
//		for ( var i = 0; i < JSONarray.length; i=i+2) {
//			if(JSONarray[i+1].present){
//				checked = "checked";
//			}else{
//				checked = "";
//			}
//			table2.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].uid+ "\" onclick=\"markDocumentSelected(\'"
//					+ JSONarray[i].uid 	+ "\');\"><td><input type=\"checkbox\" onclick=\"setDocCheck('"+JSONarray[i+1].username+"',"+JSONarray[i+1].docID+","+JSONarray[i+1].offerID+")\" "+checked+"></td><td>"
//					+ JSONarray[i].name + "</td></tr>";
//		}
//		showApplicationTable2();
	}
}




//Laedt die Dokumente in ein Drop Down Menue, welche nicht fuer die
//angezeigte Bewerbung benoetigt werden
function handledocumentsToAddToAppResponse(mime, data) {

	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "docstoaddtoapp/json") {

		var docs = eval("(" + data + ")");

		var select = document.getElementById("selectAppDocumentsToAdd");
		select.innerHTML = "";
		for ( var i = 0; i < docs.length; i++) {
			select.innerHTML += "<option value=\"" + docs[i].uid + "\">"
					+ docs[i].name + "</option>";
		}
	}

}

/**
 * This function forwards the parameters values to the ClerkServlet, where
 * the AppDocument, linked to those values, will be overwritten 
 * @param username
 * @param docid
 * @param offerid
 */
function setDocCheck(username, docid, offerid){
	//urspr�nglich nur die connect --> aber f�r Markierung das hinzugefuegt!!!
	if (selectedDocument != null)
	document.getElementById(selectedDocument).setAttribute("class", "");
	//alert("man");
	// If clicked again, unselect:
	if (selectedDocument == docid) {
	selectedDocument = null;
	//alert("man oh man");
	document.getElementById("dokumentloeschenbutton").disabled = "disabled";
	document.getElementById("dokumentHinzufuegenButton").disabled = "disabled";
	return;
	}
	// Else save & mark new one:
	//alert("hier");
	selectedDocument = docid;
	//alert("daa");
	//alert("aktuelle id: "+selectedDocument);
	document.getElementById(docid).setAttribute("class", "selected");
	
	document.getElementById("dokumentloeschenbutton").disabled = "";
	document.getElementById("dokumentHinzufuegenButton").disabled = "";
	
	prepareButton();
	
	connect("/hiwi/Clerk/js/setDocCheck", "username=" + username + "&docid="+ docid +"&offerid="+offerid, null);
}

/**
 * This function loads the applicantname and the offername in the system from
 * the database and displays them. (editapplication.jsp)
 */
function showApplicationTable2() {
	var User = getURLParameter("User");
	var Aid = getURLParameter("AID");
	selectedOffer = null;
	connect("/hiwi/Clerk/js/showApplicationTable2", "User=" + User + "&AID="
			+ Aid, handleShowApplicationTable2Response);
}

/**
 * This function displays the applicantname and the offername in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleShowApplicationTable2Response(mime, data) {
	
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "showapplicationtable2/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval("(" + data + ")");
		// Get the table:
		var table2 = document.getElementById("applicantTable");
		// Write table – probably replaces old data!
		table2.innerHTML = "<tr><td>Name:</td></tr><tr><td>" + JSONarray[0]
				+ "</td></tr><tr><td>Beworben fuer:</td></tr><tr><td>"
				+ JSONarray[1] + "</td></tr>";
	}
	
	connect("/hiwi/Clerk/js/documentsToAddToApplication", "aid=" + Aid+"&username="+User,
			handledocumentsToAddToAppResponse);
}

/**
 * This function checks if a applicant has delivered all of the rquired
 * documents.
 */

function doApplicationCompletion() {

	connect("/hiwi/Clerk/js/doApplicationCompletion", "AID=" + aid
			+ "&username=" + username, handleApplicationCompletion);
}

function handleApplicationCompletion(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "error/url") {
		alert(data);
		toggleWarning(id, true,
				"Unvollständige Dokumente. Abschluss nicht möglich");
	} else if (mime == "text/error") {
		alert(data);
	}
}