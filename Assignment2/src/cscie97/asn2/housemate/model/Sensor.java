package cscie97.asn2.housemate.model;

public class Sensor {
	private String name;
	private String state;
	private Room location;
	
	public Sensor() {
		
	}
	
	public Sensor(String name) {
		this.name = name;
	}
	
	public String getName(){
		return name;
	}
	
	public String getState(){
		return state;
	}
	
	public Room getLocation(){
		return location;
	}
}
