<%@page import="servlet.Helper"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C/DTD HTML 4.01 Transitional/EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="../style.css" />
<script type="text/javascript" src="/hiwi/library.js"></script>
<script type="text/javascript" src="/hiwi/clerk/accountscript.js"></script>
<script type="text/javascript" src="/hiwi/md5.js"></script>
<title>Account Bearbeiten | Hiwi Job Börse</title>
</head>

<body onload="loadAccount()">
	<div class="footerunten">
		<div class="header">
			<h1>
				<a class="banner" href="../provider/userindex.jsp">Hiwi-Börse</a>
			</h1>
		</div>
		<div class="right">
			<div class="commentform">
				<h3>Abmeldung</h3>
				<form name="abmeldung">
					<p>
						<label for="logout">Sie sind als Sachbearbeiter
							angemeldet.</label>
					</p>
					<br>
					<p>
						<input type="button" value="Abmelden" name="logout"
							onclick="doLogout();" />
					</p>
				</form>
			</div>
			<div class="nav">
				<h3>Navigation</h3>
				<div class="text">
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_USERINDEX%>">Startseite</a><br>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="doExcelExport();">ExcelExport</a><br>
					&rarr; Accountverwaltung<br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_OFFERMANAGEMENT%>">Angebotsverwaltung</a><br>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_APPLICATIONMANAGEMENT%>">Bewerbungsverwaltung</a>
					<br> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a
						href="<%=Helper.D_CLERK_HELP%>" title="Hier finden Sie die Hilfe">Hilfe</a>
				</div>
			</div>
		</div>
		<div class="content">
			<div class="rightborder">
				<div class="textblock">
					<h2>Account verwalten</h2>
					<div class="text">
						Hier können Sie Ihre persönlichen Daten bearbeiten. <br> Wenn
						die zu ändernden Daten hier nicht aufgeführt werden, wenden Sie
						sich bitte an den Administrator.
					</div>
				</div>
				<hr>
				<div class="textblock">
					<div class="textblock">
						<div class="haupttext">
							<div class="aendern">
								<form id="datenAendern" name="datenAendern">
									<div class="breiteAendern">
										<span> <label for="realName">Name: </label>
										</span>
										<div class="regform">
											<input id="realName" type="text" name="realName" size="20"
												maxlength="100" />
											<div id="error_realName"></div>
										</div>
										<span> <label for="newemail">E-Mail: </label>
										</span>
										<div class="regform">
											<input id="newemail" type="text" name="newemail" size="20"
												maxlength="100" />
											<div id="error_email"></div>
										</div>
										<br> <br> <span> <label for="newpasswort">Neues
												Passwort: </label>
										</span>
										<div class="regform">
											<input id="newpasswort" type="password" name="newpasswort"
												size="20" maxlength="100" />
										</div>
										<br> <span> <label for="newpasswortwdh">Neues
												Passwort wiederholen: </label>
										</span>
										<div class="regform">
											<input id="newpasswortwdh" type="password"
												name="newpasswortwdh" size="20" maxlength="100" />
											<div id="error_unequalPasswords" class="hiddenerror"></div>
											<div id="error_emptyPasswords" class="hiddenerror"></div>
										</div>
										<br> <span> <label for="stellvertreter">Stellvertreter:
										</label>
										</span>
										<div class="regform">
											<select id="selectStellvertreter" size="1">
											</select>
											<div id="error_stellvertreter" class="hiddenerror"></div>
										</div>
										<br>
									</div>
									<div class="clear"></div>
									<div class="abstand">
										<input id="dataconfirm" type="checkbox" name="dataagreement"
											value="dataAkzeptieren"> <a target="_blank"
											href="<%=Helper.D_PUBLIC_DATAAGREEMENT%>"
											title="Hier geht es zu den Datenschutzbestimmungen">Datenschutzbestimmungen</a>
										widerrufen
									</div>
									<br>
									<p>
										<input type="reset" value="Zurücksetzen"
											onclick="loadAccount();" /> <input type="submit"
											value="Ändern" onclick="check(); return false;" />
									</p>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<div class="clear"></div>
	<div class="footer"></div>
	<div id="data_acc_del" class="popup_hidden">
		<form>
			<h3>Warnung</h3>
			<hr>
			<div class="textblock">
				Wenn sie den Datenschutzbestimmungen <br>widerrufen wird ihr<br>
				Account nun gelöscht.
			</div>
			<hr>
			<div class="textblock">
				<input type="button" value="Löschen" onclick="deleteAccount();" />
				<input type="button" value="Abbrechen"
					onclick="togglePopup('data_acc_del', false);" />
			</div>
		</form>
	</div>
</body>
</html>