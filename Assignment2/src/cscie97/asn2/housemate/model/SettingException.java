package cscie97.asn2.housemate.model;

/**
 * This checked exception is thrown by functions setting status of a sensor or appliance
 *
 * @author  Xin
 */
public class SettingException extends Exception{
	public SettingException(String message) {
		super("Setting Exception: " + message);
	}
}
