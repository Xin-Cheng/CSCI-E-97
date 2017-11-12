package cscie97.asn4.housemate.entitlement;

/**
 *  User class represents occupants in the House Mate System. 
 *  User has a unique id, name, credentials, and associated with Entitlements.
 *
 * @author Xin Cheng
 */
import java.util.UUID;

public class User implements Element{

	protected String id;
	protected String name;
	protected Role role;
	private Credential credential;
	private AccessToken accessToken;
	private ResourceRole resourceRole;
	
	private UserFactory userFactory;
	
	public User() {
	}
	
	public User(UserFactory userFactory) {
		this.userFactory = userFactory;
	}
	
	/**
     * Public method to create a user and set its id and name.
     * 
     * @param id unique id of a user
     * @param name user name
     */
	public void create(String id, String name) {
		setID(id);
		setName(name);
		role = userFactory.createRole();
		createAccessToken();
	}
	
	/**
     * Public method to create a random AccessToken to a user.
     */
	public void createAccessToken(){
		AccessToken accessToken = new AccessToken();
		accessToken.setID(UUID.randomUUID().toString() + id);
		accessToken.setState("active");
		this.accessToken = accessToken;
	}
	
	/**
	 * Return the AccessToken of  a user.
	 */
	public AccessToken getAccessToken(){
		return accessToken;
	}
	
	/**
     * Public setter and getter of user credential.
     */
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
	
	public Credential getCredential(){
		return credential;
	}
	
	/**
     * Public setter and getter of resource role of a user.
     */
	public void setResourceRole(ResourceRole resourceRole) {
		this.resourceRole = resourceRole;
	}
	public ResourceRole getResourceRole(){
		return resourceRole;
	}
	
	/**
     * Public getter and setter of private attribute: name
     */
	public void setID(String id) {
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
	public String getName(){
		return name;
	}
	
	/**
     * Public getter of Role. Returns the role of a user.
     */
	public Role getRole() {
		return role;
	}
	
	/**
     * Implement the API of Element class. Accept an visitor to visit itself.
     * 
     * @param visitor a visitor
     */
	public void accept(InventoryVisitor visitor){
		visitor.visit(this);
	}
	
	@Override
	public String toString() {
		String cred = "N/A";
		if(credential != null)
			cred = credential.getName();
		String rr = "N/A";
		if(resourceRole != null)
			rr = resourceRole.getName();
		String tokenState = "N/A";
		if(accessToken != null)
			tokenState = accessToken.getState();
		return "User{" +
        "ID = '" + id + '\'' +
        ", name =" + name +
        ", role = '" + role.getID() + '\'' +
        ", credential = '" + cred + '\'' +
        ", resourceRole = '" + rr + '\'' +
        ", accessTokenState = '" + tokenState + '\'' +
        '}';
	}
}
