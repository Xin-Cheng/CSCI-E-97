package cscie97.asn3.housemate.controller;

import cscie97.asn2.housemate.model.HouseMateModelService;
import cscie97.asn2.housemate.model.ObjectNotFoundException;
import cscie97.asn4.housemate.entitlement.AccessToken;
import cscie97.asn4.housemate.entitlement.EntitlementService;
import cscie97.asn4.housemate.entitlement.EntityNotFoundException;

/**
 * The HouseMateControllerService listens to all events happen in House.
 * Once an event happens, it is invoked by HouseMateModelService and take actions according to correspond rules.
 * The HouseMateControllerService is a singleton, meaning there is only one instance of this class.  A special static method
 * (getInstance()) is provided to access the single HouseMateModelService instance. 
 *
 * @author Xin Cheng
 */

public class HouseMateControllerService implements IHouseMateControllerService{
	
	private String houseName;
	private String roomName;
	private String name;
	private String statusName;
	private String statusValue;
	private String lastWord;
	private AccessToken accessToken;
	
    /**
     * Lambda expression that interact with House Mate Model Service to change application status. 
     */
	private Command applianceStatusChangeCommand = ()-> {HouseMateModelService.getInstance().setApplianceStatus(houseName, roomName, name, statusName, statusValue, accessToken);};
    /**
     * Lambda expression that perform call 911 action when fire is detected in house.
     */
	private Command call911Command = ()-> {System.out.println("Calling 911...");};
    /**
     * Lambda expression that order beer from store when the beer count is less than 4.
     */
	private Command sendEmailCommand = ()-> {System.out.println("Sending email requsting more beers...");};
    /**
     * Lambda expression that get occupant status from Knowledge through HouseMateModelService API.
     */
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
    
