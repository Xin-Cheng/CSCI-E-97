package cscie97.asn3.housemate.controller;
import cscie97.asn2.housemate.model.*;

public interface IHouseMateControllerService {
	public void updateSensor(Sensor sensor, String status, String value);
	public void updateOccupantLocation(Occupant occupant, String location);
	public void updateOccupantStatus(Occupant occupant, String status);
	public void getOccupantLocation(String occupantName);
	public void call911();
	public void sendEmail();
}
