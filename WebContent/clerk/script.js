/**
 * @author: Patryk Boczon
 * @author: Laura Irlinger
**/

//!!!!!!!ohne alerts funktionierts nicht =( also drin lassen!!!!... weiï¿½ jmd wieso??!!!!!!!!!!!!

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
 * Stores the selected Document:
 */
var selectedDocument;

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
		table2.innerHTML = "<tr><th>Name des Zustï¿½ndigen</th><th>Angebot</th><th>Plï¿½tze</th><th>Stunden pro Woche</th></tr>";
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

/**
 * Function to edit a single offer.
 */
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
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].uid
					+ "\" onclick=\"markDocumentSelected(\'"
					+ JSONarray[i].uid + "\');\"><td>" +    JSONarray[i].name  + "</td><br></tr>";
		}
		
		//Zum Laden des DropDown-Menues des Pop-Ups bei "Dokument Hinzufuegen"
		var aid = getURLParameter("AID");
		connect("/hiwi/Clerk/js/documentsToAddToOffer", "aid="+aid, handledocumentsToAddToOfferResponse);
		
	}
}

//Laedt die Dokumente in ein Drop Down Menue, welche nicht fuer das
//angezeigte Angebot benoetigt werden
function handledocumentsToAddToOfferResponse(mime, data){

	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "documentstoaddoffer/json") {
		
		var docs = eval("("+data+")");
		
		var select = document.getElementById("selectDocumentsToAdd");
		select.innerHTML = "";
		for ( var i = 0; i < docs.length; i++) {
			select.innerHTML += "<option value=\""+docs[i].uid+"\">"+docs[i].name+"</option>";
		}
	}
	
}
	

/**
 * Function updates the 'Angebot pruefen' button by setting its onclick reference
 * to the AID of the last marked offer (first if), or
 *  Function updates the 'Bewerbung bearbeiten' button by setting its onclick reference
 * to the name of the applicant of the last marked offer (second if).
 * 
 */
function prepareButton()
{
	//alert("preparing button3");

	if (document.getElementById("angebotpruefen") != null && selectedOffer != null){		//offermanagement.jsp --> editoffer.jsp, wenn etwas markiert ist
	    document.getElementById("angebotpruefen").onclick = function(){
	        window.location='editoffer.jsp?AID='+selectedOffer;
	    };
    }

    //alert("preparing button");

    if(document.getElementById("editapplication")!=null && selectedOffer != null){	//applicationmanagement.jsp --> editapplication.jsp, wenn etwas markiert ist
	    document.getElementById("editapplication").onclick = function(){
	    	var temp = selectedOffer.split("§%#%§");
	    	var user = temp[0];
	    	var aid = temp[1];
	        window.location='editapplication.jsp?User='+user+"&AID="+aid;
	    };
    }

}


function deleteChosenDocument(){
	var aid = getURLParameter("AID");
	alert("delete Document: "+selectedDocument+" from Offer "+aid);
	connect("/hiwi/Clerk/js/deleteOfferDocument", "uid="+selectedDocument+"&aid="+aid, null);
	selectedDocument = null;
	documentsFromOffer();
}

