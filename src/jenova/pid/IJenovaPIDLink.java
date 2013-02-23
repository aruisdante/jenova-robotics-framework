package jenova.pid;

import jenova.console.JenovaError;

/**
 * Interface for simple abstract interactions with a PID controller. Provides an interface to query if the PID controller
 * linked to the interface is active, query if the PID controller linked to the interface is one that needs to be polled
 * or is 'set and forget', instruct the PID controller to operate (or update if it is polling), instruct the PID controller
 * to stop operating (if it is not polling), and to retrieve String representations of the common parameters
 * utilized in a PID algorithm. Essentially, the PIDLink removes the need for the system interfacing with PID controllers
 * to need to know anything about the implementation of the PID controller, something that wouldn't be possible using the
 * JenovaAbstractPIDController abstract class due to its use of Java Generic to allow for flexibility in implementation.
 * Also allows PIDControllers to be JenovaFramework compatible without having to use the JenovaAbstractPIDController at all. 
 * @author Adam Panzica
 *
 */
public interface IJenovaPIDLink {
	/**
	 * Query the PID controller linked to the interface if it is 'polling'.
	 * 'Polling' PID controllers must receive runPID commands() every 'cycle' to operate
	 * 'Non-polling' PID controllers only need to receive a single runPID() command and will then operate until receiving a stopPID() command
	 * @return True if polling, false if non-polling
	 */
	public boolean isPolling();
	
	/**
	 * Query the PID controller linked to the interface if it is 'active'
	 * In the case of a 'polling' PID controller, should mean fully initialized and ready to receive a startPID() command
	 * In the case of a 'non-polling' PID controller, this should mean in operation.
	 * @return True if the PID controller linked to the interface is active, else false
	 */
	public boolean isActive();
	
	/**
	 * Instructs the PID controller linked to the interface to operate.
	 * In the case of a Polling controller, this causes it to complete one 'cycle'
	 * In the case of a Non-Polling controller, this causes it to begin operation, which should continue until a stopPID() command is received.
	 * @return True if the startPID() command executed properly, else false.
	 * @throws JenovaError if the runPID command did not execute properly, throws a JenovaError explaining what went wrong
	 */
	public boolean startPID() throws JenovaError;
	
	/**
	 * Instructs the PID controller linked to the interface to stop operating
	 * In the case of a Polling controller, this command is expected to do nothing, and should always return true
	 * In the case of a Non-Polling controller, this command stops it from operating
	 * @return True if the PID controller was successfully stopped, else false.
	 * @throws JenovaError If the PID controller was not successfully stopped, throws a JenovaError explaining what went wrong
	 */
	public boolean stopPID() throws JenovaError;
	
	/**
	 * Sets the target for the system. This function needs to handle the casting from String to the correct type as required by the PID controller linked to the system
	 * NOTE: this is currently a very ugly way to handle this, but it allows for type-agnostic setting of a target value.
	 * TODO: Figure out a better way to handle this 
	 * @param target Target value for the PID system
	 * @throws JenovaError If there is no way to parse the passed target value into the correct type, an error message is thrown
	 */
	public void setTarget(String target) throws JenovaError;
	
	/**
	 * @return A representation of the Proportional Gain Constant used by the PID algorithm in the PID controller linked to the interface
	 */
	public String getPGain();
	
	/**
	 * @return A representation of the Derivative Gain Constant used by the PID algorithm in the PID controller linked to the interface
	 */
	public String getDGain();
	
	/**
	 * @return A representation of the Integral Gain Constant used by the PID algorithm in the PID controller linked to the interface
	 */
	public String getIGain();
	
	/**
	 * @return A representation of the target value of the system controlled by the PID controller linked to the interface
	 */
	public String getTargetVal();
	
	/**
	 * @return A representation of the current value of the system controlled by the PID controller linked to the interface
	 */
	public String getCurrentVal();
	
	/**
	 * @return The name of the PID controller linked to the interface
	 */
	public String getName();
}
