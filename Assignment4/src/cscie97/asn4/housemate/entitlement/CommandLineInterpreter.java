package cscie97.asn4.housemate.entitlement;
import cscie97.asn2.housemate.model.CommandLineInterface;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import cscie97.asn2.housemate.model.CommandException;

/**
 * The CommandLineInterpreter class is responsible for reading command file.
 *
 * @author Xin Cheng
 */
public class CommandLineInterpreter {
	/**
     * Public default constructor
     */
	public CommandLineInterpreter() { }
	
	/**
     * Public method for setting up house configuration.
     * Checks for valid input file name.  
     * Throws CommandException on error accessing or processing the input File.
     * 
     * @param setupFile name of the setup file
     */
	public void setupHouse(String setupFile) {
		CommandLineInterface commandLineInterface = new CommandLineInterface();
		try {
			commandLineInterface.importFile(setupFile);
		} catch (CommandException ce) {
			System.out.println(ce.getMessage());
		}
	}
	
	/**
     * Public method for setting up authentication.
     * Checks for valid input file name.  
     * Throws AuthenticationSetupException on error accessing or processing the input File.
     * 
     * @param authenticationData name of the authentication data file
     */
	public void setupAuthentication(String authenticationData) throws AuthenticationSetupException{
        int lineNumber = 0;
        String line = null;
              
        try {
    		FileReader fileReader = new FileReader(authenticationData);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((line = bufferedReader.readLine()) != null) {
                lineNumber++;
//                System.out.println(line);
            }
            bufferedReader.close();  
        } catch (IOException e) {
        	e.printStackTrace();
        	AuthenticationSetupException ie = new AuthenticationSetupException();
        	ie.setFilename(authenticationData);
            ie.setLineNumber(lineNumber);
            ie.setErrorContent(e.getMessage());
            throw ie;
        }
	}
}
