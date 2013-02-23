package jenova.mappingsystem;
/**
 * Class representing a Cartesian coordinate
 * @author Adam Panzica
 *
 */
public class CartCoord implements IMapPoint {
	private int xVal;
	private int yVal;
	
	/**
	 * Constructor for creating a CartCoord from x and y values
	 * @param xVal the X value of the Cartesian coordinate
	 * @param yYal the Y value of the Cartesian coordinate
	 */
	public CartCoord(int xVal, int yYal){
		this.xVal = xVal;
		this.yVal = yYal;
	}
	
	/**
	 * Constructor for creating a CartCoord from a PolarCoord
	 * @param coord PolarCoord to be converted into a CartCoord
	 */
	CartCoord(PolarCoord coord){
		this.xVal = coord.getX();
		this.yVal = coord.getY();
	}
	@Override
	public double getRange() {
		return Math.sqrt(Math.pow(xVal, 2)+Math.pow(yVal, 2));
	}

	@Override
	public double getTheta() {
		return Math.atan2(yVal, xVal);
	}

	@Override
	public int getX() {
		return this.xVal;
	}

	@Override
	public int getY() {
		return this.yVal;
	}
	
	public String toString(){
		return "("+this.xVal+","+this.yVal+")";
	}

	@Override
	public int getThetaD() {
		return (int)Math.round((getTheta()*(double)180/Math.PI));
	}
}
