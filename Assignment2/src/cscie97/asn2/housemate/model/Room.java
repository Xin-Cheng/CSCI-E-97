package cscie97.asn2.housemate.model;

public class Room {
	private String name;
	private int floor;
	private String type;
	private int windowCount;
	
	public Room(String name, int floor, String type, int windowCount){
		this.name = name;
		this.floor = floor;
		this.type = type;
		this.windowCount = windowCount;
	}
}
