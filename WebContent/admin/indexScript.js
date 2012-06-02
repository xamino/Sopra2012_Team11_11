/**
 * @author Tamino Hartmann
 * 
 * Dieser Skript ist alles, was bei admin/userindex.jsp benötigt wird.
 */

/**
 * Methode welche in regelmässigen Abständen die Informationen aktualisiert.
 */
function timedInfoLoop() {
	loadUserindex();
	setTimeout("timedInfoLoop()", 5000);
}

/**
 * Funktion welche die Datenanfrage fuer die Informationen startet.
 */
function loadUserindex() {
	connect("/hiwi/Admin/js/getSystemInformation", "", handleLoadUserindex);
}

/**
 * Funktion welche die Informationen verarbeitet und darstellt.
 * 
 * @param mime
 *            Der MIME-Typ der Informationen.
 * @param data
 *            Die Informationen.
 */
function handleLoadUserindex(mime, data) {
	if (mime == "text/url")
		window.location = data;
	else if (mime == "application/json") {
		// alert(data);
		var jsonData = eval("(" + data + ")");
		document.getElementById("loggedInUsers").innerHTML = jsonData.loggedInUsers;
		document.getElementById("allUsers").innerHTML = jsonData.allUsers;
		document.getElementById("totalRAM").innerHTML = jsonData.totalRAM;
		document.getElementById("maxRAM").innerHTML = jsonData.maxRAM;
	}
}