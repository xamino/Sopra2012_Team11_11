<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/admin/script.js"></script>
<script type="text/javascript" src="/hiwi/md5.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Hilfe | Hiwi Job Börse</title>
</head>

<body>
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
						href="<%=Helper.D_ADMIN_USERINDEX%>"
						title="Hier gelangen Sie auf unsere Startseite">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_DOCUMENTSMANAGEMENT%>">Unterlagenverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_DEFOFFERVALUES%>">Normwerte Angebote</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_INSTITUTESMANAGMENT%>">Instituteverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_ACCOUNTSMANAGEMENT%>">Accountsverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;Hilfe
				</div>
			</div>
		</div>
		<div class="content">
			<div class="rightborder">
				<div class="textblock">
					<h2>Hilfe</h2>
					<div class="text">Hier finden Sie Hilfe zu allen wichtigen
						Themen der Hiwi-Börse.</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
					<strong>Unterlagen (Unterlagenverwaltung)</strong><br>
					<br>
					<div style="padding-left:20px;">
					<strong>Unterlage hinzufügen</strong><br>
					Mit einem Klick auf "Eintrag hinzufügen" kann eine neue Unterlage im System angelegt werden.
					Hierfür müssen die obligatorischen Textfelder des Popups ausgefüllt und anschließend mit dem "Speichern" Button bestätigt werden.<br>
					<br>
					<strong>Unterlage bearbeiten</strong><br>
					Mit einem Klick auf "Eintrag ändern" kann eine aus der Unterlagentabelle selektierte Unterlage bearbeitet werden. 
					Hierfür müssen die obligatorischen Textfelder des Popups ausgefüllt und anschließend mit dem "Speichern" Button bestätigt werden.<br>
					<br>
					<strong>Unterlage löschen</strong><br>
					Mit einem Klick auf "Markierten Eintrag löschen" kann eine aus der Unterlagentabelle selektierte Unterlage gelöscht werden. 
					Hierbei wird die Unterlage aus dem System gelöscht und somit auch aus allen Bewerbungs- und Angebotsunterlagen entfernt.
					</div><br><br>
					<strong>Institute (Instituteverwaltung)</strong><br>
					<br>
					<div style="padding-left:20px;">
					<strong>Institut hinzufügen</strong><br>
					Mit einem Klick auf "Eintrag hinzufügen" kann eine neues Institut im System angelegt werden.
					Hierfür müssen die obligatorischen Textfelder des Popups ausgefüllt und anschließend mit dem "Speichern" Button bestätigt werden.<br>
					<br>
					<strong>Institut löschen</strong><br>
					Mit einem Klick auf "Markiertes Institut löschen" kann eines aus der Instituttabelle selektiertes Institut gelöscht werden. 
					Hierbei wird das Institut aus dem System gelöscht und somit auch aus allen Angeboten und Accounts entfernt.<br>
					</div><br><br>
					<strong>Accounts (Accountsverwaltung)</strong><br>
					<br>
					<div style="padding-left:20px;">
					<strong>Account hinzufügen</strong><br>
					Mit einem Klick auf "Account hinzufügen" kann eine neuer Account im System angelegt werden.
					Hierfür müssen die obligatorischen Textfelder ausgefüllt und anschließend mit dem "Account erstellen" Button bestätigt werden.<br>
					<br>
					<strong>Account bearbeiten</strong><br>
					Mit einem Klick auf "Account ändern" kann ein aus der Accounttabelle selektierter Account bearbeitet werden. 
					Wird das Textfeld "Passwort" nicht ausgefüllt, bleibt das alte Passwort des Accounts erhalten.
					Mithilfe des Buttons "Änderungen verwerfen", kann ohne Änderungen zu tätigen zur Accountsübersicht zurückgekehrt werden.<br>
					<br>
					<strong>Account löschen</strong><br>
					Mit einem Klick auf "Account löschen" kann ein aus der Accounttabelle selektierter Account gelöscht werden.
					- Wird ein Bewerber aus dem System entfernt, werden seine Bewerbungen und Bewerbungsunterlagen gelöscht.
					- Wird ein Anbieter aus dem System entfernt, werden seine Angebote seinem Stellvertreter zugewiesen.
					  Falls kein Stellvertreter angegeben ist, werden die Angebote des Anbieters gelöscht.
					- Wird ein Verwalter aus dem System entfernt, werden seine zu verwaltenden Bewerbungen seinem Stellvertreter zugewiesen. 
					  Falls kein Stellvertreter angegeben ist, muss ein Admin manuell einen Verwalter in das entsprechende Institut eintragen.
					  </div><br>
					  <br>
					  <strong>Normwerte der Angebote</strong>
					  Hier kann man für die Anzahl der Stunden, das Start- und das Enddatum und den Lohn Standardwerte eintragen. 
					  Gespeichert werden Änderungen durch einen Klick auf den Werte speichern - Button.
					  Die eingetragenen Werte werden dann dem Anbieter als Standard angezeigt.<br>
					  <br>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>

	<div class="footer"></div>

</body>

</html>
