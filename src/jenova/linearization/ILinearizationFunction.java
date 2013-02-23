package jenova.linearization;

/**
 * Interface for creating linearization functions for sensors
 * @author Adam Panzica
 *
 * @param <R> Type of the raw value of the sensor (I.E. Integer)
 * @param <E> Type of the engineering unit value of the sensor (I.E. Double)
 */
public interface ILinearizationFunction<R, E> {
	/**
	 * returns the linearized value of the sensor, in engineering units
	 * @param raw raw value of the sensor
	 * @return linearized value of the sensor, in engineering units
	 */
	public E linearize(R raw);
}
