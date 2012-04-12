<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="/hiwi/style.css" />
<script type="text/javascript" src="/hiwi/public/script.js"></script>
<script type="text/javascript" src="/hiwi/md5.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Startseite | Hiwi Job Börse</title>
</head>

<body>
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="commentform">
			<h3>Anmeldung</h3>
			<form name="login">
				<span> <label for="userName">Benutzername</label>
				</span>
				<div class="form">
					<input type="text" name="userName" size="20" maxlength="100" />
				</div>
				<br> <span> <label for="userPassword">Passwort</label>
				</span>
				<div class="form">
					<input type="password" name="userPassword" size="20"
						maxlength="100" />
				</div>
				<div class="clear"></div>
				<div class="text">
					<div id="error_login" class="hiddenerror"></div>
				</div>
				<p>
					<!-- return false on submit so that the form doesn't reload the form – this is handled
					by the javascript function accordingly. -->
					<input type="reset" value="Zurücksetzen"
						onclick="toggleWarning('error_login', false, '')" /> <input
						type="submit" value="Anmelden"
						onclick="checkLogin(login); return false;" />
				</p>
			</form>
			<a class="hintlink" href="register.jsp"
				title="Hier geht es zum Registrieren">Noch nicht registriert?</a> <a
				class="hintlink" href="" title="neues Passwort">Passwort
				vergessen?</a>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<div class="text">
				&rarr; Startseite <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_PUBLIC_REGISTER%>"
					title="Hier geht es zum Registrieren">Registrieren</a><br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=Helper.D_PUBLIC_HELP%>"
					title="Hier finden Sie die Hilfe">Hilfe</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Willkommen auf der Hiwi-Börse</h2>
				<div class="text">Hier finden Sie alle im Moment angebotenen Stellen. <br> Um sich zu bewerben, registrieren Sie sich bitte, oder loggen Sie sich ein.</div>
			</div>
			<hr>
			<div class="textblock">
				<div class="haupttext">
				<table class="sized">
					<tr>
						<th>Datum</th>
						<th>Bezeichnung</th>
						<th>Beschreibung</th>
					</tr>
					<!-- This is how the entrys will be generated with javascript:
					<tr id="username" onclick="function(username)">
						<td>realname</td>
						<td>username</td>
						<td>account type</td>
					</tr> 
					-->
					<tr id="huhu" onclick="">
						<td>01.01.2001</td>
						<td>La le lu Tut</td>
						<td>stellen<br> lalala<br> usw</td>
					</tr>
				</table>
				</div>
			</div>
		</div>
	</div>

	<div class="clear"></div>

	<div class="footer"></div>

</body>

</html>
