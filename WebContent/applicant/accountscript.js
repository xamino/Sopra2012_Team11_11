/**
 * @author: Laura Irlinger
 * @author: Patryk Boczon
 * @author: Oemer Sahin
 */

/**
 * This function loads the account in the system from the database and displays
 * the data.
 */
function loadAccount() {
	connect("/hiwi/Applicant/js/loadAccount", "", handleLoadAccountResponse);

}

/**
 * This function displays the account's data in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadAccountResponse(mime, data) {

	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "application/json") {
		// Evaluating the data
		var JSONdata = eval("(" + data + ")");
		// Filling email and username inputs with old data
		document.getElementById("newemail").value = JSONdata.email;
		document.getElementById("realName").value = JSONdata.realName;

		// Clearing both password inputs
		document.getElementById("newpasswort").value = "";
		document.getElementById("newpasswortwdh").value = "";
	}
}

/**
 * Sends request to delete own account.
 */
function deleteAccount() {
	connect("/hiwi/Applicant/js/deleteAccount", "", handleDeleteResponse);
}

/**
 * Handles delete request answer.
 * 
 * @param mime
 * @param data
 */
function handleDeleteResponse(mime, data) {
	if (mime == "text/error") {
		// Hier zeigen wir eine Fehlermeldung an wenn solch eine vom Server
		// kommt:
		alert(data);
	} else if (mime == "text/url") {
		// Hier leiten wir die Seite weiter wenn eine URL vom Server kommt:
		window.location = data;
	}
}

function check() {
	// Hier die Werte auslesen:
	var pw = document.getElementById("newpasswort").value;
	var pww = document.getElementById("newpasswortwdh").value;
	if ((pw != null && pw != "") || (pww != null && pww != "")) {
		// Passwort soll geändert werden, also schauen ob sie gleich sind:
		if (pw != pww) {
			toggleWarning("error_unequalPasswords", true,
					"Passwörter sind nicht gleich!");
		} else
			toggleWarning("error_unequalPasswords", false, "");
	}
	if (document.getElementById("dataconfirm").checked) {
		togglePopup("data_acc_del", true);

	} else if (pw == pww) {
		changeAccount();
	}

}

function changeAccount() {
	var form = datenAendern;
	var error = false;
	var realName = form.realName.value;
	if (realName == null || realName == "") {
		toggleWarning("error_realName", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_realName", false, "");
	var email = form.newemail.value;
	if (email == null || email == "") {
		toggleWarning("error_email", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_email", false, "");
	var password = form.newpasswort.value;
	if (password != null && password != "") {
		password = b64_md5(password);
	} else
		password = "";
	if (error)
		return;
	// As of here, send:
	// alert("All okay!");
	connect("/hiwi/Applicant/js/changeAccount", "name=" + realName + "&mail="
			+ email + "&pw=" + password, handleChangeAccountResponse);
}

function handleChangeAccountResponse(mime, data) {
	if (mime == "text/error")
		alert(data);
	if (mime == "text/url")
		window.location = data;
}
