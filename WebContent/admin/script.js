/**
 * Script for admin webpages.
 * 
 * @author: Tamino Hartmann
 */

// All public, non-xmlhttp variables here:
/**
 * Stores the selected Account:
 */
var selectedAccount;

/**
 * This function loads all the accounts in the system from the database and
 * displays them.
 */
function loadAccounts() {
	// reset selectedID (account could have been deleted in meantime)
	selectedAccount = null;
	connect("/hiwi/Admin/js/loadAccounts", "", handleLoadAccountsResponse);
}

/**
 * This function displays all the accounts in the system.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadAccountsResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "application/json") {
		// Erstelle Array aus JSON array:
		var JSONarray = eval(data);
		// Get the table:
		var table = document.getElementById("accountTable");
		// Write table – probably replaces old data!
		table.innerHTML = "<tr><th>Benutzer Name</th><th>Name</th><th>Emailaddresse</th><th>Account Typ</th></tr>";
		for ( var i = 0; i < JSONarray.length; i++) {
			table.innerHTML += "<tr class=\"\" id=\"" + JSONarray[i].username
					+ "\" onclick=\"markAccountSelected(\'"
					+ JSONarray[i].username + "\');\"><td>"
					+ JSONarray[i].username + "</td><td>" + JSONarray[i].name
					+ "</td><td>" + JSONarray[i].email + "</td><td>"
					+ getTypeString(JSONarray[i].accounttype) + "</td></tr>";
		}
	}
}

/**
 * Deletes the account currently stored in the selectedID variable.
 */
function deleteSelectedAccount() {
	if (selectedAccount == null) {
		toggleWarning("error_selection", true, "Kein Account ausgewählt!");
		return;
	}
	toggleWarning("error_selection", false, "");
	// Tell servlet to delete account:
	deleteAccount(selectedAccount, loadAccounts);
}

/**
 * This function simply deletes an account. If it receives an error back from
 * the server, it will display it in an alert. WARNING: should not be called
 * directly!
 * 
 * @param id
 *            The username ID of the account to be deleted.
 * @param callback
 *            The function to call when done.
 */

// This var saves the callback function because I can't callback with a callback
// function... :P
var callback;

function deleteAccount(id, t_callback) {
	callback = t_callback;
	connect("/hiwi/Admin/js/deleteAccount", "name=" + id,
			handleDeleteAccountResponse);
}

/**
 * 
 * @param mime
 * @param data
 */
function handleDeleteAccountResponse(mime, data) {
	if (mime == null)
		callback();
	else if (mime == "text/url") {
		window.location = data;
	} else if (mime == "text/error") {
		alert(data);
	} else
		callback();
}

/**
 * Function remembers which account has been clicked.
 * 
 * @param username
 *            The username ID of the clicked entry.
 */
function markAccountSelected(id) {
	// Remove marking from previous selected, if applicable:
	if (selectedAccount != null)
		document.getElementById(selectedAccount).setAttribute("class", "");
	// If clicked again, unselect:
	if (selectedAccount == id) {
		selectedAccount = null;
		return;
	}
	// Else save & mark new one:
	selectedAccount = id;
	toggleWarning("error_selection", false, "");
	document.getElementById(id).setAttribute("class", "selected");
	// alert(selectedID + " is selected.");
}

function checkSelection() {
	if (selectedAccount != null) {
		togglePopup('account_del', true);
	} else {
		toggleWarning("error_selection", true, "Kein Account ausgewählt! ");
	}
}

/**
 * Reacts to admin/accountsmanagement -> "Account ändern". Checks and sets
 * correct values.
 */
function checkLegalEdit() {
	if (selectedAccount == null) {
		toggleWarning("error_selection", true, "Kein Account ausgewählt!");
		return;
	}
	toggleWarning("error_selection", false, "");
	// Load editaccount.jsp with correct mode:
	// alert("editaccount.jsp?mode=edit&id=" + selectedID);
	window.location = "editaccount.jsp?mode=edit&id=" + selectedAccount;
}

/**
 * Start loading site here, else something goes wrong! This function will call
 * the loadEditOptions() once the institutes have been loaded.
 */
function loadInstitute() {
	connect(
			"/hiwi/Admin/js/loadInstitutes",
			"",
			function(mime, data) {
				if (mime == "text/url")
					window.location = data;
				else if (mime == "application/json") {
					// alert(data);
					document.getElementById("institute").innerHTML = "";
					var institute = eval(data);
					for ( var i = 0; i < institute.length; i++) {
						// alert("<option value=\"" + institute[i].IID + "\">"
						// + institute[i].name + "</option>");
						document.getElementById("institute").innerHTML += "<option value=\""
								+ institute[i].IID
								+ "\">"
								+ institute[i].name
								+ "</option>";
					}
					loadEditOptions();
				} else if (mime == "text/error")
					alert(data);
			});
}

/**
 * This function is used to correctly initialize the editaccount.jsp site.
 */
