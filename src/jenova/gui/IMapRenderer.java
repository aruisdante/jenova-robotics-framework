package jenova.gui;

import java.awt.Graphics;

import jenova.mappingsystem.MapObject;

/**
 * Interface for creating map renderers for displaying HawkEye maps in the JenovaGUI
 * @author Adam Panzica
 * @version 0.1
 */
public interface IMapRenderer {
	/**
	 * @param canvas The canvas for the renderer to operate on
	 */
	public void setCanvas(Graphics canvas);
	/**
	 * Renders the map to the canvas
	 * @param map Map to be rendered to the canvas
	 * @return Graphics object containing the updated map
	 */
	public Graphics renderMap(MapObject[][] map);
	
	/**
	 * @return A string containing information about the type of the renderer
	 */
	public String getRenderType();
	
	/**
	 * @return Version number of the renderer
	 */
	public double getVersion();
}
