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
		document.getElementById("newnutzername").value =JSONdata.username;

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
	var pw = document.getElementById("newpasswort").value;
	var mail = document.getElementById("newemail").value;
	var name = document.getElementById("newnutzername".value);
	var data="";
	var bpw=false;
	var bname=false;
	var bmail=false;
	for(i=0;i<3;i++){
		if(!(pw!=null&&pw!=""&&name!=null&&name!=""&&mail!=null&&mail!=""))break;
		if(i==0){
			if(pw!=null&&pw!=""&&!bpw){
				data+="pw="+b64_md5(pw);
				bpw=true;
				continue;
			}
			if(name!=""&&name!=null&&!bname){
				data+="name="+name;
				bname=true;
				continue;
			}
			if(mail!=""&&mail!=null&&!bmail){
				data+="email="+mail;
				bmail=true;
				continue
			}
			
		}else{
			if(pw!=null&&pw!=""&&!bpw){
				data+="&pw="+b64_md5(pw);
				bpw=true;
				continue;
			}
			if(name!=""&&name!=null&&!bname){
				data+="&name="+name;
				bname=true;
				continue;
			}
			if(mail!=""&&mail!=null&&!bmail){
				data+="&email="+mail;
				bmail=true;
				continue
			}
		}
	}
	connect("/hiwi/Applicant/js/changeAccount",data,handleChangeResponse);
}

function handleChangeResponse(mime, data){
	if(mime=="text/error")alert(data);
}
