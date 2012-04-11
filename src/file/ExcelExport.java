package file;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import database.application.Application;
import database.application.ApplicationController;
import user.UserData;

/**
 * Klasse zur erstellung von Exceldateien mit den getaetigten Einstellungen
 * eines Verwalters.
 * 
 * @author Manuel Guentzel
 * 
 */
public abstract class ExcelExport {
	/**
	 * 
	 */
	private static ApplicationController appcon=ApplicationController.getInstance();
	
	/**
	 * Erstellt eine neue Excel Datei mit den getaetigten Einstellungen des
	 * Verwalters und gibt den Link dazu zurueck. Dem uebergebenen
	 * UserData-Objekt werden der Benutzername und die Session entnommen um
	 * diese dann mittels dem FileController zum Download zur verfügung zu
	 * stellen.
	 * 
	 * @param data
	 *            UserData Objekt des Benutzers.
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public static String export(final UserData data) throws IOException, RowsExceededException, WriteException {
		String clerkname = data.getUsername(); 
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
