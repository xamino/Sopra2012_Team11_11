<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/applicant/accountscript.js"></script>
<script type="text/javascript" src="/hiwi/md5.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Accountverwaltung | Hiwi Job Börse</title>
</head>

<body onload="loadAccount();">
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="angemeldet">
			Sie sind als Bewerber angemeldet. <br>
			<input type="submit" value="Logout" onclick="doLogout();"/>
		</div>				
			<div id="error_login" class="hiddenerror"></div>
		<div class="nav">
			<h3>Navigation</h3>
			<div class="text">
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_APPLICANT_USERINDEX %>" title="Hier geht es zur Ihrer Startseite">Startseite</a><br>
				&rarr; Accountverwaltung <br>
				&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_APPLICANT_STATUS %>" title="Hier finden Sie Ihre Bewerbungen">Bewerbungen</a>
			</div>
		</div>
	</div>
	<div class="content">
	<div class="rightborder">
		<div class="textblock">
			<h2>Accountverwaltung</h2>
			<div class="text">
			Hier können Sie Ihre persönlichen Daten bearbeiten.
			Wenn die zu ändernden Daten hier nicht aufgeführt werden, wenden Sie sich bitte an den Administrator.
			</div>
		</div>
		<hr>
		<div class="textblock">
			<div class="haupttext">
				<div class="aendern">
				<form id="datenAendern" name="datenAendern">
					<div class="breiteAendern">
					<span> 
						<label for="newemail">E-Mail</label>
					</span>
					<div class="regform">
						<input id="newemail" type="text" name="newemail" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="newnutzername">Benutzername</label>
					</span>
					<div class="regform">
						<input id="newnutzername" type="text" name="newnutzername" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="newpasswort">neues Passwort</label>
					</span>
					<div class="regform">
						<input id="newpasswort" type="text" name="newpasswort" size="20" maxlength="100" />
					</div> <br>
					<span> 
						<label for="newpasswortwdh">neuesPasswort wiederholen</label>
					</span>
					<div class="regform">
						<input id="newpasswortwdh" type="text" name="newpasswortwdh" size="20" maxlength="100" />
					</div> <br>
					</div>
					<div class="clear"></div>
					<div class="abstand"><input type="checkbox" name="dataagreement" value="dataAkzeptieren"> <a href="dataagreement.jsp" title="Hier geht es zu den Datenschutzbestimmungen">Datenschutzbestimmungen</a> widerrufen</div><br>
					<p>
						<input type="reset" value="Zurücksetzen" onclick="loadAccount();" /> 
						<input type="submit" value="Ändern" />
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
