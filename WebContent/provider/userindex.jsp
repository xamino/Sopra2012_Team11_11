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
<title>Anbieter Startseite | Hiwi Job Börse</title>
</head>

<body>
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="angemeldet">
			Sie sind als Anbieter angemeldet. <br>
			<input type="submit" value="Logout" onclick="doLogout();"/>
		</div>				
			<div id="error_login" class="hiddenerror"></div>
		<div class="nav">
			<h3>Navigation</h3>
				<div class="text">
					&rarr; Startseite <br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_PROVIDER_ACCOUNTMANAGEMENT %>" title="Hier geht es zur Accountverwaltung">Accountverwaltung</a>
				</div>
		</div>
	</div>
	<div class="content">
	<div class="rightborder">
		<div class="textblock">
			<h2>Startseite</h2>
			<div class="text">Hier finden Sie Ihre angebotenen Stellen.<br>
			Hilfe finden Sie <a	href="<%=Helper.D_APPLICANT_HELP %>" title="Hilfe"> hier</a>.</div>
		</div>
		<hr>
		<div class="textblock">
			<div class="haupttext">
				<table class="sized">
					<tr>
						<th>Meine Stellenangebote:</th>
						<th>Bewerber/Stelle</th>
						<th>Ändern</th>
						<th>Widerrufen</th>
					</tr>
					<!-- This is how the entrys will be generated with javascript:
					<tr id="username" onclick="function(username)">
						<td>realname</td>
						<td>username</td>
						<td>account type</td>
					</tr> 
					-->
					<tr id="manohman" onclick="">
						<td>TUT xy</td>
						<td>14/10<br><input type="submit" value="Bewerberauswahl" onclick="window.location='applicantlist.jsp'" /></td>
						<td><br><input type="submit" value="Angebot ändern" /> </td>
						<td><br><input type="button" value="Angebot zurückziehen"
						onclick="togglePopup('offer_cancel',true);" /> </td>
						<!-- <td><br><input type="submit" value="Angebot zurückziehen" /> </td>-->
					</tr>
				</table><br>
				<input type="submit" value="Neues Angebot erstellen" onclick="window.location='createoffer.jsp'" />
			</div>
		</div>
	</div>
	</div>

	<div class="clear"></div>

	<div class="footer"></div>

</body>

</html>
