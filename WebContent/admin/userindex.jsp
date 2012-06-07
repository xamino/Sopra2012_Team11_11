<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/admin/indexScript.js"></script>
<script type="text/javascript" src="/hiwi/md5.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Administrator Startseite | Hiwi Job Börse</title>
</head>
<body onload="timedInfoLoop();">
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
				&rarr; Startseite<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_DOCUMENTSMANAGEMENT%>">Unterlagenverwaltung</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_INSTITUTESMANAGMENT%>">Instituteverwaltung</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_ACCOUNTSMANAGEMENT%>">Accountsverwaltung</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Willkommen in ihrer persönlichen Zentrale</h2>
				<div class="text">
					Erläuterungstext ...<br>TODO: Fill with what we really can
					show. Consider live update? <br> Hilfe finden Sie <a
						href="<%=Helper.D_ADMIN_HELP%>" title="Hilfe"> hier</a>.
				</div>
			</div>
			<hr>
			<div class="textblock">
				<div class="haupttext">
					<table class="hidden">
						<tr>
							<td>Angemeldete Benutzer:</td>
							<td id="loggedInUsers"></td>
						</tr>
						<tr>
							<td>Registrierte Benutzer:</td>
							<td id="allUsers"></td>
						</tr>
						<tr>
							<td>Total RAM:</td>
							<td id="totalRAM"></td>
						</tr>
				
						<tr>
							<td>Max RAM:</td>
							<td id="maxRAM"></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	</div>
	<div class="clear"></div>
	<div class="footer"></div>
</body>
</html>