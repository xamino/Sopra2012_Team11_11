<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="md5.js"></script>
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
					<input type="password" name="userPassword" size="20" maxlength="100" />
				</div>
				<div class="clear"></div>
				<div id="error_login" class="hiddenerror"></div>
				<p>
					<!-- return false on submit so that the form doesn't reload the form – this is handled
					by the javascript function accordingly. -->
					<input type="reset" value="Zurücksetzen" /> <input type="submit"
						value="Anmelden" onclick="checkLogin(login); return false;" />
				</p>
			</form>
			<a class="hintlink" href="register.jsp"
				title="Hier geht es zum Registrieren">Noch nicht registriert?</a> <a
				class="hintlink" href="" title="neues Passwort">Passwort
				vergessen?</a>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<ul>
				<li>→ Startseite</li>
				<li><a href="register.jsp"
					title="Hier geht es zum Registrieren">Registrieren</a></li>
				<li><a href="help.jsp" title="Hier finden Sie die Hilfe">Hilfe</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Willkommen auf der Hiwi-Börse</h2>
				<div class="text">Erläuterungstext ...</div>
			</div>
			<hr>
			<div class="textblock">
				<div class="haupttext">Hier steht Kram!!</div>
			</div>
		</div>
	</div>

	<div class="clear"></div>

	<div class="footer"></div>

</body>

</html>
