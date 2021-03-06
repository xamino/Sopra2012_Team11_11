<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/clerk/editAppScript.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Bewerbung Bearbeiten | Hiwi Job Börse</title>
</head>
<body onload="applicationDocuments()">
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
						href="<%=Helper.D_CLERK_USERINDEX%>">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="doExcelExport();">ExcelExport</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_ACCOUNTMANAGEMENT%>">Accountverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;
					Bewerbung bearbeiten <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_HELP%>" title="Hier finden Sie die Hilfe">Hilfe</a>
				</div>
			</div>

		</div>

		<div class="content">
			<div class="rightborder">
				<div class="textblock">
					<h2>Bewerbung bearbeiten</h2>
					<div class="text">
						Hier können Sie alle für eine Bewerbung benötigten Unterlagen
						bearbeiten. Des Weiteren können Sie Unterlagen als abgegeben
						markieren.<br> Hier können Sie auch die Bewerbung
						abschließen.
					</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<div style="float: right;">
							<table class="hidden" id="applicantTable">
								<!-- Info goes here -->
							</table>
							<br> <input type="button" value="Bewerbungsabschluss"
								onclick="doApplicationCompletion();" />
								<div id="error_appcompl" class="popup_hidden"></div>
						</div>
						<table class="" id="documentsTable">
							<!-- Documents go here -->
						</table>
						<input type="button" value="Dokument hinzufügen"
							id="dokumentHinzufuegenButton"
							onclick="togglePopup('document_add',true);" /> <input
							type="button" value="Dokument löschen"
							id="dokumentloeschenbutton"
							onclick="togglePopup('document_del',true);" />
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
		<form name="addDocumentForm" id="adddocform">
			<h3>Dokument hinzufügen</h3>
			<hr>
			<select id="selectAppDocumentsToAdd" size="1">
				<!-- <option value="wert1">Eintrag1</option>
				<option value="wert2">Eintrag2</option> -->
			</select>
			<div class="textblock">
				<!--  UID: <br> <input name="uid" type="text" /> -->
				<div id="error_addDocument_uid" class="hiddenerror"></div>
				<!--Titel: <br>
				<textarea name="title" rows="1" cols="30"></textarea>
				<div id="error_addDocument_title" class="hiddenerror"></div>
				Beschreibung: <br>
				<textarea name="description" rows="4" cols="30"></textarea>
				<div id="error_addDocument_descr" class="hiddenerror"></div>  -->
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Speichern" onclick="addDocument()" />
				<!--<input type="button" value="Erstellen" onclick="createDocument()" />-->
				<input type="button" value="Abbrechen"
					onclick="togglePopup('document_add', false);" />
			</div>
		</form>
	</div>
	<!-- Document del popup -->
	<div id="document_del" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen sie die ausgewählten<br>Dokumente wirklich löschen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Löschen" onclick="deleteAppDocument();" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('document_del', false);" />
			</div>
		</form>
	</div>
	
</body>
</html>