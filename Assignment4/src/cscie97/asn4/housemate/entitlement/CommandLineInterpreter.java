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
                System.out.println(line);
                try {
                	lineInterpreter(line);
                } catch (EntityNotFoundException e) {
					e.setFilename(authenticationData);
					e.setLineNumber(lineNumber);
					e.printStackTrace();
				} catch (InvalidCredentialException e) {	
					e.setFilename(authenticationData);
					e.setLineNumber(lineNumber);
					e.printStackTrace();
				}        
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
        // test identify
        try {
        	EntitlementService.getInstance().identify("--sam--");
        } catch (EntityNotFoundException e) {
			e.setFilename(authenticationData);
			e.setLineNumber(lineNumber);
			e.printStackTrace();
		}
	}
	
	public void lineInterpreter(String line) throws EntityNotFoundException, InvalidCredentialException{
		// Get an instance of the entitlement service
		EntitlementService entitlementService = EntitlementService.getInstance();
		// Ignore empty line and command description
		if(line.isEmpty() || line.substring(0, 1).equals("#"))
			return;
		
		// split command by space
		String[] words = line.split(" ");
		String firstWord = words[0];
		switch (firstWord) {
		case "define_role":
			entitlementService.defineRole(words);
			break;
		case "define_permission":
			entitlementService.definePermission(words);
			break;
		case "add_entitlement_to_role":
			entitlementService.addPermissionToRole(words);
			break;
		case "create_resource":
			entitlementService.createResource(words);
			break;
		case "create_resource_role":
			entitlementService.createResourceRole(words);
			break;
		case "create_user":
			entitlementService.createUser(words);
			break;
		case "add_user_credential":
			entitlementService.addUserCredential(words);
			break;
		case "add_resource_role_to_user":
			entitlementService.addResourceRole(words);
			break;
		case "login":
			try {
				entitlementService.login(words);
			} catch (EntityNotFoundException | InvalidCredentialException e) {
				throw e;
			}
			break;
		case "logout":
			entitlementService.logout(words[1]);
			break;
		default:
			break;
		}
	}
}
