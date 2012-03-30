<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="script.js"></script>
<script type="text/javascript" src="md5.js"></script>
<title>Hilfe | Hiwi Job Börse</title>
</head>

<body>
	<div class="header">
		<h1>
			<a class="banner" href="../public/index.jsp">Hiwi-Börse</a>
		</h1>
	</div>
	<div class="right">
		<div class="angemeldet">
			Sie sind als Sachbearbeiter angemeldet. <br>
			<input type="submit" value="Logout" />
		</div>
		<div class="nav">
			<h3>Navigation</h3>
				<div class="text">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a 
					href="<%=Helper.D_CLERK_USERINDEX %>" title="Hier gelangen Sie auf unsere Startseite">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&rarr; Hilfe <br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="/hiwi/Clerk/js/doExcelExport">ExcelExport</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_ACCOUNTMANAGEMENT%>">Accountverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
					href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a>
				</div>
		</div>
	</div>
	<div class="content">
		<div class="rightborder">
			<div class="textblock">
				<h2>Hilfe</h2>
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
