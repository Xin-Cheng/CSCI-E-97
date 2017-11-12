package cscie97.asn4.housemate.test;

import cscie97.asn3.housemate.controller.ImportException;
import cscie97.asn4.housemate.entitlement.AuthenticationSetupException;
import cscie97.asn4.housemate.entitlement.CommandLineInterpreter;
import cscie97.asn4.housemate.entitlement.EntitlementService;

/**
 * Test Driver class to invoke the Entitlement Service.
 *
 * @author Xin Cheng
 */
public class TestDriver {
    /**
     * Static main for TestDriver
     *
     * @param args 3 arguments, set up file name, authentication data file and trigger command file name.
     */
    public static void main(String[] args) {
    	String setupFile = args[0];
    	String authenticationData = args[1];
    	String triggerFile = args[2];
    	
    	CommandLineInterpreter interpreter = new CommandLineInterpreter();
    	try {
    		interpreter.setupHouse(setupFile);
    		interpreter.setupAuthentication(authenticationData);	
    		interpreter.triggerEnvent(triggerFile);
    	} catch (AuthenticationSetupException e) {
			System.out.println(e);
		} catch (ImportException e) {
			System.out.println(e);
		}
    }
}
