package cscie97.asn3.housemate.controller;


import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import cscie97.asn2.housemate.model.HouseMateModelService;
import cscie97.asn2.housemate.model.ObjectNotFoundException;
import cscie97.asn2.housemate.model.Occupant;

public class HouseMateControllerService implements IHouseMateControllerService{
	
	private String houseName;
	private String roomName;
	private String name;
	private String statusName;
	private String statusValue;
	private String lastWord;
	
	
	private Command applianceStatusChangeCommand = ()-> {HouseMateModelService.getInstance().setApplianceStatus(houseName, roomName, name, statusName, statusValue);};
	private Command call911Command = ()-> {System.out.println("Calling 911...");};
	private Command sendEmailCommand = ()-> {System.out.println("Sending email requsting more beers...");};
	private Command showOccupantLocationCommand = ()-> {HouseMateModelService.getInstance().showOccupantLocation(lastWord);};
	
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

			String on = commandWords[commandWords.length - 1];
			this.lastWord = on.substring(0, on.length()-1);
			
			String voice = "";
			for(int i = 6; i < commandWords.length; i++) {voice = voice.concat(commandWords[i]);}
			voice = voice.substring(1, voice.length() - 1);
			
			String where = voice.startsWith("where")? "where" : "noWhere";
			
			switch (where) {
			case "where":
				// ava receive command that ask the location of an occupant
				try {
					showOccupantLocationCommand.execute();
				} catch (ObjectNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			case "noWhere":
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
					// general command
					String applianceName = commandWords[commandWords.length - 2].substring(1);
					String fullApplianceName = new StringBuilder().append(appNames[0]).append(":").append(appNames[1]).append(":").append(applianceName).toString();
					Command showApplianceStatusCommand = ()-> {HouseMateModelService.getInstance().showAppianceStatus(fullApplianceName, lastWord);}; 
					try {
						showApplianceStatusCommand.execute();
					} catch (ObjectNotFoundException e) {
						System.out.println(e.getMessage());
					}
					return;
				}
				try {
					applianceStatusChangeCommand.execute();
				} catch (ObjectNotFoundException e) {
					System.out.println(e.getMessage());
				}
				break;
			default:
				break;
			}
		}
	}
	private void updateOccupantLocation(Occupant occupant, String location) {
		
	}
	private void updateOccupantStatus(Occupant occupant, String status) {
		
	}
	private void getOccupantLocation(String occupantName) {
		
	}
}
