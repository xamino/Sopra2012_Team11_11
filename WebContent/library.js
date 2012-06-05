/**
 * This here is the collection of important functions required throughout the project.
 * @author Tamino Hartmann
 */

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
 * Function for abstracting the connection to the Server.
 * 
 * @param URL
 *            The URL to be called.
 * @param data
 *            The data to be sent. Must be of the form "name=value&age=value".
 * @param callback
 *            The function to be called when an answer is received. Must be of
 *            the form: callback(String MIMETYPE, String CONTENT). Can be null,
 *            but nothing happens then of course.
 */
function connect(URL, data, callback) {
	// Send login data:
	xmlhttp.open("POST", URL);
	// Important!! charset=UTF-8 is the key... :D
	xmlhttp.setRequestHeader("Content-type",
			"application/x-www-form-urlencoded;charset=UTF-8");
	// When status changes do:
	xmlhttp.onreadystatechange = function() {
		if (xmlhttp.readyState == 4 && xmlhttp.status == 200) {
			if (callback != null)
				callback(getMIME(xmlhttp), xmlhttp.responseText);
		}
	};
	// Send request.
	xmlhttp.send(data);
}

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
 * Given a xmlhttp object, it will return the MIME type set.
 */
function getMIME(responseobject) {
	// alert(responseobject.getResponseHeader("Content-Type"));
	var header = responseobject.getResponseHeader("Content-Type");
	if (header == null)
		return null;
	return header.split(";")[0];
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
 * Function for logout.
 */
function doLogout() {
	connect("/hiwi/Secure/js/doLogout", "", function(mime, data) {
		if (mime == "text/url")
			window.location = data;
	});
}
/**
 * Funktion die ueberprueft ob der Text eines Textfelds gueltig ist. Laenge != 0 ist mit enthalten.
 * @param text zu ueberpruefender Text.
 * @returns {Boolean} True falls gueltig, False falls ungueltig.
 */
function checkText(text){
	if (text == "")return false;
	var regex = /^((\s)*[a-zA-Z0-9_-.]+(\s)*)*$/;
	return(regex.test(text));
}
/**
 * Funktion die ueberprueft ob ein String eine Ganze zahl ist. Laenge != 0 ist mit enthalten.
 * @param int zu pruefender String
 * @returns {Boolean} True falls gueltig, False falls ungueltig
 */
function checkInt(int){
	if(int=="")return false;
	var regex = /^(0|(-)?[1-9][0-9]*)$/;
	return(regex.test(int));
}

/**
 * Funktion die ueberprueft ob ein String eine Gleitkommazahl ist. Laenge != 0 ist mit enthalten. Punkt und Komma werden als Trennzeichen akzeptiert.
 * @param float zu pruefende Gleitkommazahl
 * @returns {Boolean} True falls gueltig, False falls ungueltig
 */
function checkFloat(float){
	if(floar=="")return false;
	var regex = /^(0|(-)?[1-9][0-9]*((,|.)[0-9]+)?|0(.|,)[0-9]+)$/;
	return(regex.test(float));
}

/**
 * Funktion die ueberprueft ob eine Email gueltig ist. Laenge != 0 ist mit enthalten.
 * @param email zu ueberpruefende Email-Adresse.
 * @returns {Boolean} True falls gueltig, False falls ungueltig.
 */
function checkEmail(email) {
	  if (email=="")return false;
	  var regex = /^[a-zA-Z]([a-zA-Z0-9]|[._-][a-zA-Z0-9])*\@(([a-zA-Z0-9])+((-|.)[a-zA-Z0-9])?.)+[a-zA-Z0-9]{2,255}$/;
	  return(regex.test(email));
}
/**
 * Funktion die ueberprueft ob ein Username gueltig ist. Laenge != 0 ist mit enthalten.
 * @param username zu ueberpruefender UserName.
 * @returns {Boolean} True falls gueltig, False falls ungueltig.
 */
function checkUsername(username){
	if(username=="")return false;
	var regex = /^[a-zA-Z]+[a-zA-Z0-9_-.]*$/;
	return(regex.test(username));
}