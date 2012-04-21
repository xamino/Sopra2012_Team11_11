<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/clerk/script.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Angebot Bearbeiten | Hiwi Job Börse</title>
</head>

<body onload="editOneOffer()">
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="commentform">
			<h3>Abmeldung</h3>
			<form name="abmeldung">
				<p>
					<label for="logout">Sie sind als Sachbearbeiter angemeldet.</label>
				</p>
				<br>
				<p>
					<input type="button" value="Abmelden" name="logout" onclick="doLogout();"/>
				</p>
			</form>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<div class="text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_USERINDEX%>">Startseite</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="doExcelExport();">ExcelExport</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_ACCOUNTMANAGEMENT%>">Accountverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;
				Angebot bearbeiten<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Angebot bearbeiten</h2>
				<div class="text">
					Erläuterungstext ...<br>TODO: Form implementation is lacking –
					do we even need forms? Maybe everything can be done with
					javascript, as we have to check for validity anyway. Implmenent
					mouseover highlight for table list on right side. Error divs.
				</div>
			</div>
			<hr>
			<div class="textblock">
			<div class="haupttext">
				<div class="bordered_float">
					<table class="">
						<tr>
							<th>Benötigte Dokumente:</th>
						</tr>
						<tr>
							<td>Bewerbung</td>
						</tr>
						<tr>
							<td>Normvertrag</td>
						</tr>
						<tr>
							<td>Glaubensbescheinigung</td>
						</tr>
						<tr>
							<td>Sonderbescheid</td>
						</tr>
					</table>
					<input type="button" value="Dokument hinzufügen"
						onclick="togglePopup('document_add',true);" /> <input
						type="button" value="Dokument löschen"
						onclick="togglePopup('document_del',true);" />
				</div>
				<table class="hidden">
					<tr>
						<td>Name des Veranstalters:</td>
						<td>Prof. Doc. Gott</td>
					</tr>
					<tr>
						<td>Titel der Stelle:</td>
						<td>Tutorium Lebensphilosophie</td>
					</tr>
					<tr>
						<td>Plätze:</td>
						<td>20</td>
					</tr>
					<tr>
						<td>Stunden die Woche:</td>
						<td><input type="text" /> std.</td>
					</tr>
					<tr>
						<td>Lohn:</td>
						<td><input type="text" />€</td>
					</tr>
					<tr>
						<td>Anbieternotiz:</td>
						<td style="background-color: lightgray;">Hallo!<br> <br>Wie
							besprochen, hier das Angebot. Ich hoffe, diesmal stimmt alles. :D<br>
							<br>Grüße,<br>Hr. Gott
						</td>
					</tr>
				</table>
				<hr>
					<div style="float: right;">
						<input type="button" value="Mail an Anbieter" />
					</div>
					<input type="button" id="angebotbestaetigen" value="Angebot bestätigen" onclick="angebotbestaetigen()"/> <input
						type="button" id="angebotablehnen" value="Angebot ablehnen" onclick="angebotablehnen()"/>
			</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="footer"></div>
	<!-- Here are the popups -->
	<!-- Document add popup -->
	<div id="document_add" class="popup_hidden">
		<form>
			<h3>Dokument hinzufügen</h3>
			<hr>
			<div class="textblock">
				Titel:<br> <input type="text" /><br>
				<div class="hiddenerror"></div>
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Speichern" /> <input type="button"
					value="Abbrechen" onclick="togglePopup('document_add', false);" />
			</div>
		</form>
	</div>
	<!-- Document del popup -->
	<div id="document_del" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen sie das ausgewählte<br>Dokument wirklich löschen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Löschen" /> <input type="button"
					value="Abbrechen" onclick="togglePopup('document_del', false);" />
			</div>
		</form>
	</div>
</body>
</html>