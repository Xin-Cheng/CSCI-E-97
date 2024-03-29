package cscie97.asn4.housemate.entitlement;

/**
 * Credential class is used in user authentication. 
 * A Credential contains a name (password and voice print), and value.
 *
 * @author Xin Cheng
 */
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
	
	/**
     * Public getter and setter of private attribute: value
     */
	public void setValue(String value){
		this.value = value;
	}
	public String getValue(){
		return value;
	}
}
