/**
 * @author Manuel Guentzel
 * @author Tamino Hartmann
 * @author Patryk Boczon
 */

package database.offer;

import java.util.Date;

/**
 * Datenklasse fuer die Angebote.
 */
public class Offer {

	/**
	 * Die id des Angebots.
	 */
	private int aid;
	
	/**
	 * Der Ersteller des Angebots.
	 */
	private String author;
	
	/**
	 * Der Name des Angebots.
	 */
	private String name;
	
	/**
	 * Die Notiz zum Angebot.
	 */
	private String note;
	
	/**
	 * Der Status des Angebots.
	 */
	private boolean checked;
	
	/**
	 * Die Anzahl der freien Plaetze zum Angebot.
	 */
	private int slots;
	
	/**
	 * Die Anzahl der Stunden pro Woche des Angebots.
	 */
	private double hoursperweek;
	
	/**
	 * Die beschreibung zum Angebot.
	 */
	private String description;
	
	/**
	 * Der Beginn des Angebots.
	 */
	private Date startdate;
	
	/**
	 * Das Ende des Angebots.
	 */
	private Date enddate;
	
	/**
	 * Der Gehalt pro Stunde des Angebots.
	 */
	private double wage;
	
	/**
	 * Das Institut des Angebots.
	 */
	private int institute;
	
	/**
	 * Das letzte Datum, an dem das Angebot bearbeitet wurde.
	 */
	private Date modificationdate;
	/**
	 * Wahrheitswert ob das Angebot abgeschlossen ist.
	 */
	private boolean finished;

	
	/**
	 * 
	 * @param aid
	 * 		Die id des Angebots.
	 * @param author
	 * 		Der Ersteller des Angebots.
	 * @param name
	 * 		Der Name des Angebots.
	 * @param note
	 * 		Die Notiz zum Angebot.
	 * @param checked
	 * 		Der Status des Angebots.
	 * @param slots
	 * 		Die Anzahl der freien Plaetze zum Angebot.
	 * @param hoursperweek
	 * 		Die Anzahl der Stunden pro Woche des Angebots.
	 * @param description
	 * 		Die beschreibung zum Angebot.
	 * @param startdate
	 * 		Der Beginn des Angebots.
	 * @param enddate
	 * 		Das Ende des Angebots.
	 * @param wage
	 * 		Der Gehalt pro Stunde des Angebots.
	 * @param institute
	 * 		Das Institut des Angebots.
	 * @param modificationdate
	 * 		Das letzte Datum, an dem das Angebot bearbeitet wurde.
	 * @param finished
	 * 		Ob das Angebot schon abgeschlossen ist.
	 */
	public Offer(int aid, String author, String name, String note,
			boolean checked, int slots, double hoursperweek,
			String description, Date startdate, Date enddate, double wage,
			int institute, Date modificationdate, boolean finished) {
		super();
		this.aid = aid;
		this.author = author;
		this.name = name;
		this.note = note;
		this.checked = checked;
		this.slots = slots;
		this.hoursperweek = hoursperweek;
		this.description = description;
		this.startdate = startdate;
		this.enddate = enddate;
		this.wage = wage;
		this.institute = institute;
		this.modificationdate = modificationdate;
		this.finished=finished;
	}
	
	/**
	 * Gibt zurueck ob das Angebot beendet ist.
	 * @return boolean ob das Angebot beendet ist.
	 */
	public boolean isFinished() {
		return finished;
	}

	/**
	 * Setzt, ob das Angebot beendet ist.
	 * @param finished Wahrheitswert ob beendet.
	 */
	public void setFinished(boolean finished) {
		this.finished = finished;
	}


	/**
	 * Gibt die id des Angebots zurueck.
	 * 
	 * @return id des Angebots.
	 */
	public int getAid() {
		return aid;
	}

	/**
	 * Setzt die Angebotsid
	 * 
	 * @param aid
	 *            Gewuenschte Angebotsid.
	 */
	public void setAid(int aid) {
		this.aid = aid;
	}

	/**
	 * Gibt den Ersteller des Angebots zurueck.
	 * 
	 * @return Ersteller des Angebots.
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * Setzt den Ersteller des Angebots.
	 * 
	 * @param author
	 *            Ersteller des Angebots.
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * Gibt den Namen des Angebots zurueck.
	 * 
	 * @return Name des Angebots.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setzt den Namen des Angebots.
	 * 
	 * @param name
	 *            Name des Angebots.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gibt die Notiz des Angebots zurueck.
	 * 
	 * @return Notiz des Angebots.
	 */
	public String getNote() {
		return note;
	}

