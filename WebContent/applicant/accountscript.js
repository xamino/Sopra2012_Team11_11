/**
 * @author: Laura Irlinger
 * @author: Patryk Boczon
 * @author: Oemer Sahin
 */

/**
 * This function loads the account in the system from the database and
 * displays the data.
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
		var JSONdata = eval("("+data+")");
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
function deleteAccount(){
	connect("/hiwi/Applicant/js/deleteAccount","",handleDeleteResponse);
}

/**
 * Handles delete request answer.
 * 
 * @param mime
 * @param data
 */
function handleDeleteResponse(mime, data) {
	alert(mime);
	alert(data);
}

function check(){
	var pw = document.getElementById("newpasswort").value;
	var pww = document.getElementById("newpasswortwdh").value;
	var mail = document.getElementById("newemail").value;
	var name = document.getElementById("newnutzername".value);
	if(document.getElementById("dataconfirm").checked){
		togglePopup("data_acc_del", true);
	}
}

function changeAccount(){
	
}