<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="/hiwi/library.js"></script>
<title>Angebot ändern | Hiwi Job Börse</title>
</head>

<body onload="loadSelectedOfferEdit()">
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="angemeldet">
			Sie sind als Anbieter angemeldet. <br>
			<input type="submit" value="Logout" onclick="doLogout();"/>
		</div>				
			<div id="error_login" class="hiddenerror"></div>
		<div class="nav">
			<h3>Navigation</h3>
				<div class="text">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_PROVIDER_USERINDEX %>" title="Hier geht es zur Ihrer Startseite">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr; Angebot ändern <br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_PROVIDER_ACCOUNTMANAGEMENT %>" title="Hier geht es zur Accountverwaltung">Accountverwaltung</a>
				</div>
		</div>
	</div>
	<div class="content">
	<div class="rightborder">
		<div class="textblock">
			<h2>Angebot erstellen</h2>
			<div class="text">
			Hier können Sie das Angebot bearbeiten.
			</div>
		</div>
		<hr>
		<div class="textblock">
			<div class="haupttext">
				<div class="register">
				<form name="angebotErstellen"> 
    				<div class="regform2">
    					<label for="titel">Titel</label>
					</div>
					<div class="float2">
						<input type="text" id="titelFeld" name="titel" size="57" maxlength="100" />
					</div>
					<div class="clear"></div>
					<div class="clear"></div>
					<br>
					<textarea name="beschreibung" id="beschreibungsFeld" cols="50" rows="10">Beschreibung</textarea> <br> <br>
					<p>
						<input type="reset" value="Zurücksetzen" /> 
						<input type="button" value="Angebot ändern"
						onclick="togglePopup('edit_offer',true);" /> 
						<!-- <input type="submit" value="Angebot ändern" />-->
					</p>
				</form>
			</div>
			</div>
		</div>
</div>
</div>

	<div class="clear"></div>

	<div class="footer"></div>

	<!-- Here are the popups -->
	<!-- Confirmation account del popup -->
	<div id="edit_offer" class="popup_hidden">
		<form>
			<h3>Bestätigung</h3>
			<hr>
			<div class="textblock">
				Wollen Sie das Angebot<br>wirklich ändern?
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Ändern"
					onclick="deleteSelectedAccount(); togglePopup('edit_offer', false);" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('edit_offer', false);" />
			</div>
		</form>
	</div>

</body>

</html>
