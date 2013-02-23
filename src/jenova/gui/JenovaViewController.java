package jenova.gui;

import java.awt.Graphics;

import javax.swing.JPanel;
import javax.swing.JTable;

import jenova.bot.JenovaBot;
import jenova.console.IJenovaConsoleDisplay;
import jenova.console.JenovaConsole;
import jenova.giu.editor.JenovaEditorGUIFactory;
import jenova.mappingsystem.Frame;
import jenova.sensors.ISensor;

/**
 * Class for controlling the interface with the JenovaView GUI system
 * @author Adam Panzica
 * @version 0.1
 * @param <R> Value type for the raw values in the sensor manager contained in the JenovaBot
 * @param <E> Value type for the engineering values in the sensor manager contained in the JenovaBot
 */
public class JenovaViewController <R, E> implements IJenovaConsoleDisplay{
	private JenovaView<R, E> view;
	private JenovaBot<R, E> bot;
	private IMapRenderer renderer;
	private int NAMECOLMN, TYPECOLMN, PORTCOLUMN, RVALCOLUMN, EVALCOLUMN, EUCOLUMN;
	private int[] initialColmns = new int[]{0,1,2,3,4,5};
	//public static enum columnName{NAMECOLMN,TYPECOLMN,PORTCOLUMN,RVALCOLUMN,EVALCOLUMN,EUCOLUMN};

	public JenovaViewController(JenovaView<R, E> view, JenovaBot<R, E> bot, IMapRenderer renderer){
		this.view = view;
		this.bot = bot;
		this.renderer = renderer;
		this.NAMECOLMN = this.initialColmns[0];
		this.TYPECOLMN = this.initialColmns[1];
		this.PORTCOLUMN = this.initialColmns[2];
		this.RVALCOLUMN = this.initialColmns[3];
		this.EVALCOLUMN = this.initialColmns[4];
		this.EUCOLUMN = this.initialColmns[5];
	}

	/********************************** INITIALIZATION CODE *************************************************/

	/**
	 * Initializes the view/view controller. This function is run to activate a new instance of a JenovaView
	 */
	public void initialize(){
		this.view.setVisible(true);
		setName(this.bot.getName());
	}

	/**
	 * Sets the name of the JENOVA bot in the view
	 * @param name name of the JENOVA bot
	 */
	private void setName(String name){
		this.view.setNameField(name);
	}

	/**
	 * Put all code for updating the window here.
	 */
	public void updateView(){
		this.setSensors();
	}

	/********************************** JENOVA CONSOLE CODE *************************************************/
	/**
	 * Handles the clicking of the Print Status Log button
	 */
	public void firePrintStatusLogButton() {
		JenovaConsole.printStatusLog();
	}

	/**
	 * Handles the clicking of the Print Error Log button
	 */
	public void firePrintErrorLogButton() {
		JenovaConsole.printErrorLog();
	}

	/**
	 * Prints an error message to the Jenova Console text area
	 * @param message message to be printed
	 */
	public void printConsoleErrorMessage(String message) {
		this.view.addConsoleText(message, JenovaView.consoleMessageType.ERROR);
	}

	/**
	 * Prints a status message to the Jenova Console text area
	 * @param message message to be printed
	 */
	public void printConsoleStatusMessage(String message) {
		this.view.addConsoleText(message, JenovaView.consoleMessageType.STATUS);
	}

	/**
	 * Prints generic message to the Jenova Console text area
	 * @param message message to be printed
	 */
	public void printConsoleMessage(String message) {
		this.view.addConsoleText(message, JenovaView.consoleMessageType.CONSOLE);
	}

	/********************************** SENSOR STATUS TABLE CODE *************************************************/

	/**
	 * Updates the sensor table with the current status of all sensors in the sensor manager
	 */
	public void setSensors(){
		JTable sensorTable = this.view.getSensorTable();
		int row;
		int endRow = this.bot.getSensorManager().getNumberOfSensors();
		ISensor<R, E> currSensor;

		System.out.println("Printing Sensors\n"+this.bot.getSensorManager());

		for(row =0; row<endRow; row++){
			currSensor = this.bot.getSensorManager().getSensor(row);
			System.out.println(currSensor);
			sensorTable.setValueAt(currSensor.getName(), row, NAMECOLMN);
			sensorTable.setValueAt(currSensor.getSensorType(), row, TYPECOLMN);
			sensorTable.setValueAt(currSensor.getID(), row, PORTCOLUMN);
			sensorTable.setValueAt(currSensor.getRawValue(), row, RVALCOLUMN);
			sensorTable.setValueAt(currSensor.getEngValue(), row, EVALCOLUMN);
			sensorTable.setValueAt(currSensor.getEngUnit(), row, EUCOLUMN);
		}
	}
	
	/**
	 * Handles double clicking on the sensor table to open a JenovaSensorEditor dialog
	 * @param row Row in the table being clicked on
	 */
	public void fireSensorTableDoubleClick(int row){
		ISensor<R, E> sensor = this.bot.getSensorManager().getSensor(row);
		if(sensor != null){
			JPanel editorPanel = JenovaEditorGUIFactory.spawnEditor(sensor);
			JenovaPopup editorPopup = new JenovaPopup(view, true);
			editorPopup.setContentPane(editorPanel);
			editorPopup.setVisible(true);
		}
	}

	/********************************** HAWKEYE LOCLAL MAP CODE *************************************************/
	/**
	 * Redraws the local map panel
	 * @param localMap new local map to be drawn
	 */
	public void drawHawkEyeLocalMap(Frame localMap){
		JPanel displayPanel = this.view.getHawkEyeLocalMapPanel();
		Graphics canvas = displayPanel.getGraphics();
		this.renderer.setCanvas(canvas);
		displayPanel.paintComponents(this.renderer.renderMap(localMap.getPoints()));
		displayPanel.repaint();
	}
}
