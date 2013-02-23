package jenova.sensors;

import jenova.analog.sensors.Potentiometer;
import jenova.linearization.LinearizationFactory;
/**
 * Singleton Factory Class for creating JenovaSensors. This allows for a single method call to create any
 * sensor type, abstracting the implementation of the sensor object from the calling method, so long as it
 * expects the resultant sensor to be an ISensor
 *
 * @author Adam Panzica
 *
 */
public class JenovaSensorFactory {
	/**
	 * Enum representing the various types of sensors producible by the sensor factory.
	 * Each value has a string representing its sensor type name to allow for easy population of
	 * tables/drop down menus with available sensor types
	 *
	 */
	public static enum SensorType{
		POTENTIOMETER ("Potentionmeter");
		
		private final String typeName;
		SensorType(String typeName){
			this.typeName = typeName;
		}
		public String toString(){
			return this.typeName;
		}
	};
	
	/**
	 * Spawns a new ISensor compatible sensor object using an appropriate implementation given the input parameters
	 * @param type The desired sensor type
	 * @param port The port number that the sensor is on
	 * @param name The name of the sensor
	 * @param engUnit The engineering unit of the sensor (E.G. Volts)
	 * @param rawPoints A series of data points in raw sensor values to calculate a linearization function for the sensor from. rawPoints[x] should correspond to engPoints[x], in ascending order. The appropriate order of function will be generated based on the number of data points given
	 * @param engPoints A series of data points in engineering values to calculate a linearization function for the sensor from. rawPoints[x] should correspond to engPoints[x], in ascending order. The appropriate order of function will be generated based on the number of data points given
	 * @param sInterface The interface function for the sensor
	 * @return An implementation of the appropriate ISensor compatible sensor object based on the input parameters
	 */
	public static ISensor<Integer, Double> spawnSensor(SensorType type, int port, String name, String engUnit, int rawPoints[], double engPoints[], ISensorInterface<Integer> sInterface){
		switch(type){
		case POTENTIOMETER:
			return new Potentiometer(port, name, engUnit, LinearizationFactory.spawnLinearization(rawPoints, engPoints), sInterface);
			default:
			return null;
		}
	}
}
