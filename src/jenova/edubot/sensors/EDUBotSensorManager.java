package jenova.edubot.sensors;

import jenova.console.JenovaConsole;
import jenova.console.JenovaError;
import jenova.console.JenovaStatus;
import jenova.edubot.device.manager.JenovaDeviceManager;
import jenova.sensors.ISensor;
import jenova.sensors.JenovaSensorManager;

/**
 * Class for representing an EDUBot sensor manager, which provides a convenient way of managing a group of sensors
 * The sensor manager is fully integrated with the EDUBot device manager, meaning that it handles all communications to and from the
 * robot. The user need only access values through the provided methods. NOTE: The default raw sensor value type is Integer,
 * and the default Engineering Unit type is Double
 * 
 * @author Adam Panzica
 * @version 0.1
 */
public class EDUBotSensorManager extends JenovaSensorManager<Integer, Double>{
	static int NOSENSOR = 99; 		//Deliminator for representing a pin without an assigned sensor. Must be out of the range of MINPIN->MAXMIN
	static int MINADCPIN = 0;		//Value of the first ADC PIN (should be 0)
	static int MAXADCPIN = 7;		//Value of the last ADC PIN (generally 7)
	static int PORTCOUNT = 32;		//Total number of ports available to sensors
	
	//private JenovaSensorManager<Integer, Double> sensors = new JenovaSensorManager<Integer, Double>();	//Jenova Sensor manager for storing sensor data
	private int[] portTable;					//Look up table correlating port number (index to array) with SensorManager index (value at index in array). Ports MINADCPIN-MAXADCPIN are reserved for analog sensors
	private JenovaDeviceManager deviceManager; 	//device manager for communicating with EDUbot
	
	/********************************** CONSTRUCTORS ******************************************/
	/**
	 * Constructor for creating an EDUBotSensorManager
	 * @param deviceManager JenovaDeviceManager to use for automated interfacing with sensors
	 */
	public EDUBotSensorManager(JenovaDeviceManager deviceManager){
		super();
		this.deviceManager = deviceManager;
		portTable = new int[PORTCOUNT];
		for(int i=MINADCPIN; i<this.portTable.length; i++) portTable[i] = NOSENSOR;
		JenovaConsole.statusMessage(new JenovaStatus("EDUBot Sensor Manager Initialized"));
	}
	
	
	
	/**************************************** SENSOR REGISTRATION METHODS **********************************************/
	
	/**
	 * Registers a new analog sensor (which implements the ISensor interface) with the sensor manager. Uses the Port field as the
	 * ADC Pin to assign the sensor to
	 * @param sensor ISensor object containing the sensor information. The ISensorInterface should be left null, as it will be generated automatically
	 * @throws JenovaError If either an invalid port or an invalid sensor are given
	 */
	public void registerAnalogSensor(ISensor<Integer, Double> sensor) throws JenovaError{
		if(checkADCBounds(sensor.getPort())){																		//check to see if the desired port is a valid ADC port
			if(this.portTable[sensor.getPort()]==NOSENSOR){															//check to see if there is already a sensor registered to that port
				sensor.setSensorInterface(new EDUBotAnalogSensorInterface(this.deviceManager, sensor.getPort()));	//Generate ISensorInterface for EDUBot analog sensors for the sensor
				this.portTable[sensor.getPort()] = this.registerSensor(sensor);								//Register sensor with the sensor manager and with the port table
				JenovaConsole.statusMessage(new JenovaStatus("New Analog Sensor Registered: Port "+sensor.getPort()+", "+sensor.getName()+", "+sensor.getSensorType()));
			} else throw new JenovaError("Sensor Already Registered To Port "+sensor.getPort());
		} else throw new JenovaError("Invalid Port Number. Valid range is "+MINADCPIN+"-"+MAXADCPIN+", given: "+sensor.getPort());
	}
	
	/**
	 * Registers a digital sensor with the sensor manager
	 * @param sensor ISensor object containing the sensor information. Must have a valid ISensorInterface in order to function
	 * @throws JenovaError If either an invalid port or an invalid sensor are given
	 */
	public void registerDigitalSensor(ISensor<Integer, Double> sensor) throws JenovaError{
		if(!checkADCBounds(sensor.getPort())&&checkPortBounds(sensor.getPort())){		//Check to ensure that the desired port is valid and not an ADC port
			if(this.portTable[sensor.getPort()]==NOSENSOR){								//Check to see if there is already a sensor registered to that port
				this.portTable[sensor.getPort()] = this.registerSensor(sensor);	//Register the sensor with the sensor manager and the port table
				JenovaConsole.statusMessage(new JenovaStatus("New Digital Sensor Registered: Port"+sensor.getPort()+", "+sensor.getName()+", "+sensor.getSensorType()));
			} else throw new JenovaError("Sensor Already Registered To Port "+sensor.getPort());
		} else throw new JenovaError("Invalid Port Number.  Valid range is 0-"+PORTCOUNT+", given: "+sensor.getPort());
	}
	
	/******************************************** PRIVATE INTERNAL SENSOR UPDATING METHODS ************************************/
	
	/**
	 * Updates the sensor on the given port (fastest access method)
	 * @param port port number of the sensor
	 * @throws Exception If an invalid port is given
	 */
	private void updateSensor(int port) throws JenovaError{
		if(checkPortBounds(port)){
			if(this.portTable[port] == NOSENSOR) throw new JenovaError("No Sensor Registered to Given Port: "+port);
			else this.getSensorByIndex(this.portTable[port]).updateRawValue();
		}else throw new JenovaError("Invalid Port Number. Valid range is 0-"+PORTCOUNT+", given: "+port);
	}
	
