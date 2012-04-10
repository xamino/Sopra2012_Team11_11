<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/admin/script.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Dokumenten Verwaltung | Hiwi Job Börse</title>
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
					<label for="logout">Sie sind als Administrator angemeldet.</label>
				</p>
				<br>
				<p>
					<input type="button" value="Abmelden" name="logout" onclick="window.location='/hiwi/Secure/js/doLogout'"/>
				</p>
			</form>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<div class="text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_USERINDEX%>">Startseite</a><br> &rarr;
				Unterlagenverwaltung<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_ACCOUNTSMANAGEMENT%>">Accountsverwaltung</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Unterlagen Verwalten</h2>
				<div class="text">
					Erläuterungstext...<br>TODO: Format tabel to look better.
				</div>
			</div>
			<hr>
			<div class="textblock">
			<div class="haupttext">
			<div class="textblock">
				<form class="listform">
					<!-- I use form here so that I can use checkboxes – this also simplifies the call in js to get the selected boxes. -->
						<input type="button" value="Eintrag hinzufügen"
							onclick="togglePopup('document_add', true);" /> <input
							align="left" type="button" value="Eintrag ändern"
							onclick="togglePopup('document_edit', true);" /> <input
							style="float: right;" type="button"
							value="Markierte Einträge löschen"
							onclick="togglePopup('document_del', true);" />
					<hr>
					<table>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Superheldenbescheingung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Bewerbungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Kündigungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Superheldenbescheingung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Bewerbungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Kündigungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Superheldenbescheingung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Bewerbungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Kündigungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Superheldenbescheingung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Bewerbungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Kündigungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Superheldenbescheingung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Bewerbungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Kündigungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Superheldenbescheingung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Bewerbungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Kündigungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Superheldenbescheingung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Bewerbungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Kündigungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Superheldenbescheingung</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Bewerbungsnormvertrag</td>
						</tr>
						<tr>
							<td><input type="checkbox" /></td>
							<td>Kündigungsnormvertrag</td>
						</tr>
					</table>
				</form>
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
	<!-- Document edit popup -->
	<div id="document_edit" class="popup_hidden">
		<form>
			<h3>Dokument modifizieren</h3>
			<hr>
			<div class="textblock">
				Titel:<br> <input type="text" /><br>Link:<br> <input
					type="text" /><br>
				<div class="hiddenerror"></div>
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Speicher" /> <input type="button"
					value="Abbrechen" onclick="togglePopup('document_edit', false);" />
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
				<input type="button" value="Löschen" /> <input type="button"
					value="Abbrechen" onclick="togglePopup('document_del', false);" />
			</div>
		</form>
	</div>
</body>
</html>