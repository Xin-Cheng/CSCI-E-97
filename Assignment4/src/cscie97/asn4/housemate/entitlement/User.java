package cscie97.asn4.housemate.entitlement;

import java.util.UUID;

public class User {

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
	
	public void create(String id, String name) {
		setID(id);
		setName(name);
		role = userFactory.createRole();
		createAccessToken();
	}
	
	public void createAccessToken(){
		AccessToken accessToken = new AccessToken();
		accessToken.setID(UUID.randomUUID().toString() + id);
		accessToken.setState("active");
		this.accessToken = accessToken;
	}
	
	public void setCredential(Credential credential) {
		this.credential = credential;
	}
		
	public void setResourceRole(ResourceRole resourceRole) {
		this.resourceRole = resourceRole;
	}
	
	public void setID(String id) {
		this.id = id;
	}
	public String getID() {
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public Role getRole() {
		return role;
	}
	
	public ResourceRole getResourceRole(){
		return resourceRole;
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
