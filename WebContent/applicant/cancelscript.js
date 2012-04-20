/**
 * Script for canceling an application
 * 
 * @author: Laura Irlinger
 * @author: Oemer Sahin
 */


/**
 * This function simply deletes an application. If it receives an error back from
 * the server, it will display it in an alert. WARNING: should not be called
 * directly!
 * 
 * @param benutzername
 *            The username of the applicant.
 * @param aid
 * 			The ID of the application which should be deleted.
 * @param callback
 *            The function to call when done.
 */


function deleteApplication(aid) {
	//callback = t_callback;
	connect("/hiwi/Applicant/js/deleteApplication","aid="+aid, handleDeleteApplicationResponse);
}

/**
 * 
 * @param mime
 * @param data
 */
function handleDeleteApplicationResponse(mime, data) {
	if (mime == null)
		callback();
	else if (mime == "text/url") {
		window.location = data;
	} else if (mime == "text/error") {
		alert(data);
	} else
		callback();
}