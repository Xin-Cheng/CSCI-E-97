package cscie97.asn4.housemate.entitlement;

public class Credential {
	private String id;
	
	/**
     * Public default constructor
     */
	public Credential() {
		
	}
	
	/**
     * Public getter and setter of private attribute: id
     */
	public void setID(String id) {
		this.id = id;
	}
	public String getID(){
		return id;
	}
}
