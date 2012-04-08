/**
 * Script for admin webpages.
 * 
 * @author: Tamino Hartmann
 */

// All public, non-xmlhttp variables here:
var selectedID;

/**
 * Get XMLHttpRequest object for all important browsers.
 * 
 * @returns XMLHttpRequest object.
 */
function getXMLObject() {
	// Code for IE7+, Firefox, Chrome, Opera, Safari
	if (window.XMLHttpRequest) {
		return new XMLHttpRequest();
	}
	// Code for IE6, IE5
	else {
		return new ActiveXObject("Microsoft.XMLHTTP");
	}
}

// Required for all calls:
var xmlhttp = new getXMLObject();

/**
 * Use this function to toggle the visibility of error messages in error divs.
 * 
 * @param id
 *            The ID of the div which to toggle.
 * @param flag
 *            If <code>true</code> show, with <code>false</code> hide.
 * @param text
 *            Text to input. If flag is false, won't be set.
 */
function toggleWarning(id, flag, text) {
	// alert("ID: " + id + "\nFLAG: " + flag);
	if (flag == true) {
		document.getElementById(id).setAttribute("class", "visibleerror");
		document.getElementById(id).innerHTML = text;
	} else {
		document.getElementById(id).setAttribute("class", "hiddenerror");
		// By false, keep old text (won't be seen anyway):
		// document.getElementById(id).innerHTML = text;
	}
}

/**
 * This function allows the simple showing / hidding of a popup.
 * 
 * @param id
 *            The ID of the popup to change.
 * @param flag
 *            <code>True</code> makes the popup visible, <code>false</code>
 *            makes it hidden.
 */
function togglePopup(id, flag) {
	if (flag) {
		document.getElementById(id).setAttribute("class", "popup_visible");
	} else {
		document.getElementById(id).setAttribute("class", "popup_hidden");
	}
}

/**
 * Given a xmlhttp object, it will return the MIME type set.
 */
function getMIME(responseobject) {
	if (responseobject.getResponseHeader("Content-Type") != null)
		return responseobject.getResponseHeader("Content-Type").split(";")[0];
	else
		return "";
}

/**
 * Function for reading parameters out of an URL. Returns an empty string if
 * none found. Credit: http://www.netlobo.com/url_query_string_javascript.html
 * 
 * @param parameterName
 *            The name of the parameter tor read.
 * @returns The value of the parameter. Null if none found.
 */
function getURLParameter(parameterName) {
	parameterName = parameterName.replace(/[\[]/, "\\\[").replace(/[\]]/,
			"\\\]");
	var regexS = "[\\?&]" + parameterName + "=([^&#]*)";
	var regex = new RegExp(regexS);
	var results = regex.exec(window.location.href);
	if (results == null) {
		return "";
	} else {
		return results[1];
	}
}

// ----------------------------------------- END HELP FUNCTIONS --------------

/**
 * This function loads all the accounts in the system from the database and
 * displays them.
 */
function loadAccounts() {
	// reset selectedID (account could have been deleted in meantime)
	selectedID = null;
	xmlhttp.open("GET", "/hiwi/Admin/js/loadAccounts");
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var mimeType = getMIME(xmlhttp);
			if (mimeType == "text/url") {
				window.location = xmlhttp.responseText;
			} else if (mimeType == "application/json") {
				// Erstelle Array aus JSON array:
				var JSONarray = eval(xmlhttp.responseText);
				// Get the table:
				var table = document.getElementById("accountTable");
				// Write table – probably replaces old data!
				table.innerHTML = "<tr><th>Benutzer Name</th><th>Name</th><th>Emailaddresse</th><th>Account Typ</th></tr>";
				for ( var i = 0; i < JSONarray.length; i++) {
					table.innerHTML += "<tr class=\"\" id=\""
							+ JSONarray[i].username
							+ "\" onclick=\"markSelected(\'"
							+ JSONarray[i].username + "\');\"><td>"
							+ JSONarray[i].username + "</td><td>"
							+ JSONarray[i].name + "</td><td>"
							+ JSONarray[i].email + "</td><td>"
							+ getTypeString(JSONarray[i].accounttype)
							+ "</td></tr>";
				}
			}
		}
	};
	xmlhttp.send();
}

/**
 * Deletes the account currently stored in the selectedID variable.
 */
