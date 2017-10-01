package cscie97.asn2.housemate.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CommandLineInterface {
	
	public CommandLineInterface() {
		
	}
	
	public void importFile(String fileName) throws CommandException{
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
        		executeCommand(line);
            }   
            bufferedReader.close();  
		} catch(FileNotFoundException ex) {
			throw new CommandException(ex.getMessage());
		} catch(IOException ioex) {
			throw new CommandException(ioex.getMessage());
		}
	}
	
	public void executeCommand(String command){
		HouseMateModelService houseMateModelService = HouseMateModelService.getInstance();
		
		String[] words = command.split(" ");
		String firstWord = words[0];
		String secondWord = words[1];
		switch (firstWord) {
		case "define":
			switch (secondWord) {
				case "house":
					String address = "";
					for (int i = 4; i < words.length; i++ )
						address = address + " " + words[i];
					houseMateModelService.defineHouse(words[2], address);
					break;
				case "room":
					try {
						houseMateModelService.defineRoom(words[2], Integer.parseInt(words[4]), words[6], Integer.parseInt(words[10]), words[8]);
					} catch (ObjectNotFoundException onfe) {
						System.out.println(onfe.getMessage());
					}
					break;
				case "occupant":
					houseMateModelService.defineOccupant(words[2], words[4]);
					break;
				case "sensor":
					try {
						String[] names = words[6].split(":");
						houseMateModelService.defineSensor(words[2], words[4], names[0], names[1]);
					} catch (ObjectNotFoundException ex) {
						System.out.println(ex.getMessage());
					}
					break;
				case "appliance":
					try {
						String[] names = words[6].split(":");
						houseMateModelService.defineAppliance(words[2], words[4], names[0], names[1]);
					} catch (ObjectNotFoundException ex) {
						System.out.println(ex.getMessage());
					}
					break;
				default:
					break;
			}
			break;
		case "add":
			try {
				houseMateModelService.addOccupant(words[2], words[4]);
			} catch (ObjectNotFoundException ex) {
				System.out.println(ex.getMessage());
			}
			break;
		case "set":
			
			break;
//		case "show":
//			show(words);
//			break;
		default:
			break;
		}
	}
}
