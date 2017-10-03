package cscie97.asn2.housemate.model;

/**
 * The Appliance is a special kind of sensor, that can share it's data and be controlled.
 * @author Xin
 */

public class Appliance extends Sensor{
	/**
     * Public default constructor
     */
	public Appliance() {
		super();
	}
	
	/**
     * Public constructor
	  * @param name unique identifier
     */
	public Appliance(String name) {
		super(name);
	}
}
