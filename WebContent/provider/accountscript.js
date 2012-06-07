/**
 * @author: Manuel Guentzel
 */

/**
 * This function loads the account in the system from the database and displays
 * the data.
 */
function loadAccount() {
	connect("/hiwi/Provider/js/loadAccount", "", handleLoadAccountResponse);
	
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
		document.getElementById("realName").value =JSONdata.realName;

		// Clearing both password inputs
		document.getElementById("newpasswort").value = "";
		document.getElementById("newpasswortwdh").value = "";
	}
}

/**
 * Sends request to delete own account.
 */
function deleteAccount() {
	connect("/hiwi/Provider/js/deleteAccount", "", handleDeleteResponse);
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
	
	if ((pw==""|| pw==null && pww=="" || pww==null) && (!document.getElementById("dataconfirm").checked) ){
		alert("Die Passwortfelder sind leer. Ihr altes Passwort wird beibehalten!");
	}
	
	// Jetzt müssen wir schauen, ob die leer sind und gültig (hier jetzt nur für
	// das passwort!). Da mehrere Sachen falsch sein könne, speichere ich den
	// Wert in einer bool damit immer alle kontrolliert werden, auch wenn einige
	// fehlen.
	var error = false;
	// ACHTUNG: Wenn eines der Passwörter NICHT leer ist, dann sollen sie
	// geändert
	// werden, also die werte hashen und mitschicken! Andere Variablen wie Name
	// und so sollten IMMER nicht leer sein! Siehe auch die register.jsp als
	// beispiel.
	if ((pw != null && pw != "") || (pww != null && pww != "")) {
		// Passwort soll geändert werden, also schauen ob sie gleich sind:
		if (pw != pww) {
			// Ja, es gab einen Fehler:
			// Denn Bentzer benachrichtigen:
			toggleWarning("error_unequalPasswords", true,
					"Passwörter sind nicht gleich!");
		}
		// ELSE brauchen wir wenn der Fehler schonmal aufgetreten ist aber jetzt
		// korrigiert wurde. Dann verstecken wir die (jetzt nicht mehr gültige)
		// Fehlermeldung wieder.
		else
			toggleWarning("error_unequalPasswords", false, "");
	}
	// Wenn es einen Fehler gab, den Benutzer die Eingaben kontrollieren lassen:

	// Ansonsten ist alles okay, also weiter:
	if (document.getElementById("dataconfirm").checked) {
		togglePopup("data_acc_del", true);

	}else if(pw==pww){
		changeAccount();
	}

}

function changeAccount(){
	var form = datenAendern;
	var error = false;
	var realName = form.realName.value;
	if (realName == null || realName == "") {
		toggleWarning("error_realName", true, "Bitte ausfüllen!");
		error = true;
	} else if(!checkText(realName)){
		toggleWarning("error_realName", true, "Unerlaubtes Sonderzeichen!");
		error = true;
	}else
		toggleWarning("error_realName", false, "");
	var email = form.newemail.value;
	if (email == null || email == "") {
		toggleWarning("error_email", true, "Bitte ausfüllen!");
		error = true;
	}else if(!checkEmail(email)){
		toggleWarning("error_email", true, "Ungültige Email!");
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
	connect("/hiwi/Provider/js/changeAccount", "name=" + realName + "&mail="
			+ email + "&pw=" + password,
			handleChangeAccountResponse);
}

function handleChangeAccountResponse(mime, data){
	if(mime=="text/error")alert(data);
	if(mime=="text/url")window.location=data;
}
