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

/**
 * This function checks that all the fields required for register and responds
 * accordingly.
 */
function checkRegister(form) {
	// Check that a form was given (shouldn't happen):
	if (form == null) {
		// alert("Form not found...");
		return;
	}
	var error = false;
	// Get & check values:
	// Might have been evoked, so clear:
	toggleWarning("error_alreadyUsed", false, "");
	// Name:
	var firstName = form.realFirstName.value;
	if (firstName == "" || firstName == null) {
		toggleWarning("error_realFirstName", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_realFirstName", false, "");
	var lastName = form.realLastName.value;
	if (lastName == "" || lastName == null) {
		toggleWarning("error_realLastName", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_realLastName", false, "");
	// Write complete name:
	var realName = firstName + " " + lastName;
	// Email:
	var email_1 = form.userEmail_1.value;
	if (email_1 == "" || email_1 == null) {
		toggleWarning("error_userEmail_1", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_userEmail_1", false, "");
	var email_2 = form.userEmail_2.value;
	if (email_2 == "" || email_2 == null) {
		toggleWarning("error_userEmail_2", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_userEmail_2", false, "");
	// No need to check equality if emails are empty:
	if (email_1 != "" || email_2 != "") {
		// Check that emails are identical:
		if (email_1 != email_2) {
			toggleWarning("error_userEmailDiff", true,
					"Emailaddressen sind nicht identisch!");
			error = true;
		} else
			toggleWarning("error_userEmailDiff", false, "");
	}
	// User name:
	var userName = form.userName.value;
	if (userName == "" || userName == null) {
		toggleWarning("error_userName", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_userName", false, "");
	var password_1 = form.userPassword_1.value;
	// Password:
	if (password_1 == "" || password_1 == null) {
		toggleWarning("error_userPassword_1", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_userPassword_1", false, "");
	var password_2 = form.userPassword_2.value;
	if (password_2 == "" || password_2 == null) {
		toggleWarning("error_userPassword_2", true, "Bitte ausfüllen!");
		error = true;
	} else
		toggleWarning("error_userPassword_2", false, "");
	// No need to check for equality on empty fields:
	if (password_1 != "" || password_2 != "") {
		// Check that passwords are identical:
		if (password_1 != password_2) {
			toggleWarning("error_userPasswordDiff", true,
					"Passwörter sind nicht identisch!");
			error = true;
		} else
			toggleWarning("error_userPasswordDiff", false, "");
	}
	// Check for user consent regarding data restrictions:
	var dataAgreement = form.dataProtection.checked;
	if (!dataAgreement) {
		toggleWarning("error_checkData", true, "Bitte stimmen sie zu!");
		error = true;
	} else
		toggleWarning("error_checkData", false, "");
	// We do not send on incorrect fields:
	if (error)
		return false;
	// alert("Going for register...");
	// MD5 hash the password:
	password_1 = b64_md5(password_1);
	// Send register data:
	xmlhttp
			.open("GET", "/hiwi/Secure/js/doRegister?realName=" + realName + "&email="
					+ email_1 + "&userName=" + userName + "&userPassword="
					+ password_1);
	// When status changes do:
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			// Check what the server sent:
			if (xmlhttp.responseText == "false") {
				// alert("Invalid!");
				// Only error that can pop up at this stage (except for hacks):
				toggleWarning("error_alreadyUsed", true,
						"Dieser Benutzername ist bereits vergeben.\nBitte geben sie einen neuen ein.");
			} else if (xmlhttp.responseText == "true") {
				alert("Register valid! TODO: implement!");
			}
		}
	};
	// Send request.
	xmlhttp.send();
}