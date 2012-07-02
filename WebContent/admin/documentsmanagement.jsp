<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/admin/docScript.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Dokumenten Verwaltung | Hiwi Job Börse</title>
</head>
<body onload="loadDocuments();">
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
					&rarr; Unterlagenverwaltung<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_DEFOFFERVALUES%>">Normwerte Angebote</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_INSTITUTESMANAGMENT%>">Instituteverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_ACCOUNTSMANAGEMENT%>">Accountsverwaltung</a>
					<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_HELP%>" title="Hier finden Sie die Hilfe">Hilfe</a>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="rightborder">
				<div class="textblock">
					<h2>Unterlagen Verwalten</h2>
					<div class="text">
						Hier können sie die Standardunterlagen verwalten. <br>Beim
						Erstellen einer neuen Unterlage MUSS die UID eineindeutig sein.
					</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<input type="button" value="Eintrag hinzufügen"
							onclick="togglePopup('document_add', true);" /> <input
							align="left" type="button" value="Eintrag ändern"
							onclick="loadSelectedEdit();" /> <input style="float: right;"
							type="button" value="Markierten Eintrag löschen"
							onclick="prepareDelete();" />
						<div style="float: right;" class="hiddenerror"
							id="error_selection"></div>
						<br>
						<br>
						<!--<div class="clear"></div>-->
						<hr>
						<table id="documentsList">
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
	<div id="document_add" class="popup_hidden">
		<form name="addDocumentForm">
			<h3>Dokument hinzufügen</h3>
			<hr>
			<div class="textblock">
				UID: <br> <input name="uid" type="text" />
				<div id="error_addDocument_uid" class="hiddenerror"></div>
				Titel: <br>
				<textarea name="title" rows="1" cols="30"></textarea>
				<div id="error_addDocument_title" class="hiddenerror"></div>
				Beschreibung: <br>
				<textarea name="description" rows="4" cols="30"></textarea>
				<div id="error_addDocument_descr" class="hiddenerror"></div>
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Speichern" onclick="addDocument()" /> <input
					type="button" value="Abbrechen"
					onclick="clearAddDocumentPopup(); togglePopup('document_add', false);" />
			</div>
		</form>
	</div>
	<!-- Document edit popup -->
	<div id="document_edit" class="popup_hidden">
		<form name="editDocumentForm">
			<h3>Dokument modifizieren</h3>
			<hr>
			<div class="textblock">
				UID: <br> <input name="uid" type="text" disabled="disabled" />
				<div id="error_editDocument_uid" class="hiddenerror"></div>
				Titel: <br>
				<textarea name="title" rows="1" cols="30"></textarea>
				<div id="error_editDocument_title" class="hiddenerror"></div>
				Beschreibung: <br>
				<textarea name="description" rows="4" cols="30"></textarea>
				<div id="error_editDocument_descr" class="hiddenerror"></div>
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Speichern" onclick="editDocument();" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('document_edit', false);" />
			</div>
		</form>
	</div>
	<!-- Confirmation popup -->
	<div id="document_del" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen sie die ausgewählten<br>Dokumente wirklich löschen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Löschen" onclick="deleteDocument();" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('document_del', false);" />
			</div>
		</form>
	</div>
</body>
</html>
