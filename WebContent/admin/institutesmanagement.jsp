<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/admin/instScript.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Dokumenten Verwaltung | Hiwi Job Börse</title>
</head>
<body onload="loadInstitutes();">
	<div class="footerunten">
		<div class="header">
			<h1>
				<a class="banner" href="../admin/userindex.jsp">Hiwi-Börse</a>
			</h1>
		</div>
		<div class="right">
			<div class="commentform">
				<h3>Abmeldung</h3>
				<form name="abmeldung">
					<p>
						<label for="logout">Sie sind als Administrator angemeldet.</label>
					</p>
					<br>
					<p>
						<input type="button" value="Abmelden" name="logout"
							onclick="doLogout();" />
					</p>
				</form>
			</div>
			<div class="nav">
				<h3>Navigation</h3>
				<div class="text">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_USERINDEX%>">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_DOCUMENTSMANAGEMENT%>">Unterlagenverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_DEFOFFERVALUES%>">Normwerte Angebote</a><br>
					&rarr; Instituteverwaltung<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_ACCOUNTSMANAGEMENT%>">Accountsverwaltung</a>
					<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_HELP%>" title="Hier finden Sie die Hilfe">Hilfe</a>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="rightborder">
				<div class="textblock">
					<h2>Institute Verwalten</h2>
					<div class="text">Hier können sie die Institute im System
						verwalten. Wenn sie ein neues Institut erstellen muss die IID muss
						eindeutig sein.Bitte beachten sie dass das default - Institut
						nicht gelöscht werden kann!</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<input type="button" value="Eintrag hinzufügen"
							onclick="togglePopup('institute_add', true);" /> <input
							style="float: right;" type="button"
							value="Markiertes Institute löschen" onclick="prepareDelete();" />
						<div style="float: right;" class="hiddenerror"
							id="error_selection"></div>
						<br> <br>
						<!--<div class="clear"></div>-->
						<hr>
						<table id="institutesList">
							<!-- javascript loads documents here -->
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="footer"></div>
	<!-- Here are the popups -->
	<!-- Document add popup -->
	<div id="institute_add" class="popup_hidden">
		<form name="addInstituteForm">
			<h3>Institut hinzufügen</h3>
			<hr>
			<div class="textblock">
				IID: <br> <input name="IID" type="text" />
				<div id="error_addInstitute_IID" class="hiddenerror"></div>
				Name: <br>
				<textarea name="name" rows="1" cols="30"></textarea>
				<div id="error_addInstitute_name" class="hiddenerror"></div>
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Speichern" onclick="addInstitute()" />
				<input type="button" value="Abbrechen"
					onclick="clearAddInstitutePopup(); togglePopup('institute_add', false);" />
			</div>
		</form>
	</div>
	<!-- Confirmation popup -->
	<div id="institute_del" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen sie das ausgewählte<br>Institut wirklich löschen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Löschen" onclick="deleteInstitute();" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('institute_del', false);" />
			</div>
		</form>
	</div>
</body>
</html>