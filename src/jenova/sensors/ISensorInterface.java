package jenova.sensors;
/**
 * Interface for controlling how a sensor gets its raw values. This allows custom interfaces to be written independent of sensor
 * type, which allows a single sensor manager type to handle both analog and digital sensors, and also allows interface hardware to be
 * changed without the need to change high level code, only the object that implements this interface
 * @author Adam Panzica
 *
 */
public interface ISensorInterface <R>{
	public R getRawValue();
}
