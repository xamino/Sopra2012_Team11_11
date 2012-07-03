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
<title>Bewerberliste | Hiwi Job Börse</title>
</head>

<body onload="applicantChoice()">
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
					Bewerbungsverwaltung <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
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
					<h2>Bewerberliste</h2>
					<div class="text">Hier sehen Sie alle Bewerber, die sich auf das Angebot beworben haben.<br>Des Weiteren können Sie hier Bewerber annehmen.</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<h4 id="freeslots">Bewerbungen</h4>
						<table class="sized" id="applicantsTable">
							<!-- <tr>
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
							<!-- <tr id="lalelu" onclick="">
						<td><input type="checkbox" /></td>
						<td>Herrrrrmann</td>
						<td>100</td>
						<td>nix</td>
					</tr>-->
						</table>
						<input type="button" value="Annehmen" id="annehmen_button"
							onclick="togglePopup('accept',true);" />
						<div id="error_already_chosen" class="hiddenerror"></div>
						<div id="error_noAppSelected" class="hiddenerror"></div>
						<!-- <input type="submit" value="Ausgewählte annehmen" />-->
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>

	<div class="footer"></div>

	<!-- Here are the popups -->
	<!-- Confirmation account del popup -->
	<div id="accept" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen Sie den/ die<br>Bewerber wirklich annehmen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Annehmen"
					onclick="takeSelectedApplicant(); togglePopup('accept', false);" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('accept', false);" />
			</div>
		</form>
	</div>

</body>

</html>
