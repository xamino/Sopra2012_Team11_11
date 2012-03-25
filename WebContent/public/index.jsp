<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
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
			<form name="anmeldung">
				<span> <label for="nutzername">Benutzername</label>
				</span>
				<div class="form">
					<input type="text" name="nutzername" size="20" maxlength="100" />
				</div>
				<br> <span> <label for="passwort">Passwort</label>
				</span>
				<div class="form">
					<input type="text" name="passwort" size="20" maxlength="100" />
				</div>
				<div class="clear"></div>
				<br>
				<p>
					<input type="reset" value="Zurücksetzen" /> 
					<input type="submit" value="Anmelden" />
				</p>
			</form>
			<a href="register.jsp" title="Hier geht es zum Registrieren">Noch nicht registriert?</a> <br>
			<a href="" title="neues Passwort">Passwort vergessen?</a>					<!--  Funktion fehlt -->
			<div id="error_login" class="hiddenerror"></div>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<ul>
				<li >
					→ Startseite
				</li>
				<li >
					<a href="register.jsp" title="Hier geht es zum Registrieren">Registrieren</a>
				</li>
				<li >
					<a href="help.jsp" title="Hier finden Sie die Hilfe">Hilfe</a>
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
