/**
 * Script for admin webpages, specifically the defaultoffervalues.jsp.
 * 
 * @author: Tamino Hartmann
 */

/**
 * Loads the values already present in the DB.
 */
function loadDefValues() {
	connect("/hiwi/Admin/js/getDefValues", "", handleLoadDefValues);
}

/**
 * Handles the server response to loadDefValues.
 * 
 * @param mime
 *            The MIME-type of the data.
 * @param data
 *            The data.
 */
function handleLoadDefValues(mime, data) {
	if (mime == "text/url")
		window.location = data;
	else if (mime == "text/error")
		alert(data);
	else if (mime == "application/json") {
		var dataObj = eval("(" + data + ")");
		inputValForm.hoursMonth.value = dataObj.hoursMonth;
		inputValForm.startDate.value = dataObj.startDate;
		inputValForm.endDate.value = dataObj.endDate;
		inputValForm.wage.value = dataObj.wage;
	} else
		return;
}

/**
 * Checks the inputed values and saves them to the DB if correct.
 */
function checkVal() {
	var form = inputValForm;
	if (form == null)
		return;
	var error = false;
	var hoursMonth = form.hoursMonth.value;
	if (hoursMonth == null || hoursMonth == "") {
		toggleWarning("error_hoursMonth", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkInt(hoursMonth)) {
		toggleWarning("error_hoursMonth", true, "Bitte nur ganze Zahlen!");
		error = true;
	} else{
		toggleWarning("error_hoursMonth", false, "");
		hoursMonth=hoursMonth.replace(",",".");
	}
	var startDate = form.startDate.value;
	if (startDate == null || startDate == "") {
		toggleWarning("error_startDate", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkDate(startDate)) {
		toggleWarning("error_startDate", true, "Inkorrektes  Datum!");
		error = true;
	} else {
		toggleWarning("error_startDate", false, "");
		startDate = unifyDate(startDate);
	}
	var endDate = form.endDate.value;
	if (endDate == null || endDate == "") {
		toggleWarning("error_endDate", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkDate(endDate)) {
		toggleWarning("error_endDate", true, "Inkorrektes  Datum!");
		error = true;
	} else {
		toggleWarning("error_endDate", false, "");
		endDate = unifyDate(endDate);
	}
	var wage = form.wage.value;
	if (wage == null || wage == "") {
		toggleWarning("error_wage", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkFloat(wage)) {
		toggleWarning("error_wage", true, "Bitte keine Sonderzeichen!");
		error = true;
	} else{
		toggleWarning("error_wage", false, "");
		wage=wage.replace(",",".");
	}
	if (error)
		return;
	// alert("All okay!");
	connect("/hiwi/Admin/js/saveDefValues", "hoursMonth=" + hoursMonth
			+ "&startDate=" + startDate + "&endDate=" + endDate + "&wage="
			+ wage, handleSaveResponse);
}

/**
 * Handles the response from checkVal if data was sent to the server.
 * 
 * @param mime
 * @param data
 */
function handleSaveResponse(mime, data) {
	if (mime == "text/url")
		window.location = data;
	else if (mime == "text/error")
		alert(data);
	else if (mime == "text/plain") {
		if (data == "true")
			window.location = "/hiwi/admin/userindex.jsp";
	}
}