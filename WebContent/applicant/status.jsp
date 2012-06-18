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
<title>Bewerbungsstatus | Hiwi Job Börse</title>
</head>

<body onload="selectApplication()">
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
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_APPLICANT_USERINDEX%>"
						title="Hier geht es zur Ihrer Startseite">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &rarr;
					Bewerbungen<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
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
					<h2>Bewerbungen</h2>
					<div class="text">Hier finden Sie den Bearbeitungsstatus
						Ihrer Bewerbungen.</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<div id="applications">
							<!-- <h4>Bewerbung für: WOW-TUT</h4>-->
						</div>
						<form class="listform">
							<table id="applicationsTable">
								<!-- Here come the documents -->
							</table>
						</form>
						<hr>
						<div class="float">
							<form method="post" action="mailto:email@email.com">
								<input type="submit" value="Verwalter kontaktieren" />
							</form>
						</div>
						<div class="float">
							<form method="post" action="mailto:email@email.com">
								<input type="submit" value="Anbieter kontaktieren" />
							</form>
						</div>
						<div class="float">
							<form name="stornieren">
								<input type="button" value="Bewerbung wiederrufen"
									onclick="togglePopup('application_cancel',true);" />
								<!-- <input type="submit" value="Bewerbung wiederrufen" />-->
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
	<div id="application_cancel" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen Sie die Bewerbung<br>wirklich wiederrufen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Wiederrufen"
					onclick="deleteApplication(); togglePopup('application_cancel', false);" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('application_cancel', false);" />
			</div>
		</form>
	</div>

</body>

</html>
