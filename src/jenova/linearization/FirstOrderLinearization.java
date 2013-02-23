package jenova.linearization;

/**
 * Class for representing a first order linearization function (y=mx+b)
 * @author Adam Panzica
 *
 */
public class FirstOrderLinearization implements ILinearizationFunction<Integer, Double> {
	private Double slope;
	private Double intercept;
	
	/**
	 * Constructor for creating a first order linearization function (y=mx+b)
	 * @param minR The minimum raw value of the sensor
	 * @param maxR The maximum raw value of the sensor
	 * @param minE The minimum engineering unit value of the sensor
	 * @param maxE The maximum engineering unit value of the sensor
	 */
	public FirstOrderLinearization(double minR, double maxR, double minE, double maxE){
		this.slope = (maxE-minE)/(maxR-minR);
		this.intercept = maxE-this.slope*maxR;
	}

	@Override
	public Double linearize(Integer raw) {
		return this.slope*(double)raw+this.intercept;
	}
	
	public String toString(){
		return "Engineering = "+this.slope+"*Raw+"+this.intercept;
	}
}
