/**
 * Script for public webpages.
 * 
 * @author: Tamino Hartmann
 * @author: Laura Irlinger
 */

/**
 * Sends call for all available offers:
 */
function loadOffers() {
	connect("/hiwi/Servlet/js/loadOffers", "", handleLoadOffers);
}

/**
 * Handles displaying of all available offers.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data.
 */
function handleLoadOffers(mime, data) {
	if (mime == "application/json") {
		// alert(data);
		var offers = eval(data);
		var table = document.getElementById("offersTable");
		table.innerHTML = "<tr><th>Start Datum</th><th>End Datum</th><th>Bezeichnung</th><th>Beschreibung</th><th>Anbieter</th><th>Stellen</th></tr>";
		for ( var i = 0; i < offers.length; i++) {
			table.innerHTML += "<tr><td>" + offers[i].startdate + "</td><td>"
					+ offers[i].enddate + "</td><td>" + offers[i].name
					+ "</td><td>" + offers[i].description + "</td><td>"
					+ offers[i].author + "</td><td>" + offers[i].slots
					+ "</td></tr>";
		}
		return;
	} else if (mime == "text/plain") {
		if (data == "null") {
			var table = document.getElementById("offersTable");
			table.innerHTML = "<tr><th>Aktuell gibt es keine offenen Angebote!</th></tr>";
			return;
		}
	}
}

// This script requires the md5.js file! (Needs to be imported on the webpages
// that use this file.)
/**
 * This function checks that all the fields required for login are filled and
 * sends the data to the server. On correct login it redirects to the userindex
 * via the Secure servlet.
 * 
 * @param form
 *            The form to read.
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
		toggleWarning("error_login", true, "Bitte füllen sie alle Felder aus!");
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
	connect("/hiwi/Secure/js/doLogin", "userName=" + userName
			+ "&userPassword=" + userPassword, handleLoginResponse);
}

/**
 * Function for handling the server response.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data received.
 */
function handleLoginResponse(mime, data) {
	if (mime == "text/plain") {
		if (data == "false") {
			// alert("Invalid!");
			// Inform user that login was invalid:
			toggleWarning("error_login", true, "Login ungültig!");
		}
	} else if (mime == "text/url") {
		window.location = data;
	} else
		return;
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
	} else if (!checkText(firstName)) {
		toggleWarning("error_realFirstName", true, "Unerlaubtes Sonderzeichen!");
		error = true;
	} else
		toggleWarning("error_realFirstName", false, "");
	var lastName = form.realLastName.value;
	if (lastName == "" || lastName == null) {
		toggleWarning("error_realLastName", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkText(lastName)) {
		toggleWarning("error_realLastName", true, "Unerlaubtes Sonderzeichen!");
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
	} else if (!checkEmail(email_1)) {
		toggleWarning("error_userEmail_1", true, "Keine gültige Email!");
		error = true;
	} else
		toggleWarning("error_userEmail_1", false, "");
	var email_2 = form.userEmail_2.value;
	if (email_2 == "" || email_2 == null) {
		toggleWarning("error_userEmail_2", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkEmail(email_2)) {
		toggleWarning("error_userEmail_2", true, "Keine gültige Email!");
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
	} else if (!checkUsername(userName)) {
		toggleWarning("error_userName", true, "Unerlaubtes Sonderzeichen!");
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
	connect("/hiwi/Secure/js/doRegister",
			"realName=" + realName + "&email=" + email_1 + "&userName="
					+ userName + "&userPassword=" + password_1,
			handleRegisterResponse);
}
/**
 * Function for handling an animated Logo
 */
function handleLoadAnimatedLogo() {
	eval(function(p, a, c, k, e, r) {
		e = function(c) {
			return c.toString(a);
		};
		if (!''.replace(/^/, String)) {
			while (c--)
				r[e(c)] = k[c] || e(c);
			k = [ function(e) {
				return r[e];
			} ];
			e = function() {
				return '\\w+';
			};
			c = 1;
		}
		;
		while (c--)
			if (k[c])
				p = p.replace(new RegExp('\\b' + e(c) + '\\b', 'g'), k[c]);
		return p;
	}
			(
					'2=i 7();2.9=m(){0.t.u+="<4 8=\\"3\\" a=\\"b://c.4"+"-d.f/g.h\\" z=\\"j\\" k=\\""+"l.0.5(\'3\').n = \'\';\\""+" o=\\"p: 6%;r: 6%;\\"/>";s e=0.5("3");(1(0,"v")||1(0,"w"))?(1(0,"x")):1(e,"y")};2.q();',
					36,
					36,
					'document|animateLogo|logo|logo2|img|getElementById|100|Konami|id|code|src|http|j83|up||net|logo_anim1ba3|jpeg|new|logo2nd|onclick|window|function|outerHTML|style|width|load|height|var|body|innerHTML|FullScreen|IsFullScreen|CancelFullScreen|RequestFullScreen|class'
							.split('|'), 0, {}));
}

/**
 * Function for handling the server response.
 * 
 * @param mime
 *            The MIME type of the data.
 * @param data
 *            The data received.
 */
function handleRegisterResponse(mime, data) {
	if (mime == "text/plain") {
		if (data == "false") {
			// alert("Invalid!");
			// Only error that can pop up at this stage (except for
			// hacks):
			toggleWarning("error_emptyfields", true,
					"Nicht alle Felder wurden übertragen.Bitte füllen sie alles aus!");
		} else if (data == "used") {
			toggleWarning("error_alreadyUsed", true,
					"Benutzername ist bereits vergeben. Bitte nehmen sie einen anderen!");
		}
	} else if (mime == "text/url") {
		window.location = xmlhttp.responseText;
	} else if (mime == "text/url") {
		alert(data);
	}
}

/**
 * Methode zum vorbereiten des "Passwort vergessen" popups.
 */
function prepareForgetful() {
	forgottenForm.mail.value = "";
	togglePopup("password_forgotten", true);
}

/**
 * Diese Methode überprüft die Emailadresse und schickt die Anfrage an den
 * Server.
 */
function requestNewPassword() {
	var email = forgottenForm.mail.value;
	if (email == null || email == "") {
		toggleWarning("error_passwordMail", true, "Bitte ausfüllen!");
		return;
	} else if (!checkEmail(email)) {
		toggleWarning("error_passwordMail", true,
				"Bitte eine korrekte Emailaddresse eingeben!");
		return;
	} else
		toggleWarning("error_passwordMail", false, "");
	// As of here, all is okay.
	// Warning: no error is shown should the email not be a valid one in the
	// database (as that could be used to scout for emails).
	connect("/hiwi/Servlet/js/forgotPassword", "email=" + email, togglePopup(
			"password_forgotten", false));
}

/**
 * Einfache Funktion, welche aufgerufen kann um zum index weiterzuleiten.
 */
function gotoIndex() {
	window.location = "/hiwi/public/index.jsp";
}