<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/provider/script.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Anbieter Startseite | Hiwi Job Börse</title>
</head>

<body onload="loadOffers()">
	<div class="footerunten">
		<div class="header">
			<h1>
				<a class="banner" href="../provider/userindex.jsp">Hiwi-Börse</a>
			</h1>
		</div>
		<div class="right">
			<div class="commentform">
				<h3>Abmeldung</h3>
				<form name="abmeldung">
					<p>
						<label for="logout">Sie sind als Anbieter angemeldet.</label>
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
						href="<%=Helper.D_PROVIDER_ACCOUNTMANAGEMENT%>"
						title="Hier geht es zur Accountverwaltung">Accountverwaltung</a> <br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=Helper.D_PROVIDER_HELP%>"
						title="Hier finden Sie die Hilfe">Hilfe</a>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="rightborder">
				<div class="textblock">
					<h2>Startseite</h2>
					<div class="text">
						Hier finden Sie Ihre angebotenen Stellen und Informationen hierzu.
						Wenn sie Stellvertreter für jemanden sind werden auch dessen
						Angebote hier aufgelistet. Hier können sie auch ein neues Angebot
						erstellen. Hilfe finden Sie <a href="<%=Helper.D_PROVIDER_HELP%>"
							title="Hilfe"> hier</a>.
					</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<table class="" id="providerTable">
							<!-- Offers are listed here -->
						</table>
						<br> <input type="submit" value="Neues Angebot erstellen"
							onclick="window.location='createoffer.jsp'" />
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>

	<div class="footer"></div>

	<!-- Here are the popups -->
	<!-- Confirmation account del popup -->
	<div id="offer_cancel" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen Sie das Angebot<br>wirklich zurückziehen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Zurückziehen" id="offerZurueckziehen"
					onclick="deleteOffer(); togglePopup('offer_cancel', false);" /> <input
					type="button" value="Abbrechen"
					onclick="togglePopup('offer_cancel', false);" />
			</div>
		</form>
	</div>

</body>

</html>
