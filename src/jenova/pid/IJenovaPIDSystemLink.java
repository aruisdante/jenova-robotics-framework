package jenova.pid;

/**
 * Simple interface for linking a PID controller to the system being controlled. Provides methods for retrieving the current
 * value of the system, and operating on the control mechanism of the system
 * @author Adam Panzica
 *
 * @param <S> Type of the current value of the system
 * @param <C> Type of the control parameter of the system
 */
public interface IJenovaPIDSystemLink<S, C>{
	/**
	 * Retrieves the current value of the system
	 * @return the current value of the system
	 */
	public S getCurrentValue();
	
	/**
	 * Applies some control parameter to the system
	 * @param controlValue Control parameter to be applied to the system
	 */
	public void appliyControl(C controlValue);
	
	/**
	 * Informs the PID controller that the system is ready to be interacted with
	 * @return true if ready, else false
	 */
	public boolean ready();
}
