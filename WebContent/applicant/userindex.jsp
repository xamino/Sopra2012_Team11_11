<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<title>Bewerber Startseite | Hiwi Job Börse</title>
</head>

<body>
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="angemeldet">
			Sie sind als Bewerber angemeldet. <br>
			<input type="submit" value="Logout" />
		</div>				
			<div id="error_login" class="hiddenerror"></div>
		<div class="nav">
			<h3>Navigation</h3>
				<div class="text">
					&rarr; Startseite <br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_APPLICANT_ACCOUNTMANAGEMENT %>" title="Hier geht es zur Accountverwaltung">Accountverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_APPLICANT_STATUS %>" title="Hier finden Sie Ihre Bewerbungen">Bewerbungen</a>
				</div>
		</div>
	</div>
	<div class="content">
	<div class="rightborder">
		<div class="textblock">
			<h2>Startseite</h2>
			<div class="text">Hier finden Sie Ihre laufenden Bewerbungen und weitere angebotene Stellen.<br>
			Hilfe finden Sie <a	href="<%=Helper.D_APPLICANT_HELP %>" title="Hilfe"> hier</a>.</div>
		</div>
		<hr>
		<div class="textblock">
			<div class="haupttext">
				<h4>Beworben:</h4>
				<table class="sized">
					<tr>
						<th>Datum</th>
						<th>Bezeichnung</th>
						<th>Beschreibung</th>
					</tr>
					<!-- This is how the entrys will be generated with javascript:
					<tr id="username" onclick="function(username)">
						<td>realname</td>
						<td>username</td>
						<td>account type</td>
					</tr> 
					-->
					<tr id="mhhh" onclick="">
						<td>01.01.2001</td>
						<td>La le lu Tut</td>
						<td><div class="float2">stellen<br> lalala<br> usw</div><div class="float"><input type="submit" value="Bewerbung ansehen" /></div><div class="clear"></div></td>
					</tr>
				</table>
				<hr>
				<h4>Offene Stellen:</h4>
				<table class="sized">
					<tr>
						<th>Datum</th>
						<th>Bezeichnung</th>
						<th>Beschreibung</th>
					</tr>
					<!-- This is how the entrys will be generated with javascript:
					<tr id="username" onclick="function(username)">
						<td>realname</td>
						<td>username</td>
						<td>account type</td>
					</tr> 
					-->
					<tr id="lala" onclick="">
						<td>01.01.2001</td>
						<td>La le lu Tut</td>
						<td><div class="float2">stellen<br> lalala<br> usw</div><div class="float"><input type="submit" value="Bewerben" /></div><div class="clear"></div></td>
					</tr>
				</table>
			</div>
		</div>
	</div>
	</div>

	<div class="clear"></div>

	<div class="footer"></div>

</body>

</html>
