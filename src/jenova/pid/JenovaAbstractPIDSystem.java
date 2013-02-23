package jenova.pid;

import jenova.console.JenovaConsole;
import jenova.console.JenovaError;
import jenova.hardwareabstraction.JenovaSimpleHardwareAbstractionLayer;

public abstract class JenovaAbstractPIDSystem<S, C, M> implements IJenovaPIDSystemLink<S, C> {
	private JenovaSimpleHardwareAbstractionLayer<M, S> system;
	
	protected JenovaAbstractPIDSystem(JenovaSimpleHardwareAbstractionLayer<M, S> system){
		this.system = system;
	}

	@Override
	public S getCurrentValue() {
		try {
			return this.system.getNextMessageFormHardware();
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
			return null;
		}
	}
	
	public void appliyControl(C controlValue){
		M message;
		message = convertControlToMessage(controlValue);
		try {
			this.system.enqueueMessageToHardware(message);
			this.system.sendNextMessageToHardware();
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
		}
	}
	
	protected abstract M convertControlToMessage(C controlValue);
}
