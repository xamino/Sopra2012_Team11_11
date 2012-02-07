package database.account;
/**
 * Verwaltet alle Datenbankzugriffe auf Account-bezogene Daten
 */
import database.DatabaseController;

public class AccountController {
	public DatabaseController dbc;
	
	public void createAccount(Account account){
		
	}
	
	public Account getInstance(){
		return null;
		
	}
	
	public void deleteAccount(Account account){
		
	}
	
	public void setEmail(Account account, String email){
		
	}
	
	public void setPassword(Account account, String password){
		
	}
	
	public void setRealname(Account account, String realname){
		
	}
	
	public void setInstitute(Account account, int institute){
		
	}
	
	public String getAccountype(String username){
		return null;
		
	}
	
	public String getEmail(String username){
		return null;
		
	}
	
	public int getInstitute(String username){
		return 0;
		
	}
	
	public String getPassword(String username){
		return null;
		
	}
	
	public String getRealname(String username){
		return null;
		
	}
	
	public int getAllbyAccounttype(int accounttype){
		return 0;
		
	}
	
	public int getAllbyInstitute(int institute){
		return 0;
		
	}
	
}