	/**
	 * Setzt die Notiz des Angebots.
	 * 
	 * @param note
	 *            Notiz des Angebots.
	 */
	public void setNote(String note) {
		this.note = note;
	}

	/**
	 * Gibt den Status des Angebots zurueck.
	 * 
	 * @return Status des Angebots.
	 */
	public boolean isChecked() {
		return checked;
	}

	/**
	 * Setzt den Status des Angebots.
	 * 
	 * @param checked
	 *            Status des Angebots.
	 */
	public void setChecked(boolean checked) {
		this.checked = checked;
	}

	/**
	 * Gibt die Anzahl der freien Plaetze des Angebots zurueck.
	 * 
	 * @return Anzahl der freien Plaetze des Angebots.
	 */
	public int getSlots() {
		return slots;
	}

	/**
	 * Setzt die freien Plaetze des Angebots.
	 * 
	 * @param slots
	 *            Freien plaetze des Angebots.
	 */
	public void setSlots(int slots) {
		this.slots = slots;
	}

	/**
	 * Gibt die Stunden pro Woche des Angebots zurueck.
	 * 
	 * @return Stunden pro Woche des Angebots.
	 */
	public double getHoursperweek() {
		return hoursperweek;
	}

	/**
	 * Setzt die Stunden pro Woche des Angebots.
	 * 
	 * @param hoursperweek
	 *            Stunden pro Woche des Angebots.
	 */
	public void setHoursperweek(double hoursperweek) {
		this.hoursperweek = hoursperweek;
	}

	/**
	 * Gibt die Beschreibung des Angebots zurueck.
	 * 
	 * @return Beschreibung des Angebots.
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Setzt die Beschreibung des Angebots.
	 * 
	 * @param description
	 *            Beschreibung des Angebots.
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Gibt den Beginn des Angebots zurueck.
	 * 
	 * @return Beginn des Angebots.
	 */
	public Date getStartdate() {
		return startdate;
	}

	/**
	 * Setzt den Beginn des Angebots.
	 * 
	 * @param startdate
	 *            Beginn des Angebots.
	 */
	public void setStartdate(Date startdate) {
		this.startdate = startdate;
	}

	/**
	 * Gibt das Ende des Angebots zurueck.
	 * 
	 * @return Ende des Angebots.
	 */
	public Date getEnddate() {
		return enddate;
	}

	/**
	 * Setzt das Ende des Angebots.
	 * 
	 * @param enddate
	 *            Ende des Angebots.
	 */
	public void setEnddate(Date enddate) {
		this.enddate = enddate;
	}

	/**
	 * Gibt den Stundenlohn des Angebots zurueck.
	 * 
	 * @return Stundenlohn des Angebots.
	 */
	public double getWage() {
		return wage;
	}

	/**
	 * Setzt den Stundenlohn des Angebots.
	 * 
	 * @param wage
	 *            Stundenlohn des Angebots.
	 */
	public void setWage(double wage) {
		this.wage = wage;
	}

	/**
	 * Gibt das zugehoerige Institut des Angebots zurueck.
	 * 
	 * @return Institut des Angebots.
	 */
	public int getInstitute() {
		return institute;
	}

	/**
	 * Setzt das Institut des Angebots.
	 * 
	 * @param institute
	 *            Institut des Angebots.
	 */
	public void setInstitute(int institute) {
		this.institute = institute;
	}

	/**
	 * Gibt das Datum zurueck, an dem das Angebot zuletzt bearbeitet wurde.
	 * 
	 * @return Letztes Aenderungsdatum des Angebots.
	 */
	public Date getModificationdate() {
		return modificationdate;
	}

	/**
	 * Setzt das letzte Aenderungsdatum des Angebots.
	 * 
	 * @param modificationdate
	 *            Letzte Aenderungsdatum des Angebots.
	 */
	public void setModificationdate(Date modificationdate) {
		this.modificationdate = modificationdate;
	}


	@Override
	public String toString() {
		return "Offer [name=" + name + ", checked=" + checked + ", institute="
				+ institute + "]";
	}


	
	
	
}
