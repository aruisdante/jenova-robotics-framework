package jenova.bot;

import jenova.mappingsystem.CartCoord;
import jenova.mappingsystem.HawkEye;
import jenova.mappingsystem.IMapSystem;
import jenova.mappingsystem.MapObject;
import jenova.mappingsystem.ObjectLocationData;
import jenova.pid.IJenovaPIDLinkManager;
import jenova.pid.JenovaPIDLinkManager;
import jenova.sensors.ISensorManager;
import jenova.sensors.JenovaSensorManager;

/**
 * Abstract class for representing robots using the Jenova framework. Does not include any GUI facilities
 * @author Adam Panzica
 *
 * @param <R> Value type for the raw values in the sensor manager
 * @param <E> Value type for the engineering values in the sensor manager
 */
public abstract class JenovaBot <R, E>{
	/**
	 * Name of the JenovaBot
	 */
	protected String name;
	/**
	 * Mapping system for the JenovaBot
	 */
	protected IMapSystem mapSystem;  					
	/**
	 * Sensor Manager for the JenovaBot
	 */
	protected ISensorManager<R, E> sensors;				
	/**
	 * JenovaPIDLink Manager for the JenovaBot
	 */
	protected IJenovaPIDLinkManager pidManager;				
	
	/**
	 * Constructor for the JenovaBot
	 * @param name 				Name of the JenovaBot
	 * @param globalMapWidth	Width of the global map (see Hawkeye)
	 * @param globalMapHeight	Height of the global map (see Hawkeye)
	 * @param localMapWidth		Width of the local map (see Hawkeye)
	 * @param localMapHeight	Height of the local map (see Hawkeye)
	 * @param globalOrigin		Origin of the global map (see Hawkeye)
	 * @param localOrigin		Origin of the local map (see Hawkeye)
	 * @param initialLocalOrientation Initial orientation of the local map inside the global map (see Hawkeye)
	 * @param initialState		Initial state to fill the global map with (see Hawkeye)
	 */
	protected JenovaBot(String name, int globalMapWidth, int globalMapHeight, int localMapWidth, int localMapHeight, CartCoord globalOrigin, CartCoord localOrigin, ObjectLocationData initialLocalOrientation, MapObject initialState){
		this.name = name;
		this.mapSystem = new HawkEye(globalMapWidth, globalMapHeight, localMapWidth, localMapHeight, globalOrigin, localOrigin, initialLocalOrientation, initialState);
		this.sensors = new JenovaSensorManager<R, E>();
		this.pidManager = new JenovaPIDLinkManager();
	}
	
	/**
	 * Constructor for creating a JenovaBot using a non-default sensorManager
	 * @param name 				Name of the JenovaBot
	 * @param sensorManager		ISensorManager to be utilized by the JenovaBot
	 * @param globalMapWidth	Width of the global map (see Hawkeye)
	 * @param globalMapHeight	Height of the global map (see Hawkeye)
	 * @param localMapWidth		Width of the local map (see Hawkeye)
	 * @param localMapHeight	Height of the local map (see Hawkeye)
	 * @param globalOrigin		Origin of the global map (see Hawkeye)
	 * @param localOrigin		Origin of the local map (see Hawkeye)
	 * @param initialLocalOrientation Initial orientation of the local map inside the global map (see Hawkeye)
	 * @param initialState		Initial state to fill the global map with (see Hawkeye)
	 */
	protected JenovaBot(String name, ISensorManager<R, E> sensorManager, int globalMapWidth, int globalMapHeight, int localMapWidth, int localMapHeight, CartCoord globalOrigin, CartCoord localOrigin, ObjectLocationData initialLocalOrientation, MapObject initialState){
		this.name = name;
		this.mapSystem = new HawkEye(globalMapWidth, globalMapHeight, localMapWidth, localMapHeight, globalOrigin, localOrigin, initialLocalOrientation, initialState);
		this.sensors = sensorManager;
		this.pidManager = new JenovaPIDLinkManager();
	}
	
	/**
	 * Constructor for creating a JenovaBot using a non-default sensorManager and a non-default mappingSystem.
	 * @param name				Name of the JenovaBot
	 * @param sensorManager		ISensorManager to be utilized by the JenovaBot.  The default SensorManager can be used by leaving the sensorManager field as null.
	 * @param mapSystem			IMapSystem to be utilized by the JenovaBot
	 * @param pidLinkManager	IPIDLinkManager to be utilized by the JenovaBot.  The default IPIDLinkManager can be used by leaving the pidLinkManager field as null.
	 */
	protected JenovaBot(String name, ISensorManager<R, E> sensorManager, IMapSystem mapSystem, IJenovaPIDLinkManager pidLinkManager){
		this.name = name;
		this.mapSystem = mapSystem;
		if(sensorManager!=null)this.sensors = sensorManager;
		else this.sensors = new JenovaSensorManager<R, E>();
		if(pidLinkManager!=null)this.pidManager = pidLinkManager;
		else this.pidManager = new JenovaPIDLinkManager();
	}
	
	/**
	 * @return The name of the JenovaBot
	 */
	public String getName(){
		return this.name;
	}
	
	/**
	 * @return The JenovaSensorManager for the JenovaBot
	 */
	public ISensorManager<R, E> getSensorManager(){
		return sensors;
	}
	
	/**
	 * @return The JenovaPIDLinkManager for the JenovaBot
	 */
	public IJenovaPIDLinkManager getPIDManager(){
		return this.pidManager;
	}
	
	/**
	 * Allows post-constructor replacement of the sensor manager. DOES NOT TRANSFER ANY INFORMATION
	 * @param sensorManager ISensorManager to replace the current ISensorManager with
	 */
	protected void setSensorManager(ISensorManager<R, E> sensorManager){
		this.sensors = sensorManager;
	}
	
	/**
	 * Allows post-constructor replacement of the map system. DOES NOT TRANSFER ANY INFORMATION
	 * @param mapSystem IMapSystem to replace the current IMapSystem with
	 */
	protected void setMapSystem(IMapSystem mapSystem){
		this.mapSystem = mapSystem;
	}
	
	/**
	 * Allows post-constructor replacement of the pidLinkManager. DOES NOT TRANSFER ANY INFORMATION
	 * @param pidLinkManager IJenovaPIDLinkManager to replace the current IJenovaPIDLinkManager with
	 */
	protected void setPIDLinkManager(IJenovaPIDLinkManager pidLinkManager){
		this.pidManager = pidLinkManager;
	}
}
