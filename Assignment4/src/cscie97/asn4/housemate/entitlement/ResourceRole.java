package cscie97.asn4.housemate.entitlement;

/**
 * ResourceRole class represents the association between Role and Resource. It has a unique id and name. 
 *
 * @author Xin Cheng
 */
public class ResourceRole {
	private String id;
	private String name;
	private Resource resource;
	private Role role;
	
	public ResourceRole() {
		
	}
	
	/**
     * Public setter and getter of private property: id.
     */
	public void setID(String id) {
		this.id = id;
	}
	public String getID(){
		return id;
	}
	
	/**
     * Public setter and getter of private property: name.
     */
	public void setName(String name){
		this.name = name;
	}
	public String getName(){
		return name;
	}
	
	/**
     * Public setter and getter of private association: Resource.
     */
	public void setResource(Resource resource){
		this.resource = resource;
	} 
	public Resource getResource(){
		return resource;
	}
	
	/**
     * Public setter and getter of private association: Role.
     */
	public void setRole(Role role){
		this.role = role;
	}
	public Role getRole(){
		return role;
	}
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "ResourceRole {Resource: " + resource.getID() + ", " + 
				"Role: " + role.getID() + "}";
	}
}
