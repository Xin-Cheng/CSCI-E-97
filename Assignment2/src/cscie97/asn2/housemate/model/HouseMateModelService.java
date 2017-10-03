package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Map;
import cscie97.asn1.knowledge.engine.KnowledgeGraph;
import cscie97.asn1.knowledge.engine.Node;

public class HouseMateModelService {
	private static HouseMateModelService instance;
	private Map<String, House> houseMap = new HashMap<>();
	private Map<String, Occupant> occupantMap = new HashMap<>();
	
	public static HouseMateModelService getInstance(){
		if(instance == null)
			instance = new HouseMateModelService();
		return instance;
	}
	
	public void defineHouse(String name, String address) {
		House house = new House(name, address);
		houseMap.put(house.getName(), house);
	}
	
	public void defineRoom(String name, int floor, String type, int windowsCount, String houseName) throws ObjectNotFoundException{
		if(!houseMap.containsKey(houseName))
			throw new ObjectNotFoundException("House Not Fount!");
		Room room = new Room(name, floor, type, windowsCount);
		houseMap.get(houseName).addRoom(room);
	}
	
	public void defineOccupant(String name, String type) {
		if(occupantMap.containsKey(name))
			System.out.println("You have already defined an occupant: " + name + "!");
		else {
			Occupant occupant = new Occupant(name, type);
			occupantMap.put(name, occupant);
		}
	}
		
	public void defineSensor(String name, String type, String houseName, String roomName) throws ObjectNotFoundException{
		if(!houseMap.containsKey(houseName))
			throw new ObjectNotFoundException("House Not Found!");
		else if(!houseMap.get(houseName).containsRoom(roomName))
			throw new ObjectNotFoundException("Room Not Found!");
		else {
			Sensor sensor;
			switch (type) {
			case "smoke_detector":
				sensor = new SmokeDetector(name);
				break;
			case "camera":
				sensor = new Camera(name);
				break;
			default:
				sensor = new Ava(name);
				break;
			}
			houseMap.get(houseName).getRoom(roomName).addSensor(sensor);
		}
	}
	
	public void defineAppliance(String name, String type, String houseName, String roomName) throws ObjectNotFoundException{
		if(!houseMap.containsKey(houseName))
			throw new ObjectNotFoundException("House Not Found!");
		else if(!houseMap.get(houseName).containsRoom(roomName))
			throw new ObjectNotFoundException("Room Not Found!");
		else {
			Appliance appliance;
			switch (type) {
			case "thermostat":
				appliance = new Thermostat(name);
				break;
			case "window":
				appliance = new Window(name);
				break;
			case "door":
				appliance = new Door(name);
				break;
			case "light":
				appliance = new Light(name);
				break;
			case "TV":
				appliance = new TV(name);
				break;
			case "Pandora":
				appliance = new Pandora(name);
				break;
			case "Oven":
				appliance = new Oven(name);
				break;
			default:
				appliance = new Refrigerator(name);
				break;
			}
			houseMap.get(houseName).getRoom(roomName).addAppliance(appliance);
		}
	}
	
	public void addOccupant(String occupantName, String houseName) throws ObjectNotFoundException{
		if(!houseMap.containsKey(houseName))
			throw new ObjectNotFoundException("House not found!");
		else
			houseMap.get(houseName).addOccupant(occupantMap.get(occupantName));
		
	}
	
	public void setSensorStatus(String houseName, String roomName, String name, String statusName, String value) throws ObjectNotFoundException{
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		
		try {
			if(!houseMap.containsKey(houseName))
				throw new ObjectNotFoundException("House Not Fount!");
			else if(!houseMap.get(houseName).containsRoom(roomName))
				throw new ObjectNotFoundException("Room Not Fount!");
			else {
				Sensor sensor = houseMap.get(houseName).getRoom(roomName).getSensor(name);
				Map<String, String> status = sensor.getStatus();
				if(status.containsKey(statusName)) {
					String oldValue = status.get(statusName);
					Node node = knowledgeGraph.getNode(oldValue);
					node.setIdentifier(value);
				} else {
					knowledgeGraph.importTriple(houseName + ":" + roomName + ":"+name, "has_" + statusName, value);
				}
				sensor.setStatus(statusName, value);
			}			
		} catch (SettingException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	public void setApplianceStatus(String houseName, String roomName, String name, String statusName, String value) throws ObjectNotFoundException{
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		try {
			if(!houseMap.containsKey(houseName))
				throw new ObjectNotFoundException("House Not Fount!");
			else if(!houseMap.get(houseName).containsRoom(roomName))
				throw new ObjectNotFoundException("Room Not Fount!");
			else {
				Appliance appliance = houseMap.get(houseName).getRoom(roomName).getAppliance(name);
				Map<String, String> status = appliance.getStatus();
				if(status.containsKey(statusName)) {
					String oldValue = status.get(statusName);
					Node node = knowledgeGraph.getNode(oldValue);
					node.setIdentifier(value);
				} else {
					knowledgeGraph.importTriple(houseName+":"+roomName+":"+name, "has_" + statusName, value);
				}
				appliance.setStatus(statusName, value);
			}
		} catch (SettingException ex) {
			System.out.println(ex.getMessage());
		}
	}
	
	
	public House getHouse(String houseName) {
		return houseMap.get(houseName); 
	}
}
