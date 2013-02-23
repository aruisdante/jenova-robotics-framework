package jenova.sensors;

import java.util.ArrayList;
import java.util.Iterator;

import jenova.console.JenovaError;

/**
 * Class for creating an object which manages a set of sensors. Note that all sensors must have
 * the same raw and engineering element type (e.g. Integer, Double), though what those values
 * represent need not be the same.
 * @author Adam Panzica
 *
 * @param <R> Type of the raw value for the sensors being managed (e.g. Integer)
 * @param <E> Type of the engineering value for the sensors being managed (e.g. Double)
 */
public class JenovaSensorManager <R, E> implements ISensorManager<R,E>{
	protected ArrayList<ISensor<R, E>> sensorList;
	
	public JenovaSensorManager(){
		this.sensorList = new ArrayList<ISensor<R, E>>();
	}
	
	/**
	 * Registers a new sensor with the sensor manager
	 * @param sensor The ISensor to be registered. Must have a unique ID and name
	 * @return the index of the sensor
	 * @throws Exception if either the supplied ID or name are already taken
	 */
	public int registerSensor(ISensor<R, E> sensor) throws JenovaError{
		ISensor<R, E> tempSense;
		for(Iterator<ISensor<R, E>> checker = sensorList.iterator(); checker.hasNext();){
			tempSense = checker.next();
			 if((sensor.getName() == tempSense.getName())){
				 throw new JenovaError("Sensor name '"+sensor.getName()+"' already exists");
			 }else if(sensor.getID() == tempSense.getID()){
				 throw new JenovaError("Sensor ID " + sensor.getID()+" already exists");
			 }
		}
		sensorList.add(sensor);
		//System.out.println("Sensor Added: "+sensor.getSensorType()+"- "+sensor.getName());
		return sensorList.indexOf(sensor); 
	}
	
	/**
	 * Generates a list of all of the sensors currently registered with the sensor manager
	 */
	public void printSensorList(){
		System.out.println("There are currrently "+sensorList.size()+" sensors registered:");
		for(int i = 0; i< sensorList.size(); i++){
			System.out.println(sensorList.get(i));
		}
	}
	
	/**
	 * Retrieves a sensor based on the internal sensor manager index. This is the fastest mode
	 * of access, but care must be taken as sensor indexes can change if sensors are de-registered
	 * @param index Index of the sensor, or null if index is out of bounds
	 * @return the ISensor at the passed index
	 */
	public ISensor<R, E> getSensorByIndex(int index){
		if(index<sensorList.size()){
			return sensorList.get(index);
		}
		return null;
	}
	
	/**
	 * Retrieves a sensor based on its unique ID
	 * @param ID Unique ID of the desired sensor
	 * @return The ISensor matching the given ID
	 * @throws Exception if an invalid ID is provided
	 */
	public ISensor<R, E> getSensorID(int ID)throws JenovaError{
		for(ISensor<R, E> tempSense : sensorList){
			if(tempSense.getID() == ID) return tempSense;
		}
		throw new JenovaError("Invalid ID "+ID+", cannot retrieve sensor");
	}
	
	/**
	 * Retrieves a sensor based on its name
	 * @param name name of the sensor
	 * @return the ISensor matching the given name
	 * @throws Exception if an invalid name is given
	 */
	public ISensor<R, E> getSensorByName(String name)throws JenovaError{
		for(ISensor<R, E> tempSense : sensorList){
			if(tempSense.getName() == name) return tempSense;
		}
		throw new JenovaError("Invalid Name '"+name+"', cannot retrieve sensor");
	}
	
	/**
	 * Gets the internal index of a specified ISensor object
	 * @param sensor ISensor object to retrieve the index of
	 * @return the index of the ISensor
	 * @throws Exception if the given ISensor is not registered in the system
	 */
	public int getSensorIndex(ISensor<R, E> sensor) throws JenovaError{
		int index = sensorList.indexOf(sensor);
		if(index == -1) throw new JenovaError("No matching sensor registered");
		else return index;
	}
	
	/**
	 * @return The number of sensors registered
	 */
	public int getNumberOfSensors(){
		return this.sensorList.size();
	}
	
	public String toString(){
		return "Currently "+getNumberOfSensors()+" Sensors Registered";
	}

	@Override
	public ISensor<R, E> getSensor(int index) {
		return getSensorByIndex(index);
	}
}
