package jenova.linearization;

/**
 * 
 * @author Adam Panzica
 *
 */
public class LinearizationFactory {
	/**
	 * Enum representing the various types of linearization functions producible by the ILinearization Factory
	 *
	 */
	public static enum LinearizationType{
		FIRST_ORDER ("First Order", 2);
		
		private final String typeName;
		private final int numberOfPoints;
		LinearizationType(String typeName, int numberOfPoints){
			this.typeName = typeName;
			this.numberOfPoints = numberOfPoints;
		}
		
		public String toString(){
			return this.typeName;
		}
		
		/**
		 * @return The number of data points (raw and engineering) needed to produce the linearization function
		 */
		public int numberOfPoints(){
			return this.numberOfPoints;
		}
	};
	
	/**
	 * 
	 * @param rawPoints
	 * @param engPoints
	 * @return
	 */
	public static ILinearizationFunction<Integer, Double> spawnLinearization(int rawPoints[], double engPoints[]){
		if (rawPoints.length == LinearizationType.FIRST_ORDER.numberOfPoints()){
			return new FirstOrderLinearization(rawPoints[0],rawPoints[1],engPoints[0],engPoints[1]);
		}else return null;		
	}
}
