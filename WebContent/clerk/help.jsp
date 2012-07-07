<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="/hiwi/md5.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Hilfe | Hiwi Job Börse</title>
</head>

<body>
	<div class="footerunten">
		<div class="header">
			<h1>
				<a class="banner" href=../provider/userindex.jsp>Hiwi-Börse</a>
			</h1>
		</div>
		<div class="right">
			<div class="commentform">
				<h3>Abmeldung</h3>
				<form name="abmeldung">
					<p>
						<label for="logout">Sie sind als Sachbearbeiter
							angemeldet.</label>
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
						href="<%=Helper.D_CLERK_USERINDEX%>"
						title="Hier gelangen Sie auf unsere Startseite">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="doExcelExport();">ExcelExport</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_ACCOUNTMANAGEMENT%>">Accountverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr; Hilfe
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
						<strong>Angebot Verwalten</strong>(Auswahl eines Angebots aus der
						Angebotstabelle mit anschließendem Klick auf "Angebot prüfen")<br>
						<br>
						<div style="padding-left: 20px;">
							<strong>Angebotsattribute ändern</strong><br> Es können die
							Stunden/Woche und der Lohn angepasst werden. Zudem kann durch den
							Button "Angebot bestätigen", dieses für Bewerbungen freigegeben
							werden und wird dadurch für den Verwalter nicht mehr abänderbar.
							Diese Änderungen werden erst durch einen Klick auf "Angebot
							Speichern" übernommen.<br> <br> <strong>Standartdokumente
								hinzufügen/löschen</strong><br> Durch einen Klick auf "Dokument
							hinzufügen" kann ein Dokument aus allen hinzufügbaren Dokumenten
							ausgewählt und mit "Speichern" hinzugefügt werden. Durch einen
							Klick auf "Dokument löschen" kann ein ausgewähltes Dokument
							gelöscht werden.<br> <br> <strong>Anbieter
								Kontaktieren</strong><br> Durch einen Klick auf "Mail an Anbieter"
							kann der Anbieter des Angebots kontaktiert werden. Hierbei öffnet
							sich der E-Mail Client.
						</div>
						<br> <br> <strong>Bewerbung Verwalten</strong> (Auswahl
						einer Bewerbung aus der Bewerbungstabelle mit anschließendem Klick
						auf "Bewerbung bearbeiten")<br> <br>
						<div style="padding-left: 20px;">
							<strong>Dokuemtentenabgabe Verwalten</strong><br> Durch ein
							Häkchen neben dem dazugehörigen Dokument kann dieses als
							abgegeben bzw. nicht abgegeben markiert werden.<br> <br>
							<strong>Bewerbungsdokumente hinzufügen/löschen</strong><br>
							Durch einen Klick auf "Dokument hinzufügen" kann ein Dokument aus
							allen hinzufügbaren Dokumenten ausgewählt und mit "Speichern"
							hinzugefügt werden. Durch einen Klick auf "Dokument löschen" kann
							ein ausgewähltes Dokument gelöscht werden.<br> <br> <strong>Bewerbungsabschluss</strong><br>
							Mit einem Klick auf den Bewerbungsabschluss-Button wird die
							bewerbung als abgeschlossen markiert und ist damit nicht mehr
							sichtbar.
						</div>
						<br> <br> <strong>Excel-Export</strong><br> Hier
						werden alle Bewerber mit Benutzername, realen Namen und beworbenem
						Angebot gespeichert.<br> <br> <strong>Account
							bearbeiten</strong><br> Durch einen Klick auf "Accountverwaltung"
						kann der Benutzer seine Accountdaten bearbeiten. Werden die
						Textfelder "neues Passwort" und "neues Passwort wiederholen"
						unausgefüllt bleiben, wird das bisherige Passwort beibehalten. Ein
						Klick auf "Zurücksetzen" lädt die ursprünglichen Accountdaten. Ein
						Klick auf "Ändern" übernimmt die getätigten eingaben und ändern
						den Account.<br> <br> <strong>Eigenen Account
							löschen</strong><br> Wird das Häkchen "Datenschutzbestimmungen
						widerrufen" aktiviert und "Ändern" geklickt, kann der eigene
						Account des Benutzers gelöscht werden.
					</div>
					<br>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>

	<div class="footer"></div>

</body>

</html>
