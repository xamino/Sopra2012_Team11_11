<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/admin/script.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Account Verwaltung | Hiwi Job Börse</title>
</head>
<body onload="loadAccounts();">
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
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
						onclick="window.location='/hiwi/Secure/js/doLogout'" />
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
				&rarr; Accountsverwaltung
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Accounts verwalten</h2>
				<div class="text">
					Hier können Sie die im System vorhandenen Accounts einsehen,
					verändern, und löschen.<br>TODO: Maybe add shortcuts à la
					doubleclick and key support (like del for löschen).
				</div>
			</div>
			<hr>
			<div class="haupttext">
				<div class="textblock">
					<!-- The URLs to editaccount.jsp are set with parameters so that the page reacts correctly. -->
					<input type="button" value="Account hinzufügen"
						onclick="window.location='editaccount.jsp?mode=add'" /> <input
						type="button" value="Account ändern" onclick="checkLegalEdit();" />
					<input style="float: right;" type="button" value="Account löschen"
						onclick="togglePopup('account_del', true);" />
					<div style="float: right;" class="hiddenerror" id="error_selection"></div>
					<div class="clear"></div>
					<hr>
					<table class="sized" id="accountTable">
						<!-- This is how the entrys are generated with javascript:
					<tr id="username" onclick="function(username)">
						<td>username</td>
						<td>realname</td>
						<td>email</td>
						<td>account type</td>
					</tr> 
					-->
					</table>
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
				Wollen sie den ausgewählten<br>Account wirklich löschen?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Löschen"
					onclick="deleteSelectedAccount(); togglePopup('account_del', false);" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('account_del', false);" />
			</div>
		</form>
	</div>
</body>
</html>