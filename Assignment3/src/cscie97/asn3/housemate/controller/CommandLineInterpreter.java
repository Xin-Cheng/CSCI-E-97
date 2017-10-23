package cscie97.asn3.housemate.controller;

import cscie97.asn2.housemate.model.CommandException;
import cscie97.asn2.housemate.model.CommandLineInterface;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/**
 * The CommandLineInterpreter class is responsible for reading command file.
 *
 * @author Xin Cheng
 */

public class CommandLineInterpreter implements ICommandLineInterpreter{
	/**
     * Public default constructor
     */
	public CommandLineInterpreter() { }
	
	/**
     * Public method for setting up house configuration.
     * Checks for valid input file name.  
     * Throws CommandException on error accessing or processing the input File.
     */
	public void setup(String setupFile) {
		CommandLineInterface commandLineInterface = new CommandLineInterface();
		try {
			commandLineInterface.importFile(setupFile);
		} catch (CommandException ce) {
			System.out.println(ce.getMessage());
		}
	}
    /**
     * Public method for importing formatted command file into the House Mate Controller Service. 
     * Checks for valid input file name.  
     * Throws ImportException on error accessing or processing the input File.
     *
     * @param fileName name of the input file
     */	
	public void importFile(String fileName) throws ImportException {
        int lineNumber = 0;
        String line = null;
              
        try {
    		FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
                System.out.println(line);
                
                // Trim leading and trailing whitespace
                line = line.trim();
                if(line.charAt(0) != '#') {
                	interpret(line);
                }
            }
            bufferedReader.close();  
        } catch (FileNotFoundException e) {
        	e.printStackTrace();
        	ImportException ie = new ImportException();
        	ie.setFilename(fileName);
            ie.setLineNumber(lineNumber);
            ie.setErrorContent(e.getMessage());
            throw ie;
        } catch (IOException e) {
            e.printStackTrace();
            ImportException ie = new ImportException();
            ie.setFilename(fileName);
            ie.setLineNumber(lineNumber);
            ie.setErrorContent(e.getMessage());
            throw ie;
        }
    }
	
	public void interpret(String command){
//		HouseMateControllerService.getInstance().executeCommand(command);
	}
}
