package cscie97.asn2.housemate.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import cscie97.asn1.knowledge.engine.KnowledgeGraph;
import cscie97.asn1.knowledge.engine.Node;
import cscie97.asn3.housemate.controller.HouseMateControllerService;
import cscie97.asn3.housemate.controller.IHouseMateControllerService;

/**
 * The HouseMateModelService manages all the houses and everything in each house.
 *
 * The HouseMateModelService is a singleton, meaning there is only one instance of this class.  A special static method
 * (getInstance()) is provided to access the single HouseMateModelService instance. 
 *
 * @author Xin
 */
public class HouseMateModelService {
	private static HouseMateModelService instance;
	private Map<String, House> houseMap = new HashMap<>();
	private Map<String, Occupant> occupantMap = new HashMap<>();

	// Changes to House Mate Model Service------------------------------------
	private HouseMateControllerService houseMateControllerService = null;
	/**
     * Public function to register itself to HMCS
	  * @param controllerService House mate controller service
     */
	public void registerHMCS(IHouseMateControllerService controllerService){
		houseMateControllerService = HouseMateControllerService.getInstance();
	}
	/**
     * Public function to remove itself from HMCS
	  * @param controllerService House mate controller service
     */
	public void removeHMCS(IHouseMateControllerService controllerService){
		if(houseMateControllerService != null) {
			houseMateControllerService = null;
		}
	}
	
	public void notifyHMCS(String statusChange) {
		houseMateControllerService.update(statusChange);
	}
	// End of change--------------------------------------------------------------
	/**
     * This method returns a reference to the single static instance of the HouseMateModelService.
     *
     * @return single instance of HouseMateModelService.
     */
	public static HouseMateModelService getInstance(){
		if(instance == null)
			instance = new HouseMateModelService();
		return instance;
	}
	
    /**
     * Public method for defining and adding a house to the service
     *
     * @param name house identifier
     * @param address house address
     */
	public void defineHouse(String name, String address) {
		House house = new House(name, address);
		houseMap.put(house.getName(), house);
	}
	
	/**
     * Public method for defining and adding a room to the house
     *
     * @param name room identifier
     * @param floor the floor number a room is on
	 * @param type the type of a room
	 * @param windowCount the number of windows a room has
	 * @param houseName the identifier of a house this room will be add to
     */
	public void defineRoom(String name, int floor, String type, int windowsCount, String houseName) throws ObjectNotFoundException{
		if(!houseMap.containsKey(houseName))
			throw new ObjectNotFoundException("House Not Fount!");
		Room room = new Room(name, floor, type, windowsCount);
		houseMap.get(houseName).addRoom(room);
	}
	
	/**
     * Public method for defining an occupant
     *
     * @param name occupant identifier
	 * @param type occupant type
     */
	public void defineOccupant(String name, String type) {
		if(occupantMap.containsKey(name))
			System.out.println("You have already defined an occupant: " + name + "!");
		else {
			Occupant occupant = new Occupant(name, type);
			occupantMap.put(name, occupant);
		}
	}

	/**
     * Public method for defining and adding a sensor to a room 
     *
     * @param name sensor identifier
	 * @param type the type of a sensor
	 * @param houseName the identifier of a house
	 * @param roomName the identifier of a room
	 * @throws ObjectNotFoundException when room/house is not found
     */	
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
	
	/**
     * Public method for defining and adding an appliance to a room 
     *
     * @param name appliance identifier
	 * @param type the type of a appliance
	 * @param houseName the identifier of a house
	 * @param roomName the identifier of a room
	 * @throws ObjectNotFoundException when room/house is not found
     */	
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
	
	/**
     * Public method for adding an occupant to a house
     *
     * @param occupantName occupant identifier
	 * @param houseName house identifier
	 * @throws ObjectNotFoundException when room/house is not found
     */
	public void addOccupant(String occupantName, String houseName) throws ObjectNotFoundException{
		if(!houseMap.containsKey(houseName))
			throw new ObjectNotFoundException("House not found!");
		else
			houseMap.get(houseName).addOccupant(occupantMap.get(occupantName));
		
	}
	
	/**
     * Public method for setting the status of a sensor
     *
	 * @param houseName house identifier
	 * @param roomName room identifier
	 * @param name sensor identifier
	 * @param statusName status name
	 * @param value status value
	 * @throws ObjectNotFoundException when room/house is not found
     */
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
	
	/**
     * Public method for setting the status of an appliance
     *
	 * @param houseName house identifier
	 * @param roomName room identifier
	 * @param name sensor identifier
	 * @param statusName status name
	 * @param value status value
	 * @throws ObjectNotFoundException when room/house is not found
     */
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
