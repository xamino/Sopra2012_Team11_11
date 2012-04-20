/**
 * Script for admin webpages.
 * 
 * @author: Laura Irlinger
 * @author: Patryk Boczon
 * @author: Oemer Sahin
 */

// All public, non-xmlhttp variables here:
/**
 * Stores the selected Account:
 */
var selectedAccount;



/**
 * This function loads the account in the system from the database and
 * displays the data.
 */
function loadAccount() {
	// reset selectedID (account could have been deleted in meantime)
	selectedAccount = null;
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
	alert(mime);

	if (mime == "text/url") {
		window.location = data;
		
	} else if (mime == "application/json") {

		// Evaluating the data
		var account = eval("("+data+")");

		
		document.getElementById("newemail").value = account.email;
		document.getElementById("newnutzername").value = account.username;
		
		// Clearing both password inputs
		document.getElementById("newpasswort").value = "";
		document.getElementById("newpasswortwdh").value = "";
	}
}