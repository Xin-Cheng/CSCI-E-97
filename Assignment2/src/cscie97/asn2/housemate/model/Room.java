package cscie97.asn2.housemate.model;

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
	}
	
	public String getName(){
		return name;
	}
	
	public void addSensor(Sensor sensor){
		sensorMap.put(sensor.getName(), sensor);
	}
	
	public void addAppliance(Appliance appliance) {
		applianceMap.put(appliance.getName(), appliance);
	}
	public void print(){
		System.out.println("Room name: " + name + " floor: " + floor + " type: " + type + " window: " + windowCount);
	}
}
