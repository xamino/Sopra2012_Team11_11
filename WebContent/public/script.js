/**
 * Script for public webpages.
 * @author: Tamino Hartmann
 */

// This script requires the md5.js file! (Needs to be imported on the webpages that use this file.)

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
 * This function checks that all the fields required for login are filled and
 * sends the data to the server. On correct login it redirects to the userindex
 * via the Secure servlet.
 * 
 * @param form The form to read.
 */
function checkLogin(form) {
	// Check that a form was given (shouldn't happen):
	if (form == null) {
		// alert("Form not found...");
		return;
	}
	// Get values:
	var userName = form.userName.value;
	var userPassword = form.userPassword.value;
	// Check for filled fields:
	if (userName == "" || userName == null || userPassword == ""
			|| userPassword == null) {
		// alert("Empty!");
		toggleWarning("error_login", true,
				"Bitte füllen sie alle Felder aus!");
		return;
	}
	// Debug input:
	// alert("Name: " + userName + "\nPassword: " + userPassword);
	// Reset warning if required:
	toggleWarning("error_login", false, "");
	// MD5 hash the password:
	userPassword = b64_md5(userPassword);
	// alert("Password hash: " + userPassword);
	// Send login data:
	xmlhttp.open("GET", "/hiwi/Secure/js/doLogin?userName=" + userName
			+ "&userPassword=" + userPassword);
	// When status changes do:
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			// Check what the server sent:
			if (xmlhttp.responseText == "false") {
				// alert("Invalid!");
				// Inform user that login was invalid:
				toggleWarning("error_login", true, "Login ungültig!");
			} else if (xmlhttp.responseText == "true") {
				alert("Valid!\nTODO: Implement redirect server-side!");
			}
		}
	};
	// Send request.
	xmlhttp.send();
}