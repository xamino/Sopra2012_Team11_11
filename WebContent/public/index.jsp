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
<script type="text/javascript"
	src="http://konami-js.googlecode.com/svn/trunk/konami.js"></script>
<title>Startseite | Hiwi Job Börse</title>
</head>

<body onload="loadOffers();handleLoadAnimatedLogo();">
	<div class="footerunten">
		<div class="header">
			<h1>
				<a class="banner" href="/hiwi/public/index.jsp">Hiwi-Börse</a>
			</h1>
		</div>
		<div class="right">
			<div class="commentform">
				<h3>Anmeldung</h3>
				<form name="login">
					<span> <label for="userName">Benutzername</label>
					</span>
					<div class="form">
						<input type="text" name="userName" size="15" maxlength="100" />
					</div>
					<br> <span> <label for="userPassword">Passwort</label>
					</span>
					<div class="form">
						<input type="password" name="userPassword" size="15"
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
				<a class="hintlink" href="/hiwi/public/register.jsp"
					title="Hier geht es zum Registrieren">Noch nicht registriert?</a> <a
					class="hintlink" href="javascript:void(0)"
					onclick="prepareForgetful();" href="">Passwort vergessen?</a>
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
					<div class="text">
						Hier finden Sie alle im Moment angebotenen Stellen. <br> Um
						sich zu bewerben, registrieren Sie sich bitte, oder loggen Sie
						sich ein.<br> Hilfe finden Sie <a
							href="<%=Helper.D_PUBLIC_HELP%>" title="Hilfe"> hier</a>.
					</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="haupttext">
						<table id="offersTable">
							<!-- Javascript loads entrys! -->
						</table>
					</div>
				</div>
			</div>
		</div>
	</div>

	<div class="clear"></div>
	<div class="footer"></div>
	<!-- Forgotten password popup -->
	<div id="password_forgotten" class="popup_hidden">
		<form name="forgottenForm">
			<h3>Password vergessen</h3>
			<hr>
			<div class="textblock">
				Email: <br>
				<textarea name="mail" rows="1" cols="30"></textarea>
				<div id="error_passwordMail" class="hiddenerror"></div>
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Neues Password senden"
					onclick="requestNewPassword();" /> <input type="button"
					value="Abbrechen"
					onclick="togglePopup('password_forgotten', false);" />
			</div>
		</form>
	</div>
</body>
</html>
