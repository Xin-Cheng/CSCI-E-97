package cscie97.asn2.housemate.model;

public class SettingException extends Exception{
	public SettingException(String message) {
		super("Setting Exception: " + message);
	}
}
