<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<title>Bewerbungsstatus | Hiwi Job Börse</title>
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
					<a href="accountmanagement.jsp" title="Hier geht es zur Accountverwaltung">Accountverwaltung</a>
				</li>
				<li >
					→ Bewerbuungen
				</li>
			</ul>
		</div>
	</div>
	<div class="content">
	<div class="rightborder">
		<div class="textblock">
			<h2>Bewerbungen</h2>
			<div class="text">
			Hier Finden Sie den Bearbeitungsstatus Ihrer Bewerbungen.
			</div>
		</div>
		<hr>
		<div class="textblock">
			<div class="haupttext">
				<div class="float">
				<form method="post" action="mailto:email@email.com">
					<input type="submit" value="Verwalter kontaktieren" />
				</form>
				</div>
				<div class="float">
				<form method="post" action="mailto:email@email.com">
					<input type="submit" value="Anbieter kontaktieren" />
				</form>
				</div>
				<div class="float">
				<form name="stornieren">
					<input type="submit" value="Bewerbung wiederrufen" />
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
