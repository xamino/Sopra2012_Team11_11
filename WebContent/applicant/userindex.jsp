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

<body onload="loadMyOffers()">
<div class="footerunten">
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="angemeldet">
			Sie sind als Bewerber angemeldet. <br> <input type="submit"
				value="Logout" onclick="doLogout();" />
		</div>
		<div id="error_login" class="hiddenerror"></div>
		<div class="nav">
			<h3>Navigation</h3>
			<div class="text">
				&rarr; Startseite <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_APPLICANT_ACCOUNTMANAGEMENT%>"
					title="Hier geht es zur Accountverwaltung">Accountverwaltung</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Startseite</h2>
				<div class="text">
					Hier finden Sie Ihre laufenden Bewerbungen und weitere angebotene
					Stellen.<br> Hilfe finden Sie <a
						href="<%=Helper.D_APPLICANT_HELP%>" title="Hilfe"> hier</a>.
				</div>
			</div>
			<hr>
			<div class="textblock">
				<div class="haupttext">
					<h4>Beworben:</h4>
					<table class="sized" id="myofferTable">
						<!-- Table for applied offers here... -->
					</table>
					<hr>
					<h4>Offene Stellen:</h4>
					<table class="sized" id="offerTable">
						<!-- Table for available offers here... -->
					</table>
				</div>
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
					onclick="apply(); togglePopup('application', false);" /> <input
					type="button" value="Abbrechen"
					onclick="togglePopup('application', false);" />
			</div>
		</form>
	</div>

</body>

</html>
