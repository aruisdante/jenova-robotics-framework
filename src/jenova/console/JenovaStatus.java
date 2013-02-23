package jenova.console;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Class for representing a Jenova status message
 * @author Adam Panzica
 *
 */
public class JenovaStatus extends Throwable{
	private String message;
	private String timestamp;
	
	public JenovaStatus(String message) {
		this.message=message;
		this.timestamp=getDateTime();
	}
	
	public String toString(){
		return this.timestamp+" :: "+this.message;
	}
	
	private String getDateTime() {
        DateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
        Date date = new Date();
        return dateFormat.format(date);
    }

	private static final long serialVersionUID = 2502417510244305185L;

}
