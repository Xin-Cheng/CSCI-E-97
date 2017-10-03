package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Map;

public class Room {
	private String name;
	private int floor;
	private String type;
	private int windowCount;
	private Map<String, Sensor> sensorMap;
	private Map<String, Appliance> applianceMap;
	
	public Room(String name, int floor, String type, int windowCount){
		this.name = name;
		this.floor = floor;
		this.type = type;
		this.windowCount = windowCount;
		sensorMap = new HashMap<>();
		applianceMap = new HashMap<>();
	}
	
	public String getName(){
		return name;
	}
	
	public Sensor getSensor(String name) {
		return sensorMap.get(name);
	}
	
	public Appliance getAppliance(String name) {
		return applianceMap.get(name);
	}
	public void addSensor(Sensor sensor){
		sensorMap.put(sensor.getName(), sensor);
	}
	
	public void addAppliance(Appliance appliance) {
		applianceMap.put(appliance.getName(), appliance);
	}
}
