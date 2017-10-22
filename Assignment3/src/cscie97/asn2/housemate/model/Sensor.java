package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * The Sensor is a LoT device that can share its data within the house
 * @author Xin
 */
public class Sensor {
	protected String name;
	protected Map<String, String> status;
	protected Room location;
		
	/**
     * Public default constructor
     */	
	public Sensor() {
		
	}
	
	/**
     * Public constructor
	 * @param name unique identifier
     */
	public Sensor(String name) {
		this.name = name;
		this.status = new HashMap<>();
	}
	
	/**
     * This method returns the unique identifier of a sensor
     *
     * @return unique identifier of a sensor
     */
	public String getName(){
		return name;
	}
	
	/**
     * This method returns the status list of a sensor
     *
     * @return status list of a sensor
     */
	public Map<String, String> getStatus(){
		return status;
	}
	
	/**
     * This method returns a refrence to a room where the sensor is in
     *
     * @return room a sensor is in
     */
	public Room getLocation(){
		return location;
	}
	
	/**
     * This method set the status of a sensor
     *
     * @param statusName name of a status
	 * @param value value of status
     */
	public void setStatus(String statusName, String value) throws SettingException{
		status.put(statusName, value);
	}
}
