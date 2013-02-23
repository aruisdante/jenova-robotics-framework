package jenova.sensors;

import jenova.linearization.ILinearizationFunction;

/**
 * Abstract sensor class for creating sensors. Acts as a template for creating sensors that implement the ISensor interface
 * @author Adam Panzica
 *
 * @param <R> type of the raw sensor value
 * @param <E> type of the engineering unit sensor value
 */
public abstract class JenovaAbstractSensor<R, E> implements ISensor<R, E> {
	/**
	 * Name of the sensor
	 */
	private String name;
	/**
	 * Current raw value (internal units) of the sensor
	 */
	private R rawVal;
	/**
	 * Current value of the sensor in engineering units 
	 */
	private E engVal;
	/**
	 * String representation of the engineering unit of the sensor (e.g. Feet)
	 */
	private String engUnit;
	
	/**
	 * Linearization function for the sensor
	 */
	private ILinearizationFunction<R, E> linearize;
	/**
	 * Hardware abstraction layer for communicating with the sensor
	 */
	private ISensorInterface<R> sensorHardware;
	
	
	/**
	 * Port that the sensor occupies
	 */
	private int port;
	
	public JenovaAbstractSensor(int port, String name, String engUnit, R initialValue, ILinearizationFunction<R, E> linearize, ISensorInterface<R> sensorInterface){
		this.port = port;
		this.name = name;
		this.engUnit = engUnit;
		this.linearize = linearize;
		this.rawVal = initialValue;
		this.engVal = getLinearizedEngVal();
	}
	
	@Override
	public int getID(){
		return this.port;	//Returns port, as all sensors must have unique port assignments
	}
	
	@Override
	public String getEngUnit() {
		return this.engUnit;
	}
	
	@Override
	public void setEngUnit(String unit){
		this.engUnit = unit;
	}

	@Override
	public E getEngValue() {
		return this.engVal;
	}

	@Override
	public R getRawValue() {
		return this.rawVal;
	}
	
	@Override
	public String getName(){
		return this.name;
	}
	
	@Override
	public void setName(String name){
		this.name = name;
	}
	
	@Override
	public void setRawVal(R rawVal){
		this.rawVal=rawVal;
	}
	
	@Override
	public void setEngVal(E engVal){
		this.engVal = engVal;
	}
	
	@Override
	public int getPort(){
		return this.port;
	}
	
	@Override
	public void setPort(int port){
		this.port=port;
	}
	
	@Override
	public void updateRawValue(){
		this.rawVal = sensorHardware.getRawValue();
	}
	
	/**
	 * @return the linearized value of the sensor in engineering units
	 */
	public E getLinearizedEngVal(){
		return this.linearize.linearize(this.rawVal);
	}
	
	public String toString(){
		return "Sensor ID "+this.port+": "+this.getSensorType()+"- "+this.name+", Raw: "+this.rawVal+", Eng: "+this.engVal+this.engUnit;
	}
	
	@Override
	public void setSensorInterface(ISensorInterface<R> sensorInterface) {
		this.sensorHardware = sensorInterface;
	}
	
}
