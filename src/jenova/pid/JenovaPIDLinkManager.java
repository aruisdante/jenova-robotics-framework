package jenova.pid;

import java.util.ArrayList;

import jenova.console.JenovaConsole;
import jenova.console.JenovaError;
import jenova.console.JenovaStatus;

public class JenovaPIDLinkManager implements IJenovaPIDLinkManager{
	/**
	 * Registery of all PIDLinks being managed
	 */
	private ArrayList <IJenovaPIDLink> registry;
	/**
	 * List of all polling PIDLinks being managed
	 */
	private ArrayList <IJenovaPIDLink> polling;
	/**
	 * List of all non polling PIDLinks being managed
	 */
	private ArrayList <IJenovaPIDLink> nonPolling;

	public JenovaPIDLinkManager(){
		this.registry = new ArrayList<IJenovaPIDLink>();
		this.polling = new ArrayList<IJenovaPIDLink>();
		this.nonPolling = new ArrayList<IJenovaPIDLink>();
	}

	@Override
	public void registerPIDLink(IJenovaPIDLink link) throws JenovaError{
		for(IJenovaPIDLink tempLink : registry){
			if(link.getName().equals(tempLink.getName())) throw new JenovaError("PID Link with the same name already exists");
		}
		this.registry.add(link);
		if(link.isPolling()==true)polling.add(link);
		else nonPolling.add(link);
		JenovaConsole.statusMessage(new JenovaStatus("PIDLink "+link.getName()+" sucessfully registered with the Link Manager"));
	}

	/**
	 * Gets a PIDLink from an ArrayList via its name field
	 * @param name Name of the PIDLink to retrieve
	 * @param list ArrayList to traverse to find the PIDLink
	 * @return The requested PIDLink, if it exists
	 * @throws JenovaError If the PIDLink does not exist, an error is thrown
	 */
	private IJenovaPIDLink getPIDLink(String name, ArrayList<IJenovaPIDLink> list) throws JenovaError{
		for(IJenovaPIDLink tempLink : list){
			if(tempLink.getName().equals(name)) return tempLink;
		}
		throw new JenovaError("No such PID Controller Exisits with the name: "+name);
	}

	@Override
	public void setTarget(String name, String target) throws JenovaError{
		IJenovaPIDLink tempLink = this.getPIDLink(name, this.registry);
		tempLink.setTarget(target);
	}

	@Override
	public void startPIDLinkNP(String name) throws JenovaError{
		IJenovaPIDLink tempLink = this.getPIDLink(name, this.nonPolling);
		tempLink.startPID();
	}

	@Override
	public void startAllPIDLinkNP() throws JenovaError{
		boolean error = false;
		int errorCount = 0;
		try{
		for(IJenovaPIDLink tempLink : nonPolling) if(tempLink.isActive()) tempLink.startPID();
		}
		catch(JenovaError e){
			JenovaConsole.errorMessage(e);
			error = true;
			errorCount++;
		}
		if(error == true) throw new JenovaError("Encountered " +errorCount+ "Error(s) while attempting to start all PIDLinks");
	}

	@Override
	public void stopPIDLinkNP(String name) throws JenovaError{
		IJenovaPIDLink tempLink = this.getPIDLink(name, this.nonPolling);
		tempLink.stopPID();
	}

	@Override
	public void stopAllPIDLinkNP() throws JenovaError{
		boolean error = false;
		int errorCount = 0;
		try{
			for(IJenovaPIDLink tempLink : nonPolling) if(tempLink.isActive()) tempLink.stopPID();
		}
		catch(JenovaError e){
			JenovaConsole.errorMessage(e);
			error = true;
			errorCount++;
		}
		if(error == true) throw new JenovaError("Encountered " +errorCount+ "Error(s) while attempting to stop all PIDLinks");
	}

	@Override
	public void updateAllPollingPIDLink() throws JenovaError{
		boolean error = false;
		int errorCount = 0;
		try{
		for(IJenovaPIDLink tempLink : polling) if(tempLink.isActive()) tempLink.startPID();
		}
		catch(JenovaError e){
			JenovaConsole.errorMessage(e);
			error = true;
			errorCount++;
		}
		if(error == true) throw new JenovaError("Encountered " +errorCount+ "Error(s) while attempting to update all PIDLinks");
	}
	
	@Override
	public ArrayList<IJenovaPIDLink> getLinks(){
		return this.registry;
	}
	
	public String toString(){
		String line1 = "\nPID Link Manager currently managing " + this.registry.size() + " Links:\n";
		String line2 = "Currently " + this.polling.size() + " Polling Links regestered:\n";
		String line3 = "";
		String line4 = "Currently " + this.nonPolling.size() + " Non Polling Links regestered:\n";
		String line5 = "";
		for(IJenovaPIDLink texter : this.polling) line3+=texter.toString()+"\n";
		for(IJenovaPIDLink texter : this.nonPolling) line5+=texter.toString()+"\n";
		return line1+line2+line3+line4+line5;
	}
}
