package cscie97.asn4.housemate.entitlement;

/**
 * UsernamePassworkCredential class is a type of Credential class. 
 * It is the Credential of an Administrator resident. 
 *
 * @author Xin Cheng
 */
public class UsernamePassworkCredential extends Credential{
	/**
     * Public default constructor
     */
	public UsernamePassworkCredential() {
		super();
	}
	
	/**
     * Public method sets password of a admin user, for security purpose, password is stored as hashcode
     * 
     * @param password target password
     */
	@Override
	public void setValue(String password){
		this.value = String.valueOf(password.hashCode());
	}
}
