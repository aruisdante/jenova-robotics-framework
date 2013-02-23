package jenova.pid;

import java.util.Iterator;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import jenova.console.JenovaError;
/**
 * Default implementation of a Polling type PID Controller based on the JenovaAbstractPIDController.
 * This PID controller is intended to have calls to its startPID() method called every 'cycle' for control of the PIDSystem,
 * taking one new error sample and generating one new correction value per startPID() call. It interfaces to the system
 * being controlled via an IJenovaPIDSystemLink object, and relies on that object to interpret the PID correction value,
 * which is simply a unit-less double representing the sum of the P, I and D components, allowing it to be as generic as possible.
 * @author Adam Panzica
 *
 */
public class JenovaPollingPIDController extends JenovaAbstractPIDController<Double, Double, Double>{
	/**
	 * Size limited queue for storing error values. This is used to provide accurate Integral/Derivative values without allowing integral windup
	 */
	private BlockingQueue <Double> errorQueue;
	/**
	 * Name of the PIDController
	 */
	private String name;
	
	/**
	 * Creates a new JenovaPollingPIDController with some initial target value for the system. This prevents the PIDController from being utilized without a target value being provided
	 * @param name Name of the PIDController
	 * @param initialTarget The initial target value for the system
	 * @param pGain The Proportional Gain Constant for the PID Algorithm
	 * @param iGain The Integral Gain Constant for the PID Algorithm
	 * @param dGain The Derivative Gain Constant for the PID Algorithm
	 * @param system The IJenovaPIDSystemLink object that allows the PIDController to access the current value of the system and apply control to the system
	 */
	protected JenovaPollingPIDController(String name, Double initialTarget, Double pGain,
			Double iGain, Double dGain,
			IJenovaPIDSystemLink<Double, Double> system) {
		super(initialTarget, pGain, iGain, dGain, system);
		errorQueue = new ArrayBlockingQueue<Double>(10);
		this.name = name;
	}

	@Override
	public boolean isPolling() {
		return true;
	}

	@Override
	public boolean isActive() {
		return this.system.ready();
	}

	@Override
	public boolean startPID() throws JenovaError {
		if (this.system.ready()==false) throw new JenovaError("PID Controller "+this.name+" is not ready for operation.");
		else{
			this.current = system.getCurrentValue();
			this.error = this.target-this.current;
			this.adjustErrorSumWindow(this.error);
			this.correctionValue = this.calcPID();
			this.system.appliyControl(this.correctionValue);
			return true;
		}
	}

	@Override
	public boolean stopPID() throws JenovaError {
		return true;
	}

	@Override
	public void setTarget(String target) throws JenovaError {
		try{
			this.target = Double.parseDouble(target);
		}
		catch(NumberFormatException e){
			throw new JenovaError("Invalid target value: "+ e.toString());
		}
	}

	@Override
	public String getPGain() {
		return this.pGain.toString();
	}

	@Override
	public String getDGain() {
		return this.dGain.toString();
	}

	@Override
	public String getIGain() {
		return this.iGain.toString();
	}

	@Override
	public String getTargetVal() {
		return this.target.toString();
	}

	@Override
	public String getCurrentVal() {
		return this.current.toString();
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	
	/**
	 * Adjust the moving sum window in the errorQueue by adding a new error value to it.
	 * Attempts to add a new error to the errorQueue, and if it is full, removes the head (which is now the oldest error value)
	 * and adds the new error value onto the queue. This prevents integral windup by limiting the amount of samples the error
	 * sum looks at.
	 * @param newError New error value to add to the errorQueue
	 */
	private void adjustErrorSumWindow(double newError){
		if(!(errorQueue.offer(newError))){
			errorQueue.poll();
			errorQueue.offer(newError);
		}
	}
	
	/**
	 * @return The proportional component of the PID algorithm
	 */
	private double calcP(){
		return this.pGain*this.error;
	}
	
	/**
	 * @return The derivative component of the PID algorithm. Is the average derivative of up to the last 10 error samples
	 */
	private double calcD(){
		double divCount=0;
		double divSum=0;
		double lastError=0;

		for(Iterator<Double> diviter = errorQueue.iterator(); diviter.hasNext();){
			double term1 = diviter.next();
			divSum += (lastError-term1);
			lastError = term1;
			divCount++;
		}
		return this.dGain*(divSum/divCount);
	}
	
	/**
	 * @return The derivative component of the PID algorithm. Is the average derivative of up to the last 10 error samples
	 */
	private double calcI(){
		double sum = 0;
		for(Iterator<Double> summer = errorQueue.iterator(); summer.hasNext();){
			sum += summer.next();
		}
		return this.iGain*sum;
	}
	
	/**
	 * @return 	 A raw PID value that is the sum of the three components has efficiency optimizations to stop unnecessary calculations if gain constants are zero
	 */
	private double calcPID(){
		double PID = this.calcP();
		if(this.iGain!=0)PID+=this.calcI();
		if(this.dGain!=0)PID+=this.calcD();
		return PID;
	}

}
