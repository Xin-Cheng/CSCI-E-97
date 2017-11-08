package cscie97.asn4.housemate.test;

import cscie97.asn4.housemate.entitlement.AuthenticationSetupException;
import cscie97.asn4.housemate.entitlement.CommandLineInterpreter;

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
    		interpreter.setupAuthentication(authenticationData);	
    	} catch (AuthenticationSetupException e) {
			System.out.println(e);
		}
    	System.out.println("abc".hashCode());
    }
}
