package cscie97.asn4.housemate.entitlement;

/**
 * The AccessToken class is used for user authentication, if authentication succeed, an AccessToken is returned
 * to the caller and it will bind users to a set of permission
 *
 * @author Xin Cheng
 */
public class AccessToken {
	private String id;
	private String state;
	
	/**
     * Public default constructor
     */
	public AccessToken() {	}
	
	/**
     * Public getter and setter of private attribute: id
     */
	public void setID(String id) {
		this.id = id;
	}
	public String getID() {
		return id;
	}
	
	/**
     * Public getter and setter of private attribute: state
     */
	public void setState(String state){
		this.state = state;
	}
	
	public String getState(){
		return state;
	}
}