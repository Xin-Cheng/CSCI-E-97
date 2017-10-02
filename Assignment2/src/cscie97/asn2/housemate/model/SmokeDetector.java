package cscie97.asn2.housemate.model;

public class SmokeDetector extends Sensor{
	public SmokeDetector(String name) {
		super(name);
	}
	
	public void setStatus(String statusName, String value) throws SettingException{
		throw new SettingException("Cannot set the status of Smoke Detector!");
	}
}
