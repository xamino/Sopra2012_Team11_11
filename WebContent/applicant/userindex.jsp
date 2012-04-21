<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/applicant/indexscript.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Bewerber Startseite | Hiwi Job Börse</title>
</head>

<body onload="loadOffers()">
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="angemeldet">
			Sie sind als Bewerber angemeldet. <br>
			<input type="submit" value="Logout" onclick="doLogout();"/>
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
				<table class="sized" id="myofferTable">
					<!-- <tr>
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
					<!-- <tr id="mhhh" onclick="">
						<td>01.01.2001</td>
						<td>La le lu Tut</td>
						<td><div class="float2">stellen<br> lalala<br> usw</div><div class="float"><input type="submit" value="Bewerbung ansehen" onclick="window.location='status.jsp'" /></div><div class="clear"></div></td>
					</tr>-->
				</table>
				<hr>
				<h4>Offene Stellen:</h4>
				<table class="sized" id="offerTable">
					<!-- <tr>
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
					<!-- <tr id="lala" onclick="">
						<td>01.01.2001</td>
						<td>La le lu Tut</td>
						<td><div class="float2">stellen<br> lalala<br> usw</div><div class="float"><input type="button" value="Bewerben"
						onclick="togglePopup('application',true);" /> </div><div class="clear"></div></td>
					</tr>-->
				</table>
			</div>
		</div>
	</div>
	</div>

	<div class="clear"></div>

	<div class="footer"></div>

	<!-- Here are the popups -->
	<!-- Confirmation account del popup -->
	<div id="application" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen Sie sich<br>wirklich bewerben?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Bewerben"
					onclick="deleteSelectedAccount(); togglePopup('application', false);" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('application', false);" />
			</div>
		</form>
	</div>

</body>

</html>
