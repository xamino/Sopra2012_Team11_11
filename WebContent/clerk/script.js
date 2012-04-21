/**
 * @author: Patryk Boczon
 */




function doExcelExport() {
	connect("/hiwi/Clerk/js/doExcelExport", "", handleExport);
}

function handleExport(mime, data) {
	if (mime == "text/url")
		window.location = data;
}