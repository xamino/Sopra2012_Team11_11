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
 * TODO!
 * 
 * @param mime
 * @param data
 */
function handleLoadDefValues(mime, data) {
	if (mime == "text/url")
		window.location = data;
	else
		alert(mime + "\n\n" + data);
}

/**
 * TODO!
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
	} else
		toggleWarning("error_hoursMonth", false, "");
	var startDate = form.startDate.value;
	if (startDate == null || startDate == "") {
		toggleWarning("error_startDate", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkText(startDate)) {
		toggleWarning("error_startDate", true, "Bitte keine Sonderzeichen!");
		error = true;
	} else
		toggleWarning("error_startDate", false, "");
	var endDate = form.endDate.value;
	if (endDate == null || endDate == "") {
		toggleWarning("error_endDate", true, "Bitte ausfüllen!");
		error = true;
	} else if (!checkText(endDate)) {
		toggleWarning("error_endDate", true, "Bitte keine Sonderzeichen!");
		error = true;
	} else
		toggleWarning("error_endDate", false, "");
	if (error)
		return;
	// alert("All okay!");
	connect("/hiwi/Admin/js/saveDefValues", "hoursMonth=" + hoursMonth
			+ "&startDate=" + startDate + "&endDate=" + endDate,
			handleSaveResponse);
}

/**
 * TODO!
 * 
 * @param mime
 * @param data
 */
function handleSaveResponse(mime, data) {
	if (mime == "text/url")
		window.location = data;
	else
		alert(mime + "\n\n" + data);
}