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
				<a class="banner" href="../applicant/userindex.jsp">Hiwi-Börse</a>
			</h1>
		</div>
		<div class="right">
			<div class="commentform">
				<h3>Abmeldung</h3>
				<form name="abmeldung">
					<p>
						<label for="logout">Sie sind als Bewerber angemeldet.</label>
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
					&rarr; Startseite <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_APPLICANT_ACCOUNTMANAGEMENT%>"
						title="Hier geht es zur Accountverwaltung">Accountverwaltung</a> <br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_APPLICANT_HELP%>"
						title="Hier finden Sie die Hilfe">Hilfe</a>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="rightborder">
				<div class="textblock">
					<h2>Startseite</h2>
					<div class="text">
						In der oberen Tabelle finden Sie jene Angebote, auf die sie sich
						beworben haben. In der unteren Tabelle finden sie alle Angebote,
						zu denen sie sich nicht beworben haben. Hilfe finden Sie <a
							href="<%=Helper.D_APPLICANT_HELP%>" title="Hilfe"> hier</a>.
					</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<h4>Beworbene Angebote:</h4>
						<table id="myofferTable">
							<!-- Table for applied offers here... -->
						</table>
						<hr>
						<br>
						<h4>Angebote mit offenen Stellen:</h4>
						<table id="offerTable">
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
