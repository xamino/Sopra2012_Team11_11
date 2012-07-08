<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/provider/createOfferScript.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Angebot erstellen | Hiwi Job Börse</title>
</head>

<body onload="loadDefValues();">
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
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_PROVIDER_USERINDEX%>"
						title="Hier geht es zur Ihrer Startseite">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;
					Angebot erstellen <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
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
					<h2>Angebot erstellen</h2>
					<div class="text">
						Hier können Sie ein neues Angebot erstellen. Füllen Sie hierzu
						alle Felder aus und klicken Sie auf "Angebot einstellen". Bitte
						beachten sie, dass der Lohn nur vom Sachbearbeiter gesetzt werden
						kann.<br> <br> Sobald der Verwalter dem Angebot
						zugestimmt hat, wird dieses in der Hiwi-Börse aufgeführt.
					</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<div class="register">
							<form id="angebotErstellen" name="angebotErstellen">
								<div class="regform2">Titel:</div>
								<input type="text" id="titel" name="titel" size="57"
									maxlength="100" />
								<!-- Oemer -->
								<div id="error_titel" class="invisibleWarning"></div>
								<!-- Oemer -->
								<table class="hidden">
									<tr>
										<td>Std/Monat:</td>
										<td><input type="text" id="std" name="std" size="20"
											maxlength="100" />
											<div id="error_std" class="invisibleWarning"></div></td>
										<td>Stellen:</td>
										<td><input type="text" id="stellen" name="stellen"
											size="20" maxlength="100" />
											<div id="error_stellen" class="invisibleWarning"></div></td>
									</tr>
									<tr>
										<td>Start Datum:</td>
										<td><input type="text" name="startDate" size="20"
											maxlength="100" />
											<div id="error_startDate" class="invisibleWarning"></div></td>
										<td>End Datum:</td>
										<td><input type="text" name="endDate" size="20"
											maxlength="100" />
											<div id="error_endDate" class="invisibleWarning"></div></td>
									</tr>
									<tr>
										<td>Standard Lohn:</td>
										<td id="wage"></td>
										<td></td>
										<td></td>
									</tr>
								</table>
								<strong>Beschreibung:</strong>
								<div id="error_beschreibung" class="invisibleWarning"></div>
								<textarea id="beschreibung" name="beschreibung" cols="50"
									rows="10"></textarea>
								<br> <br> <strong>Notiz für Sachbearbeiter:</strong>
								<div id="error_notiz" class="invisibleWarning"></div>
								<textarea id="notiz" name="notiz" cols="50" rows="10"></textarea>
								<br> <br>
								<p>
									<input type="reset" value="Zurücksetzen" /> <input
										type="button" value="Angebot einstellen"
										onclick="togglePopup('create_offer',true);" />
									<!-- <input type="submit" value="Angebot einstellen" />-->
								</p>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>

	<div class="footer"></div>

	<!-- Here are the popups -->
	<!-- Confirmation account del popup -->
	<div id="create_offer" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen Sie das Angebot<br>wirklich einstellen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Einstellen"
					onclick="addOffer(angebotErstellen);togglePopup('create_offer', false);" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('create_offer', false);" />
			</div>
		</form>
	</div>
</body>
</html>
