package cscie97.asn4.housemate.entitlement;

public class User {

	private String id;
	private String name;
	private Role role;
	private Credential credential;
	private AccessToken accessToken;
	
	public User() {
		
	}
	
	public void setID(String id) {
		this.id = id;
	}
	public String getID(){
		return id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	public void setRole(Role role){
		this.role = role;
	}
	public Role getRole(){
		return role;
	}
	
	public void setCredential(Credential credential){
		this.credential = credential;
	}
	
	public void setAccessToken(AccessToken accessToken) {
		this.accessToken = accessToken;
	}
}
