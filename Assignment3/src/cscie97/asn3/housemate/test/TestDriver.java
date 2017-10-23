package cscie97.asn3.housemate.test;

import cscie97.asn3.housemate.controller.CommandLineInterpreter;
import cscie97.asn3.housemate.controller.ImportException;
import cscie97.asn2.housemate.model.CommandLineInterface;
import cscie97.asn2.housemate.model.CommandException;

/**
 * Test Driver class to invoke the House Mate Model Service.
 *
 * @author Xin Cheng
 */
public class TestDriver {

    /**
     * Static main for TestDriver
     *
     * @param args 2 arguments, set up file name, and trigger command file name.
     */
    public static void main(String[] args) {
    	String setupFile = "housesetup.txt";
    	
    	// Set up house configuration and initial states of appliance
    	CommandLineInterface commandLineInterface = new CommandLineInterface();
		try {
			commandLineInterface.importFile(setupFile);
		} catch (CommandException ce) {
			System.out.println(ce.getMessage());
		}
		System.out.println();
				
		// Set sensor status to invoke rules in HMCS
		String triggerFile = "trigger.txt";
    	CommandLineInterpreter commandLineInterpreter = new CommandLineInterpreter();
    	try {
    		commandLineInterpreter.importFile(triggerFile);
		} catch (ImportException e) {
			System.out.println(e);
		}
    }
}
