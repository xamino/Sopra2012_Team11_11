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
				<a class="banner" href="../applicant/userindex.jsp">Hiwi-Börse</a>
			</h1>
		</div>
		<div class="right">
			<div class="commentform">
				<h3>Abmeldung</h3>
				<form name="abmeldung">
					<p>
						<label for="logout">Sie sind als Bewerber angemeldet.</label>
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
						href="<%=Helper.D_APPLICANT_USERINDEX%>"
						title="Hier gelangen Sie auf unsere Startseite">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_APPLICANT_ACCOUNTMANAGEMENT%>"
						title="Hier geht es zur Accountverwaltung">Accountverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;Hilfe
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
					<strong>Bewerben</strong><br>
					Mit einem Klick auf "Bewerben" kann sich auf eine offene Stelle beworben werden.<br>
					<br>
					<strong>Bewerbungsstatus einsehen</strong><br>
					Mit einem Klick auf "Bewerbung ansehen", kann der Status der Bewerbung eingesehen werden.
					Hier werden die für die Bewerbung benötigten Unterlagen und deren Abgabestatus, sowie der Bewerbungsstatus ("angenommen" bzw. "nicht angenommen") angezeigt.<br>
					<br>
					<strong>Anbieter kontaktieren</strong><br>
					Unter "Bewerbung ansehen", kann der für das Angebot verantwortliche Anbieter über den Button "Anbieter Kontaktieren" erreicht werden. 
					Hierbei öffnet sich der E-Mail Client.<br>
					<br>
					<strong>Verwalter kontaktieren</strong><br>
					Unter "Bewerbung ansehen", kann der für den Bewerbungsablauf verantwortliche Verwalter über den Button "Verwalter Kontaktieren" erreicht werden. 
					Hierbei öffnet sich der E-Mail Client.<br>
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
