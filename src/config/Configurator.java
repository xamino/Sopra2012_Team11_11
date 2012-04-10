package config;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;


/**
 * 
 * @author Manuel Guentzel
 **/
/**
 * Klasse zum lesen von Konfigurationsdateien
 */

public class Configurator {
	/**
 	* Pfad zur Konfigurationsdatei des Configurators
 	*/
	private final String CCPATH = System.getProperty("user.home")+System.getProperty("file.separator")+".sopra"+System.getProperty("file.separator")+"confconf";
	/**
	 * Pfad der externen Konfigurationsdatei
	 */
	private String cpath;
	/**
	 * Instanz des Configurators
	 */
	private static Configurator config;
	/**
	 * Map der Typen der gespeicherten Werte
	 */
	private HashMap<String, String> Types= new HashMap<String, String>();
	/**
	 * Map der gespeicherten Werte
	 */
	private HashMap<String, Object> Values= new HashMap<String, Object>();

	/**
	 * Gibt die Instanz des Konfigurators zurueck.Falls noch nicht vorhanden wird eine neue Instanz erstellt
	 * @return Instanz des Konfigurators
	 */
	public static Configurator getInstance(){
		if(config==null)config=new Configurator();
		return config;
	}
	/**
	 * Privater Konstruktor, in dem die Werte aus der Konfigdatei gelesen werden.
	 */
	private Configurator(){
		try{
		BufferedReader bufread = new BufferedReader(new InputStreamReader(new FileInputStream(new File(CCPATH)),"UTF-8"));
		
		String s = bufread.readLine();
		String[] sa = s.split("\\|");
		for(int y=0;y<sa.length;y++)sa[y]=properTrim(sa[y]);
		if(sa[0].equals("$HOME")){
			s="";
			s=System.getProperty("user.home");
			for(int x = 1; x< sa.length;x++){
				s+= System.getProperty("file.separator")+sa[x];
			}
			cpath = s;
		}else cpath = s;
		while(true){
			if(!bufread.ready())break;
			s = bufread.readLine();
			sa = s.split("\\|");
			for(int y=0;y<sa.length;y++)sa[y]=properTrim(sa[y]);
			if(sa[0].equalsIgnoreCase("path")){
				Types.put(sa[1],sa[0]);
				String s2="";
				if(sa[2].equals("$HOME")){
					s2=System.getProperty("user.home");
					for(int x = 3; x< sa.length;x++){
						s2+= System.getProperty("file.separator")+sa[x];
					}
				}else s2=sa[2];
				Values.put(sa[1],s2);
			}
			else if(sa.length==3){
				Types.put(sa[1], sa[0]);
				if(sa[0].equalsIgnoreCase("int"))Values.put(sa[1], Integer.parseInt(sa[2]));
				if(sa[0].equalsIgnoreCase("String"))Values.put(sa[1],sa[2]);
				if(sa[0].equalsIgnoreCase("boolean"))Values.put(sa[1], Boolean.parseBoolean(sa[2]));
			}
			else if(sa.length==2){
				Types.put(sa[1], sa[0]);
				if(sa[0].equalsIgnoreCase("int"))Values.put(sa[1], 0);
				if(sa[0].equalsIgnoreCase("String"))Values.put(sa[1],"");
				if(sa[0].equalsIgnoreCase("boolean"))Values.put(sa[1], false);
				if(sa[0].equalsIgnoreCase("path"))Values.put(sa[1], "");
			
			}
		}
		
		}catch(Exception e){
			e.printStackTrace();
		}
		readConf();
	}
	/**
	 * Private Funktion zum auslesen der Werte die vom Standard abweichen.
	 */
	private void readConf(){
		try {
			BufferedReader bufread = new BufferedReader(new InputStreamReader(new FileInputStream(new File(cpath))));
			String s;
			String[]sa;
			while(true){
				if(!bufread.ready())break;
				s = bufread.readLine();
				sa = s.split("=");
				for(int y=0;y<sa.length;y++)sa[y]=properTrim(sa[y]);
				if(sa.length==2){
					if(Values.containsKey(sa[0])){
						if(Types.get(sa[0]).equalsIgnoreCase("int"))Values.put(sa[0], Integer.parseInt(sa[1]));
						if(Types.get(sa[0]).equalsIgnoreCase("String"))Values.put(sa[0],sa[1]);
						if(Types.get(sa[0]).equalsIgnoreCase("boolean"))Values.put(sa[0], Boolean.parseBoolean(sa[1]));
						if(Types.get(sa[0]).equalsIgnoreCase("path")){
							String temp = sa[0];
							sa=sa[1].split("\\|");
							for(int y=0;y<sa.length;y++)sa[y]=properTrim(sa[y]);
							if(sa.length==1){
								if(!sa[0].equalsIgnoreCase("$HOME"))Values.put(temp,sa[0]);
						
							}else{
								if(sa[0].equals("$HOME")){
									s="";
									s=System.getProperty("user.home");
									for(int x = 1; x< sa.length;x++){
										s+= System.getProperty("file.separator")+sa[x];
									}
									Values.put(temp, s);
								}
							}
						}

					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	/**
	 * Funktion zum auslesen einer int Variablen.
	 * @param key Bezeichnung der Variablen
	 * @return zum key zugehoeriger Wert oder <code>Integer.MIN_VALUE</code> falls nicht vorhanden (oder kein int!)
	 * @throws IllegalTypeException falls gewuenschter Wert kein int ist 
	 * @throws UnknownOptionException falls der die gewünschte Variablen nicht vorhanden ist
	 */
	public int getInt(String key) throws IllegalTypeException, UnknownOptionException{
		if(Types.containsKey(key)){
			if (Types.get(key).equalsIgnoreCase("int"))return (int)(Integer)Values.get(key);
			else throw new IllegalTypeException("desired value "+key+" is not an int!");
		}else throw new UnknownOptionException(key +" is unknown");
	}
	/**
	 * Funktion zum auslesen einer boolean Variablen.
	 * @param key Bezeichnung der Variablen
	 * @return zum key zugehoeriger Wert oder <code>false</code> falls nicht vorhanden oder kein boolean
	 * @throws IllegalTypeException falls gewuenschter Wert kein boolean ist 
	 * @throws UnknownOptionException falls der die gewünschte Variablen nicht vorhanden ist
	 */
	public boolean getBoolean(String key) throws IllegalTypeException, UnknownOptionException{
		if(Types.containsKey(key)){
			if(Types.get(key).equalsIgnoreCase("boolean")){
				return (boolean)(Boolean)Values.get(key);
			}
			else throw new IllegalTypeException("desired value "+key+" is not a boolean!");
		}
		else throw new UnknownOptionException(key +" is unknown");
	}
	/**
	 * Funktion zum auslesen einer String Variablen.
	 * @param key Bezeichnung der Variablen
	 * @return zum key zugehoeriger Wert oder <code>null</code> falls nicht vorhanden, leer oder kein String
	 * @throws IllegalTypeException falls gewuenschter Wert kein String ist 
	 * @throws UnknownOptionException falls der die gewünschte Variablen nicht vorhanden ist
	 */
	public String getString(String key) throws IllegalTypeException, UnknownOptionException{
		if(Types.containsKey(key)){
			if(Types.get(key).equalsIgnoreCase("String")){
				String s = (String)Values.get(key);
				if(s.equals(""))return null;
				return s;
			}else throw new IllegalTypeException("desired value "+key+" is not a String!");
		}
		else throw new UnknownOptionException(key +" is unknown");
	}
	/**
	 * Funktion zum auslesen einer String Variablen die einen Pfad darstellt.
	 * @param key Bezeichnung der Variablen
	 * @return zum key zugehoeriger Wert oder <code>null</code> falls nicht vorhanden, leer oder kein Pfad
	 * @throws IllegalTypeException falls gewuenschter Wert kein pfad ist 
	 * @throws UnknownOptionException falls der die gewünschte Variablen nicht vorhanden ist
	 */
	public String getPath(String key) throws IllegalTypeException, UnknownOptionException{
		if(Types.containsKey(key)){
			if(Types.get(key).equalsIgnoreCase("path")){
				String s = (String)Values.get(key);
				if(s.equals(""))return null;
				return s;
			}else throw new IllegalTypeException("desired value "+key+" is not a path!");
		}
		else throw new UnknownOptionException(key +" is unknown");
	}

	/**
	 * Funktion zum feststellen ob ein Charakter ein Whitespace ist.
	 * Credits to:ZZ Coder
	 * http://stackoverflow.com/questions/1437933/how-to-properly-trim-whitespaces-from-a-string-in-java
	 * @param ch Char der ueberprueft werden soll
	 * @return Wahrheitswert ob Whitespace
	 */
	private boolean isWhitespace (int ch)
	  {
	    if (ch == ' ' || (ch >= 0x9 && ch <= 0xD))
	      return true;
	    if (ch < 0x85) 
	      return false;
	    if (ch == 0x85 || ch == 0xA0 || ch == 0x1680 || ch == 0x180E)
	      return true;
	    if (ch < 0x2000 || ch > 0x3000)
	      return false;
	    return ch <= 0x200A || ch == 0x2028 || ch == 0x2029
	      || ch == 0x202F || ch == 0x205F || ch == 0x3000;
	  }
	
	/**
	 * Selbe Funktionalität wie trim(). Entfernt jedoch mehr whitespace aehnliche zeichen
	 * @param s zu trimmender String
	 * @return getrimmter String
	 */
	private String properTrim(String s){
		int start=0;
		int end=0;
		for(int x =0;x<s.length();x++){
			if(isWhitespace(s.charAt(x)))continue;
			start = x;
			break;
		}
		for(int x=s.length()-1;x>=0;x--){
			if(isWhitespace(s.charAt(x)))continue;
			end=x+1;
			break;
		}
		return s.substring(start, end);
	}
}
