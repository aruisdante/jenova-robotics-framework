package jenova.giu.editor;

import javax.swing.JPanel;

/**
 * Abstract class defining a GUI editor that takes some kind of input and returns some kind of output
 * @author Adam Panzica
 *
 * @param <I> Input to the editor
 * @param <C> Controller for the editor
 */
public abstract class JenovaEditorGUI <I, C> extends JPanel{
	private static final long serialVersionUID = 1L;
	
	/**
	 * Input value to be operated on by the EditorGUI
	 */
	protected I input;
	/**
	 * View controller to define the behavior of the EditorGUI
	 */
	protected C controller;

	/**
	 * Constructor for a JenovaEditorGUI. Should only be called by subclasses
	 * @param input Input object to the editor
	 * @param controller View controller for the editor
	 */
	protected JenovaEditorGUI(I input, C controller){
		this.input = input;
		this.controller = controller;
	}
	
	/**
	 * @return a reference to this editor
	 */
	public JenovaEditorGUI<I, C> getEditor(){
		return this;
	}
}
