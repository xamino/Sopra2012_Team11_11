<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<title>Account Verwaltung | Hiwi Job Börse</title>
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
				&rarr; Accountsverwaltung
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="haupttext">
				<h2>Accounts verwalten</h2>
				<div class="text">
					Erläuterungstext...<br>TODO: Implement the selection so that
					the js saves which entry was clicked, then let the buttons react
					accordingly. Maybe add shortcuts à la doubleclick and key support
					(like del for löschen)? Remember to sort!
				</div>
			</div>
			<hr>
			<div class="textblock">
				<div class="textblock">
					<input type="button" value="Account hinzufügen"
						onclick="window.location='editaccount.jsp'" /> <input
						type="button" value="Account ändern"
						onclick="window.location='editaccount.jsp'" /> <input
						style="float: right;" type="button" value="Account löschen"
						onclick="togglePopup('account_del', true);" />
				</div>
				<hr>
				<table class="sized">
					<tr>
						<th>Benutzer Name</th>
						<th>Name</th>
						<th>Emailaddresse</th>
						<th>Account Typ</th>
					</tr>
					<!-- This is how the entrys will be generated with javascript:
					<tr id="username" onclick="function(username)">
						<td>realname</td>
						<td>username</td>
						<td>account type</td>
					</tr> 
					-->
					<tr id="Xamino" onclick="">
						<td>Xamino</td>
						<td>Tamino Hartmann</td>
						<td>tamino.hartmann@uni-ulm.de</td>
						<td>Bewerber</td>
					</tr>
					<tr id="pete" onclick="">
						<td>pete</td>
						<td>Peter Maier</td>
						<td>peter.maier@uni-ulm.de</td>
						<td>Administrator</td>
					</tr>
					<tr id="Captain_Jack" onclick="">
						<td>Captain_Jack</td>
						<td>Jack Sparrow</td>
						<td>captain.jack@black.pearl.int</td>
						<td>Anbieter</td>
					</tr>
				</table>
			</div>
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
				Wollen sie den ausgewählten<br>Account wirklich löschen?
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