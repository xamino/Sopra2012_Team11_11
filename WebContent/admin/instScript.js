/**
 * Script for admin webpages, specifically the documentsmanagement.jsp.
 * 
 * @author: Tamino Hartmann
 */

/**
 * Stores the selected institute:
 */
var selectedInstitute;

/**
 * Function for sending a institute load request.
 */
function loadInstitutes() {
	connect("/hiwi/Admin/js/loadInstitutes", "", handleLoadInstitutes);
	selectedInstitute = null;
}

/**
 * This function handles all that has to happen when a selection is registered.
 * 
 * @param id
 *            The ID of the object clicked.
 */
function markInstituteSelected(id) {
	// Remove marking from previous selected, if applicable:
	if (selectedInstitute != null)
		document.getElementById(selectedInstitute).setAttribute("class", "");
	// If clicked again, unselect:
	if (selectedInstitute == id) {
		selectedInstitute = null;
		return;
	}
	// Else save & mark new one:
	selectedInstitute = id;
	toggleWarning("error_selection", false, "");
	document.getElementById(id).setAttribute("class", "selected");
}

/**
 * Handles the institutes sent from the server.
 * 
 * @param mime
 *            The MIME type of the answer.
 * @param data
 *            The data.
 */
function handleLoadInstitutes(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "application/json") {
		var institutes = eval(data);
		var table = document.getElementById("institutesList");
		table.innerHTML = "<tr><th>IID</th><th>Name</th></tr>";
		for ( var i = 0; i < institutes.length; i++)
			table.innerHTML += "<tr class=\"\" id=\"" + institutes[i].IID
					+ "\" onclick=\"markInstituteSelected(" + institutes[i].IID
					+ ")\"><td>" + institutes[i].IID + "</td><td>"
					+ institutes[i].name + "</td></tr>";
	}
}

/**
 * Function handles the checking of parameters for adding a new institute.
 */
function addInstitute() {
	var form = addInstituteForm;
	if (form == null)
		return;
	var error = false;
	var IID = form.IID.value;
	if (IID == null || IID == "") {
		toggleWarning("error_addInstitute_IID", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_addInstitute_IID", false, "");
	var name = form.name.value;
	if (name == null || name == "") {
		toggleWarning("error_addInstitute_name", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_addInstitute_name", false, "");
	if (error)
		return;
	// alert("All okay!");
	connect("/hiwi/Admin/js/addInstitute", "IID=" + IID + "&name=" + name,
			handleAddInstituteResponse);
}

/**
 * Handles the response to an add request.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleAddInstituteResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/error") {
		alert(data);
	}
}

/**
 * Clears the addInstitute popup.
 */
function clearAddInstitutePopup() {
	addInstituteForm.IID.value = "";
	addInstituteForm.name.value = "";
	toggleWarning("error_addInstitute_IID", false, "");
	toggleWarning("error_addInstitute_name", false, "");
}

/**
 * Diese Funktion überprüft ob die Löschaktion legal ist (ist ein Dokument
 * ausgewählt?) und reagiert entsprechend.
 */
function prepareDelete() {
	if (selectedInstitute == null) {
		toggleWarning("error_selection", true, "Kein Institut ausgewählt! ");
		return;
	}
	togglePopup('institute_del', true);
}

/**
 * Deletes a document if one is selected.
 */
function deleteInstitute() {
	if (selectedInstitute == null) {
		toggleWarning("error_selection", true, "Kein Institut ausgewählt! ");
		togglePopup("institute_del", false);
		return;
	}
	// alert("/hiwi/Admin/js/deleteDocument?uid=" + selectedDocument);
	connect("/hiwi/Admin/js/deleteInstitute", "IID=" + selectedInstitute,
			handleDeleteInstituteResponse);
}

/**
 * Handles the response to a deletion request.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleDeleteInstituteResponse(mime, data) {
	if (mime == "text/error")
		alert(data);
	else if (mime == "text/url")
		window.location = data;
}