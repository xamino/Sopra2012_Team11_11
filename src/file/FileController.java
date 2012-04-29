/**
 * @author Oemer Sahin
 */
package file;

import java.io.File;

import servlet.Helper;

import config.Configurator;

/**
 * Klasse erstellt und entfernt Dateien zum downloaden zur jeweiligen Session.
 * 
 */

public abstract class FileController {

/**
 * Diese Methode loescht die Export Datei des Angegebenen Users.
 * @param username Username des Users dessen Datei geloescht werden soll
 * @return Wahrheitswert ob der Vorgang erfolgreich war
 */
	public static boolean deleteFile(String username){
		String path = "";
		try{
			path=Configurator.getInstance().getPath("excel")+System.getProperty("file.separator")+username+".xls";
			File f = new File(path);
			if(f.exists()){
				f.delete();
				Helper.log.write("FileController", "Successfully deleted exportfiles of User: "+username);
			}
			return true;
		}catch(Exception e){
		Helper.log.write("FileController", "Error while deleting File("+path+")");
		}
		return false;
		
	}

/**
 * Erstellt eine neue beschreibbare Excel Datei.
 * @param username Benutzername des Dateibesitzers
 * @return Datei zum weiterverarbeiten fuer den ExcelExport
 */
	public static File createFile(String username) {
		File f = null;
		String path = "";
		try {
		path = Configurator.getInstance().getPath("excel")+System.getProperty("file.separator")+username+".xls";
		f= new File(path);
		if(f.exists()){
			f.delete();
			f.createNewFile();
		}
		} catch (Exception e) {
			Helper.log.write("FileController", "Error while creating File("+"path"+")");
		}
		return f;

	}

}
