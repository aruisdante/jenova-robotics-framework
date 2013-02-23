package jenova.mappingsystem;
/**
 * Interface for accessing a map point in their polar or Cartesian coordinates
 * @author Adam Panzica
 *
 */
public interface IMapPoint {
	/**
	 * @return The X value of the IMapPoint in Cartesian coordinates
	 */
	int getX();
	/**
	 * @return The Y value of the IMapPoint in Cartesian coordinates
	 */
	int getY();
	/**
	 * @return The angle to the IMapPoint in polar coordinates
	 */
	double getTheta();
	
	/**
	 * @return The angle to the IMapPoint in degrees
	 */
	int getThetaD();
	/**
	 * @return The range to the IMapPoint in polar coordinates
	 */
	double getRange();
}
