package cscie97.asn4.housemate.entitlement;

public class Entitlement implements Visitor{
	private String id;
	private String name;
	private String description;
	
	/**
     * Public default constructor
     */
	public Entitlement() {
		
	}
	
	/**
     * Public getter and setter of private attribute: id
     */
	public void setID(String id){
		this.id = id;
	}
	public String getID() {
		return id;
	}
	
	/**
     * Public getter and setter of private attribute: name
     */
	public void setName(String name){
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	/**
     * Public getter and setter of private attribute: description
     */
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	
	public boolean hasNext(){
		return false;
	}
	public Entitlement getNext(){
		return null;
	}
}
