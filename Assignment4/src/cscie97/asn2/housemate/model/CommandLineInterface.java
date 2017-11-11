package cscie97.asn2.housemate.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.management.Query;

import cscie97.asn1.knowledge.engine.KnowledgeGraph;
import cscie97.asn1.knowledge.engine.QueryEngine;
import cscie97.asn4.housemate.entitlement.AccessToken;

/**
 * The CommandLineInterface class is responsible for reading command from input setup file. It parse each
 * line read from the setup file and passes the result to corresponding functions in HouseMateModelService.
 *
 * @author Xin
 */
public class CommandLineInterface {
	
	/**
     * Public default constructor
     */
	public CommandLineInterface() {
		
	}
	
	/**
     * Public method for importing setup file. 
     * @param inputFileName name of the setup file
	 * @throws Command on error accesing file
     */
	public void importFile(String fileName) throws CommandException{
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
            	System.out.println(line);
        		executeCommand(line);
            }   
            bufferedReader.close();  
		} catch(FileNotFoundException ex) {
			throw new CommandException(ex.getMessage());
		} catch(IOException ioex) {
			throw new CommandException(ioex.getMessage());
		}
	}
	
	/**
     * Public method for executing a command.
     *
     * @param command a single line from setup file
     */
	public void executeCommand(String command){
		if(command.isEmpty() || command.substring(0, 1).equals("#"))
			return;
		
		HouseMateModelService houseMateModelService = HouseMateModelService.getInstance();
		KnowledgeGraph knowledgeGraph = KnowledgeGraph.getInstance();
		
		// split command by space
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
					knowledgeGraph.importTriple(words[2], "has_address", address);
					break;
				case "room":
					try {
						houseMateModelService.defineRoom(words[2], Integer.parseInt(words[4]), words[6], Integer.parseInt(words[10]), words[8]);
						knowledgeGraph.importTriple(words[8] + ":" + words[2], "on_floor", words[4]);
						knowledgeGraph.importTriple(words[8] + ":" + words[2], "is_of_type", words[6]);
						knowledgeGraph.importTriple(words[8] + ":" + words[2], "has_window_count", words[10]);
					} catch (ObjectNotFoundException onfe) {
						System.out.println(onfe.getMessage());
					}
					break;
				case "occupant":
					houseMateModelService.defineOccupant(words[2], words[4]);
					knowledgeGraph.importTriple(words[2], "is_of_type", words[4]);
					break;
				case "sensor":
					try {
						String[] names = words[6].split(":");
						houseMateModelService.defineSensor(words[2], words[4], names[0], names[1]);
						knowledgeGraph.importTriple(names[0] + ":" + names[1] + ":" + words[2], "is_of_type", words[4]);
						knowledgeGraph.importTriple(names[0] + ":" + names[1] + ":" + words[2], "is_at", names[0]+":"+names[1]);
					} catch (ObjectNotFoundException ex) {
						System.out.println(ex.getMessage());
					}
					break;
				case "appliance":
					try {
						String[] names = words[6].split(":");
						houseMateModelService.defineAppliance(words[2], words[4], names[0], names[1]);
						knowledgeGraph.importTriple(names[0] + ":" + names[1] + ":" + words[2], "is_of_type", words[4]);
						knowledgeGraph.importTriple(names[0] + ":" + names[1] + ":" + words[2], "is_at", names[0]+"_"+names[1]);
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
				knowledgeGraph.importTriple(words[2], "at_house", words[4]);
			} catch (ObjectNotFoundException ex) {
				System.out.println(ex.getMessage());
			}
			break;
		case "set":
			String[] names = words[2].split(":");
			switch (secondWord) {
				case "sensor":
					try {
						houseMateModelService.setSensorStatus(names[0], names[1], names[2], words[4], words[6]);
					} catch (ObjectNotFoundException ex) {
						System.out.println(ex.getMessage());
					}
					break;
				case "appliance":
					try {
						houseMateModelService.setApplianceStatus(names[0], names[1], names[2], words[4], words[6], new AccessToken());
					} catch (ObjectNotFoundException ex) {
						System.out.println(ex.getMessage());
					}
					break;
			}
			break;
		case "show":
			QueryEngine queryEngine = new QueryEngine();
			switch (words.length) {
			case 5:
				queryEngine.executeQuery(words[2] + " " + "has_" + words[4] + " ?.");
				break;
			case 4:
				queryEngine.executeQuery(words[3] + " ?" + " ?.");
				break;
			case 3:
				queryEngine.executeQuery(words[2] + " ?" + " ?.");
				break;
			case 2:
				queryEngine.executeQuery("? ? ?.");
				break;
			default:
				break;
			}
			break;
		default:
			break;
		}
	}
}
