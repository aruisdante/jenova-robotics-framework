package jenova.mappingsystem;

/**
 * Class for representing object location data relative to a base frame with three parameters (X/Y or R/Theta and Orientation to base frame)
 * @author Adam Paznica
 *
 */
public class ObjectLocationData implements IMapPoint {
	
	private IMapPoint position;
	private double orientation;
	
	/**
	 * Constructor for creating an ObjectLocationData object. Object location data contains information on both the position and 
	 * orientation of an object relative to some base frame
	 * @param position Position of the object relative to a base frame
	 * @param orientation Orientation of the object relative to a base frame
	 */
	public ObjectLocationData(IMapPoint position, double orientation){
		this.position=position;
		this.orientation=orientation;
	}
	
	/**
	 * Updates the position and orientation of the object
	 * @param position new position of the object
	 * @param orientation new orientation of the object
	 */
	public void updateLocation(CartCoord position, double orientation){
		this.position=position;
		this.orientation=orientation;
	}

	/**
	 * @return The orientation (angle) between the object and its base frame
	 */
	public double getOrientation(){
		return this.orientation;
	}
	
	public IMapPoint getPosition(){
		return this.position;
	}
	
	@Override
	public double getRange() {
		return position.getRange();
	}

	@Override
	public double getTheta() {
		return position.getTheta();
	}

	@Override
	public int getThetaD() {
		return position.getThetaD();
	}

	@Override
	public int getX() {
		return position.getX();
	}

	@Override
	public int getY() {
		return position.getY();
	}
	
	public String toString(){
		return "("+this.position.getX()+","+this.position.getY()+"|"+this.orientation+")";
	}

}
