/**
 * 
 */
package jenova.sensors;

/**
 * @author Adam Panzica
 * This interface defines basic sensor functions. 
 */
public interface ISensor<R, E> {
	/**
	 * @return the unique ID of the sensor
	 */
	public int getID();
	/**
	 * @return The name of the sensor
	 */
	public String getName();
	
	/**
	 * @param name New name of the sensor
	 */
	public void setName(String name);
	
	/**
	 * @return A string representation of the type of sensor (E.G. "Potentiometer")
	 */
	public String getSensorType();
	/**
	 * @return The raw value (internal units) of the sensor
	 */
	public R getRawValue();
	/**
	 * @return The value of the sensor in engineering units
	 */
	public E getEngValue();
	
	/**
	 * Sets the current raw value of the sensor
	 * @param rawValue current raw value of the sensor
	 */
	public void setRawVal(R rawValue);
	
	/**
	 * Updates the raw value of the sensor using its sensor interface
	 */
	public void updateRawValue();
	
	/**
	 * Sets the current engineering unit value of the sensor
	 * @param engVal current value of the sensor in engineering units
	 */
	public void setEngVal(E engVal);
	
	/**
	 * @return S string representation of the engineering unit of the sensor (I.E. feet)
	 */
	public String getEngUnit();
	
	/**
	 * @param unit new representation of the engineering unit of the sensor (I.E. feet)
	 */
	public void setEngUnit(String unit);
	
	/**
	 * updates the sensor with a new raw value, thus updating the eng value as well
	 * @param rawVal current raw value of the sensor
	 */
	public void update(R rawVal);
	
	/**
	 * @return The port that the sensor is assigned to
	 */
	public int getPort();
	
	/**
	 * Sets the port that the sensor is assigned to
	 */
	public void setPort(int port);
	
	public void setSensorInterface(ISensorInterface<R> sensorInterface);
}
