package cscie97.asn2.housemate.model;

public class Sensor {
	protected String name;
	protected String state;
	protected Room location;
		
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
	
	public void setStatus(String statusName, String value){
		state = value;
	}
	public void print(){
		System.out.println("name: " + name); 
	}
}
