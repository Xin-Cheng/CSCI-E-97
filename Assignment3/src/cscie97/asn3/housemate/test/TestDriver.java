package cscie97.asn3.housemate.test;

import cscie97.asn3.housemate.controller.CommandLineInterpreter;
import cscie97.asn3.housemate.controller.ImportException;

/**
 * Test Driver class to invoke the House Mate Model Service.
 *
 * @author Xin Cheng
 */
public class TestDriver {

    /**
     * Static main for TestDriver
     *
     * @param args 2 arguments, input triple file name, and input query file name.
     */
    public static void main(String[] args) {
    	String fileName = "housesetup2.txt";
    	CommandLineInterpreter commandLineInterpreter = new CommandLineInterpreter();
    	try {
    		commandLineInterpreter.importFile(fileName);
		} catch (ImportException e) {
			System.out.println(e);
		}
    }
}
