<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
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
					<input type="button" value="Abmelden" name="logout" />
				</p>
			</form>
			<div id="error_login" class="hiddenerror"></div>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<div class="text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_USERINDEX%>">Zentrale</a><br> &rarr;
				Unterlagenverwaltung<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_ACCOUNTSMANAGEMENT%>">Accountsverwaltung</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="textblock">
			<h2>Unterlagen Verwalten</h2>
			<div class="text">Erläuterungstext ... und zwar ganz viel.
				Erläuterungstext ... und zwar ganz viel. Erläuterungstext ... und
				zwar ganz viel. Erläuterungstext ... und zwar ganz viel.
				Erläuterungstext ... und zwar ganz viel. Erläuterungstext ... und
				zwar ganz viel. Erläuterungstext ... und zwar ganz viel.
				Erläuterungstext ... und zwar ganz viel. Erläuterungstext ... und
				zwar ganz viel.</div>
		</div>
		<hr>
		<div class="textblock">
			<form class="listform">
				<div class="textblock">
					<input type="button" value="Markierte Einträge löschen" /> <input
						type="button" value="Eintrag ändern" /> <input type="button"
						value="Eintrag hinzufügen" />
				</div>
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
	<div class="clear"></div>
	<div class="footer"></div>
</body>
</html>