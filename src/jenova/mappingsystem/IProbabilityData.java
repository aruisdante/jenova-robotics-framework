package jenova.mappingsystem;

/**
 * Interface for accessing probability data
 * @author Adam Panzica
 *
 */
public interface IProbabilityData extends Cloneable{
	/**
	 * @return A string containing the type of probability data (I.E. Line, LIDAR)
	 */
	public String getType();
	/**
	 * @return returns the probability of the existence of the data
	 */
	public double getProb();
	
	/**
	 * Updates the data probability
	 * @param delta amount to change probability by
	 * @return new probability value
	 */
	public double updateProb(double delta);
	
	/**
	 * @return True if the probability data has been modified since the last time updated was checked, else false. Useful for smoothing
	 */
	public boolean updated();
	
	/**
	 * Creates a copy of the object implementing the IProbabiltyData interface
	 * @return A deep copy of the IProbabiltyData
	 */
	public IProbabilityData clone();
}
