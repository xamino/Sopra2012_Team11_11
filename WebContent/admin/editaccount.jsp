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
					<label for="logout">Sie sind als Administrator angemeldet.</label>
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
					href="<%=Helper.D_ADMIN_USERINDEX%>">Zentrale</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_DOCUMENTSMANAGEMENT%>">Unterlagenverwaltung</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_ACCOUNTSMANAGEMENT%>">Accountsverwaltung</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;
				Account bearbeiten
			</div>
		</div>
	</div>
	<div class="content">
		<div class="textblock">
			<h2>Daten ändern</h2>
			<div class="text">
				Erläuterungstext ...<br>TODO: Implement that "Institut" and
				"Stellvertreter" are only active when legal.
			</div>
		</div>
		<hr>
		<div class="textblock">
			<form class="listform">
				<div class="textblock">
					<input type="button" value="Änderungen übernehmen" /> <input
						type="button" value="Änderungen verwerfen"
						onclick="window.location='accountsmanagement.jsp'" /> <input
						style="float: right;" type="button" value="Account löschen"
						onclick="togglePopup('account_del',true);" />
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
						<td>Accounttyp:</td>
						<td><input type="text" value="Bewerber" /></td>
					</tr>
					<tr>
						<td>Institut:</td>
						<td><input type="text" value="Student" /></td>
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
	<!-- Here are the popups -->
	<!-- Confirmation account del popup -->
	<div id="account_del" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen sie den Account<br>wirklich löschen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Löschen" /> <input type="button"
					value="Abbrechen" onclick="togglePopup('account_del', false);" />
			</div>
		</form>
	</div>
</body>

</html>
