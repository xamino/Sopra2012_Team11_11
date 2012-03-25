<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<title>Registrierung | Hiwi Job Börse</title>
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
					<input type="reset" value="Zurücksetzen" /> <input type="submit"
						value="Anmelden" />
				</p>
			</form>
			<a href="" title="neues Passwort">Passwort vergessen?</a>					<!--  Funktion fehlt -->
			<div id="error_login" class="hiddenerror"></div>
		</div>
		<div class="nav">
			<h3>Navigation</h3>
			<ul>
				<li >
					<a href="index.jsp" title="Hier gelangen Sie auf unsere Startseite">Startseite</a>
				</li>
				<li >
				→Registrieren
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
			<h2>Registrierung</h2>
			<div class="text">
				Erläuterungstext
			</div>
		</div>
		<hr>
		<div class="textblock">
			<div class="haupttext">
				<div class="register">
				<form name="registrierung">
					<div class="breite">
					<span> 
						<label for="anrede">Anrede</label>
					</span>
					<div class="select">
						<select name="anrede" size="1">
	      					<option>Frau</option>
	      					<option>Mann</option>
	    				</select>
    				</div> <br> 
    				<span> 
    					<label for="name">Name</label>
					</span>
					<div class="regform">
						<input type="text" name="name" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="vorname">Vorname</label>
					</span>
					<div class="regform">
						<input type="text" name="vorname" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="birth">Geburtsdatum</label>
					</span>
					<div class="regform">
						<input type="text" name="birth" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="martnr">Matrikelnummer</label>
					</span>
					<div class="regform">
						<input type="text" name="matnr" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="email">E-Mail</label>
					</span>
					<div class="regform">
						<input type="text" name="email" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="nutzername">Benutzername</label>
					</span>
					<div class="regform">
						<input type="text" name="nutzername" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="passwort">Passwort</label>
					</span>
					<div class="regform">
						<input type="text" name="passwort" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="passwortwdh">Passwort wiederholen</label>
					</span>
					<div class="regform">
						<input type="text" name="passwortwdh" size="20" maxlength="100" />
					</div> <br>
					</div>
					<div class="clear"></div>
					<div class="abstand"><input type="checkbox" name="dataagreement" value="dataAkzeptieren"> Ich akzeptiere die <a href="dataagreement.jsp" title="Hier geht es zu den Datenschutzbestimmungen">Datenschutzbestimmungen</a></div><br>
					<p>
						<input type="reset" value="Zurücksetzen" /> 
						<input type="submit" value="Registrieren" />
					</p>
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
