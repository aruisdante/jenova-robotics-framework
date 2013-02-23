package jenova.mappingsystem;

public class PolarCoord implements IMapPoint {
	private double theta;
	private double range;
	
	/**
	 * Constructor for creating a polar coordinate from an angle and range to point
	 * @param theta angle to point, in radians
	 * @param range range to point
	 */
	PolarCoord(double theta, double range){
		this.theta = theta;
		this.range = range;
	}
	
	/**
	 * Constructor for creating a polar coordinate from a Cartesian coordinate
	 * @param coord Cartesian coordinate to convert into a polar coodinate
	 */
	PolarCoord(CartCoord coord){
		this.theta= coord.getTheta();
		this.range= coord.getRange();
	}
	
	/**
	 * Constructor for creating a polar coordinate from an angle and range to a point
	 * @param theta angle to point, in degrees
	 * @param range range to point
	 */
	PolarCoord(int theta, int range){
		this.theta = (double)theta*Math.PI/(double)180;
		this.range = range;
	}
	
	@Override
	public double getRange() {
		return this.range;
	}

	@Override
	public double getTheta() {
		return this.theta;
	}

	@Override
	public int getX() {
		return (int)(Math.cos(this.getTheta())*this.getRange());
	}

	@Override
	public int getY() {
		return (int)(Math.sin(this.getTheta())*this.getRange());
	}
	
	public String toString(){
		return "("+this.theta+","+this.range+")";
	}

	@Override
	public int getThetaD() {
		return (int)Math.round(this.theta*(double)180/Math.PI);
	}

}
