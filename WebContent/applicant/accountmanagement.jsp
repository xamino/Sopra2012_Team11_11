<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<title>Accountverwaltung | Hiwi Job Börse</title>
</head>

<body>
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="angemeldet">
			Sie sind angemeldet als: <br>
			<input type="submit" value="Logout" />
		</div>				
			<div id="error_login" class="hiddenerror"></div>
		<div class="nav">
			<h3>Navigation</h3>
			<ul>
				<li >
					<a href="userindex.jsp" title="Hier geht es zur Ihrem Desktop">Bewerber-Desktop</a>
				</li>
				<li >
					→ Accountverwaltung
				</li>
				<li >
					<a href="status.jsp" title="Hier finden Sie Ihre Bewerbungen">Bewerbungen</a>
				</li>
			</ul>
		</div>
	</div>
	<div class="content">
		<div class="textblock">
			<h2>Accountverwaltung</h2>
			<div class="text">
			Hier können Sie Ihre persönlichen Daten bearbeiten.
			Wenn die zu ändernden Daten hier nicht aufgeführt werden, wenden Sie sich bitte an den Administrator.
			</div>
		</div>
		<hr>
		<div class="textblock">
			<div class="text">
				<div class="aendern">
				<form name="datenAendern">
					<div class="breiteAendern">
					<span> 
						<label for="newemail">E-Mail</label>
					</span>
					<div class="regform">
						<input type="text" name="newemail" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="newnutzername">Benutzername</label>
					</span>
					<div class="regform">
						<input type="text" name="newnutzername" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="newpasswort">neues Passwort</label>
					</span>
					<div class="regform">
						<input type="text" name="newpasswort" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="newpasswortwdh">neuesPasswort wiederholen</label>
					</span>
					<div class="regform">
						<input type="text" name="newpasswortwdh" size="20" maxlength="100" />
					</div> <br>
					</div>
					<div class="clear"></div>
					<div class="abstand"><input type="checkbox" name="dataagreement" value="dataAkzeptieren"> Ich akzeptiere die <a href="dataagreement.jsp" title="Hier geht es zu den Datenschutzbestimmungen">Datenschutzbestimmungen</a></div><br>
					<p>
						<input type="reset" value="Zurücksetzen" /> 
						<input type="submit" value="Ändern" />
					</p>
				</form>
			</div>
			</div>
		</div>
	</div>

	<div class="clear"></div>

	<div class="footer"></div>

</body>

</html>