function loadEditOptions() {
	var mode = getURLParameter("mode");
	if (mode == "") {
		// alert("Error in loading the page!");
		return;
	}
	// Switch according to what the page is supposed to do:
	if (mode == "add") {
		// Rewrite buttons:
		document.getElementById("saveButton").value = "Account erstellen";
		document.getElementById("saveButton").setAttribute("onclick",
				"addAccount(dataForm);");
		document.getElementById("cancelButton").value = "Abbrechen";
		document.getElementById("deleteButton").setAttribute("disabled",
				"disabled");
		// Add input for userName:
		document.getElementById("userName").innerHTML = "<input type=\"text\" "
				+ "name=\"name\"/><div id=\"error_userName\" class=\"invisibleWarning\"></div>";
		// Add input for accountType:
		document.getElementById("changeAccountType").innerHTML = "<select id=\"accountType\" "
				+ "name=\"accountType\"><option value=\"0\">Administrator</option><option value"
				+ "=\"1\">Anbieter</option><option value=\"2\">Verwalter</option><option selected"
				+ "=\"selected\" value=\"3\">Bewerber</option></select>";
	} else if (mode = "edit") {
		// alert("Edit an account.");
		// Read username from URL:
		var username = getURLParameter("id");
		if (username == "")
			return;
		// Set selection so that deleteAccount works if called:
		selectedAccount = username;
		// Get all data that belongs to this username:
		connect("/hiwi/Admin/js/getAccountData", "name=" + username,
				handleLoadEditResponse);
	} else {
		alert("Unknown mode!");
		return;
	}
}

/**
 * 
 */
function handleLoadEditResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
	} else if (mime == "application/json") {
		// Set data:
		// alert(data);
		var account = eval("(" + data + ")");
		// Set the values we have:
		document.getElementById("userName").innerHTML = account.username;
		document.getElementById("realName").value = account.name;
		document.getElementById("changeAccountType").innerHTML = getTypeString(account.accounttype);
		document.getElementById("email").value = account.email;
		document.getElementById("institute").value = account.institute;
		// Set the values we don't necessarily have:
		document.getElementById("password").value = "";
	}
}

/**
 * This function reacts to the delete button on editaccount.jsp with mode=edit
 * (as not needed on add).
 */
function deleteEditAccount() {
	if (getURLParameter("mode") != "edit")
		return;
	var username = getURLParameter("id");
	if (username == "")
		return;
	deleteAccount(username, function() {
		window.location = "accountsmanagement.jsp";
	});
}

function saveChanges(form) {
	if (form == null)
		return;
	var error = false;
	var realName = form.realName.value;
	if (realName == null || realName == "") {
		toggleWarning("error_realName", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkText(realName)) {
		toggleWarning("error_realName", true, "Unerlaubtes Sonderzeichen!");
		error = true;
	} else
		toggleWarning("error_realName", false, "");
	var email = form.email.value;
	if (email == null || email == "") {
		toggleWarning("error_email", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkEmail(email)) {
		toggleWarning("error_email", true, "Ungültige Email!");
		error = true;
	} else
		toggleWarning("error_email", false, "");
	var password = form.password.value;
	if (password != null && password != "") password = b64_md5(password);
	else password ="";
	var institute = form.institute.value;
	if (institute == null || institute == "") {
		toggleWarning("error_institute", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_institute", false, "");
	if (error)
		return;
	// As of here, send:
	// alert("All okay!");
	var userName = document.getElementById("userName").innerHTML;
	connect("/hiwi/Admin/js/editAccount", "realName=" + realName + "&email="
			+ email + "&userName=" + userName + "&userPassword=" + password
			+ "&institute=" + institute,
			handleEditAccountResponse);
}

function handleEditAccountResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/plain") {
		if (data == "true") {
			window.location = "accountsmanagement.jsp";
			return;
		}
	} else if (mime == "text/error") {
		alert(data);
		return;
	}
}

/**
 * 
 * @param form
 */
function addAccount(form) {
	if (form == null)
		return;
	var error = false;
	var realName = form.realName.value;
	if (realName == null || realName == "") {
		toggleWarning("error_realName", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkText(realName)) {
		toggleWarning("error_realName", true, "Unerlaubtes Sonderzeichen!");
		error = true;
	} else
		toggleWarning("error_realName", false, "");
	var email = form.email.value;
	if (email == null || email == "") {
		toggleWarning("error_email", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkEmail(email)) {
		toggleWarning("error_email", true, "Ungültige Email!");
		error = true;
	} else
		toggleWarning("error_email", false, "");
	var userName = form.name.value;
	if (userName == null || userName == "") {
		toggleWarning("error_userName", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkUsername(userName)) {
		toggleWarning("error_userName", true, "Unerlaubtes Sonderzeichen!");
		error = true;
	} else
		toggleWarning("error_userName", false, "");
	var password = form.password.value;
	if (password == null || password == "") {
		toggleWarning("error_password", true, "Bitte ausfüllen!");
		error = true;
	} else {
		password = b64_md5(password);
		toggleWarning("error_password", false, "");
	}
	var accountType = form.accountType.value;
	var institute = form.institute.value;
	if (institute == null || institute == "") {
		toggleWarning("error_institute", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_institute", false, "");
	if (error)
		return;
	connect("/hiwi/Admin/js/addAccount", "realName=" + realName + "&email="
			+ email + "&userName=" + userName + "&userPassword=" + password
			+ "&accountType=" + accountType + "&institute=" + institute,
			handleCreateAccountResponse);
}

/**
 * 
 * @param mime
 * @param data
 */
function handleCreateAccountResponse(mime, data) {
	if (mime == "text/url") {
		window.location = data;
		return;
	} else if (mime == "text/plain") {
		if (data == "true") {
			window.location = "accountsmanagement.jsp";
			return;
		} else if (data == "false") {
			toggleWarning("error_userName", true,
					"Benutzername ist bereits vergeben!");
			return;
		}
	} else if (mime == "text/error") {
		alert(data);
		return;
	}
}