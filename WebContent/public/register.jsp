<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="/hiwi/md5.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Registrierung | Hiwi Job Börse</title>
</head>

<body>
<div class="footerunten">
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
			<a class="hintlink" href="" title="neues Passwort">Passwort
				vergessen?</a>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<div class="text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="<%=Helper.D_INDEX%>"
					title="Hier gelangen Sie auf unsere Startseite">Startseite</a><br>
				&rarr; Registrieren <br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_PUBLIC_HELP%>" title="Hier finden Sie die Hilfe">Hilfe</a>
			</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Registrierung</h2>
				<div class="text">Erläuterungstext</div>
			</div>
			<hr>
			<div class="textblock">
				<div class="haupttext">
					<form name="registerForm">
						Bitte geben sie ihre persönlichen Daten hier ein: <br>
						<div class="text">
							<table class="hidden">
								<tr>
									<td>Vorname:</td>
									<td><input type="text" name="realFirstName" /></td>
									<td><div id="error_realFirstName" class="invisibleWarning"></div></td>
								</tr>
								<tr>
									<td>Nachname:</td>
									<td><input type="text" name="realLastName" /></td>
									<td><div id="error_realLastName" class="invisibleWarning"></div></td>
								</tr>
								<tr>
									<td>Email Addresse:</td>
									<td><input type="text" name="userEmail_1" /></td>
									<td><div id="error_userEmail_1" class="invisibleWarning"></div></td>
								</tr>
								<tr>
									<td>Email bestätigen:</td>
									<td><input type="text" name="userEmail_2" /></td>
									<td><div id="error_userEmail_2" class="invisibleWarning"></div>
										<div id="error_userEmailDiff" class="invisibleWarning"></div></td>
								</tr>
							</table>
						</div>
						Bitte geben sie hier ihre gewünschte Account details an:
						<div class="text">
							<table class="hidden">
								<tr>
									<td>Benutzername:</td>
									<td><input type="text" name="userName" /></td>
									<td><div id="error_userName" class="hiddenerror"></div>
										<div id="error_alreadyUsed" class="hiddenerror"></div></td>
								</tr>
								<tr>
									<td>Passwort:</td>
									<td><input type="password" name="userPassword_1" /></td>
									<td><div id="error_userPassword_1" class="hiddenerror"></div></td>
								</tr>
								<tr>
									<td>Passwort bestätigen:</td>
									<td><input type="password" name="userPassword_2" /></td>
									<td><div id="error_userPassword_2" class="hiddenerror"></div>
										<div id="error_userPasswordDiff" class="hiddenerror"></div></td>
								</tr>
							</table>
						</div>
						<table class="hidden">
							<tr>
								<td><input type="checkbox" name="dataProtection" /></td>
								<td><div class="smallText">
										Ich habe die <a href="<%=Helper.D_PUBLIC_DATAAGREEMENT%>"
											target="_blank">Datenschutzbestimmungen</a> gelesen und
										stimme ihnen zu.
									</div></td>
								<td><div id="error_checkData" class="hiddenerror"></div></td>
							</tr>
						</table>
						<br> <input type="submit" value="Registrieren"
							onclick="checkRegister(registerForm); return false;" /><input
							type="button" value="Abbrechen"
							style="position: relative; left: 20%" onclick="gotoIndex()" /><br><div
							id="error_emptyfields" class="hiddenerror"></div>
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