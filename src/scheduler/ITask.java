package scheduler;

import jenova.console.JenovaError;

/**
 * Interface for creating scheduler tasks
 * @author Adam Panzica
 * @version 0.1
 */
public interface ITask {
	/**
	 * This method contains all code for executing the task represented by this interface
	 * @throws JenovaError if the task does not successfully complete
	 */
	public void exicuteTask() throws JenovaError;
}
