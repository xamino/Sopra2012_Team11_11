<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Bewerbung Bearbeiten | Hiwi Job Börse</title>
</head>
<body>
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/hiwi/Clerk/js/doExcelExport">ExcelExport</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_ACCOUNTMANAGEMENT%>">Accountverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;
				Bewerbung bearbeiten
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Bewerbung bearbeiten</h2>
				<div class="text">
					Erläuterungstext ...<br>TODO: Error divs
				</div>
			</div>
			<hr>
			<div class="textblock">
			<div class="haupttext">
				<div style="float: right;">
					<table class="hidden">
						<tr>
							<td>Name:</td>
							<td>Hans Marting</td>
						</tr>
						<tr>
							<td>Bewirbt sich für:</td>
							<td>World of Warcraft Tutorium</td>
						</tr>
						<tr>
							<td>Fachsemester:</td>
							<td>2</td>
						</tr>
						<tr>
							<td>Abschluss:</td>
							<td>Bachlor</td>
						</tr>
					</table>
					<br> <input type="button" value="Bewerbungsabschluss" />
				</div>
				<form class="listform">
					<table class="">
						<tr>
							<th></th>
							<th>Dokumente</th>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Normvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Superheldenbescheinigung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Arbeitserlaubnis</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Leistungsnachweis</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Levelbescheinigung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Internetslang für Fortgeschrittene Nachweis</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Lohnsteuerkarte</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Passbild</td>
						</tr>
					</table>
					<input type="button" value="Dokument hinzufügen"
						onclick="togglePopup('document_add',true);" /> <input
						type="button" value="Dokument löschen"
						onclick="togglePopup('document_del',true);" />
				</form>
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
				Titel:<br> <input type="text" /><br>Link:<br> <input
					type="text" /><br>
				<div class="hiddenerror"></div>
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Speicher" /> <input type="button"
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
				Wollen sie die ausgewählten<br>Dokumente wirklich löschen?
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