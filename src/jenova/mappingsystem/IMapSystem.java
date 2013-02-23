package jenova.mappingsystem;


/**
 * Interface for defining a mapping system to be used by the Jenova Framework. Defines the basic functionality that any mapping system should provide.
 * @author Adam Panzica
 *
 */
public interface IMapSystem {
	
	/**
	 * Updates the location of the local frame relative to the base frame.
	 * Saves data from the current local frame into the base frame before updating local frame with new location
	 * @param newLocation ObjectLocationData containing the new position/orientation of the local frame relative to the base frame origin
	 */
	public void updateLocation(ObjectLocationData newLocation);
	
	/**
	 * Saves the data in the local map into the global map
	 */
	public void saveToGolbal();
	
	/**
	 * Casts a point on the local map
	 * @param point location on the local map, relative to the local origin
	 * @param data MapObject data to place at the given point
	 */
	public void castPoint(IMapPoint point, MapObject data);
	
	/**
	 * Casts a line on the local map
	 * @param startPoint Starting point of the line, relative to the local origin
	 * @param startPointObject MapObject data to place at the start point of the line
	 * @param endPoint Ending point of the line, relative to the local origin
	 * @param endPointObject MapObject data to place at the end point of the line
	 * @param lineObject MapObject data to fill at each point along the line
	 */
	public void castLine(IMapPoint startPoint, MapObject startPointObject, IMapPoint endPoint, MapObject endPointObject, MapObject lineObject);
	
	/**
	 * Cast a rectangle on the local map
	 * @param topLeft Top left point of the rectangle relative to the local origin
	 * @param bottomRight Bottom right point of the rectangle relative to the local origin
	 * @param data MapObject data to fill the rectangle with
	 * @param fill True for a solid rectangle, false for edges only
	 */
	public void castRect(IMapPoint topLeft, IMapPoint bottomRight, MapObject data, boolean fill);
	
	/**
	 * Draws a circle (or fraction there of) in the frame from a center point. Follows the following rotation guide:
	 * Positive radius, positive angle: counter clockwise from positive x axis
	 * Positive radius, negative angle: clockwise from positive x axis
	 * Negative radius, positive angle: clockwise from negative x axis
	 * Negative radius, negative angle: counter clockwise from negative x axis
	 * @param center The coordinates of the center of the circle relative to the local frame
	 * @param radius The radius of the circle 
	 * @param degrees The arc of the circle (E.X. 90 for quarter circle, 180 for half, 360 for full)
	 * @param data MapObject to fill the circle with
	 * @param fill True for a solid circle, false for an edge only circle
	 */
	public void castCiricle(IMapPoint center, int radius, int degrees, MapObject data, boolean fill);
	
	/**
	 * Performs a smoothing operation on the points in the local map
	 * @param smoothFunction ISmoother defining the smoothing operation to perform upon the local map
	 */
	public void smooth(ISmoother smoothFunction);
	
	/**
	 * @return The width of the local map
	 */
	public int getLocalMapWidth();
	
	/**
	 * @return The height of the local map
	 */
	public int getLocalMapHeight();
	
	/**
	 * @return The local map Frame
	 */
	public Frame getLocalMap();
	
	/**
	 * @return The width of the global map
	 */
	public int getGlobalMapWidth();
	
	/**
	 * @return The height of the global map
	 */
	public int getGlobalMapHeight();
	
	/**
	 * @return The Global map Frame
	 */
	public Frame getGlobalMap();
	
	

}
