package cscie97.asn4.housemate.entitlement;

/**
 * Entitlement class represents user�s accessibility to a resource. 
 * Entitlement has a unique id, name, and description.
 *
 * @author Xin Cheng
 */
public class Entitlement{
	private String id;
	private String name;
	private String description;
	
	/**
     * Public default constructor
     */
	public Entitlement() {
		
	}
	
	/**
     * Public getter and setter of private attribute: id
     */
	public void setID(String id){
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
	public String getName() {
		return name;
	}
	
	/**
     * Public getter and setter of private attribute: description
     */
	public void setDescription(String description){
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
}
