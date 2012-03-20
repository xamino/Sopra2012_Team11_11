<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<title>Angebote Verwalten | Hiwi Job Börse</title>
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_ACCOUNTMANAGEMENT%>">Accountverwaltung</a><br>&rarr;
				Angebotsverwaltung<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="textblock">
			<h2>Angebote verwalten</h2>
			<div class="text">
				Erläuterungstext ...<br>TODO:
			</div>
		</div>
		<hr>
		<div class="textblock">
			<form class="listform">
				<div class="textblock">
					<input type="button" value="Angebot prüfen" />
				</div>
				<hr>
				<table>
					<tr>
						<th></th>
						<th>Name des Zuständigen</th>
						<th>Fach</th>
						<th>Plätze</th>
						<th>Stunden pro Woche</th>
					</tr>
					<tr>
						<td><input type="radio" name="select" /></td>
						<td>Prof. Doc. Gott</td>
						<td>Lebesphilosophie</td>
						<td>20</td>
						<td>8</td>
					</tr>
					<tr>
						<td><input type="radio" name="select" /></td>
						<td>Prof. Langweile</td>
						<td>Physik für Einsteiger</td>
						<td>10</td>
						<td>4</td>
					</tr>
					<tr>
						<td><input type="radio" name="select" /></td>
						<td>Prof. Ungeprüft</td>
						<td>Programmierung von Systemen</td>
						<td>15</td>
						<td>6</td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div class="clear"></div>
	<div class="footer"></div>
</body>
</html>