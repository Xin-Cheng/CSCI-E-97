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
    
    public void update(String command) {
    	System.out.println(command);
    }
    
	private void updateAppliance(Sensor sensor, Command command) {
		
	}
	private void updateOccupantLocation(Occupant occupant, String location) {
		
	}
	private void updateOccupantStatus(Occupant occupant, String status) {
		
	}
	private void getOccupantLocation(String occupantName) {
		
	}
	private void call911() {
		System.out.println("Calling 911...");		
	}
	private void sendEmail() {
		System.out.println("Sending email requsting more beers...");
	}
	private void executeCommand(String commandLine){
		System.out.println(commandLine);
	}
}
