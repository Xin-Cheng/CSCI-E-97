package cscie97.asn3.housemate.controller;

import cscie97.asn2.housemate.model.Occupant;
import cscie97.asn2.housemate.model.Sensor;

public class HouseMateControllerService implements IHouseMateControllerService{
	
    /**
     * Private default constructor
     */
    private HouseMateControllerService() {

    }
    
    /**
     * Private static singleton holder
     */
    private static class HouseMateControllerServiceSingletonHolder {
        public static final HouseMateControllerService INSTANCE = new HouseMateControllerService();
    }
    
    /**
     * This method returns a reference to the single static instance of the HouseMateControllerService.
     *
     * @return single instance of HouseMateControllerService.
     */
    public static HouseMateControllerService getInstance() {
        return HouseMateControllerServiceSingletonHolder.INSTANCE;
    }
    
	public void updateAppliance(Sensor sensor, Command command) {
		
	}
	public void updateOccupantLocation(Occupant occupant, String location) {
		
	}
	public void updateOccupantStatus(Occupant occupant, String status) {
		
	}
	public void getOccupantLocation(String occupantName) {
		
	}
	public void call911() {
		System.out.println("Calling 911...");		
	}
	public void sendEmail() {
		System.out.println("Sending email requsting more beers...");
	}
	public void executeCommand(String commandLine){
		System.out.println(commandLine);
	}
}
