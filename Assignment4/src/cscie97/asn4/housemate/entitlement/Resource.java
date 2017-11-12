package cscie97.asn4.housemate.entitlement;

/**
 * The Resource class represents resources in the House Mate System. 
 * This implementation deals with house level resources.
 *
 * @author Xin Cheng
 */
public class Resource {
	private String id;
	private String name;
	
    /**
     * Default constructor.
     */
	public Resource() {
		
	}
	
    /**
     * Public setter of private property: id.
     */
	public void setID(String id) {
		this.id = id;
	}
	
    /**
     * Public getter of private property: id. Returns the resource id
     */
	public String getID(){
		return id;
	}
	
    /**
     * Public setter of private property: name.
     */
	public void setName(String name){
		this.name = name;
	}
	
    /**
     * Public getter of private property: name. Returns the name of resource.
     */
	public String getName(){
		return name;
	}
}
