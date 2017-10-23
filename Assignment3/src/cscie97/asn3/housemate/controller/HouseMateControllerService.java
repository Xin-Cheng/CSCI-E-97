package cscie97.asn3.housemate.controller;


import cscie97.asn2.housemate.model.HouseMateModelService;
import cscie97.asn2.housemate.model.ObjectNotFoundException;
import cscie97.asn2.housemate.model.Occupant;

public class HouseMateControllerService implements IHouseMateControllerService{
	
	private String houseName;
	private String roomName;
	private String name;
	private String statusName;
	private String statusValue;
	
	
	private Command applianceStatusChangeCommand = ()-> {HouseMateModelService.getInstance().setApplianceStatus(houseName, roomName, name, statusName, statusValue);};
	
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
    	
		// split command by space
		String[] words = command.split(" ");
		if(words[1].equals("appliance")) {
			try {
				updateAppliance(words);
			} catch (InvalidCommandException e) {
	        	e.printStackTrace();
	        	InvalidCommandException ie = new InvalidCommandException();
	            ie.setCommand(command);
	            System.out.println(ie);
	        }
		}
    }
    
    private void setCommand(String houseName, String roomName, String name, String statusName, String statusValue){
    	this.houseName = houseName;
    	this.roomName = roomName;
    	this.name = name;
    	this.statusName = statusName;
    	this.statusValue = statusValue;
    }
    
	private void updateAppliance(String[] commandWords) throws InvalidCommandException{
		if (commandWords.length < 7)
			throw new InvalidCommandException();
		
		String name = commandWords[2];
		String[] appNames = name.split(":");

		if (appNames[2].startsWith("ava")) {
			String voice = "";
			for(int i = 6; i < commandWords.length; i++) {voice = voice.concat(commandWords[i]);}
			voice = voice.substring(1, voice.length() - 1);

			switch (voice) {
			case "opendoor":
				setCommand(appNames[0], appNames[1], appNames[2], "doorStatus", "open");
				System.out.println("Door in room " + name + " is opened!");
				break;
			case "closedoor":
				setCommand(appNames[0], appNames[1], appNames[2], "doorStatus", "close");
				System.out.println("Door in room " + name + " is closed!");
				break;
			case "lightson":
				setCommand(appNames[0], appNames[1], appNames[2], "lightStatus", "on");
				System.out.println("Lights in room " + name + " is turned on!");
				break;
			case "lightsoff":
				setCommand(appNames[0], appNames[1], appNames[2], "lightStatus", "off");
				System.out.println("Lights in room " + name + " is turned off!");
				break;
			default:
				break;
			}
			try {
				applianceStatusChangeCommand.execute();
			} catch (ObjectNotFoundException e) {
				System.out.println(e.getMessage());
			}
		}
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
