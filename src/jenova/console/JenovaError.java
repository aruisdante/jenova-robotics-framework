package jenova.console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for representing a Jenova operation error message. JenovaError messages contain time-stamp information and arbitrary message content
 * JenovaErrors, in the default implementation of the JenovaFramework, DO NOT HALT OPERATION in any way, they simply pass information
 * in a specific format.
 * @author Adam Panzica
 *
 */
public class JenovaError extends Throwable{

	private static final long serialVersionUID = 8372311666750988782L;
	/**
	 * The message content of the JenovaError
	 */
	private String errorMessage;
	/**
	 * The time-stamp information of the JenovaErorr
	 */
	private String timeStamp;
	
	/**
	 * Constructs a JenovaError. Time-stamp information is automatically generated
	 * @param errorMessage String containing the content of the error message
	 */
	public JenovaError(String errorMessage){
		this.errorMessage=errorMessage;
		this.timeStamp=getDateTime();
	}
	
	public String toString(){
		return this.timeStamp+" :: "+this.errorMessage;
	}
	
	/**
	 * @return String representation of the current date and time for use as a time-stamp
	 */
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }
}
