/**
 * This function loads all the documents of the chosen application in the system
 * from the database and displays them. (editapplication.jsp)
 */
function applicationDocuments() {
	User = getURLParameter("User");
	Aid = getURLParameter("AID");
	selectedItem = null;
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
		// Stores the 'present' value of the AppDocument element
		var checked;
		// Write table – probably replaces old data!
		table2.innerHTML = "<tr><th>Vorhanden?</th><th></th></tr>";
		for ( var i = 0; i < JSONarray.length; i = i + 2) {
			if (JSONarray[i + 1].present) {
				checked = "checked";
			} else {
				checked = "";
			}
			table2.innerHTML += "<tr class=\"\" id=\""
					+ JSONarray[i].uid
					+ "\"  onclick=\"markDocumentSelected(\'"
					+ JSONarray[i].uid
					+ "\');\"> <td> <input type=\"checkbox\" onclick=\"setDocCheck('"
					+ JSONarray[i + 1].username + "'," + JSONarray[i + 1].docID
					+ "," + JSONarray[i + 1].offerID + ")\" " + checked
					+ "> </td><td>" + JSONarray[i].name + "</td></tr>";
		}
		showApplicationTable2();
	}
}

/**
 * This function loads the applicantname and the offername in the system from
 * the database and displays them. (editapplication.jsp)
 */
function showApplicationTable2() {
	var User = getURLParameter("User");
	var Aid = getURLParameter("AID");
	selectedItem = null;
	connect("/hiwi/Clerk/js/getApplicantInfo", "User=" + User + "&AID=" + Aid,
			handleShowApplicationTable2Response);
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
	} else if (mime == "text/error") {
		alert(data);
	} else if (mime == "application/json") {
		// alert(data);
		// Erstelle Array aus JSON array:
		var obj = eval("(" + data + ")");
		// Get the table:
		var table2 = document.getElementById("applicantTable");
		// Write table – probably replaces old data!
		table2.innerHTML = "<tr><td>Name:</td><td>" + obj.realName
				+ "</td></tr><tr><td>Beworben für:</td><td>" + obj.offerName
				+ "</td></tr>";
		connect("/hiwi/Clerk/js/documentsToAddToApplication", "aid=" + Aid
				+ "&username=" + User, handledocumentsToAddToAppResponse);
	}
}

/**
 * Laedt die Dokumente in ein Drop Down Menue, welche nicht fuer die angezeigte
 * Bewerbung benoetigt werden
 * 
 * @param mime
 * @param data
 */
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
 * TODO comment!
 */
function handleAddDocumentResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/error") {
		alert(data);
	}
}

/**
 * TODO comment!
 */
function addDocument() {

	var chosenDocument = document.getElementById("selectAppDocumentsToAdd").value;

	document.getElementById("dokumentloeschenbutton").disabled = "disabled";
	selectedDocument = null;
	togglePopup("document_add", false);

	connect("/hiwi/Clerk/js/addAppDocument", "uid=" + chosenDocument + "&aid="
			+ Aid + "&username=" + User, applicationDocuments);

}

/**
 * Function remembers which document has been clicked.
 * 
 * @param id
 *            The ID of the clicked entry.
 */

function markDocumentSelected(id) {

	// alert("alte docid: "+selectedDocument);
	// Remove marking from previous selected, if applicable:
	if (selectedDocument != null)
		document.getElementById(selectedDocument).setAttribute("class", "");
	// If clicked again, unselect:
	if (selectedDocument == id) {
		selectedDocument = null;
		document.getElementById("dokumentloeschenbutton").disabled = "disabled";
		// document.getElementById("dokumentHinzufuegenButton").disabled =
		// "disabled";
		return;
	}
	// Else save & mark new one:
	selectedDocument = id;
	document.getElementById(id).setAttribute("class", "selected");
	document.getElementById("dokumentloeschenbutton").disabled = "";
	// document.getElementById("dokumentHinzufuegenButton").disabled = "";
	// alert("aktuelle docid: "+selectedDocument);
	// updating 'Angebot pruefen' button
	prepareButton();

}

/**
 * This function forwards the parameters values to the ClerkServlet, where the
 * AppDocument, linked to those values, will be overwritten
 * 
 * @param username
 * @param docid
 * @param offerid
 */
function setDocCheck(username, docid, offerid) {

	// urspr�nglich nur die connect --> aber f�r Markierung das
	// hinzugefuegt!!!
	if (selectedDocument != null)
		document.getElementById(selectedDocument).setAttribute("class", "");
	// alert("man");
	// If clicked again, unselect:
	if (selectedDocument == docid) {
		selectedDocument = null;
		// alert("man oh man");
		document.getElementById("dokumentloeschenbutton").disabled = "disabled";
		// document.getElementById("dokumentHinzufuegenButton").disabled =
		// "disabled";
		return;
	}
	// Else save & mark new one:
	// alert("hier");
	selectedDocument = docid;
	// alert("daa");
	// alert("aktuelle id: "+selectedDocument);
	document.getElementById(docid).setAttribute("class", "selected");

	document.getElementById("dokumentloeschenbutton").disabled = "";
	// document.getElementById("dokumentHinzufuegenButton").disabled = "";

	prepareButton();

	connect("/hiwi/Clerk/js/setDocCheck", "username=" + username + "&docid="
			+ docid + "&offerid=" + offerid, handleCheckResponse);
}

/**
 * Simple default handler for check response.
 */
function handleCheckResponse(mime, data) {
	if(mime == "text/url")
		window.location = data;
	else if (mime == "text/error")
		alert(data);
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
			&& selectedItem != null) { // offermanagement.jsp -->
		// editoffer.jsp, wenn etwas markiert
		// ist
		document.getElementById("angebotpruefen").onclick = function() {
			// wenn angebotpruefen geklickt und kein angebot selektiert
			if (selectedItem == null) { // wenn selektiert und gleich wieder
				// deselektiert wird
				// DO NOTHING
			} else
				window.location = 'editoffer.jsp?AID=' + selectedItem;
		};
	}
	// wenn angebotpruefen geklickt und kein angebot selektiert
	// alert("preparing button");

	else if (document.getElementById("editapplication") != null
			&& selectedItem != null) { // applicationmanagement.jsp -->
		// editapplication.jsp, wenn etwas
		// markiert ist
		document.getElementById("editapplication").onclick = function() {
			var temp = selectedItem.split("�%#%�");
			var user = temp[0];
			var aid = temp[1];
			window.location = 'editapplication.jsp?User=' + user + "&AID="
					+ aid;
		};
	}
}// end prepareButton

/**
 * Deletes a document from an application
 */
function deleteAppDocument() {
	if (selectedDocument != null) {
		// alert("to delete: "+selectedDocument);
		togglePopup('document_del', false);
		connect("/hiwi/Clerk/js/deleteAppDocument", "uid=" + selectedDocument
				+ "&user=" + User + "&aid=" + Aid, deleteAppDocumentResponse);
	}

}

/**
 * TODO comment!
 */
function deleteAppDocumentResponse() {
	document.getElementById("dokumentloeschenbutton").disabled = "disabled";
	selectedDocument = null;
	applicationDocuments();
}

/**
 * This function checks if a applicant has delivered all of the rquired
 * documents.
 */

function doApplicationCompletion() {
	connect("/hiwi/Clerk/js/doApplicationCompletion", "AID=" + aid
			+ "&username=" + username, handleApplicationCompletion);
}

/**
 * TODO comment!
 * 
 * @param mime
 * @param data
 */
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