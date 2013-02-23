package jenova.sensors;

public interface ISensorManager<R,E> {
	/**
	 * Gets a sensor from the sensor manager
	 * @param index index in the sensor manager arraylist of the sensor
	 * @return the sensor object at that index
	 */
	public ISensor<R,E> getSensor(int index);
	
	/**
	 * @return  The number of sensors in registered with the sensor manager
	 */
	public int getNumberOfSensors();
}
