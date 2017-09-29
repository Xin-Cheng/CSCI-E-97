package cscie97.asn2.housemate.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CommandLineInterface {
	
	public CommandLineInterface() {
		
	}
	
	public void importFile(String fileName) throws CommandException{
		HouseMateModelService houseMateModelService = HouseMateModelService.getInstance();
		
		String line = null;
		try {
			FileReader fileReader = new FileReader(fileName);
	        BufferedReader bufferedReader = new BufferedReader(fileReader);
            while((line = bufferedReader.readLine()) != null) {
        		houseMateModelService.executeCommand(line);
            }   
            bufferedReader.close();  
		} catch(FileNotFoundException ex) {
			throw new CommandException(ex.getMessage());
		} catch(IOException ioex) {
			throw new CommandException(ioex.getMessage());
		}
	}
}
