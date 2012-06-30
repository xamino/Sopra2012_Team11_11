<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="md5.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Hilfe | Hiwi Job Börse</title>
</head>

<body>
	<div class="footerunten">
		<div class="header">
			<h1>
				<a class="banner" href="../provider/userindex.jsp">Hiwi-Börse</a>
			</h1>
		</div>
		<div class="right">
			<div class="commentform">
				<h3>Abmeldung</h3>
				<form name="abmeldung">
					<p>
						<label for="logout">Sie sind als Anbieter angemeldet.</label>
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
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <a
						href="<%=Helper.D_PROVIDER_USERINDEX%>"
						title="Hier gelangen Sie auf unsere Startseite">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_PROVIDER_ACCOUNTMANAGEMENT%>"
						title="Hier geht es zur Accountverwaltung">Accountverwaltung</a> <br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;
					Hilfe
				</div>
			</div>
		</div>
		<div class="content">
			<div class="rightborder">
				<div class="textblock">
					<h2>Hilfe</h2>
					<div class="text">Hier finden Sie Hilfe zu allen wichtigen Themen der Hiwi-Börse.</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
					<strong>Neues Angebot erstellen</strong><br>
					Um ein neues Angebot zu erstellen, klickt man auf der Startseite unten links auf den gleichnamigen Button.
					Man wird zur Angebot erstellen - Seite weitergeleitet. Hier füllt man alle Felder aus.
					Achtung, nachträglich kann man nur den Titel und die Beschreibung ändern (weitere Informationen unter Angebot ändern).
					Wichtig ist hierbei das Feld Anbieternotiz, da dies der Verwalter sieht, wenn er entscheidet, ob das Angebot auf der Hiwi-Börse erscheinen darf oder nicht.
					Das Angebot erscheint erst auf der Hiwi-Börse, wenn der Verwalter zugestimmt hat.<br>
					<br>
					<strong>Angebot ändern</strong><br>
					Ändern kann man ein Angebot, indem man das entsprechende Angebot in der Liste auf der Startseite sucht und den Button Angebot ändern anklickt.
					Man wird auf die Angebot bearbeiten - Seite weitergeleitet. Hier kann man nun sowohl den Titel, als auch die Beschreibung ändern. 
					Andere Faktoren können nachträglich nicht mehr verändert werden. In diesem Fall muss man das Angebot löschen und anschließend mit einer entsprechenden 
					Anbieternotiz neu einstellen. Abschließend klickt man auf den Button Angebot ändern. Alle Bewerber werden über die Ändern per Mail informiert.<br>
					<br>
					<strong>Angebot löschen</strong><br>
					Um eine Angebot zu löschen, sucht man das entsprechende Angebot in der Liste auf der Startseite und klickt auf den Button Angebot zurückziehen. 
					Abschließend bestätigt man das Popup.<br>
					<br>
					<strong>Bewerberauswahl ansehen</strong><br>
					Um die Bewerber zu einem Angebot zu sehen, sucht man das entsprechende Angebot auf der Startseite und klickt auf den Button Bewerberauswahl. 
					Man wird auf die Bewerberliste-Seite weitergeleitet.<br>
					<br>
					<strong>Bewerber annehmen</strong><br>
					Um einen Bewerber für ein Angebot anzunehmen, selektieren sie den Bewerber in der Bewerberliste und klicken auf den Button Annehmen.<br>
					<br>
					<strong>Account bearbeiten</strong><br>
					Durch einen Klick auf "Accountverwaltung" kann der Benutzer seine Accountdaten bearbeiten.
					Werden die Textfelder "neues Passwort" und "neues Passwort wiederholen" unausgefüllt bleiben, wird das bisherige Passwort beibehalten.
					Ein Klick auf "Zurücksetzen" lädt die ursprünglichen Accountdaten.
					Ein Klick auf "Ändern" übernimmt die getätigten eingaben und ändern den Account.<br>
					<br>
					<strong>Eigenen Account löschen</strong><br>
					Wird das Häkchen "Datenschutzbestimmungen widerrufen" aktiviert und "Ändern" geklickt, kann der eigene Account des Benutzers gelöscht werden.
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>

	<div class="footer"></div>

</body>

</html>
