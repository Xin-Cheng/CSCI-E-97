package cscie97.asn4.housemate.entitlement;

public class Credential {
	private String name;
	protected String value;
	
	/**
     * Public default constructor
     */
	public Credential() {
		
	}
	
	/**
     * Public getter and setter of private attribute: name
     */
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	public void setValue(String value){
		this.value = value;
	}
}
