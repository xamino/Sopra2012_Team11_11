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
 * This function loads all the accounts in the system from the database and
 * displays them.
 */
function loadAccounts() {
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
 * 
 */
function deleteSelectedAccount() {
	if (selectedID == null) {
		toggleWarning("error_selection", true, "Kein Account ausgewählt!");
		return;
	}
	toggleWarning("error_selection", false, "");
	// Tell servlet to delete account:
	xmlhttp.open("GET", "/hiwi/Admin/js/deleteAccount?name=" + selectedID);
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			var mimeType = getMIME(xmlhttp);
			if (mimeType == "text/url") {
				window.location = xmlhttp.responseText;
			} else {
				// Reload to show deletion of account:
				loadAccounts();
			}
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