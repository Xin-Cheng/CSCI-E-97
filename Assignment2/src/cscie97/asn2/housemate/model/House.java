package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The House is a model for a house.
 *
 * @author Xin
 */
public class House {

	private String name;
	private String address;
	private Map<String, Room> roomMap;
	private Map<String, Occupant> occupantMap;
    
	/**
     * Public constructor
     * @param name unique house identifier
     * @param address house address
     */
	public House(String name, String address) {
		this.name = name;
		this.address = address;
		roomMap = new HashMap<>();
		occupantMap = new HashMap<>();
	}
	
	/**
     * This method returns the unique identifier of a house
     *
     * @return unique identifier of a house
     */
	public String getName(){
		return name;
	}
	
	/**
     * This method returns the address of a house
     *
     * @return address of a house
     */
	public String getAddress(){
		return address;
	}
	
	/**
     * This method returns a refrence to a room in a house with a specific identifier
     * @param roomName identifier of a room
     * @return refrence of a room
     */
	public Room getRoom(String roomName){
		return roomMap.get(roomName);
	}
	
	/**
     * This method add a room to a house
     * @param room room to be added
     */
	public void addRoom(Room room){
		if(roomMap.containsKey(name))
			System.out.println("You have already defined a room: " + name + "!");
		else
			roomMap.put(room.getName(), room);
	}
	
	/**
     * This method add an occupant to a house
     * @param occupant occupant to be added
     */
	public void addOccupant(Occupant occupant){
		occupantMap.put(occupant.getName(), occupant);
	}
	
	/**
     * This method returns a boolean to indicate whether a room is in a house
     * @param roomName identifier of a room
     * @return true if a house contains a room, false if not
     */
	public boolean containsRoom(String roomName){
		return roomMap.containsKey(roomName);
	}
}