function deleteSelectedAccount() {
	if (selectedID == null) {
		toggleWarning("error_selection", true, "Kein Account ausgewählt!");
		return;
	}
	toggleWarning("error_selection", false, "");
	// Tell servlet to delete account:
	deleteAccount(selectedID, loadAccounts);
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
function deleteAccount(id, callback) {
	xmlhttp.open("GET", "/hiwi/Admin/js/deleteAccount?name=" + id);
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var mimeType = getMIME(xmlhttp);
			if (mimeType == "text/url") {
				window.location = xmlhttp.responseText;
			} else if (mimeType == "text/error") {
				alert(xmlhttp.responseText);
			} else
				callback();
		}
	};
	xmlhttp.send();
}

/**
 * Function remembers which account has been clicked.
 * 
 * @param username
 *            The username ID of the clicked entry.
 */
function markSelected(id) {
	// Remove marking from previous selected, if applicable:
	if (selectedID != null)
		document.getElementById(selectedID).setAttribute("class", "");
	// If clicked again, unselect:
	if (selectedID == id) {
		selectedID = null;
		return;
	}
	// Else save & mark new one:
	selectedID = id;
	document.getElementById(id).setAttribute("class", "selected");
	// alert(selectedID + " is selected.");
}

/**
 * A help function that returns the descriptive account type name.
 * 
 * @param number
 */
function getTypeString(number) {
	switch (number) {
	case 0:
		return "Administrator";
		break;
	case 1:
		return "Anbieter";
		break;
	case 2:
		return "Verwalter";
		break;
	case 3:
		return "Bewerber";
		break;
	default:
		break;
	}
}

/**
 * Reacts to admin/accountsmanagement -> "Account ändern". Checks and sets
 * correct values.
 */
function checkLegalEdit() {
	if (selectedID == null) {
		toggleWarning("error_selection", true, "Kein Account ausgewählt!");
		return;
	}
	toggleWarning("error_selection", false, "");
	// Load editaccount.jsp with correct mode:
	// alert("editaccount.jsp?mode=edit&id=" + selectedID);
	window.location = "editaccount.jsp?mode=edit&id=" + selectedID;
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
	} else if (mode = "edit") {
		// alert("Edit an account.");
		// Read username from URL:
		var username = getURLParameter("id");
		if (username == "")
			return;
		// Set selection so that deleteAccount works if called:
		selectedID = username;
		// Get all data that belongs to this username:
		xmlhttp.open("GET", "/hiwi/Admin/js/getAccountData?name=" + username);
		xmlhttp.onreadystatechange = function() {
			if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
				var mimeType = getMIME(xmlhttp);
				if (mimeType == "text/url") {
					window.location = xmlhttp.responseText;
				} else {
					// Set data:
					// alert(xmlhttp.responseText);
					// TODO: Why do we need the () here? Doesn't work without.
					var account = eval("(" + xmlhttp.responseText + ")");
					// Set the values we have:
					document.getElementById("userName").innerHTML = account.username;
					document.getElementById("realName").value = account.name;
					document.getElementById("accountType").value = account.accounttype;
					document.getElementById("email").value = account.email;
					document.getElementById("institute").value = account.institute;
					// Set the values we don't necessarily have:
					document.getElementById("password").value = "********";
				}
			}
		};
		xmlhttp.send();
	} else {
		alert("Unknown mode!");
		return;
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

function addAccount(form) {
	if (form == null)
		return;
	var error = false;
	var realName = form.realName.value;
	if (realName == null || realName == "") {
		toggleWarning("error_realName", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_realName", false, "");
	var email = form.email.value;
	if (email == null || email == "") {
		toggleWarning("error_email", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_email", false, "");
	var userName = form.name.value;
	if (userName == null || userName == "") {
		toggleWarning("error_userName", true, "Bitte ausfüllen!");
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
	xmlhttp.open("GET", "/hiwi/Admin/js/addAccount?realName=" + realName
			+ "&email=" + email + "&userName=" + userName + "&userPassword="
			+ password + "&accountType=" + accountType + "&institute="
			+ institute);
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var mimeType = getMIME(xmlhttp);
			// alert(mimeType);
			if (mimeType == "text/url") {
				window.location = xmlhttp.responseText;
				return;
			} else if (mimeType == "text/plain") {
				if (xmlhttp.responseText == "true") {
					window.location = "accountsmanagement.jsp";
					return;
				}
			} else if (mimeType == "text/error") {
				alert(xmlhttp.responseText);
				return;
			}
		}
	};
	xmlhttp.send();
}