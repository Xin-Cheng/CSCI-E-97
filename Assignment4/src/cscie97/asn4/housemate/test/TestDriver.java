package cscie97.asn4.housemate.test;

import cscie97.asn3.housemate.controller.ImportException;
import cscie97.asn4.housemate.entitlement.AuthenticationSetupException;
import cscie97.asn4.housemate.entitlement.CommandLineInterpreter;
import cscie97.asn4.housemate.entitlement.EntitlementService;

public class TestDriver {
    /**
     * Static main for TestDriver
     *
     * @param args 2 arguments, set up file name, and trigger command file name.
     */
    public static void main(String[] args) {
    	String authenticationData = "authenticationData.txt";
    	CommandLineInterpreter interpreter = new CommandLineInterpreter();
    	try {
    		interpreter.setupHouse("housesetup.txt");
    		interpreter.setupAuthentication(authenticationData);	
    		interpreter.triggerEnvent("eventTrigger.txt");
    	} catch (AuthenticationSetupException e) {
			System.out.println(e);
		} catch (ImportException e) {
			System.out.println(e);
		}
    }
}
