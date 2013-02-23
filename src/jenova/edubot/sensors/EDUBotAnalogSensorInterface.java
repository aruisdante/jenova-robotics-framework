package jenova.edubot.sensors;

import jenova.console.JenovaConsole;
import jenova.console.JenovaError;
import jenova.edubot.device.manager.JenovaDeviceManager;
import jenova.hardwareabstraction.JenovaSimpleHardwareAbstractionLayer;
import jenova.sensors.ISensorInterface;

/**
 * Class for creating Analog Sensors for the JenovaEDUBot. Automatically handles the interface with the JenovaDeviceManager
 * for retrieving analog values
 * @author Adam Panzica
 * @version 0.1
 */
public class EDUBotAnalogSensorInterface extends JenovaSimpleHardwareAbstractionLayer<String, Integer> implements ISensorInterface<Integer> {
	@SuppressWarnings("unused")
	private JenovaDeviceManager deviceManager;
	@SuppressWarnings("unused")
	private int port;
	
	EDUBotAnalogSensorInterface(JenovaDeviceManager deviceManager, int port){
		super(1);
		this.deviceManager = deviceManager;
		this.port = port;
	}
	@Override
	public Integer getRawValue() {
		try {
			return this.getNextMessageFormHardware();
		} catch (JenovaError e) {
			JenovaConsole.errorMessage(e);
			return null;
		}
	}
	
	@Override
	public Integer getNextMessageFormHardware() throws JenovaError {
		return 1;
	}
	@Override
	protected void sendMessageToHardware(String message) throws JenovaError {
		// TODO Auto-generated method stub
		
	}
}
