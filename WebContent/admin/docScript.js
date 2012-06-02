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
	connect("/hiwi/Admin/js/loadDocuments", "", handleLoadDocuments);
	selectedDocument = null;
}

/**
 * This function handles all that has to happen when a selection is registered.
 * 
 * @param id
 *            The ID of the object clicked.
 */
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
	toggleWarning("error_selection", false, "");
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

/**
 * Function handles the checking of parameters for adding a new Document.
 */
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
	connect("/hiwi/Admin/js/addDocument", "uid=" + uid + "&title=" + title
			+ "&description=" + description, handleAddDocumentResponse);
}

/**
 * Handles the response to an add request.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleAddDocumentResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/error") {
		// Case-sensitive reaction:
		if (data == "0") {
			toggleWarning("error_addDocument_uid", true,
					"Nur ganze Zahlen erlaubt für die UID!");
		} else if (data == "1") {
			toggleWarning("error_addDocument_uid", true,
					"Die UID ist nicht eindeutig!");
		}
		// if data != number, then it is an exceptional error and we simply show
		// the data:
		else
			alert(data);
	}
}

/**
 * Clears the addDocument popup.
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
 * Diese Funktion überprüft ob die Löschaktion legal ist (ist ein Dokument
 * ausgewählt?) und reagiert entsprechend.
 */
function prepareDelete() {
	if (selectedDocument == null) {
		toggleWarning("error_selection", true, "Kein Dokument ausgewählt! ");
		return;
	}
	togglePopup('document_del', true);
}

/**
 * Deletes a document if one is selected.
 */
function deleteDocument() {
	// this if shouldn't be able to get called, but you never know... (see
	// prepareDelete())
	if (selectedDocument == null) {
		toggleWarning("error_selection", true, "Kein Dokument ausgewählt! ");
		togglePopup("document_del", false);
		return;
	}
	// alert("/hiwi/Admin/js/deleteDocument?uid=" + selectedDocument);
	connect("/hiwi/Admin/js/deleteDocument", "uid=" + selectedDocument,
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

/**
 * Loads the selected document's data and displays it, if selection is valid.
 */
function loadSelectedEdit() {
	if (selectedDocument == null) {
		toggleWarning("error_selection", true, "Kein Dokument ausgewählt! ");
		return;
	}
	connect("/hiwi/Admin/js/getDocument", "uid=" + selectedDocument,
			handleLoadEditResponse);
}
/**
 * Handles the response to selecting a document for editing.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadEditResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "text/error") {
		alert(data);
	} else if (mime == "application/json") {
		var adminDocument = eval("(" + data + ")");
		editDocumentForm.uid.value = adminDocument.uid;
		editDocumentForm.title.value = adminDocument.name;
		editDocumentForm.description.value = adminDocument.description;
		togglePopup('document_edit', true);
	}
}

/**
 * Checks that all information is legal and sends it to the server.
 */
function editDocument() {
	var form = editDocumentForm;
	if (form == null)
		return;
	var error = false;
	var uid = form.uid.value;
	if (uid == null || uid == "") {
		toggleWarning("error_editDocument_uid", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_editDocument_uid", false, "");
	var title = form.title.value;
	if (title == null || title == "") {
		toggleWarning("error_editDocument_title", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_editDocument_title", false, "");
	var description = form.description.value;
	if (description == null || description == "") {
		toggleWarning("error_editDocument_descr", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_editDocument_descr", false, "");
	if (error)
		return;
	// alert("All okay!");
	connect("/hiwi/Admin/js/editDocument", "uid=" + uid + "&title=" + title
			+ "&description=" + description, handleEditResponse);
}

/**
 * Handles the response from the server upon an edit request.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleEditResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "text/error") {
		alert(data);
	}
}

/**
 * Clears the editDocument popup.
 */
function clearEditDocumentPopup() {
	editDocumentForm.uid.value = "";
	editDocumentForm.title.value = "";
	editDocumentForm.description.value = "";
	toggleWarning("error_editDocument_uid", false, "");
	toggleWarning("error_editDocument_descr", false, "");
	toggleWarning("error_editDocument_title", false, "");
}