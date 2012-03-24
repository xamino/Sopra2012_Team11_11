<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<title>Account Bearbeiten | Hiwi Job Börse</title>
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
					<input type="button" value="Abmelden" name="logout" />
				</p>
			</form>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<div class="text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_USERINDEX%>">Zentrale</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="/hiwi/Clerk/js/doExcelExport">ExcelExport</a><br>
				&rarr; Accountverwaltung<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="textblock">
			<h2>Account verwalten</h2>
			<div class="text">
				Erläuterungstext ...<br>TODO: "Stellvertreter" should have
				better field for none than "null" – maybe like "keinen"?
			</div>
		</div>
		<hr>
		<div class="textblock">
			<form class="listform">
				<div class="textblock">
					<input type="button" value="Änderungen übernehmen" /> <input
						type="button" value="Änderungen verwerfen" onclick="window.location='userindex.jsp'"/>
					<!-- Shouldn't there be a delete option here too? 
						<input style="float: right;" type="button" value="Account löschen" />
					-->
				</div>
				<hr>
				<table class="sized">
					<tr>
						<td>Name:</td>
						<td><input type="text" value="Max Mustermann" size="40" /></td>
					</tr>
					<!-- TODO: username has a max size – use it here too -->
					<tr>
						<td>Benutzername:</td>
						<td><input type="text" value="max_the_great" /></td>
					</tr>
					<tr>
						<td>Email:</td>
						<td><input type="text" value="max.mustermann@uni-ulm.de"
							size="40" /></td>
					</tr>
					<tr>
						<td>Passwort:</td>
						<td><input type="password" value="********" /></td>
					</tr>
					<tr>
						<td>Passwort bestätigen:</td>
						<td><input type="password" value="********" /></td>
					</tr>
					<tr>
						<td>Stellvertreter:</td>
						<td><input type="text" value="null" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
	<div class="footer"></div>
</body>
</html>