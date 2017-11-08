package cscie97.asn4.housemate.entitlement;

public class UsernamePassworkCredential extends Credential{
	private String name;
	private int password;
	
	/**
     * Public default constructor
     */
	public UsernamePassworkCredential() {
		super();
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
     * Public method sets password of a admin user, for security purpose, password is stored as hashcode
     * 
     * @param password target password
     */
	public void setPassword(String password){
		this.password = password.hashCode();
	}
}
