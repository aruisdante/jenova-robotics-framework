package jenova.mappingsystem;


/**
 * Class for representing the HawkEye mapping system. Provides functionality for tracking a global map of arbitrary size as well as a
 * local frame which can be adjusted relative to the global frame. Data is entered into the local frame, and then copied back into the
 * global map with either a change in the orientation of the local frame, or with a specified synchronize command.
 * @author Adam Panzica
 * @version 0.1
 */
public class HawkEye implements IMapSystem{
	private static final double version = .1;
	
	private Frame globalMap;					//Frame for storing the global map data
	private Frame localMap;						//Frame for storing the local map data
	private ObjectLocationData localLocation;	//Location of the local frame relative to the global frame
	private CartCoord localOffset;				//Origin offset inside the local frame
	private CartCoord globalOffset;				//Origin offset inside the global frame
	
	/************************************************* CONSTRUCTORS **********************************************************/
	
	/**
	 * Constructor for creating a new HawkEye mapping system
	 * @param globalMapWidth The total width of the global map (x-axis)
	 * @param globalMapHeight The total height of the global map (y-axis)
	 * @param localMapWidth The total width of the local map (x-axis)
	 * @param localMapHeight The total height of the local map (y-axis)
	 * @param globalOrigin The location of the origin of the global map. For instance, to have a 200x200 map that goes from -100->100 in both axis, this should be (100,100)
	 * @param localOrigin The location of the origin of the local map. For instance, to have a 200x200 map that goes from -100->100 in both axis, this should be (100,100)
	 * @param initialLocalOrientation The initial position and rotation of the local map relative to the global map
	 * @param initialState Initial MapObject to fill all points in the global and local map with
	 */
	public HawkEye(int globalMapWidth, int globalMapHeight, int localMapWidth, int localMapHeight, CartCoord globalOrigin, CartCoord localOrigin, ObjectLocationData initialLocalOrientation, MapObject initialState){
		this.globalMap = new Frame(globalMapHeight, globalMapWidth, initialState);
		this.localOffset = localOrigin;
		this.globalOffset = globalOrigin;
		localLocation = new ObjectLocationData(new CartCoord(initialLocalOrientation.getX()+globalOrigin.getX(), initialLocalOrientation.getY()+globalOrigin.getY()), initialLocalOrientation.getOrientation());
		localMap = new Frame(localMapHeight, localMapWidth, globalMap, localLocation.getOrientation(), localLocation.getPosition());
	}
	
	/************************************************ LOCATION UPDATING METHODS ******************************************************/
	
	@Override
	public void updateLocation(ObjectLocationData newLocation){
		ObjectLocationData trueLocation = calcOffsetFromGlobal(newLocation);
		localMap.copyToBaseFrame();
		localMap = new Frame(localMap.getHeight(), localMap.getWidth(), globalMap, trueLocation.getOrientation(), trueLocation.getPosition());
	}
	
	@Override
	public void saveToGolbal(){
		localMap.copyToBaseFrame();
	}
	
	/************************************************ OFFSET CALCULATION METHODS *****************************************************/
	
	/**
	 * Used to calculate a true location (positive only) from an origin relative location in the base frame
	 * @param location origin relative location in the base frame
	 * @return true location in the base frame
	 */
	private ObjectLocationData calcOffsetFromGlobal(ObjectLocationData location){
		return new ObjectLocationData(new CartCoord(location.getX()+globalOffset.getX(), location.getY()+globalOffset.getY()), location.getOrientation());
	}
	
	/**
	 * Used to calculate a true point (positive only) from an origin relative point in the local frame
	 * @param point origin relative point in the local frame
	 * @return true point in the local frame
	 */
	private CartCoord calcOffsetFromLocal(IMapPoint point){
		return new CartCoord(point.getX()+localOffset.getX(), point.getY()+localOffset.getY());
	}
	
	/************************************************ SHAPE CASTING METHODS **********************************************************/
	
	@Override
	public void castPoint(IMapPoint point, MapObject data){
		localMap.castPoint(calcOffsetFromLocal(point), data);
	}
	
	@Override
	public void castLine(IMapPoint startPoint, MapObject startPointObject, IMapPoint endPoint, MapObject endPointObject, MapObject lineObject){
		localMap.castLine(calcOffsetFromLocal(startPoint), startPointObject, calcOffsetFromLocal(endPoint), endPointObject, lineObject);
	}
	
	
	@Override
	public void castRect(IMapPoint topLeft, IMapPoint bottomRight, MapObject data, boolean fill){
		localMap.castRec(calcOffsetFromLocal(topLeft), calcOffsetFromLocal(bottomRight), data, fill);
	}
	
	@Override
	public void castCiricle(IMapPoint center, int radius, int degrees, MapObject data, boolean fill){
		localMap.castCircle(calcOffsetFromLocal(center), radius, degrees, data, fill);
	}
	
	/******************************************************* SMOOTHING METHODS *******************************************************/
	
	@Override
	public void smooth(ISmoother smoothFunction){
		this.localMap.setPoints(smoothFunction.smooth(this.localMap.getPoints()));
	}
	
	
	/*********************************************************** MISC METHODS ********************************************************/
	public String toString(){
		return "GLOBAL MAP: "+globalMap.getWidth()+"x"+globalMap.getHeight()+"\nLOCAL MAP: "+localMap.getWidth()+"x"+localMap.getHeight()+"\nLOCAL ORIENTATION: "+localLocation+"\n"+globalMap.toString();
	}
	
	@Override
	public int getLocalMapWidth(){
		return this.localMap.getWidth();
	}
	
	@Override
	public int getLocalMapHeight(){
		return this.localMap.getWidth();
	}
	
	@Override
	public int getGlobalMapWidth(){
		return this.globalMap.getWidth();
	}
	
	@Override
	public int getGlobalMapHeight(){
		return this.globalMap.getWidth();
	}
	
	@Override
	public Frame getLocalMap() {
		return this.localMap;
	}
	
	@Override
	public Frame getGlobalMap() {
		return this.globalMap;
	}
	
	public double getVersion(){
		return version;
	}
}
