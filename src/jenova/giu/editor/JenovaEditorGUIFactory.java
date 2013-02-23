package jenova.giu.editor;

import javax.swing.JPanel;

import jenova.sensors.ISensor;

/**
 * Factory class for spawning JenovaEditorGUI's. Add construction information for new editor types here.
 * @author Adam Panzica
 *
 */
public class JenovaEditorGUIFactory {
	public static JPanel spawnEditor(Object input){
		//Handles an input of type ISensor. NOTE: This factory assumes that ISensor has type ISensor<Integer, Double>. Change type cases as needed if this is no longer the case
		if(input instanceof ISensor){
			JenovaSensorEditorController<Integer,Double> makeController = new JenovaSensorEditorController<Integer, Double>();
			@SuppressWarnings("unchecked")
			JenovaSensorEditor<Integer,Double> makeEditor = new JenovaSensorEditor<Integer,Double>((ISensor<Integer,Double>)input, makeController);
			makeController.setView(makeEditor);
			return makeEditor;
		}
		return null;
	}
}
