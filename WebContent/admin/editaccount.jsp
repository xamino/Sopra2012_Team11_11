<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/admin/script.js"></script>
<script type="text/javascript" src="/hiwi/md5.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Account Bearbeiten | Hiwi Job Börse</title>
</head>

<body onload="loadInstitute();">
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
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_INSTITUTESMANAGMENT%>">Instituteverwaltung</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_ACCOUNTSMANAGEMENT%>">Accountsverwaltung</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr;
				Account bearbeiten
				<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_ADMIN_HELP%>" title="Hier finden Sie die Hilfe">Hilfe</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Daten ändern</h2>
				<div class="text">
					Erläuterungstext ...<br>TODO: Implement that "Institut" and
					"Stellvertreter" are only active when legal.
				</div>
			</div>
			<hr>
			<div class="textblock">
				<form name="dataForm" class="listform">
					<div class="textblock">
						<input id="saveButton" type="button" value="Änderungen übernehmen"
							onclick="saveChanges(dataForm);" /> <input id="cancelButton"
							type="button" value="Änderungen verwerfen"
							onclick="window.location='accountsmanagement.jsp'" /> <input
							id="deleteButton" style="float: right;" type="button"
							value="Account löschen"
							onclick="togglePopup('account_del',true);" />
					</div>
					<hr>
					<table class="sized">
						<!-- Username can NOT be changed because primary key! -->
						<tr>
							<td>Benutzername:</td>
							<td id="userName"></td>
						</tr>
						<tr>
							<td>Name:</td>
							<td><input type="text" id="realName" name="realName"
								size="40" />
								<div id="error_realName" class="invisibleWarning"></div></td>
						</tr>
						<tr>
							<td>Passwort:</td>
							<td><input type="password" id="password" name="password" />
								<div id="error_password" class="invisibleWarning"></div></td>
						</tr>
						<tr>
							<td>Email:</td>
							<td><input type="text" id="email" name="email" size="40" />
								<div id="error_email" class="invisibleWarning"></div></td>
						</tr>
						<tr>
							<td>Accounttyp:</td>
							<td><select id="accountType" name="accountType"><option
										value="0">Administrator</option>
									<option value="1">Anbieter</option>
									<option value="2">Verwalter</option>
									<option selected="selected" value="3">Bewerber</option></select></td>
						</tr>
						<tr>
							<td>Institut:</td>
							<td><select id="institute" name="institute"></select>
								<div id="error_institute" class="invisibleWarning"></div></td>
						</tr>
						<!-- TODO: rename name... -->
						<!-- <tr>
							<td>Stellvertreter:</td>
							<td><input type="text" id="standIn" /></td>
						</tr> -->
					</table>
				</form>
			</div>
		</div>
	</div>
	</div>
	<div class="clear"></div>
	<div class="footer"></div>
	<!-- Here are the popups -->
	<!-- Confirmation account del popup -->
	<div id="account_del" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen sie den Account<br>wirklich löschen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Löschen" onclick="deleteEditAccount();" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('account_del', false);" />
			</div>
		</form>
	</div>
</body>
</html>