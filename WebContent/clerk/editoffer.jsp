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
					<label for="logout">Sie sind als Sachbearbeiter angemeldet.</label>
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
					href="<%=Helper.D_CLERK_USERINDEX%>">Startseite</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="doExcelExport();">ExcelExport</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_ACCOUNTMANAGEMENT%>">Accountverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;
				Angebot bearbeiten<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a>
					<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_HELP%>" title="Hier finden Sie die Hilfe">Hilfe</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Angebot bearbeiten</h2>
				<div class="text">
					Hier sehen Sie die Details des gewählten Angebots.<br>Sie können das Angebot bestätigen und abändern.<br>
					Des Weiteren werden hier die benötigten Unterlagen zum jeweiligen Angebot aufgeführt. Auch diese können Sie bearbeiten.
				</div>
			</div>
			<hr>
			<div class="textblock">
				<div class="haupttext">
					<div class="bordered_float">
						<table id="documentsTable">
							<!-- This table holds the documents attached to the offer. -->
						</table>
						<input type="button" value="Dokument hinzufügen"
							onclick="togglePopup('document_add',true);" /> <input
							type="button" value="Dokument löschen"
							id="dokumentloeschenbutton"
							onclick="togglePopup('document_del',true);" />
					</div>
					<table id="offerinfotable" class="hidden">
						<!-- This table holds all the information of the offer. -->
					</table>
					<hr>
					<div style="float: right;">
						<input type="button" value="Mail an Anbieter" id="mailToAuthorButton"/>
					</div>
					<input type="button" id="angebotbestaetigen"
						value="Angebot bestätigen" onclick="togglePopup('offer_approve',true);" /> 
					<input
						type="button" id="angebotablehnen" value="Angebot ablehnen"
						onclick="togglePopup('offer_reject',true)" />
					<input
						type="button" id="angebotspeichern" value="Angebot Speichern"
						onclick="angebotspeichern()" />
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="clear"></div>
	<div class="footer"></div>
	<!-- Here are the popups -->
	<!-- Document add popup -->
	<div id="document_add" class="popup_hidden">
		<form name="docSpeichernForm">
			<h3>Dokument hinzufügen</h3>
			<hr>
			<select id="selectDocumentsToAdd" size="1">
				<!-- <option value="wert1">Eintrag1</option>
				<option value="wert2">Eintrag2</option> -->
			</select>
			<hr>
			<div class="textblock">
				<input type="button" value="Speichern" id="docSpeichern"
					onclick="addChosenDocument();" /> <input type="button"
					value="Abbrechen" onclick="togglePopup('document_add', false);" />
			</div>
		</form>
	</div>
	<!-- Document del popup -->
	<div id="document_del" class="popup_hidden">
		<form name="docLoeschenForm">
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen sie das ausgewählte<br>Dokument wirklich löschen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Löschen" id="docLöschen"
					onclick="deleteChosenDocument();" /> <input type="button"
					value="Abbrechen" onclick="togglePopup('document_del', false);" />
			</div>
		</form>
	</div>
	<!-- Approve offer popup -->
	<div id="offer_approve" class="popup_hidden">
			<h3>Angebot bestätigen</h3>
			<hr>
			<div class="textblock">
				Wollen sie das ausgewählte<br>Angebot wirklich bestätigen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Bestätigen" id="angebotBestaetigen"
					onclick="angebotbestaetigen();" /> <input type="button"
					value="Abbrechen" onclick="togglePopup('offer_approve', false);" />
			</div>
	</div>
	<!-- Reject offer popup -->
	<div id="offer_reject" class="popup_hidden">
			<h3>Angebot ablehnen</h3>
			<hr>
			<div class="textblock">
				Wollen sie das ausgewählte<br>Angebot wirklich ablehnen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Ablehnen" id="angebotAblehnen"
					onclick="angebotablehnen();" /> <input type="button"
					value="Abbrechen" onclick="togglePopup('offer_reject', false);" />
			</div>
	</div>
	
</body>
</html>