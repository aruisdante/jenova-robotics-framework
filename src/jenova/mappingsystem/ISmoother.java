package jenova.mappingsystem;
/**
 * Interface for creating map smoothing processes
 * @author Adam Panzica
 *
 */
public interface ISmoother {
	/**
	 * Processes map data and performs smoothing upon it
	 * @param map map to perform smoothing on
	 * @return new smoothed map
	 */
	public MapObject[][] smooth (MapObject[][] map);
}
