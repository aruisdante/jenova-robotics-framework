package jenova.gui;

import java.awt.Color;
import java.awt.Graphics;

import javax.swing.JPanel;

import jenova.console.JenovaConsole;
import jenova.console.JenovaStatus;
import jenova.mappingsystem.MapObject;

/**
 * Renderer for displaying map data using the Prometheus probability map interpretation
 * @author Adam Panzica
 * @version 0.1
 */
public class PrometheusRenderer implements IMapRenderer {

	private static final double version = 0.1;
	private Graphics canvas;
	private double xScale;
	private double yScale;
	
	public PrometheusRenderer(JPanel panel, int mapHeight, int mapWidth){
		this.xScale = ((double)panel.getWidth()-10)/(double)mapWidth;
		JenovaConsole.statusMessage(new JenovaStatus("Render xScale: "+Double.toString(this.xScale)));
		this.yScale = ((double)panel.getHeight()-10)/(double)mapHeight;
		JenovaConsole.statusMessage(new JenovaStatus("Render yScale: "+Double.toString(this.yScale)));
	}
	
	@Override
	public Graphics renderMap(MapObject[][] map) {
		int newWidth = (int)Math.round(xScale);
		int newHeight = (int)Math.round(yScale);
		for(int y=0; y<map.length; y++){
			//JenovaConsole.statusMessage(new JenovaStatus("Calculating new Y value"));
			int newY = (int)Math.round(y*this.yScale)+5;
			
			for(int x=0; x<map[y].length; x++){
				//JenovaConsole.statusMessage(new JenovaStatus("Calculating new X value"));
				int newX = (int)Math.round(x*this.xScale)+5;
				
				//JenovaConsole.statusMessage(new JenovaStatus(new CartCoord(newX, newY).toString()));
				
				if(map[y][x].toString()=="1"){
					this.canvas.setColor(Color.red);
					this.canvas.fillRect(newX, newY, newWidth, newHeight);
					/*JenovaConsole.statusMessage(new JenovaStatus(
							"Rendering Point: ("+Integer.toString(newX)+","+Integer.toString(newX)+"), "
							+Integer.toString(newWidth)+"x"+Integer.toString(newHeight)+" "+"Red"));*/
				}
				else{
					this.canvas.setColor(Color.green);
					this.canvas.fillRect(newX, newY, newWidth, newHeight);
					/*JenovaConsole.statusMessage(new JenovaStatus(
							"Rendering Point: ("+Integer.toString(newX)+","+Integer.toString(newY)+"), "
							+Integer.toString(newWidth)+"x"+Integer.toString(newHeight)+" "+"Green"));*/
				}
			}
		}
		JenovaConsole.statusMessage(new JenovaStatus("Map Render Complete"));
		return this.canvas;
	}

	@Override
	public void setCanvas(Graphics canvas) {
		this.canvas = canvas;
	}

	@Override
	public String getRenderType() {
		return "Prometheus";
	}

	@Override
	public double getVersion() {
		// TODO Auto-generated method stub
		return version;
	}

}
