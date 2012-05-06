package file;

import java.io.File;
import java.io.IOException;
import java.util.Vector;

import org.apache.catalina.startup.SetContextPropertiesRule;

import jxl.Cell;
import jxl.CellFormat;
import jxl.Workbook;
import jxl.format.Border;
import jxl.format.BorderLineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFeatures;
import jxl.write.WritableCellFormat;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;
import database.account.AccountController;
import database.application.Application;
import database.application.ApplicationController;
import database.offer.OfferController;
import user.UserData;

/**
 * Klasse zur erstellung von Exceldateien mit den getaetigten Einstellungen
 * eines Verwalters.
 * 
 * @author Manuel Guentzel
 * @author Anatoli Brill
 * 
 */
public abstract class ExcelExport {
	/**
	 * ApplicationController fuer Exportinformationen
	 */
	private static ApplicationController appcon=ApplicationController.getInstance();
	/**
	 * AccountController fuer Exportinformationen
	 */
	private static AccountController acccon = AccountController.getInstance();
	
	/**
	 * OfferController fuer Exportinformationen
	 */
	private static OfferController offcon = OfferController.getInstance();
	
	/**
	 * Erstellt eine neue Excel Datei mit den getaetigten Einstellungen des
	 * Verwalters und gibt ein File objekt zurück.
	 * 
	 * @param data
	 *            UserData Objekt des Benutzers.
	 *           @return File objekt das gestreamt werden kann. 
	 * @throws IOException 
	 * @throws WriteException 
	 * @throws RowsExceededException 
	 */
	public static File export(final UserData data) throws IOException, RowsExceededException, WriteException {
		String clerkname = data.getUsername(); 
		Vector<Application> appvec = appcon.getApprovedApplicationsByClerk(clerkname);
		File file = FileController.createFile(data.getUsername());
		WritableWorkbook ww = Workbook.createWorkbook(file);
		WritableSheet sh = ww.createSheet("All Applications by "+clerkname, 0);
		sh.setColumnView(0, 25);
		sh.setColumnView(1, 25);
		sh.setColumnView(2, 40);

		Label uName = new Label(0,0,"Username");
		Label Name = new Label(1,0,"Realer Name");
		Label id = new Label(2,0,"Angebot");
		sh.addCell(Name);
		sh.addCell(id);
		sh.addCell(uName);

		for (int i = 1; i <= appvec.size(); i++) {
			Label aID = new Label(2,i,""+offcon.getOfferById(appvec.get(i-1).getAid()).getName());
			Label aName= new Label(1,i,""+acccon.getAccountByUsername(appvec.get(i-1).getUsername()).getName());
			Label auName = new Label(0,i,""+appvec.get(i-1).getUsername());
			sh.addCell(aID);
			sh.addCell(auName);
			sh.addCell(aName);
			
		}
		ww.write();
		ww.close();
		return file;

	}

}