    /**
     * Implementation of IHouseMateControllerService interface
     * @param command event that triggers the House Mate Controller Service
     * 
     */   
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
		} else if(words[1].equals("fire")) {
			manageFire(words);
		}
    }
    
    /**
     * Private function to set the configuration of command
     * @param houseName the name of house
     * @param roomName the name of room
     * @param name the name of appliance
     * @param statusName the name of the status
     * @param statusValue the value of status
     * 
     */
    private void setCommand(String houseName, String roomName, String name, String statusName, String statusValue){
    	this.houseName = houseName;
    	this.roomName = roomName;
    	this.name = name;
    	this.statusName = statusName;
    	this.statusValue = statusValue;
    }
    
	/**
     * Public method for changing the appliance status
     *
     * @param commandWords parameter array that specify the status change
	 * @throws InvalidCommandException when command has invalid syntax
     */	
	private void updateAppliance(String[] commandWords) throws InvalidCommandException{
		if (commandWords.length < 7)
			throw new InvalidCommandException();
		
		String name = commandWords[2];
		String[] appNames = name.split(":");

		// ava get voice command
		if (appNames[2].startsWith("ava")) {

			String on = commandWords[commandWords.length - 2];
			this.lastWord = on.substring(0, on.length()-1);

			// Change to HMMS: check accssToken---------------------------------------------------
			String voicePrint = commandWords[commandWords.length - 1];
			// End of change---------------------------------------------------
			
			String voice = "";
			for(int i = 6; i < commandWords.length-1; i++) {voice = voice.concat(commandWords[i]);}
			voice = voice.substring(1, voice.length() - 1);
			
			if(voice.startsWith("where")) {
				// ava receive command that ask the location of an occupant
				try {
					showOccupantLocationCommand.execute();
				} catch (ObjectNotFoundException e) {
					System.out.println(e.getMessage());
				}
			} else {
				// Change to HMMS: check accssToken---------------------------------------------------
				try {
					accessToken = EntitlementService.getInstance().identify(voicePrint);	
				} catch (EntityNotFoundException e) {
					e.printStackTrace();
				}
				// End of change---------------------------------------------------
				
				if(voice.startsWith("opendoor")) {
					// ava receive command to open the door
					setCommand(appNames[0], appNames[1], lastWord, "doorStatus", "open");
//					System.out.println("Door in room " + appNames[0] + ":" + appNames[1] + " is opened!");
				} else if(voice.startsWith("closedoor")) {
					// ava receive command to close the door
					setCommand(appNames[0], appNames[1], lastWord, "doorStatus", "close");
//					System.out.println("Door in room " + name + " is closed!");
				} else if(voice.startsWith("turn_onlight")) {
					// ava receive command to turn on the light
					setCommand(appNames[0], appNames[1], lastWord, "lightStatus", "on");
//					System.out.println("Lights in room " + name + " is turned on!");
				} else if(voice.startsWith("turn_offlight")) {
					// ava receive command to turn off the light
					setCommand(appNames[0], appNames[1], lastWord, "lightStatus", "off");
//					System.out.println("Lights in room " + name + " is turned off!");
				} else if (voice.startsWith("turn_onoven")) {
					// ava receive command to turn on the oven
					setCommand(appNames[0], appNames[1], lastWord, "ovenStatus", "on");
//					System.out.println("Oven in room " + name + " is turned on!");
				} else if (voice.startsWith("turn_offoven")) {
					// ava receive command to turn off the oven
					setCommand(appNames[0], appNames[1], lastWord, "ovenStatus", "off");
//					System.out.println("Oven in room " + name + " is turned off!");
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
		} else if(appNames[2].startsWith("refrigerator")){
			// refrigerator status beerCount change
			int beerCount = Integer.parseInt(commandWords[commandWords.length - 1]); 
			if(beerCount < 4) {
				try {
					System.out.println("Current beer count is " + commandWords[commandWords.length - 1]);
					sendEmailCommand.execute();
				} catch (ObjectNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
		} else if(appNames[2].startsWith("oven")){
			// oven status timeToCook change
			int timeToCook = Integer.parseInt(commandWords[commandWords.length - 1]); 
			Command foodReadyCommand = ()-> { System.out.println("Ava: Food is ready!");}; 
			
			if(timeToCook == 0) {
				try {
					foodReadyCommand.execute();
				} catch (ObjectNotFoundException e) {
					System.out.println(e.getMessage());
				}
			}
		}
	}
	
	/**
     * Public method that acting on camera status change
     *
     * @param commandWords parameter array that specify the corresponding action
	 * @throws InvalidCommandException when command has invalid syntax
     */	
	private void updateSensor(String[] commandWords) throws InvalidCommandException{
		if (commandWords.length != 7)
			throw new InvalidCommandException();
		
		String occupantName = commandWords[commandWords.length - 1];
		String cameraStatus = commandWords[4];
		
		String name = commandWords[2];
		String[] appNames = name.split(":");
		
		/**
	     * Lambda expression that interact with House Mate Model Service when occupant is detected in a room
	     * turn on the light
	     * increase the temperature
	     * update knowledge graph
	     */
		Command occupantDetectedCommand = ()-> {
			applianceStatusChangeCommand.execute();
			System.out.println("Lights in room " + name + " is turned on!");
			System.out.println("Temperature in room " + name + " is increased!");
			System.out.println("Knowledge graph is updated!");
			HouseMateModelService.getInstance().changeOccupantLocation(occupantName, appNames[0], appNames[1]);
		}; 
		/**
	     * Lambda expression that interact with House Mate Model Service when occupant is leaving from a room
	     * turn off the light
	     * decrease the temperature
	     * update knowledge graph
	     */
		Command occupantLeavingCommand = ()-> {
			applianceStatusChangeCommand.execute();
			System.out.println("Lights in room " + name + " is turned off!");
			System.out.println("Temperature in room " + name + " is descreased!");
			System.out.println("Knowledge graph is updated!");
			HouseMateModelService.getInstance().changeOccupantLocation(occupantName, appNames[0], null);
		}; 
		
		switch (cameraStatus) {
		case "occupant_detected":
			// occupant detected by camera in a room
			setCommand(appNames[0], appNames[1], "light1", "lightStatus", "on");
			try {
				occupantDetectedCommand.execute();
			} catch (ObjectNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			break;
		case "occupant_leaving":
			// occupant detected leaving by camera in a room
			setCommand(appNames[0], appNames[1], "light1", "lightStatus", "off");
			try {
				occupantLeavingCommand.execute();
			} catch (ObjectNotFoundException e) {
				System.out.println(e.getMessage());
				e.printStackTrace();
			}
			break;
		case "occupant_active":
			/**
		     * Lambda expression that interact with House Mate Model Service when occupant is active in a room
		     * update knowledge graph
		     */
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
			/**
		     * Lambda expression that interact with House Mate Model Service when occupant is active in a room
		     * update knowledge graph
		     */
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
	
	/**
     * Public method that handle fire in a house : 
     * turn on the lights, ask ava to notify occupants, and call 911
     *
     * @param commandWords parameter array that specify the corresponding action
     */	
	public void manageFire(String[] commandWords){
		String name = commandWords[2];
		String[] appNames = name.split(":");
		setCommand(appNames[0], appNames[1], "light1", "lightStatus", "on");
		try {
			applianceStatusChangeCommand.execute();
			System.out.println("All lights turned on!");
		} catch (ObjectNotFoundException e) {
			System.out.println(e.getMessage());
		}
		System.out.println("Ava: Fire in the house, please leave the house immediately");
		try {
			call911Command.execute();
		} catch (ObjectNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
