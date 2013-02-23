package jenova.giu.editor;

import java.awt.Container;

import javax.swing.JDialog;
import javax.swing.JFrame;

import jenova.sensors.ISensor;

/**
 * Controller for the JenovaSensorEditor view.
 * 
 * @author Adam Panzica
 *
 * @param <R> Raw value of the ISensor being edited
 * @param <E> Engineering value of the ISensor being edited
 */
public class JenovaSensorEditorController<R,E> {
	private JenovaSensorEditor<R,E> view;

	public JenovaSensorEditorController(){

	}
	
	/**
	 * @param view Assigns the SensorEditor view to be controlled by the controller
	 */
	public void setView(JenovaSensorEditor<R,E> view){
		this.view = view;
	}
	
	/**
	 * Handles the pressing of the save button in the SensorEditor. Saves the modified fields in the editor to the sensor object
	 * @param input ISensor being edited by the editor
	 */
	public void fireSaveButton(ISensor<R,E> input) {
		input.setName(this.view.getNameField().getText());
		input.setPort(Integer.parseInt(this.view.getPortField().getText()));
		input.setEngUnit(this.view.getUnitField().getText());
		this.view.getSaveButton().setEnabled(false);
	}

	/**
	 * Handles the pressing of the cancel button. Closes the dialog window without saving changes
	 */
	public void fireCancelButton() {
		Container parent = this.view.getParent();
		while((!(parent instanceof JDialog))&&(!(parent instanceof JFrame))){
			parent= parent.getParent();
			if(parent == null) break;
		}
		if(parent instanceof JDialog)((JDialog)parent).dispose();
		else if (parent instanceof JFrame)((JFrame)parent).dispose();
	}
	
	/**
	 * Checks to see if there is a change in text in the editor fields to enable the save button.
	 * @param input ISensor being edited by the editor
	 */
	public void fireTextEntry(ISensor<R,E> input){
		boolean test1 = !(input.getName().equals(view.getNameField().getText()));
		boolean test2 = input.getPort()!=Integer.parseInt(view.getPortField().getText());
		boolean test3 = !(input.getEngUnit().equals(view.getUnitField().getText()));
		if(test1||test2||test3){System.out.println("Text does not match"); view.getSaveButton().setEnabled(true);}
	}
}
