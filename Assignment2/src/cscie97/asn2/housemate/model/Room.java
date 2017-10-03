package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The Room is a model for a room.
 *
 * @author Xin
 */
public class Room {
	private String name;
	private int floor;
	private String type;
	private int windowCount;
	private Map<String, Sensor> sensorMap;
	private Map<String, Appliance> applianceMap;
	
	/**
     * Public constructor
     * @param name unique house identifier
     * @param floor the floor this room is on
	 * @param type the room type
	 * @param windowCount the number of windows a room has
     */
	public Room(String name, int floor, String type, int windowCount){
		this.name = name;
		this.floor = floor;
		this.type = type;
		this.windowCount = windowCount;
		sensorMap = new HashMap<>();
		applianceMap = new HashMap<>();
	}
	
	/**
     * This method returns the unique identifier of a room
     *
     * @return unique identifier of a room
     */
	public String getName(){
		return name;
	}
	
	/**
     * This method returns a refrence to a sensor in a room with a specific identifier
     * @param namne identifier of a sensor
     * @return refrence of a sensor
	 * @throws ObjectNotFoundException when sensor is not found
     */
	public Sensor getSensor(String name) throws ObjectNotFoundException{
		if(!sensorMap.containsKey(name))
			throw new ObjectNotFoundException("Cannot find sensor with name: " + name);
		return sensorMap.get(name);
	}
	
	/**
     * This method returns a refrence to a appliance in a room with a specific identifier
     * @param namne identifier of a appliance
     * @return refrence of a appliance
	 * @throws ObjectNotFoundException when appliance is not found
     */
	public Appliance getAppliance(String name) throws ObjectNotFoundException{
		if(!applianceMap.containsKey(name))
			throw new ObjectNotFoundException("Cannot find appliance with name: " + name);
		return applianceMap.get(name);
	}

	/**
     * This method add a sensor to a room
     * @param sensor sensor to be added
     */
	public void addSensor(Sensor sensor){
		sensorMap.put(sensor.getName(), sensor);
	}
	
	/**
     * This method add a appliance to a room
     * @param appliance appliance to be added
     */
	public void addAppliance(Appliance appliance) {
		applianceMap.put(appliance.getName(), appliance);
	}
}
