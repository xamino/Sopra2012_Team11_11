package user;

import javax.servlet.http.HttpSession;
import javax.swing.text.html.HTMLDocument.Iterator;

import com.sun.corba.se.spi.ior.Writeable;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.Vector;
import jxl.*;
import jxl.write.*;
import jxl.write.biff.RowsExceededException;
import database.DatabaseController;
import database.account.Account;
import database.application.Application;
import database.application.ApplicationController;

/**
 * Verwaltet alle Aufgaben und Daten eines Verwalters.
 */
public class Clerk extends User {

	/**
	 * Konstruktor. Erstellte Objekte werden automatisch in der LoggedInUsers
	 * klasse aufgenommen.
	 * 
	 * @param username
	 *            Benutzername im System
	 * @param email
	 *            E-Mail Adresse
	 * @param name
	 *            Realer Name
	 * @param session
	 *            Session des Benutzers
	 */
	public Clerk(String username, String email, String name, HttpSession session) {
		super(username, email, name, session);
		userManagement.LoggedInUsers.addUser(this);
	}

	/**
	 * Editiert den eigenen Account. Der Benutzername ist dabei nicht aenderbar
	 * und identifiziert den zu aendernden Account in der Datenbank
	 * 
	 * @param acc
	 *            geaenderter Account
	 */
	public void editAccount(Account acc) {

	}

	/**
	 * Methode zum bearbeiten von Bewerbungen.
	 */
	public void editApplication() {

	}

	/**
	 * Methode zum ablehnen eines Angebots.
	 */
	public void rejectOffer() {

	}

	/**
	 * Methode zum aktualiesieren eines Angebots.
	 */
	public void updateOffer() {

	}

	/**
	 * Methode zum hinzufuegen von Bewerber-Dokumenten. Dabei kann jedem
	 * Bewerber einzeln Dokumente hinzugefuegt werden.
	 */
	public void addAppDoc() {

	}

	/**
	 * Methode zum hinzufuegen von Dokumenten.
	 */
	public void addDoc() {

	}

	/**
	 * Methode zum entfernen von Dokumenten.
	 */
	public void delDoc() {

	}

	/**
	 * Methode zum annehmen eines Bewerbers.
	 */
	public void acceptApplication() {

	}

	/**
	 * Methode zum entfernen von Bewerber-Dokumenten. Dabei kann jedem Bewerber
	 * einzeln Dokumente entfernt werden.
	 */
	public void deleteAppDoc() {

	}

	/**
	 * Methode zum aktualisieren des Berwerbungs-Status.
	 */
	public void updateStatus() {

	}

	/**
	 * Export fuer die Excel-File
	 * 
	 * @return Die URL zu den Download des Excel-Files.
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */

	public String doExport(String clerkname) throws IOException, RowsExceededException, WriteException {
		Vector<Application> appvec = appcon.getApprovedApplicationsByClerk(clerkname);
		
		WritableWorkbook ww = Workbook.createWorkbook(new File("ExcelExport.xls"));
		WritableSheet sh = ww.createSheet("All Applications by "+clerkname, 0);
		
		Label Name = new Label(0,0,"Name");
		Label id = new Label(0,1,"ID");
		sh.addCell(Name);
		sh.addCell(id);
		
		for (int i = 0; i < appvec.size(); i++) {
			
			Label aID = new Label(i,1,""+appvec.get(i).getAid());
			Label aName = new Label(i,0,""+appvec.get(i).getUsername());
			sh.addCell(aID);
			sh.addCell(aName);
		}
		
		ww.write();
		ww.close();
		return "Excel Tabelle erstellt. Download noch nicht bereit";

	}

}
