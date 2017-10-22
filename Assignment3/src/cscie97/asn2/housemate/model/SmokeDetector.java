package cscie97.asn2.housemate.model;

/**
 * The SmokeDetector is a special kind of sensor that users cannot set its status
 * @author Xin
 */
public class SmokeDetector extends Sensor{
	/**
     * Public constructor
	  * @param name unique identifier
     */
	public SmokeDetector(String name) {
		super(name);
	}
	
	/**
     * A setter
	 * @throws SettingException the status of a smoke detector cannot be set
     */
	public void setStatus(String statusName, String value) throws SettingException{
		throw new SettingException("Cannot set the status of Smoke Detector!");
	}
}
