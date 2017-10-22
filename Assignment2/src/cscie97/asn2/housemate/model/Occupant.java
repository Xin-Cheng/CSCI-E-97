package cscie97.asn2.housemate.model;

/**
 * The Occupant is a model for a occupant.
 *
 * @author Xin
 */
public class Occupant {
	private String name;
	private String type;
	private String status;
	
	/**
     * Public constructor
     * @param name unique identifier
     * @param type type of occupant: adult, child, pet
     */
	public Occupant(String name, String type) {
		this.name = name;
		this.type = type;
	}
	
	/**
     * This method returns the unique identifier of an occupant
     *
     * @return unique identifier of an occupant
     */
	public String getName(){
		return name;
	}

	public String getType(){
		return type;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String value) {
		status = value;
	}
}
