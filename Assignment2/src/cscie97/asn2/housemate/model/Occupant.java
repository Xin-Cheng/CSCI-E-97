package cscie97.asn2.housemate.model;

public class Occupant {
	private String name;
	private String type;
	
	public Occupant(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	public String getName(){
		return name;
	}
	public void print(){
		System.out.println("Occupant name: " + name + " type: " + type);
	}
}
