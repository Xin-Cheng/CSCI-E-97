package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Map;

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
		house.print();
		houseMap.put(house.getName(), house);
	}
	
	public void defineRoom(String name, int floor, String type, int windowsCount, String houseName) throws ObjectNotFoundException{
		if(!houseMap.containsKey(houseName))
			throw new ObjectNotFoundException("House Not Fount!");
		Room room = new Room(name, floor, type, windowsCount);
		room.print();
		houseMap.get(houseName).addRoom(room);
	}
	
	public void defineOccupant(String name, String type) {
		if(occupantMap.containsKey(name))
			System.out.println("You have already defined an occupant: " + name + "!");
		else {
			Occupant occupant = new Occupant(name, type);
			occupant.print();
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
				sensor.print();
				break;
			case "camera":
				sensor = new Camera(name);
				sensor.print();
				break;
			default:
				sensor = new Ava(name);
				sensor.print();
				break;
			}
			houseMap.get(houseName).getRoom(roomName).addSensor(sensor);
		}
	}
	
	public void defineAppliance(){
		
	}
	
	public void addOccupant(String occupantName, String houseName) throws ObjectNotFoundException{
		if(!houseMap.containsKey(houseName))
			throw new ObjectNotFoundException("House not found!");
		else
			houseMap.get(houseName).addOccupant(occupantMap.get(occupantName));
		
	}
	
	public void setStatus(String[] command){
//		System.out.println("set Status");
	}
	
	public void show(String[] command) {
//		System.out.println("show");
	}
}