	/**
	 * Updates the sensor matching the given name
	 * @param name Name of the sensor to update
	 * @return the index of the sensor
	 * @throws Exception If and invalid name is given
	 */
	private ISensor<Integer, Double> updateSensor(String name) throws JenovaError{
		ISensor<Integer, Double> sensor = this.getSensorByName(name);	//Looks up the sensor matching the given name. This call also handles bounds checking (invalid sensor names)
		sensor.updateRawValue();
		return sensor;
	}
	
	/***************************************** PUBLIC SENSOR VALUE RETRIVING METHODS *******************************************/
	
	/**
	 * Gets the current engineering unit value from a sensor registered to a given port (fastest access method)
	 * @param port Port number of the sensor
	 * @return Engineering unit value of the sensor
	 * @throws Exception If an invalid pin is given, or if there is a sensor read error
	 */
	public Double getSensorEng(int port) throws JenovaError{
		updateSensor(port);	//Updates the sensor to return the most up to date value. This call also handles the port bounds checking
		return this.getSensorByIndex(this.portTable[port]).getEngValue();
	}
	
	/**
	 * Gets the current engineering unit value from a sensor registered to a given name
	 * @param name Name of the sensor
	 * @return Engineering unit value of the sensor
	 * @throws Exception if an invalid name is given, or if there is a sensor read error
	 */
	public Double getSensorEng(String name) throws JenovaError{
		ISensor<Integer, Double> sensor = updateSensor(name);
		return sensor.getEngValue();
	}
	
	/**
	 * For use with asynchronous data, updates a sensor with a given raw value and then returns the new engineering value
	 * @param port Port of the sensor to update
	 * @param rawVal New raw value of the sensor
	 * @return The engineering unit value of the sensor
	 * @throws JenovaError If an invalid port is specified
	 */
	public Double getSensorEng(int port, int rawVal) throws JenovaError{
		if(checkPortBounds(port)){
			if(portTable[port]!=NOSENSOR){
				ISensor<Integer,Double> sensor = this.getSensorByIndex(portTable[port]);
				sensor.update(rawVal);
				return sensor.getEngValue();
			} else throw new JenovaError("Cannot Update/Get Sensor Value. No Sensor Registered To Port "+port);
		} else throw new JenovaError("Cannot Update/Get Sensor Value, Port Out Of Bounds, Given Port "+port);
	}
	
	public Double getSensorEng(String name, int rawVal) throws JenovaError{
		ISensor<Integer,Double> sensor = this.getSensorByName(name);
		sensor.update(rawVal);
		return sensor.getEngValue();
	}
	
//	/**
//	 * Updates all of the sensors registered with the sensor manager
//	 * @throws Exception If there are no sensors registered, or if there is an update error
//	 */
//	private void updateAll(int testRaw) throws JenovaError{
//		if(sensors.getNumberOfSensors() == 0) throw new JenovaError("No Sensors Registered");
//		else{
//			for(int pin=0; pin<this.portTable.length; pin++){	//Runs through the port table looking for registered sensors to update
//				if(this.portTable[pin]!=NOSENSOR){
//					sensors.getSensorByIndex(portTable[pin]).update(testRaw)/*updateRawValue()*/;
//				}
//			}
//		}
//	}
	
	/**
	 * Gets the current value of all sensors registered with the sensor manager
	 * @return An array containing the current value in engineering units from all sensors, in order of assigned ADC pin from 0-7 (NOTE: only pins assigned sensors return values)
	 * @throws Exception If there are no registered sensors, or if there is a sensor read error
	 */
	public Double[] getAllEng() throws JenovaError{
		Double[] sensorValues;
		int step = 0;
		
		sensorValues = new Double[this.getNumberOfSensors()];
		for(int port=0;port<this.portTable.length;port++){	//runs through and gets the engineering value from all registered sensors
			if(this.portTable[port]!=NOSENSOR){
				sensorValues[step] = getSensorEng(this.portTable[port]);
				step++;
			}
		}
		return sensorValues;
	}

	/********************************************** MISC. BOOK KEEPING METHODS *************************************/
	/**
	 * Prints out a list of all registered sensors and their current states
	 */
	public void printSensorList(){
		System.out.println("There are currently "+this.getNumberOfSensors()+" sensors regestered:");
		for(int port=0; port<this.portTable.length;port++){
			if(this.portTable[port]!=NOSENSOR){
				System.out.println("Port "+port+": "+this.getSensorByIndex(portTable[port]));
			}
		}
	}
	
	/**
	 * Checks to see if a port is within the valid bounds for ADC sensors
	 * @param port Port of the sensor
	 * @return True if the sensor is on a valid ADC port
	 */
	private boolean checkADCBounds(int port){
		return (port>=MINADCPIN&&port<=MAXADCPIN);
	}
	
	/**
	 * Checks to see if a port is within the valid bounds for sensors
	 * @param port Port of the sensor
	 * @return True if the sensor is on a valid port
	 */
	private boolean checkPortBounds(int port){
		return (port>=0 && port<PORTCOUNT);
	}
	
	/******************************************* ISENSORMANGER INTERFACE METHODS ***************************************/
	
	@Override
	public ISensor<Integer, Double> getSensor(int index){
		return this.getSensorByIndex(index);
	}
	
	@Override
	public String toString(){
		return "EDUBot Analog Sensor Manager. Currently "+this.getNumberOfSensors()+" Registered";
	}	
}
