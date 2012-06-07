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
<title>Sachbearbeiter Startseite | Hiwi Job Börse</title>
</head>

<body onload="loadInfo()">
<div class="footerunten">
	<div class="header">
		<h1>
			<a class="banner" href="../clerk/userindex.jsp">Hiwi-Börse</a>
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
				&rarr; Startseite<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					onclick="doExcelExport();">ExcelExport</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_ACCOUNTMANAGEMENT%>">Accountverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Willkommen in ihrer persönlichen Zentrale</h2>
				<div class="text">
					Erläuterungstext ...<br>TODO: Fill with sensible information.<br>
					Hilfe finden Sie <a	href="<%=Helper.D_CLERK_HELP %>" title="Hilfe"> hier</a>.
				</div>
			</div>
			<hr>
			<div class="textblock">
			<div class="haupttext">
				<table class="hidden">
					<tr>
						<td>Ungeprüfte Angebote:</td>
						<td id=offers></td>
					</tr>
					<tr>
						<td>Offene Bewerbungen:</td>
						<td id=apps></td>
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