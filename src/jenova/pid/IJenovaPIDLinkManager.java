package jenova.pid;

import java.util.ArrayList;

import jenova.console.JenovaError;

/**
 * Interface for defining a JenovaPIDLink manager. Specifies the generic functionality required by a practical PIDLink manager
 * @author Adam Panzica
 *
 */
public interface IJenovaPIDLinkManager {
	/**
	 * Registers a PIDLink with the manager
	 * @param link Link to add to the manager. Must have a unique name
	 * @throws JenovaError If the name is not unique, the PIDLink can not be added 
	 */
	public void registerPIDLink(IJenovaPIDLink link) throws JenovaError;
	
	/**
	 * Sets a new system target value for a specified PIDLink
	 * @param name Name of the PIDLink to adjust the target of, if it exists
	 * @param target New system target value
	 * @throws JenovaError If a PIDLink with the given name does not exist
	 */
	public void setTarget(String name, String target) throws JenovaError;
	
	/**
	 * Starts a specified non polling PIDLink, if it exists
	 * @param name Name of the PIDLink to be turned on
	 * @throws JenovaError If the specified PIDLink does not exist, or if there is a problem activating the specified PIDLink
	 */
	public void startPIDLinkNP(String name) throws JenovaError;
	
	/**
	 * Starts all non polling PIDLinks registered with the manager
	 * @throws JenovaError if there is a problem enabling any of the PIDLinks
	 */
	public void startAllPIDLinkNP() throws JenovaError;
	
	/**
	 * Stops a specified non polling PIDLink, if it exists
	 * @param name Name of the PIDLink to be disabled
	 * @throws JenovaError If the specified PIDLink does not exist, or if there is a problem disabling the specified PIDLink
	 */
	public void stopPIDLinkNP(String name) throws JenovaError;
	
	/**
	 * Stops all non polling PIDLinks registered with the manager
	 * @throws JenovaError if there is a problem disabling one of the PIDLinks
	 */
	public void stopAllPIDLinkNP() throws JenovaError;
	
	/**
	 * Updates all polling PIDLinks registered with the manager
	 * @throws JenovaError if there is a problem updating one of the PIDLinks
	 */
	public void updateAllPollingPIDLink() throws JenovaError;
	
	/**
	 * @return An ArrayList containing all of the PIDLinks registered with the manager
	 */
	public ArrayList<IJenovaPIDLink> getLinks();
	
	
}
