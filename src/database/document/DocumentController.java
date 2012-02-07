/**
 * @author Patryk Boczon
 */

package database.document;
/**
 * Verwaltet alle Datenbankzugriffe auf Unterlagen-bezogene Daten
 */
import java.util.Vector;

import database.DatabaseController;
import database.account.Account;
import database.offer.Offer;

public class DocumentController {
	public DatabaseController dbc;
	
	
	public void createDocument(Document document){
		
	}
	
	public void deleteDocument(Document document){
		
	}
	
	public void updateDocument(Document document){
		
	}
	
	public Vector<Document> getDocumentsByOffer(int aid){
		return null;
		
	}
	
	public Vector<Document> getDocumentsByUserAndOffer(Account account, Offer offer){
		return null;
		
	}
	
	public Vector<Document> getAllDocuments(){
		return null;
		
	}
	
	public Document getInstance(){
		return null;
		
	}
	
	
	
}
