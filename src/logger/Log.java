package logger;

/**
 * @author Tamino Hartmann
 */

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;

/**
 * This class implements the Logger for the complete program. All information
 * that could be useful for debugging or is required to check the correct
 * function of the program should be posted here. The standard setting is that
 * the log prints to the console AND writes to the log file. TODO: implement
 * that the option is read from the config file.
 */
public class Log {

	/**
	 * Variable for storing the instance of Log. Access with the getInstance()
	 * method.
	 */
	private static Log instance;

	/**
	 * Variable for the single instance referencing the BufferedWriter. This is
	 * required so that deactivating the fileStream temporally will not reset
	 * the complete log file.
	 */
	private BufferedWriter fileStreamSaved;
	/**
	 * Variable for storing the reference to the BufferedWriter of the file. Can
	 * be null.
	 */
	private BufferedWriter fileStream;
	/**
	 * Variable for storing the reference to the PrintStream of System.out. Can
	 * be null.
	 */
	private PrintStream sysStream;

	/**
	 * Variable for storing time stamp of program start.
	 */
	private long timer;

	/**
	 * Method for getting a valid instance of Log. The returned instance can
	 * then be used to write all the data.
	 * 
	 * @return The instance with which to work.
	 */
	synchronized public static Log getInstance() {
		if (Log.instance == null)
			Log.instance = new Log();
		return Log.instance;
	}

	/**
	 * Private constructor to ensure singleton instance. Use getInstance() to
	 * get a reference to Log.
	 */
	private Log() {
		// Set initial start time:
		startTimer();
		// Initialize fileStreamSaved:
		fileStreamSaved = createFile();
		// Set default output stream:
		setOutputStream(WriteTo.ALL, true);
	}

	/**
	 * Method to post something in the log.
	 * 
	 * @param caller
	 *            The name of the object that wants to log information.
	 * @param message
	 *            The message to show in the log.
	 */
	synchronized public void write(String caller, String message) {
		String time = getTimePassed() + "";
		if (getTimePassed() > 1000) {
			time = time.substring(0, time.length() - 3) + "."
					+ time.substring(time.length() - 3, time.length()) + "s";
		} else
			time += "ms";
		// Create log string:
		String output = "[" + time + ":" + caller + "] " + message;

		// Write to fileStream:
		if (fileStream != null) {
			try {
				fileStream.write(output);
				fileStream.newLine();
				fileStream.flush();
			} catch (IOException e) {
				setOutputStream(WriteTo.SYSOUT, true);
				write("LOG", "Error creating file. Setting stream to SYSOUT.");
			}
		}
		if (sysStream != null) {
			sysStream.println(output);
		}
	}

	/**
	 * This method promises to try to post the message only in the designated
	 * stream.
	 * 
	 * @param caller
	 *            The name of the object that wants to log information.
	 * @param message
	 *            The message to show in the log.
	 * @param stream
	 *            The stream to try to write to exclusively.
	 */
	synchronized public void write(String caller, String message, WriteTo stream) {
		String time = getTimePassed() + "";
		if (getTimePassed() > 1000) {
			time = time.substring(0, time.length() - 3) + "."
					+ time.substring(time.length() - 3, time.length()) + "s";
		} else
			time += "ms";
		// Create log string:
		String output = "[" + time + ":" + caller + "] " + message;
		switch (stream) {
		case ALL:
			try {
				fileStreamSaved.write(output);
				fileStreamSaved.newLine();
				fileStreamSaved.flush();
			} catch (IOException e) {
				// Do nothing if fails.
			}
			System.out.println(output);
			break;
		case LOGFILE:
			try {
				fileStreamSaved.write(output);
				fileStreamSaved.newLine();
				fileStreamSaved.flush();
			} catch (IOException e) {
				// Do nothing if fails.
			}
			break;
		case SYSOUT:
			System.out.println(output);
			break;
		case NONE:
			break;
		default:
			break;
		}
	}

	/**
	 * Method for changing where the stream goes.
	 * 
	 * @param stream
	 *            The variable that sets where the logs go.
	 * @param flag
	 *            When <code>true</code> activate writing to that stream, with
	 *            <code>false</code> deactivate. Has no effect with ALL or NONE.
	 */
	synchronized public void setOutputStream(WriteTo stream, boolean flag) {
		switch (stream) {
		case LOGFILE:
			fileStream = flag ? fileStreamSaved : null;
			break;
		case SYSOUT:
			sysStream = flag ? System.out : null;
			break;
		case ALL:
			sysStream = System.out;
			fileStream = fileStreamSaved;
			break;
		case NONE:
			sysStream = null;
			fileStream = null;
			break;
		default:
			break;
		}
	}

	/**
	 * Method that handles the correct setting of the BufferedWrite. Also
	 * handles the creation of the log file and handles some of the possible
	 * errors.
	 * 
	 * @return The BufferedWriter to which to write the log.
	 */
	private BufferedWriter createFile() {
		// If a fileStream already exists, a file has already been created in
		// this session, so we will use it instead (basically appending
		// further). Otherwise, this is the first call of the method and we need
		// to create a new, empty log file – this might well replace an old one!
		if (fileStream != null)
			return fileStream;

		final String SEPERATOR = System.getProperty("file.separator");
		final String HOME = System.getProperty("user.home");

		// Creates the file in the home directory in which the log will be
		// written.
		File logFile = new File(HOME + SEPERATOR + ".sopra_log");
		// If the file already exists, we need to delete it first:
		if (logFile.exists())
			logFile.delete();
		// Now create a new file and return the BufferedWriter:
		try {
			logFile.createNewFile();
			BufferedWriter buf = new BufferedWriter(new FileWriter(logFile));
			return buf;
		} catch (IOException e) {
			// On error, set to System.out and return null:
			e.printStackTrace();
			setOutputStream(WriteTo.SYSOUT, true);
			write("LOG", "Error creating file. Setting stream to SYSOUT.");
			return null;
		}
	}

	/**
	 * Method for restarting the timer. Should not be called from outside – Log
	 * does NOT provide custom timer implementations.
	 */
	private void startTimer() {
		timer = System.currentTimeMillis();
	}

	/**
	 * Method for reading the passed time since the start of the program.
	 * 
	 * @return Time passed in milliseconds.
	 */
	public long getTimePassed() {
		return System.currentTimeMillis() - timer;
	}
}