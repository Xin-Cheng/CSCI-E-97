package cscie97.asn4.housemate.entitlement;

public class ResourceRole {
	private String id;
	private String name;
	private Resource resource;
	private Role role;
	
	public ResourceRole() {
		
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
	
	public void setResource(Resource resource){
		this.resource = resource;
	} 
	public Resource getResource(){
		return resource;
	}
	
	public void setRole(Role role){
		this.role = role;
	}
	public Role getRole(){
		return role;
	}
}
