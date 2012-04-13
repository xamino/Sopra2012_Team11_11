/**
 * Script for admin webpages, specifically the documentsmanagement.jsp.
 * 
 * @author: Tamino Hartmann
 */

/**
 * Stores the selected Document:
 */
var selectedDocument;

/**
 * Function for sending a document load request.
 */
function loadDocuments() {
	connect("/hiwi/Admin/js/loadDocuments", handleLoadDocuments);
	selectedDocument = null;
}

function markDocumentSelected(id) {
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
	document.getElementById(id).setAttribute("class", "selected");
}

/**
 * Handles the documents sent from the server.
 * 
 * @param mime
 *            The MIME type of the answer.
 * @param data
 *            The data.
 */
function handleLoadDocuments(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "application/json") {
		var documents = eval(data);
		var table = document.getElementById("documentsList");
		table.innerHTML = "<tr><th>UID</th><th>Name</th><th>Beschreibung</th></tr>";
		for ( var i = 0; i < documents.length; i++)
			table.innerHTML += "<tr class=\"\" id=\"" + documents[i].uid
					+ "\" onclick=\"markDocumentSelected(" + documents[i].uid
					+ ")\"><td>" + documents[i].uid + "</td><td>"
					+ documents[i].name + "</td><td>"
					+ documents[i].description + "</td></tr>";
	}
}

function addDocument() {
	var form = addDocumentForm;
	if (form == null)
		return;
	var error = false;
	var uid = form.uid.value;
	if (uid == null || uid == "") {
		toggleWarning("error_addDocument_uid", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_addDocument_uid", false, "");
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
	// alert("All okay!");
	connect("/hiwi/Admin/js/addDocument?uid=" + uid + "&title=" + title
			+ "&description=" + description, handleAddDocumentResponse);
}

function handleAddDocumentResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/error") {
		alert(data);
	} else if (mime == "text/plain") {
		if (data == "true") {
			clearAddDocumentPopup();
			togglePopup("document_add", false);
			loadDocuments();
		}
	}
}

function clearAddDocumentPopup() {
	addDocumentForm.uid.value = "";
	addDocumentForm.title.value = "";
	addDocumentForm.description.value = "";
	toggleWarning("error_addDocument_uid", false, "");
	toggleWarning("error_addDocument_descr", false, "");
	toggleWarning("error_addDocument_title", false, "");
}