package cscie97.asn3.housemate.controller;


import org.omg.CosNaming.NamingContextExtPackage.StringNameHelper;

import cscie97.asn2.housemate.model.House;
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
		} else if(words[1].equals("sensor")) {
			try {
				updateSensor(words);
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
			
			if(voice.startsWith("where")) {
				// ava receive command that ask the location of an occupant
				try {
					showOccupantLocationCommand.execute();
				} catch (ObjectNotFoundException e) {
					System.out.println(e.getMessage());
				}
			} else {
				if(voice.startsWith("opendoor")) {
					setCommand(appNames[0], appNames[1], lastWord, "doorStatus", "open");
					System.out.println("Door in room " + appNames[0] + ":" + appNames[1] + " is opened!");
				} else if(voice.startsWith("closedoor")) {
					setCommand(appNames[0], appNames[1], lastWord, "doorStatus", "close");
					System.out.println("Door in room " + name + " is closed!");
				} else if(voice.startsWith("turn_onlight")) {
					setCommand(appNames[0], appNames[1], lastWord, "lightStatus", "on");
					System.out.println("Lights in room " + name + " is turned on!");
				} else if(voice.startsWith("turn_offlight")) {
					setCommand(appNames[0], appNames[1], lastWord, "lightStatus", "off");
					System.out.println("Lights in room " + name + " is turned off!");
				} else {
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
			}
		}
	}
	
	private void updateSensor(String[] commandWords) throws InvalidCommandException{
		if (commandWords.length != 7)
			throw new InvalidCommandException();
		
		String occupantName = commandWords[commandWords.length - 1];
		String cameraStatus = commandWords[4];
		
		String name = commandWords[2];
		String[] appNames = name.split(":");
		
		Command occupantDetectedCommand = ()-> {
			applianceStatusChangeCommand.execute();
			System.out.println("Lights in room " + name + " is turned on!");
			System.out.println("Temperature in room " + name + " is increased!");
			System.out.println("Knowledge graph is updated!");
			HouseMateModelService.getInstance().changeOccupantLocation(occupantName, appNames[0], appNames[1]);
		}; 
		Command occupantLeavingCommand = ()-> {
			applianceStatusChangeCommand.execute();
			System.out.println("Lights in room " + name + " is turned off!");
			System.out.println("Temperature in room " + name + " is descreased!");
			System.out.println("Knowledge graph is updated!");
			HouseMateModelService.getInstance().changeOccupantLocation(occupantName, appNames[0], null);
		}; 
		
		switch (cameraStatus) {
		case "occupant_detected":
			setCommand(appNames[0], appNames[1], "light1", "lightStatus", "on");
			try {
				occupantDetectedCommand.execute();
			} catch (ObjectNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			break;
		case "occupant_leaving":
			setCommand(appNames[0], appNames[1], "light1", "lightStatus", "off");
			try {
				occupantLeavingCommand.execute();
			} catch (ObjectNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			break;
		case "occupant_active":
			Command occupantActiveCommand = ()-> { HouseMateModelService.getInstance().updateOccupantStatus(occupantName, "active");};
			try {
				occupantActiveCommand.execute();
				System.out.println(occupantName+" has_status "+"active");
				System.out.println("Knowledge graph is updated!");
			} catch (ObjectNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			break;
		case "occupant_inactive":
			Command occupantInactiveCommand = ()-> { HouseMateModelService.getInstance().updateOccupantStatus(occupantName, "inactive");};
			try {
				occupantInactiveCommand.execute();
				System.out.println(occupantName+" has_status "+"inactive");
				System.out.println("Knowledge graph is updated!");
			} catch (ObjectNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			break;
		}
	}
}
