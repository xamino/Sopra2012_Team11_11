<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<title>Bewerberliste | Hiwi Job Börse</title>
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
			<input type="submit" value="Logout" onclick="window.location='/hiwi/Secure/js/doLogout'"/>
		</div>				
			<div id="error_login" class="hiddenerror"></div>
		<div class="nav">
			<h3>Navigation</h3>
				<div class="text">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_PROVIDER_USERINDEX %>" title="Hier geht es zur Ihrer Startseite">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr; Bewerbungsverwaltung <br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_PROVIDER_ACCOUNTMANAGEMENT %>" title="Hier geht es zur Accountverwaltung">Accountverwaltung</a>
				</div>
		</div>
	</div>
	<div class="content">
	<div class="rightborder">
		<div class="textblock">
			<h2>Bewerberliste</h2>
			<div class="text">
			Hier sehen Sie Ihre Bewerber.
			</div>
		</div>
		<hr>
				<div class="textblock">
			<div class="haupttext">
			<h4>Bewerbungen für: WOW-TUT</h4>
				<table class="sized">
					<tr>
						<th></th>
						<th>Name</th>
						<th>Fachsemester</th>
						<th>Abschluss</th>
					</tr>
					<!-- This is how the entrys will be generated with javascript:
					<tr id="username" onclick="function(username)">
						<td>realname</td>
						<td>username</td>
						<td>account type</td>
					</tr> 
					-->
					<tr id="lalelu" onclick="">
						<td><input type="checkbox" /></td>
						<td>Herrrrrmann</td>
						<td>100</td>
						<td>nix</td>
					</tr>
				</table>
				<input type="submit" value="Ausgewählte annehmen" />
			</div>
		</div>
</div>
</div>

	<div class="clear"></div>

	<div class="footer"></div>

</body>

</html>
