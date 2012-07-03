/**
 * @author Manuel Guentzel
 */
package database.garbage;

import java.util.Date;
import java.util.Timer;
/**
 * Diese Klasse sorgt dafuer, dass 2 Jahre alte Angebote mit all ihren dazugehoerigen Daten geloescht werden.
 */
public class GarbageCollector {
	/**
	 * Timer fuer das Scheduling des CollectorTasks.
	 */
	private Timer t;
	/**
	 * Instanz dieser Klasse.
	 */
	private static GarbageCollector instance;
	/**
	 * Gibt die Instanz des GarbageCollectors zurueck.
	 * @return Instanz.
	 */
	public static GarbageCollector getInstance(){
		if(instance==null)instance=new GarbageCollector();
		return instance;
	}
	/**
	 * Private, da Singleton
	 */
	private GarbageCollector() {
		t=new Timer();
	}
	/**
	 * Starten des Timers. Der CollectorTask wird jede Stunde gescheduled.
	 */
	public void start(){
		stop();
		t.scheduleAtFixedRate(new CollectorTask(), new Date(), 3600000);
	}
	/**
	 * Anhalten des Timers. Dabei werden alle Tasks in der Queue geloescht und ein neuer Timer angelegt.
	 */
	public void stop(){
		t.cancel();
		t= new Timer();
	}
}
