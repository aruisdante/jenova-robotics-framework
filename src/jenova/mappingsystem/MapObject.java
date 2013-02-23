package jenova.mappingsystem;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Class for representing an object of data on the map. It is intended to be used with probability-based data mapping
 * where each point on a map contains a probability list of some type of physical property existing at that location
 * It is not intended to be a persistent object: for each refresh of the map, the MapObject should be filled with probability
 * data, and then this data should be red, interpreted, then the MapObject should be replaced by a new MapObject's worth of data using the
 * replace() method at the next map refresh.
 * @author Adam Panzica
 *
 */
public class MapObject{
	/**
	 * String representation of the state of the MapObject. In its simplest form, can be used to create a simple text based map from an array of map objects
	 */
	private String state;
	/**
	 * List of data about the probability of some physical property existing at the location represented by this map object
	 */
	private ArrayList<IProbabilityData> probabilityDataList;
	
	/**
	 * Creates a map object with a pre-defined state and an empty list of probability data.
	 * @param state State of the MapObject
	 */
	public MapObject(String state){
		this.state=state;
		this.probabilityDataList = new ArrayList<IProbabilityData>();
	}
	
	/**
	 * Creates a MapObject with a pre-defined list of probability data
	 * @param probabilityDataList List of probability data to initialize the MapObject with
	 */
	public MapObject(ArrayList<IProbabilityData> probabilityDataList){
		this.state = "";
		this.probabilityDataList = probabilityDataList;
	}
	
	/**
	 * Creates a MapObject that is a copy of another MapObject (NOT a reference to the other object, a new object with a state copy)
	 * @param toCopy The MapObject to be copied into the new MapObject
	 */
	public MapObject(MapObject toCopy){
		this.probabilityDataList = new ArrayList<IProbabilityData>();
		replace(toCopy);
	}
	
	/**
	 * @return The state of the MapObject
	 */
	private String getState() {
		return this.state;
	}
	
	/**
	 * Replaces the state and IProbabiltyData of this MapObject with a copy (NOT A REFERENCE) of the state and IProbabiltyData of the passed MapObject
	 * @param replaceWith MapObject to replace this MapObject's data with
	 */
	public void replace(MapObject replaceWith){
		this.state = replaceWith.getState();
		for(Iterator<IProbabilityData> copier = replaceWith.getProbData().iterator(); copier.hasNext();){
			this.probabilityDataList.add( copier.next().clone());
		}
	}
	
	/**
	 * @return The list of IProbabiltyData for the MapObject
	 */
	public ArrayList<IProbabilityData> getProbData() {
		return this.probabilityDataList;
	}
	
	/**
	 * Adds IProbabiltyData to the MapObject
	 * @param data New IProbabiltyData to add to the MapObject
	 */
	public void addProbData(IProbabilityData data){
		this.probabilityDataList.add(data);
	}

	public String toString(){
		return this.state;
	}
}
