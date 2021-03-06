<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/admin/defScript.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Normwerte für Angebote | Hiwi Job Börse</title>
</head>
<body onload="loadDefValues();">
	<div class="footerunten">
		<div class="header">
			<h1>
				<a class="banner" href="../admin/userindex.jsp">Hiwi-Börse</a>
			</h1>
		</div>
		<div class="right">
			<div class="commentform">
				<h3>Abmeldung</h3>
				<form name="abmeldung">
					<p>
						<label for="logout">Sie sind als Administrator angemeldet.</label>
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
						href="<%=Helper.D_ADMIN_USERINDEX%>">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_DOCUMENTSMANAGEMENT%>">Unterlagenverwaltung</a><br>
					&rarr; Normwerte Angebote<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_INSTITUTESMANAGMENT%>">Instituteverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_ACCOUNTSMANAGEMENT%>">Accountsverwaltung</a>
					<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_ADMIN_HELP%>" title="Hier finden Sie die Hilfe">Hilfe</a>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="rightborder">
				<div class="textblock">
					<h2>Normwerte für Angebote</h2>
					<div class="text">Diese Seite dient zur Einrichtung von
						Standardwerten für Anbieter bei Erstellung eines neuen Angebots.</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<form name="inputValForm">
							<table class="hidden">
								<tr>
									<td>Standard Anzahl der Stunden<br>pro Monat:
									</td>
									<td><input type="text" name="hoursMonth" size="4" />std.
										<div id="error_hoursMonth" class="invisibleWarning"></div></td>
								</tr>
								<tr>
									<td>Startdatum des Vertrags:</td>
									<td><input type="text" name="startDate" />
										<div id="error_startDate" class="invisibleWarning"></div></td>
								</tr>
								<tr>
									<td>Enddatum des Vertrags:</td>
									<td><input type="text" name="endDate" />
										<div id="error_endDate" class="invisibleWarning"></div></td>
								</tr>
								<tr>
									<td>Standard Lohn:</td>
									<td><input type="text" name="wage" />€/std.
										<div id="error_wage" class="invisibleWarning"></div></td>
								</tr>
							</table>
							<input type="submit" value="Werte Speichern"
								onclick="checkVal(); return false;" />
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