/**
 * @author Manuel Guentzel
 */
package database.garbage;

import java.util.Date;
import java.util.Timer;
/**
 * 
 */
public class GarbageCollector {

	/**
	 * 
	 */
	private Timer t;
	/**
	 * 
	 */
	private static GarbageCollector instance;
	/**
	 * 
	 * @return
	 */
	static GarbageCollector getInstance(){
		if(instance==null)instance=new GarbageCollector();
		return instance;
	}
	/**
	 * 
	 */
	private GarbageCollector() {
		t=new Timer();
	}
	/**
	 * 
	 */
	public void start(){
		stop();
		t.scheduleAtFixedRate(new CollectorTask(), new Date(), 3600000);
	}
	/**
	 * 
	 */
	public void stop(){
		t.cancel();
		t= new Timer();
	}
}