function addChosenDocument(){
	var aid = getURLParameter("AID");
	var selectedDocument = document.getElementById("selectDocumentsToAdd").value;
	
	alert(selectedDocument);
	
//	alert("add Document: "+selectedDocument+" from Offer "+aid);
	connect("/hiwi/Clerk/js/addOfferDocument", "uid="+selectedDocument+"&aid="+aid, null);
	selectedDocument=null;
	documentsFromOffer();
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
 * Function remembers which document has been clicked.
 * 
 * @param id
 *            The ID of the clicked entry.
 */
function markDocumentSelected(id) {
	alert("alte docid: "+selectedDocument);
	// Remove marking from previous selected, if applicable:
	if (selectedDocument != null)
		document.getElementById(selectedDocument).setAttribute("class", "");
	// If clicked again, unselect:
	if (selectedDocument == id) {
		selectedDocument = null;
		return;
	}
	// Else save & mark new one:
	selectedDocument = id;

	alert("aktuelle docid: "+selectedDocument);

	document.getElementById(id).setAttribute("class", "selected");
	
	//updating 'Angebot pruefen' button
	prepareButton();

}


/**
 * Function remembers which account has been clicked.
 * 
 * @param id
 *            The ID of the clicked entry.
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
	
	//updating 'Angebot pruefen' button
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
		toggleWarning("error_selection", true, "Kein Dokument ausgewÃ¤hlt! ");
		togglePopup("document_del", false);
		return;
	}
	// alert("/hiwi/Admin/js/deleteDocument?uid=" + selectedDocument);
	connect("/hiwi/Clerk/js/deleteDocument", "uid=" + selectedDocument,
			handleDeleteDocumentResponse);
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

function addDocument() {
	var form = FormAddDocument;
	if (form == null)
		return;
	var error = false;
	var uid = form.uid.value;
	if (uid == null || uid == "") {
		toggleWarning("error_addDocument_uid", true, "Bitte ausfÃ¼llen!");
		error = true;
	} else
		toggleWarning("error_addDocument_uid", false, "");
	var title = form.title.value;
	if (title == null || title == "") {
		toggleWarning("error_addDocument_title", true, "Bitte ausfÃ¼llen!");
		error = true;
	} else
		toggleWarning("error_addDocument_title", false, "");
	var description = form.description.value;
	if (description == null || description == "") {
		toggleWarning("error_addDocument_descr", true, "Bitte ausfÃ¼llen!");
		error = true;
	} else
		toggleWarning("error_addDocument_descr", false, "");
	if (error)
		return;
	// alert("All okay!");
	connect("/hiwi/Clerk/js/addDocument", "uid=" + uid + "&title=" + title
			+ "&description=" + description, handleAddDocumentResponse);
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
 * This function loads all the applicants of all offers from the clerk's institute in the system from the database and
 * displays them. (applicationmanagement.jsp)
 */
function showApplication(){
	selectedOffer = null;
	connect("/hiwi/Clerk/js/showApplication", "", handleShowApplicationResponse);
}

/**
 * This function displays all the applicants of all offers from the clerk's institute in the system.
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
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table2 = document.getElementById("applicationTable");
		// Write table â€“ probably replaces old data!
		table2.innerHTML = "<tr><th>Name des Bewerbers</th><th>Bewibt sich fuer</th></tr>";		
		for ( var i = 0; i < JSONarray.length; i++) {
			table2.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].username +"§%#%§"+ JSONarray[i].aid
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].username +"§%#%§"+ JSONarray[i].aid+ "\');\"><td>" 
					+ JSONarray[i].bewerbername + "</td><td>"
					+ JSONarray[i].angebotsname + "</td></tr>";
		}
	}
} // --> preparing Button --> editapplication.jsp --> applicationDocuments()

/**
 * This function loads all the documents of the chosen application in the system from the database and
 * displays them. (editapplication.jsp)
 */
function applicationDocuments(){
	var User = getURLParameter("User");
	var Aid = getURLParameter("AID");
	selectedOffer = null;
	connect("/hiwi/Clerk/js/applicationDocuments","User=" + User +"&AID=" +Aid, handleApplicationDocumentsResponse);
}

/**
 * This function displays all the documents of the chosen application in the system.
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
		// Erstelle Array aus JSON array:
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table2 = document.getElementById("documentsTable");
		// Write table â€“ probably replaces old data!
		table2.innerHTML = "<tr><th></th><th>Dokumente</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table2.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].username + JSONarray[i].aid
					+ "\" onclick=\"markOfferSelected(\'"
					+ JSONarray[i].username + JSONarray[i].aid+ "\');\"><td><input type=\"checkbox\" /></td><td>" 
					+ JSONarray[i].name + "</td></tr>";
		}
		showApplicationTable2();
	}
}

/**
 * This function loads the applicantname and the offername in the system from the database and
 * displays them. (editapplication.jsp)
 */
function showApplicationTable2(){
	var User = getURLParameter("User");
	var Aid = getURLParameter("AID");
	selectedOffer = null;
	connect("/hiwi/Clerk/js/showApplicationTable2", "User=" + User +"&AID=" +Aid, handleShowApplicationTable2Response);
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
		var JSONarray = eval("("+data+")");
		// Get the table:
		var table2 = document.getElementById("applicantTable");
		// Write table â€“ probably replaces old data!
		table2.innerHTML = "<tr><td>Name:</td></tr><tr><td>"+JSONarray[0]+"</td></tr><tr><td>Beworben fuer:</td></tr><tr><td>"+JSONarray[1]+"</td></tr>";		
	}
} // --> from applicationDocuments()

/**
 * This function checks if a applicant has delivered all of the rquired documents.
 */

function doApplicationCompletion(){
	
	connect("/hiwi/Clerk/js/doApplicationCompletion", "AID="+aid+"&username=" +username, handleApplicationCompletion);
}

function handleApplicationCompletion(mime,data){
	if (mime == "text/url") {
		window.location = data;
	}
	else if (mime == "error/url"){
		alert(data);
		toggleWarning(id, true, "UnvollstÃ¤ndige Dokumente. Abschluss nicht mÃ¶glich");
		}
	else if (mime == "text/error"){
		alert(data);
	}
}
