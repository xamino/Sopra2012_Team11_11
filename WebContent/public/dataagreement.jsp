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
<title>Datenschutzerklärungen | Hiwi Job Börse</title>
</head>

<body>
<div class="footerunten">
	<div class="header">
		<h1>
			<a class="banner">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="datamitte">
	<div class="content">
			<div class="textblock">
				<h2>Datenschutzerklärungen</h2>
				<div class="text">Hier können Sie die aktuellen
					Datenschutzbestimmungen einsehen.</div>
			</div>
			<hr>
			<div class="textblock">
				<div class="haupttext">Wir erheben und verwenden Ihre personenbezogenen Daten ausschließlich im Rahmen der Bestimmungen des Datenschutzrechts der 
				Bundesrepublik Deutschland. Im Folgenden unterrichten wir Sie über Art, Umfang und Zwecke der Erhebung und Verwendung personenbezogener Daten. 
				Sie können diese Unterrichtung jederzeit auf der Hiwi-Börse unter Ihrer Accountverwaltung abrufen und widerrufen.<br>
				<br>
				<strong>Datenübermittlung und -protokollierung zu systeminternen und statistischen Zwecken</strong><br>
				Ihr Internet-Browser übermittelt beim Zugriff auf unsere Webseite aus technischen Gründen automatisch Daten an unseren Webserver. 
				Es handelt sich dabei unter anderem um Datum und Uhrzeit des Zugriffs, URL der verweisenden Webseite, abgerufene Datei, Menge der gesendeten Daten, 
				Browsertyp und -version, Betriebssystem sowie Ihre IP-Adresse. Diese Daten werden nicht gespeichert. <br>
				Eine Zuordnung dieser Daten zu einer bestimmten Person ist uns nicht möglich. <br>
				<br> 
				<strong>Nutzungsdaten</strong><br>
				Wir erheben und verwenden personenbezogene Daten von Ihnen, soweit dies erforderlich ist, um die Inanspruchnahme unseres Internetangebotes zu ermöglichen.
				Diese Daten werden ein Jahr für Dokumentationszwecke gespeichert.
				Wenn Sie die Datenschutzbestimmungen widerrufen bzw. Ihren Account löschen, werden alle personenbezogenen Daten von Ihenen umgehend gelöscht.<br>
				Ihre personenbezogenen Daten werden an in keinem Fall an Dritte weitergegeben oder sonst übermittelt.
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
