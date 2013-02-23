package jenova.console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Console shell to allow for the output of formatted Jenova messages as well as logging facilities.
 * MUST BE A SINGLETON IN IMPLEMENTATIONS. THERE SHOULD BE ONE, AND ONLY ONE, INSTANCE OF THIS CLASS
 * @author Adam Panzica
 *
 */
public class JenovaConsole {
	private static ArrayList<JenovaError> errorLog = new ArrayList<JenovaError>();
	private static ArrayList<JenovaStatus> statusLog = new ArrayList<JenovaStatus>();
	private static IJenovaConsoleDisplay consoleDisplay;
	
	/**
	 * Constructor for instantiating a JenovaConsole without any view controller. Will print messages to the default Java console
	 * Shouldn't be used as all methods are static and the Java console can be accessed without instantiating the class, but provided to show the option
	 */
	public static void SetConsoleDisplay(){
		
	};
	
	/**
	 * Constructor for instantiating a JenovaConsole using a view controller implementing the IJenovaConsoleDisplay interface to print messages to rather than the default Java console
	 * @param consoleDisplay view controller that will handle displaying messages.
	 */
	public static void SetConsoleDisplay(IJenovaConsoleDisplay consoleDisplay){
		JenovaConsole.consoleDisplay=consoleDisplay;
	}
	
	/**
	 * Outputs a formatted JenovaError to the console
	 * @param error JenovaError to process
	 */
	public static void errorMessage(JenovaError error){
		String message = "JENOVA ERROR- "+error;
		if(consoleDisplay == null) System.err.println(message);
		else consoleDisplay.printConsoleErrorMessage(message+"\n");
		logError(error);
	}
	
	/**
	 * Outputs a formatted JenovaStatus to the console
	 * @param status JenovaStatus to process
	 */
	public static void statusMessage(JenovaStatus status){
		String message = "JENOVA STATUS- "+status;
		if(consoleDisplay == null) System.out.println(message);
		else consoleDisplay.printConsoleStatusMessage(message+"\n");
		logStatus(status);
	}
	
	/**
	 * Prints out a log of all JenovaErrors processed with time stamps
	 */
	public static void printErrorLog(){
		String header = "JENOVA ERROR LOG AS OF -"+getDateTime()+"-";
		if(consoleDisplay == null){
		System.out.println(header);
		for(int i=0; i<errorLog.size(); i++){
			System.out.println(errorLog.get(i));
		}
		}
		else{
			consoleDisplay.printConsoleMessage(header+"\n");
			for(int i=0; i<errorLog.size(); i++){
				consoleDisplay.printConsoleMessage(errorLog.get(i).toString()+"\n");
			}
		}
	}
	
	/**
	 * Prints out a log of all JenovaStatus processed with time stamps
	 */
	public static void printStatusLog() {
		String header = "JENOVA STATUS LOG AS OF -"+getDateTime()+"-";
		if(consoleDisplay == null){
			System.out.println(header);
			for(int i=0; i<statusLog.size(); i++){
				System.out.println(statusLog.get(i)+"\n");
			}
		}
		else{
			consoleDisplay.printConsoleMessage(header+"\n");
			for(int i=0; i<statusLog.size(); i++){
				consoleDisplay.printConsoleMessage(statusLog.get(i).toString()+"\n");
			}
		}
		
	}
	
	/**
	 * Adds a JenovaError to the error log
	 * @param error JenovaError to add to the log
	 */
	private static void logError(JenovaError error){
		errorLog.add(error);
	}
	
	/**
	 * Adds a JenovaStatus to the status log
	 * @param status JenovaStatus to add to the log
	 */
	private static void logStatus(JenovaStatus status){
		statusLog.add(status);
	}
	
	/**
	 * Allows for the generation of time stamps
	 * @return formatted time stamp string
	 */
	private static String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
