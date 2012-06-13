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
<title>Bewerbungen Verwalten | Hiwi Job Börse</title>
</head>

<body onload="showApplication()">
<div class="footerunten">
	<div class="header">
		<h1>
			<a class="banner" href=../provider/userindex.jsp>Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="commentform">
			<h3>Abmeldung</h3>
			<form name="abmeldung">
				<p>
					<label for="logout">Sie sind als Sachbearbeiter angemeldet.</label>
				</p>
				<br>
				<p>
					<input type="button" value="Abmelden" name="logout" onclick="doLogout();"/>
				</p>
			</form>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<div class="text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_USERINDEX%>">Startseite</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="doExcelExport();">ExcelExport</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_ACCOUNTMANAGEMENT%>">Accountverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>&rarr;
				Bewerbungsverwaltung
				<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_HELP%>" title="Hier finden Sie die Hilfe">Hilfe</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Bewerbungen verwalten</h2>
				<div class="text">
					Erläuterungstext ...<br>TODO: Button that leads to
					editapplication view MUST transfer data!
				</div>
			</div>
			<hr>
			<div class="textblock">
			<div class="haupttext">
				<form class="listform">
						<input type="button" value="Bewerbung bearbeiten" id="editapplication" />
							<!-- onclick="window.location='editapplication.jsp'"--> 
							<div id="error_noOfferSelected" class="hiddenerror"></div>
					<hr>
					<table id="applicationTable">
						<tr>
							<!-- <th></th>
							<th>Name des Bewerbers</th>
							<th>Bewibt sich für</th>
							<th>Fachsemester</th>
							<th>Abschluss</th>
						</tr>
						<tr>
							<td><input type="radio" name="select" /></td>
							<td>Issac Newton</td>
							<td>Physik Tutorium</td>
							<td>5</td>
							<td>Bachlor</td>
						</tr>
						<tr>
							<td><input type="radio" name="select" /></td>
							<td>Hans Martin</td>
							<td>World of Warcraft Tutorium</td>
							<td>2</td>
							<td>Bachlor</td>
						</tr>
						<tr>
							<td><input type="radio" name="select" /></td>
							<td>Hubschraub Bär</td>
							<td>Maschienenbau Tutorium</td>
							<td>1</td>
							<td>Master</td>
						</tr>-->
					</table>
				</form>
			</div>
			</div>
		</div>
	</div>
	</div>
	<div class="clear"></div>
	<div class="footer"></div>
</body>
</html>