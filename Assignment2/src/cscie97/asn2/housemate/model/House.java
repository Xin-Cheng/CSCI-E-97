package cscie97.asn2.housemate.model;

import java.util.HashMap;
import java.util.Map;

import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

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
	
	public String getName(){
		return name;
	}
	
	public String getAddress(){
		return address;
	}
	
	public Room getRoom(String roomName){
		return roomMap.get(roomName);
	}
	
	public void addRoom(Room room){
		if(roomMap.containsKey(name))
			System.out.println("You have already defined a room: " + name + "!");
		else
			roomMap.put(room.getName(), room);
	}
	
	public void addOccupant(Occupant occupant){
		occupantMap.put(occupant.getName(), occupant);
	}
	
	public boolean containsRoom(String roomName){
		return roomMap.containsKey(roomName);
	}
}
