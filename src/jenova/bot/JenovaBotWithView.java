package jenova.bot;

import jenova.console.JenovaConsole;
import jenova.gui.JenovaView;
import jenova.gui.JenovaViewController;
import jenova.gui.PrometheusRenderer;
import jenova.mappingsystem.CartCoord;
import jenova.mappingsystem.IMapSystem;
import jenova.mappingsystem.MapObject;
import jenova.mappingsystem.ObjectLocationData;
import jenova.pid.IJenovaPIDLinkManager;
import jenova.sensors.ISensorManager;

/**
 * Abstract class for representing robots using the Jenova framework. Includes GUI facilities
 * @author Adam Panzica
 *
 * @param <R> Value type for the raw values in the sensor manager
 * @param <E> Value type for the engineering values in the sensor manager
 */
public abstract class JenovaBotWithView<R, E> extends JenovaBot<R, E>{
	protected JenovaView<R, E> view;					//View, aka GUI, for the JenovaBot
	protected JenovaViewController<R, E> controller; 	//View controller for interacting with the GUI
	
	
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
	protected JenovaBotWithView(String name, int globalMapWidth, int globalMapHeight, int localMapWidth, int localMapHeight, CartCoord globalOrigin, CartCoord localOrigin, ObjectLocationData initialLocalOrientation, MapObject initialState){
		super(name, globalMapWidth, globalMapHeight, localMapWidth, localMapHeight, globalOrigin, localOrigin, initialLocalOrientation, initialState);
		init();
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
	protected JenovaBotWithView(String name, ISensorManager<R, E> sensorManager, int globalMapWidth, int globalMapHeight, int localMapWidth, int localMapHeight, CartCoord globalOrigin, CartCoord localOrigin, ObjectLocationData initialLocalOrientation, MapObject initialState){
		super(name, sensorManager, globalMapWidth, globalMapHeight, localMapWidth, localMapHeight, globalOrigin, localOrigin, initialLocalOrientation, initialState);
		init();
	}
	
	/**
	 * Constructor for creating a JenovaBot using a non-default sensorManager and a non-default mappingSystem. Note that the default SensorManager can be used by leaving the sensorManager field as null.
	 * @param name				Name of the JenovaBot
	 * @param sensorManager		ISensorManager to be utilized by the JenovaBot
	 * @param mapSystem			IMapSystem to be utilized by the JenovaBot
	 * @param pidLinkManager	IPIDLinkManager to be utilized by the JenovaBot.  The default IPIDLinkManager can be used by leaving the pidLinkManager field as null.
	 */
	protected JenovaBotWithView(String name, ISensorManager<R, E> sensorManager, IMapSystem mapSystem, IJenovaPIDLinkManager pidLinkManager){
		super(name, sensorManager, mapSystem, pidLinkManager);
		init();
	}
	
	/**
	 * Initialization function for creating the view
	 */
	private void init(){
		this.view = new JenovaView<R, E>();
		this.controller = new JenovaViewController<R, E>(this.view, this, new PrometheusRenderer(this.view.getHawkEyeLocalMapPanel(), mapSystem.getLocalMapHeight(), mapSystem.getLocalMapWidth()));
		this.view.setController(controller);
		JenovaConsole.SetConsoleDisplay(this.controller);
	}
	
	/**
	 * @return The JenovaView for the JenovaBot
	 */
	public JenovaView<R, E> getView(){
		return this.view;
	}
	
	/**
	 * @return The JenovaViewController for the JenovaBot
	 */
	public JenovaViewController<R, E> getController(){
		return this.controller;
	}
}
