/**
 * 
 */
package jenova.pid;

/**
 *  * Abstract class providing the basic interface and resources for creating a PID controller
 * @author Adam Panzica
 *
 * @param <T> Type of the target, current, and error values in the system
 * @param <C> Type of the correction value for the system
 * @param <G> Type of the gain constants for the PID algorithm.
 */
public abstract class JenovaAbstractPIDController <T, C, G> implements IJenovaPIDLink{
	/**
	 * Target value of the system
	 */
	protected T target;
	/**
	 * Current value of the system
	 */
	protected T current;
	/**
	 * Error in the system
	 */
	protected T error;
	/**
	 * Proportional gain constant
	 */
	protected G pGain;
	/**
	 * Derivative gain constant
	 */
	protected G dGain;
	/**
	 * Integral gain constant
	 */
	protected G iGain;
	/**
	 * Correction value for the system
	 */
	protected C correctionValue;
	
	/**
	 * Link to the system controlled by the PID controller
	 */
	protected IJenovaPIDSystemLink<T, C> system;
	
	/**
	 * 
	 * Constructor for the JenovaAbstractPIDController.
	 * @param initialTarget	Initial target value for the PID controller
	 * @param initialPGain	Initial proportional gain constant for the PID controller
	 * @param initialIGain	Initial Integral gain constant for the PID controller
	 * @param initialDGain	Initial Derivative gain constant for the PID controller
	 * @param system IJenovaPIDSystemLink for connecting the PID controller to the system it is controlling 
	 */
	protected JenovaAbstractPIDController(T initialTarget, G initialPGain, G initialIGain, G initialDGain, IJenovaPIDSystemLink<T, C> system){
		this.target = initialTarget;
		this.pGain = initialPGain;
		this.iGain = initialIGain;
		this.dGain = initialDGain;
		this.system = system;
	}
	
	/**
	 * Updates the current value of the system being controlled by the PID controller
	 */
	public void updateCurrent(){
		this.current = this.system.getCurrentValue();
	}
	
	/**
	 * Sets the proportional gain for the PID algorithm
	 * @param p New proportional gain
	 */
	public void setPGain(G p){
		this.pGain = p;
	}
	
	/**
	 * Sets the integral gain for the PID algorithm
	 * @param i New integral gain
	 */
	public void setIGain(G i){
		this.iGain = i;
	}
	
	/**
	 * Sets the derivative gain for the PID algorithm
	 * @param d New derivative gain
	 */
	public void setDGain(G d){
		this.dGain = d;
	}
	
	/**
	 * Applies the current correctionValue to the system. Intended to be used by a 'polling' type PID controller, as defined by the IJenovaPIDLink interface
	 */
	protected void applyCorrectionValue(){
		this.system.appliyControl(correctionValue);
	}
}
